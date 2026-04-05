package Test.Manual.Equipment;

import org.example.equipment.Equipment;
import org.example.equipment.EquipmentStatus;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EquipmentTest {

    private Equipment equipment;

    @BeforeEach
    void setUp() {
        equipment = new Equipment("EQ-001", "Microscope", "Lab A");
    }

    @Test
    void testEquipmentInitialization() {
        assertEquals("EQ-001", equipment.getEquipmentId());
        assertEquals("Microscope", equipment.getName());
        assertEquals("Microscope", equipment.getDescription());
        assertEquals("Lab A", equipment.getLabLocation());
        assertEquals(EquipmentStatus.AVAILABLE, equipment.getStatus());
        assertEquals(1, equipment.getAvailableUnits());
        assertEquals("", equipment.getProductStatistics());
        assertTrue(equipment.getTags().isEmpty());
        assertEquals("", equipment.getImagePath());
    }

    @Test
    void testEquipmentIdNotNull() {
        assertNotNull(equipment.getEquipmentId());
    }

    @Test
    void testSetAndGetName() {
        equipment.setName("Advanced Microscope");
        assertEquals("Advanced Microscope", equipment.getName());
    }

    @Test
    void testSetAndGetStatus() {
        equipment.setStatus(EquipmentStatus.MAINTENANCE);
        assertEquals(EquipmentStatus.MAINTENANCE, equipment.getStatus());

        equipment.setStatus(EquipmentStatus.DISABLED);
        assertEquals(EquipmentStatus.DISABLED, equipment.getStatus());

        equipment.setStatus(EquipmentStatus.IN_USE);
        assertEquals(EquipmentStatus.IN_USE, equipment.getStatus());
    }

    @Test
    void testSetAndGetAvailableUnits() {
        equipment.setAvailableUnits(5);
        assertEquals(5, equipment.getAvailableUnits());

        equipment.setAvailableUnits(0);
        assertEquals(0, equipment.getAvailableUnits());

        equipment.setAvailableUnits(100);
        assertEquals(100, equipment.getAvailableUnits());
    }

    @Test
    void testSetAndGetProductStatistics() {
        String stats = "Usage: 50 hours, Maintenance: Done";
        equipment.setProductStatistics(stats);
        assertEquals(stats, equipment.getProductStatistics());
    }

    @Test
    void testSetAndGetTags() {
        List<String> tags = new ArrayList<>();
        tags.add("optical");
        tags.add("lab-equipment");
        equipment.setTags(tags);
        assertEquals(2, equipment.getTags().size());
        assertTrue(equipment.getTags().contains("optical"));
        assertTrue(equipment.getTags().contains("lab-equipment"));
    }

    @Test
    void testSetAndGetImagePath() {
        String imagePath = "images/microscope.jpg";
        equipment.setImagePath(imagePath);
        assertEquals(imagePath, equipment.getImagePath());
    }

    @Test
    void testToString() {
        String expectedString = "EQ-001 - Microscope (Lab A) [AVAILABLE]";
        assertEquals(expectedString, equipment.toString());
    }

    @Test
    void testToStringWithDifferentStatuses() {
        equipment.setStatus(EquipmentStatus.MAINTENANCE);
        assertTrue(equipment.toString().contains("MAINTENANCE"));

        equipment.setStatus(EquipmentStatus.DISABLED);
        assertTrue(equipment.toString().contains("DISABLED"));
    }

    @Test
    void testGetLabLocation() {
        assertEquals("Lab A", equipment.getLabLocation());
    }

    @Test
    void testEmptyTagsList() {
        assertTrue(equipment.getTags().isEmpty());
        List<String> newTags = new ArrayList<>();
        equipment.setTags(newTags);
        assertTrue(equipment.getTags().isEmpty());
    }

    @Test
    void testMultipleEquipmentInstances() {
        Equipment equipment2 = new Equipment("EQ-002", "Spectrometer", "Lab B");

        assertEquals("EQ-001", equipment.getEquipmentId());
        assertEquals("EQ-002", equipment2.getEquipmentId());

        equipment.setStatus(EquipmentStatus.MAINTENANCE);
        assertEquals(EquipmentStatus.AVAILABLE, equipment2.getStatus());
    }

    @Test
    void testPreserveInitialValuesAfterModifications() {
        String originalId = equipment.getEquipmentId();
        String originalDescription = equipment.getDescription();
        String originalLocation = equipment.getLabLocation();

        equipment.setName("New Name");
        equipment.setStatus(EquipmentStatus.IN_USE);
        equipment.setAvailableUnits(10);

        assertEquals(originalId, equipment.getEquipmentId());
        assertEquals(originalDescription, equipment.getDescription());
        assertEquals(originalLocation, equipment.getLabLocation());
    }

    @Test
    void testSettersWithNullValues() {
        equipment.setName(null);
        assertNull(equipment.getName());

        equipment.setProductStatistics(null);
        assertNull(equipment.getProductStatistics());

        equipment.setImagePath(null);
        assertNull(equipment.getImagePath());
    }

    @Test
    void testZeroUnitsValid() {
        equipment.setAvailableUnits(0);
        assertEquals(0, equipment.getAvailableUnits());
        assertEquals(EquipmentStatus.AVAILABLE, equipment.getStatus());
    }
}
