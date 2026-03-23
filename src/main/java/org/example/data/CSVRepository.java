package org.example.data;

import org.example.users.*;
import org.example.equipment.*;
import org.example.reservation.*;
import org.example.payment.*;

import java.util.*;


public class CSVRepository {

    // File paths
    private static final String USERS_FILE        = "src/main/resources/data/users.csv";
    private static final String EQUIPMENT_FILE     = "src/main/resources/data/equipment.csv";
    private static final String RESERVATIONS_FILE  = "src/main/resources/data/reservations.csv";
    private static final String PAYMENTS_FILE      = "src/main/resources/data/payments.csv";

    // Headers
    private static final String USERS_HEADER        = "userId,name,email,password,userType,departmentId,idNumber,approved,decorationType";
    private static final String EQUIPMENT_HEADER    = "equipmentId,description,labLocation,status,name,availableUnits,productStatistics,tags,imagePath";
    private static final String RESERVATIONS_HEADER = "reservationId,userId,equipmentId,startTime,endTime,status,deposit";
    private static final String PAYMENTS_HEADER     = "paymentId,reservationId,amount,paymentMethod,isDeposit,timestamp";

    private final CSVHandler handler;

    public CSVRepository() {
        this.handler = CSVHandler.getInstance();
        // Initialize all CSV files if they don't exist
        handler.initCSV(USERS_FILE, USERS_HEADER);
        handler.initCSV(EQUIPMENT_FILE, EQUIPMENT_HEADER);
        handler.initCSV(RESERVATIONS_FILE, RESERVATIONS_HEADER);
        handler.initCSV(PAYMENTS_FILE, PAYMENTS_HEADER);
    }

    // ─── USER METHODS ────────────────────────────────────────────

    public void saveUser(User user) {
        String[] row = {
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getUserType(),
                user.getDepartmentId() != null ? user.getDepartmentId() : "",
                user.getIdNumber() != null ? user.getIdNumber() : "",
                "false",
                ""
        };
        handler.appendCSV(USERS_FILE, row);
    }

    public User findUserByEmail(String email) {
        List<String[]> rows = handler.readCSV(USERS_FILE);
        for (String[] row : rows) {
            if (row[2].equalsIgnoreCase(email)) {
                return mapRowToUser(row);
            }
        }
        return null;
    }

    public User findUserById(String userId) {
        List<String[]> rows = handler.readCSV(USERS_FILE);
        for (String[] row : rows) {
            if (row[0].equals(userId)) {
                return mapRowToUser(row);
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        for (String[] row : handler.readCSV(USERS_FILE)) {
            users.add(mapRowToUser(row));
        }
        return users;
    }

    public void updateUser(User user) {
        List<String[]> rows = handler.readCSV(USERS_FILE);
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i)[0].equals(user.getUserId())) {
                rows.set(i, new String[]{
                        user.getUserId(),
                        user.getName(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getUserType(),
                        user.getDepartmentId() != null ? user.getDepartmentId() : "",
                        user.getIdNumber() != null ? user.getIdNumber() : "",
                        "false",
                        ""
                });
                break;
            }
        }
        handler.writeCSV(USERS_FILE, USERS_HEADER, rows);
    }

    private User mapRowToUser(String[] row) {
        String userId       = row[0];
        String name         = row[1];
        String email        = row[2];
        String password     = row[3];
        String userType     = row[4];
        String departmentId = row[5];
        String idNumber     = row[6];

        return UserFactory.createUser(userType, userId, name, email, password, departmentId, idNumber);
    }

    // ─── EQUIPMENT METHODS ───────────────────────────────────────

    public void saveEquipment(Equipment equipment) {
        String tagsJoined = String.join("|", equipment.getTags());
        String[] row = {
                equipment.getEquipmentId(),
                equipment.getDescription(),
                equipment.getLabLocation(),
                equipment.getStatus().name(),
                equipment.getName(),
                String.valueOf(equipment.getAvailableUnits()),
                equipment.getProductStatistics().replace("\n", "\\n"),
                tagsJoined,
                equipment.getImagePath() != null ? equipment.getImagePath() : ""
        };
        handler.appendCSV(EQUIPMENT_FILE, row);
    }

