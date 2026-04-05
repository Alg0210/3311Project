package org.example.AI_assistant.auth;

import org.example.auth.AuthService;
import org.example.data.CSVRepository;
import org.example.users.User;
import org.example.users.UserDecorator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {

    private static final String USERS_FILE = "src/main/resources/data/users.csv";

    private byte[] usersBackup;
    private AuthService authService;
    private CSVRepository repository;

    @BeforeEach
    public void setUp() throws IOException {
        usersBackup = Files.readAllBytes(Paths.get(USERS_FILE));
        AuthService.logout();
        authService = new AuthService();
        repository = new CSVRepository();
    }

    @AfterEach
    public void tearDown() throws IOException {
        AuthService.logout();
        Files.write(Paths.get(USERS_FILE), usersBackup);
    }

    @Test
    public void loginReturnsUserAndSetsCurrentUserForValidCredentials() {
        boolean registered = authService.register("GUEST", "Alice", "ai-auth-alice@example.com", "Test123!", null,
                "ID-AUTH-001");

        assertTrue(registered);

        User loggedIn = authService.login("ai-auth-alice@example.com", "Test123!");

        assertNotNull(loggedIn);
        assertEquals("ai-auth-alice@example.com", loggedIn.getEmail());
        assertEquals(loggedIn, AuthService.getCurrentUser());
    }

    @Test
    public void loginReturnsNullForWrongPassword() {
        authService.register("GUEST", "Bob", "ai-auth-bob@example.com", "Test123!", null, "ID-AUTH-002");

        User loggedIn = authService.login("ai-auth-bob@example.com", "Wrong123!");

        assertNull(loggedIn);
        assertNull(AuthService.getCurrentUser());
    }

    @Test
    public void registerRejectsWeakPassword() {
        boolean registered = authService.register("GUEST", "Cara", "ai-auth-cara@example.com", "weak", null,
                "ID-AUTH-003");

        assertFalse(registered);
        assertNull(repository.findUserByEmail("ai-auth-cara@example.com"));
    }

    @Test
    public void registerRejectsDuplicateEmail() {
        boolean firstRegistration = authService.register("GUEST", "Dan", "ai-auth-dan@example.com", "Test123!", null,
                "ID-AUTH-004");
        boolean secondRegistration = authService.register("GUEST", "Dan Clone", "ai-auth-dan@example.com", "Test123!",
                null, "ID-AUTH-005");

        assertTrue(firstRegistration);
        assertFalse(secondRegistration);
    }

    @Test
    public void registerStudentStoresApprovalDecoratorAndIsNotApprovedInitially() {
        boolean registered = authService.register("STUDENT", "Eve", "ai-auth-eve@example.com", "Test123!", "EECS",
                "ID-AUTH-006");

        User savedUser = repository.findUserByEmail("ai-auth-eve@example.com");

        assertTrue(registered);
        assertNotNull(savedUser);
        assertTrue(savedUser instanceof UserDecorator);
        assertEquals("APPROVAL", ((UserDecorator) savedUser).getDecorationType());
        assertFalse(authService.isApproved("ai-auth-eve@example.com"));
    }

    @Test
    public void guestUsersAreAutomaticallyConsideredApproved() {
        authService.register("GUEST", "Finn", "ai-auth-finn@example.com", "Test123!", null, "ID-AUTH-007");

        User savedUser = repository.findUserByEmail("ai-auth-finn@example.com");

        assertNotNull(savedUser);
        assertFalse(savedUser instanceof UserDecorator);
        assertTrue(authService.isApproved("ai-auth-finn@example.com"));
    }

    @Test
    public void logoutClearsCurrentUser() {
        authService.register("GUEST", "Gia", "ai-auth-gia@example.com", "Test123!", null, "ID-AUTH-008");
        authService.login("ai-auth-gia@example.com", "Test123!");

        AuthService.logout();

        assertNull(AuthService.getCurrentUser());
    }
}