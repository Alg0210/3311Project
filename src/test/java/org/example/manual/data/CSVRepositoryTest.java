package org.example.manual.data;

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

import static org.junit.jupiter.api.Assertions.*;

public class CSVRepositoryTest {

    private static final String userFile = "src/main/resources/data/users.csv";
    private static final String equipmentFile = "src/main/resources/data/equipment.csv";
    private static final String reservationsFile = "src/main/resources/data/reservations.csv";
    private static final String paymentsFile = "src/main/resources/data/payments.csv";

    private byte[] usersBackup;
    private byte[] equipmentBackup;
    private byte[] reservationsBackup;
    private byte[] paymentsBackup;
    private CSVRepository repository;

    @BeforeEach
    public void setUp() throws IOException {
        usersBackup = Files.readAllBytes(Paths.get(userFile));
        equipmentBackup = Files.readAllBytes(Paths.get(equipmentFile));
        reservationsBackup = Files.readAllBytes(Paths.get(reservationsFile));
        paymentsBackup = Files.readAllBytes(Paths.get(paymentsFile));
        repository = new CSVRepository();
    }

    @AfterEach
    public void restart() throws IOException {
        Files.write(Paths.get(userFile), usersBackup);
        Files.write(Paths.get(equipmentFile), equipmentBackup);
        Files.write(Paths.get(reservationsFile), reservationsBackup);
        Files.write(Paths.get(paymentsFile), paymentsBackup);
    }

    // user tests

    @Test
    public void testSaveAndFindUserByEmail() {
        User user = UserFactory.createUser("GUEST", "TEST-GUE-001", "Alice", "alice@csvrepotest.com", "Test123!", null,
                "ID001");
        repository.saveUser(user);

        User found = repository.findUserByEmail("alice@csvrepotest.com");

        assertNotNull(found);
        assertEquals("Alice", found.getName());
        assertEquals("alice@csvrepotest.com", found.getEmail());
    }

    @Test
    public void testFindUserByEmailReturnsNullForUnknownEmail() {
        User result = repository.findUserByEmail("nobody@csvrepotest.com");
        assertNull(result);
    }

    @Test
    public void testFindUserByIdReturnsNull() {
        User result = repository.findUserById("NONEXISTENT-ID-XYZ");
        assertNull(result);
    }

    @Test
    public void testFindUserByIdReturnsCorrectUser() {
        User user = UserFactory.createUser("GUEST", "TEST-GUE-002", "Bob", "bob@csvrepotest.com", "Test123!", null,
                "ID002");
        repository.saveUser(user);

        User found = repository.findUserById("TEST-GUE-002");

        assertNotNull(found);
        assertEquals("bob@csvrepotest.com", found.getEmail());
    }

