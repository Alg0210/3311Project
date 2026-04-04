package org.example.auth;

import org.example.data.CSVRepository;
import org.example.users.User;
import org.example.users.UserDecorator;
import org.example.users.UserFactory;

public class AuthService {
    private static User currentUser;
    private final CSVRepository repository;

    public AuthService() {
        this.repository = new CSVRepository();
    }

    public User login(String email, String password) {
        User user = repository.findUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return user;
        }
        return null;
    }

    public boolean register(String type, String name, String email,
                            String password, String departmentId, String idNumber) {

        // check password strength
        if (!PasswordValidator.isValid(password)) {
            return false;
        }

        // check email is unique
        if (repository.findUserByEmail(email) != null) {
            return false;
        }

        // generate unique ID
        String userId = type.toUpperCase().substring(0, 3) + "-" + System.currentTimeMillis();

        // create the user via factory
        User user = UserFactory.createUser(type, userId, name, email, password, departmentId, idNumber);

        // wrap with decorator if university affiliated
        if (requiresApproval(type)) {
            user = new UserDecorator(user, "APPROVAL");
        } else if (requiresCertification(type)) {
            user = new UserDecorator(user, "CERTIFICATION");
        }

        // save to CSV
        repository.saveUser(user);
        return true;
    }

    public boolean isApproved(String email) {
        User user = repository.findUserByEmail(email);
        if (user instanceof UserDecorator) {
            return ((UserDecorator) user).isApproved();
        }
        return true;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void logout() {
        currentUser = null;
    }

    // Test helper methods
    public static void setCurrentUserForTesting(User user) {
        currentUser = user;
    }

    public static void clearCurrentUserForTesting() {
        currentUser = null;
    }

    private boolean requiresApproval(String type) {
        return type.equalsIgnoreCase("STUDENT") ||
                type.equalsIgnoreCase("FACULTY") ||
                type.equalsIgnoreCase("RESEARCHER");
    }

    private boolean requiresCertification(String type) {
        return type.equalsIgnoreCase("RESEARCHER");
    }
}
