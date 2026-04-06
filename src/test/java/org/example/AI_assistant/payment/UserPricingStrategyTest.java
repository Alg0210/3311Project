package org.example.AI_assistant.payment;

import org.example.payment.PricingStrategy;
import org.example.payment.UserPricingStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserPricingStrategyTest {

    @Test
    void testStudentHourlyRate() {
        UserPricingStrategy strategy = new UserPricingStrategy("STUDENT");
        assertEquals(10.0, strategy.getHourlyRate());
    }

    @Test
    void testStudentDeposit() {
        UserPricingStrategy strategy = new UserPricingStrategy("STUDENT");
        assertEquals(10.0, strategy.calculateDeposit());
    }

    @Test
    void testStudentTotal1Hour() {
        UserPricingStrategy strategy = new UserPricingStrategy("STUDENT");
        assertEquals(10.0, strategy.calculateTotal(1));
    }

    @Test
    void testStudentTotal2Hours() {
        UserPricingStrategy strategy = new UserPricingStrategy("STUDENT");
        assertEquals(20.0, strategy.calculateTotal(2));
    }

    @Test
    void testStudentTotal5Hours() {
        UserPricingStrategy strategy = new UserPricingStrategy("STUDENT");
        assertEquals(50.0, strategy.calculateTotal(5));
    }

    @Test
    void testFacultyHourlyRate() {
        UserPricingStrategy strategy = new UserPricingStrategy("FACULTY");
        assertEquals(15.0, strategy.getHourlyRate());
    }

    @Test
    void testFacultyDeposit() {
        UserPricingStrategy strategy = new UserPricingStrategy("FACULTY");
        assertEquals(15.0, strategy.calculateDeposit());
    }

    @Test
    void testFacultyTotal1Hour() {
        UserPricingStrategy strategy = new UserPricingStrategy("FACULTY");
        assertEquals(15.0, strategy.calculateTotal(1));
    }

    @Test
    void testFacultyTotal3Hours() {
        UserPricingStrategy strategy = new UserPricingStrategy("FACULTY");
        assertEquals(45.0, strategy.calculateTotal(3));
    }

    @Test
    void testResearcherHourlyRate() {
        UserPricingStrategy strategy = new UserPricingStrategy("RESEARCHER");
        assertEquals(20.0, strategy.getHourlyRate());
    }

    @Test
    void testResearcherDeposit() {
        UserPricingStrategy strategy = new UserPricingStrategy("RESEARCHER");
        assertEquals(20.0, strategy.calculateDeposit());
    }

    @Test
    void testResearcherTotal1Hour() {
        UserPricingStrategy strategy = new UserPricingStrategy("RESEARCHER");
        assertEquals(20.0, strategy.calculateTotal(1));
    }

    @Test
    void testResearcherTotal4Hours() {
        UserPricingStrategy strategy = new UserPricingStrategy("RESEARCHER");
        assertEquals(80.0, strategy.calculateTotal(4));
    }

    @Test
    void testGuestHourlyRate() {
        UserPricingStrategy strategy = new UserPricingStrategy("GUEST");
        assertEquals(30.0, strategy.getHourlyRate());
    }

    @Test
    void testGuestDeposit() {
        UserPricingStrategy strategy = new UserPricingStrategy("GUEST");
        assertEquals(30.0, strategy.calculateDeposit());
    }

    @Test
    void testGuestTotal1Hour() {
        UserPricingStrategy strategy = new UserPricingStrategy("GUEST");
        assertEquals(30.0, strategy.calculateTotal(1));
    }

    @Test
    void testGuestTotal5Hours() {
        UserPricingStrategy strategy = new UserPricingStrategy("GUEST");
        assertEquals(150.0, strategy.calculateTotal(5));
    }

    @Test
    void testUnknownUserTypeHourlyRate() {
        UserPricingStrategy strategy = new UserPricingStrategy("UNKNOWN");
        assertEquals(0.0, strategy.getHourlyRate());
    }

    @Test
    void testUnknownUserTypeDeposit() {
        UserPricingStrategy strategy = new UserPricingStrategy("UNKNOWN");
        assertEquals(0.0, strategy.calculateDeposit());
    }

    @Test
    void testUnknownUserTypeTotal() {
        UserPricingStrategy strategy = new UserPricingStrategy("UNKNOWN");
        assertEquals(0.0, strategy.calculateTotal(5));
    }

    @Test
    void testLowercaseStudentResolvesCorrectly() {
        UserPricingStrategy strategy = new UserPricingStrategy("student");
        assertEquals(10.0, strategy.getHourlyRate());
    }

    @Test
    void testMixedCaseFacultyResolvesCorrectly() {
        UserPricingStrategy strategy = new UserPricingStrategy("Faculty");
        assertEquals(15.0, strategy.getHourlyRate());
    }

    @Test
    void testLowercaseGuestResolvesCorrectly() {
        UserPricingStrategy strategy = new UserPricingStrategy("guest");
        assertEquals(30.0, strategy.getHourlyRate());
    }

    @Test
    void testZeroHours() {
        UserPricingStrategy strategy = new UserPricingStrategy("STUDENT");
        assertEquals(0.0, strategy.calculateTotal(0));
    }

    @Test
    void testLargeNumberOfHours() {
        UserPricingStrategy strategy = new UserPricingStrategy("GUEST");
        assertEquals(3000.0, strategy.calculateTotal(100));
    }

    @Test
    void testDepositEqualsOneHourRate() {
        for (String type : new String[]{"STUDENT", "FACULTY", "RESEARCHER", "GUEST"}) {
            UserPricingStrategy strategy = new UserPricingStrategy(type);
            assertEquals(strategy.getHourlyRate(), strategy.calculateDeposit(),
                    "Deposit should equal one hour's fee for " + type);
        }
    }

    @Test
    void testTotalEqualsHourlyRateTimesHours() {
        for (String type : new String[]{"STUDENT", "FACULTY", "RESEARCHER", "GUEST"}) {
            UserPricingStrategy strategy = new UserPricingStrategy(type);
            int hours = 3;
            assertEquals(strategy.getHourlyRate() * hours, strategy.calculateTotal(hours),
                    "Total should equal hourlyRate * hours for " + type);
        }
    }

    @Test
    void testImplementsPricingStrategy() {
        UserPricingStrategy strategy = new UserPricingStrategy("STUDENT");
        assertInstanceOf(PricingStrategy.class, strategy);
    }

    @Test
    void testStudentCheaperThanFaculty() {
        UserPricingStrategy student = new UserPricingStrategy("STUDENT");
        UserPricingStrategy faculty = new UserPricingStrategy("FACULTY");
        assertTrue(student.getHourlyRate() < faculty.getHourlyRate());
    }

    @Test
    void testFacultyCheaperThanResearcher() {
        UserPricingStrategy faculty = new UserPricingStrategy("FACULTY");
        UserPricingStrategy researcher = new UserPricingStrategy("RESEARCHER");
        assertTrue(faculty.getHourlyRate() < researcher.getHourlyRate());
    }

    @Test
    void testResearcherCheaperThanGuest() {
        UserPricingStrategy researcher = new UserPricingStrategy("RESEARCHER");
        UserPricingStrategy guest = new UserPricingStrategy("GUEST");
        assertTrue(researcher.getHourlyRate() < guest.getHourlyRate());
    }
}

