package ec.edu.espe.electromechanicalsystem.controller;

import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.Client;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.Equipment;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Business rules for clients. */
public class ClientController {
    private final SystemState state;

    public ClientController(SystemState state) {
        this.state = state;
    }

    public List<Client> findAll() {
        return state.clients;
    }

    public Client findById(int id) {
        for (Client client : state.clients) {
            if (client.getClientId() == id) {
                return client;
            }
        }
        return null;
    }

    public Client register(String name, String contactInfo, String address) {
        int id = IdGenerator.nextId(state.clients, Client::getClientId);
        Client client = new Client(id, name, contactInfo, address);
        state.clients.add(client);
        return client;
    }

    /** Deletes the client only; its equipment records remain in the system. */
    public void delete(Client client) {
        state.clients.remove(client);
    }

    public List<Client> search(String term) {
        String text = term.toLowerCase(Locale.ROOT);
        List<Client> matches = new ArrayList<>();
        for (Client client : state.clients) {
            if (String.valueOf(client.getClientId()).equals(text)
                    || client.getName().toLowerCase(Locale.ROOT).contains(text)
                    || client.getContactInfo().contains(text)) {
                matches.add(client);
            }
        }
        return matches;
    }

    public List<Client> ownersOf(Equipment equipment) {
        List<Client> owners = new ArrayList<>();
        for (Client client : state.clients) {
            if (ownsEquipment(client, equipment.getEquipmentId())) {
                owners.add(client);
            }
        }
        return owners;
    }

    public boolean ownsEquipment(Client client, int equipmentId) {
        for (Equipment equipment : client.getEquipmentList()) {
            if (equipment.getEquipmentId() == equipmentId) {
                return true;
            }
        }
        return false;
    }

    /** Removes only the client-equipment association. Returns false if the client does not own it. */
    public boolean removeEquipment(Client client, int equipmentId) {
        if (!ownsEquipment(client, equipmentId)) {
            return false;
        }
        client.removeEquipment(equipmentId);
        return true;
    }
}
