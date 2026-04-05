package org.example.manual.gui;

import org.example.auth.AuthService;
import org.example.data.CSVRepository;
import org.example.equipment.Equipment;
import org.example.equipment.EquipmentManager;
import org.example.equipment.EquipmentStatus;
import org.example.payment.Payment;
import org.example.reservation.Reservation;
import org.example.reservation.ReservationManager;
import org.example.reservation.ReservationStatus;
import org.example.users.User;
import org.example.users.UserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the logic behind MyReservationsController.
 * Covers reservation creation, cancellation, extension, arrival-time check,
 * and final payment processing — all via service classes without JavaFX.
 */
public class MyReservationsControllerTest {

    private static final String USERS_FILE = "src/main/resources/data/users.csv";
    private static final String EQUIPMENT_FILE = "src/main/resources/data/equipment.csv";
    private static final String RESERVATIONS_FILE = "src/main/resources/data/reservations.csv";
    private static final String PAYMENTS_FILE = "src/main/resources/data/payments.csv";

    private byte[] usersBackup;
    private byte[] equipmentBackup;
    private byte[] reservationsBackup;
    private byte[] paymentsBackup;

    private ReservationManager reservationManager;
    private EquipmentManager equipmentManager;
    private CSVRepository repository;
    private User testUser;
    private Equipment testEquipment;

    @BeforeEach
    public void setUp() throws IOException {
        usersBackup = Files.readAllBytes(Paths.get(USERS_FILE));
        equipmentBackup = Files.readAllBytes(Paths.get(EQUIPMENT_FILE));
        reservationsBackup = Files.readAllBytes(Paths.get(RESERVATIONS_FILE));
        paymentsBackup = Files.readAllBytes(Paths.get(PAYMENTS_FILE));

        repository = new CSVRepository();
        reservationManager = new ReservationManager();
        equipmentManager = new EquipmentManager();

        testUser = UserFactory.createUser("GUEST", "MR-GUE-001", "TestUser", "mr@test.com", "Pass123!", null, "ID1");
        repository.saveUser(testUser);

        testEquipment = new Equipment("MR-EQ-001", "Microscope", "Lab A");
        equipmentManager.addEquipment(testEquipment);
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.write(Paths.get(USERS_FILE), usersBackup);
        Files.write(Paths.get(EQUIPMENT_FILE), equipmentBackup);
        Files.write(Paths.get(RESERVATIONS_FILE), reservationsBackup);
        Files.write(Paths.get(PAYMENTS_FILE), paymentsBackup);
        AuthService.logout();
    }

    @Test
    public void testCreateReservationReturnsConfirmedReservation() {
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = reservationManager.createReservation(testUser, testEquipment, start, end);
        assertNotNull(res);
        assertEquals(ReservationStatus.CONFIRMED, res.getStatus());
    }

    @Test
    public void testCancelReservationChangesStatusToCancelled() {
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = reservationManager.createReservation(testUser, testEquipment, start, end);
        assertNotNull(res);

        reservationManager.cancelReservation(res.getReservationId());
        Reservation updated = reservationManager.getReservationById(res.getReservationId());
        assertEquals(ReservationStatus.CANCELLED, updated.getStatus());
    }

    @Test
    public void testExtendReservationUpdatesEndTime() {
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = reservationManager.createReservation(testUser, testEquipment, start, end);
        assertNotNull(res);

        LocalDateTime newEnd = end.plusHours(2);
        reservationManager.extendReservation(res.getReservationId(), newEnd);
        Reservation updated = reservationManager.getReservationById(res.getReservationId());
        assertEquals(newEnd, updated.getEndTime());
    }

    @Test
    public void testArrivedOnTimeWithinWindowReturnsTrue() {
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = new Reservation("RES-TEST-001", testUser, testEquipment, start, end, 30.0);
        // Arrive exactly at start time
        assertTrue(res.arrivedOnTime(start));
    }

    @Test
    public void testArrivedOnTimeAt20MinutesReturnsTrue() {
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = new Reservation("RES-TEST-002", testUser, testEquipment, start, end, 30.0);
        // Arrive at exactly 20 min mark
        assertTrue(res.arrivedOnTime(start.plusMinutes(20)));
    }

    @Test
    public void testArrivedOnTimeAfter20MinutesReturnsFalse() {
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = new Reservation("RES-TEST-003", testUser, testEquipment, start, end, 30.0);
        // Arrive 21 minutes late
        assertFalse(res.arrivedOnTime(start.plusMinutes(21)));
    }

    @Test
    public void testReservationDepositMatchesPricingStrategy() {
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = reservationManager.createReservation(testUser, testEquipment, start, end);
        assertNotNull(res);
        // Guest deposit = hourly rate = $30
        assertEquals(30.0, res.getDeposit(), 0.01);
    }

    @Test
    public void testReservationDurationHoursCalculation() {
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(3);
        Reservation res = new Reservation("RES-TEST-004", testUser, testEquipment, start, end, 30.0);
        assertEquals(3, res.getDurationHours());
    }

    @Test
    public void testProcessFinalPaymentOnTimeDeductsDeposit() {
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = reservationManager.createReservation(testUser, testEquipment, start, end);
        assertNotNull(res);

        Payment payment = reservationManager.processFinalPayment(res, "CREDIT", "12345678", true);
        assertNotNull(payment);
        // Total = 30*2 = 60, minus deposit 30 = 30
        assertEquals(30.0, payment.getAmount(), 0.01);
    }

    @Test
    public void testProcessFinalPaymentLateKeepsFullAmount() {
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = reservationManager.createReservation(testUser, testEquipment, start, end);
        assertNotNull(res);

        Payment payment = reservationManager.processFinalPayment(res, "CREDIT", "12345678", false);
        assertNotNull(payment);
        // Total = 30*2 = 60, no deposit deduction
        assertEquals(60.0, payment.getAmount(), 0.01);
    }
}



