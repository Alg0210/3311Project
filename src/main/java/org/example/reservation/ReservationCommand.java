package org.example.reservation;

public interface ReservationCommand {
    void execute();
    void undo();
}
