package org.example.gui.panels;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.auth.AuthService;
import org.example.gui.MainController;
import org.example.users.User;

public class LoginController {

    @FXML
    private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private final AuthService authService = new AuthService();

    @FXML
    public void initialize() {
        errorLabel.setText("");
    }

    @FXML
    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please fill in all fields.");
            return;
        }

        User user = authService.login(email, password);

        if (user == null) {
            errorLabel.setText("Invalid email or password.");
            return;
        }

        MainController.onLoginSuccess(user);
    }

    @FXML
    private void handleRegister() {
       org.example.gui.MainApp.switchScene("RegisterPage");
    }
}
