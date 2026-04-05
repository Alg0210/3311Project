package org.example.manual.reservation;

import org.example.reservation.ReservationStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationStatusTest {

    @Test
    void testPendingExists() {
        assertNotNull(ReservationStatus.PENDING);
    }

    @Test
    void testConfirmedExists() {
        assertNotNull(ReservationStatus.CONFIRMED);
    }

    @Test
    void testCancelledExists() {
        assertNotNull(ReservationStatus.CANCELLED);
    }

    @Test
    void testCompletedExists() {
        assertNotNull(ReservationStatus.COMPLETED);
    }

    @Test
    void testExtendedExists() {
        assertNotNull(ReservationStatus.EXTENDED);
    }

    @Test
    void testValueOfPending() {
        assertEquals(ReservationStatus.PENDING, ReservationStatus.valueOf("PENDING"));
    }

    @Test
    void testValueOfConfirmed() {
        assertEquals(ReservationStatus.CONFIRMED, ReservationStatus.valueOf("CONFIRMED"));
    }

    @Test
    void testValueOfCancelled() {
        assertEquals(ReservationStatus.CANCELLED, ReservationStatus.valueOf("CANCELLED"));
    }

    @Test
    void testValueOfCompleted() {
        assertEquals(ReservationStatus.COMPLETED, ReservationStatus.valueOf("COMPLETED"));
    }

    @Test
    void testValueOfExtended() {
        assertEquals(ReservationStatus.EXTENDED, ReservationStatus.valueOf("EXTENDED"));
    }

    @Test
    void testInvalidValueThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ReservationStatus.valueOf("INVALID"));
    }

    @Test
    void testValuesReturnsAllStatuses() {
        ReservationStatus[] values = ReservationStatus.values();
        assertEquals(5, values.length);
    }
}
