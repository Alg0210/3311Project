package org.example.AI_assistant.users;

import org.example.users.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("STU-123", "John Doe", "john.doe@example.com", "password123", "CS", "S12345");
    }

    @Test
    void testStudentCreation() {
        assertNotNull(student);
    }

    @Test
    void testGetUserId() {
        assertEquals("STU-123", student.getUserId());
    }

    @Test
    void testGetName() {
        assertEquals("John Doe", student.getName());
    }

    @Test
    void testGetEmail() {
        assertEquals("john.doe@example.com", student.getEmail());
    }

    @Test
    void testGetPassword() {
        assertEquals("password123", student.getPassword());
    }

    @Test
    void testGetUserType() {
        assertEquals("STUDENT", student.getUserType());
    }

    @Test
    void testGetDepartmentId() {
        assertEquals("CS", student.getDepartmentId());
    }

    @Test
    void testGetIdNumber() {
        assertEquals("S12345", student.getIdNumber());
    }

    @Test
    void testSetAndGetName() {
        student.setName("Jane Doe");
        assertEquals("Jane Doe", student.getName());
    }

    @Test
    void testSetAndGetEmail() {
        student.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", student.getEmail());
    }
}

