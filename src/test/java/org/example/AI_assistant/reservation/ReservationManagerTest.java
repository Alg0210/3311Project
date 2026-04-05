package org.example.AI_assistant.reservation;

import org.example.data.CSVRepository;
import org.example.equipment.Equipment;
import org.example.equipment.EquipmentManager;
import org.example.equipment.EquipmentStatus;
import org.example.payment.Payment;
import org.example.reservation.Reservation;
import org.example.reservation.ReservationManager;
import org.example.reservation.ReservationStatus;
import org.example.users.*;
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

    // ─── MOCK CLASSES ────────────────────────────────────────────

    static class MockRepository extends CSVRepository {
        public List<Reservation> savedRes = new ArrayList<>();
        public List<String[]> allRows = new ArrayList<>();
        public List<Payment> savedPayments = new ArrayList<>();

        @Override
        public void saveReservation(Reservation res) {
            savedRes.add(res);
            allRows.add(new String[]{
                    res.getReservationId(),
                    res.getUser() != null ? res.getUser().getUserId() : "U1",
                    res.getEquipment().getEquipmentId(),
                    res.getStartTime().toString(),
                    res.getEndTime().toString(),
                    res.getStatus().name(),
                    String.valueOf(res.getDeposit())
            });
        }

        @Override
        public void updateReservation(Reservation res) {
            for (String[] row : allRows) {
                if (row[0].equals(res.getReservationId())) {
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

        @Override
        public User findUserById(String userId) {
            return new Student("U1", "Test", "test@test.com", "Pass1!", "CS", "STU-001");
        }
    }

    static class MockEqManager extends EquipmentManager {
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

    // ─── HELPERS ─────────────────────────────────────────────────

    private User createStudent() {
        return new Student("U1", "Alice", "alice@uni.edu", "Pass1!", "CS", "STU-001");
    }

    private User createFaculty() {
        return new Faculty("U2", "Bob", "bob@uni.edu", "Pass2!", "CS", "FAC-001");
    }

    private User createResearcher() {
        return new Researcher("U3", "Carol", "carol@uni.edu", "Pass3!", "PHYS", "RES-001");
    }

    private User createGuest() {
        return new Guest("U4", "Dave", "dave@ext.com", "Pass4!", "", "GUEST-001");
    }

    // ─── CREATE RESERVATION ──────────────────────────────────────

    @Test
    void testCreateReservationSuccess() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        eq.setStatus(EquipmentStatus.AVAILABLE);
        mockEqManager.eq = eq;

        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);

        Reservation res = manager.createReservation(user, eq, start, end);
        assertNotNull(res);
        assertEquals(ReservationStatus.CONFIRMED, res.getStatus());
        assertEquals(10.0, res.getDeposit()); // student deposit = $10
        assertEquals(1, mockRepo.savedRes.size());
    }

    @Test
    void testCreateReservationFacultyDeposit() {
        User user = createFaculty();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        eq.setStatus(EquipmentStatus.AVAILABLE);
        mockEqManager.eq = eq;

        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(3);

        Reservation res = manager.createReservation(user, eq, start, end);
        assertNotNull(res);
        assertEquals(15.0, res.getDeposit()); // faculty deposit = $15
    }

    @Test
    void testCreateReservationResearcherDeposit() {
        User user = createResearcher();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        eq.setStatus(EquipmentStatus.AVAILABLE);
        mockEqManager.eq = eq;

        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);

        Reservation res = manager.createReservation(user, eq, start, end);
        assertNotNull(res);
        assertEquals(20.0, res.getDeposit()); // researcher deposit = $20
    }

    @Test
    void testCreateReservationGuestDeposit() {
        User user = createGuest();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        eq.setStatus(EquipmentStatus.AVAILABLE);
        mockEqManager.eq = eq;

        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);

        Reservation res = manager.createReservation(user, eq, start, end);
        assertNotNull(res);
        assertEquals(30.0, res.getDeposit()); // guest deposit = $30
    }

    @Test
    void testCreateReservationEquipmentNotAvailable() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        eq.setStatus(EquipmentStatus.IN_USE);

        Reservation res = manager.createReservation(user, eq,
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1));
        assertNull(res);
    }

    @Test
    void testCreateReservationEquipmentInMaintenance() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        eq.setStatus(EquipmentStatus.MAINTENANCE);

        Reservation res = manager.createReservation(user, eq,
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1));
        assertNull(res);
    }

    @Test
    void testCreateReservationEquipmentDisabled() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        eq.setStatus(EquipmentStatus.DISABLED);

        Reservation res = manager.createReservation(user, eq,
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1));
        assertNull(res);
    }

    @Test
    void testCreateReservationOverloadDirect() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        Reservation res = new Reservation("R1", user, eq,
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), 10.0);

        manager.createReservation(res);
        assertEquals(1, mockRepo.savedRes.size());
    }

    // ─── CONFLICT DETECTION (Overlapping Reservations) ───────────

    @Test
    void testOverlappingReservationRejected() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        eq.setStatus(EquipmentStatus.AVAILABLE);
        mockEqManager.eq = eq;

        LocalDateTime start1 = LocalDateTime.now().plusDays(1);
        LocalDateTime end1 = start1.plusHours(2);

        Reservation res1 = manager.createReservation(user, eq, start1, end1);
        assertNotNull(res1);

        // second overlapping reservation fails
        LocalDateTime start2 = start1.plusHours(1);
        LocalDateTime end2 = start2.plusHours(2);
        Reservation res2 = manager.createReservation(user, eq, start2, end2);
        assertNull(res2);
    }

    @Test
    void testExactSameTimeSlotRejected() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        eq.setStatus(EquipmentStatus.AVAILABLE);
        mockEqManager.eq = eq;

        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);

        manager.createReservation(user, eq, start, end);

        Reservation res2 = manager.createReservation(user, eq, start, end);
        assertNull(res2);
    }

    @Test
    void testAdjacentReservationsAllowed() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        eq.setStatus(EquipmentStatus.AVAILABLE);
        mockEqManager.eq = eq;

        LocalDateTime start1 = LocalDateTime.now().plusDays(1);
        LocalDateTime end1 = start1.plusHours(2);

        manager.createReservation(user, eq, start1, end1);

        // adjacent — starts exactly when first ends — no overlap
        Reservation res2 = manager.createReservation(user, eq, end1, end1.plusHours(2));
        assertNotNull(res2);
    }

    @Test
    void testPartialOverlapAtStart() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        eq.setStatus(EquipmentStatus.AVAILABLE);
        mockEqManager.eq = eq;

        LocalDateTime start1 = LocalDateTime.now().plusDays(1);
        LocalDateTime end1 = start1.plusHours(3);
        manager.createReservation(user, eq, start1, end1);

        // new reservation starts before and ends inside existing
        Reservation res2 = manager.createReservation(user, eq, start1.minusHours(1), start1.plusHours(1));
        assertNull(res2);
    }

    @Test
    void testPartialOverlapAtEnd() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        eq.setStatus(EquipmentStatus.AVAILABLE);
        mockEqManager.eq = eq;

        LocalDateTime start1 = LocalDateTime.now().plusDays(1);
        LocalDateTime end1 = start1.plusHours(2);
        manager.createReservation(user, eq, start1, end1);

        // new reservation overlaps at the end
        Reservation res2 = manager.createReservation(user, eq, end1.minusHours(1), end1.plusHours(1));
        assertNull(res2);
    }

    @Test
    void testNewReservationFullyEnclosesExisting() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        eq.setStatus(EquipmentStatus.AVAILABLE);
        mockEqManager.eq = eq;

        LocalDateTime start1 = LocalDateTime.now().plusDays(1);
        LocalDateTime end1 = start1.plusHours(2);
        manager.createReservation(user, eq, start1, end1);

        Reservation res2 = manager.createReservation(user, eq, start1.minusHours(1), end1.plusHours(1));
        assertNull(res2);
    }

    @Test
    void testNonOverlappingDifferentEquipmentAllowed() {
        User user = createStudent();
        Equipment eq2 = new Equipment("E2", "Oscilloscope", "Lab-B");
        eq2.setStatus(EquipmentStatus.AVAILABLE);
        mockEqManager.eq = eq2;

        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);

        // manually add a row for E1
        mockRepo.allRows.add(new String[]{"R1", "U1", "E1", start.toString(), end.toString(),
                ReservationStatus.CONFIRMED.name(), "10.0"});

        // same time slot but different equipment — should succeed
        Reservation res2 = manager.createReservation(user, eq2, start, end);
        assertNotNull(res2);
    }

    @Test
    void testCancelledReservationDoesNotBlockNewBooking() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        eq.setStatus(EquipmentStatus.AVAILABLE);
        mockEqManager.eq = eq;

        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);

        Reservation res1 = manager.createReservation(user, eq, start, end);
        assertNotNull(res1);

        manager.cancelReservation(res1.getReservationId());

        // same slot now re-bookable
        Reservation res2 = manager.createReservation(user, eq, start, end);
        assertNotNull(res2);
    }

    // ─── CANCEL RESERVATION (Req8) ──────────────────────────────

    @Test
    void testCancelReservationBeforeStart() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
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
    void testCancelReservationAlreadyStartedFails() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        mockEqManager.eq = eq;

        LocalDateTime start = LocalDateTime.now().minusHours(1);
        LocalDateTime end = start.plusHours(3);
        Reservation res = new Reservation("R1", user, eq, start, end, 10.0);
        res.setStatus(ReservationStatus.CONFIRMED);
        mockRepo.saveReservation(res);

        manager.cancelReservation("R1");

        Reservation fetched = manager.getReservationById("R1");
        assertEquals(ReservationStatus.CONFIRMED, fetched.getStatus());
    }

    @Test
    void testCancelNonExistentReservation() {
        // should not throw
        manager.cancelReservation("NON-EXISTENT");
    }

    // ─── MODIFY RESERVATION (Req8) ──────────────────────────────

    @Test
    void testModifyReservationBeforeStart() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
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
    void testModifyReservationAfterStartFails() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        mockEqManager.eq = eq;

        LocalDateTime start = LocalDateTime.now().minusHours(1);
        LocalDateTime end = start.plusHours(3);
        Reservation res = new Reservation("R1", user, eq, start, end, 10.0);
        res.setStatus(ReservationStatus.CONFIRMED);
        mockRepo.saveReservation(res);

        LocalDateTime newEnd = end.plusHours(1);
        manager.modifyReservation("R1", newEnd);

        Reservation fetched = manager.getReservationById("R1");
        assertEquals(end, fetched.getEndTime());
    }

    @Test
    void testModifyNonExistentReservation() {
        // should not throw
        manager.modifyReservation("NON-EXISTENT", LocalDateTime.now().plusHours(5));
    }

    // ─── EXTEND RESERVATION (Req9) ──────────────────────────────

    @Test
    void testExtendReservationSuccess() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        mockEqManager.eq = eq;

        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = new Reservation("R1", user, eq, start, end, 10.0);
        res.setStatus(ReservationStatus.CONFIRMED);
        mockRepo.saveReservation(res);

        LocalDateTime newEnd = end.plusHours(2);
        manager.extendReservation("R1", newEnd);

        Reservation fetched = manager.getReservationById("R1");
        assertEquals(newEnd, fetched.getEndTime());
        assertEquals(ReservationStatus.EXTENDED, fetched.getStatus());
    }

    @Test
    void testExtendReservationBlockedByNextBooking() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        eq.setStatus(EquipmentStatus.AVAILABLE);
        mockEqManager.eq = eq;

        LocalDateTime start1 = LocalDateTime.now().plusDays(1);
        LocalDateTime end1 = start1.plusHours(2);
        Reservation res1 = new Reservation("R1", user, eq, start1, end1, 10.0);
        res1.setStatus(ReservationStatus.CONFIRMED);
        mockRepo.saveReservation(res1);

        // another reservation right after
        Reservation res2 = new Reservation("R2", user, eq, end1, end1.plusHours(2), 10.0);
        res2.setStatus(ReservationStatus.CONFIRMED);
        mockRepo.saveReservation(res2);

        // try to extend R1 into R2's slot
        manager.extendReservation("R1", end1.plusHours(1));

        Reservation fetched = manager.getReservationById("R1");
        assertEquals(end1, fetched.getEndTime());
        assertEquals(ReservationStatus.CONFIRMED, fetched.getStatus());
    }

    @Test
    void testExtendNonExistentReservation() {
        // should not throw
        manager.extendReservation("NON-EXISTENT", LocalDateTime.now().plusHours(5));
    }

    // ─── UPDATE RESERVATION ─────────────────────────────────────

    @Test
    void testUpdateReservation() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        mockEqManager.eq = eq;

        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = new Reservation("R1", user, eq, start, end, 10.0);
        res.setStatus(ReservationStatus.CONFIRMED);
        mockRepo.saveReservation(res);

        res.setStatus(ReservationStatus.COMPLETED);
        manager.updateReservation(res);

        Reservation fetched = manager.getReservationById("R1");
        assertEquals(ReservationStatus.COMPLETED, fetched.getStatus());
    }

    // ─── DEPOSIT PROCESSING (Req4) ──────────────────────────────

    @Test
    void testProcessDepositStudent() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        Reservation res = new Reservation("R1", user, eq,
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), 10.0);

        Payment p = manager.processDeposit(res, "CREDIT", "12345678");
        assertNotNull(p);
        assertTrue(p.isDeposit());
        assertEquals(10.0, p.getAmount());
        assertEquals(1, mockRepo.savedPayments.size());
    }

    @Test
    void testProcessDepositFaculty() {
        User user = createFaculty();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        Reservation res = new Reservation("R1", user, eq,
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), 15.0);

        Payment p = manager.processDeposit(res, "DEBIT", "87654321");
        assertEquals(15.0, p.getAmount());
    }

    @Test
    void testProcessDepositResearcher() {
        User user = createResearcher();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        Reservation res = new Reservation("R1", user, eq,
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), 20.0);

        Payment p = manager.processDeposit(res, "GRANT", "GRT-001");
        assertEquals(20.0, p.getAmount());
    }

    @Test
    void testProcessDepositGuest() {
        User user = createGuest();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        Reservation res = new Reservation("R1", user, eq,
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), 30.0);

        Payment p = manager.processDeposit(res, "CREDIT", "12345678");
        assertEquals(30.0, p.getAmount());
    }

    // ─── FINAL PAYMENT (Req4 – on-time vs late) ─────────────────

    @Test
    void testFinalPaymentOnTimeDeductDeposit() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        // 2 hours * $10/hr = $20 total, deposit = $10
        Reservation res = new Reservation("R1", user, eq, start, start.plusHours(2), 10.0);

        Payment p = manager.processFinalPayment(res, "CREDIT", "12345678", true);
        assertNotNull(p);
        assertFalse(p.isDeposit());
        // $20 - $10 deposit = $10
        assertEquals(10.0, p.getAmount());
    }

    @Test
    void testFinalPaymentLateDepositForfeited() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        // 2 hours * $10/hr = $20, deposit forfeited
        Reservation res = new Reservation("R1", user, eq, start, start.plusHours(2), 10.0);

        Payment p = manager.processFinalPayment(res, "CREDIT", "12345678", false);
        assertNotNull(p);
        assertEquals(20.0, p.getAmount());
    }

    @Test
    void testFinalPaymentFacultyOnTime() {
        User user = createFaculty();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        // 3 hours * $15/hr = $45, deposit = $15
        Reservation res = new Reservation("R1", user, eq, start, start.plusHours(3), 15.0);

        Payment p = manager.processFinalPayment(res, "INSTITUTIONAL", "INST-001", true);
        // $45 - $15 = $30
        assertEquals(30.0, p.getAmount());
    }

    @Test
    void testFinalPaymentResearcherLate() {
        User user = createResearcher();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        // 2 hours * $20/hr = $40
        Reservation res = new Reservation("R1", user, eq, start, start.plusHours(2), 20.0);

        Payment p = manager.processFinalPayment(res, "GRANT", "GRT-001", false);
        assertEquals(40.0, p.getAmount());
    }

    @Test
    void testFinalPaymentGuestOnTime() {
        User user = createGuest();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        // 2 hours * $30/hr = $60, deposit = $30
        Reservation res = new Reservation("R1", user, eq, start, start.plusHours(2), 30.0);

        Payment p = manager.processFinalPayment(res, "CREDIT", "12345678", true);
        // $60 - $30 = $30
        assertEquals(30.0, p.getAmount());
    }

    // ─── AVAILABILITY CHECK ──────────────────────────────────────

    @Test
    void testEquipmentAvailableWhenNoBookings() {
        assertTrue(manager.isEquipmentAvailable("E1",
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2)));
    }

    @Test
    void testEquipmentNotAvailableOverlap() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        eq.setStatus(EquipmentStatus.AVAILABLE);
        mockEqManager.eq = eq;

        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = new Reservation("R1", user, eq, start, end, 10.0);
        res.setStatus(ReservationStatus.CONFIRMED);
        mockRepo.saveReservation(res);

        assertFalse(manager.isEquipmentAvailable("E1", start.plusHours(1), start.plusHours(3)));
    }

    @Test
    void testEquipmentAvailableAfterCancellation() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        eq.setStatus(EquipmentStatus.AVAILABLE);
        mockEqManager.eq = eq;

        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);

        Reservation res = manager.createReservation(user, eq, start, end);
        manager.cancelReservation(res.getReservationId());

        assertTrue(manager.isEquipmentAvailable("E1", start, end));
    }

    @Test
    void testEquipmentAvailableForDifferentId() {
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);

        mockRepo.allRows.add(new String[]{"R1", "U1", "E1", start.toString(), end.toString(),
                ReservationStatus.CONFIRMED.name(), "10.0"});

        assertTrue(manager.isEquipmentAvailable("E2", start, end));
    }

    // ─── SENSOR OBSERVER (Req5) ─────────────────────────────────

    @Test
    void testUpdateMaintenanceCancelsConfirmedReservations() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        mockEqManager.eq = eq;

        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = new Reservation("R1", user, eq, start, end, 10.0);
        res.setStatus(ReservationStatus.CONFIRMED);
        mockRepo.saveReservation(res);

        manager.update("E1", "MAINTENANCE");

        Reservation fetched = manager.getReservationById("R1");
        assertEquals(ReservationStatus.CANCELLED, fetched.getStatus());
    }

    @Test
    void testUpdateDisabledCancelsConfirmedReservations() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        mockEqManager.eq = eq;

        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = new Reservation("R1", user, eq, start, end, 10.0);
        res.setStatus(ReservationStatus.CONFIRMED);
        mockRepo.saveReservation(res);

        manager.update("E1", "DISABLED");

        Reservation fetched = manager.getReservationById("R1");
        assertEquals(ReservationStatus.CANCELLED, fetched.getStatus());
    }

    @Test
    void testUpdateDoesNotAffectOtherEquipment() {
        User user = createStudent();
        Equipment eq = new Equipment("E2", "Oscilloscope", "Lab-B");
        mockEqManager.eq = eq;

        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = new Reservation("R1", user, eq, start, end, 10.0);
        res.setStatus(ReservationStatus.CONFIRMED);
        mockRepo.saveReservation(res);

        manager.update("E1", "MAINTENANCE");

        Reservation fetched = manager.getReservationById("R1");
        assertEquals(ReservationStatus.CONFIRMED, fetched.getStatus());
    }

    @Test
    void testUpdateDoesNotCancelAlreadyCancelledReservation() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        mockEqManager.eq = eq;

        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = new Reservation("R1", user, eq, start, end, 10.0);
        res.setStatus(ReservationStatus.CANCELLED);
        mockRepo.saveReservation(res);

        manager.update("E1", "MAINTENANCE");

        Reservation fetched = manager.getReservationById("R1");
        assertEquals(ReservationStatus.CANCELLED, fetched.getStatus());
    }

    // ─── GET METHODS ─────────────────────────────────────────────

    @Test
    void testGetReservationByIdFound() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        mockEqManager.eq = eq;

        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = new Reservation("R1", user, eq, start, end, 10.0);
        res.setStatus(ReservationStatus.CONFIRMED);
        mockRepo.saveReservation(res);

        Reservation fetched = manager.getReservationById("R1");
        assertNotNull(fetched);
        assertEquals("R1", fetched.getReservationId());
    }

    @Test
    void testGetReservationByIdNotFound() {
        assertNull(manager.getReservationById("NON-EXISTENT"));
    }

    @Test
    void testGetAllReservationRows() {
        User user = createStudent();
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");

        Reservation res1 = new Reservation("R1", user, eq,
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), 10.0);
        res1.setStatus(ReservationStatus.CONFIRMED);
        mockRepo.saveReservation(res1);

        Reservation res2 = new Reservation("R2", user, eq,
                LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(2), 10.0);
        res2.setStatus(ReservationStatus.CONFIRMED);
        mockRepo.saveReservation(res2);

        List<String[]> rows = manager.getAllReservationRows();
        assertEquals(2, rows.size());
    }
}

