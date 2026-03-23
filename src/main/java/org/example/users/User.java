package org.example.users;

public abstract class User {
    protected String userId;
    protected String name;
    protected String email;
    protected String password;
    protected String userType;
    protected String departmentId;
    protected String idNumber;

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

    public abstract String getUserType();

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
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

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
