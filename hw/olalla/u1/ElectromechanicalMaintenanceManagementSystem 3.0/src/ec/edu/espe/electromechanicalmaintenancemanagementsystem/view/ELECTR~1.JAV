package ec.edu.espe.electromechanicalmaintenancemanagementsystem.view;

import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.*;
import ec.edu.espe.electromechanicalsystem.controller.JsonFileManager;
import ec.edu.espe.electromechanicalsystem.controller.ReportJsonFileManager;
import ec.edu.espe.electromechanicalsystem.controller.SystemState;
import java.time.LocalDate;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.Scanner;

public class ElectromechanicalMaintenanceManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JsonFileManager clientFileManager = new JsonFileManager("clients.json");
        ReportJsonFileManager reportFileManager = new ReportJsonFileManager("reports.json");
        SystemState state = SystemState.load("maintenance_data.ser");
        if (state.clients.isEmpty()) state.clients = new ArrayList<>(clientFileManager.loadClients());
        if (state.reports.isEmpty()) state.reports = new ArrayList<>(reportFileManager.loadReports());
        ArrayList<Client> clients = state.clients;
        ArrayList<Equipment> equipments = state.equipments;
        ArrayList<Technician> technicians = state.technicians;
        ArrayList<WorkOrder> orders = state.orders;
        ArrayList<MaintenanceSchedule> schedules = state.schedules;
        ArrayList<MaintenanceHistory> histories = state.histories;
        ArrayList<Part> parts = state.parts;
        ArrayList<SafetyRegulation> regulations = state.regulations;
        ArrayList<Alert> alerts = state.alerts;
        ArrayList<Report> reports = state.reports;
        ArrayList<QrCode> qrCodes = state.qrCodes;
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
            System.out.println("14. Generate CSV Report (Excel)");
            System.out.println("15. Edit or delete a client");
            System.out.println("17. Search clients and equipment");
            System.out.println("18. Update equipment information");
            System.out.println("19. Search complete equipment maintenance history");
            System.out.println("20. Consult equipment by QR code or serial number");
            System.out.println("21. Show upcoming maintenance alerts");
            System.out.println("22. Update technician availability");
            System.out.println("23. Update client contact and address");
            System.out.println("24. Exit");
            option = readInt(scanner, "Choose an option: ");

            switch (option) {
                
case 1: {
    String name = readName(scanner, "Client name (letters only): ");
    String contact;

    while (true) {
        contact = readText(scanner,
                "Contact information (10 digits starting with 0): ");

        if (contact.matches("0[0-9]{9}")) {
            break;
        }

        System.out.println(
                "Invalid phone number. It must contain exactly 10 digits and start with 0."
        );
    }

    String address = readText(scanner, "Address: ");

   int newId = 1;

for (Client c : clients) {
    if (c.getClientId() >= newId) {
        newId = c.getClientId() + 1;
    }
}

Client client = new Client(newId, name, contact, address);

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
                    String name = readName(scanner, "Equipment name: ");
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
                    String name = readName(scanner, "Technician name: ");
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
                    String date = readDate(scanner, "Scheduled date (YYYY-MM-DD, today or future within this year): ");
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
                    order.setDescription("Equipment ID: " + equipment.getEquipmentId() + " | " + readText(scanner, "Work description: "));
                    order.setPriority(readChoice(scanner, "Priority (low/medium/high): ", "low", "medium", "high"));
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
                    task.setEstimatedDuration(readNonNegativeFloat(scanner, "Estimated duration in hours (0 or greater): "));
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
                        int quantity = readNonNegativeInt(scanner, "Stock quantity (0 or greater): ");
                        float cost = readNonNegativeFloat(scanner, "Unit cost (0 or greater): ");
                        String supplier = readText(scanner, "Supplier: ");
                        Part part = new Part(parts.size() + 1, name, quantity, cost, supplier);
                        parts.add(part);
                        task.recordPart(part);
                        System.out.println(part);
                    }
                    String title = readText(scanner, "Safety regulation title: ");
                    String description = readText(scanner, "Safety description: ");
                    String risk = readChoice(scanner, "Risk level (low/medium/high/critical): ", "low", "medium", "high", "critical");
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
                    int equipmentIdForHistory = -1;
                    java.util.regex.Matcher equipmentMatcher = java.util.regex.Pattern.compile("Equipment ID: (\\d+)").matcher(order.getDescription());
                    if (equipmentMatcher.find()) equipmentIdForHistory = Integer.parseInt(equipmentMatcher.group(1));
                    Equipment maintainedEquipment = findEquipment(equipments, equipmentIdForHistory);
                    if (maintainedEquipment != null) {
                        StringBuilder maintenanceDetails = new StringBuilder(order.getDescription());
                        for (MaintenanceTask completedTask : order.getTasks()) {
                            maintenanceDetails.append(" | Task: ").append(completedTask.getTaskDescription());
                            for (Part usedPart : completedTask.getParts()) maintenanceDetails.append(" | Part: ").append(usedPart.getName()).append(" x ").append(usedPart.getStockQuantity());
                        }
                        MaintenanceHistory automaticHistory = new MaintenanceHistory(histories.size() + 1, LocalDate.now().toString(), "Corrective/Completed work order", maintenanceDetails.toString());
                        histories.add(automaticHistory);
                        maintainedEquipment.attachHistory(automaticHistory);
                    }
                    Report report = order.createReport();
                    report.setReportId(nextReportId(reports));
                    reports.add(report);
                    reportFileManager.saveReports(reports);
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
                    boolean belongsToClient = false;
                    for (Equipment item : client.getEquipmentList()) {
                        if (item.getEquipmentId() == equipmentId) {
                            belongsToClient = true;
                            break;
                        }
                    }
                    if (!belongsToClient) {
                        System.out.println("That equipment is not assigned to the selected client.");
                        break;
                    }
                    client.removeEquipment(equipmentId);
                    System.out.println("Equipment association removed from this client. The equipment record remains in the system.");
                    System.out.println("Updated client equipment list:");
                    show(java.util.Arrays.asList(client.getEquipmentList()));
                    break;
                }
                case 14:
                    exportCsv("maintenance_report.csv", clients, equipments, technicians, schedules,
                            orders, histories, parts, regulations, alerts, reports, qrCodes);
                    break;
                case 15: {
                    if (clients.isEmpty()) {
                        System.out.println("No clients registered.");
                        break;
                    }
                    System.out.println("1. Correct a client's name");
                    System.out.println("2. Delete a client completely");
                    int action = readIntInRange(scanner, "Choose: ", 1, 2);
                    show(clients);
                    Client selected = findClient(clients, readPositiveInt(scanner, "Client ID: "));
                    if (selected == null) {
                        System.out.println("Client not found.");
                        break;
                    }
                    if (action == 1) {
                        String correctedName = readName(scanner, "Corrected client name: ");
                        selected.setName(correctedName);
                        clientFileManager.saveClients(clients);
                        System.out.println("Client name updated and saved.");
                    } else {
                        if (readBoolean(scanner, "Confirm permanent deletion (yes/no): ")) {
                            clients.remove(selected);
                            clientFileManager.saveClients(clients);
                            System.out.println("Client deleted from clients.json.");
                            System.out.println("Note: equipment records held only in memory are not automatically deleted.");
                        } else {
                            System.out.println("Deletion cancelled.");
                        }
                    }
                    break;
                }
                case 24:
                    System.out.println("Goodbye!");
                    break;
                case 17: {
                    System.out.println("1. Search client by ID or name");
                    System.out.println("2. Search equipment by ID, name or serial number");
                    int searchType = readIntInRange(scanner, "Choose: ", 1, 2);
                    String term = readText(scanner, "Search term: ").toLowerCase(java.util.Locale.ROOT);
                    if (searchType == 1) {
                        for (Client c : clients) if (String.valueOf(c.getClientId()).equals(term) || c.getName().toLowerCase(java.util.Locale.ROOT).contains(term) || c.getContactInfo().contains(term)) System.out.println(c);
                    } else {
                        for (Equipment e : equipments) if (String.valueOf(e.getEquipmentId()).equals(term) || e.getName().toLowerCase(java.util.Locale.ROOT).contains(term) || e.getSerialNumber().toLowerCase(java.util.Locale.ROOT).contains(term)) {
                            System.out.println(e);
                            for (Client c : clients) for (Equipment ce : c.getEquipmentList()) if (ce.getEquipmentId() == e.getEquipmentId()) System.out.println("Client: " + c.getName() + " (ID " + c.getClientId() + ")");
                        }
                    }
                    break;
                }
                case 18: {
                    show(equipments);
                    Equipment e = findEquipment(equipments, readPositiveInt(scanner, "Equipment ID: "));
                    if (e == null) { System.out.println("Equipment not found."); break; }
                    e.setName(readName(scanner, "New equipment name: "));
                    e.setSerialNumber(readText(scanner, "New serial number: "));
                    e.setLocation(readText(scanner, "New location: "));
                    e.setStatus(readText(scanner, "New status: "));
                    System.out.println("Equipment updated: " + e);
                    break;
                }
                case 19: {
                    show(equipments);
                    Equipment e = findEquipment(equipments, readPositiveInt(scanner, "Equipment ID: "));
                    if (e == null) { System.out.println("Equipment not found."); break; }
                    System.out.println("History recorded directly for equipment:");
                    show(e.getMaintenanceHistories());
                    System.out.println("Scheduled maintenance:");
                    show(e.getMaintenanceSchedules());
                    System.out.println("Work orders:");
                    for (WorkOrder o : orders) if (o.getDescription().toLowerCase(java.util.Locale.ROOT).contains(e.getName().toLowerCase(java.util.Locale.ROOT)) || o.getDescription().toLowerCase(java.util.Locale.ROOT).contains("equipment id: " + e.getEquipmentId())) System.out.println(o);
                    break;
                }
                case 20: {
                    String code = readText(scanner, "Scan/paste QR payload, QR ID, or equipment serial number: ");
                    Equipment found = null;
                    for (Equipment e : equipments) {
                        QrCode q = e.createQrCode();
                        if (code.equalsIgnoreCase(String.valueOf(q.getQrId())) || code.equalsIgnoreCase(q.getCodeData()) || code.equalsIgnoreCase(q.getGenerateUrl()) || code.equalsIgnoreCase(e.getSerialNumber())) { found = e; break; }
                    }
                    if (found == null) System.out.println("No equipment matches that QR payload or serial number.");
                    else {
                        System.out.println("Equipment information: " + found);
                        for (Client c : clients) for (Equipment ce : c.getEquipmentList()) if (ce.getEquipmentId() == found.getEquipmentId()) System.out.println("Client: " + c);
                        System.out.println("QR payload: " + found.createQrCode().getGenerateUrl());
                    }
                    break;
                }
                case 21: {
                    LocalDate today = LocalDate.now();
                    boolean any = false;
                    for (MaintenanceSchedule schedule : schedules) {
                        try {
                            LocalDate due = LocalDate.parse(schedule.getScheduleDate());
                            if (!due.isBefore(today) && !due.isAfter(today.plusDays(30))) {
                                any = true;
                                System.out.println("UPCOMING: " + schedule);
                            } else if (due.isBefore(today)) {
                                any = true;
                                System.out.println("OVERDUE: " + schedule);
                            }
                        } catch (RuntimeException ignored) {
                        }
                    }
                    for (Alert alert : alerts) if (!alert.getIsResolved()) { any = true; System.out.println("PENDING ALERT: " + alert); }
                    if (!any) System.out.println("No upcoming or overdue maintenance in the next 30 days.");
                    break;
                }
                case 22: {
                    show(technicians);
                    Technician t = findTechnician(technicians, readPositiveInt(scanner, "Technician ID: "));
                    if (t == null) { System.out.println("Technician not found."); break; }
                    t.setAvailabilityStatus(readBoolean(scanner, "Technician available (yes/no): "));
                    System.out.println("Technician availability updated: " + t);
                    break;
                }
                case 23: {
                    show(clients);
                    Client c = findClient(clients, readPositiveInt(scanner, "Client ID: "));
                    if (c == null) { System.out.println("Client not found."); break; }
                    c.setName(readName(scanner, "Client name: "));
                    c.setContactInfo(readPhone(scanner, "Contact phone: "));
                    c.setAddress(readText(scanner, "Address: "));
                    clientFileManager.saveClients(clients);
                    System.out.println("Client information updated.");
                    break;
                }
                default:
                    System.out.println("Invalid option.");
            }
            state.clients = clients;
            state.equipments = equipments;
            state.technicians = technicians;
            state.orders = orders;
            state.schedules = schedules;
            state.histories = histories;
            state.parts = parts;
            state.regulations = regulations;
            state.alerts = alerts;
            state.reports = reports;
            state.qrCodes = qrCodes;
            state.save("maintenance_data.ser");
            clientFileManager.saveClients(clients);
            reportFileManager.saveReports(reports);
        } while (option != 16 && option != 24);
    }

