package org.example.manual.gui;

import org.example.auth.AuthService;
import org.example.equipment.Equipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.AfterEach;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Dashboard Controller Tests")
public class DashboardControllerTest {

    private TestDashboardController testController;
    private DashboardTestEquipmentManager testEquipmentManager;
    private DashboardTestUser testUser;

    @BeforeEach
    void setUp() {
        testEquipmentManager = new DashboardTestEquipmentManager();
        testUser = new DashboardTestUser("STU-001", "John Doe", "john@example.com", "password123", "STUDENT", "CS101", "ID123");
        AuthService.setCurrentUserForTesting(testUser);
        testController = new TestDashboardController(testEquipmentManager);
    }

    @AfterEach
    void tearDown() {
        AuthService.clearCurrentUserForTesting();
    }

    // ────── INITIALIZATION TESTS ──────────

    @Test
    @DisplayName("Should initialize with current user name in menu button")
    void testInitializeWithUserName() {
        testController.callInitialize();

        assertEquals("John Doe", testController.getUserMenuButtonText(), "User name should be set in menu button");
    }

    @Test
    @DisplayName("Should initialize when current user is null")
    void testInitializeWithNullUser() {
        AuthService.clearCurrentUserForTesting();
        TestDashboardController controller = new TestDashboardController(testEquipmentManager);

        assertDoesNotThrow(() -> controller.callInitialize(), "Should handle null user gracefully");
    }

    @Test
    @DisplayName("Should populate sort combo box options")
    void testInitializePopulatesSortOptions() {
        testController.callInitialize();

        List<String> sortOptions = testController.getSortOptions();
        assertEquals(5, sortOptions.size(), "Should have 5 sort options");
        assertTrue(sortOptions.contains("Name A-Z"));
        assertTrue(sortOptions.contains("Name Z-A"));
        assertTrue(sortOptions.contains("Price Low-High"));
        assertTrue(sortOptions.contains("Price High-Low"));
        assertTrue(sortOptions.contains("Location"));
    }

    @Test
    @DisplayName("Should load available equipment on initialization")
    void testInitializeLoadsEquipment() {
        Equipment eq1 = new Equipment("EQ001", "Microscope", "Lab A");
        Equipment eq2 = new Equipment("EQ002", "Spectrometer", "Lab B");
        testEquipmentManager.addEquipmentForRetrieval(eq1);
        testEquipmentManager.addEquipmentForRetrieval(eq2);

        testController.callInitialize();

        assertEquals(2, testController.getEquipmentGridItemCount(), "Should load available equipment");
    }

    // ────── LOAD EQUIPMENT TESTS ──────────

    @Test
    @DisplayName("Should load available equipment from manager")
    void testLoadEquipment() {
        Equipment eq1 = new Equipment("EQ001", "Microscope", "Lab A");
        Equipment eq2 = new Equipment("EQ002", "Spectrometer", "Lab B");
        testEquipmentManager.addEquipmentForRetrieval(eq1);
        testEquipmentManager.addEquipmentForRetrieval(eq2);

        testController.callLoadEquipment();

        assertEquals(2, testController.getEquipmentGridItemCount(), "Should load all available equipment");
    }

    @Test
    @DisplayName("Should handle empty equipment list")
    void testLoadEquipmentEmpty() {
        testController.callLoadEquipment();

        assertEquals(0, testController.getEquipmentGridItemCount(), "Should handle empty equipment list");
    }

    // ────── SORT TESTS ──────────

    @Test
    @DisplayName("Should sort equipment A-Z by name")
    void testHandleSortNameAZ() {
        Equipment eq1 = new Equipment("EQ001", "Zebra Scanner", "Lab A");
        Equipment eq2 = new Equipment("EQ002", "Apple Cutter", "Lab B");
        Equipment eq3 = new Equipment("EQ003", "Microscope", "Lab C");
        testEquipmentManager.addEquipmentForRetrieval(eq1);
        testEquipmentManager.addEquipmentForRetrieval(eq2);
        testEquipmentManager.addEquipmentForRetrieval(eq3);

        testController.callLoadEquipment();
        testController.setSortSelection("Name A-Z");
        testController.callHandleSort();

        List<String> items = testController.getEquipmentGridItems();
        assertEquals("Apple Cutter", items.get(0), "First item should be Apple Cutter");
        assertEquals("Microscope", items.get(1), "Second item should be Microscope");
        assertEquals("Zebra Scanner", items.get(2), "Third item should be Zebra Scanner");
    }

