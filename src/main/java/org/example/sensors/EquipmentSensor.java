package org.example.sensors;

import java.util.ArrayList;
import java.util.List;

public class EquipmentSensor implements SensorObservable {
    private String equipmentId;
    private String currentStatus;
    private List<SensorObserver> observers;

    public EquipmentSensor(String equipmentId) {
        this.equipmentId = equipmentId;
        this.currentStatus = "AVAILABLE";
        this.observers = new ArrayList<>();
    }

    // call this to simulate a sensor detecting a status change
    public void triggerUpdate(String newStatus) {
        this.currentStatus = newStatus;
        notifyObservers(equipmentId, newStatus);
    }

    @Override
    public void addObserver(SensorObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(SensorObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String equipmentId, String status) {
        for (SensorObserver observer : observers) {
            observer.update(equipmentId, status);
        }
    }

    public String getEquipmentId() { return equipmentId; }
    public String getCurrentStatus() { return currentStatus; }
}