private static void exportCsv(String fileName,
        ArrayList<Client> clients,
        ArrayList<Equipment> equipments,
        ArrayList<Technician> technicians,
        ArrayList<MaintenanceSchedule> schedules,
        ArrayList<WorkOrder> orders,
        ArrayList<MaintenanceHistory> histories,
        ArrayList<Part> parts,
        ArrayList<SafetyRegulation> regulations,
        ArrayList<Alert> alerts,
        ArrayList<Report> reports,
        ArrayList<QrCode> qrCodes) {

    try {
        Path output = Path.of(fileName).toAbsolutePath().normalize();
        Path folder = output.getParent();

        if (folder == null) {
            folder = Path.of(".").toAbsolutePath().normalize();
        }

        Files.createDirectories(folder);
        int count = 0;

        count += exportCsvFile(folder.resolve("clients.csv"),
                Client.class, clients);

        count += exportCsvFile(folder.resolve("equipments.csv"),
                Equipment.class, equipments);

        count += exportCsvFile(folder.resolve("technicians.csv"),
                Technician.class, technicians);

        count += exportCsvFile(folder.resolve("maintenance_schedules.csv"),
                MaintenanceSchedule.class, schedules);

        count += exportCsvFile(folder.resolve("work_orders.csv"),
                WorkOrder.class, orders);

        ArrayList<MaintenanceTask> tasks = new ArrayList<>();

        for (WorkOrder order : orders) {
            tasks.addAll(order.getTasks());
        }

        count += exportCsvFile(folder.resolve("maintenance_tasks.csv"),
                MaintenanceTask.class, tasks);

        count += exportCsvFile(folder.resolve("maintenance_history.csv"),
                MaintenanceHistory.class, histories);

        count += exportCsvFile(folder.resolve("parts.csv"),
                Part.class, parts);

        count += exportCsvFile(folder.resolve("safety_regulations.csv"),
                SafetyRegulation.class, regulations);

        count += exportCsvFile(folder.resolve("alerts.csv"),
                Alert.class, alerts);

        count += exportCsvFile(folder.resolve("reports.csv"),
                Report.class, reports);

        count += exportCsvFile(folder.resolve("qr_codes.csv"),
                QrCode.class, qrCodes);

        System.out.println("CSV files generated successfully.");
        System.out.println("Folder: " + folder);
        System.out.println("Records exported: " + count);

    } catch (IOException | SecurityException exception) {
        System.out.println("Could not generate CSV files: "
                + exception.getMessage());
    } catch (InvalidPathException exception) {
        System.out.println("Invalid file path: "
                + exception.getMessage());
    }
}

