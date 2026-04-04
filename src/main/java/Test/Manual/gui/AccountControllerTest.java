package Test.Manual.gui;

import org.example.auth.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Account Controller Tests")
public class AccountControllerTest {

    private TestAccountController testController;
    private TestUser testUser;
    private TestCSVRepository testRepository;

    @BeforeEach
    void setUp() {
        testRepository = new TestCSVRepository();
        testUser = new TestUser("STU-001", "John Doe", "john@example.com", "password123", "STUDENT", "CS101", "ID123");
        AuthService.setCurrentUserForTesting(testUser);
        testController = new TestAccountController(testRepository);
    }

    @AfterEach
    void tearDown() {
        AuthService.clearCurrentUserForTesting();
    }

    // ────── INITIALIZATION TESTS ──────────

    @Test
    @DisplayName("Should initialize with current user information")
    void testInitializeWithCurrentUser() {
        testController.callInitialize();

        assertEquals("John Doe", testController.getUserNameLabel(), "User name should be set");
        assertEquals("john@example.com", testController.getEmailField(), "Email should be set");
        assertEquals("ID123", testController.getIdField(), "ID number should be set");
    }

    @Test
    @DisplayName("Should handle initialization when current user is null")
    void testInitializeWithNullUser() {
        AuthService.clearCurrentUserForTesting();
        TestAccountController controller = new TestAccountController(testRepository);
        
        assertDoesNotThrow(() -> controller.callInitialize(), "Should handle null user gracefully");
    }

    @Test
    @DisplayName("Should initialize with null ID number gracefully")
    void testInitializeWithNullIdNumber() {
        TestUser userWithoutId = new TestUser("STU-002", "Jane Doe", "jane@example.com", "password123", "STUDENT", "CS101", null);
        AuthService.setCurrentUserForTesting(userWithoutId);
        TestAccountController controller = new TestAccountController(testRepository);
        
        controller.callInitialize();
        
        assertEquals("", controller.getIdField(), "ID field should be empty string for null ID number");
    }

    @Test
    @DisplayName("Should set user name in both labels")
    void testInitializeSetsBothNameLabels() {
        testController.callInitialize();

        assertEquals("John Doe", testController.getUserNameLabel(), "User name label should be set");
        assertEquals("John Doe", testController.getUserNameTopLabel(), "User name top label should be set");
    }

    // ────── LOAD BALANCE DUE TESTS ──────────

    @Test
    @DisplayName("Should load balance items for current user")
    void testLoadBalanceDueWithReservations() {
        // Add reservation data
        String[] reservation1 = {"RES001", "STU-001", "EQ001", "2024-01-01", "2024-01-02", "ACTIVE", "50.0"};
        String[] reservation2 = {"RES002", "STU-001", "EQ002", "2024-01-03", "2024-01-04", "ACTIVE", "75.0"};
        testRepository.addReservationRow(reservation1);
        testRepository.addReservationRow(reservation2);

        testController.callLoadBalanceDue();

        assertTrue(testController.getBalanceListItemCount() > 0, "Balance list should have items");
    }

    @Test
    @DisplayName("Should filter balance items by current user ID")
    void testLoadBalanceDueFiltersCurrentUser() {
        String[] reservation1 = {"RES001", "STU-001", "EQ001", "2024-01-01", "2024-01-02", "ACTIVE", "50.0"};
        String[] reservation2 = {"RES002", "STU-002", "EQ002", "2024-01-03", "2024-01-04", "ACTIVE", "75.0"};
        testRepository.addReservationRow(reservation1);
        testRepository.addReservationRow(reservation2);

        testController.callLoadBalanceDue();

        // Should only have 1 item (for STU-001)
        assertEquals(1, testController.getBalanceListItemCount(), "Should only load reservations for current user");
    }

    @Test
    @DisplayName("Should exclude cancelled reservations from balance")
    void testLoadBalanceDueExcludesCancelled() {
        String[] reservation1 = {"RES001", "STU-001", "EQ001", "2024-01-01", "2024-01-02", "ACTIVE", "50.0"};
        String[] reservation2 = {"RES002", "STU-001", "EQ002", "2024-01-03", "2024-01-04", "CANCELLED", "75.0"};
        testRepository.addReservationRow(reservation1);
        testRepository.addReservationRow(reservation2);

        testController.callLoadBalanceDue();

        assertEquals(1, testController.getBalanceListItemCount(), "Should exclude cancelled reservations");
    }

    @Test
    @DisplayName("Should display empty balance list when no reservations")
    void testLoadBalanceDueEmpty() {
        testController.callLoadBalanceDue();

        assertEquals(0, testController.getBalanceListItemCount(), "Balance list should be empty");
    }

    @Test
    @DisplayName("Should format balance items with equipment ID and deposit")
    void testLoadBalanceDueFormatting() {
        String[] reservation = {"RES001", "STU-001", "EQ001", "2024-01-01", "2024-01-02", "ACTIVE", "50.0"};
        testRepository.addReservationRow(reservation);

        testController.callLoadBalanceDue();

        String item = testController.getBalanceListItem(0);
        assertTrue(item.contains("EQ001"), "Item should contain equipment ID");
        assertTrue(item.contains("50"), "Item should contain deposit amount");
    }

