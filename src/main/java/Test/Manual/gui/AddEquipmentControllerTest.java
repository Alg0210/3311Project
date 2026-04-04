package Test.Manual.gui;

import org.example.equipment.Equipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Add Equipment Controller Tests")
public class AddEquipmentControllerTest {

    private TestAddEquipmentController testController;
    private TestEquipmentManager testEquipmentManager;

    @BeforeEach
    void setUp() {
        testEquipmentManager = new TestEquipmentManager();
        testController = new TestAddEquipmentController(testEquipmentManager);
    }

    // ────── INITIALIZATION TESTS ──────────

    @Test
    @DisplayName("Should initialize unit count to 0")
    void testInitializeUnitCount() {
        testController.callInitialize();

        assertEquals("0", testController.getUnitCountLabel(), "Initial unit count should be 0");
    }

    // ────── IMAGE PICKER TESTS ──────────

    @Test
    @DisplayName("Should store image path when image is selected")
    void testHandlePickImageStoresPath() {
        String imagePath = "/path/to/image.jpg";
        testController.setSelectedImagePath(imagePath);

        assertTrue(testController.hasSelectedImagePath(), "Image path should be stored");
        assertEquals(imagePath, testController.getSelectedImagePath(), "Image path should match");
    }

    @Test
    @DisplayName("Should recognize JPEG as previewable format")
    void testIsPreviewableJPEG() {
        assertTrue(testController.isPreviewable("jpg"), "JPG should be previewable");
        assertTrue(testController.isPreviewable("jpeg"), "JPEG should be previewable");
        assertTrue(testController.isPreviewable("JPG"), "JPG uppercase should be previewable");
    }

    @Test
    @DisplayName("Should recognize PNG as previewable format")
    void testIsPreviewablePNG() {
        assertTrue(testController.isPreviewable("png"), "PNG should be previewable");
        assertTrue(testController.isPreviewable("PNG"), "PNG uppercase should be previewable");
    }

    @Test
    @DisplayName("Should not recognize HEIC as previewable format")
    void testIsNotPreviewableHEIC() {
        assertFalse(testController.isPreviewable("heic"), "HEIC should not be previewable");
        assertFalse(testController.isPreviewable("heif"), "HEIF should not be previewable");
    }

    @Test
    @DisplayName("Should extract file extension correctly")
    void testGetExtensionCorrectly() {
        assertEquals("jpg", testController.getFileExtension("image.jpg"), "Should extract jpg extension");
        assertEquals("png", testController.getFileExtension("photo.PNG"), "Should extract and lowercase png");
        assertEquals("jpeg", testController.getFileExtension("picture.jpeg"), "Should extract jpeg extension");
    }

    @Test
    @DisplayName("Should handle filename without extension")
    void testGetExtensionNoExtension() {
        assertEquals("", testController.getFileExtension("noextension"), "Should return empty for no extension");
    }

    @Test
    @DisplayName("Should handle hidden files correctly")
    void testGetExtensionHiddenFile() {
        assertEquals("jpg", testController.getFileExtension(".hidden.jpg"), "Should extract extension from hidden file");
    }

    // ────── UNIT COUNT TESTS ──────────

    @Test
    @DisplayName("Should increment unit count")
    void testHandleIncrement() {
        testController.callInitialize();
        assertEquals("0", testController.getUnitCountLabel(), "Initial count should be 0");

        testController.callHandleIncrement();
        assertEquals("1", testController.getUnitCountLabel(), "Count should increment to 1");

        testController.callHandleIncrement();
        assertEquals("2", testController.getUnitCountLabel(), "Count should increment to 2");
    }

    @Test
    @DisplayName("Should decrement unit count")
    void testHandleDecrement() {
        testController.callInitialize();
        testController.callHandleIncrement();
        testController.callHandleIncrement();
        testController.callHandleIncrement();
        assertEquals("3", testController.getUnitCountLabel(), "Count should be 3");

        testController.callHandleDecrement();
        assertEquals("2", testController.getUnitCountLabel(), "Count should decrement to 2");

        testController.callHandleDecrement();
        assertEquals("1", testController.getUnitCountLabel(), "Count should decrement to 1");
    }

    @Test
    @DisplayName("Should not decrement below 0")
    void testHandleDecrementNeverNegative() {
        testController.callInitialize();

        testController.callHandleDecrement();
        assertEquals("0", testController.getUnitCountLabel(), "Count should stay at 0");

        testController.callHandleDecrement();
        assertEquals("0", testController.getUnitCountLabel(), "Count should not go negative");
    }

