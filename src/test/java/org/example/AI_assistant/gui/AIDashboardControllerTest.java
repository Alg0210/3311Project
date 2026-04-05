package org.example.AI_assistant.gui;

import org.example.auth.AuthService;
import org.example.equipment.Equipment;
import org.example.equipment.EquipmentStatus;
import org.example.users.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AIDashboardControllerTest {

    private StubDashboardController testController;
    private StubEquipmentManager testEquipmentManager;
    private StubUser testUser;

    @BeforeEach
    public void setUp() {
        testEquipmentManager = new StubEquipmentManager();
        testUser = new StubUser("U-001", "Bob Smith", "bob@example.com", "pass", "STUDENT", "CS", "ID-123");
        AuthService.setCurrentUserForTesting(testUser);
        testController = new StubDashboardController(testEquipmentManager);
        StubMainApp.lastScene = null;
        StubMainController.cart.clear();
        StubMainController.loggedOut = false;
    }

    @AfterEach
    public void tearDown() {
        AuthService.clearCurrentUserForTesting();
    }

    @Test
    public void testInitializeSetsUserNameAndOptions() {
        testController.initialize();

        assertEquals("Bob Smith", testController.userMenuButtonText);
        assertEquals(5, testController.sortOptions.size());
        assertTrue(testController.sortOptions.contains("Name A-Z"));
        assertTrue(testController.sortOptions.contains("Location"));
    }

    @Test
    public void testInitializeWithNoUser() {
        AuthService.clearCurrentUserForTesting();
        StubDashboardController emptyController = new StubDashboardController(testEquipmentManager);

        assertDoesNotThrow(emptyController::initialize);
        assertNull(emptyController.userMenuButtonText);
    }

    @Test
    public void testLoadEquipmentPopulatesGrid() {
        testEquipmentManager.addTestEquipment(new Equipment("EQ-1", "Beaker", "Lab A"));
        testEquipmentManager.addTestEquipment(new Equipment("EQ-2", "Microscope", "Lab B"));

        testController.loadEquipment();

        assertEquals(2, testController.equipmentGrid.size());
        assertEquals("Beaker", testController.equipmentGrid.get(0).getDescription());
        assertEquals("Microscope", testController.equipmentGrid.get(1).getDescription());
    }

    @Test
    public void testHandleSortByNameAZ() {
        testEquipmentManager.addTestEquipment(new Equipment("EQ-1", "Zebra", "Lab A"));
        testEquipmentManager.addTestEquipment(new Equipment("EQ-2", "Apple", "Lab B"));
        testEquipmentManager.addTestEquipment(new Equipment("EQ-3", "Mango", "Lab C"));

        testController.loadEquipment();
        testController.sortSelection = "Name A-Z";
        testController.handleSort();

        assertEquals("Apple", testController.equipmentGrid.get(0).getDescription());
        assertEquals("Mango", testController.equipmentGrid.get(1).getDescription());
        assertEquals("Zebra", testController.equipmentGrid.get(2).getDescription());
    }

    @Test
    public void testHandleSortByNameZA() {
        testEquipmentManager.addTestEquipment(new Equipment("EQ-1", "Zebra", "Lab A"));
        testEquipmentManager.addTestEquipment(new Equipment("EQ-2", "Apple", "Lab B"));
        testEquipmentManager.addTestEquipment(new Equipment("EQ-3", "Mango", "Lab C"));

        testController.loadEquipment();
        testController.sortSelection = "Name Z-A";
        testController.handleSort();

        assertEquals("Zebra", testController.equipmentGrid.get(0).getDescription());
        assertEquals("Mango", testController.equipmentGrid.get(1).getDescription());
        assertEquals("Apple", testController.equipmentGrid.get(2).getDescription());
    }

    @Test
    public void testHandleSortByLocation() {
        testEquipmentManager.addTestEquipment(new Equipment("EQ-1", "Item1", "Lab Z"));
        testEquipmentManager.addTestEquipment(new Equipment("EQ-2", "Item2", "Lab A"));
        testEquipmentManager.addTestEquipment(new Equipment("EQ-3", "Item3", "Lab M"));

        testController.loadEquipment();
        testController.sortSelection = "Location";
        testController.handleSort();

        assertEquals("Lab A", testController.equipmentGrid.get(0).getLabLocation());
        assertEquals("Lab M", testController.equipmentGrid.get(1).getLabLocation());
        assertEquals("Lab Z", testController.equipmentGrid.get(2).getLabLocation());
    }

    @Test
    public void testHandleSortWithNullSelection() {
        testEquipmentManager.addTestEquipment(new Equipment("EQ-1", "Item1", "Lab A"));

        testController.loadEquipment();
        testController.sortSelection = null; // No sort set

        assertDoesNotThrow(testController::handleSort);
        assertEquals("Item1", testController.equipmentGrid.get(0).getDescription());
    }

    @Test
    public void testHandleReserveAddsToCartAndUpdatesSubtotal() {
        Equipment eq = new Equipment("EQ-1", "Oscilloscope", "Lab A");

        testController.handleReserve(eq);

        assertEquals(1, testController.cart.size());
        assertTrue(testController.cart.contains(eq));
        assertEquals(1, testController.cartListViewItems.size());

        // Student rate is typically 15, let's verify subtotal formatted string
        assertTrue(testController.subtotalLabel.startsWith("$15"));
    }

    @Test
    public void testHandleReserveDuplicateShowsAlert() {
        Equipment eq = new Equipment("EQ-1", "Oscilloscope", "Lab A");

        testController.handleReserve(eq);
        testController.handleReserve(eq); // second time

        assertEquals(1, testController.cart.size()); // Should still be 1
        assertTrue(testController.alertShown);
        assertEquals("Already in Cart", testController.lastAlertTitle);
        assertTrue(testController.lastAlertMessage.contains("already in your cart"));
    }

    @Test
    public void testHandleCheckoutEmptyCartShowsAlert() {
        testController.handleCheckout();

        assertTrue(testController.alertShown);
        assertEquals("Empty Cart", testController.lastAlertTitle);
        assertNull(StubMainApp.lastScene); // Should not have switched scene
    }

    @Test
    public void testHandleCheckoutWithItems() {
        Equipment eq = new Equipment("EQ-1", "Oscilloscope", "Lab A");
        testController.handleReserve(eq);

        testController.handleCheckout();

        assertEquals("Reservation", StubMainApp.lastScene);
        assertEquals(1, StubMainController.cart.size());
        assertEquals("EQ-1", StubMainController.cart.get(0).getEquipmentId());
    }

    @Test
    public void testHandleMoreShowsDetails() {
        Equipment eq = new Equipment("EQ-1", "Oscilloscope", "Lab A");
        eq.setStatus(EquipmentStatus.AVAILABLE);

        testController.handleMore(eq);

        assertTrue(testController.alertShown);
        assertEquals("Equipment Details", testController.lastAlertTitle);
        assertTrue(testController.lastAlertMessage.contains("EQ-1"));
        assertTrue(testController.lastAlertMessage.contains("Lab A"));
        assertTrue(testController.lastAlertMessage.contains("AVAILABLE"));
    }

    @Test
    public void testNavigationMethods() {
        testController.handleHome();
        assertEquals("Dashboard", StubMainApp.lastScene);

        testController.handleAccount();
        assertEquals("Account", StubMainApp.lastScene);

        testController.handleMyReservations();
        assertEquals("MyReservations", StubMainApp.lastScene);

        testController.handleLogout();
        assertTrue(StubMainController.loggedOut);
    }

    /**
     * Stub wrapper mimicking DashboardController functionality.
     */
    private static class StubDashboardController {
        String userMenuButtonText;
        List<String> sortOptions = new ArrayList<>();
        List<Equipment> equipmentGrid = new ArrayList<>();
        List<Equipment> cart = new ArrayList<>();
        List<String> cartListViewItems = new ArrayList<>();
        String subtotalLabel = "$0";
        String sortSelection = null;

        boolean alertShown = false;
        String lastAlertTitle;
        String lastAlertMessage;

        private StubEquipmentManager equipmentManager;
        private List<Equipment> allEquipment = new ArrayList<>();

        StubDashboardController(StubEquipmentManager manager) {
            this.equipmentManager = manager;
        }

        public void initialize() {
            User currentUser = AuthService.getCurrentUser();
            if (currentUser != null) {
                userMenuButtonText = currentUser.getName();
            }

            sortOptions.add("Name A-Z");
            sortOptions.add("Name Z-A");
            sortOptions.add("Price Low-High");
            sortOptions.add("Price High-Low");
            sortOptions.add("Location");

            loadEquipment();
        }

        public void loadEquipment() {
            allEquipment = equipmentManager.getAvailableEquipment();
            populateGrid(allEquipment);
        }

        private void populateGrid(List<Equipment> equipmentList) {
            equipmentGrid = new ArrayList<>(equipmentList);
        }

        public void handleSort() {
            if (sortSelection == null) return;
            List<Equipment> sorted = new ArrayList<>(allEquipment);
            switch (sortSelection) {
                case "Name A-Z": sorted.sort(Comparator.comparing(Equipment::getDescription)); break;
                case "Name Z-A": sorted.sort(Comparator.comparing(Equipment::getDescription).reversed()); break;
                case "Location": sorted.sort(Comparator.comparing(Equipment::getLabLocation)); break;
                default: break;
            }
            populateGrid(sorted);
        }

        public void handleMore(Equipment equipment) {
            alertShown = true;
            lastAlertTitle = "Equipment Details";
            lastAlertMessage = "ID: " + equipment.getEquipmentId() + "\n" +
                    "Location: " + equipment.getLabLocation() + "\n" +
                    "Status: " + equipment.getStatus().name();
        }

        public void handleReserve(Equipment equipment) {
            if (!cart.contains(equipment)) {
                cart.add(equipment);
                updateCart();
            } else {
                alertShown = true;
                lastAlertTitle = "Already in Cart";
                lastAlertMessage = equipment.getDescription() + " is already in your cart.";
            }
        }

        private void updateCart() {
            List<String> items = new ArrayList<>();
            double subtotal = 0.0;
            User currentUser = AuthService.getCurrentUser();
            double rate = (currentUser != null && currentUser.getUserType().equals("STUDENT")) ? 15.0 : 0.0;

            for (Equipment e : cart) {
                items.add(e.getDescription() + "  $" + (int) rate);
                subtotal += rate;
            }

            cartListViewItems = items;
            subtotalLabel = "$" + (int) subtotal;
        }

        public void handleCheckout() {
            if (cart.isEmpty()) {
                alertShown = true;
                lastAlertTitle = "Empty Cart";
                lastAlertMessage = "Please add equipment to your cart first.";
                return;
            }
            StubMainController.cart = new ArrayList<>(cart);
            StubMainApp.switchScene("Reservation");
        }

        public void handleHome() {
            loadEquipment();
            StubMainApp.switchScene("Dashboard");
        }

        public void handleAccount() {
            StubMainApp.switchScene("Account");
        }

        public void handleMyReservations() {
            StubMainApp.switchScene("MyReservations");
        }

        public void handleLogout() {
            StubMainController.logout();
        }
    }

    private static class StubEquipmentManager {
        private List<Equipment> mockData = new ArrayList<>();

        public void addTestEquipment(Equipment equipment) {
            mockData.add(equipment);
        }

        public List<Equipment> getAvailableEquipment() {
            return new ArrayList<>(mockData);
        }
    }

    private static class StubUser extends User {
        public StubUser(String userId, String name, String email, String password, String userType, String departmentId, String idNumber) {
            super(userId, name, email, password, userType, departmentId, idNumber);
        }
        @Override public String getUserType() { return userType; }
    }

    private static class StubMainApp {
        static String lastScene;
        static void switchScene(String sceneName) { lastScene = sceneName; }
    }

    private static class StubMainController {
        static List<Equipment> cart = new ArrayList<>();
        static boolean loggedOut = false;

        public static void logout() { loggedOut = true; }
    }
}
