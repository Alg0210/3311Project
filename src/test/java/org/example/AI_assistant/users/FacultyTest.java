package org.example.ai.users;

import org.example.users.Faculty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FacultyTest {

    private Faculty faculty;

    @BeforeEach
    void setUp() {
        faculty = new Faculty("FAC-123", "John Doe", "john.doe@example.com", "password123", "CS", "F12345");
    }

    @Test
    void testFacultyCreation() {
        assertNotNull(faculty);
    }

    @Test
    void testGetUserId() {
        assertEquals("FAC-123", faculty.getUserId());
    }

    @Test
    void testGetName() {
        assertEquals("John Doe", faculty.getName());
    }

    @Test
    void testGetEmail() {
        assertEquals("john.doe@example.com", faculty.getEmail());
    }

    @Test
    void testGetPassword() {
        assertEquals("password123", faculty.getPassword());
    }

    @Test
    void testGetUserType() {
        assertEquals("FACULTY", faculty.getUserType());
    }

    @Test
    void testGetDepartmentId() {
        assertEquals("CS", faculty.getDepartmentId());
    }

    @Test
    void testGetIdNumber() {
        assertEquals("F12345", faculty.getIdNumber());
    }

    @Test
    void testSetAndGetName() {
        faculty.setName("Jane Doe");
        assertEquals("Jane Doe", faculty.getName());
    }

    @Test
    void testSetAndGetEmail() {
        faculty.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", faculty.getEmail());
    }
}

