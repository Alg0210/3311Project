package org.example.gui;

import org.example.auth.AuthService;
import org.example.users.User;

public class MainController {

    private static User currentUser;

    // call this after a successful login
    public static void onLoginSuccess(User user) {
        currentUser = user;
        routeByUserType(user);
    }

    // routes to the correct screen based on who logged in
    private static void routeByUserType(User user) {
        switch (user.getUserType()) {
            case "HEAD_COORDINATOR":
                MainApp.switchScene("HeadCoordinator");
                break;
            case "MANAGER":
                MainApp.switchScene("Dashboard");
                break;
            default:
                MainApp.switchScene("Dashboard");
                break;
        }
    }

    public static void logout() {
        AuthService.logout();
        currentUser = null;
        MainApp.switchScene("Login");
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}