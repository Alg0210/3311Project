package org.example.reservation;
import org.example.equipment.Equipment;
import org.example.users.User;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
public class Reservation {
    private String reservationId;
    private User user;
    private Equipment equipment;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ReservationStatus status;
    private double deposit;

    public Reservation(String reservationId, User user, Equipment equipment,
                       LocalDateTime startTime, LocalDateTime endTime, double deposit) {
        this.reservationId = reservationId;
        this.user = user;
        this.equipment = equipment;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = ReservationStatus.PENDING;
        this.deposit = deposit;
    }

    public long getDurationHours() {
        return ChronoUnit.HOURS.between(startTime, endTime);
    }

    public double calculateTotal(double hourlyRate) {
        return hourlyRate * getDurationHours();
    }

    // checks if user arrived within 20 minute window per Req4
    public boolean arrivedOnTime(LocalDateTime arrivalTime) {
        LocalDateTime cutoff = startTime.plusMinutes(20);
        return !arrivalTime.isAfter(cutoff);
    }

    public String getReservationId() { return reservationId; }
    public User getUser() { return user; }
    public Equipment getEquipment() { return equipment; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public ReservationStatus getStatus() { return status; }
    public double getDeposit() { return deposit; }
    public void setStatus(ReservationStatus status) { this.status = status; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

}
