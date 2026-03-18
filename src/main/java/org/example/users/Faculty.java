package org.example.users;

public class Faculty extends User {

    public Faculty(String userId, String name, String email, String password, String departmentId, String idNumber) {
        super(userId, name, email, password, "FACULTY", departmentId, idNumber);
    }

    @Override
    public double getHourlyRate() { return 15.0; }

    @Override
    public String getUserType() { return "FACULTY"; }
}
