package Test.Manual.Equipment;

import org.example.equipment.*;
import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EquipmentManagerTest {

    private TestEquipmentRepository testRepository;
    private EquipmentManager equipmentManager;

    @BeforeEach
    void setUp() {
        testRepository = new TestEquipmentRepository();
        equipmentManager = new EquipmentManager(testRepository);
    }

    // Add equipment

    @Test
    void testAddEquipment() {
        Equipment equipment = new Equipment("EQ001", "Microscope", "Lab A");

        equipmentManager.addEquipment(equipment);

        assertTrue(testRepository.wasEquipmentSaved(), "Equipment should be saved");
        assertEquals(equipment, testRepository.getLastSavedEquipment(), "Last saved equipment should match");
    }

    @Test
    void testAddMultipleEquipment() {
        Equipment eq1 = new Equipment("EQ001", "Microscope", "Lab A");
        Equipment eq2 = new Equipment("EQ002", "Spectrometer", "Lab B");

        equipmentManager.addEquipment(eq1);
        equipmentManager.addEquipment(eq2);

        assertEquals(2, testRepository.getSaveCount(), "Both equipment should be saved");
    }

    // Update equipment

    @Test
    void testUpdateEquipment() {
        Equipment equipment = new Equipment("EQ001", "Microscope", "Lab A");
        equipment.setName("Advanced Microscope");

        equipmentManager.updateEquipment(equipment);

        assertTrue(testRepository.wasEquipmentUpdated(), "Equipment should be updated");
        assertEquals(equipment, testRepository.getLastUpdatedEquipment(), "Last updated equipment should match");
    }

    @Test
    void testUpdateEquipmentPreservesId() {
        Equipment equipment = new Equipment("EQ001", "Microscope", "Lab A");
        equipment.setName("Updated Microscope");

        equipmentManager.updateEquipment(equipment);

        assertEquals("EQ001", testRepository.getLastUpdatedEquipment().getEquipmentId(), "Equipment ID should remain unchanged");
    }

    // Get equipment

    @Test
    void testGetAllEquipment() {
        Equipment eq1 = new Equipment("EQ001", "Microscope", "Lab A");
        Equipment eq2 = new Equipment("EQ002", "Spectrometer", "Lab B");
        
        testRepository.addEquipmentForRetrieval(eq1);
        testRepository.addEquipmentForRetrieval(eq2);

        List<Equipment> allEquipment = equipmentManager.getAllEquipment();

        assertNotNull(allEquipment, "Should return non-null list");
        assertEquals(2, allEquipment.size(), "Should return all equipment");
    }

    @Test
    void testGetAllEquipmentEmpty() {
        List<Equipment> allEquipment = equipmentManager.getAllEquipment();

        assertNotNull(allEquipment, "Should return non-null list");
        assertTrue(allEquipment.isEmpty(), "Should return empty list when no equipment");
    }

    @Test
    void testGetEquipmentById() {
        Equipment eq1 = new Equipment("EQ001", "Microscope", "Lab A");
        Equipment eq2 = new Equipment("EQ002", "Spectrometer", "Lab B");
        
        testRepository.addEquipmentForRetrieval(eq1);
        testRepository.addEquipmentForRetrieval(eq2);

        Equipment found = equipmentManager.getEquipmentById("EQ001");

        assertNotNull(found, "Equipment should be found");
        assertEquals("EQ001", found.getEquipmentId(), "Equipment ID should match");
        assertEquals("Microscope", found.getDescription(), "Equipment description should match");
    }

    @Test
    void testGetEquipmentByIdNotFound() {
        Equipment eq1 = new Equipment("EQ001", "Microscope", "Lab A");
        testRepository.addEquipmentForRetrieval(eq1);

        Equipment found = equipmentManager.getEquipmentById("EQ999");

        assertNull(found, "Should return null for non-existent equipment");
    }

    @Test
    void testGetEquipmentByIdEmpty() {
        Equipment found = equipmentManager.getEquipmentById("EQ001");

        assertNull(found, "Should return null when no equipment exists");
    }


    @Test
    void testGetAvailableEquipment() {
        Equipment eq1 = new Equipment("EQ001", "Microscope", "Lab A");
        eq1.setStatus(EquipmentStatus.AVAILABLE);
        
        Equipment eq2 = new Equipment("EQ002", "Spectrometer", "Lab B");
        eq2.setStatus(EquipmentStatus.DISABLED);
        
        Equipment eq3 = new Equipment("EQ003", "Centrifuge", "Lab C");
        eq3.setStatus(EquipmentStatus.AVAILABLE);
        
        testRepository.addEquipmentForRetrieval(eq1);
        testRepository.addEquipmentForRetrieval(eq2);
        testRepository.addEquipmentForRetrieval(eq3);

        List<Equipment> available = equipmentManager.getAvailableEquipment();

        assertEquals(2, available.size(), "Should return only available equipment");
        assertTrue(available.stream().allMatch(e -> e.getStatus() == EquipmentStatus.AVAILABLE), 
                   "All returned equipment should be available");
    }

    @Test
    void testGetAvailableEquipmentNone() {
        Equipment eq1 = new Equipment("EQ001", "Microscope", "Lab A");
        eq1.setStatus(EquipmentStatus.MAINTENANCE);
        
        testRepository.addEquipmentForRetrieval(eq1);

        List<Equipment> available = equipmentManager.getAvailableEquipment();

        assertTrue(available.isEmpty(), "Should return empty list when no equipment available");
    }

    // Enable equipment

    @Test
    void testEnableEquipment() {
        Equipment equipment = new Equipment("EQ001", "Microscope", "Lab A");
        equipment.setStatus(EquipmentStatus.DISABLED);
        testRepository.addEquipmentForRetrieval(equipment);

        equipmentManager.enableEquipment("EQ001");

        assertTrue(testRepository.wasStatusUpdated(), "Status should be updated");
        assertEquals(EquipmentStatus.AVAILABLE, testRepository.getLastUpdatedStatus(), "Status should be AVAILABLE");
    }

    @Test
    void testEnableNonExistentEquipment() { assertDoesNotThrow(() -> equipmentManager.enableEquipment("EQ999"),"Should not throw exception for non-existent equipment"); }

    // Disable equipment

    @Test
    void testDisableEquipment() {
        Equipment equipment = new Equipment("EQ001", "Microscope", "Lab A");
        equipment.setStatus(EquipmentStatus.AVAILABLE);
        testRepository.addEquipmentForRetrieval(equipment);

        equipmentManager.disableEquipment("EQ001");

        assertTrue(testRepository.wasStatusUpdated(), "Status should be updated");
        assertEquals(EquipmentStatus.DISABLED, testRepository.getLastUpdatedStatus(), "Status should be DISABLED");
    }

    @Test
    void testDisableNonExistentEquipment() {
        assertDoesNotThrow(() -> equipmentManager.disableEquipment("EQ999"), "Should not throw exception for non-existent equipment"); }

    // Maintenance

    @Test
    void testSetMaintenance() {
        Equipment equipment = new Equipment("EQ001", "Microscope", "Lab A");
        equipment.setStatus(EquipmentStatus.AVAILABLE);
        testRepository.addEquipmentForRetrieval(equipment);

        equipmentManager.setMaintenance("EQ001");

        assertTrue(testRepository.wasStatusUpdated(), "Status should be updated");
        assertEquals(EquipmentStatus.MAINTENANCE, testRepository.getLastUpdatedStatus(), "Status should be MAINTENANCE");
    }

    @Test
    @DisplayName("Should handle maintenance for non-existent equipment")
    void testSetMaintenanceNonExistent() { assertDoesNotThrow(() -> equipmentManager.setMaintenance("EQ999"), "Should not throw exception for non-existent equipment"); }

    // Update

    @Test
    void testUpdateEquipmentStatus() {
        Equipment equipment = new Equipment("EQ001", "Microscope", "Lab A");
        equipment.setStatus(EquipmentStatus.AVAILABLE);
        testRepository.addEquipmentForRetrieval(equipment);

        equipmentManager.updateEquipmentStatus("EQ001", EquipmentStatus.IN_USE);

        assertTrue(testRepository.wasStatusUpdated(), "Status should be updated");
        assertEquals(EquipmentStatus.IN_USE, testRepository.getLastUpdatedStatus(), "Status should be IN_USE");
    }

    @Test
    void testUpdateStatusNonExistent() { assertDoesNotThrow(() -> equipmentManager.updateEquipmentStatus("EQ999", EquipmentStatus.AVAILABLE), "Should not throw exception for non-existent equipment");}

    // Sensor

    @Test
    void testSensorUpdateWithValidStatus() {
        Equipment equipment = new Equipment("EQ001", "Microscope", "Lab A");
        equipment.setStatus(EquipmentStatus.AVAILABLE);
        testRepository.addEquipmentForRetrieval(equipment);

        equipmentManager.update("EQ001", "MAINTENANCE");

        assertTrue(testRepository.wasStatusUpdated(), "Status should be updated by sensor");
        assertEquals(EquipmentStatus.MAINTENANCE, testRepository.getLastUpdatedStatus(), "Status should be updated to MAINTENANCE");
    }

    @Test
    void testSensorUpdateWithLowercaseStatus() {
        Equipment equipment = new Equipment("EQ001", "Microscope", "Lab A");
        equipment.setStatus(EquipmentStatus.AVAILABLE);
        testRepository.addEquipmentForRetrieval(equipment);

        equipmentManager.update("EQ001", "disabled");

        assertTrue(testRepository.wasStatusUpdated(), "Status should be updated");
        assertEquals(EquipmentStatus.DISABLED, testRepository.getLastUpdatedStatus(), "Status should handle lowercase input");
    }

    @Test
    void testSensorUpdateWithInvalidStatus() {
        Equipment equipment = new Equipment("EQ001", "Microscope", "Lab A");
        equipment.setStatus(EquipmentStatus.AVAILABLE);
        testRepository.addEquipmentForRetrieval(equipment);

        assertDoesNotThrow(() -> equipmentManager.update("EQ001", "INVALID_STATUS"), 
                          "Should handle invalid status gracefully");
    }

    @Test
    void testSensorUpdateNonExistent() { assertDoesNotThrow(() -> equipmentManager.update("EQ999", "MAINTENANCE"), "Should handle non-existent equipment gracefully"); }

    // Integration

    @Test
    void testEquipmentLifecycle() {
        // Create and add equipment
        Equipment equipment = new Equipment("EQ001", "Microscope", "Lab A");
        equipmentManager.addEquipment(equipment);
        assertTrue(testRepository.wasEquipmentSaved(), "Equipment should be added");

        // Add to retrieval list
        testRepository.addEquipmentForRetrieval(equipment);

        // Retrieve and verify
        Equipment found = equipmentManager.getEquipmentById("EQ001");
        assertNotNull(found, "Equipment should be retrievable");

        // Update status through enable
        equipmentManager.enableEquipment("EQ001");
        assertTrue(testRepository.wasStatusUpdated(), "Status should be updated");

        // Update status through disable
        equipmentManager.disableEquipment("EQ001");
        assertTrue(testRepository.wasStatusUpdated(), "Status should be updated again");
    }

    @Test
    void testMultipleEquipmentStatusManagement() {
        Equipment eq1 = new Equipment("EQ001", "Microscope", "Lab A");
        eq1.setStatus(EquipmentStatus.AVAILABLE);
        
        Equipment eq2 = new Equipment("EQ002", "Spectrometer", "Lab B");
        eq2.setStatus(EquipmentStatus.DISABLED);
        
        Equipment eq3 = new Equipment("EQ003", "Centrifuge", "Lab C");
        eq3.setStatus(EquipmentStatus.MAINTENANCE);

        testRepository.addEquipmentForRetrieval(eq1);
        testRepository.addEquipmentForRetrieval(eq2);
        testRepository.addEquipmentForRetrieval(eq3);

        List<Equipment> available = equipmentManager.getAvailableEquipment();
        assertEquals(1, available.size(), "Only one equipment should be available");
        assertEquals("EQ001", available.get(0).getEquipmentId(), "EQ001 should be the available equipment");
    }
}

