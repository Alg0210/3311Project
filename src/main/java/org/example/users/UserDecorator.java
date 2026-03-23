package org.example.users;

public class UserDecorator extends User {

    private User wrappedUser;
    private String decorationType;
    private boolean approved;
    private String certificationNumber;

    public UserDecorator(User user, String decorationType) {
        super(user.getUserId(), user.getName(), user.getEmail(),
                user.getPassword(), user.getUserType(),
                user.getDepartmentId(), user.getIdNumber());
        this.wrappedUser = user;
        this.decorationType = decorationType;
        this.approved = false;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getDecorationType() {
        return decorationType;
    }

    public String getCertificationNumber() {
        return certificationNumber;
    }

    @Override
    public String getUserType() {
        return wrappedUser.getUserType();
    }

}
