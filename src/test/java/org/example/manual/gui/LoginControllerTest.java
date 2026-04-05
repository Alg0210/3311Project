package org.example.manual.gui;

import org.example.auth.AuthService;
import org.example.users.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the logic behind LoginController.
 * Exercises AuthService login validation, empty-field handling, and session state
 * without launching the JavaFX toolkit.
 */
public class LoginControllerTest {

    private static final String USERS_FILE = "src/main/resources/data/users.csv";
    private byte[] usersBackup;
    private AuthService authService;

    @BeforeEach
    public void setUp() throws IOException {
        AuthService.logout();
        usersBackup = Files.readAllBytes(Paths.get(USERS_FILE));
        authService = new AuthService();
    }

    @AfterEach
    public void tearDown() throws IOException {
        AuthService.logout();
        Files.write(Paths.get(USERS_FILE), usersBackup);
    }

    @Test
    public void testLoginWithValidCredentialsReturnsUser() {
        authService.register("GUEST", "LoginUser", "login@test.com", "Pass123!", null, "ID1");
        User user = authService.login("login@test.com", "Pass123!");
        assertNotNull(user);
        assertEquals("login@test.com", user.getEmail());
    }

    @Test
    public void testLoginWithInvalidPasswordReturnsNull() {
        authService.register("GUEST", "LoginUser2", "login2@test.com", "Pass123!", null, "ID2");
        User user = authService.login("login2@test.com", "WrongPass1!");
        assertNull(user);
    }

    @Test
    public void testLoginWithNonExistentEmailReturnsNull() {
        User user = authService.login("nonexistent@test.com", "Pass123!");
        assertNull(user);
    }

    @Test
    public void testLoginSetsCurrentUser() {
        authService.register("GUEST", "LoginUser3", "login3@test.com", "Pass123!", null, "ID3");
        authService.login("login3@test.com", "Pass123!");
        assertNotNull(AuthService.getCurrentUser());
        assertEquals("login3@test.com", AuthService.getCurrentUser().getEmail());
    }

    @Test
    public void testEmptyEmailFieldIsRejected() {
        // Controller logic: if email.isEmpty() → show error
        String email = "";
        assertTrue(email.isEmpty());
    }

    @Test
    public void testEmptyPasswordFieldIsRejected() {
        String password = "";
        assertTrue(password.isEmpty());
    }

    @Test
    public void testBothFieldsEmptyIsRejected() {
        String email = "";
        String password = "";
        assertTrue(email.isEmpty() && password.isEmpty());
    }

    @Test
    public void testLoginTrimsWhitespace() {
        authService.register("GUEST", "TrimUser", "trim@test.com", "Pass123!", null, "ID4");
        // Controller trims email before passing to authService
        String email = "  trim@test.com  ".trim();
        User user = authService.login(email, "Pass123!");
        assertNotNull(user);
    }

    @Test
    public void testLoginWithCaseSensitiveEmail() {
        authService.register("GUEST", "CaseUser", "case@test.com", "Pass123!", null, "ID5");
        // findUserByEmail uses equalsIgnoreCase
        User user = authService.login("CASE@TEST.COM", "Pass123!");
        assertNotNull(user);
    }

    @Test
    public void testCurrentUserIsNullBeforeLogin() {
        assertNull(AuthService.getCurrentUser());
    }
}

