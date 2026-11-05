package ec.edu.espe.electromechanicalmaintenancemanagementsystem.view;

import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.Client;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.Equipment;
import ec.edu.espe.electromechanicalsystem.controller.ClientController;
import java.util.Arrays;
import java.util.List;

/** Console screens for clients. */
public class ClientView {
    private final ClientController controller;
    private final ConsoleInput input;

    public ClientView(ClientController controller, ConsoleInput input) {
        this.controller = controller;
        this.input = input;
    }

    public void register() {
        String name = input.readName("Client name (letters only): ");
        String contact = input.readPhone("Contact information (10 digits starting with 0): ");
        String address = input.readText("Address: ");
        Client client = controller.register(name, contact, address);
        System.out.println("Client saved.");
        System.out.println(client);
    }

    public void list() {
        ConsoleOutput.show(controller.findAll());
    }

    /** Shows the clients and asks for one. Returns null (after telling the user) if none is selected. */
    public Client select() {
        if (controller.findAll().isEmpty()) {
            System.out.println("No clients registered. Register a client first.");
            return null;
        }
        list();
        Client client = controller.findById(input.readPositiveInt("Client ID: "));
        if (client == null) {
            System.out.println("Client not found.");
        }
        return client;
    }

    public void edit() {
        Client client = select();
        if (client == null) {
            return;
        }
        System.out.println("1. Name\n2. Contact phone\n3. Address");
        switch (input.readIntInRange("Field to edit: ", 1, 3)) {
            case 1:
                client.setName(input.readName("New name: "));
                break;
            case 2:
                client.setContactInfo(input.readPhone("New contact phone: "));
                break;
            default:
                client.setAddress(input.readText("New address: "));
                break;
        }
        System.out.println("Client updated: " + client);
    }

    public void delete() {
        Client client = select();
        if (client == null) {
            return;
        }
        if (!input.readBoolean("Confirm permanent deletion of " + client.getName() + " (yes/no): ")) {
            System.out.println("Deletion cancelled.");
            return;
        }
        controller.delete(client);
        System.out.println("Client deleted. Equipment records are kept in the system.");
    }

    public void removeEquipment() {
        Client client = select();
        if (client == null) {
            return;
        }
        List<Equipment> equipment = Arrays.asList(client.getEquipmentList());
        ConsoleOutput.show(equipment);
        int equipmentId = input.readPositiveInt("Equipment ID to remove from client: ");
        if (!controller.removeEquipment(client, equipmentId)) {
            System.out.println("That equipment is not assigned to the selected client.");
            return;
        }
        System.out.println("Association removed. The equipment record remains in the system.");
        System.out.println("Updated client equipment list:");
        ConsoleOutput.show(Arrays.asList(client.getEquipmentList()));
    }

    public void search() {
        List<Client> matches = controller.search(input.readText("Search term (ID, name or phone): "));
        ConsoleOutput.show(matches);
    }
}
