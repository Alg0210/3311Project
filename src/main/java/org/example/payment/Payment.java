package org.example.payment;

import java.time.LocalDateTime;

public class Payment {

    private String paymentId;
    private String reservationId;
    private double amount;
    private String paymentMethod;
    private boolean isDeposit;
    private boolean forfeited;
    private LocalDateTime timestamp;

    public Payment(String paymentId, String reservationId, double amount,
                   String paymentMethod, boolean isDeposit) {
        this.paymentId = paymentId;
        this.reservationId = reservationId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.isDeposit = isDeposit;
        this.forfeited = false;
        this.timestamp = LocalDateTime.now();
    }

    public String getPaymentId() { return paymentId; }
    public String getReservationId() { return reservationId; }
    public double getAmount() { return amount; }
    public String getPaymentMethod() { return paymentMethod; }
    public boolean isDeposit() { return isDeposit; }
    public boolean isForfeited() { return forfeited; }
    public void setForfeited(boolean forfeited) { this.forfeited = forfeited; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
