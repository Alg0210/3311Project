package org.example.gui.panels;

import org.example.auth.AuthService;
import org.example.data.CSVRepository;
import org.example.gui.MainApp;
import org.example.gui.MainController;
import org.example.payment.UserPricingStrategy;
import org.example.users.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class AccountController {

    @FXML private Label userNameLabel;
    @FXML private Label userNameTopLabel;
    @FXML private TextField emailField;
    @FXML private TextField idField;
    @FXML private Label updateStatusLabel;
    @FXML private ListView<String> balanceListView;

    private final CSVRepository repository = new CSVRepository();
    private User currentUser;

    @FXML
    public void initialize() {
        currentUser = AuthService.getCurrentUser();
        if (currentUser == null) return;

        userNameLabel.setText(currentUser.getName());
        userNameTopLabel.setText(currentUser.getName());
        emailField.setText(currentUser.getEmail());
        idField.setText(currentUser.getIdNumber() != null ? currentUser.getIdNumber() : "");

        loadBalanceDue();
    }

    private void loadBalanceDue() {
        List<String> balanceItems = new ArrayList<>();
        List<String[]> reservationRows = repository.getAllReservationRows();
        UserPricingStrategy strategy = new UserPricingStrategy(currentUser.getUserType());

        for (String[] row : reservationRows) {
            // row: reservationId, userId, equipmentId, startTime, endTime, status, deposit
            if (!row[1].equals(currentUser.getUserId())) continue;
            if (row[5].equals("CANCELLED")) continue;

            String equipmentId = row[2];
            double deposit = Double.parseDouble(row[6]);
            balanceItems.add(equipmentId + ":   $" + (int) deposit);
        }

        balanceListView.setItems(FXCollections.observableArrayList(balanceItems));
    }

    @FXML
    private void handleUpdateCredentials() {
        String newEmail = emailField.getText().trim();
        String newId    = idField.getText().trim();

        if (newEmail.isEmpty()) {
            updateStatusLabel.setText("Email cannot be empty.");
            return;
        }

        currentUser.setEmail(newEmail);
        currentUser.setIdNumber(newId);
        repository.updateUser(currentUser);
        updateStatusLabel.setText("Credentials updated successfully.");
    }

    @FXML
    private void handleMakePayment() {
        MainApp.switchScene("Payment");
    }

    @FXML
    private void handleHome() {
        MainApp.switchScene("Dashboard");
    }
}
