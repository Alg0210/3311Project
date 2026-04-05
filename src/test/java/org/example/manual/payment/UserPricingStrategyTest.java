package org.example.manual.payment;

import org.example.payment.UserPricingStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserPricingStrategyTest {

    // ─── HOURLY RATE TESTS ───────────────────────────────────────

    @Test
    void testStudentHourlyRate() {
        assertEquals(10.0, new UserPricingStrategy("STUDENT").getHourlyRate());
    }

    @Test
    void testFacultyHourlyRate() {
        assertEquals(15.0, new UserPricingStrategy("FACULTY").getHourlyRate());
    }

    @Test
    void testResearcherHourlyRate() {
        assertEquals(20.0, new UserPricingStrategy("RESEARCHER").getHourlyRate());
    }

    @Test
    void testGuestHourlyRate() {
        assertEquals(30.0, new UserPricingStrategy("GUEST").getHourlyRate());
    }

    @Test
    void testUnknownUserTypeRate() {
        assertEquals(0.0, new UserPricingStrategy("UNKNOWN").getHourlyRate());
    }

    @Test
    void testCaseInsensitiveUserType() {
        assertEquals(10.0, new UserPricingStrategy("student").getHourlyRate());
        assertEquals(15.0, new UserPricingStrategy("Faculty").getHourlyRate());
    }

    // ─── DEPOSIT TESTS ──────────────────────────────────────────

    @Test
    void testStudentDeposit() {
        assertEquals(10.0, new UserPricingStrategy("STUDENT").calculateDeposit());
    }

    @Test
    void testFacultyDeposit() {
        assertEquals(15.0, new UserPricingStrategy("FACULTY").calculateDeposit());
    }

    @Test
    void testResearcherDeposit() {
        assertEquals(20.0, new UserPricingStrategy("RESEARCHER").calculateDeposit());
    }

    @Test
    void testGuestDeposit() {
        assertEquals(30.0, new UserPricingStrategy("GUEST").calculateDeposit());
    }

    // ─── TOTAL CALCULATION TESTS ─────────────────────────────────

    @Test
    void testStudentTotalThreeHours() {
        assertEquals(30.0, new UserPricingStrategy("STUDENT").calculateTotal(3));
    }

    @Test
    void testGuestTotalTwoHours() {
        assertEquals(60.0, new UserPricingStrategy("GUEST").calculateTotal(2));
    }

    @Test
    void testTotalZeroHours() {
        assertEquals(0.0, new UserPricingStrategy("STUDENT").calculateTotal(0));
        assertEquals(0.0, new UserPricingStrategy("GUEST").calculateTotal(0));
    }

    @Test
    void testDepositEqualsHourlyRateForAllTypes() {
        String[] types = {"STUDENT", "FACULTY", "RESEARCHER", "GUEST"};
        for (String type : types) {
            UserPricingStrategy strategy = new UserPricingStrategy(type);
            assertEquals(strategy.getHourlyRate(), strategy.calculateDeposit(),
                    "Deposit should equal hourly rate for " + type);
        }
    }
}
