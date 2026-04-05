package org.example.AI_assistant.payment;

import org.example.payment.Payment;
import org.example.payment.PaymentDecorator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentDecoratorTest {

    // ─── CREDIT VALIDATION ──────────────────────────────────────

    @Test
    void testCreditValidationSuccess8Chars() {
        Payment p = new Payment("P1", "R1", 50.0, "CREDIT", true);
        PaymentDecorator d = new PaymentDecorator(p, "CREDIT", "12345678");
        assertTrue(d.validate());
    }

    @Test
    void testCreditValidationFailTooShort() {
        Payment p = new Payment("P1", "R1", 50.0, "CREDIT", true);
        PaymentDecorator d = new PaymentDecorator(p, "CREDIT", "1234");
        assertFalse(d.validate());
    }

    @Test
    void testCreditValidationFailTooLong() {
        Payment p = new Payment("P1", "R1", 50.0, "CREDIT", true);
        PaymentDecorator d = new PaymentDecorator(p, "CREDIT", "123456789");
        assertFalse(d.validate());
    }

    @Test
    void testCreditValidationFailNull() {
        Payment p = new Payment("P1", "R1", 50.0, "CREDIT", true);
        PaymentDecorator d = new PaymentDecorator(p, "CREDIT", null);
        assertFalse(d.validate());
    }

    @Test
    void testCreditValidationFailEmpty() {
        Payment p = new Payment("P1", "R1", 50.0, "CREDIT", true);
        PaymentDecorator d = new PaymentDecorator(p, "CREDIT", "");
        assertFalse(d.validate());
    }

    @Test
    void testCreditProcessSuccess() {
        Payment p = new Payment("P1", "R1", 50.0, "CREDIT", true);
        PaymentDecorator d = new PaymentDecorator(p, "CREDIT", "12345678");
        Payment result = d.process();
        assertNotNull(result);
        assertEquals(p, result);
    }

    @Test
    void testCreditProcessFail() {
        Payment p = new Payment("P1", "R1", 50.0, "CREDIT", true);
        PaymentDecorator d = new PaymentDecorator(p, "CREDIT", "short");
        assertNull(d.process());
    }

    // ─── DEBIT VALIDATION ───────────────────────────────────────

    @Test
    void testDebitValidationSuccess() {
        Payment p = new Payment("P1", "R1", 50.0, "DEBIT", true);
        PaymentDecorator d = new PaymentDecorator(p, "DEBIT", "ABCDEFGH");
        assertTrue(d.validate());
    }

    @Test
    void testDebitValidationFailTooShort() {
        Payment p = new Payment("P1", "R1", 50.0, "DEBIT", true);
        PaymentDecorator d = new PaymentDecorator(p, "DEBIT", "1234567");
        assertFalse(d.validate());
    }

    @Test
    void testDebitProcessSuccess() {
        Payment p = new Payment("P1", "R1", 50.0, "DEBIT", true);
        PaymentDecorator d = new PaymentDecorator(p, "DEBIT", "12345678");
        assertNotNull(d.process());
    }

    @Test
    void testDebitProcessFail() {
        Payment p = new Payment("P1", "R1", 50.0, "DEBIT", true);
        PaymentDecorator d = new PaymentDecorator(p, "DEBIT", "123");
        assertNull(d.process());
    }

    // ─── INSTITUTIONAL VALIDATION ───────────────────────────────

    @Test
    void testInstitutionalValidationSuccess() {
        Payment p = new Payment("P1", "R1", 50.0, "INSTITUTIONAL", true);
        PaymentDecorator d = new PaymentDecorator(p, "INSTITUTIONAL", "INST-001");
        assertTrue(d.validate());
    }

    @Test
    void testInstitutionalValidationSuccessLongRef() {
        Payment p = new Payment("P1", "R1", 50.0, "INSTITUTIONAL", true);
        PaymentDecorator d = new PaymentDecorator(p, "INSTITUTIONAL", "INST-UNIVERSITY-2026");
        assertTrue(d.validate());
    }

    @Test
    void testInstitutionalValidationFailWrongPrefix() {
        Payment p = new Payment("P1", "R1", 50.0, "INSTITUTIONAL", true);
        PaymentDecorator d = new PaymentDecorator(p, "INSTITUTIONAL", "NO-INST");
        assertFalse(d.validate());
    }

    @Test
    void testInstitutionalValidationFailNull() {
        Payment p = new Payment("P1", "R1", 50.0, "INSTITUTIONAL", true);
        PaymentDecorator d = new PaymentDecorator(p, "INSTITUTIONAL", null);
        assertFalse(d.validate());
    }

    @Test
    void testInstitutionalProcessSuccess() {
        Payment p = new Payment("P1", "R1", 50.0, "INSTITUTIONAL", true);
        PaymentDecorator d = new PaymentDecorator(p, "INSTITUTIONAL", "INST-XYZ");
        assertNotNull(d.process());
    }

    // ─── GRANT VALIDATION ───────────────────────────────────────

    @Test
    void testGrantValidationSuccess() {
        Payment p = new Payment("P1", "R1", 50.0, "GRANT", true);
        PaymentDecorator d = new PaymentDecorator(p, "GRANT", "GRT-123");
        assertTrue(d.validate());
    }

    @Test
    void testGrantValidationSuccessLongRef() {
        Payment p = new Payment("P1", "R1", 50.0, "GRANT", true);
        PaymentDecorator d = new PaymentDecorator(p, "GRANT", "GRT-RESEARCH-2026-FUND");
        assertTrue(d.validate());
    }

    @Test
    void testGrantValidationFailWrongPrefix() {
        Payment p = new Payment("P1", "R1", 50.0, "GRANT", true);
        PaymentDecorator d = new PaymentDecorator(p, "GRANT", "WRONG");
        assertFalse(d.validate());
    }

    @Test
    void testGrantValidationFailNull() {
        Payment p = new Payment("P1", "R1", 50.0, "GRANT", true);
        PaymentDecorator d = new PaymentDecorator(p, "GRANT", null);
        assertFalse(d.validate());
    }

    @Test
    void testGrantProcessSuccess() {
        Payment p = new Payment("P1", "R1", 50.0, "GRANT", true);
        PaymentDecorator d = new PaymentDecorator(p, "GRANT", "GRT-999");
        assertNotNull(d.process());
    }

    @Test
    void testGrantProcessFail() {
        Payment p = new Payment("P1", "R1", 50.0, "GRANT", true);
        PaymentDecorator d = new PaymentDecorator(p, "GRANT", "INVALID");
        assertNull(d.process());
    }

    // ─── INVALID / UNKNOWN METHOD ───────────────────────────────

    @Test
    void testInvalidMethodCash() {
        Payment p = new Payment("P1", "R1", 50.0, "CASH", true);
        PaymentDecorator d = new PaymentDecorator(p, "CASH", "NONE");
        assertFalse(d.validate());
        assertNull(d.process());
    }

    @Test
    void testInvalidMethodBitcoin() {
        Payment p = new Payment("P1", "R1", 50.0, "BITCOIN", true);
        PaymentDecorator d = new PaymentDecorator(p, "BITCOIN", "wallet123");
        assertFalse(d.validate());
        assertNull(d.process());
    }

    @Test
    void testEmptyMethod() {
        Payment p = new Payment("P1", "R1", 50.0, "", true);
        PaymentDecorator d = new PaymentDecorator(p, "", "12345678");
        assertFalse(d.validate());
    }

    // ─── GETTERS ─────────────────────────────────────────────────

    @Test
    void testGetPayment() {
        Payment p = new Payment("P1", "R1", 50.0, "CREDIT", true);
        PaymentDecorator d = new PaymentDecorator(p, "CREDIT", "12345678");
        assertEquals(p, d.getPayment());
    }

    @Test
    void testGetPaymentMethod() {
        Payment p = new Payment("P1", "R1", 50.0, "CREDIT", true);
        PaymentDecorator d = new PaymentDecorator(p, "CREDIT", "12345678");
        assertEquals("CREDIT", d.getPaymentMethod());
    }

    @Test
    void testGetAccountReference() {
        Payment p = new Payment("P1", "R1", 50.0, "CREDIT", true);
        PaymentDecorator d = new PaymentDecorator(p, "CREDIT", "12345678");
        assertEquals("12345678", d.getAccountReference());
    }

    // ─── DECORATOR WITH DIFFERENT PRICING TIER AMOUNTS ──────────

    @Test
    void testDecoratorWithStudentDeposit() {
        Payment p = new Payment("P1", "R1", 10.0, "CREDIT", true);
        PaymentDecorator d = new PaymentDecorator(p, "CREDIT", "12345678");
        assertTrue(d.validate());
        Payment result = d.process();
        assertNotNull(result);
        assertEquals(10.0, result.getAmount());
    }

    @Test
    void testDecoratorWithGuestDeposit() {
        Payment p = new Payment("P1", "R1", 30.0, "DEBIT", true);
        PaymentDecorator d = new PaymentDecorator(p, "DEBIT", "87654321");
        assertTrue(d.validate());
        Payment result = d.process();
        assertEquals(30.0, result.getAmount());
    }

    @Test
    void testDecoratorWithInstitutionalResearcherPayment() {
        Payment p = new Payment("P1", "R1", 20.0, "INSTITUTIONAL", true);
        PaymentDecorator d = new PaymentDecorator(p, "INSTITUTIONAL", "INST-RES");
        assertTrue(d.validate());
        Payment result = d.process();
        assertEquals(20.0, result.getAmount());
    }

    @Test
    void testDecoratorWithGrantFacultyPayment() {
        Payment p = new Payment("P1", "R1", 15.0, "GRANT", true);
        PaymentDecorator d = new PaymentDecorator(p, "GRANT", "GRT-FAC");
        assertTrue(d.validate());
        Payment result = d.process();
        assertEquals(15.0, result.getAmount());
    }

    // ─── EXACTLY 8 CHARS BOUNDARY FOR CREDIT/DEBIT ──────────────

    @Test
    void testCreditExactly7CharsFails() {
        Payment p = new Payment("P1", "R1", 50.0, "CREDIT", true);
        PaymentDecorator d = new PaymentDecorator(p, "CREDIT", "1234567");
        assertFalse(d.validate());
    }

    @Test
    void testCreditExactly8CharsSucceeds() {
        Payment p = new Payment("P1", "R1", 50.0, "CREDIT", true);
        PaymentDecorator d = new PaymentDecorator(p, "CREDIT", "12345678");
        assertTrue(d.validate());
    }

    @Test
    void testCreditExactly9CharsFails() {
        Payment p = new Payment("P1", "R1", 50.0, "CREDIT", true);
        PaymentDecorator d = new PaymentDecorator(p, "CREDIT", "123456789");
        assertFalse(d.validate());
    }
}

