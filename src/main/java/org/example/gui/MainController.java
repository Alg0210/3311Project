package org.example.gui;

import org.example.auth.AuthService;
import org.example.equipment.Equipment;
import org.example.users.User;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    private static User currentUser;
    private static Equipment selectedEquipment;

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
                MainApp.switchScene("manager");
                break;
            default:
                MainApp.switchScene("Dashboard");
                break;
        }
    }

    public static void logout() {
        AuthService.logout();
        currentUser = null;
        MainApp.switchScene("LoginPg");
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    private static List<Equipment> cart = new ArrayList<>();

    public static void setCart(List<Equipment> cartItems) {
        cart = cartItems;
    }

    public static List<Equipment> getCart() {
        return cart;
    }

    public static void setSelectedEquipment(Equipment equipment) {
        selectedEquipment = equipment;
    }

    public static Equipment getSelectedEquipment() {
        return selectedEquipment;
    }
}