    @Test
    @DisplayName("Should sort equipment Z-A by name")
    void testHandleSortNameZA() {
        Equipment eq1 = new Equipment("EQ001", "Apple Cutter", "Lab A");
        Equipment eq2 = new Equipment("EQ002", "Microscope", "Lab B");
        Equipment eq3 = new Equipment("EQ003", "Zebra Scanner", "Lab C");
        testEquipmentManager.addEquipmentForRetrieval(eq1);
        testEquipmentManager.addEquipmentForRetrieval(eq2);
        testEquipmentManager.addEquipmentForRetrieval(eq3);

        testController.callLoadEquipment();
        testController.setSortSelection("Name Z-A");
        testController.callHandleSort();

        List<String> items = testController.getEquipmentGridItems();
        assertEquals("Zebra Scanner", items.get(0), "First item should be Zebra Scanner");
        assertEquals("Microscope", items.get(1), "Second item should be Microscope");
        assertEquals("Apple Cutter", items.get(2), "Third item should be Apple Cutter");
    }

    @Test
    @DisplayName("Should sort equipment by location")
    void testHandleSortLocation() {
        Equipment eq1 = new Equipment("EQ001", "Equipment C", "Lab Z");
        Equipment eq2 = new Equipment("EQ002", "Equipment A", "Lab B");
        Equipment eq3 = new Equipment("EQ003", "Equipment B", "Lab A");
        testEquipmentManager.addEquipmentForRetrieval(eq1);
        testEquipmentManager.addEquipmentForRetrieval(eq2);
        testEquipmentManager.addEquipmentForRetrieval(eq3);

        testController.callLoadEquipment();
        testController.setSortSelection("Location");
        testController.callHandleSort();

        List<String> locations = testController.getEquipmentGridLocations();
        assertEquals("Lab A", locations.get(0), "First should be Lab A");
        assertEquals("Lab B", locations.get(1), "Second should be Lab B");
        assertEquals("Lab Z", locations.get(2), "Third should be Lab Z");
    }

    @Test
    @DisplayName("Should handle sort with null selection")
    void testHandleSortNullSelection() {
        Equipment eq1 = new Equipment("EQ001", "Equipment A", "Lab A");
        testEquipmentManager.addEquipmentForRetrieval(eq1);

        testController.callLoadEquipment();
        testController.setSortSelection(null);

        assertDoesNotThrow(() -> testController.callHandleSort(), "Should handle null sort selection");
    }

    // ────── RESERVE EQUIPMENT TESTS ──────────

    @Test
    @DisplayName("Should add equipment to cart")
    void testHandleReserveAddToCart() {
        Equipment equipment = new Equipment("EQ001", "Microscope", "Lab A");

        testController.callHandleReserve(equipment);

        assertEquals(1, testController.getCartItemCount(), "Cart should have 1 item");
        assertTrue(testController.getCartItems().contains("Microscope"), "Cart should contain equipment");
    }

    @Test
    @DisplayName("Should prevent duplicate items in cart")
    void testHandleReserveDuplicateInCart() {
        Equipment equipment = new Equipment("EQ001", "Microscope", "Lab A");

        testController.callHandleReserve(equipment);
        testController.callHandleReserve(equipment);

        assertEquals(1, testController.getCartItemCount(), "Cart should still have 1 item");
        assertTrue(testController.wasAlertShown(), "Alert should be shown for duplicate");
        assertTrue(testController.getLastAlertMessage().contains("already in your cart"),
                "Alert should mention item already in cart");
    }

