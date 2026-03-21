package org.example.users;

public class UserFactory {

    public static User createUser(String type, String userId, String name, String email, String password,
            String departmentId, String idNumber) {
        switch (type.toUpperCase()) {
            case "STUDENT":
                return new Student(userId, name, email, password, departmentId, idNumber);
            case "FACULTY":
                return new Faculty(userId, name, email, password, departmentId, idNumber);
            case "RESEARCHER":
                return new Researcher(userId, name, email, password, departmentId, idNumber);
            case "GUEST":
                return new Guest(userId, name, email, password, null, idNumber);
            case "MANAGER":
                return new LabManager(userId, name, email, password, "MGR-" + userId);
            case "HEAD_COORDINATOR":
                return HeadCoordinator.getInstance(userId, name, email, password);
            default:
                throw new IllegalArgumentException("Unknown user type: " + type);
        }
    }
}
