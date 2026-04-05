package Test.Manual.Sensors;

import org.example.sensors.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EquipmentSensorTest {

    private EquipmentSensor sensor;
    private TestSensorObserver observer1;
    private TestSensorObserver observer2;

    @BeforeEach
    void setUp() {
        sensor = new EquipmentSensor("EQ001");
        observer1 = new TestSensorObserver();
        observer2 = new TestSensorObserver();
    }


    @Test
    void testSensorInitialization() {
        assertEquals("EQ001", sensor.getEquipmentId(), "Equipment ID should match");
    }

    @Test
    void testSensorInitialStatus() {
        assertEquals("AVAILABLE", sensor.getCurrentStatus(), "Initial status should be AVAILABLE");
    }

    @Test
    void testSensorInitialEmptyObservers() {
        EquipmentSensor newSensor = new EquipmentSensor("EQ002");
        // Verify no observers are notified when trigger is called on fresh sensor
        newSensor.triggerUpdate("MAINTENANCE");
        // If no exception is thrown, observers list is initialized properly
        assertDoesNotThrow(() -> newSensor.triggerUpdate("AVAILABLE"));
    }


    @Test
    void testAddSingleObserver() {
        sensor.addObserver(observer1);
        
        sensor.triggerUpdate("MAINTENANCE");
        
        assertTrue(observer1.wasUpdateCalled(), "Observer should be notified");
        assertEquals("EQ001", observer1.getLastEquipmentId(), "Equipment ID should match");
        assertEquals("MAINTENANCE", observer1.getLastStatus(), "Status should match");
    }

    @Test
    void testAddMultipleObservers() {
        sensor.addObserver(observer1);
        sensor.addObserver(observer2);
        
        sensor.triggerUpdate("DISABLED");
        
        assertTrue(observer1.wasUpdateCalled(), "First observer should be notified");
        assertTrue(observer2.wasUpdateCalled(), "Second observer should be notified");
        assertEquals("DISABLED", observer1.getLastStatus(), "First observer status should match");
        assertEquals("DISABLED", observer2.getLastStatus(), "Second observer status should match");
    }

    @Test
    void testAddSameObserverMultipleTimes() {
        sensor.addObserver(observer1);
        sensor.addObserver(observer1);
        
        sensor.triggerUpdate("MAINTENANCE");
        
        // Observer should be notified twice
        assertEquals(2, observer1.getUpdateCallCount(), "Observer should be notified twice");
    }


    @Test
    void testRemoveObserver() {
        sensor.addObserver(observer1);
        sensor.addObserver(observer2);
        
        sensor.removeObserver(observer1);
        sensor.triggerUpdate("MAINTENANCE");
        
        assertFalse(observer1.wasUpdateCalled(), "Removed observer should not be notified");
        assertTrue(observer2.wasUpdateCalled(), "Remaining observer should be notified");
    }

    @Test
    void testRemoveNonExistentObserver() {
        sensor.addObserver(observer1);

        assertDoesNotThrow(() -> sensor.removeObserver(observer2));

        sensor.triggerUpdate("AVAILABLE");
        assertTrue(observer1.wasUpdateCalled(), "Non-removed observer should still be notified");
    }

    @Test
    void testRemoveObserverFromMultiple() {
        sensor.addObserver(observer1);
        sensor.addObserver(observer2);
        TestSensorObserver observer3 = new TestSensorObserver();
        sensor.addObserver(observer3);
        
        sensor.removeObserver(observer2);
        sensor.triggerUpdate("MAINTENANCE");
        
        assertTrue(observer1.wasUpdateCalled(), "Observer 1 should be notified");
        assertFalse(observer2.wasUpdateCalled(), "Observer 2 should not be notified");
        assertTrue(observer3.wasUpdateCalled(), "Observer 3 should be notified");
    }


    @Test
    void testTriggerUpdate() {
        sensor.addObserver(observer1);
        sensor.addObserver(observer2);
        
        sensor.triggerUpdate("IN_USE");
        
        assertTrue(observer1.wasUpdateCalled(), "First observer should be notified");
        assertTrue(observer2.wasUpdateCalled(), "Second observer should be notified");
    }

    @Test
    void testTriggerUpdateChangesStatus() {
        assertEquals("AVAILABLE", sensor.getCurrentStatus(), "Initial status should be AVAILABLE");
        
        sensor.triggerUpdate("MAINTENANCE");
        
        assertEquals("MAINTENANCE", sensor.getCurrentStatus(), "Status should be updated to MAINTENANCE");
    }

    @Test
    void testMultipleTriggerUpdates() {
        sensor.addObserver(observer1);
        
        sensor.triggerUpdate("MAINTENANCE");
        assertEquals("MAINTENANCE", sensor.getCurrentStatus());
        assertEquals("MAINTENANCE", observer1.getLastStatus());
        
        sensor.triggerUpdate("AVAILABLE");
        assertEquals("AVAILABLE", sensor.getCurrentStatus());
        assertEquals("AVAILABLE", observer1.getLastStatus());
        
        sensor.triggerUpdate("IN_USE");
        assertEquals("IN_USE", sensor.getCurrentStatus());
        assertEquals("IN_USE", observer1.getLastStatus());
    }

    @Test
    void testTriggerUpdateNoObservers() {
        assertDoesNotThrow(() -> sensor.triggerUpdate("MAINTENANCE"));
        assertEquals("MAINTENANCE", sensor.getCurrentStatus(), "Status should be updated");
    }


    @Test
    void testNotifyObserversEquipmentId() {
        sensor.addObserver(observer1);
        
        sensor.notifyObservers("EQ001", "MAINTENANCE");
        
        assertEquals("EQ001", observer1.getLastEquipmentId(), "Equipment ID should be passed correctly");
    }

    @Test
    void testNotifyObserversStatus() {
        sensor.addObserver(observer1);
        
        sensor.notifyObservers("EQ001", "DISABLED");
        
        assertEquals("DISABLED", observer1.getLastStatus(), "Status should be passed correctly");
    }

    @Test
    void testNotifyAllObserversWithSameData() {
        sensor.addObserver(observer1);
        sensor.addObserver(observer2);
        
        sensor.notifyObservers("EQ001", "AVAILABLE");
        
        assertEquals("EQ001", observer1.getLastEquipmentId());
        assertEquals("EQ001", observer2.getLastEquipmentId());
        assertEquals("AVAILABLE", observer1.getLastStatus());
        assertEquals("AVAILABLE", observer2.getLastStatus());
    }


    @Test
    void testGetEquipmentId() {
        EquipmentSensor sensor2 = new EquipmentSensor("EQ002");
        EquipmentSensor sensor3 = new EquipmentSensor("EQ003");
        
        assertEquals("EQ001", sensor.getEquipmentId());
        assertEquals("EQ002", sensor2.getEquipmentId());
        assertEquals("EQ003", sensor3.getEquipmentId());
    }

    @Test
    void testGetCurrentStatus() {
        assertEquals("AVAILABLE", sensor.getCurrentStatus());
        
        sensor.triggerUpdate("MAINTENANCE");
        assertEquals("MAINTENANCE", sensor.getCurrentStatus());
        
        sensor.triggerUpdate("DISABLED");
        assertEquals("DISABLED", sensor.getCurrentStatus());
    }


    @Test
    void testCompleteObserverLifecycle() {
        sensor.addObserver(observer1);
        sensor.addObserver(observer2);
        sensor.triggerUpdate("MAINTENANCE");
        assertTrue(observer1.wasUpdateCalled());
        assertTrue(observer2.wasUpdateCalled());
        sensor.removeObserver(observer1);
        observer1.reset();
        observer2.reset();
        sensor.triggerUpdate("AVAILABLE");
        assertFalse(observer1.wasUpdateCalled(), "Removed observer should not be notified");
        assertTrue(observer2.wasUpdateCalled(), "Remaining observer should be notified");
    }

    @Test
    void testRapidObserverChanges() {
        TestSensorObserver obs3 = new TestSensorObserver();

        sensor.addObserver(observer1);
        sensor.addObserver(observer2);
        sensor.addObserver(obs3);
        sensor.removeObserver(observer2);
        sensor.triggerUpdate("IN_USE");
        
        assertTrue(observer1.wasUpdateCalled());
        assertFalse(observer2.wasUpdateCalled());
        assertTrue(obs3.wasUpdateCalled());
    }

    @Test
    void testMultipleSensorsIndependence() {
        EquipmentSensor sensor2 = new EquipmentSensor("EQ002");
        TestSensorObserver obs2 = new TestSensorObserver();
        sensor.addObserver(observer1);
        sensor2.addObserver(obs2);
        sensor.triggerUpdate("MAINTENANCE");
        sensor2.triggerUpdate("DISABLED");

        assertEquals("MAINTENANCE", sensor.getCurrentStatus());
        assertEquals("DISABLED", sensor2.getCurrentStatus());
        assertEquals("EQ001", observer1.getLastEquipmentId());
        assertEquals("EQ002", obs2.getLastEquipmentId());
    }

    @Test
    void testObserverMultipleSensors() {
        EquipmentSensor sensor2 = new EquipmentSensor("EQ002");
        sensor.addObserver(observer1);
        sensor2.addObserver(observer1);
        sensor.triggerUpdate("MAINTENANCE");

        assertEquals("EQ001", observer1.getLastEquipmentId());
        assertEquals("MAINTENANCE", observer1.getLastStatus());
        sensor2.triggerUpdate("IN_USE");
        assertEquals("EQ002", observer1.getLastEquipmentId());
        assertEquals("IN_USE", observer1.getLastStatus());
    }
}

class TestSensorObserver implements SensorObserver {
    
    private boolean updateCalled = false;
    private String lastEquipmentId = null;
    private String lastStatus = null;
    private int updateCallCount = 0;

    @Override
    public void update(String equipmentId, String status) {
        updateCalled = true;
        lastEquipmentId = equipmentId;
        lastStatus = status;
        updateCallCount++;
    }

    public boolean wasUpdateCalled() {
        return updateCalled;
    }

    public String getLastEquipmentId() {
        return lastEquipmentId;
    }

    public String getLastStatus() {
        return lastStatus;
    }

    public int getUpdateCallCount() {
        return updateCallCount;
    }

    public void reset() {
        updateCalled = false;
        lastEquipmentId = null;
        lastStatus = null;
        updateCallCount = 0;
    }
}
