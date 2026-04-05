package org.example.manual.users;

import org.example.users.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserFactoryTest {

    @Test
    public void testCreateStudent() {
        User user = UserFactory.createUser("STUDENT", "STU-001", "Alice", "alice@york.ca", "Pass123!", "CS", "ID100");
        assertNotNull(user);
        assertTrue(user instanceof Student);
        assertEquals("STUDENT", user.getUserType());
    }

    @Test
    public void testCreateFaculty() {
        User user = UserFactory.createUser("FACULTY", "FAC-001", "Dr. Smith", "smith@york.ca", "Pass123!", "EECS", "ID200");
        assertNotNull(user);
        assertTrue(user instanceof Faculty);
        assertEquals("FACULTY", user.getUserType());
    }

    @Test
    public void testCreateResearcher() {
        User user = UserFactory.createUser("RESEARCHER", "RES-001", "Bob", "bob@york.ca", "Pass123!", "BIO", "ID300");
        assertNotNull(user);
        assertTrue(user instanceof Researcher);
        assertEquals("RESEARCHER", user.getUserType());
    }

    @Test
    public void testCreateGuest() {
        User user = UserFactory.createUser("GUEST", "GUE-001", "Visitor", "visitor@email.com", "Pass123!", null, "ID400");
        assertNotNull(user);
        assertTrue(user instanceof Guest);
        assertEquals("GUEST", user.getUserType());
    }

    @Test
    public void testCreateGuestIgnoresDepartmentId() {
        User user = UserFactory.createUser("GUEST", "GUE-002", "Visitor", "visitor@email.com", "Pass123!", "SHOULD_BE_NULL", "ID401");
        // Guest constructor in factory passes null for departmentId
        assertNull(user.getDepartmentId());
    }

    @Test
    public void testCreateManager() {
        User user = UserFactory.createUser("MANAGER", "MGR-001", "Admin", "admin@york.ca", "Pass123!", null, null);
        assertNotNull(user);
        assertTrue(user instanceof LabManager);
        assertEquals("MANAGER", user.getUserType());
    }

    @Test
    public void testCreateManagerSetsManagerId() {
        User user = UserFactory.createUser("MANAGER", "MGR-001", "Admin", "admin@york.ca", "Pass123!", null, null);
        LabManager manager = (LabManager) user;
        assertEquals("MGR-MGR-001", manager.getManagerId());
    }

    @Test
    public void testCreateHeadCoordinator() {
        User user = UserFactory.createUser("HEAD_COORDINATOR", "HC-001", "Head", "head@york.ca", "Pass123!", null, null);
        assertNotNull(user);
        assertTrue(user instanceof HeadCoordinator);
        assertEquals("HEAD_COORDINATOR", user.getUserType());
    }

    @Test
    public void testCreateUserCaseInsensitive() {
        User user = UserFactory.createUser("student", "STU-010", "Alice", "alice@york.ca", "Pass123!", "CS", "ID100");
        assertNotNull(user);
        assertTrue(user instanceof Student);
    }

    @Test
    public void testCreateUserWithUnknownTypeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserFactory.createUser("UNKNOWN", "X-001", "Name", "email@test.com", "Pass123!", null, null);
        });
    }
}

