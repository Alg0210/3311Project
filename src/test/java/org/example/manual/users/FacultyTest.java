package org.example.manual.users;

import org.example.users.Faculty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FacultyTest {

    @Test
    public void testConstructorSetsAllFields() {
        Faculty faculty = new Faculty("FAC-001", "Dr. Smith", "smith@york.ca", "Pass123!", "EECS", "ID200");
        assertEquals("FAC-001", faculty.getUserId());
        assertEquals("Dr. Smith", faculty.getName());
        assertEquals("smith@york.ca", faculty.getEmail());
        assertEquals("Pass123!", faculty.getPassword());
        assertEquals("EECS", faculty.getDepartmentId());
        assertEquals("ID200", faculty.getIdNumber());
    }

    @Test
    public void testGetUserTypeReturnsFaculty() {
        Faculty faculty = new Faculty("FAC-002", "Dr. Jones", "jones@york.ca", "Pass123!", "PHYS", "ID201");
        assertEquals("FACULTY", faculty.getUserType());
    }

    @Test
    public void testNullDepartmentIdIsAllowed() {
        Faculty faculty = new Faculty("FAC-003", "Dr. Lee", "lee@york.ca", "Pass123!", null, "ID202");
        assertNull(faculty.getDepartmentId());
    }

    @Test
    public void testSettersUpdateFields() {
        Faculty faculty = new Faculty("FAC-004", "Dr. Kim", "kim@york.ca", "Pass123!", "MATH", "ID203");
        faculty.setName("Dr. Kimura");
        faculty.setEmail("kimura@york.ca");

        assertEquals("Dr. Kimura", faculty.getName());
        assertEquals("kimura@york.ca", faculty.getEmail());
    }
}

