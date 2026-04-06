package org.example.AI_assistant.reservation;

import org.example.equipment.Equipment;
import org.example.reservation.Reservation;
import org.example.reservation.ReservationAction;
import org.example.reservation.ReservationManager;
import org.example.reservation.ReservationStatus;
import org.example.users.Student;
import org.example.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationActionTest {

    private ReservationAction action;
    private ReservationManager manager;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        manager = new ReservationManager() {
            @Override
            public void createReservation(Reservation res) {
                res.setStatus(ReservationStatus.CONFIRMED);
            }

            @Override
            public void cancelReservation(String id) {
                if (reservation.getReservationId().equals(id)) {
                    reservation.setStatus(ReservationStatus.CANCELLED);
                }
            }

            @Override
            public void modifyReservation(String id, LocalDateTime newEnd) {
                if (reservation.getReservationId().equals(id)) {
                    reservation.setEndTime(newEnd);
                }
            }

            @Override
            public void extendReservation(String id, LocalDateTime newEnd) {
                if (reservation.getReservationId().equals(id)) {
                    reservation.setEndTime(newEnd);
                    reservation.setStatus(ReservationStatus.EXTENDED);
                }
            }

            @Override
            public void updateReservation(Reservation res) {
                // stub for undo
            }
        };

        User user = new Student("U1", "Alice", "alice@uni.edu", "Pass1!", "CS", "STU-001");
        Equipment eq = new Equipment("E1", "Microscope", "Lab-A");
        LocalDateTime start = LocalDateTime.of(2026, 6, 1, 10, 0);
        LocalDateTime end = start.plusHours(2);
        reservation = new Reservation("R1", user, eq, start, end, 10.0);
    }

    @Test
    void testExecuteCreate() {
        action = new ReservationAction(manager, reservation, "CREATE");
        action.execute();
        assertEquals(ReservationStatus.CONFIRMED, reservation.getStatus());
    }

    @Test
    void testExecuteCancel() {
        action = new ReservationAction(manager, reservation, "CANCEL");
        action.execute();
        assertEquals(ReservationStatus.CANCELLED, reservation.getStatus());
    }

    @Test
    void testExecuteModify() {
        LocalDateTime newEnd = LocalDateTime.of(2026, 6, 1, 15, 0);
        action = new ReservationAction(manager, reservation, "MODIFY", newEnd);
        action.execute();
        assertEquals(newEnd, reservation.getEndTime());
    }

    @Test
    void testExecuteExtend() {
        LocalDateTime newEnd = LocalDateTime.of(2026, 6, 1, 16, 0);
        action = new ReservationAction(manager, reservation, "EXTEND", newEnd);
        action.execute();
        assertEquals(newEnd, reservation.getEndTime());
        assertEquals(ReservationStatus.EXTENDED, reservation.getStatus());
    }

    @Test
    void testExecuteUnknownAction() {
        action = new ReservationAction(manager, reservation, "UNKNOWN");
        action.execute();
        // status unchanged
        assertEquals(ReservationStatus.PENDING, reservation.getStatus());
    }

    @Test
    void testExecuteCreateCaseInsensitive() {
        action = new ReservationAction(manager, reservation, "create");
        action.execute();
        assertEquals(ReservationStatus.CONFIRMED, reservation.getStatus());
    }

    @Test
    void testExecuteCancelCaseInsensitive() {
        action = new ReservationAction(manager, reservation, "cancel");
        action.execute();
        assertEquals(ReservationStatus.CANCELLED, reservation.getStatus());
    }

    @Test
    void testUndoCreate() {
        action = new ReservationAction(manager, reservation, "CREATE");
        action.execute();
        assertEquals(ReservationStatus.CONFIRMED, reservation.getStatus());
        action.undo();
        assertEquals(ReservationStatus.CANCELLED, reservation.getStatus());
    }

    @Test
    void testUndoCancel() {
        action = new ReservationAction(manager, reservation, "CANCEL");
        action.execute();
        assertEquals(ReservationStatus.CANCELLED, reservation.getStatus());
        action.undo();
        assertEquals(ReservationStatus.CONFIRMED, reservation.getStatus());
    }

    @Test
    void testUndoModify() {
        LocalDateTime originalEnd = reservation.getEndTime();
        action = new ReservationAction(manager, reservation, "MODIFY", LocalDateTime.of(2026, 6, 1, 17, 0));
        action.undo();
        assertEquals(originalEnd, reservation.getEndTime());
    }

    @Test
    void testUndoExtend() {
        LocalDateTime originalEnd = reservation.getEndTime();
        action = new ReservationAction(manager, reservation, "EXTEND", LocalDateTime.of(2026, 6, 1, 18, 0));
        action.undo();
        assertEquals(originalEnd, reservation.getEndTime());
    }

    @Test
    void testCreateThenCancel() {
        ReservationAction createAction = new ReservationAction(manager, reservation, "CREATE");
        createAction.execute();
        assertEquals(ReservationStatus.CONFIRMED, reservation.getStatus());

        ReservationAction cancelAction = new ReservationAction(manager, reservation, "CANCEL");
        cancelAction.execute();
        assertEquals(ReservationStatus.CANCELLED, reservation.getStatus());
    }

    @Test
    void testCreateThenExtend() {
        ReservationAction createAction = new ReservationAction(manager, reservation, "CREATE");
        createAction.execute();
        assertEquals(ReservationStatus.CONFIRMED, reservation.getStatus());

        LocalDateTime newEnd = LocalDateTime.of(2026, 6, 1, 16, 0);
        ReservationAction extendAction = new ReservationAction(manager, reservation, "EXTEND", newEnd);
        extendAction.execute();
        assertEquals(newEnd, reservation.getEndTime());
        assertEquals(ReservationStatus.EXTENDED, reservation.getStatus());
    }

    @Test
    void testCreateUndoRestoresCancelled() {
        ReservationAction createAction = new ReservationAction(manager, reservation, "CREATE");
        createAction.execute();
        assertEquals(ReservationStatus.CONFIRMED, reservation.getStatus());

        createAction.undo();
        assertEquals(ReservationStatus.CANCELLED, reservation.getStatus());
    }
}

