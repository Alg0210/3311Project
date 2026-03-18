package org.example.sensors;

public interface SensorObservable {

    void addObserver(SensorObserver observer);
    void removeObserver(SensorObserver observer);
    void notifyObservers(String equipmentId, String status);
}
