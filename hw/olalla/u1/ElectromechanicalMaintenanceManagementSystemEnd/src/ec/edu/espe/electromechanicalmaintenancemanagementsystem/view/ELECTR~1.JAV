package ec.edu.espe.electromechanicalmaintenancemanagementsystem.view;

import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.*;
import ec.edu.espe.electromechanicalsystem.controller.JsonFileManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ElectromechanicalMaintenanceManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JsonFileManager clientFileManager = new JsonFileManager("clients.json");
        ArrayList<Client> clients = new ArrayList<>(clientFileManager.loadClients());
        ArrayList<Equipment> equipments = new ArrayList<>();
        ArrayList<Technician> technicians = new ArrayList<>();
        ArrayList<WorkOrder> orders = new ArrayList<>();
        ArrayList<MaintenanceSchedule> schedules = new ArrayList<>();
        ArrayList<MaintenanceHistory> histories = new ArrayList<>();
        ArrayList<Part> parts = new ArrayList<>();
        ArrayList<SafetyRegulation> regulations = new ArrayList<>();
        ArrayList<Alert> alerts = new ArrayList<>();
        ArrayList<Report> reports = new ArrayList<>();
        ArrayList<QrCode> qrCodes = new ArrayList<>();
        int option;

        do {
            System.out.println("\n===== ELECTROMECHANICAL MAINTENANCE MANAGEMENT SYSTEM =====");
            System.out.println("1. Register client");
            System.out.println("2. Register equipment for a client");
            System.out.println("3. Register technician");
            System.out.println("4. Schedule maintenance");
            System.out.println("5. Create work order");
            System.out.println("6. Assign technician to work order");
            System.out.println("7. Add task to work order");
            System.out.println("8. Register part and safety regulation");
            System.out.println("9. Execute task");
            System.out.println("10. Close work order and generate report");
            System.out.println("11. Record maintenance history");
            System.out.println("12. Show records");
            System.out.println("13. Remove equipment from client");
            System.out.println("14. Exit");
            option = readInt(scanner, "Choose an option: ");

            switch (option) {
                case 1: {
                    String name = readText(scanner, "Client name: ");
                    String contact = readText(scanner, "Contact information: ");
                    String address = readText(scanner, "Address: ");
                    Client client = new Client(clients.size() + 1, name, contact, address);
                    clients.add(client);
                    clientFileManager.saveClients(clients);
                    System.out.println("Client saved to clients.json.");
                    System.out.println(client);
                    break;
                }
                case 2: {
                    if (clients.isEmpty()) {
                        System.out.println("Register a client first.");
                        break;
                    }
                    show(clients);
                    int id = readInt(scanner, "Client ID: ");
                    Client client = findClient(clients, id);
                    if (client == null) {
                        System.out.println("Client not found.");
                        break;
                    }
                    String name = readText(scanner, "Equipment name: ");
                    String serial = readText(scanner, "Serial number: ");
                    String location = readText(scanner, "Location: ");
                    Equipment equipment = new Equipment(equipments.size() + 1, name, serial, location, "Available");
                    equipments.add(equipment);
                    client.addEquipment(equipment);
                    QrCode qr = equipment.createQrCode();
                    qrCodes.add(qr);
                    System.out.println(equipment);
                    System.out.println(qr);
                    break;
                }
                case 3: {
                    String name = readText(scanner, "Technician name: ");
                    String expertise = readText(scanner, "Expertise: ");
                    Technician technician = new Technician(technicians.size() + 1, name, expertise, true);
                    technicians.add(technician);
                    System.out.println(technician);
                    break;
                }
                case 4: {
                    if (equipments.isEmpty()) {
                        System.out.println("Register equipment first.");
                        break;
                    }
                    show(equipments);
                    Equipment equipment = findEquipment(equipments, readInt(scanner, "Equipment ID: "));
                    if (equipment == null) {
                        System.out.println("Equipment not found.");
                        break;
                    }
                    String frequency = readText(scanner, "Frequency: ");
                    String date = readText(scanner, "Scheduled date (YYYY-MM-DD): ");
                    boolean preventive = readBoolean(scanner, "Preventive maintenance (yes/no): ");
                    MaintenanceSchedule schedule = new MaintenanceSchedule(schedules.size() + 1, frequency, date, preventive);
                    schedules.add(schedule);
                    equipment.assignSchedule(schedule);
                    System.out.println(schedule);
                    break;
                }
                case 5: {
                    if (equipments.isEmpty()) {
                        System.out.println("Register equipment first.");
                        break;
                    }
                    show(equipments);
                    Equipment equipment = findEquipment(equipments, readInt(scanner, "Equipment ID: "));
                    if (equipment == null) {
                        System.out.println("Equipment not found.");
                        break;
                    }
                    WorkOrder order = equipment.requestMaintenanceOrder();
                    order.setWorkOrderId(orders.size() + 1);
                    order.setDescription(readText(scanner, "Work description: "));
                    order.setPriority(readText(scanner, "Priority: "));
                    orders.add(order);
                    equipment.updateStatus("Maintenance required");
                    System.out.println(order);
                    break;
                }
                case 6: {
                    if (orders.isEmpty() || technicians.isEmpty()) {
                        System.out.println("Register a technician and a work order first.");
                        break;
                    }
                    show(orders);
                    WorkOrder order = findOrder(orders, readInt(scanner, "Work order ID: "));
                    show(technicians);
                    Technician technician = findTechnician(technicians, readInt(scanner, "Technician ID: "));
                    if (order == null || technician == null || order.getStatus().equals("Closed") || order.getTechnician() != null || !technician.getAvailabilityStatus()) {
                        System.out.println("Invalid selection or technician unavailable.");
                        break;
                    }
                    technician.acceptWorkOrder(order);
                    order.setStatus("In progress");
                    System.out.println("Technician assigned: " + technician.getName());
                    break;
                }
                case 7: {
                    if (orders.isEmpty()) {
                        System.out.println("Create a work order first.");
                        break;
                    }
                    show(orders);
                    WorkOrder order = findOrder(orders, readInt(scanner, "Work order ID: "));
                    if (order == null || order.getStatus().equals("Closed")) {
                        System.out.println("Work order not found or closed.");
                        break;
                    }
                    MaintenanceTask task = order.createTask();
                    task.setTaskDescription(readText(scanner, "Task description: "));
                    task.setTaskType(readText(scanner, "Task type: "));
                    task.setEstimatedDuration(readFloat(scanner, "Estimated duration in hours: "));
                    System.out.println(task);
                    break;
                }
                case 8: {
                    if (orders.isEmpty()) {
                        System.out.println("Create a work order and task first.");
                        break;
                    }
                    show(orders);
                    WorkOrder order = findOrder(orders, readInt(scanner, "Work order ID: "));
                    if (order == null || order.getStatus().equals("Closed") || order.getTasks().isEmpty()) {
                        System.out.println("No open order with tasks found.");
                        break;
                    }
                    show(order.getTasks());
                    MaintenanceTask task = findTask(order, readInt(scanner, "Task ID: "));
                    if (task == null || task.getIsCompleted()) {
                        System.out.println("Task not found or already completed.");
                        break;
                    }
                    if (readBoolean(scanner, "Add a part (yes/no): ")) {
                        String name = readText(scanner, "Part name: ");
                        int quantity = readInt(scanner, "Stock quantity: ");
                        float cost = readFloat(scanner, "Unit cost: ");
                        String supplier = readText(scanner, "Supplier: ");
                        Part part = new Part(parts.size() + 1, name, quantity, cost, supplier);
                        parts.add(part);
                        task.recordPart(part);
                        System.out.println(part);
                    }
                    String title = readText(scanner, "Safety regulation title: ");
                    String description = readText(scanner, "Safety description: ");
                    String risk = readText(scanner, "Risk level: ");
                    SafetyRegulation regulation = new SafetyRegulation(regulations.size() + 1, title, description, risk);
                    regulations.add(regulation);
                    task.verifySafety(regulation);
                    System.out.println(regulation);
                    break;
                }
                case 9: {
                    show(orders);
                    WorkOrder order = findOrder(orders, readInt(scanner, "Work order ID: "));
                    if (order == null || order.getStatus().equals("Closed") || order.getTasks().isEmpty()) {
                        System.out.println("No open order with tasks found.");
                        break;
                    }
                    show(order.getTasks());
                    MaintenanceTask task = findTask(order, readInt(scanner, "Task ID: "));
                    if (task == null || task.getIsCompleted() || task.getRegulations().isEmpty()) {
                        System.out.println("Task not found, already completed, or missing safety verification.");
                        break;
                    }
                    task.executeTask();
                    System.out.println("Task completed: " + task.getIsCompleted());
                    break;
                }
                case 10: {
                    show(orders);
                    WorkOrder order = findOrder(orders, readInt(scanner, "Work order ID: "));
                    if (order == null || order.getStatus().equals("Closed") || order.getTasks().isEmpty()) {
                        System.out.println("No open work order with tasks found.");
                        break;
                    }
                    order.closeOrder();
                    if (!order.getStatus().equals("Closed")) {
                        System.out.println("Complete all tasks before closing the order.");
                        break;
                    }
                    Report report = order.createReport();
                    reports.add(report);
                    Alert alert = order.createAlert();
                    alert.setMessage("Work order completed");
                    alert.setIsResolved(true);
                    alerts.add(alert);
                    System.out.println(report);
                    System.out.println(alert);
                    break;
                }
                case 11: {
                    show(equipments);
                    Equipment equipment = findEquipment(equipments, readInt(scanner, "Equipment ID: "));
                    if (equipment == null) {
                        System.out.println("Equipment not found.");
                        break;
                    }
                    String type = readText(scanner, "Maintenance type: ");
                    String description = readText(scanner, "Description: ");
                    MaintenanceHistory history = new MaintenanceHistory(histories.size() + 1, LocalDate.now().toString(), type, description);
                    histories.add(history);
                    equipment.attachHistory(history);
                    System.out.println(history);
                    break;
                }
                case 12: {
                    System.out.println("\nCLIENTS"); show(clients);
                    System.out.println("\nEQUIPMENT"); show(equipments);
                    System.out.println("\nQR CODES"); show(qrCodes);
                    System.out.println("\nTECHNICIANS"); show(technicians);
                    System.out.println("\nMAINTENANCE SCHEDULES"); show(schedules);
                    System.out.println("\nWORK ORDERS"); show(orders);
                    for (WorkOrder order : orders) {
                        System.out.println("Tasks for work order " + order.getWorkOrderId());
                        show(order.getTasks());
                    }
                    System.out.println("\nPARTS"); show(parts);
                    System.out.println("\nSAFETY REGULATIONS"); show(regulations);
                    System.out.println("\nMAINTENANCE HISTORIES"); show(histories);
                    System.out.println("\nALERTS"); show(alerts);
                    System.out.println("\nREPORTS"); show(reports);
                    break;
                }
                case 13: {
                    show(clients);
                    Client client = findClient(clients, readInt(scanner, "Client ID: "));
                    if (client == null) {
                        System.out.println("Client not found.");
                        break;
                    }
                    show(java.util.Arrays.asList(client.getEquipmentList()));
                    int equipmentId = readInt(scanner, "Equipment ID to remove from client: ");
                    client.removeEquipment(equipmentId);
                    System.out.println("Updated client equipment list:");
                    show(java.util.Arrays.asList(client.getEquipmentList()));
                    break;
                }
                case 14:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 14);
    }

    private static int readInt(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println("Enter a valid integer.");
            }
        }
    }

    private static float readFloat(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                return Float.parseFloat(scanner.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException exception) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    private static String readText(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static boolean readBoolean(Scanner scanner, String message) {
        return readText(scanner, message).equalsIgnoreCase("yes");
    }

    private static void show(java.util.List<?> items) {
        if (items.isEmpty()) {
            System.out.println("No records.");
        }
        for (Object item : items) {
            System.out.println(item);
        }
    }

    private static Client findClient(ArrayList<Client> clients, int id) {
        for (Client client : clients) {
            if (client.getClientId() == id) return client;
        }
        return null;
    }

    private static Equipment findEquipment(ArrayList<Equipment> equipments, int id) {
        for (Equipment equipment : equipments) {
            if (equipment.getEquipmentId() == id) return equipment;
        }
        return null;
    }

    private static Technician findTechnician(ArrayList<Technician> technicians, int id) {
        for (Technician technician : technicians) {
            if (technician.getTechnicianId() == id) return technician;
        }
        return null;
    }

    private static WorkOrder findOrder(ArrayList<WorkOrder> orders, int id) {
        for (WorkOrder order : orders) {
            if (order.getWorkOrderId() == id) return order;
        }
        return null;
    }

    private static MaintenanceTask findTask(WorkOrder order, int id) {
        for (MaintenanceTask task : order.getTasks()) {
            if (task.getTaskId() == id) return task;
        }
        return null;
    }
}
