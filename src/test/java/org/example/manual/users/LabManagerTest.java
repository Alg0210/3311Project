package org.example.manual.users;

import org.example.users.LabManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LabManagerTest {

    @Test
    public void testConstructorSetsAllFields() {
        LabManager manager = new LabManager("MGR-001", "Admin", "admin@york.ca", "Pass123!", "MGR-MGR-001");
        assertEquals("MGR-001", manager.getUserId());
        assertEquals("Admin", manager.getName());
        assertEquals("admin@york.ca", manager.getEmail());
        assertEquals("Pass123!", manager.getPassword());
        assertEquals("MGR-MGR-001", manager.getManagerId());
    }

    @Test
    public void testGetUserTypeReturnsManager() {
        LabManager manager = new LabManager("MGR-002", "Bob", "bob@york.ca", "Pass123!", "MGR-002");
        assertEquals("MANAGER", manager.getUserType());
    }

    @Test
    public void testDepartmentIdIsNull() {
        LabManager manager = new LabManager("MGR-003", "Carol", "carol@york.ca", "Pass123!", "MGR-003");
        assertNull(manager.getDepartmentId());
    }

    @Test
    public void testIdNumberIsNull() {
        LabManager manager = new LabManager("MGR-004", "Dan", "dan@york.ca", "Pass123!", "MGR-004");
        assertNull(manager.getIdNumber());
    }

    @Test
    public void testSettersUpdateFields() {
        LabManager manager = new LabManager("MGR-005", "Eve", "eve@york.ca", "Pass123!", "MGR-005");
        manager.setName("Evelyn");
        manager.setEmail("evelyn@york.ca");
        manager.setPassword("NewPass1!");

        assertEquals("Evelyn", manager.getName());
        assertEquals("evelyn@york.ca", manager.getEmail());
        assertEquals("NewPass1!", manager.getPassword());
    }

    @Test
    public void testGetManagerIdReturnsCorrectValue() {
        LabManager manager = new LabManager("MGR-006", "Frank", "frank@york.ca", "Pass123!", "MGR-CUSTOM");
        assertEquals("MGR-CUSTOM", manager.getManagerId());
    }
}

