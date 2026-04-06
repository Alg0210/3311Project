package org.example.manual.reservation;

import org.example.equipment.Equipment;
import org.example.reservation.Reservation;
import org.example.reservation.ReservationAction;
import org.example.reservation.ReservationManager;
import org.example.reservation.ReservationStatus;
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
        };

        User user = new User("U1", "T", "E", "P", "STUDENT", "D", "ID") {
            public String getUserType() { return "STUDENT"; }
        };
        Equipment eq = new Equipment("E1", "Desc", "Lab1");
        reservation = new Reservation("R1", user, eq, LocalDateTime.now(), LocalDateTime.now().plusHours(1), 10.0);
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
        LocalDateTime newEnd = LocalDateTime.now().plusHours(3);
        action = new ReservationAction(manager, reservation, "MODIFY", newEnd);
        action.execute();
        assertEquals(newEnd, reservation.getEndTime());
    }

    @Test
    void testExecuteExtend() {
        LocalDateTime newEnd = LocalDateTime.now().plusHours(4);
        action = new ReservationAction(manager, reservation, "EXTEND", newEnd);
        action.execute();
        assertEquals(newEnd, reservation.getEndTime());
        assertEquals(ReservationStatus.EXTENDED, reservation.getStatus());
    }

    @Test
    void testUndoCreate() {
        action = new ReservationAction(manager, reservation, "CREATE");
        action.undo();
        assertEquals(ReservationStatus.CANCELLED, reservation.getStatus());
    }

    @Test
    void testUndoCancel() {
        action = new ReservationAction(manager, reservation, "CANCEL");
        action.undo();
        assertEquals(ReservationStatus.CONFIRMED, reservation.getStatus());
    }

    @Test
    void testUndoModify() {
        LocalDateTime originalEnd = reservation.getEndTime();
        action = new ReservationAction(manager, reservation, "MODIFY", LocalDateTime.now().plusHours(5));
        action.undo();
        assertEquals(originalEnd, reservation.getEndTime());
    }
}

