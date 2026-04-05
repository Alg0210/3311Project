package org.example.manual.auth;

import org.example.auth.AuthService;
import org.example.users.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {

    private static final String userFile = "src/main/resources/data/users.csv";

    private byte[] usersBackup;
    private AuthService authService;

    @BeforeEach
    public void setUp() throws IOException {
        AuthService.logout();
        usersBackup = Files.readAllBytes(Paths.get(userFile));
        authService = new AuthService();
    }

    @AfterEach
    public void restart() throws IOException {
        AuthService.logout();
        Files.write(Paths.get(userFile), usersBackup);
    }

    @Test
    public void testLoginWithValidCredentials() {
        authService.register("GUEST", "Alice", "alice@authtest.com", "Test123!", null, "ID001");
        User result = authService.login("alice@authtest.com", "Test123!");
        assertNotNull(result);
        assertEquals("alice@authtest.com", result.getEmail());
    }

    @Test
    public void testLoginWithWrongPasswordReturnsNull() {
        authService.register("GUEST", "Bob", "bob@authtest.com", "Test123!", null, "ID002");
        User result = authService.login("bob@authtest.com", "WrongPassword1!");
        assertNull(result);
    }

    @Test
    public void testLoginWithNonExistentEmailReturnsNull() {
        User result = authService.login("nobody@authtest.com", "Test123!");
        assertNull(result);
    }

    @Test
    public void testRegisterWithWeakPasswordReturnsFalse() {
        boolean result = authService.register("GUEST", "Charlie", "charlie@authtest.com", "weak", null, "ID003");
        assertFalse(result);
    }

    @Test
    public void testRegisterDuplicateEmailReturnsFalse() {
        boolean firstRegister = authService.register("GUEST", "Dave", "dave@authtest.com", "Test123!", null, "ID004");
        boolean secondRegister = authService.register("GUEST", "Dave2", "dave@authtest.com", "Test123!", null, "ID005");
        assertTrue(firstRegister);
        assertFalse(secondRegister);
    }

    @Test
    public void testLogoutClearsCurrentUser() {
        authService.register("GUEST", "Eve", "eve@authtest.com", "Test123!", null, "ID006");
        authService.login("eve@authtest.com", "Test123!");
        assertNotNull(AuthService.getCurrentUser());
        AuthService.logout();
        assertNull(AuthService.getCurrentUser());
    }

    @Test
    public void testGetCurrentUserAfterLoginReturnsLoggedInUser() {
        authService.register("GUEST", "Frank", "frank@authtest.com", "Test123!", null, "ID007");
        authService.login("frank@authtest.com", "Test123!");
        User current = AuthService.getCurrentUser();
        assertNotNull(current);
        assertEquals("frank@authtest.com", current.getEmail());
    }

    @Test
    public void testIsApprovedForGuestUserReturnsTrue() {
        authService.register("GUEST", "Grace", "grace@authtest.com", "Test123!", null, "ID008");
        assertTrue(authService.isApproved("grace@authtest.com"));
    }

    @Test
    public void testIsApprovedForStudentReturnsFalseBeforeApproval() {
        authService.register("STUDENT", "Henry", "henry@authtest.com", "Test123!", "CS", "ID009");
        assertFalse(authService.isApproved("henry@authtest.com"));
    }

    @Test
    public void testIsApprovedForFacultyReturnsFalseBeforeApproval() {
        authService.register("FACULTY", "Irene", "irene@authtest.com", "Test123!", "EE", "ID010");
        assertFalse(authService.isApproved("irene@authtest.com"));
    }

    @Test
    public void testRegisterStudentPersistsToCSV() {
        boolean registered = authService.register("STUDENT", "Jack", "jack@authtest.com", "Test123!", "CS", "ID011");
        assertTrue(registered);
        User found = authService.login("jack@authtest.com", "Test123!");
        assertNotNull(found);
    }

    @Test
    public void testGetCurrentUserBeforeLoginReturnsNull() {
        assertNull(AuthService.getCurrentUser());
    }
}

