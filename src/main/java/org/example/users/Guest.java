package org.example.users;

public class Guest extends User{

    public Guest(String userId, String name, String email, String password, String departmentId, String idNumber) {
        super(userId, name, email, password, "GUEST", departmentId, idNumber);
    }

    @Override
    public double getHourlyRate() { return 30.0; }

    @Override
    public String getUserType() { return "GUEST"; }
}
