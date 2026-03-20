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
    @FXML private ComboBox<String> userTypeCombo;
    @FXML private TextField departmentField;
    @FXML private TextField idNumberField;
    @FXML private Label idNumberLabel;
    @FXML private Label errorLabel;

    private final AuthService authService = new AuthService();

    @FXML
    public void initialize() {
        errorLabel.setText("");

        // populate user type dropdown
        userTypeCombo.setItems(FXCollections.observableArrayList(
                "STUDENT", "FACULTY", "RESEARCHER", "GUEST"
        ));

        // dynamically change the ID number label based on user type selected
        userTypeCombo.setOnAction(e -> {
            String selected = userTypeCombo.getValue();
            if (selected == null) return;
            switch (selected) {
                case "RESEARCHER":
                    idNumberLabel.setText("Certification Number");
                    idNumberField.setPromptText("Enter your certification number");
                    departmentField.setDisable(false);
                    break;
                case "GUEST":
                    idNumberLabel.setText("ID Number");
                    idNumberField.setPromptText("Enter your ID number");
                    departmentField.setDisable(true);
                    departmentField.clear();
                    break;
                default:
                    idNumberLabel.setText("Student/Staff ID");
                    idNumberField.setPromptText("Enter your student/staff ID");
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
        String userType     = userTypeCombo.getValue();
        String departmentId = departmentField.getText().trim();
        String idNumber     = idNumberField.getText().trim();

        // basic validation
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || userType == null) {
            errorLabel.setText("Please fill in all required fields.");
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
            errorLabel.setText("Registration failed. Check your password strength or email is already taken.");
            return;
        }

        // go back to login after successful registration
        MainApp.switchScene("LoginPg");
    }

    @FXML
    private void handleCancel() {
        MainApp.switchScene("LoginPg");
    }
}