private static int exportCsvFile(Path output, Class<?> recordClass,
        java.util.List<?> records) throws IOException {

    java.util.List<java.lang.reflect.Method> getters =
            new ArrayList<>();

    for (java.lang.reflect.Method method : recordClass.getMethods()) {
        String name = method.getName();

        boolean getter = (name.startsWith("get") && name.length() > 3)
                || (name.startsWith("is") && name.length() > 2);

        if (!getter || method.getParameterCount() != 0
                || name.equals("getClass")) {
            continue;
        }

        Class<?> type = method.getReturnType();

        boolean simple = type.isPrimitive()
                || type == String.class
                || Number.class.isAssignableFrom(type)
                || type == Boolean.class
                || type == Character.class
                || type.isEnum()
                || java.util.Date.class.isAssignableFrom(type)
                || type.getName().startsWith("java.time.");

        if (simple) {
            getters.add(method);
        }
    }

    getters.sort((a, b) -> {
        String nameA = csvPropertyName(a);
        String nameB = csvPropertyName(b);

        boolean idA = nameA.toLowerCase().endsWith("id");
        boolean idB = nameB.toLowerCase().endsWith("id");

        if (idA != idB) {
            return idA ? -1 : 1;
        }

        return nameA.compareToIgnoreCase(nameB);
    });

    try (BufferedWriter writer = Files.newBufferedWriter(
            output, StandardCharsets.UTF_8)) {

        writer.write("\uFEFF");

        for (int i = 0; i < getters.size(); i++) {
            if (i > 0) {
                writer.write(',');
            }

            writer.write(csvCell(
                    csvHeader(csvPropertyName(getters.get(i)))));
        }

        writer.newLine();

        int count = 0;

        for (Object record : records) {
            for (int i = 0; i < getters.size(); i++) {
                if (i > 0) {
                    writer.write(',');
                }

                try {
                    Object value = getters.get(i).invoke(record);

                    writer.write(csvCell(
                            value == null ? "" : String.valueOf(value)));

                } catch (ReflectiveOperationException exception) {
                    throw new IOException(
                            "Could not read field from "
                            + recordClass.getSimpleName(),
                            exception);
                }
            }

            writer.newLine();
            count++;
        }

        System.out.println("Generated: " + output.getFileName());
        return count;
    }
}

