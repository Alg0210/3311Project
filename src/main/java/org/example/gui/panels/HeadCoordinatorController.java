package org.example.gui.panels;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import javafx.util.Duration;

import org.example.auth.PasswordValidator;
import org.example.data.CSVRepository;
import org.example.gui.MainController;
import org.example.users.*;

import java.util.ArrayList;
import java.util.List;

public class HeadCoordinatorController {

    private final CSVRepository repository = new CSVRepository();

    private List<User> allUsers;
    private ObservableList<User> displayedUsers;
    private String currentCategory = "Accounts";
    private User selectedOverlayUser;
    private Label[] navItems;

    @FXML
    private MenuButton userMenuButton;

    @FXML
    private Label navAccounts;
    @FXML
    private Label navManager;
    @FXML
    private Label navStudent;
    @FXML
    private Label navFaculty;
    @FXML
    private Label navResearcher;
    @FXML
    private Label navGuest;

    @FXML
    private TableView<User> usersTable;
    @FXML
    private TableColumn<User, String> colName;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, String> colType;
    @FXML
    private TableColumn<User, Void> colCredentials;
    @FXML
    private TableColumn<User, Void> colApproval;
    @FXML
    private TableColumn<User, Void> colEquipment;
    @FXML
    private TableColumn<User, Void> colDelete;
    @FXML
    private HBox addManagerBox;
    @FXML
    private Button addManagerBtn;

    @FXML
    private VBox credentialsOverlay;
    @FXML
    private Label credUserName;
    @FXML
    private Label credUserType;
    @FXML
    private TextField credIdNumber;
    @FXML
    private Label credTypeLabel;

    @FXML
    private VBox equipmentOverlay;
    @FXML
    private Label equipUserName;
    @FXML
    private Label equipUserType;
    @FXML
    private VBox equipmentList;

    @FXML
    private VBox createManagerOverlay;
    @FXML
    private TextField managerFirstName;
    @FXML
    private TextField managerLastName;
    @FXML
    private TextField managerEmail;
    @FXML
    private PasswordField managerPassword;
    @FXML
    private PasswordField managerConfirmPassword;
    @FXML
    private Label managerErrorLabel;

    @FXML
    private HBox snackbarContainer;
    @FXML
    private Label snackbarLabel;

