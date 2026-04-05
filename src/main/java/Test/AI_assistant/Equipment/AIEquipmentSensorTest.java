package Test.AI_assistant.Equipment;

import org.example.sensors.EquipmentSensor;
import org.example.sensors.SensorObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AIEquipmentSensorTest {

    private EquipmentSensor sensor;
    private StubObserver observer1;
    private StubObserver observer2;

    @BeforeEach
    public void setUp() {
        sensor = new EquipmentSensor("EQ-001");
        observer1 = new StubObserver();
        observer2 = new StubObserver();
    }

    @Test
    public void testInitialState() {
        assertEquals("EQ-001", sensor.getEquipmentId());
        assertEquals("AVAILABLE", sensor.getCurrentStatus());
    }

    @Test
    public void testAddObserverAndTriggerUpdate() {
        sensor.addObserver(observer1);
        sensor.triggerUpdate("MAINTENANCE");

        assertEquals("MAINTENANCE", sensor.getCurrentStatus());
        assertTrue(observer1.wasUpdated);
        assertEquals("EQ-001", observer1.lastEquipmentId);
        assertEquals("MAINTENANCE", observer1.lastStatus);
    }

    @Test
    public void testMultipleObservers() {
        sensor.addObserver(observer1);
        sensor.addObserver(observer2);

        sensor.triggerUpdate("DISABLED");

        assertTrue(observer1.wasUpdated);
        assertEquals("DISABLED", observer1.lastStatus);

        assertTrue(observer2.wasUpdated);
        assertEquals("DISABLED", observer2.lastStatus);
    }

    @Test
    public void testRemoveObserver() {
        sensor.addObserver(observer1);
        sensor.addObserver(observer2);

        sensor.removeObserver(observer1);
        sensor.triggerUpdate("IN_USE");

        assertFalse(observer1.wasUpdated);
        assertNull(observer1.lastStatus);

        assertTrue(observer2.wasUpdated);
        assertEquals("IN_USE", observer2.lastStatus);
    }

    @Test
    public void testNotifyObserversDirectly() {
        sensor.addObserver(observer1);
        
        // notifyObservers does not automatically change the internal state in EquipmentSensor (triggerUpdate does that)
        sensor.notifyObservers("EQ-001", "BROKEN");

        assertTrue(observer1.wasUpdated);
        assertEquals("EQ-001", observer1.lastEquipmentId);
        assertEquals("BROKEN", observer1.lastStatus);
        
        // Internal state is still default AVAILABLE
        assertEquals("AVAILABLE", sensor.getCurrentStatus());
    }

    private static class StubObserver implements SensorObserver {
        boolean wasUpdated = false;
        String lastEquipmentId = null;
        String lastStatus = null;

        @Override
        public void update(String equipmentId, String status) {
            wasUpdated = true;
            lastEquipmentId = equipmentId;
            lastStatus = status;
        }
    }
}