private static String csvPropertyName(
        java.lang.reflect.Method method) {

    String name = method.getName();

    if (name.startsWith("get")) {
        name = name.substring(3);
    } else if (name.startsWith("is")) {
        name = name.substring(2);
    }

    if (name.startsWith("Is") && name.length() > 2) {
        name = name.substring(2);
    }

    return Character.toLowerCase(name.charAt(0))
            + name.substring(1);
}

private static String csvHeader(String property) {
    String spaced = property.replaceAll("([a-z])([A-Z])", "$1 $2");
    return Character.toUpperCase(spaced.charAt(0))
            + spaced.substring(1);
}

private static String csvCell(String value) {
    if (value == null) {
        value = "";
    }

    if (!value.isEmpty()
            && (value.charAt(0) == '='
            || value.charAt(0) == '+'
            || value.charAt(0) == '-'
            || value.charAt(0) == '@'
            || value.charAt(0) == '\t'
            || value.charAt(0) == '\r')) {
        value = "'" + value;
    }

    return "\"" + value.replace("\"", "\"\"") + "\"";
}

private static int readInt(Scanner scanner, String message) {
    while (true) {
        try {
            System.out.print(message);
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException exception) {
            System.out.println("Invalid input. Enter a whole number.");
        }
    }
}