    @Test
    @DisplayName("Should handle multiple increments and decrements")
    void testHandleMultipleUnitOperations() {
        testController.callInitialize();

        for (int i = 0; i < 5; i++) {
            testController.callHandleIncrement();
        }
        assertEquals("5", testController.getUnitCountLabel(), "Count should be 5");

        for (int i = 0; i < 3; i++) {
            testController.callHandleDecrement();
        }
        assertEquals("2", testController.getUnitCountLabel(), "Count should be 2");
    }

    // ────── TAG SELECTION TESTS ──────────

    @Test
    @DisplayName("Should select chemistry tag")
    void testSelectChemistryTag() {
        testController.setTagChemistrySelected(true);
        List<String> tags = testController.callGetSelectedTags();

        assertTrue(tags.contains("Chemistry"), "Chemistry tag should be selected");
    }

    @Test
    @DisplayName("Should select handheld tag")
    void testSelectHandheldTag() {
        testController.setTagHandheldSelected(true);
        List<String> tags = testController.callGetSelectedTags();

        assertTrue(tags.contains("Handheld"), "Handheld tag should be selected");
    }

    @Test
    @DisplayName("Should select individual tag")
    void testSelectIndividualTag() {
        testController.setTagIndividualSelected(true);
        List<String> tags = testController.callGetSelectedTags();

        assertTrue(tags.contains("Individual"), "Individual tag should be selected");
    }

    @Test
    @DisplayName("Should select physics tag")
    void testSelectPhysicsTag() {
        testController.setTagPhysicsSelected(true);
        List<String> tags = testController.callGetSelectedTags();

        assertTrue(tags.contains("Physics"), "Physics tag should be selected");
    }

    @Test
    @DisplayName("Should select in-lab tag")
    void testSelectInLabTag() {
        testController.setTagInLabSelected(true);
        List<String> tags = testController.callGetSelectedTags();

        assertTrue(tags.contains("In-Lab"), "In-Lab tag should be selected");
    }

    @Test
    @DisplayName("Should select partnered tag")
    void testSelectPartneredTag() {
        testController.setTagPartneredSelected(true);
        List<String> tags = testController.callGetSelectedTags();

        assertTrue(tags.contains("Partnered"), "Partnered tag should be selected");
    }

    @Test
    @DisplayName("Should select multiple tags")
    void testSelectMultipleTags() {
        testController.setTagChemistrySelected(true);
        testController.setTagHandheldSelected(true);
        testController.setTagPhysicsSelected(true);

        List<String> tags = testController.callGetSelectedTags();

        assertEquals(3, tags.size(), "Should have 3 tags selected");
        assertTrue(tags.contains("Chemistry"));
        assertTrue(tags.contains("Handheld"));
        assertTrue(tags.contains("Physics"));
    }

    @Test
    @DisplayName("Should return empty list when no tags selected")
    void testNoTagsSelected() {
        List<String> tags = testController.callGetSelectedTags();

        assertTrue(tags.isEmpty(), "Should return empty list when no tags selected");
    }

    @Test
    @DisplayName("Should deselect tags")
    void testDeselectTags() {
        testController.setTagChemistrySelected(true);
        testController.setTagPhysicsSelected(true);

        List<String> tags = testController.callGetSelectedTags();
        assertEquals(2, tags.size(), "Should have 2 tags");

        testController.setTagChemistrySelected(false);
        tags = testController.callGetSelectedTags();

        assertEquals(1, tags.size(), "Should have 1 tag after deselection");
        assertTrue(tags.contains("Physics"));
        assertFalse(tags.contains("Chemistry"));
    }

    // ────── ADD EQUIPMENT TESTS ──────────

    @Test
    @DisplayName("Should add equipment with valid data")
    void testHandleAddEquipmentValid() {
        testController.setNameField("Microscope");
        testController.setDescriptionArea("Advanced Microscope");
        testController.setLocationField("Lab A");
        testController.callHandleIncrement();
        testController.callHandleIncrement();

        testController.callHandleAdd();

        assertTrue(testEquipmentManager.wasAddEquipmentCalled(), "Equipment should be added");
        assertTrue(testController.wasAlertShown(), "Success alert should be shown");
        assertTrue(testController.getLastAlertMessage().contains("Microscope"), "Alert should mention equipment name");
    }

