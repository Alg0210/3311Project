package org.example.gui.panels;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.example.equipment.Equipment;
import org.example.equipment.EquipmentManager;
import org.example.gui.MainApp;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddEquipmentController {

    @FXML private StackPane    imagePlaceholder;
    @FXML private ImageView    imagePreview;
    @FXML private VBox         uploadOverlay;

    @FXML private TextField  nameField;
    @FXML private TextArea   descriptionArea;
    @FXML private TextField  locationField;
    @FXML private Label      unitCountLabel;
    @FXML private TextArea   statsArea;

    // Tag toggles
    @FXML private ToggleButton tagChemistry;
    @FXML private ToggleButton tagHandheld;
    @FXML private ToggleButton tagIndividual;
    @FXML private ToggleButton tagPhysics;
    @FXML private ToggleButton tagInLab;
    @FXML private ToggleButton tagPartnered;

    private int    unitCount        = 0;
    private String selectedImagePath = "";

    // Extensions that JavaFX Image
    private static final Set<String> PREVIEWABLE = new HashSet<>(
            Arrays.asList("jpg", "jpeg", "png"));

    private final EquipmentManager equipmentManager = new EquipmentManager();

    @FXML
    public void initialize() {
        unitCountLabel.setText("0");
    }

    // ─── Image Picker ────────────────────────────────────────────

    @FXML
    private void handlePickImage() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Equipment Photo");
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

        selectedImagePath = file.getAbsolutePath();
        String ext = getExtension(file.getName());

        if (PREVIEWABLE.contains(ext)) {
            // Show live preview for JPEG / PNG
            try {
                Image img = new Image(file.toURI().toString(), 295, 240, true, true);
                imagePreview.setImage(img);
                imagePreview.setVisible(true);
                uploadOverlay.setVisible(false);
            } catch (Exception ex) {
                showNonPreviewableFeedback(file.getName());
            }
        } else {
            showNonPreviewableFeedback(file.getName());
        }
    }

    /** Image pane UI */
    private void showNonPreviewableFeedback(String fileName) {
        imagePreview.setVisible(false);
        // Replace overlay content with a short "file selected" message
        uploadOverlay.setVisible(true);
        uploadOverlay.getChildren().clear();
        Label icon = new Label("📷");
        icon.setStyle("-fx-font-size:32px;");
        Label name = new Label(fileName);
        name.setStyle("-fx-text-fill:white; -fx-font-size:11px; -fx-wrap-text:true; -fx-text-alignment:center;");
        Label ok   = new Label("File selected ✓");
        ok.setStyle("-fx-text-fill:#7ec87e; -fx-font-size:12px; -fx-font-weight:bold;");
        uploadOverlay.getChildren().addAll(icon, name, ok);
    }

    // ─── Unit Increate and Decrement ────────────────────────────────────────────

    @FXML
    private void handleDecrement() {
        if (unitCount > 0) {
            unitCount--;
            unitCountLabel.setText(String.valueOf(unitCount));
        }
    }

    @FXML
    private void handleIncrement() {
        unitCount++;
        unitCountLabel.setText(String.valueOf(unitCount));
    }

    // ─── Save ────────────────────────────────────────────────────

    @FXML
    private void handleAdd() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            showAlert("Validation Error", "Equipment Name is required.");
            return;
        }

        String equipmentId = "EQ-" + System.currentTimeMillis();

        String description = descriptionArea.getText().trim();
        if (description.isEmpty()) description = name;

        String location = locationField.getText().trim();
        if (location.isEmpty()) location = "TBD";

        Equipment equipment = new Equipment(equipmentId, description, location);
        equipment.setName(name);
        equipment.setAvailableUnits(unitCount);
        equipment.setProductStatistics(statsArea.getText().trim());
        equipment.setTags(getSelectedTags());
        equipment.setImagePath(selectedImagePath);

        equipmentManager.addEquipment(equipment);
        showAlert("Success", "\"" + name + "\" has been added successfully.");
        MainApp.switchScene("Manager");
    }

    @FXML
    private void handleBack() {
        MainApp.switchScene("Manager");
    }

    // ─── Helpers ─────────────────────────────────────────────────

    private List<String> getSelectedTags() {
        List<String> selected = new ArrayList<>();
        if (tagChemistry.isSelected())  selected.add("Chemistry");
        if (tagHandheld.isSelected())   selected.add("Handheld");
        if (tagIndividual.isSelected()) selected.add("Individual");
        if (tagPhysics.isSelected())    selected.add("Physics");
        if (tagInLab.isSelected())      selected.add("In-Lab");
        if (tagPartnered.isSelected())  selected.add("Partnered");
        return selected;
    }

    private String getExtension(String fileName) {
        int dot = fileName.lastIndexOf('.');
        return (dot >= 0) ? fileName.substring(dot + 1).toLowerCase() : "";
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

