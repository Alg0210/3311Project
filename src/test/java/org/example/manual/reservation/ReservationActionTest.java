package org.example.manual.reservation;

import org.example.equipment.Equipment;
import org.example.reservation.*;
import org.example.users.Guest;
import org.example.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationActionTest {

    private ActTrackingReservationManager manager;
    private User user;
    private Equipment equipment;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        manager = new ActTrackingReservationManager();
        user = new Guest("GUE-ACT-001", "Alice", "alice@act.com", "Pass123!", null, "ID001");
        equipment = new Equipment("EQ-ACT-001", "Microscope", "Lab A");
        reservation = new Reservation("RES-ACT-001", user, equipment,
                LocalDateTime.of(2027, 6, 1, 9, 0),
                LocalDateTime.of(2027, 6, 1, 11, 0), 30.0);
    }

    // ─── EXECUTE TESTS ───────────────────────────────────────────

    @Test
    void testCreateActionExecute() {
        ReservationAction action = new ReservationAction(manager, reservation, "CREATE");
        action.execute();
        assertTrue(manager.createReservationCalled);
    }

    @Test
    void testCancelActionExecute() {
        ReservationAction action = new ReservationAction(manager, reservation, "CANCEL");
        action.execute();
        assertTrue(manager.cancelReservationCalled);
        assertEquals("RES-ACT-001", manager.lastCancelledId);
    }

    @Test
    void testModifyActionExecute() {
        LocalDateTime newEnd = LocalDateTime.of(2027, 6, 1, 14, 0);
        ReservationAction action = new ReservationAction(manager, reservation, "MODIFY", newEnd);
        action.execute();
        assertTrue(manager.modifyReservationCalled);
        assertEquals("RES-ACT-001", manager.lastModifiedId);
        assertEquals(newEnd, manager.lastModifiedEndTime);
    }

    @Test
    void testExtendActionExecute() {
        LocalDateTime newEnd = LocalDateTime.of(2027, 6, 1, 14, 0);
        ReservationAction action = new ReservationAction(manager, reservation, "EXTEND", newEnd);
        action.execute();
        assertTrue(manager.extendReservationCalled);
    }

    @Test
    void testUnknownActionDoesNotThrow() {
        ReservationAction action = new ReservationAction(manager, reservation, "UNKNOWN");
        assertDoesNotThrow(() -> action.execute());
    }

    @Test
    void testUnknownActionCallsNothing() {
        ReservationAction action = new ReservationAction(manager, reservation, "UNKNOWN");
        action.execute();
        assertFalse(manager.createReservationCalled);
        assertFalse(manager.cancelReservationCalled);
        assertFalse(manager.modifyReservationCalled);
        assertFalse(manager.extendReservationCalled);
    }

    @Test
    void testCaseInsensitiveAction() {
        ReservationAction action = new ReservationAction(manager, reservation, "create");
        action.execute();
        assertTrue(manager.createReservationCalled);
    }

    // ─── UNDO TESTS ─────────────────────────────────────────────

    @Test
    void testUndoCreate() {
        ReservationAction action = new ReservationAction(manager, reservation, "CREATE");
        action.execute();
        action.undo();
        assertTrue(manager.cancelReservationCalled);
        assertEquals("RES-ACT-001", manager.lastCancelledId);
    }

    @Test
    void testUndoCancel() {
        ReservationAction action = new ReservationAction(manager, reservation, "CANCEL");
        action.execute();
        action.undo();
        assertEquals(ReservationStatus.CONFIRMED, reservation.getStatus());
        assertTrue(manager.updateReservationCalled);
    }

    @Test
    void testUndoModify() {
        LocalDateTime newEnd = LocalDateTime.of(2027, 6, 1, 14, 0);
        ReservationAction action = new ReservationAction(manager, reservation, "MODIFY", newEnd);
        action.execute();
        action.undo();
        assertTrue(manager.updateReservationCalled);
    }

    @Test
    void testUndoExtend() {
        LocalDateTime newEnd = LocalDateTime.of(2027, 6, 1, 14, 0);
        ReservationAction action = new ReservationAction(manager, reservation, "EXTEND", newEnd);
        action.execute();
        action.undo();
        assertTrue(manager.updateReservationCalled);
    }

    @Test
    void testThreeParamConstructor() {
        ReservationAction action = new ReservationAction(manager, reservation, "CREATE");
        assertDoesNotThrow(() -> action.execute());
        assertTrue(manager.createReservationCalled);
    }

    @Test
    void testFourParamConstructor() {
        LocalDateTime newEnd = LocalDateTime.of(2027, 6, 1, 15, 0);
        ReservationAction action = new ReservationAction(manager, reservation, "MODIFY", newEnd);
        action.execute();
        assertEquals(newEnd, manager.lastModifiedEndTime);
    }
}

/**
 * Tracking stub for ReservationManager that records which methods were called.
 */
class ActTrackingReservationManager extends ReservationManager {

    boolean createReservationCalled = false;
    boolean cancelReservationCalled = false;
    boolean modifyReservationCalled = false;
    boolean extendReservationCalled = false;
    boolean updateReservationCalled = false;

    String lastCancelledId = null;
    String lastModifiedId = null;
    LocalDateTime lastModifiedEndTime = null;

    @Override
    public void createReservation(Reservation reservation) {
        createReservationCalled = true;
    }

    @Override
    public void cancelReservation(String reservationId) {
        cancelReservationCalled = true;
        lastCancelledId = reservationId;
    }

    @Override
    public void modifyReservation(String reservationId, LocalDateTime newEndTime) {
        modifyReservationCalled = true;
        lastModifiedId = reservationId;
        lastModifiedEndTime = newEndTime;
    }

    @Override
    public void extendReservation(String reservationId, LocalDateTime newEndTime) {
        extendReservationCalled = true;
    }

    @Override
    public void updateReservation(Reservation reservation) {
        updateReservationCalled = true;
    }
}
