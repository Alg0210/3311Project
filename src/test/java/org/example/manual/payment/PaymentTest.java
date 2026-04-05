package org.example.manual.payment;

import org.example.payment.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment("PAY-001", "RES-001", 50.0, "CREDIT", true);
    }

    // ─── CONSTRUCTOR / GETTER TESTS ──────────────────────────────

    @Test
    void testGetPaymentId() {
        assertEquals("PAY-001", payment.getPaymentId());
    }

    @Test
    void testGetReservationId() {
        assertEquals("RES-001", payment.getReservationId());
    }

    @Test
    void testGetAmount() {
        assertEquals(50.0, payment.getAmount());
    }

    @Test
    void testGetPaymentMethod() {
        assertEquals("CREDIT", payment.getPaymentMethod());
    }

    @Test
    void testIsDepositTrue() {
        assertTrue(payment.isDeposit());
    }

    @Test
    void testIsDepositFalse() {
        Payment nonDeposit = new Payment("PAY-002", "RES-002", 100.0, "DEBIT", false);
        assertFalse(nonDeposit.isDeposit());
    }

    // ─── FORFEITED TESTS ─────────────────────────────────────────

    @Test
    void testDefaultForfeitedIsFalse() {
        assertFalse(payment.isForfeited());
    }

    @Test
    void testSetForfeitedTrue() {
        payment.setForfeited(true);
        assertTrue(payment.isForfeited());
    }

    @Test
    void testSetForfeitedBackToFalse() {
        payment.setForfeited(true);
        payment.setForfeited(false);
        assertFalse(payment.isForfeited());
    }

    // ─── TIMESTAMP TESTS ─────────────────────────────────────────

    @Test
    void testTimestampIsSetOnCreation() {
        assertNotNull(payment.getTimestamp());
    }

    @Test
    void testSetTimestamp() {
        LocalDateTime newTime = LocalDateTime.of(2027, 1, 1, 12, 0);
        payment.setTimestamp(newTime);
        assertEquals(newTime, payment.getTimestamp());
    }

    @Test
    void testTimestampIsRecentOnCreation() {
        LocalDateTime before = LocalDateTime.now().minusSeconds(5);
        Payment fresh = new Payment("PAY-TIME", "RES-TIME", 10.0, "CASH", false);
        LocalDateTime after = LocalDateTime.now().plusSeconds(5);
        assertTrue(fresh.getTimestamp().isAfter(before) || fresh.getTimestamp().isEqual(before));
        assertTrue(fresh.getTimestamp().isBefore(after) || fresh.getTimestamp().isEqual(after));
    }

    // ─── EDGE CASE TESTS ─────────────────────────────────────────

    @Test
    void testZeroAmountPayment() {
        Payment zero = new Payment("PAY-Z", "RES-Z", 0.0, "CREDIT", false);
        assertEquals(0.0, zero.getAmount());
    }

    @Test
    void testLargeAmountPayment() {
        Payment large = new Payment("PAY-L", "RES-L", 99999.99, "GRANT", false);
        assertEquals(99999.99, large.getAmount());
    }
}
