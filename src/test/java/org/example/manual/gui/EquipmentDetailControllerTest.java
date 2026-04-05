package org.example.manual.gui;

import org.example.auth.AuthService;
import org.example.data.CSVRepository;
import org.example.equipment.Equipment;
import org.example.equipment.EquipmentManager;
import org.example.equipment.EquipmentStatus;
import org.example.gui.MainController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the logic behind EquipmentDetailController.
 * Exercises EquipmentManager status changes and MainController state
 * that the controller depends on (no JavaFX toolkit required).
 */
public class EquipmentDetailControllerTest {

    private static final String EQUIPMENT_FILE = "src/main/resources/data/equipment.csv";
    private byte[] equipmentBackup;
    private EquipmentManager equipmentManager;

    @BeforeEach
    public void setUp() throws IOException {
        equipmentBackup = Files.readAllBytes(Paths.get(EQUIPMENT_FILE));
        equipmentManager = new EquipmentManager();
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.write(Paths.get(EQUIPMENT_FILE), equipmentBackup);
        MainController.setSelectedEquipment(null);
    }

    @Test
    public void testSetSelectedEquipmentStoresEquipment() {
        Equipment eq = new Equipment("EQ-DET-001", "Microscope", "Lab A");
        MainController.setSelectedEquipment(eq);
        assertEquals("EQ-DET-001", MainController.getSelectedEquipment().getEquipmentId());
    }

    @Test
    public void testGetSelectedEquipmentReturnsNullWhenNotSet() {
        MainController.setSelectedEquipment(null);
        assertNull(MainController.getSelectedEquipment());
    }

    @Test
    public void testDisableEquipmentChangesStatusToDisabled() {
        Equipment eq = new Equipment("EQ-DET-002", "Centrifuge", "Lab B");
        equipmentManager.addEquipment(eq);
        equipmentManager.disableEquipment("EQ-DET-002");

        Equipment updated = equipmentManager.getEquipmentById("EQ-DET-002");
        assertNotNull(updated);
        assertEquals(EquipmentStatus.DISABLED, updated.getStatus());
    }

    @Test
    public void testEnableEquipmentChangesStatusToAvailable() {
        Equipment eq = new Equipment("EQ-DET-003", "Spectrometer", "Lab C");
        equipmentManager.addEquipment(eq);
        equipmentManager.disableEquipment("EQ-DET-003");
        equipmentManager.enableEquipment("EQ-DET-003");

        Equipment updated = equipmentManager.getEquipmentById("EQ-DET-003");
        assertEquals(EquipmentStatus.AVAILABLE, updated.getStatus());
    }

    @Test
    public void testSetMaintenanceChangesStatusToMaintenance() {
        Equipment eq = new Equipment("EQ-DET-004", "Oscilloscope", "Lab D");
        equipmentManager.addEquipment(eq);
        equipmentManager.setMaintenance("EQ-DET-004");

        Equipment updated = equipmentManager.getEquipmentById("EQ-DET-004");
        assertEquals(EquipmentStatus.MAINTENANCE, updated.getStatus());
    }

    @Test
    public void testEquipmentDefaultStatusIsAvailable() {
        Equipment eq = new Equipment("EQ-DET-005", "Voltmeter", "Lab E");
        assertEquals(EquipmentStatus.AVAILABLE, eq.getStatus());
    }

    @Test
    public void testEquipmentDisplayNameFallsBackToDescription() {
        Equipment eq = new Equipment("EQ-DET-006", "3D Printer", "Lab F");
        // Constructor sets name = description
        String displayName = (eq.getName() != null && !eq.getName().isEmpty())
                ? eq.getName() : eq.getDescription();
        assertEquals("3D Printer", displayName);
    }

    @Test
    public void testEquipmentWithExplicitNameUsesName() {
        Equipment eq = new Equipment("EQ-DET-007", "Desc", "Lab G");
        eq.setName("Custom Name");
        String displayName = (eq.getName() != null && !eq.getName().isEmpty())
                ? eq.getName() : eq.getDescription();
        assertEquals("Custom Name", displayName);
    }

    @Test
    public void testEquipmentImagePathDefaultsToEmpty() {
        Equipment eq = new Equipment("EQ-DET-008", "Laser Cutter", "Lab H");
        assertEquals("", eq.getImagePath());
    }

    @Test
    public void testSetImagePathPersistsAfterUpdate() {
        Equipment eq = new Equipment("EQ-DET-009", "CNC Mill", "Lab I");
        equipmentManager.addEquipment(eq);
        eq.setImagePath("/images/cnc.jpg");
        equipmentManager.updateEquipment(eq);

        Equipment updated = equipmentManager.getEquipmentById("EQ-DET-009");
        assertNotNull(updated);
        assertEquals("/images/cnc.jpg", updated.getImagePath());
    }
}

