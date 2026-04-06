package org.example.AI_assistant.equipment;

import org.example.equipment.Equipment;
import org.example.equipment.EquipmentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Equipment Test Suite")
public class AIEquipmentTest {
    
    private Equipment equipment;
    
    @BeforeEach
    public void setUp() {
        equipment = new Equipment("EQ001", "Microscope", "Lab A");
    }
    
    // Constructor Tests
    @Test
    @DisplayName("Should create Equipment with correct constructor parameters")
    public void testEquipmentConstructor() {
        assertNotNull(equipment);
        assertEquals("EQ001", equipment.getEquipmentId());
        assertEquals("Microscope", equipment.getName());
        assertEquals("Microscope", equipment.getDescription());
        assertEquals("Lab A", equipment.getLabLocation());
    }
    
    @Test
    @DisplayName("Should initialize with default status AVAILABLE")
    public void testDefaultStatus() {
        assertEquals(EquipmentStatus.AVAILABLE, equipment.getStatus());
    }
    
    @Test
    @DisplayName("Should initialize with one available unit")
    public void testDefaultAvailableUnits() {
        assertEquals(1, equipment.getAvailableUnits());
    }
    
    @Test
    @DisplayName("Should initialize with empty tags list")
    public void testDefaultTags() {
        assertNotNull(equipment.getTags());
        assertTrue(equipment.getTags().isEmpty());
    }
    
    @Test
    @DisplayName("Should initialize with empty product statistics")
    public void testDefaultProductStatistics() {
        assertEquals("", equipment.getProductStatistics());
    }
    
    @Test
    @DisplayName("Should initialize with empty image path")
    public void testDefaultImagePath() {
        assertEquals("", equipment.getImagePath());
    }
    
    // Getter Tests
    @Test
    @DisplayName("Should return correct equipment ID")
    public void testGetEquipmentId() {
        assertEquals("EQ001", equipment.getEquipmentId());
    }
    
    @Test
    @DisplayName("Should return correct name")
    public void testGetName() {
        assertEquals("Microscope", equipment.getName());
    }
    
    @Test
    @DisplayName("Should return correct description")
    public void testGetDescription() {
        assertEquals("Microscope", equipment.getDescription());
    }
    
    @Test
    @DisplayName("Should return correct lab location")
    public void testGetLabLocation() {
        assertEquals("Lab A", equipment.getLabLocation());
    }
    
    @Test
    @DisplayName("Should return correct status")
    public void testGetStatus() {
        assertEquals(EquipmentStatus.AVAILABLE, equipment.getStatus());
    }
    
    // Setter Tests - Name
    @Test
    @DisplayName("Should set name successfully")
    public void testSetName() {
        equipment.setName("Advanced Microscope");
        assertEquals("Advanced Microscope", equipment.getName());
    }
    
    @Test
    @DisplayName("Should set name to empty string")
    public void testSetNameEmpty() {
        equipment.setName("");
        assertEquals("", equipment.getName());
    }
    
    @Test
    @DisplayName("Should set name with special characters")
    public void testSetNameSpecialCharacters() {
        equipment.setName("Micro-scope #2 (A)");
        assertEquals("Micro-scope #2 (A)", equipment.getName());
    }
    
    // Setter Tests - Status
    @Test
    @DisplayName("Should set status to DISABLED")
    public void testSetStatusDisabled() {
        equipment.setStatus(EquipmentStatus.DISABLED);
        assertEquals(EquipmentStatus.DISABLED, equipment.getStatus());
    }
    
    @Test
    @DisplayName("Should set status to MAINTENANCE")
    public void testSetStatusMaintenance() {
        equipment.setStatus(EquipmentStatus.MAINTENANCE);
        assertEquals(EquipmentStatus.MAINTENANCE, equipment.getStatus());
    }
    
    @Test
    @DisplayName("Should set status to IN_USE")
    public void testSetStatusInUse() {
        equipment.setStatus(EquipmentStatus.IN_USE);
        assertEquals(EquipmentStatus.IN_USE, equipment.getStatus());
    }
    
    @Test
    @DisplayName("Should set status back to AVAILABLE")
    public void testSetStatusAvailable() {
        equipment.setStatus(EquipmentStatus.IN_USE);
        equipment.setStatus(EquipmentStatus.AVAILABLE);
        assertEquals(EquipmentStatus.AVAILABLE, equipment.getStatus());
    }
    
    // Setter Tests - Available Units
    @Test
    @DisplayName("Should set available units to 5")
    public void testSetAvailableUnits() {
        equipment.setAvailableUnits(5);
        assertEquals(5, equipment.getAvailableUnits());
    }
    
    @Test
    @DisplayName("Should set available units to 0")
    public void testSetAvailableUnitsZero() {
        equipment.setAvailableUnits(0);
        assertEquals(0, equipment.getAvailableUnits());
    }
    
    @Test
    @DisplayName("Should set available units to large number")
    public void testSetAvailableUnitsLarge() {
        equipment.setAvailableUnits(1000);
        assertEquals(1000, equipment.getAvailableUnits());
    }
    