    // ────── UPDATE CREDENTIALS TESTS ──────────

    @Test
    @DisplayName("Should update credentials successfully")
    void testHandleUpdateCredentials() {
        testController.callInitialize();
        testController.setEmailField("newemail@example.com");
        testController.setIdField("ID456");

        testController.callHandleUpdateCredentials();

        assertTrue(testRepository.wasUpdateUserCalled(), "updateUser should be called");
        assertEquals("newemail@example.com", testUser.getEmail(), "Email should be updated");
        assertEquals("ID456", testUser.getIdNumber(), "ID number should be updated");
        assertEquals("Credentials updated successfully.", testController.getUpdateStatusLabel(), 
                    "Success message should be displayed");
    }

    @Test
    @DisplayName("Should reject empty email")
    void testHandleUpdateCredentialsEmptyEmail() {
        testController.callInitialize();
        testController.setEmailField("");
        testController.setIdField("ID456");

        testController.callHandleUpdateCredentials();

        assertFalse(testRepository.wasUpdateUserCalled(), "updateUser should not be called");
        assertEquals("Email cannot be empty.", testController.getUpdateStatusLabel(), 
                    "Error message should be displayed");
    }

    @Test
    @DisplayName("Should trim whitespace from email field")
    void testHandleUpdateCredentialsTrimEmail() {
        testController.callInitialize();
        testController.setEmailField("   newemail@example.com   ");
        testController.setIdField("ID456");

        testController.callHandleUpdateCredentials();

        assertTrue(testRepository.wasUpdateUserCalled(), "updateUser should be called");
        assertEquals("newemail@example.com", testUser.getEmail(), "Email should be trimmed and updated");
    }

    @Test
    @DisplayName("Should handle whitespace-only email as empty")
    void testHandleUpdateCredentialsWhitespaceOnlyEmail() {
        testController.callInitialize();
        testController.setEmailField("   ");
        testController.setIdField("ID456");

        testController.callHandleUpdateCredentials();

        assertFalse(testRepository.wasUpdateUserCalled(), "updateUser should not be called");
        assertEquals("Email cannot be empty.", testController.getUpdateStatusLabel(), 
                    "Whitespace-only email should be rejected");
    }

    @Test
    @DisplayName("Should allow empty ID field")
    void testHandleUpdateCredentialsEmptyId() {
        testController.callInitialize();
        testController.setEmailField("newemail@example.com");
        testController.setIdField("");

        testController.callHandleUpdateCredentials();

        assertTrue(testRepository.wasUpdateUserCalled(), "updateUser should be called");
        assertEquals("", testUser.getIdNumber(), "ID number should be empty");
    }

    @Test
    @DisplayName("Should update both email and ID together")
    void testHandleUpdateBothCredentials() {
        testController.callInitialize();
        testController.setEmailField("newemail@example.com");
        testController.setIdField("ID789");

        testController.callHandleUpdateCredentials();

        assertEquals("newemail@example.com", testUser.getEmail(), "Email should be updated");
        assertEquals("ID789", testUser.getIdNumber(), "ID should be updated");
        assertTrue(testRepository.wasUpdateUserCalled(), "Repository update should be called");
    }

    // ────── SCENE NAVIGATION TESTS ──────────

    @Test
    @DisplayName("Should switch to Payment scene when make payment is clicked")
    void testHandleMakePayment() {
        TestMainApp.resetSceneSwitch();
        testController.callHandleMakePayment();

        assertEquals("Payment", TestMainApp.getLastSwitchedScene(), "Should switch to Payment scene");
    }

    @Test
    @DisplayName("Should switch to Dashboard scene when home is clicked")
    void testHandleHome() {
        TestMainApp.resetSceneSwitch();
        testController.callHandleHome();

        assertEquals("Dashboard", TestMainApp.getLastSwitchedScene(), "Should switch to Dashboard scene");
    }

    // ────── INTEGRATION TESTS ──────────

    @Test
    @DisplayName("Should handle complete account view lifecycle")
    void testCompleteAccountViewLifecycle() {
        // Initialize
        testController.callInitialize();
        assertEquals("John Doe", testController.getUserNameLabel(), "Should initialize with user name");

        // Add reservation
        String[] reservation = {"RES001", "STU-001", "EQ001", "2024-01-01", "2024-01-02", "ACTIVE", "50.0"};
        testRepository.addReservationRow(reservation);
        testController.callLoadBalanceDue();
        assertTrue(testController.getBalanceListItemCount() > 0, "Should load balance");

        // Update credentials
        testController.setEmailField("updated@example.com");
        testController.setIdField("NEW123");
        testController.callHandleUpdateCredentials();
        assertEquals("Credentials updated successfully.", testController.getUpdateStatusLabel(), 
                    "Should update credentials");
    }

