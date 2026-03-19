package org.example.users;

public class Researcher extends User {

    public Researcher(String userId, String name, String email, String password, String departmentId, String idNumber) {
        super(userId, name, email, password, "RESEARCHER", departmentId, idNumber);
    }

    @Override
    public String getUserType() {
        return "RESEARCHER";
    }
}
