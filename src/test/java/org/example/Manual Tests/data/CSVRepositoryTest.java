package org.example.data;

import org.example.equipment.Equipment;
import org.example.users.User;
import org.example.users.UserDecorator;
import org.example.users.UserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CSVRepositoryTest {

    private static final String USERS_FILE = "src/main/resources/data/users.csv";
    private static final String EQUIPMENT_FILE = "src/main/resources/data/equipment.csv";

    private byte[] usersBackup;
    private byte[] equipmentBackup;
    private CSVRepository repository;

    @BeforeEach
    public void setUp() throws IOException {
        usersBackup = Files.readAllBytes(Paths.get(USERS_FILE));
        equipmentBackup = Files.readAllBytes(Paths.get(EQUIPMENT_FILE));
        repository = new CSVRepository();
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.write(Paths.get(USERS_FILE), usersBackup);
        Files.write(Paths.get(EQUIPMENT_FILE), equipmentBackup);
    }

    // ─── USER TESTS ───────────────────────────────────────────────────────────

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

    // ─── EQUIPMENT TESTS ─────────────────────────────────────────────────────

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
}