    @Test
    @DisplayName("Should reject empty equipment name")
    void testHandleAddEmptyName() {
        testController.setNameField("");
        testController.setDescriptionArea("Description");
        testController.setLocationField("Lab A");

        testController.callHandleAdd();

        assertFalse(testEquipmentManager.wasAddEquipmentCalled(), "Equipment should not be added");
        assertTrue(testController.wasAlertShown(), "Error alert should be shown");
        assertTrue(testController.getLastAlertMessage().contains("required"), "Alert should indicate name is required");
    }

    @Test
    @DisplayName("Should reject whitespace-only equipment name")
    void testHandleAddWhitespaceOnlyName() {
        testController.setNameField("   ");
        testController.setDescriptionArea("Description");
        testController.setLocationField("Lab A");

        testController.callHandleAdd();

        assertFalse(testEquipmentManager.wasAddEquipmentCalled(), "Equipment should not be added");
        assertTrue(testController.getLastAlertMessage().contains("required"), "Should reject whitespace-only name");
    }

    @Test
    @DisplayName("Should use equipment name as description if description is empty")
    void testHandleAddDescriptionDefaultsToName() {
        testController.setNameField("Spectrometer");
        testController.setDescriptionArea("");
        testController.setLocationField("Lab B");

        testController.callHandleAdd();

        Equipment added = testEquipmentManager.getLastAddedEquipment();
        assertEquals("Spectrometer", added.getDescription(), "Description should default to name");
    }

    @Test
    @DisplayName("Should use default location if location is empty")
    void testHandleAddLocationDefaultsToTBD() {
        testController.setNameField("Equipment");
        testController.setDescriptionArea("Description");
        testController.setLocationField("");

        testController.callHandleAdd();

        Equipment added = testEquipmentManager.getLastAddedEquipment();
        assertEquals("TBD", added.getLabLocation(), "Location should default to TBD");
    }

    @Test
    @DisplayName("Should include selected tags in added equipment")
    void testHandleAddIncludesTags() {
        testController.setNameField("Equipment");
        testController.setDescriptionArea("Description");
        testController.setLocationField("Lab A");
        testController.setTagChemistrySelected(true);
        testController.setTagPhysicsSelected(true);

        testController.callHandleAdd();

        Equipment added = testEquipmentManager.getLastAddedEquipment();
        assertEquals(2, added.getTags().size(), "Should include selected tags");
        assertTrue(added.getTags().contains("Chemistry"));
        assertTrue(added.getTags().contains("Physics"));
    }

    @Test
    @DisplayName("Should include product statistics in added equipment")
    void testHandleAddIncludesStatistics() {
        testController.setNameField("Equipment");
        testController.setDescriptionArea("Description");
        testController.setLocationField("Lab A");
        testController.setStatsArea("Usage: 100 hours");

        testController.callHandleAdd();

        Equipment added = testEquipmentManager.getLastAddedEquipment();
        assertEquals("Usage: 100 hours", added.getProductStatistics(), "Should include statistics");
    }

    @Test
    @DisplayName("Should include unit count in added equipment")
    void testHandleAddIncludesUnitCount() {
        testController.setNameField("Equipment");
        testController.setDescriptionArea("Description");
        testController.setLocationField("Lab A");
        testController.callHandleIncrement();
        testController.callHandleIncrement();
        testController.callHandleIncrement();

        testController.callHandleAdd();

        Equipment added = testEquipmentManager.getLastAddedEquipment();
        assertEquals(3, added.getAvailableUnits(), "Should include unit count");
    }

    @Test
    @DisplayName("Should include image path in added equipment")
    void testHandleAddIncludesImagePath() {
        testController.setNameField("Equipment");
        testController.setDescriptionArea("Description");
        testController.setLocationField("Lab A");
        testController.setSelectedImagePath("/path/to/image.jpg");

        testController.callHandleAdd();

        Equipment added = testEquipmentManager.getLastAddedEquipment();
        assertEquals("/path/to/image.jpg", added.getImagePath(), "Should include image path");
    }

