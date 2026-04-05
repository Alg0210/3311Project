package org.example.manual.users;

import org.example.users.Guest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GuestTest {

    @Test
    public void testConstructorSetsAllFields() {
        Guest guest = new Guest("GUE-001", "Visitor", "visitor@email.com", "Pass123!", null, "ID400");
        assertEquals("GUE-001", guest.getUserId());
        assertEquals("Visitor", guest.getName());
        assertEquals("visitor@email.com", guest.getEmail());
        assertEquals("Pass123!", guest.getPassword());
        assertEquals("ID400", guest.getIdNumber());
    }

    @Test
    public void testGetUserTypeReturnsGuest() {
        Guest guest = new Guest("GUE-002", "Bob", "bob@email.com", "Pass123!", null, "ID401");
        assertEquals("GUEST", guest.getUserType());
    }

    @Test
    public void testDepartmentIdCanBeNull() {
        Guest guest = new Guest("GUE-003", "Carol", "carol@email.com", "Pass123!", null, "ID402");
        assertNull(guest.getDepartmentId());
    }

    @Test
    public void testDepartmentIdCanBeSetExplicitly() {
        Guest guest = new Guest("GUE-004", "Dan", "dan@email.com", "Pass123!", "DEPT", "ID403");
        assertEquals("DEPT", guest.getDepartmentId());
    }

    @Test
    public void testSettersUpdateFields() {
        Guest guest = new Guest("GUE-005", "Eve", "eve@email.com", "Pass123!", null, "ID404");
        guest.setName("Evelyn");
        guest.setEmail("evelyn@email.com");

        assertEquals("Evelyn", guest.getName());
        assertEquals("evelyn@email.com", guest.getEmail());
    }
}