private static int readIntInRange(
        Scanner scanner, String message, int min, int max) {
    while (true) {
        int value = readInt(scanner, message);

        if (value >= min && value <= max) {
            return value;
        }

        System.out.println("Enter a number between " + min + " and " + max + ".");
    }
}

private static int readNonNegativeInt(Scanner scanner, String message) {
    while (true) {
        int value = readInt(scanner, message);
        if (value >= 0) return value;
        System.out.println("The value cannot be negative.");
    }
}

private static int readPositiveInt(Scanner scanner, String message) {
    while (true) {
        int value = readInt(scanner, message);

        if (value > 0) {
            return value;
        }

        System.out.println("The value must be greater than zero.");
    }
}

private static float readFloat(Scanner scanner, String message) {
    while (true) {
        try {
            System.out.print(message);
            float value = Float.parseFloat(
                    scanner.nextLine().trim().replace(',', '.'));

            if (Float.isFinite(value)) {
                return value;
            }

            System.out.println("Enter a finite number.");
        } catch (NumberFormatException exception) {
            System.out.println("Invalid number. Example: 12.50");
        }
    }
}

private static float readPositiveFloat(
        Scanner scanner, String message) {
    while (true) {
        float value = readFloat(scanner, message);

        if (value > 0) {
            return value;
        }

        System.out.println("The value must be greater than zero.");
    }
}

