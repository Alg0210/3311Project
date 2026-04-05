package org.example.AI_assistant.data;

import org.example.data.CSVRepository;
import org.example.users.User;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class CSVRepositoryTest {
    private static final String TEST_USERS_FILE = "src/main/resources/data/users.csv";
    private CSVRepository repository;

    @BeforeEach
    void setUp() {
        // Clean up test users file
        File file = new File(TEST_USERS_FILE);
        if (file.exists()) file.delete();
        repository = new CSVRepository();
    }

    @Test
    void testSaveAndFindUser() {
        User user = repository.findUserByEmail("repo@example.com");
        assertNull(user);
        repository.saveUser(org.example.users.UserFactory.createUser("STUDENT", "STU-1", "Repo User", "repo@example.com", "Password1!", "D01", "12345"));
        user = repository.findUserByEmail("repo@example.com");
        assertNotNull(user);
        assertEquals("Repo User", user.getName());
    }

    @Test
    void testUpdateUser() {
        repository.saveUser(org.example.users.UserFactory.createUser("STUDENT", "STU-2", "Update User", "update@example.com", "Password1!", "D01", "54321"));
        User user = repository.findUserByEmail("update@example.com");
        assertNotNull(user);
        user.setName("Updated Name");
        repository.updateUser(user);
        User updated = repository.findUserByEmail("update@example.com");
        assertEquals("Updated Name", updated.getName());
    }

    @Test
    void testDeleteUser() {
        repository.saveUser(org.example.users.UserFactory.createUser("STUDENT", "STU-3", "Delete User", "delete@example.com", "Password1!", "D01", "67890"));
        User user = repository.findUserByEmail("delete@example.com");
        assertNotNull(user);
        repository.deleteUser(user.getUserId());
        User deleted = repository.findUserByEmail("delete@example.com");
        assertNull(deleted);
    }
}