class TestEquipmentRepository extends org.example.data.CSVRepository {
    
    private boolean equipmentSaved = false;
    private Equipment lastSavedEquipment = null;
    private int saveCount = 0;
    
    private boolean equipmentUpdated = false;
    private Equipment lastUpdatedEquipment = null;
    
    private java.util.List<Equipment> equipmentList = new java.util.ArrayList<>();
    
    private boolean statusUpdated = false;
    private EquipmentStatus lastUpdatedStatus = null;

    public void saveEquipment(Equipment equipment) {
        equipmentSaved = true;
        lastSavedEquipment = equipment;
        saveCount++;
    }

    public void updateEquipment(Equipment equipment) {
        equipmentUpdated = true;
        lastUpdatedEquipment = equipment;
        lastUpdatedStatus = equipment.getStatus();
        statusUpdated = true;
    }

    public List<Equipment> getAllEquipment() {
        return new java.util.ArrayList<>(equipmentList);
    }

    // Helper method
    public void addEquipmentForRetrieval(Equipment equipment) {
        equipmentList.add(equipment);
    }

    // Getters
    public boolean wasEquipmentSaved() {
        return equipmentSaved;
    }

    public Equipment getLastSavedEquipment() {
        return lastSavedEquipment;
    }

    public int getSaveCount() {
        return saveCount;
    }

    public boolean wasEquipmentUpdated() {
        return equipmentUpdated;
    }

    public Equipment getLastUpdatedEquipment() {
        return lastUpdatedEquipment;
    }

    public boolean wasStatusUpdated() {
        return statusUpdated;
    }

    public EquipmentStatus getLastUpdatedStatus() {
        return lastUpdatedStatus;
    }

    public void setLastUpdatedStatus(EquipmentStatus status) {
        this.lastUpdatedStatus = status;
    }
}
