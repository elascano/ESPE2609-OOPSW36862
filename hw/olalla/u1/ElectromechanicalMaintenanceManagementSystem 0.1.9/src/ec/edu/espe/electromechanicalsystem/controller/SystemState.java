package ec.edu.espe.electromechanicalsystem.controller;

import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.*;
import java.io.*;
import java.util.ArrayList;

public class SystemState implements Serializable {
    private static final long serialVersionUID = 1L;
    public ArrayList<Client> clients = new ArrayList<>();
    public ArrayList<Equipment> equipments = new ArrayList<>();
    public ArrayList<Technician> technicians = new ArrayList<>();
    public ArrayList<WorkOrder> orders = new ArrayList<>();
    public ArrayList<MaintenanceSchedule> schedules = new ArrayList<>();
    public ArrayList<MaintenanceHistory> histories = new ArrayList<>();
    public ArrayList<Part> parts = new ArrayList<>();
    public ArrayList<SafetyRegulation> regulations = new ArrayList<>();
    public ArrayList<Alert> alerts = new ArrayList<>();
    public ArrayList<Report> reports = new ArrayList<>();
    public ArrayList<QrCode> qrCodes = new ArrayList<>();

    public static SystemState load(String file) {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
            Object value = input.readObject();
            if (value instanceof SystemState) return (SystemState) value;
        } catch (IOException | ClassNotFoundException ignored) {
        }
        return new SystemState();
    }

    public void save(String file) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file))) {
            output.writeObject(this);
        } catch (IOException exception) {
            System.out.println("Could not save application data: " + exception.getMessage());
        }
    }
}
