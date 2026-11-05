package ec.edu.espe.electromechanicalmaintenancemanagementsystem.view;

import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.Technician;
import ec.edu.espe.electromechanicalsystem.controller.TechnicianController;

/** Console screens for technicians. */
public class TechnicianView {
    private final TechnicianController controller;
    private final ConsoleInput input;

    public TechnicianView(TechnicianController controller, ConsoleInput input) {
        this.controller = controller;
        this.input = input;
    }

    public void register() {
        String name = input.readName("Technician name: ");
        String expertise = input.readText("Expertise: ");
        System.out.println(controller.register(name, expertise));
    }

    public void list() {
        ConsoleOutput.show(controller.findAll());
    }

    /** Shows the technicians and asks for one. Returns null (after telling the user) if none is selected. */
    public Technician select() {
        if (controller.findAll().isEmpty()) {
            System.out.println("No technicians registered. Register a technician first.");
            return null;
        }
        list();
        Technician technician = controller.findById(input.readPositiveInt("Technician ID: "));
        if (technician == null) {
            System.out.println("Technician not found.");
        }
        return technician;
    }

    public void edit() {
        Technician technician = select();
        if (technician == null) {
            return;
        }
        System.out.println("1. Name\n2. Expertise\n3. Availability");
        switch (input.readIntInRange("Field to edit: ", 1, 3)) {
            case 1:
                technician.setName(input.readName("New name: "));
                break;
            case 2:
                technician.setExpertise(input.readText("New expertise: "));
                break;
            default:
                technician.setAvailabilityStatus(input.readBoolean("Technician available (yes/no): "));
                break;
        }
        System.out.println("Technician updated: " + technician);
    }

    public void delete() {
        Technician technician = select();
        if (technician == null) {
            return;
        }
        if (controller.isAssignedToOpenOrder(technician)) {
            System.out.println("This technician is assigned to an open work order and cannot be deleted.");
            return;
        }
        if (!input.readBoolean("Confirm permanent deletion of " + technician.getName() + " (yes/no): ")) {
            System.out.println("Deletion cancelled.");
            return;
        }
        controller.delete(technician);
        System.out.println("Technician deleted.");
    }
}
