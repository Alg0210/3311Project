package org.example.manual.payment;

import org.example.payment.Payment;
import org.example.payment.PaymentDecorator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentDecoratorTest {

    @Test
    public void testCreditDebitValidationSuccess() {
        Payment p = new Payment("P1", "R1", 50, "CREDIT", true);
        PaymentDecorator d = new PaymentDecorator(p, "CREDIT", "12345678");
        assertTrue(d.validate());
        assertNotNull(d.process());
    }

    @Test
    public void testCreditDebitValidationFail() {
        Payment p = new Payment("P1", "R1", 50, "DEBIT", true);
        PaymentDecorator d = new PaymentDecorator(p, "DEBIT", "1234");
        assertFalse(d.validate());
        assertNull(d.process());
    }

    @Test
    public void testInstitutionalValidationSuccess() {
        Payment p = new Payment("P1", "R1", 50, "INSTITUTIONAL", true);
        PaymentDecorator d = new PaymentDecorator(p, "INSTITUTIONAL", "INST-001");
        assertTrue(d.validate());
        assertNotNull(d.process());
    }

    @Test
    public void testInstitutionalValidationFail() {
        Payment p = new Payment("P1", "R1", 50, "INSTITUTIONAL", true);
        PaymentDecorator d = new PaymentDecorator(p, "INSTITUTIONAL", "NO-INST");
        assertFalse(d.validate());
    }

    @Test
    public void testGrantValidationSuccess() {
        Payment p = new Payment("P1", "R1", 50, "GRANT", true);
        PaymentDecorator d = new PaymentDecorator(p, "GRANT", "GRT-123");
        assertTrue(d.validate());
        assertNotNull(d.process());
    }

    @Test
    public void testGrantValidationFail() {
        Payment p = new Payment("P1", "R1", 50, "GRANT", true);
        PaymentDecorator d = new PaymentDecorator(p, "GRANT", "WRONG");
        assertFalse(d.validate());
    }

    @Test
    public void testInvalidMethod() {
        Payment p = new Payment("P1", "R1", 50, "CASH", true);
        PaymentDecorator d = new PaymentDecorator(p, "CASH", "NONE");
        assertFalse(d.validate());
        assertNull(d.process());
    }

    @Test
    public void testGetters() {
        Payment p = new Payment("P1", "R1", 50, "CREDIT", true);
        PaymentDecorator d = new PaymentDecorator(p, "CREDIT", "12345678");
        assertEquals(p, d.getPayment());
        assertEquals("CREDIT", d.getPaymentMethod());
        assertEquals("12345678", d.getAccountReference());
    }
}

