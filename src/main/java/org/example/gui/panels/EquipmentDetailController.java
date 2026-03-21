package org.example.gui.panels;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import org.example.equipment.Equipment;
import org.example.equipment.EquipmentManager;
import org.example.gui.MainApp;
import org.example.gui.MainController;

public class EquipmentDetailController {

    @FXML private Label nameLabel;
    @FXML private Label descLabel;
    @FXML private Label unitsLabel;
    @FXML private Label stat1Label;
    @FXML private Label stat2Label;
    @FXML private Label stat3Label;
    @FXML private Label stat4Label;
    @FXML private Label stat5Label;
    @FXML private HBox  tagsBox;

    private final EquipmentManager equipmentManager = new EquipmentManager();
    private Equipment equipment;

    @FXML
    public void initialize() {
        equipment = MainController.getSelectedEquipment();
        if (equipment == null) return;

        nameLabel.setText(equipment.getDescription());
        descLabel.setText("Lab: " + equipment.getLabLocation());
        unitsLabel.setText("Available Units: 1");

        stat1Label.setText("Equipment ID:  " + equipment.getEquipmentId());
        stat2Label.setText("Location:      " + equipment.getLabLocation());
        stat3Label.setText("Status:        " + equipment.getStatus().name());
        stat4Label.setText("");
        stat5Label.setText("");

        // Tag chips
        Label locTag    = new Label(equipment.getLabLocation());
        locTag.getStyleClass().add("manage-tag");
        Label statusTag = new Label(equipment.getStatus().name());
        statusTag.getStyleClass().add("manage-tag");
        tagsBox.getChildren().addAll(locTag, statusTag);
    }

    @FXML private void handleBack() { MainApp.switchScene("Manager"); }

    @FXML
    private void handleViewStatus() {
        showAlert("Equipment Status",
                equipment.getDescription() + "\nCurrent Status: " + equipment.getStatus().name());
    }

    @FXML
    private void handleMark() {
        equipmentManager.setMaintenance(equipment.getEquipmentId());
        showAlert("Marked for Maintenance", equipment.getDescription() + " → MAINTENANCE");
        MainApp.switchScene("Manager");
    }

    @FXML
    private void handleDisable() {
        equipmentManager.disableEquipment(equipment.getEquipmentId());
        showAlert("Disabled", equipment.getDescription() + " → DISABLED");
        MainApp.switchScene("Manager");
    }

    @FXML
    private void handleEnable() {
        equipmentManager.enableEquipment(equipment.getEquipmentId());
        showAlert("Enabled", equipment.getDescription() + " → AVAILABLE");
        MainApp.switchScene("Manager");
    }

    private void showAlert(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setContentText(msg);
        a.showAndWait();
    }
}
