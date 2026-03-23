package org.example.gui.panels;

import org.example.auth.AuthService;
import org.example.data.CSVRepository;
import org.example.equipment.Equipment;
import org.example.equipment.EquipmentManager;
import org.example.gui.MainApp;
import org.example.payment.Payment;
import org.example.reservation.Reservation;
import org.example.reservation.ReservationAction;
import org.example.reservation.ReservationManager;
import org.example.reservation.ReservationStatus;
import org.example.users.User;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyReservationsController {

    @FXML private FlowPane reservationsGrid;
    @FXML private Label userNameLabel;

    private final ReservationManager reservationManager = new ReservationManager();
    private final EquipmentManager equipmentManager     = new EquipmentManager();
    private final CSVRepository repository              = new CSVRepository();

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

    // track check in times per reservation so Complete Session can use them
    private final Map<String, LocalDateTime> checkInTimes = new HashMap<>();
    // track which payment method was used per reservation
    private final Map<String, String> paymentMethods = new HashMap<>();

    @FXML
    public void initialize() {
        User currentUser = AuthService.getCurrentUser();
        if (currentUser == null) return;

        userNameLabel.setText(currentUser.getName());
        loadReservations(currentUser);
    }

    private void loadReservations(User currentUser) {
        reservationsGrid.getChildren().clear();
        List<String[]> rows = repository.getAllReservationRows();

        for (String[] row : rows) {
            if (!row[1].equals(currentUser.getUserId())) continue;
            if (row[5].equals(ReservationStatus.CANCELLED.name())) continue;
            if (row[5].equals(ReservationStatus.COMPLETED.name())) continue;

            Equipment equipment = equipmentManager.getEquipmentById(row[2]);
            if (equipment == null) continue;

            LocalDateTime start = LocalDateTime.parse(row[3]);
            LocalDateTime end   = LocalDateTime.parse(row[4]);

            VBox card = createReservationCard(row[0], equipment, start, end, row[5]);
            reservationsGrid.getChildren().add(card);
        }
    }

    private VBox createReservationCard(String reservationId, Equipment equipment,
                                       LocalDateTime start, LocalDateTime end,
                                       String status) {
        VBox card = new VBox(10);
        card.getStyleClass().add("reservation-card");

        // top row: image + details
        HBox topRow = new HBox(10);

        Pane imagePlaceholder = new Pane();
        imagePlaceholder.getStyleClass().add("card-image-placeholder-small");

        VBox details = new VBox(5);
        Label nameLabel = new Label(equipment.getDescription());
        nameLabel.getStyleClass().add("card-name");

        Label reservedOnLabel = new Label("Reserved on:\n" + start.format(FORMATTER));
        reservedOnLabel.getStyleClass().add("reservation-detail-label");
        reservedOnLabel.setWrapText(true);

        Label endsOnLabel = new Label("Reservation ends on:\n" + end.format(FORMATTER));
        endsOnLabel.getStyleClass().add("reservation-detail-label");
        endsOnLabel.setWrapText(true);

        Label statusLabel = new Label("Status: " + status);
        statusLabel.getStyleClass().add("reservation-detail-label");

        details.getChildren().addAll(nameLabel, reservedOnLabel, endsOnLabel, statusLabel);
        topRow.getChildren().addAll(imagePlaceholder, details);

        // ─── BUTTONS ─────────────────────────────────────────────

        Button cancelButton = new Button("Cancel");
        cancelButton.getStyleClass().add("cancel-res-button");
        cancelButton.setOnAction(e -> handleCancel(reservationId));

        Button extendButton = new Button("Extend");
        extendButton.getStyleClass().add("extend-button");
        extendButton.setOnAction(e -> handleExtend(reservationId, end));

        Button checkInButton = new Button("Check In");
        checkInButton.getStyleClass().add("checkin-button");
        checkInButton.setOnAction(e -> handleCheckIn(reservationId, start, checkInButton));

        Button completeButton = new Button("Complete Session");
        completeButton.getStyleClass().add("complete-button");
        completeButton.setDisable(true); // disabled until checked in
        completeButton.setOnAction(e -> handleCompleteSession(reservationId, completeButton));

        // store reference so check in can enable complete button
        checkInButton.setUserData(completeButton);

        HBox buttons = new HBox(8, cancelButton, extendButton, checkInButton, completeButton);
        buttons.setAlignment(Pos.CENTER);




        card.getChildren().addAll(topRow, buttons);
        return card;
    }

    // ─── CHECK IN ────────────────────────────────────────────────

    private void handleCheckIn(String reservationId, LocalDateTime start,
                               Button checkInButton) {
        LocalDateTime arrivalTime = LocalDateTime.now();


        if (arrivalTime.isBefore(start)) {
            showAlert("Cannot Check In",
                    "Your reservation has not started yet. It starts on " +
                            start.format(FORMATTER) + ".");
            return;
        }
        checkInTimes.put(reservationId, arrivalTime);

        Reservation reservation = reservationManager.getReservationById(reservationId);
        if (reservation == null) return;

        boolean onTime = reservation.arrivedOnTime(arrivalTime);

        if (onTime) {
            showAlert("Checked In",
                    "You have checked in on time. Your deposit will be deducted from the total.");
        } else {
            showAlert("Late Arrival",
                    "You have arrived after the 20 minute window. Your deposit of $" +
                            (int) reservation.getDeposit() + " has been forfeited.");
        }

        // disable check in and enable complete session
        checkInButton.setDisable(true);
        Button completeButton = (Button) checkInButton.getUserData();
        if (completeButton != null) {
            completeButton.setDisable(false);
        }

        // also disable cancel and extend after check in
        HBox buttons = (HBox) checkInButton.getParent();
        buttons.getChildren().forEach(node -> {
            if (node instanceof Button) {
                Button btn = (Button) node;
                if (btn.getText().equals("Cancel")) {
                    btn.setDisable(true);
                }
            }
        });
    }

    // ─── COMPLETE SESSION ─────────────────────────────────────────

    private void handleCompleteSession(String reservationId, Button completeButton) {
        LocalDateTime arrivalTime = checkInTimes.get(reservationId);
        if (arrivalTime == null) {
            showAlert("Error", "Please check in first.");
            return;
        }

        Reservation reservation = reservationManager.getReservationById(reservationId);
        if (reservation == null) return;

        boolean arrivedOnTime = reservation.arrivedOnTime(arrivalTime);

        // ask for payment method for final payment
        List<String> choices = new java.util.ArrayList<>();
        choices.add("CREDIT");
        choices.add("DEBIT");
        choices.add("INSTITUTIONAL");
        choices.add("GRANT");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("CREDIT", choices);
        dialog.setTitle("Final Payment");
        dialog.setHeaderText("Select payment method for final payment:");
        dialog.showAndWait().ifPresent(method -> {

            Payment finalPayment = reservationManager.processFinalPayment(
                    reservation, method, "", arrivedOnTime);

            if (finalPayment != null) {
                double amount = finalPayment.getAmount();
                showAlert("Session Complete",
                        "Session completed.\nFinal payment of $" +
                                String.format("%.2f", amount) + " processed via " + method + ".");
                completeButton.setDisable(true);
                // reload to remove completed reservation from view
                initialize();
            }
        });
    }

    // ─── CANCEL ──────────────────────────────────────────────────

    private void handleCancel(String reservationId) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Cancel Reservation");
        confirm.setContentText("Are you sure you want to cancel this reservation?");
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                ReservationAction action = new ReservationAction(
                        reservationManager,
                        reservationManager.getReservationById(reservationId),
                        "CANCEL"
                );
                action.execute();
                Reservation updated = reservationManager.getReservationById(reservationId);
                if (updated != null && updated.getStatus() == ReservationStatus.CANCELLED) {
                    showAlert("Cancelled", "Reservation cancelled successfully.");
                    initialize();
                } else {
                    showAlert("Cancellation Failed",
                            "Cannot cancel — reservation has already started.");
                }
            }
        });
    }

    // ─── EXTEND ──────────────────────────────────────────────────

    private void handleExtend(String reservationId, LocalDateTime currentEnd) {
        List<String> choices = new java.util.ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            choices.add("+ " + i + " hour(s) → " +
                    currentEnd.plusHours(i).format(FORMATTER));
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.setTitle("Extend Reservation");
        dialog.setHeaderText("Select how long to extend:");

        dialog.showAndWait().ifPresent(choice -> {
            int hours = Integer.parseInt(choice.substring(2, 3));
            LocalDateTime newEnd = currentEnd.plusHours(hours);

            ReservationAction action = new ReservationAction(
                    reservationManager,
                    reservationManager.getReservationById(reservationId),
                    "EXTEND",
                    newEnd
            );
            action.execute();
            Reservation updated = reservationManager.getReservationById(reservationId);
            if (updated != null && updated.getEndTime().equals(newEnd)) {
                showAlert("Extended", "Reservation extended successfully.");
                initialize();
            } else {
                showAlert("Extension Failed",
                        "Cannot extend — equipment is already booked for that time slot.");
            }
        });
    }

    @FXML
    private void handleHome() {
        MainApp.switchScene("Dashboard");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}