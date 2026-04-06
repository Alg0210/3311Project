package org.example.AI_assistant.users;

import org.example.users.Researcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ResearcherTest {

    private Researcher researcher;

    @BeforeEach
    void setUp() {
        researcher = new Researcher("RES-123", "John Doe", "john.doe@example.com", "password123", "CS", "R12345");
    }

    @Test
    void testResearcherCreation() {
        assertNotNull(researcher);
    }

    @Test
    void testGetUserId() {
        assertEquals("RES-123", researcher.getUserId());
    }

    @Test
    void testGetName() {
        assertEquals("John Doe", researcher.getName());
    }

    @Test
    void testGetEmail() {
        assertEquals("john.doe@example.com", researcher.getEmail());
    }

    @Test
    void testGetPassword() {
        assertEquals("password123", researcher.getPassword());
    }

    @Test
    void testGetUserType() {
        assertEquals("RESEARCHER", researcher.getUserType());
    }

    @Test
    void testGetDepartmentId() {
        assertEquals("CS", researcher.getDepartmentId());
    }

    @Test
    void testGetIdNumber() {
        assertEquals("R12345", researcher.getIdNumber());
    }

    @Test
    void testSetAndGetName() {
        researcher.setName("Jane Doe");
        assertEquals("Jane Doe", researcher.getName());
    }

    @Test
    void testSetAndGetEmail() {
        researcher.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", researcher.getEmail());
    }
}

