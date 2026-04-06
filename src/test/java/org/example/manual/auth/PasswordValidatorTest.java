package org.example.manual.auth;

import org.example.auth.PasswordValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordValidatorTest {

    @Test
    public void testValidPassword() {
        assertTrue(PasswordValidator.isValid("Test123!"));
    }

    @Test
    public void testTooShortReturnsFalse() {
        assertFalse(PasswordValidator.isValid("Te1!"));
    }

    @Test
    public void testSevenCharactersReturnsFalse() {
        // All criteria met but only 7 characters
        assertFalse(PasswordValidator.isValid("Test12!"));
    }

    @Test
    public void testNoUpperCaseReturnsFalse() {
        assertFalse(PasswordValidator.isValid("test123!"));
    }

    @Test
    public void testNoLowerCaseReturnsFalse() {
        assertFalse(PasswordValidator.isValid("TEST123!"));
    }

    @Test
    public void testNoDigitReturnsFalse() {
        assertFalse(PasswordValidator.isValid("Testtest!"));
    }

    @Test
    public void testNoSymbolReturnsFalse() {
        assertFalse(PasswordValidator.isValid("Test1234"));
    }

    @Test
    public void testEmptyPasswordReturnsFalse() {
        assertFalse(PasswordValidator.isValid(""));
    }

    @Test
    public void testLongComplexPasswordReturnsTrue() {
        assertTrue(PasswordValidator.isValid("MyS3cur3P@ssw0rd!"));
    }

    @Test
    public void testPasswordWithMultipleSymbolsReturnsTrue() {
        assertTrue(PasswordValidator.isValid("T3st@#$%"));
    }

    @Test
    public void testSpaceCountsAsSymbol() {
        assertTrue(PasswordValidator.isValid("Test1 2!"));
    }
}

