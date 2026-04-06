package org.example.AI_assistant.users;

import org.example.users.Guest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GuestTest {

    private Guest guest;

    @BeforeEach
    void setUp() {
        guest = new Guest("GUE-123", "John Doe", "john.doe@example.com", "password123", "N/A", "G12345");
    }

    @Test
    void testGuestCreation() {
        assertNotNull(guest);
    }

    @Test
    void testGetUserId() {
        assertEquals("GUE-123", guest.getUserId());
    }

    @Test
    void testGetName() {
        assertEquals("John Doe", guest.getName());
    }

    @Test
    void testGetEmail() {
        assertEquals("john.doe@example.com", guest.getEmail());
    }

    @Test
    void testGetPassword() {
        assertEquals("password123", guest.getPassword());
    }

    @Test
    void testGetUserType() {
        assertEquals("GUEST", guest.getUserType());
    }

    @Test
    void testGetDepartmentId() {
        assertEquals("N/A", guest.getDepartmentId());
    }

    @Test
    void testGetIdNumber() {
        assertEquals("G12345", guest.getIdNumber());
    }

    @Test
    void testSetAndGetName() {
        guest.setName("Jane Doe");
        assertEquals("Jane Doe", guest.getName());
    }

    @Test
    void testSetAndGetEmail() {
        guest.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", guest.getEmail());
    }
}

