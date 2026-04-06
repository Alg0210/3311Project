package org.example.AI_assistant.users;

import org.example.users.LabManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LabManagerTest {

    private LabManager labManager;

    @BeforeEach
    void setUp() {
        labManager = new LabManager("MGR-123", "Manager Name", "manager@example.com", "managerpass", "MGR-ID-1");
    }

    @Test
    void testLabManagerCreation() {
        assertNotNull(labManager);
    }

    @Test
    void testGetUserId() {
        assertEquals("MGR-123", labManager.getUserId());
    }

    @Test
    void testGetName() {
        assertEquals("Manager Name", labManager.getName());
    }

    @Test
    void testGetEmail() {
        assertEquals("manager@example.com", labManager.getEmail());
    }

    @Test
    void testGetPassword() {
        assertEquals("managerpass", labManager.getPassword());
    }

    @Test
    void testGetUserType() {
        assertEquals("MANAGER", labManager.getUserType());
    }

    @Test
    void testGetManagerId() {
        assertEquals("MGR-ID-1", labManager.getManagerId());
    }

    @Test
    void testSetAndGetName() {
        labManager.setName("New Manager Name");
        assertEquals("New Manager Name", labManager.getName());
    }

    @Test
    void testSetAndGetEmail() {
        labManager.setEmail("new.manager@example.com");
        assertEquals("new.manager@example.com", labManager.getEmail());
    }

    @Test
    void testNullDepartmentAndIdNumber() {
        assertNull(labManager.getDepartmentId());
        assertNull(labManager.getIdNumber());
    }
}

