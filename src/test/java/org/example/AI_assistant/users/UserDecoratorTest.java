package org.example.AI_assistant.users;

import org.example.users.Student;
import org.example.users.User;
import org.example.users.UserDecorator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserDecoratorTest {

    private User student;
    private UserDecorator decoratedUser;

    @BeforeEach
    void setUp() {
        student = new Student("STU-123", "John Doe", "john.doe@example.com", "password123", "CS", "S12345");
        decoratedUser = new UserDecorator(student, "APPROVAL");
    }

    @Test
    void testDecoratorCreation() {
        assertNotNull(decoratedUser);
    }

    @Test
    void testGetUserType() {
        assertEquals("STUDENT", decoratedUser.getUserType());
    }

    @Test
    void testGetUserId() {
        assertEquals("STU-123", decoratedUser.getUserId());
    }

    @Test
    void testGetDecorationType() {
        assertEquals("APPROVAL", decoratedUser.getDecorationType());
    }

    @Test
    void testDefaultApprovalStatus() {
        assertEquals("NOT_APPROVED", decoratedUser.getApprovalStatus());
        assertFalse(decoratedUser.isApproved());
    }

    @Test
    void testSetAndGetApprovalStatus() {
        decoratedUser.setApprovalStatus("APPROVED");
        assertEquals("APPROVED", decoratedUser.getApprovalStatus());
        assertTrue(decoratedUser.isApproved());
    }

    @Test
    void testGetCertificationNumber() {
        assertNull(decoratedUser.getCertificationNumber());
    }

    @Test
    void testInheritedMethods() {
        assertEquals("John Doe", decoratedUser.getName());
        assertEquals("john.doe@example.com", decoratedUser.getEmail());
        assertEquals("password123", decoratedUser.getPassword());
    }

    @Test
    void testSetAndGetOnDecoratedUser() {
        decoratedUser.setName("Jane Doe");
        assertEquals("Jane Doe", decoratedUser.getName());
    }

    @Test
    void testIsApprovedWithDifferentStatus() {
        decoratedUser.setApprovalStatus("PENDING");
        assertFalse(decoratedUser.isApproved());
    }
}

