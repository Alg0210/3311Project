package org.example.manual.users;

import org.example.users.Researcher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResearcherTest {

    @Test
    public void testConstructorSetsAllFields() {
        Researcher researcher = new Researcher("RES-001", "Alice", "alice@york.ca", "Pass123!", "BIO", "ID300");
        assertEquals("RES-001", researcher.getUserId());
        assertEquals("Alice", researcher.getName());
        assertEquals("alice@york.ca", researcher.getEmail());
        assertEquals("Pass123!", researcher.getPassword());
        assertEquals("BIO", researcher.getDepartmentId());
        assertEquals("ID300", researcher.getIdNumber());
    }

    @Test
    public void testGetUserTypeReturnsResearcher() {
        Researcher researcher = new Researcher("RES-002", "Bob", "bob@york.ca", "Pass123!", "CHEM", "ID301");
        assertEquals("RESEARCHER", researcher.getUserType());
    }

    @Test
    public void testNullDepartmentIdIsAllowed() {
        Researcher researcher = new Researcher("RES-003", "Carol", "carol@york.ca", "Pass123!", null, "ID302");
        assertNull(researcher.getDepartmentId());
    }

    @Test
    public void testSettersUpdateFields() {
        Researcher researcher = new Researcher("RES-004", "Dan", "dan@york.ca", "Pass123!", "BIO", "ID303");
        researcher.setName("Daniel");
        researcher.setEmail("daniel@york.ca");
        researcher.setPassword("NewPass1!");

        assertEquals("Daniel", researcher.getName());
        assertEquals("daniel@york.ca", researcher.getEmail());
        assertEquals("NewPass1!", researcher.getPassword());
    }
}

