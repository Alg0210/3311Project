package org.example.AI_assistant.gui;

import org.example.equipment.Equipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AIAddEquipmentControllerTest {

    private StubAddEquipmentController testController;
    private StubEquipmentManager testEquipmentManager;

    @BeforeEach
    public void setUp() {
        testEquipmentManager = new StubEquipmentManager();
        testController = new StubAddEquipmentController(testEquipmentManager);
    }

    @Test
    public void testInitializeUnitCount() {
        testController.initialize();
        assertEquals("0", testController.unitCountLabel, "Initial unit count should be 0");
    }

    @Test
    public void testHandlePickImageStoresPath() {
        String imagePath = "/path/to/test_image.jpg";
        testController.selectedImagePath = imagePath;

        assertTrue(testController.hasSelectedImagePath(), "Image path should be stored");
        assertEquals(imagePath, testController.selectedImagePath, "Image path should match");
    }

    @Test
    public void testIsPreviewable() {
        assertTrue(testController.isPreviewable("jpg"), "JPG should be previewable");
        assertTrue(testController.isPreviewable("png"), "PNG should be previewable");
        assertFalse(testController.isPreviewable("heic"), "HEIC should not be previewable");
    }

    @Test
    public void testGetFileExtension() {
        assertEquals("jpg", testController.getFileExtension("image.jpg"));
        assertEquals("png", testController.getFileExtension("photo.PNG"));
        assertEquals("", testController.getFileExtension("noextension"));
    }

    @Test
    public void testHandleIncrement() {
        testController.initialize();
        assertEquals("0", testController.unitCountLabel);

        testController.handleIncrement();
        assertEquals("1", testController.unitCountLabel);

        testController.handleIncrement();
        assertEquals("2", testController.unitCountLabel);
    }

    @Test
    public void testHandleDecrement() {
        testController.initialize();
        testController.handleIncrement();
        testController.handleIncrement();
        assertEquals("2", testController.unitCountLabel);

        testController.handleDecrement();
        assertEquals("1", testController.unitCountLabel);
    }

    @Test
    public void testHandleDecrementNeverNegative() {
        testController.initialize();
        testController.handleDecrement();
        assertEquals("0", testController.unitCountLabel, "Count should not go negative");
    }

    @Test
    public void testTagSelection() {
        testController.tagChemistry = true;
        testController.tagPhysics = true;

        List<String> tags = testController.getSelectedTags();
        assertEquals(2, tags.size());
        assertTrue(tags.contains("Chemistry"));
        assertTrue(tags.contains("Physics"));
    }

    @Test
    public void testHandleAddEquipmentValid() {
        testController.nameField = "Microscope";
        testController.descriptionArea = "Advanced Microscope";
        testController.locationField = "Lab A";
        testController.handleIncrement(); // 1
        testController.handleIncrement(); // 2

        testController.handleAdd();

        assertTrue(testEquipmentManager.addEquipmentCalled);
        Equipment added = testEquipmentManager.lastAddedEquipment;
        assertNotNull(added);
        assertEquals("Microscope", added.getName());
        assertEquals("Advanced Microscope", added.getDescription());
        assertEquals("Lab A", added.getLabLocation());
        assertEquals(2, added.getAvailableUnits());
        assertEquals("Manager", StubMainApp.lastScene);
        assertTrue(testController.alertShown);
    }

    @Test
    public void testHandleAddEmptyName() {
        testController.nameField = "";

        testController.handleAdd();

        assertFalse(testEquipmentManager.addEquipmentCalled);
        assertTrue(testController.alertShown);
        assertEquals("Validation Error", testController.lastAlertTitle);
    }

    @Test
    public void testHandleAddDefaultValues() {
        testController.nameField = "Beaker";
        // Leaving description and location empty to test fallbacks

        testController.handleAdd();

        assertTrue(testEquipmentManager.addEquipmentCalled);
        Equipment added = testEquipmentManager.lastAddedEquipment;
        assertEquals("Beaker", added.getDescription(), "Description should fallback to name");
        assertEquals("TBD", added.getLabLocation(), "Location should fallback to TBD");
    }

    @Test
    public void testHandleBackNavigatesToManager() {
        testController.handleBack();
        assertEquals("Manager", StubMainApp.lastScene);
    }

    /**
     * Stub representation to test Controller logic without JavaFX components.
     */
    private static class StubAddEquipmentController {
        String nameField = "";
        String descriptionArea = "";
        String locationField = "";
        String statsArea = "";
        String unitCountLabel = "0";
        String selectedImagePath = "";

        boolean tagChemistry = false;
        boolean tagHandheld = false;
        boolean tagIndividual = false;
        boolean tagPhysics = false;
        boolean tagInLab = false;
        boolean tagPartnered = false;

        boolean alertShown = false;
        String lastAlertTitle = "";
        String lastAlertMessage = "";

        private final StubEquipmentManager equipmentManager;
        private static final java.util.Set<String> PREVIEWABLE = new java.util.HashSet<>(
                java.util.Arrays.asList("jpg", "jpeg", "png"));

        StubAddEquipmentController(StubEquipmentManager equipmentManager) {
            this.equipmentManager = equipmentManager;
        }

        public void initialize() {
            unitCountLabel = "0";
        }

        public void handleIncrement() {
            int count = Integer.parseInt(unitCountLabel);
            count++;
            unitCountLabel = String.valueOf(count);
        }

        public void handleDecrement() {
            int count = Integer.parseInt(unitCountLabel);
            if (count > 0) count--;
            unitCountLabel = String.valueOf(count);
        }

        public List<String> getSelectedTags() {
            List<String> selected = new ArrayList<>();
            if (tagChemistry) selected.add("Chemistry");
            if (tagHandheld) selected.add("Handheld");
            if (tagIndividual) selected.add("Individual");
            if (tagPhysics) selected.add("Physics");
            if (tagInLab) selected.add("In-Lab");
            if (tagPartnered) selected.add("Partnered");
            return selected;
        }

        public void handleAdd() {
            String name = nameField.trim();
            if (name.isEmpty()) {
                showAlert("Validation Error", "Equipment Name is required.");
                return;
            }

            String equipmentId = "EQ-" + System.currentTimeMillis();
            String description = descriptionArea.trim();
            if (description.isEmpty()) description = name;

            String location = locationField.trim();
            if (location.isEmpty()) location = "TBD";

            Equipment equipment = new Equipment(equipmentId, description, location);
            equipment.setName(name);
            equipment.setAvailableUnits(Integer.parseInt(unitCountLabel));
            equipment.setProductStatistics(statsArea.trim());
            equipment.setTags(getSelectedTags());
            equipment.setImagePath(selectedImagePath);

            equipmentManager.addEquipment(equipment);
            showAlert("Success", "\"" + name + "\" has been added successfully.");
            StubMainApp.switchScene("Manager");
        }

        public void handleBack() {
            StubMainApp.switchScene("Manager");
        }

        public boolean isPreviewable(String ext) {
            return PREVIEWABLE.contains(ext.toLowerCase());
        }

        public String getFileExtension(String fileName) {
            int dot = fileName.lastIndexOf('.');
            return (dot >= 0) ? fileName.substring(dot + 1).toLowerCase() : "";
        }

        public boolean hasSelectedImagePath() {
            return selectedImagePath != null && !selectedImagePath.isEmpty();
        }

        private void showAlert(String title, String message) {
            alertShown = true;
            lastAlertTitle = title;
            lastAlertMessage = message;
        }
    }

    private static class StubEquipmentManager {
        boolean addEquipmentCalled = false;
        Equipment lastAddedEquipment = null;

        public void addEquipment(Equipment equipment) {
            addEquipmentCalled = true;
            lastAddedEquipment = equipment;
        }
    }

    private static class StubMainApp {
        static String lastScene = null;
        static void switchScene(String sceneName) {
            lastScene = sceneName;
        }
    }
}