    public List<Equipment> getAllEquipment() {
        List<Equipment> list = new ArrayList<>();
        for (String[] row : handler.readCSV(EQUIPMENT_FILE)) {
            Equipment e = new Equipment(row[0], row[1], row[2]);
            e.setStatus(EquipmentStatus.valueOf(row[3]));
            if (row.length > 4 && !row[4].isEmpty()) e.setName(row[4]);
            if (row.length > 5 && !row[5].isEmpty()) {
                try { e.setAvailableUnits(Integer.parseInt(row[5])); } catch (NumberFormatException ignored) {}
            }
            if (row.length > 6 && !row[6].isEmpty()) e.setProductStatistics(row[6].replace("\\n", "\n"));
            if (row.length > 7 && !row[7].isEmpty()) {
                java.util.Arrays.stream(row[7].split("\\|"))
                        .filter(t -> !t.trim().isEmpty())
                        .forEach(e.getTags()::add);
            }
            if (row.length > 8 && !row[8].isEmpty()) e.setImagePath(row[8]);
            list.add(e);
        }
        return list;
    }

    public void updateEquipment(Equipment equipment) {
        List<String[]> rows = handler.readCSV(EQUIPMENT_FILE);
        String tagsJoined = String.join("|", equipment.getTags());
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i)[0].equals(equipment.getEquipmentId())) {
                rows.set(i, new String[]{
                        equipment.getEquipmentId(),
                        equipment.getDescription(),
                        equipment.getLabLocation(),
                        equipment.getStatus().name(),
                        equipment.getName(),
                        String.valueOf(equipment.getAvailableUnits()),
                        equipment.getProductStatistics().replace("\n", "\\n"),
                        tagsJoined,
                        equipment.getImagePath() != null ? equipment.getImagePath() : ""
                });
                break;
            }
        }
        handler.writeCSV(EQUIPMENT_FILE, EQUIPMENT_HEADER, rows);
    }

    // ─── RESERVATION METHODS ─────────────────────────────────────

    public void saveReservation(Reservation reservation) {
        String[] row = {
                reservation.getReservationId(),
                reservation.getUser().getUserId(),
                reservation.getEquipment().getEquipmentId(),
                reservation.getStartTime().toString(),
                reservation.getEndTime().toString(),
                reservation.getStatus().name(),
                String.valueOf(reservation.getDeposit())
        };
        handler.appendCSV(RESERVATIONS_FILE, row);
    }

    public List<String[]> getAllReservationRows() {
        return handler.readCSV(RESERVATIONS_FILE);
    }

    public void updateReservation(Reservation reservation) {
        List<String[]> rows = handler.readCSV(RESERVATIONS_FILE);
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i)[0].equals(reservation.getReservationId())) {
                rows.set(i, new String[]{
                        reservation.getReservationId(),
                        reservation.getUser().getUserId(),
                        reservation.getEquipment().getEquipmentId(),
                        reservation.getStartTime().toString(),
                        reservation.getEndTime().toString(),
                        reservation.getStatus().name(),
                        String.valueOf(reservation.getDeposit())
                });
                break;
            }
        }
        handler.writeCSV(RESERVATIONS_FILE, RESERVATIONS_HEADER, rows);
    }

    // ─── PAYMENT METHODS ─────────────────────────────────────────

    public void savePayment(Payment payment) {
        String[] row = {
                payment.getPaymentId(),
                payment.getReservationId(),
                String.valueOf(payment.getAmount()),
                payment.getPaymentMethod(),
                String.valueOf(payment.isDeposit()),
                payment.getTimestamp().toString()
        };
        handler.appendCSV(PAYMENTS_FILE, row);
    }

    public List<String[]> getAllPaymentRows() {
        return handler.readCSV(PAYMENTS_FILE);
    }
}

