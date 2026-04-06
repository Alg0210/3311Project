package org.example.AI_assistant.users;

import org.example.users.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserFactoryTest {

    @Test
    void testCreateStudent() {
        User user = UserFactory.createUser("STUDENT", "S1", "SName", "s@e.com", "p", "CS", "S123");
        assertTrue(user instanceof Student);
    }

    @Test
    void testCreateFaculty() {
        User user = UserFactory.createUser("FACULTY", "F1", "FName", "f@e.com", "p", "CS", "F123");
        assertTrue(user instanceof Faculty);
    }

    @Test
    void testCreateResearcher() {
        User user = UserFactory.createUser("RESEARCHER", "R1", "RName", "r@e.com", "p", "CS", "R123");
        assertTrue(user instanceof Researcher);
    }

    @Test
    void testCreateGuest() {
        User user = UserFactory.createUser("GUEST", "G1", "GName", "g@e.com", "p", null, "G123");
        assertTrue(user instanceof Guest);
    }

    @Test
    void testCreateManager() {
        User user = UserFactory.createUser("MANAGER", "M1", "MName", "m@e.com", "p", null, null);
        assertTrue(user instanceof LabManager);
    }

    @Test
    void testCreateHeadCoordinator() {
        User user = UserFactory.createUser("HEAD_COORDINATOR", "H1", "HName", "h@e.com", "p", null, null);
        assertTrue(user instanceof HeadCoordinator);
    }

    @Test
    void testCreateUserCaseInsensitive() {
        User user = UserFactory.createUser("student", "S1", "SName", "s@e.com", "p", "CS", "S123");
        assertTrue(user instanceof Student);
    }

    @Test
    void testUnknownUserType() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserFactory.createUser("UNKNOWN", "U1", "UName", "u@e.com", "p", "CS", "U123");
        });
    }

    @Test
    void testCreatedStudentDetails() {
        User user = UserFactory.createUser("STUDENT", "S1", "SName", "s@e.com", "p", "CS", "S123");
        assertEquals("S1", user.getUserId());
        assertEquals("SName", user.getName());
    }

    @Test
    void testCreatedGuestDetails() {
        User user = UserFactory.createUser("GUEST", "G1", "GName", "g@e.com", "p", "SomeDept", "G123");
        assertTrue(user instanceof Guest);
        assertNull(user.getDepartmentId()); // Should be null as per factory logic
    }
}

