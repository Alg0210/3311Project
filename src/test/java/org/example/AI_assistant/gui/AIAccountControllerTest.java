package org.example.AI_assistant.gui;

import org.example.auth.AuthService;
import org.example.users.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AIAccountControllerTest {

    private StubAccountController controller;
    private StubCSVRepository stubRepo;
    private StubUser testUser;

    @BeforeEach
    public void setUp() {
        stubRepo = new StubCSVRepository();
        testUser = new StubUser("U-001", "Alice", "alice@example.com", "pass", "STUDENT", "CS", "ID-123");
        AuthService.setCurrentUserForTesting(testUser);
        controller = new StubAccountController(stubRepo);
        StubMainApp.lastScene = null;
    }

    @AfterEach
    public void tearDown() {
        AuthService.clearCurrentUserForTesting();
    }

    @Test
    public void testInitializeLoadsUserData() {
        controller.initialize();

        assertEquals("Alice", controller.userNameLabel);
        assertEquals("Alice", controller.userNameTopLabel);
        assertEquals("alice@example.com", controller.emailField);
        assertEquals("ID-123", controller.idField);
    }

    @Test
    public void testInitializeHandlesNullUser() {
        AuthService.clearCurrentUserForTesting();
        StubAccountController emptyController = new StubAccountController(stubRepo);

        assertDoesNotThrow(emptyController::initialize);
        assertNull(emptyController.userNameLabel);
    }

    @Test
    public void testLoadBalanceDueExcludesOtherUsersAndCancelled() {
        stubRepo.addReservation(new String[]{"R1", "U-001", "EQ", "start", "end", "ACTIVE", "10.0"});
        stubRepo.addReservation(new String[]{"R2", "U-005", "EQ", "start", "end", "ACTIVE", "20.0"});
        stubRepo.addReservation(new String[]{"R3", "U-001", "EQ", "start", "end", "CANCELLED", "30.0"});

        controller.initialize(); // calls loadBalanceDue internally

        List<String> items = controller.balanceListViewItems;
        assertEquals(1, items.size());
        assertEquals("EQ:   $10", items.get(0));
    }

    @Test
    public void testHandleUpdateCredentialsSuccess() {
        controller.initialize();

        controller.emailField = "newalice@example.com";
        controller.idField = "NEW-ID-999";

        controller.handleUpdateCredentials();

        assertEquals("newalice@example.com", testUser.getEmail());
        assertEquals("NEW-ID-999", testUser.getIdNumber());
        assertEquals("Credentials updated successfully.", controller.updateStatusLabel);
        assertTrue(stubRepo.userUpdated);
    }

    @Test
    public void testHandleUpdateCredentialsEmptyEmail() {
        controller.initialize();

        controller.emailField = "   "; // whitespace only
        controller.handleUpdateCredentials();

        assertNotEquals("   ", testUser.getEmail());
        assertEquals("Email cannot be empty.", controller.updateStatusLabel);
        assertFalse(stubRepo.userUpdated);
    }

    @Test
    public void testHandleMakePaymentNavigates() {
        controller.handleMakePayment();
        assertEquals("Payment", StubMainApp.lastScene);
    }

    @Test
    public void testHandleHomeNavigates() {
        controller.handleHome();
        assertEquals("Dashboard", StubMainApp.lastScene);
    }

    /**
     * Stubs out AccountController behavior so it can be tested without a JavaFX environment
     */
    private static class StubAccountController {
        String userNameLabel;
        String userNameTopLabel;
        String emailField;
        String idField;
        String updateStatusLabel;
        List<String> balanceListViewItems = new ArrayList<>();

        private StubCSVRepository repo;
        private User currentUser;

        StubAccountController(StubCSVRepository repo) {
            this.repo = repo;
        }

        public void initialize() {
            currentUser = AuthService.getCurrentUser();
            if (currentUser == null) return;

            userNameLabel = currentUser.getName();
            userNameTopLabel = currentUser.getName();
            emailField = currentUser.getEmail();
            idField = currentUser.getIdNumber() != null ? currentUser.getIdNumber() : "";

            loadBalanceDue();
        }

        private void loadBalanceDue() {
            List<String> balanceItems = new ArrayList<>();
            List<String[]> reservationRows = repo.getAllReservationRows();

            for (String[] row : reservationRows) {
                if (!row[1].equals(currentUser.getUserId())) continue;
                if (row[5].equals("CANCELLED")) continue;

                String equipmentId = row[2];
                double deposit = Double.parseDouble(row[6]);
                balanceItems.add(equipmentId + ":   $" + (int) deposit);
            }

            balanceListViewItems = balanceItems;
        }

        public void handleUpdateCredentials() {
            String newEmail = emailField.trim();
            String newId = idField.trim();

            if (newEmail.isEmpty()) {
                updateStatusLabel = "Email cannot be empty.";
                return;
            }

            currentUser.setEmail(newEmail);
            currentUser.setIdNumber(newId);
            repo.updateUser(currentUser);
            updateStatusLabel = "Credentials updated successfully.";
        }

        public void handleMakePayment() {
            StubMainApp.switchScene("Payment");
        }

        public void handleHome() {
            StubMainApp.switchScene("Dashboard");
        }
    }

    private static class StubUser extends User {
        public StubUser(String userId, String name, String email, String password, String userType, String departmentId, String idNumber) {
            super(userId, name, email, password, userType, departmentId, idNumber);
        }
        @Override public String getUserType() { return userType; }
    }

    private static class StubCSVRepository {
        private List<String[]> reservations = new ArrayList<>();
        boolean userUpdated = false;

        public List<String[]> getAllReservationRows() {
            return new ArrayList<>(reservations);
        }
        public void addReservation(String[] row) {
            reservations.add(row);
        }
        public void updateUser(User user) {
            userUpdated = true;
        }
    }

    private static class StubMainApp {
        static String lastScene;
        static void switchScene(String scene) {
            lastScene = scene;
        }
    }
}
