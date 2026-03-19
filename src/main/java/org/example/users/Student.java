package org.example.users;

public class Student extends User {

    public Student(String userId, String name, String email, String password, String departmentId, String idNumber) {
        super(userId, name, email, password, "STUDENT", departmentId, idNumber);
    }

    @Override
    public String getUserType() {
        return "STUDENT";
    }
}
