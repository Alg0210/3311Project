package org.example.manual.payment;

import org.example.payment.PricingStrategy;
import org.example.payment.UserPricingStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PricingStrategyTest {

    @Test
    void testUserPricingStrategyImplementsInterface() {
        PricingStrategy strategy = new UserPricingStrategy("STUDENT");
        assertTrue(strategy instanceof PricingStrategy);
    }

    @Test
    void testGetHourlyRateStudentViaInterface() {
        PricingStrategy strategy = new UserPricingStrategy("STUDENT");
        assertEquals(10.0, strategy.getHourlyRate());
    }

    @Test
    void testGetHourlyRateFacultyViaInterface() {
        PricingStrategy strategy = new UserPricingStrategy("FACULTY");
        assertEquals(15.0, strategy.getHourlyRate());
    }

    @Test
    void testGetHourlyRateResearcherViaInterface() {
        PricingStrategy strategy = new UserPricingStrategy("RESEARCHER");
        assertEquals(20.0, strategy.getHourlyRate());
    }

    @Test
    void testGetHourlyRateGuestViaInterface() {
        PricingStrategy strategy = new UserPricingStrategy("GUEST");
        assertEquals(30.0, strategy.getHourlyRate());
    }

    @Test
    void testCalculateDepositEqualsHourlyRate() {
        PricingStrategy strategy = new UserPricingStrategy("STUDENT");
        assertEquals(strategy.getHourlyRate(), strategy.calculateDeposit());
    }

    @Test
    void testCalculateTotalViaInterface() {
        PricingStrategy strategy = new UserPricingStrategy("FACULTY");
        assertEquals(45.0, strategy.calculateTotal(3)); // $15 * 3
    }

    @Test
    void testDifferentStrategiesReturnDifferentRates() {
        PricingStrategy student = new UserPricingStrategy("STUDENT");
        PricingStrategy guest = new UserPricingStrategy("GUEST");
        assertNotEquals(student.getHourlyRate(), guest.getHourlyRate());
    }

    @Test
    void testInterfaceDeclaresGetHourlyRate() throws NoSuchMethodException {
        assertNotNull(PricingStrategy.class.getMethod("getHourlyRate"));
    }

    @Test
    void testInterfaceDeclaresCalculateDeposit() throws NoSuchMethodException {
        assertNotNull(PricingStrategy.class.getMethod("calculateDeposit"));
    }

    @Test
    void testInterfaceDeclaresCalculateTotal() throws NoSuchMethodException {
        assertNotNull(PricingStrategy.class.getMethod("calculateTotal", int.class));
    }

    @Test
    void testPolymorphicTotalCalculation() {
        PricingStrategy strategy = new UserPricingStrategy("STUDENT");
        assertEquals(50.0, strategy.calculateTotal(5));
    }
}