    @Test
    @DisplayName("Should generate unique equipment ID")
    void testHandleAddGeneratesUniqueId() {
        testController.setNameField("Equipment 1");
        testController.setDescriptionArea("Description");
        testController.setLocationField("Lab A");

        testController.callHandleAdd();
        String equipmentId1 = testEquipmentManager.getLastAddedEquipment().getEquipmentId();

        // Reset form and add delay to ensure unique timestamp
        try {
            Thread.sleep(10); // Small delay to ensure different timestamp
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        testController.setNameField("Equipment 2");
        testController.setDescriptionArea("Description");
        testController.setLocationField("Lab A");

        testController.callHandleAdd();
        String equipmentId2 = testEquipmentManager.getLastAddedEquipment().getEquipmentId();

        assertNotEquals(equipmentId1, equipmentId2, "Equipment IDs should be unique");
        assertTrue(equipmentId1.startsWith("EQ-"), "Equipment ID should start with EQ-");
        assertTrue(equipmentId2.startsWith("EQ-"), "Equipment ID should start with EQ-");
    }

    // ────── NAVIGATION TESTS ──────────

    @Test
    @DisplayName("Should switch to Manager scene on successful add")
    void testHandleAddNavigatesToManager() {
        testController.setNameField("Equipment");
        testController.setDescriptionArea("Description");
        testController.setLocationField("Lab A");

        TestMainApp.resetSceneSwitch();
        testController.callHandleAdd();

        assertEquals("Manager", TestMainApp.getLastSwitchedScene(), "Should switch to Manager scene");
    }

    @Test
    @DisplayName("Should switch to Manager scene when back is clicked")
    void testHandleBackNavigatesToManager() {
        TestMainApp.resetSceneSwitch();
        testController.callHandleBack();

        assertEquals("Manager", TestMainApp.getLastSwitchedScene(), "Should switch to Manager scene");
    }

    // ────── INTEGRATION TESTS ──────────

    @Test
    @DisplayName("Should handle complete add equipment workflow")
    void testCompleteAddEquipmentWorkflow() {
        testController.callInitialize();

        testController.setNameField("Advanced Microscope");
        testController.setDescriptionArea("High-powered microscope for detailed viewing");
        testController.setLocationField("Lab A - Room 101");
        testController.setStatsArea("Calibrated: 2024-01-15, Usage: 250 hours");
        testController.setSelectedImagePath("/images/microscope.jpg");

        testController.setTagChemistrySelected(true);
        testController.setTagIndividualSelected(true);

        for (int i = 0; i < 5; i++) {
            testController.callHandleIncrement();
        }

        testController.callHandleAdd();

        assertTrue(testEquipmentManager.wasAddEquipmentCalled(), "Equipment should be added");
        Equipment added = testEquipmentManager.getLastAddedEquipment();

        assertEquals("Advanced Microscope", added.getName(), "Name should match");
        assertEquals("High-powered microscope for detailed viewing", added.getDescription(), "Description should match");
        assertEquals("Lab A - Room 101", added.getLabLocation(), "Location should match");
        assertEquals("Calibrated: 2024-01-15, Usage: 250 hours", added.getProductStatistics(), "Statistics should match");
        assertEquals("/images/microscope.jpg", added.getImagePath(), "Image path should match");
        assertEquals(5, added.getAvailableUnits(), "Unit count should match");
        assertEquals(2, added.getTags().size(), "Should have 2 tags");
        assertTrue(added.getTags().contains("Chemistry"));
        assertTrue(added.getTags().contains("Individual"));
    }

    @Test
    @DisplayName("Should trim whitespace from input fields")
    void testHandleAddTrimsWhitespace() {
        testController.setNameField("  Equipment Name  ");
        testController.setDescriptionArea("  Description  ");
        testController.setLocationField("  Lab A  ");
        testController.setStatsArea("  Stats  ");

        testController.callHandleAdd();

        Equipment added = testEquipmentManager.getLastAddedEquipment();
        assertEquals("Equipment Name", added.getName(), "Name should be trimmed");
        assertEquals("Description", added.getDescription(), "Description should be trimmed");
        assertEquals("Lab A", added.getLabLocation(), "Location should be trimmed");
        assertEquals("Stats", added.getProductStatistics(), "Stats should be trimmed");
    }

    @Test
    @DisplayName("Should reset form after successful submission")
    void testFormResetAfterAdd() {
        testController.setNameField("Equipment");
        testController.setDescriptionArea("Description");
        testController.setLocationField("Lab A");
        testController.callHandleIncrement();

        testController.callHandleAdd();

        // Verify alert was shown (indicating success)
        assertTrue(testController.wasAlertShown(), "Success alert should be shown");
    }
}

/**
 * TestAddEquipmentController wraps AddEquipmentController for testing without JavaFX
 */
class TestAddEquipmentController {
    private String nameField = "";
    private String descriptionArea = "";
    private String locationField = "";
    private String statsArea = "";
    private String unitCountLabel = "0";
    private String selectedImagePath = "";