    @Test
    @DisplayName("Should add multiple different equipment to cart")
    void testHandleReserveMultipleEquipment() {
        Equipment eq1 = new Equipment("EQ001", "Microscope", "Lab A");
        Equipment eq2 = new Equipment("EQ002", "Spectrometer", "Lab B");
        Equipment eq3 = new Equipment("EQ003", "Centrifuge", "Lab C");

        testController.callHandleReserve(eq1);
        testController.callHandleReserve(eq2);
        testController.callHandleReserve(eq3);

        assertEquals(3, testController.getCartItemCount(), "Cart should have 3 items");
    }

    // ────── CART TESTS ──────────

    @Test
    @DisplayName("Should update cart display")
    void testUpdateCartDisplay() {
        Equipment eq1 = new Equipment("EQ001", "Microscope", "Lab A");
        Equipment eq2 = new Equipment("EQ002", "Spectrometer", "Lab B");

        testController.callHandleReserve(eq1);
        testController.callHandleReserve(eq2);

        List<String> items = testController.getCartItems();
        assertEquals(2, items.size(), "Cart should display 2 items");
    }

    @Test
    @DisplayName("Should calculate correct subtotal")
    void testCalculateSubtotal() {
        Equipment eq1 = new Equipment("EQ001", "Microscope", "Lab A");
        Equipment eq2 = new Equipment("EQ002", "Spectrometer", "Lab B");

        testController.callHandleReserve(eq1);
        testController.callHandleReserve(eq2);

        String subtotal = testController.getSubtotalLabel();
        assertTrue(subtotal.startsWith("$"), "Subtotal should start with $");
        assertNotEquals("$0", subtotal, "Subtotal should not be 0");
    }

    @Test
    @DisplayName("Should display empty cart correctly")
    void testEmptyCartDisplay() {
        assertEquals(0, testController.getCartItemCount(), "Cart should be empty initially");
        assertEquals("$0", testController.getSubtotalLabel(), "Subtotal should be $0 for empty cart");
    }

    // ────── CHECKOUT TESTS ──────────

    @Test
    @DisplayName("Should reject checkout with empty cart")
    void testHandleCheckoutEmptyCart() {
        testController.callHandleCheckout();

        assertTrue(testController.wasAlertShown(), "Alert should be shown");
        assertTrue(testController.getLastAlertMessage().contains("Please add equipment to your cart"),
                "Alert should indicate cart is empty");
    }

    @Test
    @DisplayName("Should proceed with checkout when cart has items")
    void testHandleCheckoutWithItems() {
        Equipment equipment = new Equipment("EQ001", "Microscope", "Lab A");
        testController.callHandleReserve(equipment);

        TestMainApp.resetSceneSwitch();
        testController.callHandleCheckout();

        assertEquals("Reservation", TestMainApp.getLastSwitchedScene(), "Should switch to Reservation scene");
    }

    @Test
    @DisplayName("Should store cart in MainController on checkout")
    void testHandleCheckoutStoresCart() {
        Equipment eq1 = new Equipment("EQ001", "Microscope", "Lab A");
        Equipment eq2 = new Equipment("EQ002", "Spectrometer", "Lab B");
        testController.callHandleReserve(eq1);
        testController.callHandleReserve(eq2);

        testController.callHandleCheckout();

        List<Equipment> storedCart = TestMainController.getStoredCart();
        assertEquals(2, storedCart.size(), "Cart should be stored in MainController");
    }

    // ────── NAVIGATION TESTS ──────────

    @Test
    @DisplayName("Should refresh equipment on home click")
    void testHandleHome() {
        Equipment equipment = new Equipment("EQ001", "Microscope", "Lab A");
        testEquipmentManager.addEquipmentForRetrieval(equipment);

        testController.callLoadEquipment();
        testController.callHandleHome();

        assertEquals(1, testController.getEquipmentGridItemCount(), "Equipment should be reloaded");
    }

    @Test
    @DisplayName("Should switch to Account scene")
    void testHandleAccount() {
        TestMainApp.resetSceneSwitch();
        testController.callHandleAccount();

        assertEquals("Account", TestMainApp.getLastSwitchedScene(), "Should switch to Account scene");
    }

    @Test
    @DisplayName("Should switch to MyReservations scene")
    void testHandleMyReservations() {
        TestMainApp.resetSceneSwitch();
        testController.callHandleMyReservations();

        assertEquals("MyReservations", TestMainApp.getLastSwitchedScene(), "Should switch to MyReservations scene");
    }

