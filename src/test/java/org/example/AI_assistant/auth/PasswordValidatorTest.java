package org.example.AI_assistant.auth;

import org.example.auth.PasswordValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordValidatorTest {
    @Test
    void testValidPassword() {
        assertTrue(PasswordValidator.isValid("Password1!"));
    }

    @Test
    void testShortPassword() {
        assertFalse(PasswordValidator.isValid("P1!a"));
    }

    @Test
    void testNoUppercase() {
        assertFalse(PasswordValidator.isValid("password1!"));
    }

    @Test
    void testNoLowercase() {
        assertFalse(PasswordValidator.isValid("PASSWORD1!"));
    }

    @Test
    void testNoDigit() {
        assertFalse(PasswordValidator.isValid("Password!"));
    }

    @Test
    void testNoSymbol() {
        assertFalse(PasswordValidator.isValid("Password1"));
    }
}
