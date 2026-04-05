package org.example.AI_assistant.data;

import org.example.data.CSVRepository;
import org.example.equipment.Equipment;
import org.example.equipment.EquipmentStatus;
import org.example.payment.Payment;
import org.example.reservation.Reservation;
import org.example.reservation.ReservationStatus;
import org.example.users.User;
import org.example.users.UserDecorator;
import org.example.users.UserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CSVRepositoryTest {

    private static final String USERS_FILE = "src/main/resources/data/users.csv";
    private static final String EQUIPMENT_FILE = "src/main/resources/data/equipment.csv";
    private static final String RESERVATIONS_FILE = "src/main/resources/data/reservations.csv";
    private static final String PAYMENTS_FILE = "src/main/resources/data/payments.csv";

    private byte[] usersBackup;
    private byte[] equipmentBackup;
    private byte[] reservationsBackup;
    private byte[] paymentsBackup;
    private CSVRepository repository;

    @BeforeEach
    public void setUp() throws IOException {
        usersBackup = Files.readAllBytes(Paths.get(USERS_FILE));
        equipmentBackup = Files.readAllBytes(Paths.get(EQUIPMENT_FILE));
        reservationsBackup = Files.readAllBytes(Paths.get(RESERVATIONS_FILE));
        paymentsBackup = Files.readAllBytes(Paths.get(PAYMENTS_FILE));
        repository = new CSVRepository();
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.write(Paths.get(USERS_FILE), usersBackup);
        Files.write(Paths.get(EQUIPMENT_FILE), equipmentBackup);
        Files.write(Paths.get(RESERVATIONS_FILE), reservationsBackup);
        Files.write(Paths.get(PAYMENTS_FILE), paymentsBackup);
    }

    @Test
    public void saveAndFindUserByEmailRoundTripsPlainUser() {
        User user = UserFactory.createUser("GUEST", "AI-GUE-001", "Alice", "ai-repo-alice@example.com", "Test123!",
                null,
                "ID-REPO-001");

        repository.saveUser(user);

        User found = repository.findUserByEmail("ai-repo-alice@example.com");
        assertNotNull(found);
        assertEquals("AI-GUE-001", found.getUserId());
        assertEquals("Alice", found.getName());
    }

    @Test
    public void updateUserPersistsDecoratorApprovalState() {
        User baseUser = UserFactory.createUser("STUDENT", "AI-STU-001", "Brie", "ai-repo-brie@example.com", "Test123!",
                "EECS", "ID-REPO-002");
        UserDecorator decoratedUser = new UserDecorator(baseUser, "APPROVAL");
        repository.saveUser(decoratedUser);

        decoratedUser.setApprovalStatus("APPROVED");
        decoratedUser.setName("Brie Updated");
        repository.updateUser(decoratedUser);

        User found = repository.findUserByEmail("ai-repo-brie@example.com");
        assertTrue(found instanceof UserDecorator);
        assertEquals("Brie Updated", found.getName());
        assertEquals("APPROVED", ((UserDecorator) found).getApprovalStatus());
    }

    @Test
    public void deleteUserRemovesStoredUser() {
        User user = UserFactory.createUser("GUEST", "AI-GUE-002", "Cory", "ai-repo-cory@example.com", "Test123!", null,
                "ID-REPO-003");
        repository.saveUser(user);

        repository.deleteUser("AI-GUE-002");

        assertNull(repository.findUserByEmail("ai-repo-cory@example.com"));
    }

    @Test
    public void saveAndUpdateEquipmentPersistAllFields() {
        Equipment equipment = new Equipment("AI-EQ-001", "Microscope", "Lab A");
        equipment.setName("High Precision Microscope");
        equipment.setStatus(EquipmentStatus.IN_USE);
        equipment.setAvailableUnits(4);
        equipment.setProductStatistics("uptime=99%\nchecks=20");
        equipment.setTags(Arrays.asList("optics", "biology"));
        equipment.setImagePath("/images/microscope.png");
        repository.saveEquipment(equipment);

        equipment.setStatus(EquipmentStatus.MAINTENANCE);
        equipment.setAvailableUnits(2);
        repository.updateEquipment(equipment);

        List<Equipment> allEquipment = repository.getAllEquipment();
        Equipment found = allEquipment.stream()
                .filter(item -> "AI-EQ-001".equals(item.getEquipmentId()))
                .findFirst()
                .orElse(null);

        assertNotNull(found);
        assertEquals("High Precision Microscope", found.getName());
        assertEquals(EquipmentStatus.MAINTENANCE, found.getStatus());
        assertEquals(2, found.getAvailableUnits());
        assertEquals("uptime=99%\nchecks=20", found.getProductStatistics());
        assertTrue(found.getTags().contains("optics"));
        assertEquals("/images/microscope.png", found.getImagePath());
    }

    @Test
    public void findEquipmentRowByIdReturnsNullForUnknownIdentifier() {
        assertNull(repository.findEquipmentRowById("AI-EQ-MISSING"));
    }

    @Test
    public void reservationQueriesReturnSavedAndFilteredRows() {
        Reservation reservationOne = buildReservation("AI-RES-001", "AI-USER-001", "AI-EQ-RES-001", 25.0);
        Reservation reservationTwo = buildReservation("AI-RES-002", "AI-USER-002", "AI-EQ-RES-002", 40.0);
        repository.saveReservation(reservationOne);
        repository.saveReservation(reservationTwo);

        List<String[]> allRows = repository.getAllReservationRows();
        List<String[]> filteredRows = repository.getReservationRowsByUserId("AI-USER-001");

        assertTrue(allRows.stream().anyMatch(row -> "AI-RES-001".equals(row[0])));
        assertTrue(allRows.stream().anyMatch(row -> "AI-RES-002".equals(row[0])));
        assertEquals(1, filteredRows.size());
        assertEquals("AI-RES-001", filteredRows.get(0)[0]);
    }

    @Test
    public void updateReservationPersistsStatusAndTimes() {
        Reservation reservation = buildReservation("AI-RES-003", "AI-USER-003", "AI-EQ-RES-003", 60.0);
        repository.saveReservation(reservation);

        reservation.setStatus(ReservationStatus.CONFIRMED);
        reservation.setStartTime(LocalDateTime.of(2026, 6, 2, 14, 0));
        reservation.setEndTime(LocalDateTime.of(2026, 6, 2, 16, 30));
        repository.updateReservation(reservation);

        String[] row = repository.getAllReservationRows().stream()
                .filter(item -> "AI-RES-003".equals(item[0]))
                .findFirst()
                .orElse(null);

        assertNotNull(row);
        assertEquals("CONFIRMED", row[5]);
        assertEquals("2026-06-02T14:00", row[3]);
        assertEquals("2026-06-02T16:30", row[4]);
    }

    @Test
    public void paymentQueriesReturnSavedAndFilteredRows() {
        Payment paymentOne = new Payment("AI-PAY-001", "AI-RES-PAY-001", 100.0, "CARD", true);
        Payment paymentTwo = new Payment("AI-PAY-002", "AI-RES-PAY-002", 55.5, "CASH", false);
        repository.savePayment(paymentOne);
        repository.savePayment(paymentTwo);

        List<String[]> allRows = repository.getAllPaymentRows();
        List<String[]> filteredRows = repository.getPaymentRowsByReservationId("AI-RES-PAY-001");

        assertTrue(allRows.stream().anyMatch(row -> "AI-PAY-001".equals(row[0])));
        assertTrue(allRows.stream().anyMatch(row -> "AI-PAY-002".equals(row[0])));
        assertEquals(1, filteredRows.size());
        assertEquals("AI-PAY-001", filteredRows.get(0)[0]);
        assertEquals("100.0", filteredRows.get(0)[2]);
        assertEquals("true", filteredRows.get(0)[4]);
    }

    private Reservation buildReservation(String reservationId, String userId, String equipmentId, double deposit) {
        User user = UserFactory.createUser("GUEST", userId, "Reservation User", userId.toLowerCase() + "@example.com",
                "Test123!", null, "ID-" + userId);
        Equipment equipment = new Equipment(equipmentId, "Reservation Equipment", "Lab R");
        return new Reservation(
                reservationId,
                user,
                equipment,
                LocalDateTime.of(2026, 6, 1, 9, 0),
                LocalDateTime.of(2026, 6, 1, 12, 0),
                deposit);
    }
}