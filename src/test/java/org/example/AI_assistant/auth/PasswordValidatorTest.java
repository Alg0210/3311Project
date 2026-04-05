package org.example.AI_assistant.auth;

import org.example.auth.PasswordValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordValidatorTest {

    @Test
    public void acceptsPasswordMeetingAllRequirements() {
        assertTrue(PasswordValidator.isValid("Valid123!"));
    }

    @Test
    public void rejectsPasswordShorterThanEightCharacters() {
        assertFalse(PasswordValidator.isValid("Aa1!abc"));
    }

    @Test
    public void rejectsPasswordWithoutUppercaseLetter() {
        assertFalse(PasswordValidator.isValid("valid123!"));
    }

    @Test
    public void rejectsPasswordWithoutLowercaseLetter() {
        assertFalse(PasswordValidator.isValid("VALID123!"));
    }

    @Test
    public void rejectsPasswordWithoutDigit() {
        assertFalse(PasswordValidator.isValid("Invalid!!"));
    }

    @Test
    public void rejectsPasswordWithoutSymbol() {
        assertFalse(PasswordValidator.isValid("Invalid123"));
    }
}