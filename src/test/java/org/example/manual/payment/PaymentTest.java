package org.example.manual.payment;

import org.example.payment.Payment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    @Test
    public void testPaymentCreationAndGetters() {
        Payment payment = new Payment("PAY-1", "RES-1", 100.0, "CREDIT", true);
        
        assertEquals("PAY-1", payment.getPaymentId());
        assertEquals("RES-1", payment.getReservationId());
        assertEquals(100.0, payment.getAmount());
        assertEquals("CREDIT", payment.getPaymentMethod());
        assertTrue(payment.isDeposit());
        assertFalse(payment.isForfeited());
        assertNotNull(payment.getTimestamp());
    }

    @Test
    public void testSetters() {
        Payment payment = new Payment("PAY-1", "RES-1", 100.0, "CREDIT", true);
        payment.setForfeited(true);
        assertTrue(payment.isForfeited());
        
        LocalDateTime now = LocalDateTime.now().plusDays(1);
        payment.setTimestamp(now);
        assertEquals(now, payment.getTimestamp());
    }
}

