package Test.AI_assistant.Equipment;

import org.example.data.CSVRepository;
import org.example.equipment.Equipment;
import org.example.equipment.EquipmentManager;
import org.example.equipment.EquipmentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AIEquipmentManagerTest {

    private StubCSVRepository stubRepo;
    private EquipmentManager manager;

    @BeforeEach
    public void setUp() {
        stubRepo = new StubCSVRepository();
        manager = new EquipmentManager(stubRepo);
    }

    @Test
    public void testAddEquipment() {
        Equipment eq = new Equipment("EQ-001", "Microscope", "Lab A");
        manager.addEquipment(eq);

        assertEquals(1, stubRepo.getAllEquipment().size());
        assertEquals("EQ-001", stubRepo.getAllEquipment().get(0).getEquipmentId());
    }

    @Test
    public void testUpdateEquipment() {
        Equipment eq = new Equipment("EQ-001", "Microscope", "Lab A");
        manager.addEquipment(eq);

        eq.setName("Advanced Microscope");
        manager.updateEquipment(eq);

        Equipment updated = manager.getEquipmentById("EQ-001");
        assertNotNull(updated);
        assertEquals("Advanced Microscope", updated.getName());
    }

    @Test
    public void testGetAllEquipment() {
        manager.addEquipment(new Equipment("EQ-001", "Microscope", "Lab A"));
        manager.addEquipment(new Equipment("EQ-002", "Telescope", "Lab B"));

        List<Equipment> all = manager.getAllEquipment();
        assertEquals(2, all.size());
    }

    @Test
    public void testGetEquipmentById() {
        Equipment eq = new Equipment("EQ-001", "Microscope", "Lab A");
        manager.addEquipment(eq);

        Equipment found = manager.getEquipmentById("EQ-001");
        assertNotNull(found);
        assertEquals("EQ-001", found.getEquipmentId());

        Equipment notFound = manager.getEquipmentById("EQ-999");
        assertNull(notFound);
    }

    @Test
    public void testGetAvailableEquipment() {
        Equipment eq1 = new Equipment("EQ-001", "Eq 1", "Lab A");
        eq1.setStatus(EquipmentStatus.AVAILABLE);

        Equipment eq2 = new Equipment("EQ-002", "Eq 2", "Lab B");
        eq2.setStatus(EquipmentStatus.MAINTENANCE);

        Equipment eq3 = new Equipment("EQ-003", "Eq 3", "Lab C");
        eq3.setStatus(EquipmentStatus.AVAILABLE);

        manager.addEquipment(eq1);
        manager.addEquipment(eq2);
        manager.addEquipment(eq3);

        List<Equipment> available = manager.getAvailableEquipment();
        assertEquals(2, available.size());
        assertTrue(available.stream().allMatch(e -> e.getStatus() == EquipmentStatus.AVAILABLE));
    }

    @Test
    public void testEnableEquipment() {
        Equipment eq = new Equipment("EQ-001", "Microscope", "Lab A");
        eq.setStatus(EquipmentStatus.DISABLED);
        manager.addEquipment(eq);

        manager.enableEquipment("EQ-001");

        Equipment updated = manager.getEquipmentById("EQ-001");
        assertEquals(EquipmentStatus.AVAILABLE, updated.getStatus());
    }

    @Test
    public void testDisableEquipment() {
        Equipment eq = new Equipment("EQ-001", "Microscope", "Lab A");
        eq.setStatus(EquipmentStatus.AVAILABLE);
        manager.addEquipment(eq);

        manager.disableEquipment("EQ-001");

        Equipment updated = manager.getEquipmentById("EQ-001");
        assertEquals(EquipmentStatus.DISABLED, updated.getStatus());
    }

    @Test
    public void testSetMaintenance() {
        Equipment eq = new Equipment("EQ-001", "Microscope", "Lab A");
        eq.setStatus(EquipmentStatus.AVAILABLE);
        manager.addEquipment(eq);

        manager.setMaintenance("EQ-001");

        Equipment updated = manager.getEquipmentById("EQ-001");
        assertEquals(EquipmentStatus.MAINTENANCE, updated.getStatus());
    }

    @Test
    public void testUpdateEquipmentStatus() {
        Equipment eq = new Equipment("EQ-001", "Microscope", "Lab A");
        eq.setStatus(EquipmentStatus.AVAILABLE);
        manager.addEquipment(eq);

        manager.updateEquipmentStatus("EQ-001", EquipmentStatus.DISABLED);

        Equipment updated = manager.getEquipmentById("EQ-001");
        assertEquals(EquipmentStatus.DISABLED, updated.getStatus());
    }

    @Test
    public void testObserverUpdateValidStatus() {
        Equipment eq = new Equipment("EQ-001", "Microscope", "Lab A");
        eq.setStatus(EquipmentStatus.AVAILABLE);
        manager.addEquipment(eq);

        manager.update("EQ-001", "MAINTENANCE");

        Equipment updated = manager.getEquipmentById("EQ-001");
        assertEquals(EquipmentStatus.MAINTENANCE, updated.getStatus());
    }

    @Test
    public void testObserverUpdateInvalidStatus() {
        Equipment eq = new Equipment("EQ-001", "Microscope", "Lab A");
        eq.setStatus(EquipmentStatus.AVAILABLE);
        manager.addEquipment(eq);

        // Invalid status should be caught and print a message without crashing
        assertDoesNotThrow(() -> manager.update("EQ-001", "INVALID_STATUS"));

        // Status should remain unchanged
        Equipment notUpdated = manager.getEquipmentById("EQ-001");
        assertEquals(EquipmentStatus.AVAILABLE, notUpdated.getStatus());
    }

    /**
     * A stub implementation of CSVRepository to avoid actual disk I/O during tests.
     */
    private static class StubCSVRepository extends CSVRepository {
        private final List<Equipment> mockData = new ArrayList<>();

        @Override
        public void saveEquipment(Equipment equipment) {
            mockData.add(equipment);
        }

        @Override
        public void updateEquipment(Equipment equipment) {
            for (int i = 0; i < mockData.size(); i++) {
                if (mockData.get(i).getEquipmentId().equals(equipment.getEquipmentId())) {
                    mockData.set(i, equipment);
                    return;
                }
            }
        }

        @Override
        public List<Equipment> getAllEquipment() {
            return new ArrayList<>(mockData);
        }
    }
}
