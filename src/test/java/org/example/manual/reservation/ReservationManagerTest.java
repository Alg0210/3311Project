package org.example.manual.reservation;

import org.example.data.CSVRepository;
import org.example.equipment.Equipment;
import org.example.equipment.EquipmentStatus;
import org.example.payment.Payment;
import org.example.reservation.*;
import org.example.users.User;
import org.example.users.UserFactory;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationManagerTest {

    private static final String USERS_FILE = "src/main/resources/data/users.csv";
    private static final String EQUIPMENT_FILE = "src/main/resources/data/equipment.csv";
    private static final String RESERVATIONS_FILE = "src/main/resources/data/reservations.csv";
    private static final String PAYMENTS_FILE = "src/main/resources/data/payments.csv";

    private byte[] usersBackup;
    private byte[] equipmentBackup;
    private byte[] reservationsBackup;
    private byte[] paymentsBackup;

    private ReservationManager reservationManager;
    private CSVRepository repository;
    private User testUser;
    private Equipment testEquipment;

    @BeforeEach
    void setUp() throws IOException {
        usersBackup = Files.readAllBytes(Paths.get(USERS_FILE));
        equipmentBackup = Files.readAllBytes(Paths.get(EQUIPMENT_FILE));
        reservationsBackup = Files.readAllBytes(Paths.get(RESERVATIONS_FILE));
        paymentsBackup = Files.readAllBytes(Paths.get(PAYMENTS_FILE));

        repository = new CSVRepository();
        reservationManager = new ReservationManager();

        // save a test user (GUEST — no decorator needed)
        testUser = UserFactory.createUser("GUEST", "AI-USR-001", "TestUser",
                "aitest@test.com", "Test123!", null, "ID-AI-001");
        repository.saveUser(testUser);

        // save test equipment
        testEquipment = new Equipment("AI-EQ-001", "Test Microscope", "Lab X");
        repository.saveEquipment(testEquipment);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.write(Paths.get(USERS_FILE), usersBackup);
        Files.write(Paths.get(EQUIPMENT_FILE), equipmentBackup);
        Files.write(Paths.get(RESERVATIONS_FILE), reservationsBackup);
        Files.write(Paths.get(PAYMENTS_FILE), paymentsBackup);
    }

    // ─── CREATE RESERVATION ──────────────────────────────────────

    @Test
    void testCreateReservationSuccess() {
        Reservation res = reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2027, 6, 1, 9, 0),
                LocalDateTime.of(2027, 6, 1, 11, 0));
        assertNotNull(res);
        assertEquals(ReservationStatus.CONFIRMED, res.getStatus());
    }

    @Test
    void testCreateReservationGuestDeposit() {
        Reservation res = reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2027, 6, 2, 9, 0),
                LocalDateTime.of(2027, 6, 2, 11, 0));
        assertNotNull(res);
        assertEquals(30.0, res.getDeposit());
    }

    @Test
    void testCreateReservationStudentDeposit() {
        User student = UserFactory.createUser("STUDENT", "AI-STU-001", "StudentUser",
                "student@test.com", "Test123!", "CS", "STU-001");
        repository.saveUser(student);

        Reservation res = reservationManager.createReservation(student, testEquipment,
                LocalDateTime.of(2027, 6, 3, 9, 0),
                LocalDateTime.of(2027, 6, 3, 11, 0));
        assertNotNull(res);
        assertEquals(10.0, res.getDeposit());
    }

    @Test
    void testCreateReservationUnavailableEquipment() {
        testEquipment.setStatus(EquipmentStatus.MAINTENANCE);
        Reservation res = reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2027, 6, 4, 9, 0),
                LocalDateTime.of(2027, 6, 4, 11, 0));
        assertNull(res);
    }

    @Test
    void testCreateReservationDisabledEquipment() {
        testEquipment.setStatus(EquipmentStatus.DISABLED);
        Reservation res = reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2027, 6, 5, 9, 0),
                LocalDateTime.of(2027, 6, 5, 11, 0));
        assertNull(res);
    }

    @Test
    void testCreateReservationOverlappingSlot() {
        reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2027, 7, 1, 9, 0),
                LocalDateTime.of(2027, 7, 1, 11, 0));

        Reservation second = reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2027, 7, 1, 10, 0),
                LocalDateTime.of(2027, 7, 1, 12, 0));
        assertNull(second);
    }

    @Test
    void testCreateReservationDirectOverload() {
        Reservation res = new Reservation("AI-RES-DIRECT", testUser, testEquipment,
                LocalDateTime.of(2027, 7, 5, 9, 0),
                LocalDateTime.of(2027, 7, 5, 11, 0), 30.0);
        reservationManager.createReservation(res);

        boolean found = repository.getAllReservationRows().stream()
                .anyMatch(r -> "AI-RES-DIRECT".equals(r[0]));
        assertTrue(found);
    }

    // ─── CANCEL RESERVATION ─────────────────────────────────────

    @Test
    void testCancelReservationBeforeStart() {
        Reservation res = reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2027, 8, 1, 9, 0),
                LocalDateTime.of(2027, 8, 1, 11, 0));
        assertNotNull(res);

        reservationManager.cancelReservation(res.getReservationId());
        assertEquals("CANCELLED", findStatus(res.getReservationId()));
    }

    @Test
    void testCancelReservationAfterStartDoesNothing() {
        Reservation pastRes = new Reservation("AI-RES-PAST-01", testUser, testEquipment,
                LocalDateTime.of(2020, 1, 1, 9, 0),
                LocalDateTime.of(2020, 1, 1, 11, 0), 30.0);
        pastRes.setStatus(ReservationStatus.CONFIRMED);
        reservationManager.createReservation(pastRes);

        reservationManager.cancelReservation("AI-RES-PAST-01");
        assertEquals("CONFIRMED", findStatus("AI-RES-PAST-01"));
    }

    // ─── MODIFY RESERVATION ─────────────────────────────────────

    @Test
    void testModifyReservationBeforeStart() {
        Reservation res = reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2027, 9, 1, 9, 0),
                LocalDateTime.of(2027, 9, 1, 11, 0));
        assertNotNull(res);

        LocalDateTime newEnd = LocalDateTime.of(2027, 9, 1, 14, 0);
        reservationManager.modifyReservation(res.getReservationId(), newEnd);

        String[] row = findRow(res.getReservationId());
        assertNotNull(row);
        assertEquals(newEnd.toString(), row[4]);
    }

    @Test
    void testModifyReservationAfterStartDoesNothing() {
        Reservation pastRes = new Reservation("AI-RES-PAST-02", testUser, testEquipment,
                LocalDateTime.of(2020, 2, 1, 9, 0),
                LocalDateTime.of(2020, 2, 1, 11, 0), 30.0);
        pastRes.setStatus(ReservationStatus.CONFIRMED);
        reservationManager.createReservation(pastRes);

        reservationManager.modifyReservation("AI-RES-PAST-02",
                LocalDateTime.of(2020, 2, 1, 14, 0));

        String[] row = findRow("AI-RES-PAST-02");
        assertNotNull(row);
        assertEquals(LocalDateTime.of(2020, 2, 1, 11, 0).toString(), row[4]);
    }

    // ─── EXTEND RESERVATION ─────────────────────────────────────

    @Test
    void testExtendReservationWhenFree() {
        Reservation res = reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2027, 10, 1, 9, 0),
                LocalDateTime.of(2027, 10, 1, 11, 0));
        assertNotNull(res);

        reservationManager.extendReservation(res.getReservationId(),
                LocalDateTime.of(2027, 10, 1, 14, 0));
        assertEquals("EXTENDED", findStatus(res.getReservationId()));
    }

    @Test
    void testExtendReservationBlocked() {
        Reservation res1 = reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2027, 11, 1, 9, 0),
                LocalDateTime.of(2027, 11, 1, 11, 0));
        Reservation res2 = reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2027, 11, 1, 11, 0),
                LocalDateTime.of(2027, 11, 1, 13, 0));
        assertNotNull(res1);
        assertNotNull(res2);

        reservationManager.extendReservation(res1.getReservationId(),
                LocalDateTime.of(2027, 11, 1, 12, 0));
        assertEquals("CONFIRMED", findStatus(res1.getReservationId()));
    }

    @Test
    void testExtendNonExistentReservation() {
        assertDoesNotThrow(() ->
                reservationManager.extendReservation("NONEXISTENT",
                        LocalDateTime.of(2027, 12, 1, 14, 0)));
    }

    // ─── UPDATE RESERVATION ──────────────────────────────────────

    @Test
    void testUpdateReservation() {
        Reservation res = reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2027, 7, 20, 9, 0),
                LocalDateTime.of(2027, 7, 20, 11, 0));
        assertNotNull(res);

        res.setStatus(ReservationStatus.COMPLETED);
        reservationManager.updateReservation(res);
        assertEquals("COMPLETED", findStatus(res.getReservationId()));
    }

    // ─── AVAILABILITY CHECK ──────────────────────────────────────

    @Test
    void testIsEquipmentAvailableNoConflict() {
        assertTrue(reservationManager.isEquipmentAvailable("AI-EQ-001",
                LocalDateTime.of(2028, 1, 1, 9, 0),
                LocalDateTime.of(2028, 1, 1, 11, 0)));
    }

    @Test
    void testIsEquipmentAvailableWithOverlap() {
        reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2028, 2, 1, 9, 0),
                LocalDateTime.of(2028, 2, 1, 11, 0));

        assertFalse(reservationManager.isEquipmentAvailable("AI-EQ-001",
                LocalDateTime.of(2028, 2, 1, 10, 0),
                LocalDateTime.of(2028, 2, 1, 12, 0)));
    }

    @Test
    void testIsEquipmentAvailableCancelledIgnored() {
        Reservation res = reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2028, 3, 1, 9, 0),
                LocalDateTime.of(2028, 3, 1, 11, 0));
        assertNotNull(res);
        reservationManager.cancelReservation(res.getReservationId());

        assertTrue(reservationManager.isEquipmentAvailable("AI-EQ-001",
                LocalDateTime.of(2028, 3, 1, 9, 0),
                LocalDateTime.of(2028, 3, 1, 11, 0)));
    }

    @Test
    void testAdjacentReservationsDoNotOverlap() {
        reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2028, 4, 1, 9, 0),
                LocalDateTime.of(2028, 4, 1, 11, 0));

        assertTrue(reservationManager.isEquipmentAvailable("AI-EQ-001",
                LocalDateTime.of(2028, 4, 1, 11, 0),
                LocalDateTime.of(2028, 4, 1, 13, 0)));
    }

    // ─── PAYMENT HANDLING ────────────────────────────────────────

    @Test
    void testProcessDeposit() {
        Reservation res = reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2027, 6, 15, 9, 0),
                LocalDateTime.of(2027, 6, 15, 11, 0));
        assertNotNull(res);

        Payment payment = reservationManager.processDeposit(res, "CREDIT", "12345678");
        assertNotNull(payment);
        assertTrue(payment.isDeposit());
        assertEquals(30.0, payment.getAmount()); // GUEST deposit = $30
    }

    @Test
    void testProcessFinalPaymentOnTime() {
        Reservation res = reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2027, 6, 20, 9, 0),
                LocalDateTime.of(2027, 6, 20, 11, 0));
        assertNotNull(res);

        Payment payment = reservationManager.processFinalPayment(res, "CREDIT", "12345678", true);
        assertNotNull(payment);
        assertFalse(payment.isDeposit());
        // total = $30 * 2h = $60, minus $30 deposit = $30
        assertEquals(30.0, payment.getAmount());
    }

    @Test
    void testProcessFinalPaymentLate() {
        Reservation res = reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2027, 6, 25, 9, 0),
                LocalDateTime.of(2027, 6, 25, 11, 0));
        assertNotNull(res);

        Payment payment = reservationManager.processFinalPayment(res, "CREDIT", "12345678", false);
        assertNotNull(payment);
        // total = $30 * 2h = $60, deposit forfeited so full $60
        assertEquals(60.0, payment.getAmount());
    }

    // ─── OBSERVER (SensorObserver) ───────────────────────────────

    @Test
    void testObserverCancelsOnMaintenance() {
        Reservation res = reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2027, 12, 1, 9, 0),
                LocalDateTime.of(2027, 12, 1, 11, 0));
        assertNotNull(res);

        reservationManager.update("AI-EQ-001", "MAINTENANCE");
        assertEquals("CANCELLED", findStatus(res.getReservationId()));
    }

    @Test
    void testObserverCancelsOnDisabled() {
        Reservation res = reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2027, 12, 5, 9, 0),
                LocalDateTime.of(2027, 12, 5, 11, 0));
        assertNotNull(res);

        reservationManager.update("AI-EQ-001", "DISABLED");
        assertEquals("CANCELLED", findStatus(res.getReservationId()));
    }

    @Test
    void testObserverDoesNotCancelOnAvailable() {
        Reservation res = reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2027, 12, 10, 9, 0),
                LocalDateTime.of(2027, 12, 10, 11, 0));
        assertNotNull(res);

        reservationManager.update("AI-EQ-001", "AVAILABLE");
        assertEquals("CONFIRMED", findStatus(res.getReservationId()));
    }

    // ─── GETTERS ─────────────────────────────────────────────────

    @Test
    void testGetReservationById() {
        Reservation res = reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2027, 7, 1, 9, 0),
                LocalDateTime.of(2027, 7, 1, 11, 0));
        assertNotNull(res);

        Reservation found = reservationManager.getReservationById(res.getReservationId());
        assertNotNull(found);
        assertEquals(res.getReservationId(), found.getReservationId());
    }

    @Test
    void testGetReservationByIdReturnsNull() {
        assertNull(reservationManager.getReservationById("NONEXISTENT-ID"));
    }

    @Test
    void testGetAllReservationRows() {
        Reservation res = reservationManager.createReservation(testUser, testEquipment,
                LocalDateTime.of(2027, 7, 15, 9, 0),
                LocalDateTime.of(2027, 7, 15, 11, 0));
        assertNotNull(res);

        List<String[]> rows = reservationManager.getAllReservationRows();
        boolean found = rows.stream().anyMatch(r -> r[0].equals(res.getReservationId()));
        assertTrue(found);
    }

    // ─── HELPERS ─────────────────────────────────────────────────

    private String findStatus(String reservationId) {
        for (String[] row : repository.getAllReservationRows()) {
            if (row[0].equals(reservationId)) return row[5];
        }
        return null;
    }

    private String[] findRow(String reservationId) {
        for (String[] row : repository.getAllReservationRows()) {
            if (row[0].equals(reservationId)) return row;
        }
        return null;
    }
}
