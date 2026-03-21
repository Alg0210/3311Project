package org.example.reservation;

import org.example.data.CSVRepository;
import org.example.equipment.Equipment;
import org.example.equipment.EquipmentManager;
import org.example.equipment.EquipmentStatus;
import org.example.payment.Payment;
import org.example.payment.UserPricingStrategy;
import org.example.sensors.SensorObserver;
import org.example.users.User;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationManager implements SensorObserver {

    private CSVRepository repository;
    private EquipmentManager equipmentManager;

    public ReservationManager() {
        this.repository = new CSVRepository();
        this.equipmentManager = new EquipmentManager();
    }

    // ─── CORE RESERVATION METHODS ────────────────────────────────

    public Reservation createReservation(User user, Equipment equipment,
                                         LocalDateTime start, LocalDateTime end) {
        // check equipment is available
        if (equipment.getStatus() != EquipmentStatus.AVAILABLE) {
            System.out.println("Equipment is not available.");
            return null;
        }

        // check no overlapping reservations
        if (!isEquipmentAvailable(equipment.getEquipmentId(), start, end)) {
            System.out.println("Equipment already booked for this time slot.");
            return null;
        }

        // calculate deposit using pricing strategy
        UserPricingStrategy strategy = new UserPricingStrategy(user.getUserType());
        double deposit = strategy.calculateDeposit();

        // create reservation
        String reservationId = "RES-" + System.currentTimeMillis();
        Reservation reservation = new Reservation(reservationId, user, equipment, start, end, deposit);
        reservation.setStatus(ReservationStatus.CONFIRMED);

        // save to CSV
        repository.saveReservation(reservation);
        return reservation;
    }

    public void createReservation(Reservation reservation) {
        repository.saveReservation(reservation);
    }

    public void cancelReservation(String reservationId) {
        Reservation reservation = getReservationById(reservationId);
        if (reservation != null && reservation.getStartTime().isAfter(LocalDateTime.now())) {
            reservation.setStatus(ReservationStatus.CANCELLED);
            repository.updateReservation(reservation);
        } else {
            System.out.println("Cannot cancel — reservation has already started.");
        }
    }

    public void modifyReservation(String reservationId, LocalDateTime newEndTime) {
        Reservation reservation = getReservationById(reservationId);
        if (reservation != null && reservation.getStartTime().isAfter(LocalDateTime.now())) {
            reservation.setEndTime(newEndTime);
            repository.updateReservation(reservation);
        } else {
            System.out.println("Cannot modify — reservation has already started.");
        }
    }

    public void extendReservation(String reservationId, LocalDateTime newEndTime) {
        Reservation reservation = getReservationById(reservationId);
        if (reservation == null) return;

        // check equipment is free after current end time
        if (!isEquipmentAvailable(reservation.getEquipment().getEquipmentId(),
                reservation.getEndTime(), newEndTime)) {
            System.out.println("Cannot extend — equipment is booked after current end time.");
            return;
        }

        reservation.setEndTime(newEndTime);
        reservation.setStatus(ReservationStatus.EXTENDED);
        repository.updateReservation(reservation);
    }

    public void updateReservation(Reservation reservation) {
        repository.updateReservation(reservation);
    }

    // ─── PAYMENT HANDLING ────────────────────────────────────────

    public Payment processDeposit(Reservation reservation, String paymentMethod,
                                  String accountReference) {
        UserPricingStrategy strategy = new UserPricingStrategy(
                reservation.getUser().getUserType());
        double depositAmount = strategy.calculateDeposit();

        Payment payment = new Payment(
                "PAY-" + System.currentTimeMillis(),
                reservation.getReservationId(),
                depositAmount,
                paymentMethod,
                true
        );
        repository.savePayment(payment);
        return payment;
    }

    public Payment processFinalPayment(Reservation reservation, String paymentMethod,
                                       String accountReference, boolean arrivedOnTime) {
        UserPricingStrategy strategy = new UserPricingStrategy(
                reservation.getUser().getUserType());
        double total = strategy.calculateTotal((int) reservation.getDurationHours());

        if (arrivedOnTime) {
            // deduct deposit from total
            total = total - reservation.getDeposit();
        } else {
            // deposit is forfeited, still pay full amount
            System.out.println("Deposit forfeited due to late arrival.");
        }

        Payment payment = new Payment(
                "PAY-" + System.currentTimeMillis(),
                reservation.getReservationId(),
                total,
                paymentMethod,
                false
        );
        repository.savePayment(payment);
        return payment;
    }

    // ─── AVAILABILITY CHECK ──────────────────────────────────────

    public boolean isEquipmentAvailable(String equipmentId,
                                        LocalDateTime start, LocalDateTime end) {
        List<String[]> rows = repository.getAllReservationRows();
        for (String[] row : rows) {
            if (!row[2].equals(equipmentId)) continue;
            if (row[5].equals(ReservationStatus.CANCELLED.name())) continue;

            LocalDateTime existingStart = LocalDateTime.parse(row[3]);
            LocalDateTime existingEnd   = LocalDateTime.parse(row[4]);

            // check for overlap
            boolean overlaps = start.isBefore(existingEnd) && end.isAfter(existingStart);
            if (overlaps) return false;
        }
        return true;
    }

    // ─── GETTERS ─────────────────────────────────────────────────

    public Reservation getReservationById(String reservationId) {
        List<String[]> rows = repository.getAllReservationRows();
        for (String[] row : rows) {
            if (row[0].equals(reservationId)) {
                return mapRowToReservation(row);
            }
        }
        return null;
    }

    public List<String[]> getAllReservationRows() {
        return repository.getAllReservationRows();
    }

    // ─── OBSERVER ────────────────────────────────────────────────

    @Override
    public void update(String equipmentId, String status) {
        // if sensor marks equipment as unavailable mid booking, cancel active reservations
        if (status.equalsIgnoreCase("MAINTENANCE") || status.equalsIgnoreCase("DISABLED")) {
            List<String[]> rows = repository.getAllReservationRows();
            for (String[] row : rows) {
                if (row[2].equals(equipmentId) &&
                        row[5].equals(ReservationStatus.CONFIRMED.name())) {
                    cancelReservation(row[0]);
                    System.out.println("Reservation " + row[0] +
                            " cancelled due to equipment status change.");
                }
            }
        }
    }

    // ─── PRIVATE HELPERS ─────────────────────────────────────────

    private Reservation mapRowToReservation(String[] row) {
        // row: reservationId, userId, equipmentId, startTime, endTime, status, deposit
        Equipment equipment = equipmentManager.getEquipmentById(row[2]);
        LocalDateTime start = LocalDateTime.parse(row[3]);
        LocalDateTime end   = LocalDateTime.parse(row[4]);
        double deposit      = Double.parseDouble(row[6]);
        User user = repository.findUserById(row[1]);

        // note: we pass null for user here for simplicity
        // in a full implementation you would look up the user by userId via AuthService
        Reservation r = new Reservation(row[0], user, equipment, start, end, deposit);
        r.setStatus(ReservationStatus.valueOf(row[5]));
        return r;
    }
}
