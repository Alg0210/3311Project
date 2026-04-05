package Test.Manual.Equipment;

import org.example.equipment.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class EquipmentActionTest {

    private TestEquipmentManager testManager;
    private Equipment equipment;
    private EquipmentAction equipmentAction;

    @BeforeEach
    void setUp() {
        testManager = new TestEquipmentManager();
        equipment = new Equipment("EQ001", "Microscope", "Lab A");
    }

    @Test
    void testAddActionExecute() {
        equipmentAction = new EquipmentAction(testManager, equipment, "ADD");
        
        equipmentAction.execute();
        
        assertTrue(testManager.wasAddEquipmentCalled(), "addEquipment should have been called");
        assertEquals(equipment, testManager.getLastAddedEquipment(), "Equipment passed should match");
    }
    

    @Test
    void testEnableActionExecute() {
        equipment.setStatus(EquipmentStatus.DISABLED);
        equipmentAction = new EquipmentAction(testManager, equipment, "ENABLE");
        
        equipmentAction.execute();
        
        assertTrue(testManager.wasEnableEquipmentCalled(), "enableEquipment should have been called");
        assertEquals("EQ001", testManager.getLastEnabledEquipmentId(), "Equipment ID should match");
    }

    @Test
    void testDisableActionExecute() {
        equipment.setStatus(EquipmentStatus.AVAILABLE);
        equipmentAction = new EquipmentAction(testManager, equipment, "DISABLE");
        
        equipmentAction.execute();
        
        assertTrue(testManager.wasDisableEquipmentCalled(), "disableEquipment should have been called");
        assertEquals("EQ001", testManager.getLastDisabledEquipmentId(), "Equipment ID should match");
    }

    @Test
    void testMaintenanceActionExecute() {
        equipmentAction = new EquipmentAction(testManager, equipment, "MAINTENANCE");
        
        equipmentAction.execute();
        
        assertTrue(testManager.wasSetMaintenanceCalled(), "setMaintenance should have been called");
        assertEquals("EQ001", testManager.getLastMaintenanceEquipmentId(), "Equipment ID should match");
    }

    @Test
    void testUnknownActionType() {
        equipmentAction = new EquipmentAction(testManager, equipment, "UNKNOWN");
        
        assertDoesNotThrow(() -> equipmentAction.execute());
        
        assertFalse(testManager.wasAddEquipmentCalled(), "addEquipment should not be called");
        assertFalse(testManager.wasEnableEquipmentCalled(), "enableEquipment should not be called");
        assertFalse(testManager.wasDisableEquipmentCalled(), "disableEquipment should not be called");
        assertFalse(testManager.wasSetMaintenanceCalled(), "setMaintenance should not be called");
    }

    @Test
    void testCaseInsensitiveActionTypes() {
        equipmentAction = new EquipmentAction(testManager, equipment, "add");
        
        equipmentAction.execute();
        
        assertTrue(testManager.wasAddEquipmentCalled(), "addEquipment should be called with lowercase 'add'");
        assertEquals(equipment, testManager.getLastAddedEquipment(), "Equipment should match");
    }

    @Test
    void testUndoRestoresPreviousStatus() {
        equipment.setStatus(EquipmentStatus.AVAILABLE);
        equipmentAction = new EquipmentAction(testManager, equipment, "DISABLE");
        
        equipmentAction.execute();
        equipment.setStatus(EquipmentStatus.DISABLED);
        equipmentAction.undo();
        
        assertTrue(testManager.wasUpdateEquipmentStatusCalled(), "updateEquipmentStatus should have been called");
        assertEquals(EquipmentStatus.AVAILABLE, equipment.getStatus());
    }

    @Test
    void testUndoWithNullPreviousStatus() {
        equipmentAction = new EquipmentAction(testManager, equipment, "ADD");
        
        equipmentAction.undo();

        assertFalse(testManager.wasUpdateEquipmentStatusCalled(), "updateEquipmentStatus should not be called");
    }

    @Test
    void testUndoFromMaintenanceStatus() {
        equipment.setStatus(EquipmentStatus.AVAILABLE);
        equipmentAction = new EquipmentAction(testManager, equipment, "MAINTENANCE");
        
        equipmentAction.execute();
        equipment.setStatus(EquipmentStatus.MAINTENANCE);
        equipmentAction.undo();
        
        assertTrue(testManager.wasUpdateEquipmentStatusCalled(), "updateEquipmentStatus should have been called");
        assertEquals(EquipmentStatus.AVAILABLE, equipment.getStatus());
    }

    @Test
    void testEquipmentReferenceIsMaintained() {
        equipment.setName("Advanced Microscope");
        equipmentAction = new EquipmentAction(testManager, equipment, "ADD");
        
        equipmentAction.execute();
        
        assertEquals("Advanced Microscope", testManager.getLastAddedEquipment().getName(), "Equipment name should match");
    }

    @Test
    void testMultipleConsecutiveActions() {
        equipment.setStatus(EquipmentStatus.AVAILABLE);

        equipmentAction = new EquipmentAction(testManager, equipment, "DISABLE");
        equipmentAction.execute();
        equipment.setStatus(EquipmentStatus.DISABLED);

        EquipmentAction enableAction = new EquipmentAction(testManager, equipment, "ENABLE");
        enableAction.execute();
        
        assertTrue(testManager.wasDisableEquipmentCalled(), "disableEquipment should have been called");
        assertTrue(testManager.wasEnableEquipmentCalled(), "enableEquipment should have been called");
    }

    @Test
    void testUndoAfterMultipleStatusChanges() {
        equipment.setStatus(EquipmentStatus.AVAILABLE);
        equipmentAction = new EquipmentAction(testManager, equipment, "DISABLE");
        
        equipmentAction.execute();
        EquipmentStatus statusBeforeUndoCall = EquipmentStatus.DISABLED;
        equipment.setStatus(statusBeforeUndoCall);
        
        equipmentAction.undo();

        assertEquals(EquipmentStatus.AVAILABLE, equipment.getStatus());
    }
}

