package org.example.equipment;

import java.util.ArrayList;
import java.util.List;

public class Equipment {
    private String equipmentId;
    private String name;
    private String description;
    private String labLocation;
    private EquipmentStatus status;
    private int availableUnits;
    private String productStatistics;
    private List<String> tags;
    private String imagePath;

    public Equipment(String equipmentId, String description, String labLocation) {
        this.equipmentId = equipmentId;
        this.name = description; // default name = description for backward compat
        this.description = description;
        this.labLocation = labLocation;
        this.status = EquipmentStatus.AVAILABLE;
        this.availableUnits = 1;
        this.productStatistics = "";
        this.tags = new ArrayList<>();
        this.imagePath = "";
    }

    public String getEquipmentId() { return equipmentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public String getLabLocation() { return labLocation; }

    public EquipmentStatus getStatus() { return status; }
    public void setStatus(EquipmentStatus status) { this.status = status; }

    public int getAvailableUnits() { return availableUnits; }
    public void setAvailableUnits(int availableUnits) { this.availableUnits = availableUnits; }

    public String getProductStatistics() { return productStatistics; }
    public void setProductStatistics(String productStatistics) { this.productStatistics = productStatistics; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    @Override
    public String toString() {
        return equipmentId + " - " + description + " (" + labLocation + ") [" + status + "]";
    }
}
