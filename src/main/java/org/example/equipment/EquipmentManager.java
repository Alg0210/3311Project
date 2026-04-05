package org.example.equipment;

import org.example.data.CSVRepository;
import org.example.sensors.SensorObserver;
import java.util.List;


public class EquipmentManager implements SensorObserver{
    private final CSVRepository repository;

    public EquipmentManager() {
        this.repository = new CSVRepository();
    }

    public EquipmentManager(CSVRepository repository) { this.repository = repository; }

    // ─── Add, UPDATE EQUIPMENT ────────────────────────────────────────────────────

    public void addEquipment(Equipment equipment) {
        repository.saveEquipment(equipment);
    }

    public void updateEquipment(Equipment equipment) {
        repository.updateEquipment(equipment);
    }

    public List<Equipment> getAllEquipment() {
        return repository.getAllEquipment();
    }

    public Equipment getEquipmentById(String id) {
        for (Equipment e : getAllEquipment()) {
            if (e.getEquipmentId().equals(id)) return e;
        }
        return null;
    }

    public List<Equipment> getAvailableEquipment() {
        List<Equipment> all = getAllEquipment();
        all.removeIf(e -> e.getStatus() != EquipmentStatus.AVAILABLE);
        return all;
    }

    // ─── STATUS CHANGES ──────────────────────────────────────────

    public void enableEquipment(String id) {
        updateStatus(id, EquipmentStatus.AVAILABLE);
    }

    public void disableEquipment(String id) {
        updateStatus(id, EquipmentStatus.DISABLED);
    }

    public void setMaintenance(String id) {
        updateStatus(id, EquipmentStatus.MAINTENANCE);
    }

    private void updateStatus(String id, EquipmentStatus status) {
        Equipment equipment = getEquipmentById(id);
        if (equipment != null) {
            equipment.setStatus(status);
            repository.updateEquipment(equipment);
        }
    }

    public void updateEquipmentStatus(String id, EquipmentStatus status) {
        updateStatus(id, status);
    }

    // ─── OBSERVER ────────────────────────────────────────────────

    @Override
    public void update(String equipmentId, String status) {
        // called by sensor when equipment status changes
        try {
            EquipmentStatus newStatus = EquipmentStatus.valueOf(status.toUpperCase());
            updateStatus(equipmentId, newStatus);
            System.out.println("Sensor update received: " + equipmentId + " is now " + status);
        } catch (IllegalArgumentException e) {
            System.out.println("Unknown status received from sensor: " + status);
        }
    }

}
