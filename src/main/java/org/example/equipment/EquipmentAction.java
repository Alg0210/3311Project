package org.example.equipment;

public class EquipmentAction implements EquipmentCommand{

    private EquipmentManager manager;
    private Equipment equipment;
    private String actionType;
    private EquipmentStatus previousStatus;

    public EquipmentAction(EquipmentManager manager, Equipment equipment, String actionType) {
        this.manager = manager;
        this.equipment = equipment;
        this.actionType = actionType;
    }

    @Override
    public void execute() {
        previousStatus = equipment.getStatus();
        switch (actionType.toUpperCase()) {
            case "ADD":
                manager.addEquipment(equipment);
                break;
            case "ENABLE":
                manager.enableEquipment(equipment.getEquipmentId());
                break;
            case "DISABLE":
                manager.disableEquipment(equipment.getEquipmentId());
                break;
            case "MAINTENANCE":
                manager.setMaintenance(equipment.getEquipmentId());
                break;
            default:
                System.out.println("Unknown action: " + actionType);
        }
    }

    @Override
    public void undo() {
        if (previousStatus != null) {
            equipment.setStatus(previousStatus);
            manager.updateEquipmentStatus(equipment.getEquipmentId(), previousStatus);
        }
    }
}
