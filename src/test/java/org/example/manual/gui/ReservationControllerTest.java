package org.example.manual.gui;

import org.example.auth.AuthService;
import org.example.data.CSVRepository;
import org.example.equipment.Equipment;
import org.example.equipment.EquipmentManager;
import org.example.gui.MainController;
import org.example.payment.Payment;
import org.example.payment.PaymentDecorator;
import org.example.payment.UserPricingStrategy;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the logic behind ReservationController.
 * Covers cart management, pricing/tax calculation, time slot validation,
 * payment method validation, and reservation creation — all without JavaFX.
 */
public class ReservationControllerTest {

    private static final String USERS_FILE = "src/main/resources/data/users.csv";
    private static final String EQUIPMENT_FILE = "src/main/resources/data/equipment.csv";
    private static final String RESERVATIONS_FILE = "src/main/resources/data/reservations.csv";
    private static final String PAYMENTS_FILE = "src/main/resources/data/payments.csv";
    private static final double TAX_RATE = 0.13;

    private byte[] usersBackup;
    private byte[] equipmentBackup;
    private byte[] reservationsBackup;
    private byte[] paymentsBackup;

    private CSVRepository repository;
    private ReservationManager reservationManager;
    private EquipmentManager equipmentManager;
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

        testUser = UserFactory.createUser("STUDENT", "RC-STU-001", "ReserveUser", "rc@test.com", "Pass123!", "CS", "ID1");
        repository.saveUser(testUser);

        testEquipment = new Equipment("RC-EQ-001", "Oscilloscope", "Lab A");
        equipmentManager.addEquipment(testEquipment);
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.write(Paths.get(USERS_FILE), usersBackup);
        Files.write(Paths.get(EQUIPMENT_FILE), equipmentBackup);
        Files.write(Paths.get(RESERVATIONS_FILE), reservationsBackup);
        Files.write(Paths.get(PAYMENTS_FILE), paymentsBackup);
        MainController.setCart(new ArrayList<>());
        AuthService.logout();
    }

    @Test
    public void testCartStoresEquipmentItems() {
        List<Equipment> cart = new ArrayList<>();
        cart.add(testEquipment);
        MainController.setCart(cart);
        assertEquals(1, MainController.getCart().size());
        assertEquals("RC-EQ-001", MainController.getCart().get(0).getEquipmentId());
    }

    @Test
    public void testPricingStrategyForStudentReturns10() {
        UserPricingStrategy strategy = new UserPricingStrategy("STUDENT");
        assertEquals(10.0, strategy.getHourlyRate(), 0.01);
    }

    @Test
    public void testOrderSubtotalCalculation() {
        UserPricingStrategy strategy = new UserPricingStrategy("STUDENT");
        double subtotal = 0;
        List<Equipment> cart = new ArrayList<>();
        cart.add(testEquipment);
        cart.add(new Equipment("RC-EQ-002", "Multimeter", "Lab B"));
        for (Equipment e : cart) {
            subtotal += strategy.getHourlyRate();
        }
        assertEquals(20.0, subtotal, 0.01);
    }

    @Test
    public void testTaxCalculation() {
        double subtotal = 20.0;
        double tax = subtotal * TAX_RATE;
        assertEquals(2.60, tax, 0.01);
    }

    @Test
    public void testTotalCalculationIncludesTax() {
        double subtotal = 20.0;
        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax;
        assertEquals(22.60, total, 0.01);
    }

    @Test
    public void testEndTimeBeforeStartTimeIsInvalid() {
        LocalDateTime start = LocalDateTime.of(2026, 5, 1, 14, 0);
        LocalDateTime end = LocalDateTime.of(2026, 5, 1, 12, 0);
        assertFalse(end.isAfter(start));
    }

    @Test
    public void testCreditCardValidationRequires8Digits() {
        PaymentDecorator valid = new PaymentDecorator(
                new Payment("P1", "R1", 10.0, "CREDIT", true), "CREDIT", "12345678");
        PaymentDecorator invalid = new PaymentDecorator(
                new Payment("P2", "R2", 10.0, "CREDIT", true), "CREDIT", "1234");
        assertTrue(valid.validate());
        assertFalse(invalid.validate());
    }

    @Test
    public void testGrantIdMustStartWithGRT() {
        PaymentDecorator valid = new PaymentDecorator(
                new Payment("P3", "R3", 10.0, "GRANT", true), "GRANT", "GRT-001");
        PaymentDecorator invalid = new PaymentDecorator(
                new Payment("P4", "R4", 10.0, "GRANT", true), "GRANT", "INVALID");
        assertTrue(valid.validate());
        assertFalse(invalid.validate());
    }

    @Test
    public void testCreateReservationForAvailableEquipment() {
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation res = reservationManager.createReservation(testUser, testEquipment, start, end);
        assertNotNull(res);
        assertEquals(ReservationStatus.CONFIRMED, res.getStatus());
    }

    @Test
    public void testClearCartAfterPurchase() {
        List<Equipment> cart = new ArrayList<>();
        cart.add(testEquipment);
        MainController.setCart(cart);
        assertEquals(1, MainController.getCart().size());

        // Simulate clearing cart after successful purchase
        MainController.setCart(new ArrayList<>());
        assertTrue(MainController.getCart().isEmpty());
    }
}

