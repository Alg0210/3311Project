package org.example.manual.reservation;

import org.example.equipment.Equipment;
import org.example.reservation.Reservation;
import org.example.reservation.ReservationStatus;
import org.example.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationTest {

    private Reservation reservation;
    private User testUser;
    private Equipment testEquipment;
    private LocalDateTime start;
    private LocalDateTime end;

    @BeforeEach
    void setUp() {
        testUser = new User("U1", "Test", "test@test.com", "pass", "STUDENT", "D1", "123") {
            @Override
            public String getUserType() {
                return "STUDENT";
            }
        };
        testEquipment = new Equipment("E1", "Desc", "Lab1");
        start = LocalDateTime.now();
        end = start.plusHours(2);
        reservation = new Reservation("R1", testUser, testEquipment, start, end, 15.0);
    }

    @Test
    void testReservationInitialization() {
        assertEquals("R1", reservation.getReservationId());
        assertEquals(testUser, reservation.getUser());
        assertEquals(testEquipment, reservation.getEquipment());
        assertEquals(start, reservation.getStartTime());
        assertEquals(end, reservation.getEndTime());
        assertEquals(ReservationStatus.PENDING, reservation.getStatus());
        assertEquals(15.0, reservation.getDeposit());
    }

    @Test
    void testGetDurationHours() {
        assertEquals(2, reservation.getDurationHours());
    }

    @Test
    void testCalculateTotal() {
        double hourlyRate = 10.0;
        assertEquals(20.0, reservation.calculateTotal(hourlyRate));
    }

    @Test
    void testArrivedOnTime() {
        LocalDateTime arrivalTimeValid = start.plusMinutes(15);
        assertTrue(reservation.arrivedOnTime(arrivalTimeValid));

        LocalDateTime arrivalTimeExact = start.plusMinutes(20);
        assertTrue(reservation.arrivedOnTime(arrivalTimeExact));

        LocalDateTime arrivalTimeLate = start.plusMinutes(21);
        assertFalse(reservation.arrivedOnTime(arrivalTimeLate));
    }

    @Test
    void testSetters() {
        reservation.setStatus(ReservationStatus.CONFIRMED);
        assertEquals(ReservationStatus.CONFIRMED, reservation.getStatus());

        LocalDateTime newStart = start.plusHours(1);
        reservation.setStartTime(newStart);
        assertEquals(newStart, reservation.getStartTime());

        LocalDateTime newEnd = end.plusHours(1);
        reservation.setEndTime(newEnd);
        assertEquals(newEnd, reservation.getEndTime());
    }
}

