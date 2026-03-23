package org.example.gui.panels;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import org.example.equipment.Equipment;
import org.example.equipment.EquipmentManager;
import org.example.gui.MainApp;
import org.example.gui.MainController;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EquipmentDetailController {

    @FXML private StackPane imagePane;
    @FXML private ImageView detailImageView;
    @FXML private Label     changeImageOverlay;

    @FXML private Label nameLabel;
    @FXML private Label descLabel;
    @FXML private Label unitsLabel;
    @FXML private Label stat1Label;
    @FXML private Label stat2Label;
    @FXML private Label statProductLabel;
    @FXML private HBox  tagsBox;

    private final EquipmentManager equipmentManager = new EquipmentManager();
    private Equipment equipment;

    @FXML
    public void initialize() {
        equipment = MainController.getSelectedEquipment();
        if (equipment == null) return;

        // ── Image ──────────────────────────────────────────────
        String imgPath = equipment.getImagePath();
        if (imgPath != null && !imgPath.isEmpty()) {
            File imgFile = new File(imgPath);
            if (imgFile.exists()) {
                try {
                    Image img = new Image(imgFile.toURI().toString(), 290, 230, false, true);
                    detailImageView.setImage(img);
                    detailImageView.setVisible(true);
                } catch (Exception ignored) { }
            }
        }

        imagePane.setOnMouseEntered(e -> changeImageOverlay.setVisible(true));
        imagePane.setOnMouseExited(e  -> changeImageOverlay.setVisible(false));

        // ── Left panel ─────────────────────────────────────────
        String displayName = (equipment.getName() != null && !equipment.getName().isEmpty())
                ? equipment.getName() : equipment.getDescription();
        nameLabel.setText(displayName);
        descLabel.setText("Lab: " + equipment.getLabLocation());
        unitsLabel.setText("Available Units: " + equipment.getAvailableUnits());

        // ── Product Stats ───────────────────────────
        stat1Label.setText("Equipment ID:  " + equipment.getEquipmentId());
        stat2Label.setText("Status:        " + equipment.getStatus().name());

        String prodStats = equipment.getProductStatistics();
        if (prodStats != null && !prodStats.isEmpty()) {
            statProductLabel.setText("Notes:         " + prodStats);
        } else {
            statProductLabel.setText("");
        }

        // ── Tags ────────────────────────────────────
        tagsBox.getChildren().clear();
        if (equipment.getTags() != null && !equipment.getTags().isEmpty()) {
            for (String tag : equipment.getTags()) {
                Label chip = new Label(tag);
                chip.getStyleClass().add("manage-tag");
                tagsBox.getChildren().add(chip);
            }
        } else {
            Label locTag = new Label(equipment.getLabLocation());
            locTag.getStyleClass().add("manage-tag");
            Label statusTag = new Label(equipment.getStatus().name());
            statusTag.getStyleClass().add("manage-tag");
            tagsBox.getChildren().addAll(locTag, statusTag);
        }
    }

    private static final Set<String> PREVIEWABLE = new HashSet<>(
            Arrays.asList("jpg", "jpeg", "png"));

    @FXML
    private void handleChangeImage() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Change Equipment Photo");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Image Files (JPEG, PNG, HEIC)",
                        "*.jpg", "*.jpeg", "*.png",
                        "*.heic", "*.heif",
                        "*.JPG", "*.JPEG", "*.PNG",
                        "*.HEIC", "*.HEIF"
                )
        );

        File file = chooser.showOpenDialog(MainApp.getPrimaryStage());
        if (file == null) return;

        equipment.setImagePath(file.getAbsolutePath());
        equipmentManager.updateEquipment(equipment);

        String ext = file.getName().contains(".")
                ? file.getName().substring(file.getName().lastIndexOf('.') + 1).toLowerCase() : "";

        if (PREVIEWABLE.contains(ext)) {
            try {
                Image img = new Image(file.toURI().toString(), 290, 230, false, true);
                detailImageView.setImage(img);
                detailImageView.setVisible(true);
            } catch (Exception ignored) { }
        }
    }

    @FXML private void handleBack() { MainApp.switchScene("Manager"); }


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
