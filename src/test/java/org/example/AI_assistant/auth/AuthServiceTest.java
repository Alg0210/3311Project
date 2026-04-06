package org.example.AI_assistant.auth;

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
    private static final String USERS_FILE = "src/main/resources/data/users.csv";
    private AuthService authService;
    private byte[] usersBackup;

    @BeforeEach
    void setUp() throws IOException {
        AuthService.logout();
        usersBackup = Files.readAllBytes(Paths.get(USERS_FILE));
        authService = new AuthService();
    }

    @AfterEach
    void tearDown() throws IOException {
        AuthService.logout();
        Files.write(Paths.get(USERS_FILE), usersBackup);
    }

    @Test
    void testRegisterAndLoginSuccess() {
        boolean registered = authService.register("STUDENT", "Test User", "ai_auth_login@test.com", "Password1!", "D01",
                "12345");
        assertTrue(registered);
        User user = authService.login("ai_auth_login@test.com", "Password1!");
        assertNotNull(user);
        assertEquals("ai_auth_login@test.com", user.getEmail());
    }

    @Test
    void testRegisterDuplicateEmail() {
        authService.register("STUDENT", "Test User", "ai_auth_dupe@test.com", "Password1!", "D01", "12345");
        boolean registered = authService.register("STUDENT", "Test User2", "ai_auth_dupe@test.com", "Password1!", "D01",
                "54321");
        assertFalse(registered);
    }

    @Test
    void testRegisterWeakPassword() {
        boolean registered = authService.register("STUDENT", "Test User", "ai_auth_weak@test.com", "weak", "D01",
                "12345");
        assertFalse(registered);
    }

    @Test
    void testLoginWrongPassword() {
        authService.register("STUDENT", "Test User", "ai_auth_wrongpw@test.com", "Password1!", "D01", "12345");
        User user = authService.login("ai_auth_wrongpw@test.com", "WrongPassword");
        assertNull(user);
    }

    @Test
    void testIsApprovedForDecorator() {
        authService.register("STUDENT", "Test User", "ai_auth_decorator@test.com", "Password1!", "D01", "12345");
        assertFalse(authService.isApproved("ai_auth_decorator@test.com"));
    }

    @Test
    void testLogout() {
        authService.register("STUDENT", "Test User", "ai_auth_logout@test.com", "Password1!", "D01", "12345");
        authService.login("ai_auth_logout@test.com", "Password1!");
        AuthService.logout();
        assertNull(AuthService.getCurrentUser());
    }
}