    @Test
    public void testGetAllUsersIncludesSavedUser() {
        User user = UserFactory.createUser("GUEST", "TEST-GUE-003", "Carol", "carol@csvrepotest.com", "Test123!", null,
                "ID003");
        repository.saveUser(user);

        List<User> allUsers = repository.getAllUsers();

        assertNotNull(allUsers);
        boolean found = false;
        for (User u : allUsers) {
            if ("carol@csvrepotest.com".equals(u.getEmail())) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testDeleteUserRemovesItFromStorage() {
        User user = UserFactory.createUser("GUEST", "TEST-GUE-004", "Dan", "dan@csvrepotest.com", "Test123!", null,
                "ID004");
        repository.saveUser(user);
        assertNotNull(repository.findUserByEmail("dan@csvrepotest.com"));

        repository.deleteUser("TEST-GUE-004");

        assertNull(repository.findUserByEmail("dan@csvrepotest.com"));
    }

    @Test
    public void testUpdateUserPersistsNameChange() {
        User user = UserFactory.createUser("GUEST", "TEST-GUE-005", "Eve", "eve@csvrepotest.com", "Test123!", null,
                "ID005");
        repository.saveUser(user);

        user.setName("Eve Updated");
        repository.updateUser(user);

        User updated = repository.findUserByEmail("eve@csvrepotest.com");
        assertNotNull(updated);
        assertEquals("Eve Updated", updated.getName());
    }

    @Test
    public void testSaveUserWithDecoratorPreservesDecoratorType() {
        User base = UserFactory.createUser("STUDENT", "TEST-STU-001", "Frank", "frank@csvrepotest.com", "Test123!",
                "CS", "ID006");
        User decorated = new UserDecorator(base, "APPROVAL");
        repository.saveUser(decorated);

        User found = repository.findUserByEmail("frank@csvrepotest.com");

        assertNotNull(found);
        assertTrue(found instanceof UserDecorator);
    }

    // equipment tests

    @Test
    public void testSaveAndFindEquipmentInGetAllEquipment() {
        Equipment equipment = new Equipment("TEST-EQ-001", "Microscope", "Lab A");
        repository.saveEquipment(equipment);

        List<Equipment> all = repository.getAllEquipment();
        boolean found = false;
        for (Equipment e : all) {
            if ("TEST-EQ-001".equals(e.getEquipmentId())) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testFindEquipmentRowByIdReturnsNullForUnknownId() {
        String[] row = repository.findEquipmentRowById("NONEXISTENT-EQ-XYZ");
        assertNull(row);
    }

    @Test
    public void testFindEquipmentRowByIdReturnsCorrectRow() {
        Equipment equipment = new Equipment("TEST-EQ-002", "Centrifuge", "Lab B");
        repository.saveEquipment(equipment);

        String[] row = repository.findEquipmentRowById("TEST-EQ-002");

        assertNotNull(row);
        assertEquals("TEST-EQ-002", row[0]);
        assertEquals("Lab B", row[2]);
    }

    @Test
    public void testUpdateEquipmentPersistsChanges() {
        Equipment equipment = new Equipment("TEST-EQ-003", "Oscilloscope", "Lab C");
        repository.saveEquipment(equipment);

        equipment.setName("Digital Oscilloscope");
        equipment.setStatus(EquipmentStatus.MAINTENANCE);
        equipment.setAvailableUnits(3);
        repository.updateEquipment(equipment);

        String[] row = repository.findEquipmentRowById("TEST-EQ-003");
        assertNotNull(row);
        assertEquals("Digital Oscilloscope", row[4]);
        assertEquals("MAINTENANCE", row[3]);
        assertEquals("3", row[5]);
    }

    @Test
    public void testGetAllEquipmentWithRichData() {
        Equipment equipment = new Equipment("TEST-EQ-004", "Spectrometer", "Lab D");
        equipment.setName("Mass Spectrometer");
        equipment.setAvailableUnits(2);
        equipment.setProductStatistics("efficiency=95%");
        equipment.setTags(Arrays.asList("chemistry", "analysis"));
        equipment.setImagePath("/images/spectrometer.png");
        repository.saveEquipment(equipment);

        List<Equipment> all = repository.getAllEquipment();
        Equipment found = null;
        for (Equipment e : all) {
            if ("TEST-EQ-004".equals(e.getEquipmentId())) {
                found = e;
                break;
            }
        }

        assertNotNull(found);
        assertEquals("Mass Spectrometer", found.getName());
        assertEquals(2, found.getAvailableUnits());
        assertEquals("efficiency=95%", found.getProductStatistics());
        assertTrue(found.getTags().contains("chemistry"));
        assertTrue(found.getTags().contains("analysis"));
        assertEquals("/images/spectrometer.png", found.getImagePath());
    }

    @Test
    public void testUpdateUserWithDecoratorPersistsApprovalStatus() {
        User base = UserFactory.createUser("STUDENT", "TEST-STU-002", "Grace", "grace@csvrepotest.com", "Test123!",
                "Bio", "ID007");
        UserDecorator decorated = new UserDecorator(base, "APPROVAL");
        repository.saveUser(decorated);

        decorated.setApprovalStatus("APPROVED");
        repository.updateUser(decorated);

        User found = repository.findUserByEmail("grace@csvrepotest.com");
        assertNotNull(found);
        assertTrue(found instanceof UserDecorator);
        assertEquals("APPROVED", ((UserDecorator) found).getApprovalStatus());
    }

    @Test
    public void testDeleteUserForNonExistentIdDoesNotCorruptData() {
        User user = UserFactory.createUser("GUEST", "TEST-GUE-006", "Harry", "harry@csvrepotest.com", "Test123!", null,
                "ID008");
        repository.saveUser(user);
        int sizeBefore = repository.getAllUsers().size();

        repository.deleteUser("BOGUS-ID-99999");

        assertEquals(sizeBefore, repository.getAllUsers().size());
        assertNotNull(repository.findUserByEmail("harry@csvrepotest.com"));
    }

    // reservation tests

    private Reservation buildReservation(String reservationId, String userId, String equipmentId) {
        User user = UserFactory.createUser("GUEST", userId, "TestUser", userId + "@csvrepotest.com", "Test123!", null,
                "ID-" + userId);
        Equipment equipment = new Equipment(equipmentId, "Test Equipment", "Lab Z");
        LocalDateTime start = LocalDateTime.of(2026, 5, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2026, 5, 1, 11, 0);
        return new Reservation(reservationId, user, equipment, start, end, 50.0);
    }

    @Test
    public void testSaveReservationAppearsInAllReservationRows() {
        Reservation reservation = buildReservation("TEST-RES-001", "TEST-USR-001", "TEST-EQ-RES-001");
        repository.saveReservation(reservation);

        List<String[]> rows = repository.getAllReservationRows();
        boolean found = rows.stream().anyMatch(r -> "TEST-RES-001".equals(r[0]));
        assertTrue(found);
    }

    @Test
    public void testGetAllReservationRowsContainsSavedReservation() {
        Reservation reservation = buildReservation("TEST-RES-002", "TEST-USR-002", "TEST-EQ-RES-002");
        repository.saveReservation(reservation);

        List<String[]> rows = repository.getAllReservationRows();
        String[] match = rows.stream().filter(r -> "TEST-RES-002".equals(r[0])).findFirst().orElse(null);

        assertNotNull(match);
        assertEquals("TEST-USR-002", match[1]);
        assertEquals("TEST-EQ-RES-002", match[2]);
        assertEquals("PENDING", match[5]);
        assertEquals("50.0", match[6]);
    }

    @Test
    public void testGetReservationRowsByUserIdReturnsOnlyMatchingRows() {
        Reservation resA = buildReservation("TEST-RES-003", "TEST-USR-003", "TEST-EQ-RES-003");
        Reservation resB = buildReservation("TEST-RES-004", "TEST-USR-004", "TEST-EQ-RES-004");
        repository.saveReservation(resA);
        repository.saveReservation(resB);

        List<String[]> rows = repository.getReservationRowsByUserId("TEST-USR-003");

        assertTrue(rows.stream().anyMatch(r -> "TEST-RES-003".equals(r[0])));
        assertFalse(rows.stream().anyMatch(r -> "TEST-RES-004".equals(r[0])));
    }

    @Test
    public void testUpdateReservationPersistsStatusChange() {
        Reservation reservation = buildReservation("TEST-RES-005", "TEST-USR-005", "TEST-EQ-RES-005");
        repository.saveReservation(reservation);

        reservation.setStatus(ReservationStatus.CONFIRMED);
        repository.updateReservation(reservation);

        List<String[]> rows = repository.getAllReservationRows();
        String[] match = rows.stream().filter(r -> "TEST-RES-005".equals(r[0])).findFirst().orElse(null);
        assertNotNull(match);
        assertEquals("CONFIRMED", match[5]);
    }

    // payment tests

    @Test
    public void testSavePaymentAppearsInAllPaymentRows() {
        Payment payment = new Payment("TEST-PAY-001", "TEST-RES-PAY-001", 100.0, "CREDIT_CARD", true);
        repository.savePayment(payment);

        List<String[]> rows = repository.getAllPaymentRows();
        boolean found = rows.stream().anyMatch(r -> "TEST-PAY-001".equals(r[0]));
        assertTrue(found);
    }

    @Test
    public void testGetAllPaymentRowsContainsSavedPayment() {
        Payment payment = new Payment("TEST-PAY-002", "TEST-RES-PAY-002", 75.5, "DEBIT_CARD", false);
        repository.savePayment(payment);

        List<String[]> rows = repository.getAllPaymentRows();
        String[] match = rows.stream().filter(r -> "TEST-PAY-002".equals(r[0])).findFirst().orElse(null);

        assertNotNull(match);
        assertEquals("TEST-RES-PAY-002", match[1]);
        assertEquals("75.5", match[2]);
        assertEquals("DEBIT_CARD", match[3]);
        assertEquals("false", match[4]);
    }

    @Test
    public void testGetPaymentRowsByReservationIdReturnsOnlyMatchingRows() {
        Payment payA = new Payment("TEST-PAY-003", "TEST-RES-PAY-003", 50.0, "CASH", true);
        Payment payB = new Payment("TEST-PAY-004", "TEST-RES-PAY-004", 60.0, "CASH", false);
        repository.savePayment(payA);
        repository.savePayment(payB);

        List<String[]> rows = repository.getPaymentRowsByReservationId("TEST-RES-PAY-003");

        assertTrue(rows.stream().anyMatch(r -> "TEST-PAY-003".equals(r[0])));
        assertFalse(rows.stream().anyMatch(r -> "TEST-PAY-004".equals(r[0])));
    }
}
