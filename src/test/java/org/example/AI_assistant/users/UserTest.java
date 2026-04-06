package org.example.AI_assistant.users;

import org.example.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    // Concrete implementation of User for testing purposes
    private static class TestUser extends User {
        public TestUser(String userId, String name, String email, String password, String userType, String departmentId, String idNumber) {
            super(userId, name, email, password, userType, departmentId, idNumber);
        }

        @Override
        public String getUserType() {
            return userType;
        }
    }

    @BeforeEach
    void setUp() {
        user = new TestUser("TEST-123", "Test User", "test@example.com", "password", "TEST", "CS", "T12345");
    }

    @Test
    void testUserCreation() {
        assertNotNull(user);
    }

    @Test
    void testGetUserId() {
        assertEquals("TEST-123", user.getUserId());
    }

    @Test
    void testGetName() {
        assertEquals("Test User", user.getName());
    }

    @Test
    void testGetEmail() {
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void testGetPassword() {
        assertEquals("password", user.getPassword());
    }

    @Test
    void testGetUserType() {
        assertEquals("TEST", user.getUserType());
    }

    @Test
    void testGetDepartmentId() {
        assertEquals("CS", user.getDepartmentId());
    }

    @Test
    void testGetIdNumber() {
        assertEquals("T12345", user.getIdNumber());
    }

    @Test
    void testSetters() {
        user.setUserId("NEW-ID");
        user.setName("New Name");
        user.setEmail("new@example.com");
        user.setPassword("newpassword");
        user.setIdNumber("N123");

        assertEquals("NEW-ID", user.getUserId());
        assertEquals("New Name", user.getName());
        assertEquals("new@example.com", user.getEmail());
        assertEquals("newpassword", user.getPassword());
        assertEquals("N123", user.getIdNumber());
    }

    @Test
    void testNullValues() {
        user = new TestUser(null, null, null, null, null, null, null);
        assertNull(user.getUserId());
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getUserType());
        assertNull(user.getDepartmentId());
        assertNull(user.getIdNumber());
    }
}

