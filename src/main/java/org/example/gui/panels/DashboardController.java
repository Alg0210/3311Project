package org.example.gui.panels;

import org.example.auth.AuthService;
import org.example.equipment.Equipment;
import org.example.equipment.EquipmentManager;
import org.example.gui.MainApp;
import org.example.gui.MainController;
import org.example.payment.UserPricingStrategy;
import org.example.users.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DashboardController {

    @FXML private MenuButton userMenuButton;
    @FXML private ComboBox<String> sortCombo;
    @FXML private FlowPane equipmentGrid;
    @FXML private ListView<String> cartListView;
    @FXML private Label subtotalLabel;

    private final EquipmentManager equipmentManager = new EquipmentManager();
    private List<Equipment> allEquipment = new ArrayList<>();
    private List<Equipment> cart = new ArrayList<>();

    @FXML
    public void initialize() {
        // set user name in menu button
        User currentUser = AuthService.getCurrentUser();
        if (currentUser != null) {
            userMenuButton.setText(currentUser.getName());
        }

        // populate sort options
        sortCombo.setItems(FXCollections.observableArrayList(
                "Name A-Z",
                "Name Z-A",
                "Price Low-High",
                "Price High-Low",
                "Location"
        ));

        // load equipment
        loadEquipment();
    }

    private void loadEquipment() {
        allEquipment = equipmentManager.getAvailableEquipment();
        populateGrid(allEquipment);
    }

    private void populateGrid(List<Equipment> equipmentList) {
        equipmentGrid.getChildren().clear();
        for (Equipment equipment : equipmentList) {
            VBox card = createEquipmentCard(equipment);
            equipmentGrid.getChildren().add(card);
        }
    }

    private VBox createEquipmentCard(Equipment equipment) {
        VBox card = new VBox(8);
        card.getStyleClass().add("equipment-card");

        // placeholder image pane
        Pane imagePlaceholder = new Pane();
        imagePlaceholder.getStyleClass().add("card-image-placeholder");

        // equipment name
        Label nameLabel = new Label(equipment.getDescription());
        nameLabel.getStyleClass().add("card-name");

        // price label
        User currentUser = AuthService.getCurrentUser();
        double rate = 0.0;
        if (currentUser != null) {
            UserPricingStrategy strategy = new UserPricingStrategy(currentUser.getUserType());
            rate = strategy.getHourlyRate();
        }
        Label priceLabel = new Label("$" + (int) rate + "/hr");
        priceLabel.getStyleClass().add("card-price");

        // buttons
        Button moreButton = new Button("More");
        moreButton.getStyleClass().add("more-button");
        moreButton.setOnAction(e -> handleMore(equipment));

        Button reserveButton = new Button("Reserve");
        reserveButton.getStyleClass().add("reserve-button");
        reserveButton.setOnAction(e -> handleReserve(equipment));

        HBox buttons = new HBox(8, moreButton, reserveButton);
        buttons.setAlignment(javafx.geometry.Pos.CENTER);

        card.getChildren().addAll(imagePlaceholder, nameLabel, priceLabel, buttons);
        return card;
    }

    // ─── SORT ────────────────────────────────────────────────────

    @FXML
    private void handleSort() {
        String selected = sortCombo.getValue();
        if (selected == null) return;

        List<Equipment> sorted = new ArrayList<>(allEquipment);

        switch (selected) {
            case "Name A-Z":
                sorted.sort(Comparator.comparing(Equipment::getDescription));
                break;
            case "Name Z-A":
                sorted.sort(Comparator.comparing(Equipment::getDescription).reversed());
                break;
            case "Location":
                sorted.sort(Comparator.comparing(Equipment::getLabLocation));
                break;
            // price sorting would need user type context
            default:
                break;
        }

        populateGrid(sorted);
    }

    // ─── EQUIPMENT ACTIONS ───────────────────────────────────────

    private void handleMore(Equipment equipment) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Equipment Details");
        alert.setHeaderText(equipment.getDescription());
        alert.setContentText(
                "ID: " + equipment.getEquipmentId() + "\n" +
                        "Location: " + equipment.getLabLocation() + "\n" +
                        "Status: " + equipment.getStatus().name()
        );
        alert.showAndWait();
    }

    private void handleReserve(Equipment equipment) {
        // add to cart
        if (!cart.contains(equipment)) {
            cart.add(equipment);
            updateCart();
        } else {
            showAlert("Already in Cart", equipment.getDescription() + " is already in your cart.");
        }
    }

    // ─── CART ────────────────────────────────────────────────────

    private void updateCart() {
        List<String> cartItems = new ArrayList<>();
        double subtotal = 0.0;
        User currentUser = AuthService.getCurrentUser();
        double rate = 0.0;
        if (currentUser != null) {
            UserPricingStrategy strategy = new UserPricingStrategy(currentUser.getUserType());
            rate = strategy.getHourlyRate();
        }

        for (Equipment e : cart) {
            cartItems.add(e.getDescription() + "  $" + (int) rate);
            subtotal += rate;
        }

        cartListView.setItems(FXCollections.observableArrayList(cartItems));
        subtotalLabel.setText("$" + (int) subtotal);
    }

    @FXML
    private void handleCheckout() {
        if (cart.isEmpty()) {
            showAlert("Empty Cart", "Please add equipment to your cart first.");
            return;
        }
        // store cart in MainController and switch to reservation screen
        MainController.setCart(cart);
        MainApp.switchScene("Reservation");
    }

    // ─── NAV ─────────────────────────────────────────────────────

    @FXML
    private void handleHome() {
        loadEquipment();
    }

    @FXML
    private void handleAccount() {
        MainApp.switchScene("Account");
    }

    @FXML
    private void handleMyReservations() {
        MainApp.switchScene("MyReservations");
    }

    @FXML
    private void handleLogout() {
        MainController.logout();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}