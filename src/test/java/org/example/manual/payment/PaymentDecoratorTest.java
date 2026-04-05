package org.example.manual.payment;

import org.example.payment.Payment;
import org.example.payment.PaymentDecorator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentDecoratorTest {

    private Payment basePayment;

    @BeforeEach
    void setUp() {
        basePayment = new Payment("PAY-DEC-001", "RES-001", 50.0, "CREDIT", true);
    }

    // ─── CREDIT VALIDATION ───────────────────────────────────────

    @Test
    void testValidateCreditValid() {
        PaymentDecorator dec = new PaymentDecorator(basePayment, "CREDIT", "12345678");
        assertTrue(dec.validate());
    }

    @Test
    void testValidateCreditTooShort() {
        PaymentDecorator dec = new PaymentDecorator(basePayment, "CREDIT", "1234");
        assertFalse(dec.validate());
    }

    @Test
    void testValidateCreditTooLong() {
        PaymentDecorator dec = new PaymentDecorator(basePayment, "CREDIT", "123456789");
        assertFalse(dec.validate());
    }

    @Test
    void testValidateCreditNullReference() {
        PaymentDecorator dec = new PaymentDecorator(basePayment, "CREDIT", null);
        assertFalse(dec.validate());
    }

    // ─── DEBIT VALIDATION ────────────────────────────────────────

    @Test
    void testValidateDebitValid() {
        PaymentDecorator dec = new PaymentDecorator(basePayment, "DEBIT", "87654321");
        assertTrue(dec.validate());
    }

    @Test
    void testValidateDebitInvalid() {
        PaymentDecorator dec = new PaymentDecorator(basePayment, "DEBIT", "123");
        assertFalse(dec.validate());
    }

    // ─── INSTITUTIONAL VALIDATION ────────────────────────────────

    @Test
    void testValidateInstitutionalValid() {
        PaymentDecorator dec = new PaymentDecorator(basePayment, "INSTITUTIONAL", "INST-YU-001");
        assertTrue(dec.validate());
    }

    @Test
    void testValidateInstitutionalInvalidPrefix() {
        PaymentDecorator dec = new PaymentDecorator(basePayment, "INSTITUTIONAL", "YORK-001");
        assertFalse(dec.validate());
    }

    @Test
    void testValidateInstitutionalNull() {
        PaymentDecorator dec = new PaymentDecorator(basePayment, "INSTITUTIONAL", null);
        assertFalse(dec.validate());
    }

    // ─── GRANT VALIDATION ────────────────────────────────────────

    @Test
    void testValidateGrantValid() {
        PaymentDecorator dec = new PaymentDecorator(basePayment, "GRANT", "GRT-NSERC-001");
        assertTrue(dec.validate());
    }

    @Test
    void testValidateGrantInvalidPrefix() {
        PaymentDecorator dec = new PaymentDecorator(basePayment, "GRANT", "GRANT-001");
        assertFalse(dec.validate());
    }

    @Test
    void testValidateGrantNull() {
        PaymentDecorator dec = new PaymentDecorator(basePayment, "GRANT", null);
        assertFalse(dec.validate());
    }

    // ─── UNKNOWN METHOD ──────────────────────────────────────────

    @Test
    void testValidateUnknownMethod() {
        PaymentDecorator dec = new PaymentDecorator(basePayment, "BITCOIN", "abc123");
        assertFalse(dec.validate());
    }

    // ─── PROCESS TESTS ───────────────────────────────────────────

    @Test
    void testProcessReturnsPaymentWhenValid() {
        PaymentDecorator dec = new PaymentDecorator(basePayment, "CREDIT", "12345678");
        Payment result = dec.process();
        assertNotNull(result);
        assertEquals(basePayment, result);
    }

    @Test
    void testProcessReturnsNullWhenInvalid() {
        PaymentDecorator dec = new PaymentDecorator(basePayment, "CREDIT", "short");
        Payment result = dec.process();
        assertNull(result);
    }

    // ─── GETTER TESTS ────────────────────────────────────────────

    @Test
    void testGetPayment() {
        PaymentDecorator dec = new PaymentDecorator(basePayment, "CREDIT", "12345678");
        assertEquals(basePayment, dec.getPayment());
    }

    @Test
    void testGetPaymentMethod() {
        PaymentDecorator dec = new PaymentDecorator(basePayment, "GRANT", "GRT-001");
        assertEquals("GRANT", dec.getPaymentMethod());
    }

    @Test
    void testGetAccountReference() {
        PaymentDecorator dec = new PaymentDecorator(basePayment, "CREDIT", "12345678");
        assertEquals("12345678", dec.getAccountReference());
    }
}