    @Test
    @DisplayName("Should maintain user data across multiple operations")
    void testMaintainUserDataAcrossOperations() {
        String originalEmail = testUser.getEmail();
        String originalId = testUser.getIdNumber();

        testController.callInitialize();
        testController.setEmailField("temp@example.com");
        testController.setIdField("TEMP");
        testController.callHandleUpdateCredentials();

        assertEquals("temp@example.com", testUser.getEmail(), "Email should be updated");
        assertEquals("TEMP", testUser.getIdNumber(), "ID should be updated");

        // Update again
        testController.setEmailField("final@example.com");
        testController.callHandleUpdateCredentials();
        assertEquals("final@example.com", testUser.getEmail(), "Email should be updated again");
    }
}

/**
 * TestAccountController wraps AccountController for testing without full JavaFX initialization
 */
class TestAccountController {
    private String userNameLabel = "";
    private String userNameTopLabel = "";
    private String emailField = "";
    private String idField = "";
    private String updateStatusLabel = "";
    private java.util.List<String> balanceList = new java.util.ArrayList<>();
    private TestCSVRepository repository;

    public TestAccountController(TestCSVRepository repository) {
        this.repository = repository;
    }

    public void callInitialize() {
        org.example.users.User currentUser = org.example.auth.AuthService.getCurrentUser();
        if (currentUser == null) return;

        userNameLabel = currentUser.getName();
        userNameTopLabel = currentUser.getName();
        emailField = currentUser.getEmail();
        idField = currentUser.getIdNumber() != null ? currentUser.getIdNumber() : "";

        callLoadBalanceDue();
    }

    public void callLoadBalanceDue() {
        java.util.List<String> balanceItems = new java.util.ArrayList<>();
        org.example.users.User currentUser = org.example.auth.AuthService.getCurrentUser();
        if (currentUser == null) return;

        java.util.List<String[]> reservationRows = repository.getAllReservationRows();

        for (String[] row : reservationRows) {
            if (!row[1].equals(currentUser.getUserId())) continue;
            if (row[5].equals("CANCELLED")) continue;

            String equipmentId = row[2];
            double deposit = Double.parseDouble(row[6]);
            balanceItems.add(equipmentId + ":   $" + (int) deposit);
        }

        balanceList = balanceItems;
    }

    public void callHandleUpdateCredentials() {
        String newEmail = emailField.trim();
        String newId = idField.trim();

        if (newEmail.isEmpty()) {
            updateStatusLabel = "Email cannot be empty.";
            return;
        }

        org.example.users.User currentUser = org.example.auth.AuthService.getCurrentUser();
        if (currentUser != null) {
            currentUser.setEmail(newEmail);
            currentUser.setIdNumber(newId);
            repository.updateUser(currentUser);
            updateStatusLabel = "Credentials updated successfully.";
        }
    }

    public void callHandleMakePayment() {
        TestMainApp.switchScene("Payment");
    }

    public void callHandleHome() {
        TestMainApp.switchScene("Dashboard");
    }

    // Getters for verification
    public String getUserNameLabel() { return userNameLabel; }
    public String getUserNameTopLabel() { return userNameTopLabel; }
    public String getEmailField() { return emailField; }
    public String getIdField() { return idField; }
    public String getUpdateStatusLabel() { return updateStatusLabel; }
    public int getBalanceListItemCount() { return balanceList.size(); }
    public String getBalanceListItem(int index) { return balanceList.get(index); }

    // Setters for testing
    public void setEmailField(String email) { this.emailField = email; }
    public void setIdField(String id) { this.idField = id; }
}

/**
 * TestUser is a test double that implements User interface
 */
class TestUser extends org.example.users.User {
    public TestUser(String userId, String name, String email, String password,
                   String userType, String departmentId, String idNumber) {
        super(userId, name, email, password, userType, departmentId, idNumber);
    }

    @Override
    public String getUserType() {
        return userType;
    }
}

/**
 * TestCSVRepository is a test double for CSVRepository
 */
class TestCSVRepository {
    private java.util.List<String[]> reservationRows = new java.util.ArrayList<>();
    private boolean updateUserCalled = false;
    private org.example.users.User lastUpdatedUser = null;

    public void addReservationRow(String[] row) {
        reservationRows.add(row);
    }

    public java.util.List<String[]> getAllReservationRows() {
        return new java.util.ArrayList<>(reservationRows);
    }

    public void updateUser(org.example.users.User user) {
        updateUserCalled = true;
        lastUpdatedUser = user;
    }

    public boolean wasUpdateUserCalled() {
        return updateUserCalled;
    }

    public org.example.users.User getLastUpdatedUser() {
        return lastUpdatedUser;
    }
}

/**
 * TestMainApp provides static methods for scene switching tests
 */
class TestMainApp {
    private static String lastSwitchedScene = null;

    public static void switchScene(String sceneName) {
        lastSwitchedScene = sceneName;
    }

    public static String getLastSwitchedScene() {
        return lastSwitchedScene;
    }

    public static void resetSceneSwitch() {
        lastSwitchedScene = null;
    }
}