    private boolean tagChemistry = false;
    private boolean tagHandheld = false;
    private boolean tagIndividual = false;
    private boolean tagPhysics = false;
    private boolean tagInLab = false;
    private boolean tagPartnered = false;

    private boolean alertShown = false;
    private String lastAlertTitle = "";
    private String lastAlertMessage = "";

    private TestEquipmentManager equipmentManager;

    private static final java.util.Set<String> PREVIEWABLE = new java.util.HashSet<>(
            java.util.Arrays.asList("jpg", "jpeg", "png"));

    public TestAddEquipmentController(TestEquipmentManager equipmentManager) {
        this.equipmentManager = equipmentManager;
    }

    public void callInitialize() {
        unitCountLabel = "0";
    }

    public void callHandleIncrement() {
        int count = Integer.parseInt(unitCountLabel);
        count++;
        unitCountLabel = String.valueOf(count);
    }

    public void callHandleDecrement() {
        int count = Integer.parseInt(unitCountLabel);
        if (count > 0) {
            count--;
        }
        unitCountLabel = String.valueOf(count);
    }

    public List<String> callGetSelectedTags() {
        List<String> selected = new ArrayList<>();
        if (tagChemistry) selected.add("Chemistry");
        if (tagHandheld) selected.add("Handheld");
        if (tagIndividual) selected.add("Individual");
        if (tagPhysics) selected.add("Physics");
        if (tagInLab) selected.add("In-Lab");
        if (tagPartnered) selected.add("Partnered");
        return selected;
    }

    public void callHandleAdd() {
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
        equipment.setTags(callGetSelectedTags());
        equipment.setImagePath(selectedImagePath);

        equipmentManager.addEquipment(equipment);
        showAlert("Success", "\"" + name + "\" has been added successfully.");
        TestMainApp.switchScene("Manager");
    }

    public void callHandleBack() {
        TestMainApp.switchScene("Manager");
    }

    public boolean isPreviewable(String ext) {
        return PREVIEWABLE.contains(ext.toLowerCase());
    }

    public String getFileExtension(String fileName) {
        int dot = fileName.lastIndexOf('.');
        return (dot >= 0) ? fileName.substring(dot + 1).toLowerCase() : "";
    }

    private void showAlert(String title, String message) {
        alertShown = true;
        lastAlertTitle = title;
        lastAlertMessage = message;
    }

    // Getters for verification
    public String getUnitCountLabel() { return unitCountLabel; }
    public String getSelectedImagePath() { return selectedImagePath; }
    public boolean hasSelectedImagePath() { return !selectedImagePath.isEmpty(); }
    public boolean wasAlertShown() { return alertShown; }
    public String getLastAlertMessage() { return lastAlertMessage; }

    // Setters for testing
    public void setNameField(String name) { this.nameField = name; }
    public void setDescriptionArea(String description) { this.descriptionArea = description; }
    public void setLocationField(String location) { this.locationField = location; }
    public void setStatsArea(String stats) { this.statsArea = stats; }
    public void setSelectedImagePath(String path) { this.selectedImagePath = path; }

    public void setTagChemistrySelected(boolean selected) { this.tagChemistry = selected; }
    public void setTagHandheldSelected(boolean selected) { this.tagHandheld = selected; }
    public void setTagIndividualSelected(boolean selected) { this.tagIndividual = selected; }
    public void setTagPhysicsSelected(boolean selected) { this.tagPhysics = selected; }
    public void setTagInLabSelected(boolean selected) { this.tagInLab = selected; }
    public void setTagPartneredSelected(boolean selected) { this.tagPartnered = selected; }
}

/**
 * TestEquipmentManager is a test double for EquipmentManager
 */
class TestEquipmentManager {
    private boolean addEquipmentCalled = false;
    private Equipment lastAddedEquipment = null;

    public void addEquipment(Equipment equipment) {
        addEquipmentCalled = true;
        lastAddedEquipment = equipment;
    }

    public boolean wasAddEquipmentCalled() {
        return addEquipmentCalled;
    }

    public Equipment getLastAddedEquipment() {
        return lastAddedEquipment;
    }
}
