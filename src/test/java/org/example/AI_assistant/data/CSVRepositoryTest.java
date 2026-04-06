package org.example.AI_assistant.data;

import org.example.data.CSVRepository;
import org.example.users.User;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class CSVRepositoryTest {
    private static final String TEST_USERS_FILE = "src/main/resources/data/users.csv";
    private CSVRepository repository;
    private byte[] usersBackup;

    @BeforeEach
    void setUp() throws IOException {
        usersBackup = Files.readAllBytes(Paths.get(TEST_USERS_FILE));
        repository = new CSVRepository();
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.write(Paths.get(TEST_USERS_FILE), usersBackup);
    }

    @Test
    void testSaveAndFindUser() {
        User user = repository.findUserByEmail("ai_csvrepo_save@test.com");
        assertNull(user);
        repository.saveUser(org.example.users.UserFactory.createUser("STUDENT", "STU-1", "Repo User",
                "ai_csvrepo_save@test.com", "Password1!", "D01", "12345"));
        user = repository.findUserByEmail("ai_csvrepo_save@test.com");
        assertNotNull(user);
        assertEquals("Repo User", user.getName());
    }

    @Test
    void testUpdateUser() {
        repository.saveUser(org.example.users.UserFactory.createUser("STUDENT", "STU-2", "Update User",
                "ai_csvrepo_update@test.com", "Password1!", "D01", "54321"));
        User user = repository.findUserByEmail("ai_csvrepo_update@test.com");
        assertNotNull(user);
        user.setName("Updated Name");
        repository.updateUser(user);
        User updated = repository.findUserByEmail("ai_csvrepo_update@test.com");
        assertEquals("Updated Name", updated.getName());
    }

    @Test
    void testDeleteUser() {
        repository.saveUser(org.example.users.UserFactory.createUser("STUDENT", "STU-3", "Delete User",
                "ai_csvrepo_delete@test.com", "Password1!", "D01", "67890"));
        User user = repository.findUserByEmail("ai_csvrepo_delete@test.com");
        assertNotNull(user);
        repository.deleteUser(user.getUserId());
        User deleted = repository.findUserByEmail("ai_csvrepo_delete@test.com");
        assertNull(deleted);
    }
}
