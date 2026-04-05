package org.example.manual.reservation;

import org.example.data.CSVRepository;
import org.example.equipment.Equipment;
import org.example.equipment.EquipmentManager;
import org.example.equipment.EquipmentStatus;
import org.example.payment.Payment;
import org.example.reservation.Reservation;
import org.example.reservation.ReservationManager;
import org.example.reservation.ReservationStatus;
import org.example.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationManagerTest {

    private ReservationManager manager;
    private MockRepository mockRepo;
    private MockEqManager mockEqManager;

    class MockRepository extends CSVRepository {
        public List<Reservation> savedRes = new ArrayList<>();
        public List<String[]> allRows = new ArrayList<>();
        public List<Payment> savedPayments = new ArrayList<>();

        @Override
        public void saveReservation(Reservation res) {
            savedRes.add(res);
            allRows.add(new String[]{res.getReservationId(), "U1", res.getEquipment().getEquipmentId(),
                    res.getStartTime().toString(), res.getEndTime().toString(),
                    res.getStatus().name(), String.valueOf(res.getDeposit())});
        }

        @Override
        public void updateReservation(Reservation res) {
            for (String[] row : allRows) {
                if(row[0].equals(res.getReservationId())) {
                    row[5] = res.getStatus().name();
                    row[4] = res.getEndTime().toString();
                }
            }
        }

        @Override
        public List<String[]> getAllReservationRows() {
            return allRows;
        }

        @Override
        public void savePayment(Payment p) {
            savedPayments.add(p);
        }
    }

    class MockEqManager extends EquipmentManager {
        public Equipment eq;
        @Override
        public Equipment getEquipmentById(String id) {
            return eq;
        }
    }

    @BeforeEach
    void setUp() throws Exception {
        manager = new ReservationManager();

        mockRepo = new MockRepository();
        mockEqManager = new MockEqManager();

        Field repoField = ReservationManager.class.getDeclaredField("repository");
        repoField.setAccessible(true);
        repoField.set(manager, mockRepo);

        Field eqField = ReservationManager.class.getDeclaredField("equipmentManager");
        eqField.setAccessible(true);
        eqField.set(manager, mockEqManager);
    }

    @Test
    void testCreateReservation() {
        User user = new User("U1", "T", "E", "P", "STUDENT", "D", "ID") {
            public String getUserType() { return "STUDENT"; }
        };
        Equipment eq = new Equipment("E1", "Desc", "Lab1");
        eq.setStatus(EquipmentStatus.AVAILABLE);

        mockEqManager.eq = eq;
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);

        Reservation res = manager.createReservation(user, eq, start, end);
        assertNotNull(res);
        assertEquals(ReservationStatus.CONFIRMED, res.getStatus());
        assertEquals(1, mockRepo.savedRes.size());
    }

    @Test
    void testCreateReservationNotAvailable() {
        User user = new User("U1", "T", "E", "P", "STUDENT", "D", "ID") {
            public String getUserType() { return "STUDENT"; }
        };
        Equipment eq = new Equipment("E1", "Desc", "Lab1");
        eq.setStatus(EquipmentStatus.IN_USE);

        Reservation res = manager.createReservation(user, eq, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        assertNull(res);
    }

    @Test
    void testCancelReservation() {
        User user = new User("U1", "T", "E", "P", "STUDENT", "D", "ID") {
            public String getUserType() { return "STUDENT"; }
        };
        Equipment eq = new Equipment("E1", "Desc", "Lab1");
        mockEqManager.eq = eq;
        
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = new Reservation("R1", user, eq, start, end, 10.0);
        res.setStatus(ReservationStatus.CONFIRMED);
        
        mockRepo.saveReservation(res);

        manager.cancelReservation("R1");
        Reservation fetched = manager.getReservationById("R1");
        assertNotNull(fetched);
        assertEquals(ReservationStatus.CANCELLED, fetched.getStatus());
    }

    @Test
    void testExtendReservation() {
        User user = new User("U1", "T", "E", "P", "STUDENT", "D", "ID") {
            public String getUserType() { return "STUDENT"; }
        };
        Equipment eq = new Equipment("E2", "Desc", "Lab1");
        mockEqManager.eq = eq;

        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = new Reservation("R2", user, eq, start, end, 10.0);
        res.setStatus(ReservationStatus.CONFIRMED);
        mockRepo.saveReservation(res);

        LocalDateTime newEnd = end.plusHours(2);
        manager.extendReservation("R2", newEnd);

        Reservation fetched = manager.getReservationById("R2");
        assertEquals(newEnd, fetched.getEndTime());
        assertEquals(ReservationStatus.EXTENDED, fetched.getStatus());
    }

    @Test
    void testProcessDeposit() {
        User user = new User("U1", "T", "E", "P", "STUDENT", "D", "ID") {
            public String getUserType() { return "STUDENT"; }
        };
        Equipment eq = new Equipment("E1", "Desc", "Lab1");
        Reservation res = new Reservation("R1", user, eq, LocalDateTime.now(), LocalDateTime.now().plusHours(2), 5.0);
        
        Payment p = manager.processDeposit(res, "CC", "Ref123");
        assertNotNull(p);
        assertTrue(p.isDeposit());
        assertEquals("CC", p.getPaymentMethod());
        assertEquals(1, mockRepo.savedPayments.size());
    }

    @Test
    void testIsEquipmentAvailableOverlap() {
        User user = new User("U1", "T", "E", "P", "STUDENT", "D", "ID") {
            public String getUserType() { return "STUDENT"; }
        };
        Equipment eq = new Equipment("E1", "Desc", "Lab1");
        mockEqManager.eq = eq;
        
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = new Reservation("R1", user, eq, start, end, 10.0);
        res.setStatus(ReservationStatus.CONFIRMED);
        mockRepo.saveReservation(res);

        boolean available = manager.isEquipmentAvailable("E1", start.plusHours(1), start.plusHours(3));
        assertFalse(available);
    }
    
    @Test
    void testUpdateStatusObserver() {
        User user = new User("U1", "T", "E", "P", "STUDENT", "D", "ID") {
            public String getUserType() { return "STUDENT"; }
        };
        Equipment eq = new Equipment("E1", "Desc", "Lab1");
        mockEqManager.eq = eq;
        
        LocalDateTime start = LocalDateTime.now().plusDays(1); // active later
        LocalDateTime end = start.plusHours(2);
        Reservation res = new Reservation("R1", user, eq, start, end, 10.0);
        res.setStatus(ReservationStatus.CONFIRMED);
        
        mockRepo.saveReservation(res);

        manager.update("E1", "MAINTENANCE");

        Reservation fetched = manager.getReservationById("R1");
        assertEquals(ReservationStatus.CANCELLED, fetched.getStatus());
    }

    @Test
    void testCreateReservationOverload() {
        User user = new User("U1", "T", "E", "P", "STUDENT", "D", "ID") {
            public String getUserType() { return "STUDENT"; }
        };
        Equipment eq = new Equipment("E1", "Desc", "Lab1");
        Reservation res = new Reservation("R1", user, eq, LocalDateTime.now(), LocalDateTime.now().plusHours(2), 10.0);
        
        manager.createReservation(res);
        assertEquals(1, mockRepo.savedRes.size());
    }

    @Test
    void testModifyReservation() {
        User user = new User("U1", "T", "E", "P", "STUDENT", "D", "ID") {
            public String getUserType() { return "STUDENT"; }
        };
        Equipment eq = new Equipment("E1", "Desc", "Lab1");
        mockEqManager.eq = eq;
        
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = new Reservation("R1", user, eq, start, end, 10.0);
        res.setStatus(ReservationStatus.CONFIRMED);
        
        mockRepo.saveReservation(res);

        LocalDateTime newEnd = start.plusHours(3);
        manager.modifyReservation("R1", newEnd);

        Reservation fetched = manager.getReservationById("R1");
        assertNotNull(fetched);
        assertEquals(newEnd, fetched.getEndTime());
    }

    @Test
    void testUpdateReservation() {
        User user = new User("U1", "T", "E", "P", "STUDENT", "D", "ID") {
            public String getUserType() { return "STUDENT"; }
        };
        Equipment eq = new Equipment("E1", "Desc", "Lab1");
        mockEqManager.eq = eq;
        
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = new Reservation("R1", user, eq, start, end, 10.0);
        res.setStatus(ReservationStatus.CONFIRMED);
        
        mockRepo.saveReservation(res);
        
        res.setStatus(ReservationStatus.CANCELLED);
        manager.updateReservation(res);

        Reservation fetched = manager.getReservationById("R1");
        assertNotNull(fetched);
        assertEquals(ReservationStatus.CANCELLED, fetched.getStatus());
    }

    @Test
    void testProcessFinalPayment() {
        User user = new User("U1", "T", "E", "P", "STUDENT", "D", "ID") {
            public String getUserType() { return "STUDENT"; }
        };
        Equipment eq = new Equipment("E1", "Desc", "Lab1");
        Reservation res = new Reservation("R1", user, eq, LocalDateTime.now(), LocalDateTime.now().plusHours(2), 5.0);
        
        Payment p = manager.processFinalPayment(res, "CC", "Ref123", true);
        assertNotNull(p);
        assertFalse(p.isDeposit());
        assertEquals("CC", p.getPaymentMethod());
        assertEquals(1, mockRepo.savedPayments.size());
    }

    @Test
    void testGetAllReservationRows() {
        User user = new User("U1", "T", "E", "P", "STUDENT", "D", "ID") {
            public String getUserType() { return "STUDENT"; }
        };
        Equipment eq = new Equipment("E1", "Desc", "Lab1");
        Reservation res = new Reservation("R1", user, eq, LocalDateTime.now(), LocalDateTime.now().plusHours(2), 10.0);
        res.setStatus(ReservationStatus.CONFIRMED);
        
        mockRepo.saveReservation(res);

        List<String[]> rows = manager.getAllReservationRows();
        assertEquals(1, rows.size());
        assertEquals("R1", rows.get(0)[0]);
    }
}
