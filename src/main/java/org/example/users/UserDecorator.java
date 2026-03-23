package org.example.users;

public class UserDecorator extends User {

    private User wrappedUser;
    private String decorationType; // "APPROVAL", "CERTIFICATION"
    private String approvalStatus;
    private String certificationNumber;

    public UserDecorator(User user, String decorationType) {
        super(user.getUserId(), user.getName(), user.getEmail(),
                user.getPassword(), user.getUserType(),
                user.getDepartmentId(), user.getIdNumber());
        this.wrappedUser = user;
        this.decorationType = decorationType;
        this.approvalStatus = "NOT_APPROVED";
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public boolean isApproved() {
        return "APPROVED".equals(approvalStatus);
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
