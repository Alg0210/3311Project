package org.example.gui.panels;

import org.example.auth.AuthService;
import org.example.gui.MainApp;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegisterController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private ComboBox<String> userTypeCombo;
    @FXML private TextField departmentField;
    @FXML private TextField idNumberField;
    @FXML private Label errorLabel;

    private final AuthService authService = new AuthService();

    @FXML
    public void initialize() {
        errorLabel.setText("");

        userTypeCombo.setItems(FXCollections.observableArrayList(
                "STUDENT", "FACULTY", "RESEARCHER", "GUEST"
        ));

        userTypeCombo.setOnAction(e -> {
            String selected = userTypeCombo.getValue();
            if (selected == null) return;
            switch (selected) {
                case "RESEARCHER":
                    idNumberField.setPromptText("Certification Number");
                    departmentField.setDisable(false);
                    break;
                case "GUEST":
                    idNumberField.setPromptText("ID Number");
                    departmentField.setDisable(true);
                    departmentField.clear();
                    break;
                default:
                    idNumberField.setPromptText("Student/Staff ID");
                    departmentField.setDisable(false);
                    break;
            }
        });
    }

    @FXML
    private void handleRegister() {
        String name         = nameField.getText().trim();
        String email        = emailField.getText().trim();
        String password     = passwordField.getText().trim();
        String confirmPass  = confirmPasswordField.getText().trim();
        String userType     = userTypeCombo.getValue();
        String departmentId = departmentField.getText().trim();
        String idNumber     = idNumberField.getText().trim();

        // validation
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || userType == null) {
            errorLabel.setText("Please fill in all required fields.");
            return;
        }

        if (!password.equals(confirmPass)) {
            errorLabel.setText("Passwords do not match.");
            return;
        }

        if (!userType.equals("GUEST") && idNumber.isEmpty()) {
            errorLabel.setText("Please enter your ID or certification number.");
            return;
        }

        boolean success = authService.register(
                userType, name, email, password, departmentId, idNumber
        );

        if (!success) {
            errorLabel.setText("Registration failed. Check password strength or email already exists.");
            return;
        }

        MainApp.switchScene("LoginPg");
    }

    @FXML
    private void handleCancel() {
        MainApp.switchScene("LoginPg");
    }
}