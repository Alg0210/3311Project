package org.example.AI_assistant.payment;

import org.example.payment.Payment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    // ─── CREATION & GETTERS ─────────────────────────────────────

    @Test
    void testDepositPaymentCreation() {
        Payment p = new Payment("PAY-1", "RES-1", 10.0, "CREDIT", true);
        assertEquals("PAY-1", p.getPaymentId());
        assertEquals("RES-1", p.getReservationId());
        assertEquals(10.0, p.getAmount());
        assertEquals("CREDIT", p.getPaymentMethod());
        assertTrue(p.isDeposit());
        assertFalse(p.isForfeited());
        assertNotNull(p.getTimestamp());
    }

    @Test
    void testFinalPaymentCreation() {
        Payment p = new Payment("PAY-2", "RES-1", 50.0, "DEBIT", false);
        assertEquals("PAY-2", p.getPaymentId());
        assertEquals("RES-1", p.getReservationId());
        assertEquals(50.0, p.getAmount());
        assertEquals("DEBIT", p.getPaymentMethod());
        assertFalse(p.isDeposit());
        assertFalse(p.isForfeited());
    }

    // ─── DIFFERENT PAYMENT METHODS (Req10) ──────────────────────

    @Test
    void testCreditPayment() {
        Payment p = new Payment("PAY-1", "RES-1", 10.0, "CREDIT", true);
        assertEquals("CREDIT", p.getPaymentMethod());
    }

    @Test
    void testDebitPayment() {
        Payment p = new Payment("PAY-1", "RES-1", 15.0, "DEBIT", true);
        assertEquals("DEBIT", p.getPaymentMethod());
    }

    @Test
    void testInstitutionalPayment() {
        Payment p = new Payment("PAY-1", "RES-1", 20.0, "INSTITUTIONAL", true);
        assertEquals("INSTITUTIONAL", p.getPaymentMethod());
    }

    @Test
    void testGrantPayment() {
        Payment p = new Payment("PAY-1", "RES-1", 30.0, "GRANT", true);
        assertEquals("GRANT", p.getPaymentMethod());
    }

    // ─── DEPOSIT AMOUNTS PER USER TYPE (Req3/Req4) ──────────────

    @Test
    void testStudentDepositAmount() {
        Payment p = new Payment("PAY-1", "RES-1", 10.0, "CREDIT", true);
        assertEquals(10.0, p.getAmount());
        assertTrue(p.isDeposit());
    }

    @Test
    void testFacultyDepositAmount() {
        Payment p = new Payment("PAY-1", "RES-1", 15.0, "CREDIT", true);
        assertEquals(15.0, p.getAmount());
    }

    @Test
    void testResearcherDepositAmount() {
        Payment p = new Payment("PAY-1", "RES-1", 20.0, "CREDIT", true);
        assertEquals(20.0, p.getAmount());
    }

    @Test
    void testGuestDepositAmount() {
        Payment p = new Payment("PAY-1", "RES-1", 30.0, "CREDIT", true);
        assertEquals(30.0, p.getAmount());
    }

    // ─── FORFEITURE (Req4) ──────────────────────────────────────

    @Test
    void testForfeitDeposit() {
        Payment p = new Payment("PAY-1", "RES-1", 10.0, "CREDIT", true);
        assertFalse(p.isForfeited());
        p.setForfeited(true);
        assertTrue(p.isForfeited());
    }

    @Test
    void testUnforfeitDeposit() {
        Payment p = new Payment("PAY-1", "RES-1", 10.0, "CREDIT", true);
        p.setForfeited(true);
        assertTrue(p.isForfeited());
        p.setForfeited(false);
        assertFalse(p.isForfeited());
    }

    @Test
    void testForfeitedDefaultFalse() {
        Payment p = new Payment("PAY-1", "RES-1", 10.0, "CREDIT", true);
        assertFalse(p.isForfeited());
    }

    // ─── TIMESTAMP ──────────────────────────────────────────────

    @Test
    void testTimestampAutoSet() {
        Payment p = new Payment("PAY-1", "RES-1", 10.0, "CREDIT", true);
        assertNotNull(p.getTimestamp());
        assertTrue(p.getTimestamp().isBefore(LocalDateTime.now().plusSeconds(5)));
    }

    @Test
    void testSetTimestamp() {
        Payment p = new Payment("PAY-1", "RES-1", 10.0, "CREDIT", true);
        LocalDateTime customTime = LocalDateTime.of(2026, 5, 1, 14, 30);
        p.setTimestamp(customTime);
        assertEquals(customTime, p.getTimestamp());
    }

    // ─── EDGE CASES ──────────────────────────────────────────────

    @Test
    void testZeroAmountPayment() {
        Payment p = new Payment("PAY-1", "RES-1", 0.0, "CREDIT", false);
        assertEquals(0.0, p.getAmount());
    }

    @Test
    void testLargeAmountPayment() {
        Payment p = new Payment("PAY-1", "RES-1", 9999.99, "GRANT", false);
        assertEquals(9999.99, p.getAmount());
    }

    @Test
    void testMultiplePaymentsForSameReservation() {
        Payment deposit = new Payment("PAY-1", "RES-1", 10.0, "CREDIT", true);
        Payment finalPay = new Payment("PAY-2", "RES-1", 10.0, "CREDIT", false);

        assertEquals(deposit.getReservationId(), finalPay.getReservationId());
        assertTrue(deposit.isDeposit());
        assertFalse(finalPay.isDeposit());
    }
}

