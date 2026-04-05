package org.example.manual.equipment;

import org.example.equipment.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Equipment Action Tests")
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
    @DisplayName("Test ADD action executes correctly")
    void testAddActionExecute() {
        equipmentAction = new EquipmentAction(testManager, equipment, "ADD");

        equipmentAction.execute();

        assertTrue(testManager.wasAddEquipmentCalled(), "addEquipment should have been called");
        assertEquals(equipment, testManager.getLastAddedEquipment(), "Equipment passed should match");
    }


    @Test
    @DisplayName("Test ENABLE action executes correctly")
    void testEnableActionExecute() {
        equipment.setStatus(EquipmentStatus.DISABLED);
        equipmentAction = new EquipmentAction(testManager, equipment, "ENABLE");

        equipmentAction.execute();

        assertTrue(testManager.wasEnableEquipmentCalled(), "enableEquipment should have been called");
        assertEquals("EQ001", testManager.getLastEnabledEquipmentId(), "Equipment ID should match");
    }

    @Test
    @DisplayName("Test DISABLE action executes correctly")
    void testDisableActionExecute() {
        equipment.setStatus(EquipmentStatus.AVAILABLE);
        equipmentAction = new EquipmentAction(testManager, equipment, "DISABLE");

        equipmentAction.execute();

        assertTrue(testManager.wasDisableEquipmentCalled(), "disableEquipment should have been called");
        assertEquals("EQ001", testManager.getLastDisabledEquipmentId(), "Equipment ID should match");
    }

    @Test
    @DisplayName("Test MAINTENANCE action executes correctly")
    void testMaintenanceActionExecute() {
        equipmentAction = new EquipmentAction(testManager, equipment, "MAINTENANCE");

        equipmentAction.execute();

        assertTrue(testManager.wasSetMaintenanceCalled(), "setMaintenance should have been called");
        assertEquals("EQ001", testManager.getLastMaintenanceEquipmentId(), "Equipment ID should match");
    }

    @Test
    @DisplayName("Test unknown action type prints to console")
    void testUnknownActionType() {
        equipmentAction = new EquipmentAction(testManager, equipment, "UNKNOWN");

        // Execute should not throw exception for unknown action
        assertDoesNotThrow(() -> equipmentAction.execute());

        // Manager methods should not be called for unknown action
        assertFalse(testManager.wasAddEquipmentCalled(), "addEquipment should not be called");
        assertFalse(testManager.wasEnableEquipmentCalled(), "enableEquipment should not be called");
        assertFalse(testManager.wasDisableEquipmentCalled(), "disableEquipment should not be called");
        assertFalse(testManager.wasSetMaintenanceCalled(), "setMaintenance should not be called");
    }

    @Test
    @DisplayName("Test case-insensitive action types")
    void testCaseInsensitiveActionTypes() {
        equipmentAction = new EquipmentAction(testManager, equipment, "add");

        equipmentAction.execute();

        assertTrue(testManager.wasAddEquipmentCalled(), "addEquipment should be called with lowercase 'add'");
        assertEquals(equipment, testManager.getLastAddedEquipment(), "Equipment should match");
    }

    @Test
    @DisplayName("Test undo restores previous status")
    void testUndoRestoresPreviousStatus() {
        equipment.setStatus(EquipmentStatus.AVAILABLE);
        equipmentAction = new EquipmentAction(testManager, equipment, "DISABLE");

        equipmentAction.execute();
        equipment.setStatus(EquipmentStatus.DISABLED); // Simulate status change
        equipmentAction.undo();

        assertTrue(testManager.wasUpdateEquipmentStatusCalled(), "updateEquipmentStatus should have been called");
        assertEquals(EquipmentStatus.AVAILABLE, equipment.getStatus());
    }

    @Test
    @DisplayName("Test undo when previous status is null")
    void testUndoWithNullPreviousStatus() {
        equipmentAction = new EquipmentAction(testManager, equipment, "ADD");
        // Don't call execute, so previousStatus remains null

        equipmentAction.undo();

        // Should not throw exception and should not call update
        assertFalse(testManager.wasUpdateEquipmentStatusCalled(), "updateEquipmentStatus should not be called");
    }

    @Test
    @DisplayName("Test undo from MAINTENANCE status")
    void testUndoFromMaintenanceStatus() {
        equipment.setStatus(EquipmentStatus.AVAILABLE);
        equipmentAction = new EquipmentAction(testManager, equipment, "MAINTENANCE");

        equipmentAction.execute();
        equipment.setStatus(EquipmentStatus.MAINTENANCE); // Simulate status change
        equipmentAction.undo();

        assertTrue(testManager.wasUpdateEquipmentStatusCalled(), "updateEquipmentStatus should have been called");
        assertEquals(EquipmentStatus.AVAILABLE, equipment.getStatus());
    }

    @Test
    @DisplayName("Test equipment reference is maintained")
    void testEquipmentReferenceIsMaintained() {
        equipment.setName("Advanced Microscope");
        equipmentAction = new EquipmentAction(testManager, equipment, "ADD");

        equipmentAction.execute();

        assertEquals("Advanced Microscope", testManager.getLastAddedEquipment().getName(), "Equipment name should match");
    }

    @Test
    @DisplayName("Test multiple consecutive actions")
    void testMultipleConsecutiveActions() {
        equipment.setStatus(EquipmentStatus.AVAILABLE);

        // First action: DISABLE
        equipmentAction = new EquipmentAction(testManager, equipment, "DISABLE");
        equipmentAction.execute();
        equipment.setStatus(EquipmentStatus.DISABLED);

        // Second action: ENABLE
        EquipmentAction enableAction = new EquipmentAction(testManager, equipment, "ENABLE");
        enableAction.execute();

        assertTrue(testManager.wasDisableEquipmentCalled(), "disableEquipment should have been called");
        assertTrue(testManager.wasEnableEquipmentCalled(), "enableEquipment should have been called");
    }

    @Test
    @DisplayName("Test undo after multiple status changes")
    void testUndoAfterMultipleStatusChanges() {
        equipment.setStatus(EquipmentStatus.AVAILABLE);
        equipmentAction = new EquipmentAction(testManager, equipment, "DISABLE");

        equipmentAction.execute();
        EquipmentStatus statusBeforeUndoCall = EquipmentStatus.DISABLED;
        equipment.setStatus(statusBeforeUndoCall);

        equipmentAction.undo();

        // Should restore to AVAILABLE (the status before execute was called)
        assertEquals(EquipmentStatus.AVAILABLE, equipment.getStatus());
    }
}

/**
 * TestEquipmentManager is a test double that simulates EquipmentManager behavior
 * without actually performing file I/O or database operations.
 * It tracks which methods were called and stores the parameters for verification.
 */
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

    // Getter methods for verification
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