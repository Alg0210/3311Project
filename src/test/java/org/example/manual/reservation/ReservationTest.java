package org.example.manual.reservation;

import org.example.equipment.Equipment;
import org.example.reservation.Reservation;
import org.example.reservation.ReservationStatus;
import org.example.users.Guest;
import org.example.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationTest {

    private User user;
    private Equipment equipment;
    private LocalDateTime start;
    private LocalDateTime end;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        user = new Guest("GUE-001", "Alice", "alice@test.com", "Pass123!", null, "ID001");
        equipment = new Equipment("EQ-001", "Microscope", "Lab A");
        start = LocalDateTime.of(2027, 6, 1, 9, 0);
        end = LocalDateTime.of(2027, 6, 1, 11, 0);
        reservation = new Reservation("RES-001", user, equipment, start, end, 30.0);
    }

    // ─── CONSTRUCTOR / GETTER TESTS ──────────────────────────────

    @Test
    void testConstructorSetsReservationId() {
        assertEquals("RES-001", reservation.getReservationId());
    }

    @Test
    void testConstructorSetsUser() {
        assertEquals(user, reservation.getUser());
    }

    @Test
    void testConstructorSetsEquipment() {
        assertEquals(equipment, reservation.getEquipment());
    }

    @Test
    void testConstructorSetsStartTime() {
        assertEquals(start, reservation.getStartTime());
    }

    @Test
    void testConstructorSetsEndTime() {
        assertEquals(end, reservation.getEndTime());
    }

    @Test
    void testConstructorSetsDeposit() {
        assertEquals(30.0, reservation.getDeposit());
    }

    @Test
    void testDefaultStatusIsPending() {
        assertEquals(ReservationStatus.PENDING, reservation.getStatus());
    }

    // ─── getDurationHours TESTS ──────────────────────────────────

    @Test
    void testGetDurationHoursTwoHours() {
        assertEquals(2, reservation.getDurationHours());
    }

    @Test
    void testGetDurationHoursOneHour() {
        Reservation oneHour = new Reservation("RES-002", user, equipment,
                LocalDateTime.of(2027, 6, 1, 9, 0),
                LocalDateTime.of(2027, 6, 1, 10, 0), 30.0);
        assertEquals(1, oneHour.getDurationHours());
    }

    @Test
    void testGetDurationHoursEightHours() {
        Reservation eightHours = new Reservation("RES-003", user, equipment,
                LocalDateTime.of(2027, 6, 1, 8, 0),
                LocalDateTime.of(2027, 6, 1, 16, 0), 30.0);
        assertEquals(8, eightHours.getDurationHours());
    }

    @Test
    void testGetDurationHoursZero() {
        LocalDateTime same = LocalDateTime.of(2027, 6, 1, 9, 0);
        Reservation zeroHour = new Reservation("RES-004", user, equipment, same, same, 30.0);
        assertEquals(0, zeroHour.getDurationHours());
    }

    // ─── calculateTotal TESTS ────────────────────────────────────

    @Test
    void testCalculateTotalGuestRate() {
        // 2 hours * $30/hr = $60
        assertEquals(60.0, reservation.calculateTotal(30.0));
    }

    @Test
    void testCalculateTotalStudentRate() {
        // 2 hours * $10/hr = $20
        assertEquals(20.0, reservation.calculateTotal(10.0));
    }

    @Test
    void testCalculateTotalZeroRate() {
        assertEquals(0.0, reservation.calculateTotal(0.0));
    }

    // ─── arrivedOnTime TESTS ─────────────────────────────────────

    @Test
    void testArrivedOnTimeExactlyAtStart() {
        assertTrue(reservation.arrivedOnTime(start));
    }

    @Test
    void testArrivedOnTimeWithin10Minutes() {
        assertTrue(reservation.arrivedOnTime(start.plusMinutes(10)));
    }

    @Test
    void testArrivedOnTimeExactly20Minutes() {
        assertTrue(reservation.arrivedOnTime(start.plusMinutes(20)));
    }

    @Test
    void testArrivedLateAt21Minutes() {
        assertFalse(reservation.arrivedOnTime(start.plusMinutes(21)));
    }

    @Test
    void testArrivedLateOneHour() {
        assertFalse(reservation.arrivedOnTime(start.plusHours(1)));
    }

    @Test
    void testArrivedEarlyBeforeStart() {
        assertTrue(reservation.arrivedOnTime(start.minusMinutes(5)));
    }

    // ─── SETTER TESTS ────────────────────────────────────────────

    @Test
    void testSetStatus() {
        reservation.setStatus(ReservationStatus.CONFIRMED);
        assertEquals(ReservationStatus.CONFIRMED, reservation.getStatus());
    }

    @Test
    void testSetStartTime() {
        LocalDateTime newStart = LocalDateTime.of(2027, 7, 1, 10, 0);
        reservation.setStartTime(newStart);
        assertEquals(newStart, reservation.getStartTime());
    }

    @Test
    void testSetEndTime() {
        LocalDateTime newEnd = LocalDateTime.of(2027, 7, 1, 14, 0);
        reservation.setEndTime(newEnd);
        assertEquals(newEnd, reservation.getEndTime());
    }
}
