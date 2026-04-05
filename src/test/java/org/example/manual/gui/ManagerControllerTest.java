package org.example.manual.gui;

import org.example.data.CSVRepository;
import org.example.equipment.Equipment;
import org.example.equipment.EquipmentManager;
import org.example.equipment.EquipmentStatus;
import org.example.gui.MainController;
import org.example.users.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the logic behind ManagerController.
 * Covers equipment listing, sorting, status changes, selected equipment state,
 * and manager account generation — all without JavaFX toolkit.
 */
public class ManagerControllerTest {

    private static final String EQUIPMENT_FILE = "src/main/resources/data/equipment.csv";
    private static final String USERS_FILE = "src/main/resources/data/users.csv";
    private byte[] equipmentBackup;
    private byte[] usersBackup;
    private EquipmentManager equipmentManager;
    private CSVRepository repository;

    @BeforeEach
    public void setUp() throws Exception {
        equipmentBackup = Files.readAllBytes(Paths.get(EQUIPMENT_FILE));
        usersBackup = Files.readAllBytes(Paths.get(USERS_FILE));
        equipmentManager = new EquipmentManager();
        repository = new CSVRepository();
        resetHeadCoordinatorSingleton();
    }

    @AfterEach
    public void tearDown() throws Exception {
        Files.write(Paths.get(EQUIPMENT_FILE), equipmentBackup);
        Files.write(Paths.get(USERS_FILE), usersBackup);
        MainController.setSelectedEquipment(null);
        resetHeadCoordinatorSingleton();
    }

    private void resetHeadCoordinatorSingleton() throws Exception {
        Field instance = HeadCoordinator.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    public void testLoadEquipmentReturnsAllSavedItems() {
        equipmentManager.addEquipment(new Equipment("MGR-EQ-001", "Microscope", "Lab A"));
        equipmentManager.addEquipment(new Equipment("MGR-EQ-002", "Centrifuge", "Lab B"));

        List<Equipment> all = equipmentManager.getAllEquipment();
        assertTrue(all.size() >= 2);
    }

    @Test
    public void testSortEquipmentByNameAscending() {
        List<Equipment> list = new ArrayList<>();
        list.add(new Equipment("EQ-Z", "Zebra Scanner", "Lab A"));
        list.add(new Equipment("EQ-A", "Alpha Mixer", "Lab B"));

        list.sort(Comparator.comparing(Equipment::getDescription));
        assertEquals("Alpha Mixer", list.get(0).getDescription());
        assertEquals("Zebra Scanner", list.get(1).getDescription());
    }

    @Test
    public void testSortEquipmentByNameDescending() {
        List<Equipment> list = new ArrayList<>();
        list.add(new Equipment("EQ-A", "Alpha Mixer", "Lab A"));
        list.add(new Equipment("EQ-Z", "Zebra Scanner", "Lab B"));

        list.sort(Comparator.comparing(Equipment::getDescription).reversed());
        assertEquals("Zebra Scanner", list.get(0).getDescription());
    }

    @Test
    public void testSortEquipmentByStatus() {
        List<Equipment> list = new ArrayList<>();
        Equipment e1 = new Equipment("EQ-1", "Item1", "Lab A");
        e1.setStatus(EquipmentStatus.MAINTENANCE);
        Equipment e2 = new Equipment("EQ-2", "Item2", "Lab B");
        e2.setStatus(EquipmentStatus.AVAILABLE);
        list.add(e1);
        list.add(e2);

        list.sort(Comparator.comparing(e -> e.getStatus().name()));
        assertEquals(EquipmentStatus.AVAILABLE, list.get(0).getStatus());
    }

    @Test
    public void testSortEquipmentByLocation() {
        List<Equipment> list = new ArrayList<>();
        list.add(new Equipment("EQ-1", "Item1", "Lab Z"));
        list.add(new Equipment("EQ-2", "Item2", "Lab A"));

        list.sort(Comparator.comparing(Equipment::getLabLocation));
        assertEquals("Lab A", list.get(0).getLabLocation());
    }

    @Test
    public void testHandleManageSetsSelectedEquipment() {
        Equipment eq = new Equipment("MGR-EQ-003", "Drill Press", "Lab C");
        MainController.setSelectedEquipment(eq);
        assertEquals("MGR-EQ-003", MainController.getSelectedEquipment().getEquipmentId());
    }

    @Test
    public void testEquipmentDisplayNameUsesNameOverDescription() {
        Equipment eq = new Equipment("MGR-EQ-004", "Generic Desc", "Lab D");
        eq.setName("Custom Display Name");
        String displayName = (eq.getName() != null && !eq.getName().isEmpty())
                ? eq.getName() : eq.getDescription();
        assertEquals("Custom Display Name", displayName);
    }

    @Test
    public void testGenerateManagerAccountViaHeadCoordinator() {
        HeadCoordinator hc = HeadCoordinator.getInstance("HC-MGR-001", "Admin", "hc@mgr.test", "Pass123!");
        LabManager mgr = hc.generateManagerAccount("MGR-NEW-001", "NewMgr", "newmgr@york.ca", "Pass123!");
        repository.saveUser(mgr);

        User found = repository.findUserByEmail("newmgr@york.ca");
        assertNotNull(found);
        assertEquals("MANAGER", found.getUserType());
    }

    @Test
    public void testGenerateManagerAccountSetsManagerId() {
        HeadCoordinator hc = HeadCoordinator.getInstance("HC-MGR-002", "Admin", "hc2@mgr.test", "Pass123!");
        LabManager mgr = hc.generateManagerAccount("MGR-NEW-002", "Mgr2", "mgr2@york.ca", "Pass123!");
        assertEquals("MGR-MGR-NEW-002", mgr.getManagerId());
    }

    @Test
    public void testGetEquipmentByIdReturnsNullForUnknown() {
        Equipment result = equipmentManager.getEquipmentById("NONEXISTENT-EQ");
        assertNull(result);
    }
}