    @FXML
    public void initialize() {
        User current = MainController.getCurrentUser();
        if (current != null) {
            userMenuButton.setText(current.getName());
        }

        navItems = new Label[] { navAccounts, navManager, navStudent,
                navFaculty, navResearcher, navGuest };

        selectNavItem(navAccounts);

        colName.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("email"));
        colType.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("userType"));

        setupCredentialsColumn();
        setupApprovalColumn();
        setupEquipmentColumn();
        setupDeleteColumn();

        displayedUsers = FXCollections.observableArrayList();
        usersTable.setItems(displayedUsers);
        refreshUsers();
    }

    // ─── TABLE CELL FACTORIES ─────────────────────────────

    private void setupCredentialsColumn() {
        colCredentials.setCellFactory(new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
            @Override
            public TableCell<User, Void> call(TableColumn<User, Void> param) {
                return new TableCell<User, Void>() {
                    private final Button btn = new Button("View Credentials");
                    {
                        btn.getStyleClass().add("table-action-button");
                        btn.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
                            @Override
                            public void handle(javafx.event.ActionEvent e) {
                                User user = getTableView().getItems().get(getIndex());
                                showCredentialsOverlay(user);
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            User user = getTableView().getItems().get(getIndex());
                            String type = user.getUserType();
                            if ("STUDENT".equals(type) || "FACULTY".equals(type)
                                    || "RESEARCHER".equals(type) || "GUEST".equals(type)) {
                                setGraphic(btn);
                            } else {
                                setGraphic(null);
                            }
                        }
                    }
                };
            }
        });
    }

    private void setupApprovalColumn() {
        colApproval.setCellFactory(new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
            @Override
            public TableCell<User, Void> call(TableColumn<User, Void> param) {
                return new TableCell<User, Void>() {
                    private final Label label = new Label();

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            User user = getTableView().getItems().get(getIndex());
                            if ("GUEST".equals(user.getUserType()) || "MANAGER".equals(user.getUserType())) {
                                label.setText("NO APPROVAL REQUIRED");
                                label.setStyle(
                                        "-fx-text-fill: #888888; -fx-font-weight: bold; -fx-border-color: #888888; -fx-border-width: 1px; -fx-border-radius: 4px; -fx-padding: 2px;");
                            } else {
                                String status = (user instanceof UserDecorator)
                                        ? ((UserDecorator) user).getApprovalStatus()
                                        : "NOT_APPROVED";
                                label.setText(status.replace("_", " "));
                                if ("APPROVED".equals(status)) {
                                    label.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
                                } else if ("DENIED".equals(status)) {
                                    label.setStyle("-fx-text-fill: #e31837; -fx-font-weight: bold;");
                                } else {
                                    label.setStyle("-fx-text-fill: #888888; -fx-font-weight: bold;");
                                }
                            }
                            setGraphic(label);
                        }
                    }
                };
            }
        });
    }

    private void setupEquipmentColumn() {
        colEquipment.setCellFactory(new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
            @Override
            public TableCell<User, Void> call(TableColumn<User, Void> param) {
                return new TableCell<User, Void>() {
                    private final Button btn = new Button("View Equipment Rented");
                    {
                        btn.getStyleClass().add("table-action-button");
                        btn.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
                            @Override
                            public void handle(javafx.event.ActionEvent e) {
                                User user = getTableView().getItems().get(getIndex());
                                showEquipmentOverlay(user);
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            User user = getTableView().getItems().get(getIndex());
                            setGraphic("MANAGER".equals(user.getUserType()) ? null : btn);
                        }
                    }
                };
            }
        });
    }

    private void setupDeleteColumn() {
        colDelete.setCellFactory(new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
            @Override
            public TableCell<User, Void> call(TableColumn<User, Void> param) {
                return new TableCell<User, Void>() {
                    private final Button btn = new Button("Delete Account");
                    {
                        btn.getStyleClass().add("table-delete-button");
                        btn.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
                            @Override
                            public void handle(javafx.event.ActionEvent e) {
                                User user = getTableView().getItems().get(getIndex());
                                handleDeleteUser(user);
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        setGraphic(empty ? null : btn);
                    }
                };
            }
        });
    }

    // ─── SIDEBAR NAVIGATION ───────────────────────────────

    @FXML
    private void handleNavAccounts() {
        selectNavItem(navAccounts);
        currentCategory = "Accounts";
        filterAndDisplay();
    }

    @FXML
    private void handleNavManager() {
        selectNavItem(navManager);
        currentCategory = "MANAGER";
        filterAndDisplay();
    }

    @FXML
    private void handleNavStudent() {
        selectNavItem(navStudent);
        currentCategory = "STUDENT";
        filterAndDisplay();
    }

    @FXML
    private void handleNavFaculty() {
        selectNavItem(navFaculty);
        currentCategory = "FACULTY";
        filterAndDisplay();
    }

    @FXML
    private void handleNavResearcher() {
        selectNavItem(navResearcher);
        currentCategory = "RESEARCHER";
        filterAndDisplay();
    }

    @FXML
    private void handleNavGuest() {
        selectNavItem(navGuest);
        currentCategory = "GUEST";
        filterAndDisplay();
    }

    private void selectNavItem(Label selected) {
        for (Label nav : navItems) {
            nav.getStyleClass().remove("sidebar-item-selected");
        }
        selected.getStyleClass().add("sidebar-item-selected");
    }

    private void filterAndDisplay() {
        List<User> filtered;
        if ("Accounts".equals(currentCategory)) {
            filtered = allUsers;
        } else {
            filtered = new ArrayList<User>();
            for (User u : allUsers) {
                if (u.getUserType().equals(currentCategory)) {
                    filtered.add(u);
                }
            }
        }
        displayedUsers.setAll(filtered);

        boolean showAdd = "MANAGER".equals(currentCategory);
        addManagerBox.setVisible(showAdd);
        addManagerBox.setManaged(showAdd);
    }

    // ─── DATA LOADING ─────────────────────────────────────

    private void refreshUsers() {
        List<User> all = repository.getAllUsers();
        allUsers = new ArrayList<User>();
        for (User u : all) {
            if (!"HEAD_COORDINATOR".equals(u.getUserType())) {
                allUsers.add(u);
            }
        }
        filterAndDisplay();
    }

    // ─── CREDENTIALS OVERLAY ──────────────────────────────

    private void showCredentialsOverlay(User user) {
        selectedOverlayUser = user;
        credUserName.setText(user.getName());
        credUserType.setText(user.getUserType());
        credIdNumber.setText(user.getIdNumber() != null ? user.getIdNumber() : "N/A");
        String type = user.getUserType();
        if ("FACULTY".equals(type) || "RESEARCHER".equals(type)) {
            credTypeLabel.setText("Staff ID");
        } else if ("GUEST".equals(type)) {
            credTypeLabel.setText("Guest Certification");
        } else {
            credTypeLabel.setText("Student ID");
        }
        credentialsOverlay.setVisible(true);
    }

    @FXML
    private void handleCredentialsBack() {
        credentialsOverlay.setVisible(false);
        selectedOverlayUser = null;
    }

    @FXML
    private void handleCredentialsApprove() {
        if (selectedOverlayUser == null)
            return;
        if (selectedOverlayUser instanceof UserDecorator) {
            ((UserDecorator) selectedOverlayUser).setApprovalStatus("APPROVED");
            repository.updateUser(selectedOverlayUser);
        }
        String name = selectedOverlayUser.getName();
        credentialsOverlay.setVisible(false);
        selectedOverlayUser = null;
        refreshUsers();
        showSnackbar(name + "'s credentials have been approved.");
    }

    @FXML
    private void handleCredentialsDeny() {
        if (selectedOverlayUser == null)
            return;
        if (selectedOverlayUser instanceof UserDecorator) {
            ((UserDecorator) selectedOverlayUser).setApprovalStatus("DENIED");
            repository.updateUser(selectedOverlayUser);
        }
        String name = selectedOverlayUser.getName();
        credentialsOverlay.setVisible(false);
        selectedOverlayUser = null;
        refreshUsers();
        showSnackbar(name + "'s credentials have been denied.");
    }

    // ─── EQUIPMENT OVERLAY ────────────────────────────────

    private void showEquipmentOverlay(User user) {
        selectedOverlayUser = user;
        equipUserName.setText(user.getName());
        equipUserType.setText(user.getUserType());
        equipmentList.getChildren().clear();

        List<String[]> reservations = repository.getReservationRowsByUserId(user.getUserId());

        if (reservations.isEmpty()) {
            Label noData = new Label("No reserved equipment.");
            noData.getStyleClass().add("overlay-label");
            equipmentList.getChildren().add(noData);
        } else {
            for (String[] resRow : reservations) {
                String equipmentId = resRow[2];
                String startTime = resRow[3];
                String endTime = resRow[4];
                String deposit = resRow[6];

                String[] equipRow = repository.findEquipmentRowById(equipmentId);
                String equipName = (equipRow != null) ? equipRow[1] : equipmentId;

                List<String[]> payments = repository.getPaymentRowsByReservationId(resRow[0]);
                double amountPaid = 0.0;
                for (String[] payRow : payments) {
                    amountPaid += Double.parseDouble(payRow[2]);
                }
                double depositAmount = Double.parseDouble(deposit);
                double amountDue = depositAmount - amountPaid;
                if (amountDue < 0)
                    amountDue = 0;

                HBox card = new HBox(12);
                card.getStyleClass().add("equipment-card");

                Rectangle placeholder = new Rectangle(80, 80);
                placeholder.setFill(Color.web("#1a1a1a"));
                placeholder.setArcWidth(8);
                placeholder.setArcHeight(8);

                VBox details = new VBox(4);
                Label nameLabel = new Label(equipName);
                nameLabel.getStyleClass().add("equipment-card-name");

                Label paidLabel = new Label(String.format("Amount Paid:\t$%.0f", amountPaid));
                paidLabel.getStyleClass().add("equipment-card-detail");

                Label dueLabel = new Label(String.format("Amount Due:\t$%.0f", amountDue));
                dueLabel.getStyleClass().add("equipment-card-detail");

                VBox dateDetails = new VBox(4);
                Label startLabel = new Label("Reserved on:\n" + startTime);
                startLabel.getStyleClass().add("equipment-card-detail");
                startLabel.setWrapText(true);

                Label endLabel = new Label("Reservation ends on:\n" + endTime);
                endLabel.getStyleClass().add("equipment-card-detail");
                endLabel.setWrapText(true);

                dateDetails.getChildren().addAll(startLabel, endLabel);
                details.getChildren().addAll(nameLabel, paidLabel, dueLabel);

                card.getChildren().addAll(placeholder, details, dateDetails);
                equipmentList.getChildren().add(card);
            }
        }
        equipmentOverlay.setVisible(true);
    }

    @FXML
    private void handleEquipmentBack() {
        equipmentOverlay.setVisible(false);
        selectedOverlayUser = null;
    }

    // ─── CREATE MANAGER OVERLAY ───────────────────────────

    @FXML
    private void handleAddManager() {
        managerFirstName.clear();
        managerLastName.clear();
        managerEmail.clear();
        managerPassword.clear();
        managerConfirmPassword.clear();
        managerErrorLabel.setText("");
        createManagerOverlay.setVisible(true);
    }

    @FXML
    private void handleCreateManagerCancel() {
        createManagerOverlay.setVisible(false);
    }

    @FXML
    private void handleCreateManagerSubmit() {
        String firstName = managerFirstName.getText().trim();
        String lastName = managerLastName.getText().trim();
        String email = managerEmail.getText().trim();
        String password = managerPassword.getText();
        String confirmPass = managerConfirmPassword.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()
                || password.isEmpty() || confirmPass.isEmpty()) {
            managerErrorLabel.setText("Please fill in all fields.");
            return;
        }

        if (!password.equals(confirmPass)) {
            managerErrorLabel.setText("Passwords do not match.");
            return;
        }

        if (!PasswordValidator.isValid(password)) {
            managerErrorLabel.setText("Password does not meet requirements.");
            return;
        }

        if (repository.findUserByEmail(email) != null) {
            managerErrorLabel.setText("An account with this email already exists.");
            return;
        }

        String fullName = firstName + " " + lastName;
        String userId = "MGR-" + System.currentTimeMillis();

        User currentUser = MainController.getCurrentUser();
        LabManager newManager;
        if (currentUser instanceof HeadCoordinator) {
            newManager = ((HeadCoordinator) currentUser).generateManagerAccount(
                    userId, fullName, email, password);
        } else {
            newManager = new LabManager(userId, fullName, email, password, "MGR-" + userId);
        }

        repository.saveUser(newManager);
        createManagerOverlay.setVisible(false);
        refreshUsers();
        showSnackbar("Lab Manager account for " + fullName + " created.");
    }

    // ─── DELETE USER ──────────────────────────────────────

    private void handleDeleteUser(User user) {
        repository.deleteUser(user.getUserId());
        refreshUsers();
        showSnackbar(user.getName() + "'s account has been deleted.");
    }

    // ─── SNACKBAR ─────────────────────────────────────────

    private void showSnackbar(String message) {
        snackbarLabel.setText(message);
        snackbarContainer.setVisible(true);

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent e) {
                snackbarContainer.setVisible(false);
            }
        });
        pause.play();
    }

    @FXML
    private void handleSnackbarClose() {
        snackbarContainer.setVisible(false);
    }

    // ─── LOGOUT ───────────────────────────────────────────

    @FXML
    private void handleLogout() {
        MainController.logout();
    }
}
