package org.example.AI_assistant.reservation;

import org.example.equipment.Equipment;
import org.example.reservation.Reservation;
import org.example.reservation.ReservationStatus;
import org.example.users.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationTest {

    private User studentUser;
    private User facultyUser;
    private User researcherUser;
    private User guestUser;
    private Equipment equipment;
    private LocalDateTime start;
    private LocalDateTime end;

    @BeforeEach
    void setUp() {
        studentUser = new Student("U1", "Alice", "alice@uni.edu", "Pass1!", "CS", "STU-001");
        facultyUser = new Faculty("U2", "Bob", "bob@uni.edu", "Pass2!", "CS", "FAC-001");
        researcherUser = new Researcher("U3", "Carol", "carol@uni.edu", "Pass3!", "PHYS", "RES-001");
        guestUser = new Guest("U4", "Dave", "dave@ext.com", "Pass4!", "", "GUEST-001");

        equipment = new Equipment("E1", "Microscope", "Lab-A");
        start = LocalDateTime.of(2026, 5, 1, 10, 0);
        end = LocalDateTime.of(2026, 5, 1, 12, 0);
    }

    // ─── INITIALIZATION ──────────────────────────────────────────

    @Test
    void testReservationInitialization() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, end, 10.0);
        assertEquals("R1", res.getReservationId());
        assertEquals(studentUser, res.getUser());
        assertEquals(equipment, res.getEquipment());
        assertEquals(start, res.getStartTime());
        assertEquals(end, res.getEndTime());
        assertEquals(ReservationStatus.PENDING, res.getStatus());
        assertEquals(10.0, res.getDeposit());
    }

    @Test
    void testInitialStatusIsPending() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, end, 10.0);
        assertEquals(ReservationStatus.PENDING, res.getStatus());
    }

    @Test
    void testReservationWithDifferentUserTypes() {
        Reservation rStudent = new Reservation("R1", studentUser, equipment, start, end, 10.0);
        assertEquals("STUDENT", rStudent.getUser().getUserType());

        Reservation rFaculty = new Reservation("R2", facultyUser, equipment, start, end, 15.0);
        assertEquals("FACULTY", rFaculty.getUser().getUserType());

        Reservation rResearcher = new Reservation("R3", researcherUser, equipment, start, end, 20.0);
        assertEquals("RESEARCHER", rResearcher.getUser().getUserType());

        Reservation rGuest = new Reservation("R4", guestUser, equipment, start, end, 30.0);
        assertEquals("GUEST", rGuest.getUser().getUserType());
    }

    @Test
    void testReservationWithZeroDeposit() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, end, 0.0);
        assertEquals(0.0, res.getDeposit());
    }

    // ─── DURATION CALCULATION ────────────────────────────────────

    @Test
    void testGetDurationHoursTwoHours() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, end, 10.0);
        assertEquals(2, res.getDurationHours());
    }

    @Test
    void testGetDurationHoursOneHour() {
        LocalDateTime endOneHour = start.plusHours(1);
        Reservation res = new Reservation("R1", studentUser, equipment, start, endOneHour, 10.0);
        assertEquals(1, res.getDurationHours());
    }

    @Test
    void testGetDurationHoursLongBooking() {
        LocalDateTime endEightHours = start.plusHours(8);
        Reservation res = new Reservation("R1", studentUser, equipment, start, endEightHours, 10.0);
        assertEquals(8, res.getDurationHours());
    }

    @Test
    void testGetDurationHoursZero() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, start, 10.0);
        assertEquals(0, res.getDurationHours());
    }

    // ─── TOTAL CALCULATION PER PRICING TIER ──────────────────────

    @Test
    void testCalculateTotalStudentRate() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, end, 10.0);
        // 2 hours * $10/hr = $20
        assertEquals(20.0, res.calculateTotal(10.0));
    }

    @Test
    void testCalculateTotalFacultyRate() {
        Reservation res = new Reservation("R1", facultyUser, equipment, start, end, 15.0);
        // 2 hours * $15/hr = $30
        assertEquals(30.0, res.calculateTotal(15.0));
    }

    @Test
    void testCalculateTotalResearcherRate() {
        Reservation res = new Reservation("R1", researcherUser, equipment, start, end, 20.0);
        // 2 hours * $20/hr = $40
        assertEquals(40.0, res.calculateTotal(20.0));
    }

    @Test
    void testCalculateTotalGuestRate() {
        Reservation res = new Reservation("R1", guestUser, equipment, start, end, 30.0);
        // 2 hours * $30/hr = $60
        assertEquals(60.0, res.calculateTotal(30.0));
    }

    @Test
    void testCalculateTotalZeroDuration() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, start, 10.0);
        assertEquals(0.0, res.calculateTotal(10.0));
    }

    @Test
    void testCalculateTotalLongBooking() {
        LocalDateTime endTenHours = start.plusHours(10);
        Reservation res = new Reservation("R1", studentUser, equipment, start, endTenHours, 10.0);
        // 10 hours * $10/hr = $100
        assertEquals(100.0, res.calculateTotal(10.0));
    }

    // ─── ARRIVAL TIME CHECK (Req4: 20-minute window) ────────────

    @Test
    void testArrivedOnTimeExactStart() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, end, 10.0);
        assertTrue(res.arrivedOnTime(start));
    }

    @Test
    void testArrivedOnTimeWithin20Minutes() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, end, 10.0);
        assertTrue(res.arrivedOnTime(start.plusMinutes(10)));
    }

    @Test
    void testArrivedOnTimeExactly20Minutes() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, end, 10.0);
        assertTrue(res.arrivedOnTime(start.plusMinutes(20)));
    }

    @Test
    void testArrivedLateAt21Minutes() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, end, 10.0);
        assertFalse(res.arrivedOnTime(start.plusMinutes(21)));
    }

    @Test
    void testArrivedLateAt30Minutes() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, end, 10.0);
        assertFalse(res.arrivedOnTime(start.plusMinutes(30)));
    }

    @Test
    void testArrivedLateAt1Hour() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, end, 10.0);
        assertFalse(res.arrivedOnTime(start.plusHours(1)));
    }

    @Test
    void testArrivedEarlyBeforeStart() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, end, 10.0);
        assertTrue(res.arrivedOnTime(start.minusMinutes(5)));
    }

    @Test
    void testArrivedAt19Minutes() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, end, 10.0);
        assertTrue(res.arrivedOnTime(start.plusMinutes(19)));
    }

    // ─── STATUS TRANSITIONS ─────────────────────────────────────

    @Test
    void testStatusTransitionPendingToConfirmed() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, end, 10.0);
        assertEquals(ReservationStatus.PENDING, res.getStatus());
        res.setStatus(ReservationStatus.CONFIRMED);
        assertEquals(ReservationStatus.CONFIRMED, res.getStatus());
    }

    @Test
    void testStatusTransitionConfirmedToCancelled() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, end, 10.0);
        res.setStatus(ReservationStatus.CONFIRMED);
        res.setStatus(ReservationStatus.CANCELLED);
        assertEquals(ReservationStatus.CANCELLED, res.getStatus());
    }

    @Test
    void testStatusTransitionConfirmedToCompleted() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, end, 10.0);
        res.setStatus(ReservationStatus.CONFIRMED);
        res.setStatus(ReservationStatus.COMPLETED);
        assertEquals(ReservationStatus.COMPLETED, res.getStatus());
    }

    @Test
    void testStatusTransitionConfirmedToExtended() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, end, 10.0);
        res.setStatus(ReservationStatus.CONFIRMED);
        res.setStatus(ReservationStatus.EXTENDED);
        assertEquals(ReservationStatus.EXTENDED, res.getStatus());
    }

    @Test
    void testAllStatusValues() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, end, 10.0);
        for (ReservationStatus status : ReservationStatus.values()) {
            res.setStatus(status);
            assertEquals(status, res.getStatus());
        }
    }

    // ─── SETTERS ─────────────────────────────────────────────────

    @Test
    void testSetStartTime() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, end, 10.0);
        LocalDateTime newStart = start.plusHours(1);
        res.setStartTime(newStart);
        assertEquals(newStart, res.getStartTime());
    }

    @Test
    void testSetEndTime() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, end, 10.0);
        LocalDateTime newEnd = end.plusHours(2);
        res.setEndTime(newEnd);
        assertEquals(newEnd, res.getEndTime());
    }

    @Test
    void testModifyTimesUpdatesDuration() {
        Reservation res = new Reservation("R1", studentUser, equipment, start, end, 10.0);
        assertEquals(2, res.getDurationHours());
        res.setEndTime(start.plusHours(5));
        assertEquals(5, res.getDurationHours());
    }

    // ─── EDGE CASES ──────────────────────────────────────────────

    @Test
    void testReservationSpanningMidnight() {
        LocalDateTime eveningStart = LocalDateTime.of(2026, 5, 1, 22, 0);
        LocalDateTime morningEnd = LocalDateTime.of(2026, 5, 2, 2, 0);
        Reservation res = new Reservation("R1", studentUser, equipment, eveningStart, morningEnd, 10.0);
        assertEquals(4, res.getDurationHours());
        assertEquals(40.0, res.calculateTotal(10.0));
    }

    @Test
    void testReservationOnWeekend() {
        LocalDateTime satStart = LocalDateTime.of(2026, 5, 2, 10, 0);
        LocalDateTime satEnd = LocalDateTime.of(2026, 5, 2, 14, 0);
        Reservation res = new Reservation("R1", guestUser, equipment, satStart, satEnd, 30.0);
        assertEquals(4, res.getDurationHours());
        // Guest: 4 hours * $30 = $120
        assertEquals(120.0, res.calculateTotal(30.0));
    }

    @Test
    void testReservationWithDifferentEquipment() {
        Equipment eq2 = new Equipment("E2", "Oscilloscope", "Lab-B");
        Reservation res = new Reservation("R1", studentUser, eq2, start, end, 10.0);
        assertEquals("E2", res.getEquipment().getEquipmentId());
        assertEquals("Lab-B", res.getEquipment().getLabLocation());
    }
}

