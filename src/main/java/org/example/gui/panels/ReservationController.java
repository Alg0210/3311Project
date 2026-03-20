package org.example.gui.panels;

import org.example.auth.AuthService;
import org.example.equipment.Equipment;
import org.example.gui.MainApp;
import org.example.gui.MainController;
import org.example.payment.PaymentDecorator;
import org.example.payment.Payment;
import org.example.payment.UserPricingStrategy;
import org.example.reservation.Reservation;
import org.example.reservation.ReservationAction;
import org.example.reservation.ReservationManager;
import org.example.users.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationController {

    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> startTimeCombo;
    @FXML private ComboBox<String> endTimeCombo;
    @FXML private TextField idNumberField;
    @FXML private Label timeErrorLabel;

    @FXML private TextField cardNumberField;
    @FXML private TextField expiryField;
    @FXML private TextField cvcField;
    @FXML private TextField cardholderField;
    @FXML private TextField grantIdField;

    @FXML private ListView<String> orderListView;
    @FXML private Label subtotalLabel;
    @FXML private Label taxLabel;
    @FXML private Label totalLabel;
    @FXML private Label paymentErrorLabel;

    private final ReservationManager reservationManager = new ReservationManager();
    private List<Equipment> cart;
    private String selectedPaymentMethod = null;
    private String selectedAccountReference = null;

    private static final double TAX_RATE = 0.13;

    @FXML
    public void initialize() {
        cart = MainController.getCart();
        timeErrorLabel.setText("");
        paymentErrorLabel.setText("");

        // populate time slots every 30 minutes
        List<String> timeSlots = new ArrayList<>();
        for (int h = 0; h < 24; h++) {
            timeSlots.add(String.format("%02d:00", h));
            timeSlots.add(String.format("%02d:30", h));
        }
        startTimeCombo.setItems(FXCollections.observableArrayList(timeSlots));
        endTimeCombo.setItems(FXCollections.observableArrayList(timeSlots));

        loadOrderSummary();
    }

    private void loadOrderSummary() {
        if (cart == null || cart.isEmpty()) return;

        User currentUser = AuthService.getCurrentUser();
        UserPricingStrategy strategy = new UserPricingStrategy(
                currentUser.getUserType());

        List<String> items = new ArrayList<>();
        double subtotal = 0.0;

        for (Equipment e : cart) {
            double rate = strategy.getHourlyRate();
            items.add(e.getDescription() + "   $" + (int) rate);
            subtotal += rate;
        }

        double tax   = subtotal * TAX_RATE;
        double total = subtotal + tax;

        orderListView.setItems(FXCollections.observableArrayList(items));
        subtotalLabel.setText("$" + String.format("%.2f", subtotal));
        taxLabel.setText("$" + String.format("%.2f", tax));
        totalLabel.setText("$" + String.format("%.2f", total));
    }

    @FXML
    private void handleSelectCard() {
        String cardNumber = cardNumberField.getText().trim();
        if (cardNumber.length() != 8) {
            paymentErrorLabel.setText("Card number must be 8 digits.");
            return;
        }
        selectedPaymentMethod    = "CREDIT";
        selectedAccountReference = cardNumber;
        paymentErrorLabel.setText("Credit/Debit selected.");
        grantIdField.clear();
    }

    @FXML
    private void handleSelectGrant() {
        String grantId = grantIdField.getText().trim();
        if (!grantId.startsWith("GRT-")) {
            paymentErrorLabel.setText("Grant ID must start with GRT-");
            return;
        }
        selectedPaymentMethod    = "GRANT";
        selectedAccountReference = grantId;
        paymentErrorLabel.setText("Grant selected.");
        cardNumberField.clear();
        expiryField.clear();
        cvcField.clear();
        cardholderField.clear();
    }

    @FXML
    private void handleCompletePurchase() {
        // validate time slot
        LocalDate date        = datePicker.getValue();
        String startTimeStr   = startTimeCombo.getValue();
        String endTimeStr     = endTimeCombo.getValue();
        String idNumber       = idNumberField.getText().trim();

        if (date == null || startTimeStr == null || endTimeStr == null) {
            timeErrorLabel.setText("Please select a date and time slot.");
            return;
        }

        if (idNumber.isEmpty()) {
            timeErrorLabel.setText("Please enter your ID or certification number.");
            return;
        }

        if (selectedPaymentMethod == null) {
            paymentErrorLabel.setText("Please select a payment method.");
            return;
        }

        LocalDateTime start = LocalDateTime.of(date,
                LocalTime.parse(startTimeStr));
        LocalDateTime end   = LocalDateTime.of(date,
                LocalTime.parse(endTimeStr));

        if (!end.isAfter(start)) {
            timeErrorLabel.setText("End time must be after start time.");
            return;
        }

        User currentUser = AuthService.getCurrentUser();

        // create a reservation for each item in cart
        List<Reservation> createdReservations = new ArrayList<>();
        for (Equipment equipment : cart) {
            Reservation reservation = reservationManager.createReservation(
                    currentUser, equipment, start, end);

            if (reservation == null) {
                paymentErrorLabel.setText(
                        equipment.getDescription() + " is not available for this time slot.");
                return;
            }

            // process deposit payment
            PaymentDecorator decorator = new PaymentDecorator(
                    new Payment(
                            "PAY-" + System.currentTimeMillis(),
                            reservation.getReservationId(),
                            reservation.getDeposit(),
                            selectedPaymentMethod,
                            true
                    ),
                    selectedPaymentMethod,
                    selectedAccountReference
            );

            Payment processed = decorator.process();
            if (processed == null) {
                paymentErrorLabel.setText("Payment failed. Check your payment details.");
                return;
            }

            createdReservations.add(reservation);
        }

        // clear cart and go to my reservations
        MainController.setCart(new ArrayList<>());
        showAlert("Success", "Reservations confirmed for " + createdReservations.size() + " item(s).");
        MainApp.switchScene("MyReservations");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleCancel() {
        // clear the cart and go back to dashboard
        MainController.setCart(new ArrayList<>());
        MainApp.switchScene("Dashboard");
    }
}