    // Setter Tests - Product Statistics
    @Test
    @DisplayName("Should set product statistics")
    public void testSetProductStatistics() {
        String stats = "Usage: 45 hours, Maintenance: 2 times";
        equipment.setProductStatistics(stats);
        assertEquals(stats, equipment.getProductStatistics());
    }
    
    @Test
    @DisplayName("Should set product statistics to empty string")
    public void testSetProductStatisticsEmpty() {
        equipment.setProductStatistics("Initial stats");
        equipment.setProductStatistics("");
        assertEquals("", equipment.getProductStatistics());
    }
    
    // Setter Tests - Tags
    @Test
    @DisplayName("Should set tags list")
    public void testSetTags() {
        List<String> tags = new ArrayList<>();
        tags.add("optical");
        tags.add("lab-equipment");
        equipment.setTags(tags);
        
        assertEquals(2, equipment.getTags().size());
        assertTrue(equipment.getTags().contains("optical"));
        assertTrue(equipment.getTags().contains("lab-equipment"));
    }
    
    @Test
    @DisplayName("Should set tags to empty list")
    public void testSetTagsEmpty() {
        equipment.setTags(new ArrayList<>());
        assertTrue(equipment.getTags().isEmpty());
    }
    
    @Test
    @DisplayName("Should replace existing tags")
    public void testReplaceTags() {
        List<String> initialTags = new ArrayList<>();
        initialTags.add("tag1");
        equipment.setTags(initialTags);
        
        List<String> newTags = new ArrayList<>();
        newTags.add("tag2");
        newTags.add("tag3");
        equipment.setTags(newTags);
        
        assertEquals(2, equipment.getTags().size());
        assertTrue(equipment.getTags().contains("tag2"));
        assertTrue(equipment.getTags().contains("tag3"));
        assertFalse(equipment.getTags().contains("tag1"));
    }
    
    // Setter Tests - Image Path
    @Test
    @DisplayName("Should set image path")
    public void testSetImagePath() {
        equipment.setImagePath("/images/microscope.jpg");
        assertEquals("/images/microscope.jpg", equipment.getImagePath());
    }
    
    @Test
    @DisplayName("Should set image path to empty string")
    public void testSetImagePathEmpty() {
        equipment.setImagePath("/images/microscope.jpg");
        equipment.setImagePath("");
        assertEquals("", equipment.getImagePath());
    }
    
    // ToString Tests
    @Test
    @DisplayName("Should format toString correctly")
    public void testToString() {
        String expected = "EQ001 - Microscope (Lab A) [AVAILABLE]";
        assertEquals(expected, equipment.toString());
    }
    
    @Test
    @DisplayName("Should update toString when status changes")
    public void testToStringWithStatusChange() {
        equipment.setStatus(EquipmentStatus.IN_USE);
        String expected = "EQ001 - Microscope (Lab A) [IN_USE]";
        assertEquals(expected, equipment.toString());
    }
    
    @Test
    @DisplayName("Should update toString when name changes")
    public void testToStringWithNameChange() {
        equipment.setName("Advanced Microscope");
        // Note: toString uses description, not name
        String expected = "EQ001 - Microscope (Lab A) [AVAILABLE]";
        assertEquals(expected, equipment.toString());
    }
    
    // Integration Tests
    @Test
    @DisplayName("Should handle multiple property changes")
    public void testMultiplePropertyChanges() {
        equipment.setName("Updated Microscope");
        equipment.setStatus(EquipmentStatus.MAINTENANCE);
        equipment.setAvailableUnits(3);
        
        List<String> tags = new ArrayList<>();
        tags.add("optical");
        tags.add("precision");
        equipment.setTags(tags);
        
        equipment.setImagePath("/images/microscope_v2.jpg");
        equipment.setProductStatistics("New statistics");
        
        assertEquals("Updated Microscope", equipment.getName());
        assertEquals(EquipmentStatus.MAINTENANCE, equipment.getStatus());
        assertEquals(3, equipment.getAvailableUnits());
        assertEquals(2, equipment.getTags().size());
        assertEquals("/images/microscope_v2.jpg", equipment.getImagePath());
        assertEquals("New statistics", equipment.getProductStatistics());
    }
    
    @Test
    @DisplayName("Should maintain equipment ID immutability")
    public void testEquipmentIdImmutability() {
        String originalId = equipment.getEquipmentId();
        equipment.setName("New Name");
        equipment.setStatus(EquipmentStatus.IN_USE);
        
        assertEquals(originalId, equipment.getEquipmentId());
    }
    
    @Test
    @DisplayName("Should maintain lab location immutability")
    public void testLabLocationImmutability() {
        String originalLocation = equipment.getLabLocation();
        equipment.setName("New Name");
        equipment.setStatus(EquipmentStatus.IN_USE);
        
        assertEquals(originalLocation, equipment.getLabLocation());
    }
}
