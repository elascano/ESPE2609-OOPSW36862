package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private int clientId;
    private String name;
    private String contactInfo;
    private String address;

    private List<Equipment> equipmentList = new ArrayList<>();

    public Client(int clientId, String name, String contactInfo, String address) {
        this.clientId = clientId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.address = address;
    }

    public int getClientId() {
        return clientId;
    }
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public void addEquipment(Equipment equipment) {
        if (equipment != null && !equipmentList.contains(equipment)) equipmentList.add(equipment);
    }

    public void removeEquipment(int equipmentId) {
        equipmentList.removeIf(equipment -> equipment.getEquipmentId() == equipmentId);
    }

    public Equipment[] getEquipmentList() {
        return equipmentList.toArray(new Equipment[0]);
    }

    @Override
    public String toString() {
        return "Client{" + "clientId=" + clientId + ", " + "name=" + name + ", " + "contactInfo=" + contactInfo + ", " + "address=" + address + "}";
    }
}