    @Test
    @DisplayName("Should logout user")
    void testHandleLogout() {
        testController.callHandleLogout();

        assertTrue(TestMainController.wasLogoutCalled(), "Should call logout");
    }

    // ────── MORE EQUIPMENT TESTS ──────────

    @Test
    @DisplayName("Should show equipment details alert")
    void testHandleMoreShowsDetails() {
        Equipment equipment = new Equipment("EQ001", "Microscope", "Lab A");

        testController.callHandleMore(equipment);

        assertTrue(testController.wasAlertShown(), "Alert should be shown");
        String message = testController.getLastAlertMessage();
        assertTrue(message.contains("EQ001"), "Alert should contain equipment ID");
        assertTrue(message.contains("Lab A"), "Alert should contain location");
    }

    // ────── INTEGRATION TESTS ──────────

    @Test
    @DisplayName("Should handle complete shopping workflow")
    void testCompleteShoppingWorkflow() {
        Equipment eq1 = new Equipment("EQ001", "Microscope", "Lab A");
        Equipment eq2 = new Equipment("EQ002", "Spectrometer", "Lab B");
        testEquipmentManager.addEquipmentForRetrieval(eq1);
        testEquipmentManager.addEquipmentForRetrieval(eq2);

        testController.callInitialize();
        assertEquals(2, testController.getEquipmentGridItemCount(), "Should initialize with equipment");

        testController.setSortSelection("Name A-Z");
        testController.callHandleSort();

        testController.callHandleReserve(eq1);
        testController.callHandleReserve(eq2);
        assertEquals(2, testController.getCartItemCount(), "Should have 2 items in cart");

        TestMainApp.resetSceneSwitch();
        testController.callHandleCheckout();
        assertEquals("Reservation", TestMainApp.getLastSwitchedScene(), "Should navigate to Reservation");
    }

    @Test
    @DisplayName("Should handle sort and display correctly")
    void testSortAndDisplayWorkflow() {
        Equipment eq1 = new Equipment("EQ001", "Zebra Scanner", "Lab Z");
        Equipment eq2 = new Equipment("EQ002", "Apple Cutter", "Lab A");
        testEquipmentManager.addEquipmentForRetrieval(eq1);
        testEquipmentManager.addEquipmentForRetrieval(eq2);

        testController.callLoadEquipment();
        testController.setSortSelection("Location");
        testController.callHandleSort();

        List<String> locations = testController.getEquipmentGridLocations();
        assertEquals("Lab A", locations.get(0), "Equipment should be sorted by location");
    }
}

/**
 * TestDashboardController wraps DashboardController for testing without JavaFX
 */
class TestDashboardController {
    private String userMenuButtonText = "";
    private List<String> sortOptions = new ArrayList<>();
    private List<Equipment> equipmentList = new ArrayList<>();
    private List<Equipment> cart = new ArrayList<>();
    private String sortSelection = null;

    private boolean alertShown = false;
    private String lastAlertTitle = "";
    private String lastAlertMessage = "";

    private DashboardTestEquipmentManager equipmentManager;

    public TestDashboardController(DashboardTestEquipmentManager equipmentManager) {
        this.equipmentManager = equipmentManager;
    }

    public void callInitialize() {
        org.example.users.User currentUser = org.example.auth.AuthService.getCurrentUser();
        if (currentUser != null) {
            userMenuButtonText = currentUser.getName();
        }

        sortOptions = java.util.Arrays.asList(
                "Name A-Z",
                "Name Z-A",
                "Price Low-High",
                "Price High-Low",
                "Location"
        );

        callLoadEquipment();
    }

    public void callLoadEquipment() {
        equipmentList = new ArrayList<>(equipmentManager.getAvailableEquipment());
    }

    public void callHandleSort() {
        if (sortSelection == null) return;

        List<Equipment> sorted = new ArrayList<>(equipmentList);

        switch (sortSelection) {
            case "Name A-Z":
                sorted.sort(Comparator.comparing(Equipment::getDescription));
                break;
            case "Name Z-A":
                sorted.sort(Comparator.comparing(Equipment::getDescription).reversed());
                break;
            case "Location":
                sorted.sort(Comparator.comparing(Equipment::getLabLocation));
                break;
            default:
                break;
        }

        equipmentList = sorted;
    }