class TestEquipmentManager extends EquipmentManager {
    
    private boolean addEquipmentCalled = false;
    private Equipment lastAddedEquipment = null;
    
    private boolean enableEquipmentCalled = false;
    private String lastEnabledEquipmentId = null;
    
    private boolean disableEquipmentCalled = false;
    private String lastDisabledEquipmentId = null;
    
    private boolean setMaintenanceCalled = false;
    private String lastMaintenanceEquipmentId = null;
    
    private boolean updateEquipmentStatusCalled = false;

    @Override
    public void addEquipment(Equipment equipment) {
        addEquipmentCalled = true;
        lastAddedEquipment = equipment;
    }

    @Override
    public void enableEquipment(String id) {
        enableEquipmentCalled = true;
        lastEnabledEquipmentId = id;
    }

    @Override
    public void disableEquipment(String id) {
        disableEquipmentCalled = true;
        lastDisabledEquipmentId = id;
    }

    @Override
    public void setMaintenance(String id) {
        setMaintenanceCalled = true;
        lastMaintenanceEquipmentId = id;
    }

    @Override
    public void updateEquipmentStatus(String id, EquipmentStatus status) {
        updateEquipmentStatusCalled = true;
    }

    // Getters to verify
    public boolean wasAddEquipmentCalled() {
        return addEquipmentCalled;
    }

    public Equipment getLastAddedEquipment() {
        return lastAddedEquipment;
    }

    public boolean wasEnableEquipmentCalled() {
        return enableEquipmentCalled;
    }

    public String getLastEnabledEquipmentId() {
        return lastEnabledEquipmentId;
    }

    public boolean wasDisableEquipmentCalled() {
        return disableEquipmentCalled;
    }

    public String getLastDisabledEquipmentId() {
        return lastDisabledEquipmentId;
    }

    public boolean wasSetMaintenanceCalled() {
        return setMaintenanceCalled;
    }

    public String getLastMaintenanceEquipmentId() {
        return lastMaintenanceEquipmentId;
    }

    public boolean wasUpdateEquipmentStatusCalled() {
        return updateEquipmentStatusCalled;
    }
}
