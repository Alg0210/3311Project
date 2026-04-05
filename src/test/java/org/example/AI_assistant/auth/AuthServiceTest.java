package org.example.AI_assistant.auth;

import org.example.auth.AuthService;
import org.example.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {
    private AuthService authService;

    @BeforeEach
    void setUp() {
        authService = new AuthService();
    }

    @Test
    void testRegisterAndLoginSuccess() {
        boolean registered = authService.register("STUDENT", "Test User", "testuser@example.com", "Password1!", "D01", "12345");
        assertTrue(registered);
        User user = authService.login("testuser@example.com", "Password1!");
        assertNotNull(user);
        assertEquals("testuser@example.com", user.getEmail());
    }

    @Test
    void testRegisterDuplicateEmail() {
        authService.register("STUDENT", "Test User", "dupe@example.com", "Password1!", "D01", "12345");
        boolean registered = authService.register("STUDENT", "Test User2", "dupe@example.com", "Password1!", "D01", "54321");
        assertFalse(registered);
    }

    @Test
    void testRegisterWeakPassword() {
        boolean registered = authService.register("STUDENT", "Test User", "weakpass@example.com", "weak", "D01", "12345");
        assertFalse(registered);
    }

    @Test
    void testLoginWrongPassword() {
        authService.register("STUDENT", "Test User", "wrongpass@example.com", "Password1!", "D01", "12345");
        User user = authService.login("wrongpass@example.com", "WrongPassword");
        assertNull(user);
    }

    @Test
    void testIsApprovedForDecorator() {
        authService.register("STUDENT", "Test User", "decorator@example.com", "Password1!", "D01", "12345");
        assertFalse(authService.isApproved("decorator@example.com"));
    }

    @Test
    void testLogout() {
        authService.register("STUDENT", "Test User", "logout@example.com", "Password1!", "D01", "12345");
        authService.login("logout@example.com", "Password1!");
        AuthService.logout();
        assertNull(AuthService.getCurrentUser());
    }
}
