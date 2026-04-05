package org.example.manual.users;

import org.example.users.HeadCoordinator;
import org.example.users.LabManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class HeadCoordinatorTest {

    @BeforeEach
    public void resetSingleton() throws Exception {
        // Reset the singleton instance via reflection so each test starts fresh
        Field instance = HeadCoordinator.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    public void testGetInstanceReturnsSameObject() {
        HeadCoordinator hc1 = HeadCoordinator.getInstance("HC-001", "Admin", "admin@york.ca", "Pass123!");
        HeadCoordinator hc2 = HeadCoordinator.getInstance("HC-002", "Other", "other@york.ca", "Pass456!");
        assertSame(hc1, hc2);
    }

    @Test
    public void testSingletonPreservesFirstInstanceValues() {
        HeadCoordinator hc1 = HeadCoordinator.getInstance("HC-001", "Admin", "admin@york.ca", "Pass123!");
        HeadCoordinator hc2 = HeadCoordinator.getInstance("HC-999", "Other", "other@york.ca", "Pass456!");
        assertEquals("HC-001", hc2.getUserId());
        assertEquals("Admin", hc2.getName());
    }

    @Test
    public void testGetUserTypeReturnsHeadCoordinator() {
        HeadCoordinator hc = HeadCoordinator.getInstance("HC-001", "Admin", "admin@york.ca", "Pass123!");
        assertEquals("HEAD_COORDINATOR", hc.getUserType());
    }

    @Test
    public void testGenerateManagerAccountCreatesLabManager() {
        HeadCoordinator hc = HeadCoordinator.getInstance("HC-001", "Admin", "admin@york.ca", "Pass123!");
        LabManager manager = hc.generateManagerAccount("MGR-NEW", "NewManager", "mgr@york.ca", "MgrPass1!");

        assertNotNull(manager);
        assertEquals("MGR-NEW", manager.getUserId());
        assertEquals("NewManager", manager.getName());
        assertEquals("mgr@york.ca", manager.getEmail());
        assertEquals("MgrPass1!", manager.getPassword());
        assertEquals("MGR-MGR-NEW", manager.getManagerId());
    }

    @Test
    public void testGenerateManagerAccountReturnsManagerType() {
        HeadCoordinator hc = HeadCoordinator.getInstance("HC-001", "Admin", "admin@york.ca", "Pass123!");
        LabManager manager = hc.generateManagerAccount("MGR-002", "Bob", "bob@york.ca", "Pass123!");
        assertEquals("MANAGER", manager.getUserType());
    }

    @Test
    public void testHeadCoordinatorExtendsLabManager() {
        HeadCoordinator hc = HeadCoordinator.getInstance("HC-001", "Admin", "admin@york.ca", "Pass123!");
        assertTrue(hc instanceof LabManager);
    }

    @Test
    public void testHeadCoordinatorManagerIdIsHead() {
        HeadCoordinator hc = HeadCoordinator.getInstance("HC-001", "Admin", "admin@york.ca", "Pass123!");
        assertEquals("HEAD", hc.getManagerId());
    }
}

