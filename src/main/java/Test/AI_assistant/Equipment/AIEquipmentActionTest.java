package Test.AI_assistant.Equipment;

import org.example.equipment.Equipment;
import org.example.equipment.EquipmentAction;
import org.example.equipment.EquipmentManager;
import org.example.equipment.EquipmentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AIEquipmentActionTest {

    private boolean addEquipmentCalled;
    private boolean enableEquipmentCalled;
    private boolean disableEquipmentCalled;
    private boolean setMaintenanceCalled;
    private boolean updateEquipmentStatusCalled;

    private String lastUpdatedEquipmentId;
    private EquipmentStatus lastUpdatedStatus;
    
    private EquipmentManager testManager;
    private Equipment testEquipment;

    @BeforeEach
    public void setUp() {
        addEquipmentCalled = false;
        enableEquipmentCalled = false;
        disableEquipmentCalled = false;
        setMaintenanceCalled = false;
        updateEquipmentStatusCalled = false;
        lastUpdatedEquipmentId = null;
        lastUpdatedStatus = null;

        testManager = new EquipmentManager(null) {
            @Override
            public void addEquipment(Equipment equipment) {
                addEquipmentCalled = true;
            }

            @Override
            public void enableEquipment(String id) {
                enableEquipmentCalled = true;
            }

            @Override
            public void disableEquipment(String id) {
                disableEquipmentCalled = true;
            }

            @Override
            public void setMaintenance(String id) {
                setMaintenanceCalled = true;
            }

            @Override
            public void updateEquipmentStatus(String id, EquipmentStatus status) {
                updateEquipmentStatusCalled = true;
                lastUpdatedEquipmentId = id;
                lastUpdatedStatus = status;
            }
        };

        testEquipment = new Equipment("EQ-1", "Test Equipment", "Lab A");
        testEquipment.setStatus(EquipmentStatus.DISABLED);
    }

    @Test
    public void testExecuteAdd() {
        EquipmentAction action = new EquipmentAction(testManager, testEquipment, "ADD");
        action.execute();
        assertTrue(addEquipmentCalled);
    }

    @Test
    public void testExecuteEnable() {
        EquipmentAction action = new EquipmentAction(testManager, testEquipment, "ENABLE");
        action.execute();
        assertTrue(enableEquipmentCalled);
    }

    @Test
    public void testExecuteDisable() {
        EquipmentAction action = new EquipmentAction(testManager, testEquipment, "DISABLE");
        action.execute();
        assertTrue(disableEquipmentCalled);
    }

    @Test
    public void testExecuteMaintenance() {
        EquipmentAction action = new EquipmentAction(testManager, testEquipment, "MAINTENANCE");
        action.execute();
        assertTrue(setMaintenanceCalled);
    }

    @Test
    public void testUndo() {
        EquipmentAction action = new EquipmentAction(testManager, testEquipment, "ENABLE");
        action.execute();
        
        testEquipment.setStatus(EquipmentStatus.AVAILABLE);

        action.undo();
        
        assertEquals(EquipmentStatus.DISABLED, testEquipment.getStatus());
        assertTrue(updateEquipmentStatusCalled);
        assertEquals("EQ-1", lastUpdatedEquipmentId);
        assertEquals(EquipmentStatus.DISABLED, lastUpdatedStatus);
    }
}
