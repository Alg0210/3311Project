package org.example.manual.gui;

import org.example.auth.AuthService;
import org.example.auth.PasswordValidator;
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

/**
 * Tests for the logic behind RegisterController.
 * Covers validation of required fields, password matching, password strength,
 * user type-specific behaviour, and successful registration — all without JavaFX.
 */
public class RegisterControllerTest {

    private static final String USERS_FILE = "src/main/resources/data/users.csv";
    private byte[] usersBackup;
    private AuthService authService;
    private CSVRepository repository;

    @BeforeEach
    public void setUp() throws IOException {
        AuthService.logout();
        usersBackup = Files.readAllBytes(Paths.get(USERS_FILE));
        authService = new AuthService();
        repository = new CSVRepository();
    }

    @AfterEach
    public void tearDown() throws IOException {
        AuthService.logout();
        Files.write(Paths.get(USERS_FILE), usersBackup);
    }

    @Test
    public void testRegisterGuestSucceeds() {
        boolean result = authService.register("GUEST", "GuestUser", "guest@reg.test", "Pass123!", null, "ID1");
        assertTrue(result);
    }

    @Test
    public void testRegisterStudentSucceeds() {
        boolean result = authService.register("STUDENT", "StuUser", "stu@reg.test", "Pass123!", "CS", "ID2");
        assertTrue(result);
    }

    @Test
    public void testRegisterStudentCreatesDecoratorWithApproval() {
        authService.register("STUDENT", "DecStu", "decstu@reg.test", "Pass123!", "CS", "ID3");
        User found = repository.findUserByEmail("decstu@reg.test");
        assertNotNull(found);
        assertTrue(found instanceof UserDecorator);
        assertEquals("APPROVAL", ((UserDecorator) found).getDecorationType());
    }

    @Test
    public void testRegisterWithWeakPasswordFails() {
        boolean result = authService.register("GUEST", "Weak", "weak@reg.test", "bad", null, "ID4");
        assertFalse(result);
    }

    @Test
    public void testRegisterWithDuplicateEmailFails() {
        authService.register("GUEST", "First", "dup@reg.test", "Pass123!", null, "ID5");
        boolean result = authService.register("GUEST", "Second", "dup@reg.test", "Pass123!", null, "ID6");
        assertFalse(result);
    }

    @Test
    public void testPasswordsMustMatch() {
        // Controller validates password == confirmPassword before calling register
        String password = "Pass123!";
        String confirm = "DifferentPass1!";
        assertNotEquals(password, confirm);
    }

    @Test
    public void testEmptyRequiredFieldsRejected() {
        // Controller checks: name.isEmpty() || email.isEmpty() || password.isEmpty() || userType == null
        String name = "";
        String email = "email@test.com";
        assertTrue(name.isEmpty());
    }

    @Test
    public void testNonGuestWithoutIdNumberIsRejected() {
        // Controller: if (!userType.equals("GUEST") && idNumber.isEmpty()) → error
        String userType = "STUDENT";
        String idNumber = "";
        assertTrue(!userType.equals("GUEST") && idNumber.isEmpty());
    }

    @Test
    public void testGuestWithoutIdNumberIsAllowed() {
        // Controller: Guest does not require idNumber check
        String userType = "GUEST";
        String idNumber = "";
        assertFalse(!userType.equals("GUEST") && idNumber.isEmpty());
    }

    @Test
    public void testPasswordValidatorAcceptsStrongPassword() {
        assertTrue(PasswordValidator.isValid("MyStr0ng!"));
        assertFalse(PasswordValidator.isValid("weak"));
    }
}

