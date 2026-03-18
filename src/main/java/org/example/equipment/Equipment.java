package org.example.equipment;

public class Equipment {
    private String equipmentId;
    private String description;
    private String labLocation;
    private EquipmentStatus status;

    public Equipment(String equipmentId, String description, String labLocation) {
        this.equipmentId = equipmentId;
        this.description = description;
        this.labLocation = labLocation;
        this.status = EquipmentStatus.AVAILABLE;
    }

    public String getEquipmentId() { return equipmentId; }
    public String getDescription() { return description; }
    public String getLabLocation() { return labLocation; }
    public EquipmentStatus getStatus() { return status; }
    public void setStatus(EquipmentStatus status) { this.status = status; }

    @Override
    public String toString() {
        return equipmentId + " - " + description + " (" + labLocation + ") [" + status + "]";
    }
}
