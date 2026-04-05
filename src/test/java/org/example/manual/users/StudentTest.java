package org.example.manual.users;

import org.example.users.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    public void testConstructorSetsAllFields() {
        Student student = new Student("STU-001", "Alice", "alice@york.ca", "Pass123!", "CS", "ID100");
        assertEquals("STU-001", student.getUserId());
        assertEquals("Alice", student.getName());
        assertEquals("alice@york.ca", student.getEmail());
        assertEquals("Pass123!", student.getPassword());
        assertEquals("CS", student.getDepartmentId());
        assertEquals("ID100", student.getIdNumber());
    }

    @Test
    public void testGetUserTypeReturnsStudent() {
        Student student = new Student("STU-002", "Bob", "bob@york.ca", "Pass123!", "EE", "ID101");
        assertEquals("STUDENT", student.getUserType());
    }

    @Test
    public void testNullDepartmentIdIsAllowed() {
        Student student = new Student("STU-003", "Carol", "carol@york.ca", "Pass123!", null, "ID102");
        assertNull(student.getDepartmentId());
    }

    @Test
    public void testSettersUpdateFields() {
        Student student = new Student("STU-004", "Dan", "dan@york.ca", "Pass123!", "CS", "ID103");
        student.setName("Daniel");
        student.setEmail("daniel@york.ca");
        student.setPassword("NewPass1!");
        student.setIdNumber("ID999");

        assertEquals("Daniel", student.getName());
        assertEquals("daniel@york.ca", student.getEmail());
        assertEquals("NewPass1!", student.getPassword());
        assertEquals("ID999", student.getIdNumber());
    }

    @Test
    public void testSetUserIdUpdatesId() {
        Student student = new Student("STU-005", "Eve", "eve@york.ca", "Pass123!", "CS", "ID104");
        student.setUserId("STU-UPDATED");
        assertEquals("STU-UPDATED", student.getUserId());
    }
}

