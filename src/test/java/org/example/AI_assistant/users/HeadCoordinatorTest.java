package org.example.AI_assistant.users;

import org.example.users.HeadCoordinator;
import org.example.users.LabManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.*;

class HeadCoordinatorTest {

    @BeforeEach
    void setUp() throws Exception {
        // Reset singleton instance before each test
        Field instance = HeadCoordinator.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    void testGetInstance() {
        HeadCoordinator hc1 = HeadCoordinator.getInstance("HC-001", "Admin", "admin@example.com", "adminpass");
        assertNotNull(hc1);
    }

    @Test
    void testSingletonInstance() {
        HeadCoordinator hc1 = HeadCoordinator.getInstance("HC-001", "Admin", "admin@example.com", "adminpass");
        HeadCoordinator hc2 = HeadCoordinator.getInstance("HC-002", "Admin2", "admin2@example.com", "adminpass2");
        assertSame(hc1, hc2);
    }

    @Test
    void testGetUserType() {
        HeadCoordinator hc = HeadCoordinator.getInstance("HC-001", "Admin", "admin@example.com", "adminpass");
        assertEquals("HEAD_COORDINATOR", hc.getUserType());
    }

    @Test
    void testGenerateManagerAccount() {
        HeadCoordinator hc = HeadCoordinator.getInstance("HC-001", "Admin", "admin@example.com", "adminpass");
        LabManager manager = hc.generateManagerAccount("MGR-123", "Manager Name", "manager@example.com", "managerpass");
        assertNotNull(manager);
        assertEquals("MGR-123", manager.getUserId());
        assertEquals("Manager Name", manager.getName());
    }

    @Test
    void testGetUserId() {
        HeadCoordinator hc = HeadCoordinator.getInstance("HC-001", "Admin", "admin@example.com", "adminpass");
        assertEquals("HC-001", hc.getUserId());
    }

    @Test
    void testGetName() {
        HeadCoordinator hc = HeadCoordinator.getInstance("HC-001", "Admin", "admin@example.com", "adminpass");
        assertEquals("Admin", hc.getName());
    }

    @Test
    void testGetEmail() {
        HeadCoordinator hc = HeadCoordinator.getInstance("HC-001", "Admin", "admin@example.com", "adminpass");
        assertEquals("admin@example.com", hc.getEmail());
    }

    @Test
    void testGetPassword() {
        HeadCoordinator hc = HeadCoordinator.getInstance("HC-001", "Admin", "admin@example.com", "adminpass");
        assertEquals("adminpass", hc.getPassword());
    }

    @Test
    void testGenerateManagerHasCorrectUserType() {
        HeadCoordinator hc = HeadCoordinator.getInstance("HC-001", "Admin", "admin@example.com", "adminpass");
        LabManager manager = hc.generateManagerAccount("MGR-123", "Manager Name", "manager@example.com", "managerpass");
        assertEquals("MANAGER", manager.getUserType());
    }
}

