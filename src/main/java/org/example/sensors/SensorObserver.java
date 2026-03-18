package org.example.sensors;

public interface SensorObserver {
    void update(String equipmentId, String status);
}