private static float readNonNegativeFloat(
        Scanner scanner, String message) {
    while (true) {
        float value = readFloat(scanner, message);

        if (value >= 0) {
            return value;
        }

        System.out.println("The value cannot be negative.");
    }
}

private static String readText(Scanner scanner, String message) {
    while (true) {
        System.out.print(message);
        String value = scanner.nextLine().trim();

        if (!value.isEmpty()) {
            return value;
        }

        System.out.println("This field cannot be empty.");
    }
}

private static String readName(Scanner scanner, String message) {
    while (true) {
        String name = readText(scanner, message);

        if (name.matches("[\\p{L}][\\p{L} .'-]*")) {
            return name;
        }

        System.out.println(
                "Use letters only. Spaces, accents, apostrophes "
                + "and hyphens are allowed.");
    }
}

private static String readPhone(Scanner scanner, String message) {
    while (true) {
        String phone = readText(scanner, message);

        if (phone.matches("0[0-9]{9}")) {
            return phone;
        }

        System.out.println(
                "The phone number must contain exactly 10 digits "
                + "and start with 0.");
    }
}

private static String readDate(Scanner scanner, String message) {
    while (true) {
        String input = readText(scanner, message);

        try {
            LocalDate date = LocalDate.parse(input);

            if (date.isBefore(LocalDate.now())) {
                System.out.println("The date must be today or later.");
                continue;
            }

            return date.toString();

        } catch (java.time.format.DateTimeParseException exception) {
            System.out.println(
                    "Invalid date. Use the format YYYY-MM-DD, "
                    + "for example 2026-11-27.");
        }
    }
}

private static String readChoice(
        Scanner scanner, String message, String... choices) {
    while (true) {
        String value = readText(scanner, message);

        for (String choice : choices) {
            if (value.equalsIgnoreCase(choice)) {
                return choice;
            }
        }

        System.out.println(
                "Invalid option. Allowed values: "
                + String.join(", ", choices));
    }
}

private static boolean readBoolean(Scanner scanner, String message) {
    while (true) {
        String value = readText(scanner, message);

        if (value.equalsIgnoreCase("yes")
                || value.equalsIgnoreCase("y")) {
            return true;
        }

        if (value.equalsIgnoreCase("no")
                || value.equalsIgnoreCase("n")) {
            return false;
        }

        System.out.println("Enter yes or no.");
    }
}

    private static void show(java.util.List<?> items) {
        if (items.isEmpty()) {
            System.out.println("No records.");
        }
        for (Object item : items) {
            System.out.println(item);
        }
    }

    private static int nextReportId(ArrayList<Report> reports) {
        int max = 0;
        for (Report report : reports) max = Math.max(max, report.getReportId());
        return max + 1;
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
