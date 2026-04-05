package org.example.manual.reservation;

import org.example.equipment.Equipment;
import org.example.reservation.*;
import org.example.users.Guest;
import org.example.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationCommandTest {

    private User user;
    private Equipment equipment;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        user = new Guest("GUE-CMD-001", "Alice", "alice@cmd.com", "Pass123!", null, "ID001");
        equipment = new Equipment("EQ-CMD-001", "Microscope", "Lab A");
        reservation = new Reservation("RES-CMD-001", user, equipment,
                LocalDateTime.of(2027, 6, 1, 9, 0),
                LocalDateTime.of(2027, 6, 1, 11, 0), 30.0);
    }

    @Test
    void testReservationActionImplementsInterface() {
        CmdStubReservationManager mgr = new CmdStubReservationManager();
        ReservationAction action = new ReservationAction(mgr, reservation, "CREATE");
        assertTrue(action instanceof ReservationCommand);
    }

    @Test
    void testExecuteViaInterfaceCreate() {
        CmdStubReservationManager mgr = new CmdStubReservationManager();
        ReservationCommand cmd = new ReservationAction(mgr, reservation, "CREATE");
        cmd.execute();
        assertTrue(mgr.createCalled);
    }

    @Test
    void testExecuteViaInterfaceCancel() {
        CmdStubReservationManager mgr = new CmdStubReservationManager();
        ReservationCommand cmd = new ReservationAction(mgr, reservation, "CANCEL");
        cmd.execute();
        assertTrue(mgr.cancelCalled);
    }

    @Test
    void testExecuteViaInterfaceModify() {
        CmdStubReservationManager mgr = new CmdStubReservationManager();
        LocalDateTime newEnd = LocalDateTime.of(2027, 6, 1, 13, 0);
        ReservationCommand cmd = new ReservationAction(mgr, reservation, "MODIFY", newEnd);
        cmd.execute();
        assertTrue(mgr.modifyCalled);
    }

    @Test
    void testExecuteViaInterfaceExtend() {
        CmdStubReservationManager mgr = new CmdStubReservationManager();
        LocalDateTime newEnd = LocalDateTime.of(2027, 6, 1, 13, 0);
        ReservationCommand cmd = new ReservationAction(mgr, reservation, "EXTEND", newEnd);
        cmd.execute();
        assertTrue(mgr.extendCalled);
    }

    @Test
    void testUndoViaInterfaceCreate() {
        CmdStubReservationManager mgr = new CmdStubReservationManager();
        ReservationCommand cmd = new ReservationAction(mgr, reservation, "CREATE");
        cmd.execute();
        cmd.undo();
        assertTrue(mgr.cancelCalled);
    }

    @Test
    void testUndoViaInterfaceCancel() {
        CmdStubReservationManager mgr = new CmdStubReservationManager();
        ReservationCommand cmd = new ReservationAction(mgr, reservation, "CANCEL");
        cmd.execute();
        cmd.undo();
        assertTrue(mgr.updateCalled);
        assertEquals(ReservationStatus.CONFIRMED, reservation.getStatus());
    }

    @Test
    void testMultipleCommandsViaInterface() {
        CmdStubReservationManager mgr = new CmdStubReservationManager();
        ReservationCommand cmd1 = new ReservationAction(mgr, reservation, "CREATE");

        Reservation res2 = new Reservation("RES-CMD-002", user, equipment,
                LocalDateTime.of(2027, 6, 2, 9, 0),
                LocalDateTime.of(2027, 6, 2, 11, 0), 30.0);
        ReservationCommand cmd2 = new ReservationAction(mgr, res2, "CREATE");

        cmd1.execute();
        cmd2.execute();
        assertEquals(2, mgr.createCount);
    }

    @Test
    void testInterfaceHasExecuteMethod() throws NoSuchMethodException {
        assertNotNull(ReservationCommand.class.getMethod("execute"));
    }

    @Test
    void testInterfaceHasUndoMethod() throws NoSuchMethodException {
        assertNotNull(ReservationCommand.class.getMethod("undo"));
    }
}

/**
 * Stub ReservationManager that tracks method calls without file I/O.
 */
class CmdStubReservationManager extends ReservationManager {
    boolean createCalled = false;
    boolean cancelCalled = false;
    boolean modifyCalled = false;
    boolean extendCalled = false;
    boolean updateCalled = false;
    int createCount = 0;

    @Override
    public void createReservation(Reservation reservation) {
        createCalled = true;
        createCount++;
    }

    @Override
    public void cancelReservation(String id) {
        cancelCalled = true;
    }

    @Override
    public void modifyReservation(String id, java.time.LocalDateTime end) {
        modifyCalled = true;
    }

    @Override
    public void extendReservation(String id, java.time.LocalDateTime end) {
        extendCalled = true;
    }

    @Override
    public void updateReservation(Reservation reservation) {
        updateCalled = true;
    }
}
