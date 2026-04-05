package org.example.manual.payment;

import org.example.payment.UserPricingStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserPricingStrategyTest {

    @Test
    public void testStudentPricing() {
        UserPricingStrategy strategy = new UserPricingStrategy("STUDENT");
        assertEquals(10.0, strategy.getHourlyRate());
        assertEquals(10.0, strategy.calculateDeposit());
        assertEquals(20.0, strategy.calculateTotal(2));
    }

    @Test
    public void testFacultyPricing() {
        UserPricingStrategy strategy = new UserPricingStrategy("FACULTY");
        assertEquals(15.0, strategy.getHourlyRate());
        assertEquals(15.0, strategy.calculateDeposit());
        assertEquals(45.0, strategy.calculateTotal(3));
    }

    @Test
    public void testResearcherPricing() {
        UserPricingStrategy strategy = new UserPricingStrategy("RESEARCHER");
        assertEquals(20.0, strategy.getHourlyRate());
        assertEquals(20.0, strategy.calculateDeposit());
        assertEquals(80.0, strategy.calculateTotal(4));
    }

    @Test
    public void testGuestPricing() {
        UserPricingStrategy strategy = new UserPricingStrategy("GUEST");
        assertEquals(30.0, strategy.getHourlyRate());
        assertEquals(30.0, strategy.calculateDeposit());
        assertEquals(150.0, strategy.calculateTotal(5));
    }

    @Test
    public void testUnknownPricing() {
        UserPricingStrategy strategy = new UserPricingStrategy("UNKNOWN");
        assertEquals(0.0, strategy.getHourlyRate());
        assertEquals(0.0, strategy.calculateDeposit());
        assertEquals(0.0, strategy.calculateTotal(5));
    }
}

