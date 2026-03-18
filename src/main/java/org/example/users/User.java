package org.example.users;

public abstract class User {
    protected String userId;
    protected String name;
    protected String email;
    protected String password;
    protected String userType;
    protected String departmentId;
    protected String idNumber; // student/staff ID or certification number

    public User(String userId, String name, String email, String password,
                String userType, String departmentId, String idNumber) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.departmentId = departmentId;
        this.idNumber = idNumber;
    }
    public abstract double getHourlyRate();
    public abstract String getUserType();


    public String getUserId() {
        return userId;
    }
    public String getName()
    { return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getDepartmentId() {
        return departmentId;
    }
    public String getIdNumber() {
        return idNumber;
    }
}
