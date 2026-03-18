package org.example.users;

public class HeadCoordinator extends LabManager{

    private static HeadCoordinator instance; // Singleton

    private HeadCoordinator(String userId, String name, String email, String password) {
        super(userId, name, email, password, "HEAD");
    }

    public static HeadCoordinator getInstance(String userId, String name, String email, String password) {
        if (instance == null) {
            instance = new HeadCoordinator(userId, name, email, password);
        }
        return instance;
    }

    public LabManager generateManagerAccount(String userId, String name, String email, String password) {
        return new LabManager(userId, name, email, password, "MGR-" + userId);
    }

    @Override
    public String getUserType() { return "HEAD_COORDINATOR"; }
}
