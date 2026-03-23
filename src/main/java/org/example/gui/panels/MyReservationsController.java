package org.example.gui.panels;

import org.example.auth.AuthService;
import org.example.data.CSVRepository;
import org.example.equipment.Equipment;
import org.example.equipment.EquipmentManager;
import org.example.gui.MainApp;
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
import java.util.List;

public class MyReservationsController {

    @FXML private FlowPane reservationsGrid;
    @FXML private Label userNameLabel;

    private final ReservationManager reservationManager = new ReservationManager();
    private final EquipmentManager equipmentManager     = new EquipmentManager();
    private final CSVRepository repository              = new CSVRepository();

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

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

            Equipment equipment = equipmentManager.getEquipmentById(row[2]);
            if (equipment == null) continue;

            LocalDateTime start = LocalDateTime.parse(row[3]);
            LocalDateTime end   = LocalDateTime.parse(row[4]);

            VBox card = createReservationCard(row[0], equipment, start, end);
            reservationsGrid.getChildren().add(card);
        }
    }

    private VBox createReservationCard(String reservationId, Equipment equipment,
                                       LocalDateTime start, LocalDateTime end) {
        VBox card = new VBox(10);
        card.getStyleClass().add("reservation-card");

        // top row: image + details
        HBox topRow = new HBox(10);

        // placeholder image
        Pane imagePlaceholder = new Pane();
        imagePlaceholder.getStyleClass().add("card-image-placeholder-small");

        // details
        VBox details = new VBox(5);
        Label nameLabel = new Label(equipment.getDescription());
        nameLabel.getStyleClass().add("card-name");

        Label reservedOnLabel = new Label("Reserved on:\n" + start.format(FORMATTER));
        reservedOnLabel.getStyleClass().add("reservation-detail-label");
        reservedOnLabel.setWrapText(true);

        Label endsOnLabel = new Label("Reservation ends on:\n" + end.format(FORMATTER));
        endsOnLabel.getStyleClass().add("reservation-detail-label");
        endsOnLabel.setWrapText(true);

        details.getChildren().addAll(nameLabel, reservedOnLabel, endsOnLabel);
        topRow.getChildren().addAll(imagePlaceholder, details);

        // buttons
        Button cancelButton = new Button("Cancel");
        cancelButton.getStyleClass().add("cancel-res-button");
        cancelButton.setOnAction(e -> handleCancel(reservationId));

        Button extendButton = new Button("Extend");
        extendButton.getStyleClass().add("extend-button");
        extendButton.setOnAction(e -> handleExtend(reservationId, end));

        HBox buttons = new HBox(10, cancelButton, extendButton);
        buttons.setAlignment(Pos.CENTER);

        card.getChildren().addAll(topRow, buttons);
        return card;
    }

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
                initialize();
            }
        });
    }

    private void handleExtend(String reservationId, LocalDateTime currentEnd) {
        // simple dialog to pick new end time
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
            initialize();
        });
    }

    @FXML
    private void handleHome() {
        MainApp.switchScene("Dashboard");
    }
}