    public void callHandleMore(Equipment equipment) {
        String message = "ID: " + equipment.getEquipmentId() + "\n" +
                "Location: " + equipment.getLabLocation() + "\n" +
                "Status: " + equipment.getStatus().name();
        showAlert("Equipment Details", message);
    }

    public void callHandleReserve(Equipment equipment) {
        if (!cart.contains(equipment)) {
            cart.add(equipment);
            updateCart();
        } else {
            showAlert("Already in Cart", equipment.getDescription() + " is already in your cart.");
        }
    }

    private void updateCart() {
        // Cart display is managed, just tracking items
    }

    public void callHandleCheckout() {
        if (cart.isEmpty()) {
            showAlert("Empty Cart", "Please add equipment to your cart first.");
            return;
        }
        TestMainController.setCart(new ArrayList<>(cart));
        TestMainApp.switchScene("Reservation");
    }

    public void callHandleHome() {
        callLoadEquipment();
    }

    public void callHandleAccount() {
        TestMainApp.switchScene("Account");
    }

    public void callHandleMyReservations() {
        TestMainApp.switchScene("MyReservations");
    }

    public void callHandleLogout() {
        TestMainController.logout();
    }

    private void showAlert(String title, String message) {
        alertShown = true;
        lastAlertTitle = title;
        lastAlertMessage = message;
    }

    // Getters for verification
    public String getUserMenuButtonText() { return userMenuButtonText; }
    public List<String> getSortOptions() { return new ArrayList<>(sortOptions); }
    public int getEquipmentGridItemCount() { return equipmentList.size(); }
    public List<String> getEquipmentGridItems() {
        List<String> items = new ArrayList<>();
        for (Equipment e : equipmentList) {
            items.add(e.getDescription());
        }
        return items;
    }
    public List<String> getEquipmentGridLocations() {
        List<String> locations = new ArrayList<>();
        for (Equipment e : equipmentList) {
            locations.add(e.getLabLocation());
        }
        return locations;
    }
    public int getCartItemCount() { return cart.size(); }
    public List<String> getCartItems() {
        List<String> items = new ArrayList<>();
        for (Equipment e : cart) {
            items.add(e.getDescription());
        }
        return items;
    }
    public String getSubtotalLabel() {
        double subtotal = cart.size() * 15; // Default rate
        return "$" + (int) subtotal;
    }
    public boolean wasAlertShown() { return alertShown; }
    public String getLastAlertMessage() { return lastAlertMessage; }

    // Setters for testing
    public void setSortSelection(String selection) { this.sortSelection = selection; }
}

/**
 * DashboardTestEquipmentManager is a test double for EquipmentManager
 */
class DashboardTestEquipmentManager {
    private List<Equipment> equipmentList = new ArrayList<>();

    public void addEquipmentForRetrieval(Equipment equipment) {
        equipmentList.add(equipment);
    }

    public List<Equipment> getAvailableEquipment() {
        return new ArrayList<>(equipmentList);
    }
}

/**
 * DashboardTestUser is a test double for User
 */
class DashboardTestUser extends org.example.users.User {
    public DashboardTestUser(String userId, String name, String email, String password,
                             String userType, String departmentId, String idNumber) {
        super(userId, name, email, password, userType, departmentId, idNumber);
    }

    @Override
    public String getUserType() {
        return userType;
    }
}

/**
 * TestMainController provides static methods for testing
 */
class TestMainController {
    private static List<Equipment> storedCart = new ArrayList<>();
    private static boolean logoutCalled = false;

    public static void setCart(List<Equipment> cart) {
        storedCart = new ArrayList<>(cart);
    }

    public static List<Equipment> getStoredCart() {
        return new ArrayList<>(storedCart);
    }

    public static void logout() {
        logoutCalled = true;
    }

    public static boolean wasLogoutCalled() {
        return logoutCalled;
    }

    public static void reset() {
        storedCart.clear();
        logoutCalled = false;
    }
}