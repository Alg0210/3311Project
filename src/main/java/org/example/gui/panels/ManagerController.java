package org.example.gui.panels;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.example.auth.AuthService;
import org.example.data.CSVRepository;
import org.example.equipment.Equipment;
import org.example.equipment.EquipmentManager;
import org.example.gui.MainApp;
import org.example.gui.MainController;
import org.example.users.HeadCoordinator;
import org.example.users.User;

import java.util.*;

public class ManagerController {

    @FXML private Label categoryLabel;
    @FXML private MenuButton userMenuButton;
    @FXML private ComboBox<String> sortCombo;
    @FXML private FlowPane equipmentGrid;

    private final EquipmentManager equipmentManager = new EquipmentManager();
    private final CSVRepository repository = new CSVRepository();
    private List<Equipment> allEquipment = new ArrayList<>();

    @FXML
    public void initialize() {
        User currentUser = AuthService.getCurrentUser();
        if (currentUser != null) {
            userMenuButton.setText(currentUser.getName());
            if (currentUser.getUserType().equalsIgnoreCase("HEAD_COORDINATOR")) {
                MenuItem genItem = new MenuItem("Generate Manager Account");
                genItem.setOnAction(e -> handleGenerateManagerAccount());
                userMenuButton.getItems().add(genItem);
            }
        }
        sortCombo.setItems(FXCollections.observableArrayList(
                "Name A-Z", "Name Z-A", "Status", "Location"));
        loadEquipment();
    }

    private void loadEquipment() {
        allEquipment = equipmentManager.getAllEquipment();
        populateGrid(allEquipment);
    }

    private void populateGrid(List<Equipment> list) {
        equipmentGrid.getChildren().clear();
        for (Equipment e : list) equipmentGrid.getChildren().add(createEquipmentCard(e));
        equipmentGrid.getChildren().add(createAddCard());
    }

    private VBox createEquipmentCard(Equipment equipment) {
        VBox card = new VBox(8);
        card.getStyleClass().add("equipment-card");

        Pane img = new Pane();
        img.getStyleClass().add("card-image-placeholder");

        Label name = new Label(equipment.getDescription());
        name.getStyleClass().add("card-name");

        Label loc = new Label(equipment.getLabLocation());
        loc.getStyleClass().add("card-price");

        Label status = new Label(equipment.getStatus().name());
        status.getStyleClass().addAll("status-badge",
                "status-" + equipment.getStatus().name().toLowerCase());

        Button moreBtn = new Button("More");
        moreBtn.getStyleClass().add("more-button");
        moreBtn.setOnAction(e -> handleMore(equipment));

        Button manageBtn = new Button("Manage");
        manageBtn.getStyleClass().add("manage-button");
        manageBtn.setOnAction(e -> handleManage(equipment));

        HBox btns = new HBox(8, moreBtn, manageBtn);
        btns.setAlignment(Pos.CENTER);

        card.getChildren().addAll(img, name, loc, status, btns);
        return card;
    }

    private VBox createAddCard() {
        VBox card = new VBox();
        card.getStyleClass().add("add-equipment-card");
        card.setAlignment(Pos.CENTER);
        card.setOnMouseClicked(e -> handleAddEquipment());
        Label plus = new Label("+");
        plus.getStyleClass().add("add-icon-label");
        card.getChildren().add(plus);
        return card;
    }

    @FXML
    private void handleSort() {
        String sel = sortCombo.getValue();
        if (sel == null) return;
        List<Equipment> sorted = new ArrayList<>(allEquipment);
        switch (sel) {
            case "Name A-Z": sorted.sort(Comparator.comparing(Equipment::getDescription)); break;
            case "Name Z-A": sorted.sort(Comparator.comparing(Equipment::getDescription).reversed()); break;
            case "Status":   sorted.sort(Comparator.comparing(e -> e.getStatus().name())); break;
            case "Location": sorted.sort(Comparator.comparing(Equipment::getLabLocation)); break;
        }
        populateGrid(sorted);
    }

    private void handleMore(Equipment eq) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Equipment Details");
        a.setHeaderText(eq.getDescription());
        a.setContentText("ID: " + eq.getEquipmentId() + "\nLocation: " + eq.getLabLocation()
                + "\nStatus: " + eq.getStatus().name());
        a.showAndWait();
    }

    private void handleManage(Equipment eq) {
        MainController.setSelectedEquipment(eq);
        MainApp.switchScene("EquipmentDetail");
    }

    private void handleAddEquipment() {
        Optional<String> idRes = prompt("Add Equipment", "Step 1/3: Equipment ID", "Equipment ID:");
        if (!idRes.isPresent() || idRes.get().trim().isEmpty()) return;

        Optional<String> descRes = prompt("Add Equipment", "Step 2/3: Description", "Description:");
        if (!descRes.isPresent() || descRes.get().trim().isEmpty()) return;

        Optional<String> locRes = prompt("Add Equipment", "Step 3/3: Lab Location", "Location:");
        if (!locRes.isPresent() || locRes.get().trim().isEmpty()) return;

        equipmentManager.addEquipment(
                new Equipment(idRes.get().trim(), descRes.get().trim(), locRes.get().trim()));
        loadEquipment();
        showAlert("Success", "Equipment added successfully.");
    }

    private void handleGenerateManagerAccount() {
        Optional<String> name  = prompt("Generate Manager", "New Lab Manager", "Full Name:");
        if (!name.isPresent()  || name.get().trim().isEmpty()) return;
        Optional<String> email = prompt("Generate Manager", "New Lab Manager", "Email:");
        if (!email.isPresent() || email.get().trim().isEmpty()) return;
        Optional<String> pass  = prompt("Generate Manager", "New Lab Manager", "Temporary Password:");
        if (!pass.isPresent()  || pass.get().trim().isEmpty()) return;

        User cu = AuthService.getCurrentUser();
        HeadCoordinator hc = HeadCoordinator.getInstance(cu.getUserId(), cu.getName(), cu.getEmail(), cu.getPassword());
        String mgrId = "MGR-" + System.currentTimeMillis();
        org.example.users.LabManager mgr = hc.generateManagerAccount(mgrId, name.get().trim(), email.get().trim(), pass.get().trim());
        repository.saveUser(mgr);
        showAlert("Success", "Manager account created for " + mgr.getName() + ".");
    }

    @FXML private void handleAccount() { MainApp.switchScene("Account"); }
    @FXML private void handleLogout()  { MainController.logout(); }

    private Optional<String> prompt(String title, String header, String content) {
        TextInputDialog d = new TextInputDialog();
        d.setTitle(title); d.setHeaderText(header); d.setContentText(content);
        return d.showAndWait();
    }

    private void showAlert(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title); a.setContentText(msg); a.showAndWait();
    }
}
