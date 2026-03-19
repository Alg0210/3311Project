package org.example.users;

public class LabManager extends User {

    private String managerId;

    public LabManager(String userId, String name, String email, String password, String managerId) {
        super(userId, name, email, password, "MANAGER", null, null);
        this.managerId = managerId;
    }

    @Override
    public String getUserType() {
        return "MANAGER";
    }

    public String getManagerId() {
        return managerId;
    }
}
