package org.example.reservation;

import java.time.LocalDateTime;

public class ReservationAction implements ReservationCommand {

    private ReservationManager manager;
    private Reservation reservation;
    private String actionType; // "CREATE", "MODIFY", "CANCEL", "EXTEND"
    private LocalDateTime newEndTime;

    // for CREATE, CANCEL
    public ReservationAction(ReservationManager manager, Reservation reservation, String actionType) {
        this.manager = manager;
        this.reservation = reservation;
        this.actionType = actionType;
    }

    // for MODIFY, EXTEND
    public ReservationAction(ReservationManager manager, Reservation reservation,
                             String actionType, LocalDateTime newEndTime) {
        this(manager, reservation, actionType);
        this.newEndTime = newEndTime;
    }

    @Override
    public void execute() {
        switch (actionType.toUpperCase()) {
            case "CREATE":
                manager.createReservation(reservation);
                break;
            case "CANCEL":
                manager.cancelReservation(reservation.getReservationId());
                break;
            case "MODIFY":
                manager.modifyReservation(reservation.getReservationId(), newEndTime);
                break;
            case "EXTEND":
                manager.extendReservation(reservation.getReservationId(), newEndTime);
                break;
            default:
                System.out.println("Unknown action: " + actionType);
        }
    }

    @Override
    public void undo() {
        // reverse the action
        switch (actionType.toUpperCase()) {
            case "CREATE":
                manager.cancelReservation(reservation.getReservationId());
                break;
            case "CANCEL":
                reservation.setStatus(ReservationStatus.CONFIRMED);
                manager.updateReservation(reservation);
                break;
            case "MODIFY":
            case "EXTEND":
                reservation.setEndTime(reservation.getEndTime());
                manager.updateReservation(reservation);
                break;
        }
    }

}
