package ec.edu.espe.electromechanicalmaintenancemanagementsystem.view;

import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.Client;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.Equipment;
import ec.edu.espe.electromechanicalsystem.controller.ClientController;
import ec.edu.espe.electromechanicalsystem.controller.EquipmentController;
import ec.edu.espe.electromechanicalsystem.controller.WorkOrderController;
import java.util.List;

/** Console screens for equipment. */
public class EquipmentView {
    private final EquipmentController controller;
    private final ClientController clientController;
    private final WorkOrderController workOrderController;
    private final ClientView clientView;
    private final ConsoleInput input;

    public EquipmentView(EquipmentController controller, ClientController clientController,
            WorkOrderController workOrderController, ClientView clientView, ConsoleInput input) {
        this.controller = controller;
        this.clientController = clientController;
        this.workOrderController = workOrderController;
        this.clientView = clientView;
        this.input = input;
    }

    public void register() {
        Client client = clientView.select();
        if (client == null) {
            return;
        }
        String name = input.readName("Equipment name: ");
        String serial = input.readText("Serial number: ");
        String location = input.readText("Location: ");
        Equipment equipment = controller.register(client, name, serial, location);
        System.out.println(equipment);
        System.out.println(equipment.getQrCode());
    }

    /** Shows the equipment and asks for one. Returns null (after telling the user) if none is selected. */
    public Equipment select() {
        if (controller.findAll().isEmpty()) {
            System.out.println("No equipment registered. Register equipment first.");
            return null;
        }
        ConsoleOutput.show(controller.findAll());
        Equipment equipment = controller.findById(input.readPositiveInt("Equipment ID: "));
        if (equipment == null) {
            System.out.println("Equipment not found.");
        }
        return equipment;
    }

    public void update() {
        Equipment equipment = select();
        if (equipment == null) {
            return;
        }
        equipment.setName(input.readName("New equipment name: "));
        equipment.setSerialNumber(input.readText("New serial number: "));
        equipment.setLocation(input.readText("New location: "));
        equipment.setStatus(input.readText("New status: "));
        System.out.println("Equipment updated: " + equipment);
    }

    public void search() {
        List<Equipment> matches = controller.search(input.readText("Search term (ID, name or serial number): "));
        ConsoleOutput.show(matches);
        for (Equipment equipment : matches) {
            printOwners(equipment);
        }
    }

    public void consultByQrOrSerial() {
        String code = input.readText("Scan/paste QR payload, QR ID, or equipment serial number: ");
        Equipment equipment = controller.findByQrOrSerial(code);
        if (equipment == null) {
            System.out.println("No equipment matches that QR payload or serial number.");
            return;
        }
        System.out.println("Equipment information: " + equipment);
        printOwners(equipment);
        System.out.println("QR payload: " + equipment.getQrCode().getGenerateUrl());
    }

    public void showMaintenanceHistory() {
        Equipment equipment = select();
        if (equipment == null) {
            return;
        }
        System.out.println("History recorded for equipment:");
        ConsoleOutput.show(equipment.getMaintenanceHistories());
        System.out.println("Scheduled maintenance:");
        ConsoleOutput.show(equipment.getMaintenanceSchedules());
        System.out.println("Work orders:");
        ConsoleOutput.show(workOrderController.ordersFor(equipment));
    }

    private void printOwners(Equipment equipment) {
        for (Client client : clientController.ownersOf(equipment)) {
            System.out.println("Client: " + client.getName() + " (ID " + client.getClientId() + ")");
        }
    }
}
