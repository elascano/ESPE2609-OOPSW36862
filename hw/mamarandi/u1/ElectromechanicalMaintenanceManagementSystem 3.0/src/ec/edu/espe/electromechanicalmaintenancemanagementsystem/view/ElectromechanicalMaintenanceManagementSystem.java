package ec.edu.espe.electromechanicalmaintenancemanagementsystem.view;

import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.*;
import ec.edu.espe.electromechanicalsystem.controller.JsonFileManager;
import ec.edu.espe.electromechanicalsystem.controller.ReportJsonFileManager;
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
        ArrayList<Client> clients = new ArrayList<>(clientFileManager.loadClients());
        ArrayList<Equipment> equipments = new ArrayList<>();
        ArrayList<Technician> technicians = new ArrayList<>();
        ArrayList<WorkOrder> orders = new ArrayList<>();
        ArrayList<MaintenanceSchedule> schedules = new ArrayList<>();
        ArrayList<MaintenanceHistory> histories = new ArrayList<>();
        ArrayList<Part> parts = new ArrayList<>();
        ArrayList<SafetyRegulation> regulations = new ArrayList<>();
        ArrayList<Alert> alerts = new ArrayList<>();
        ReportJsonFileManager reportFileManager = new ReportJsonFileManager("reports.json");
        ArrayList<Report> reports = new ArrayList<>(reportFileManager.loadReports());
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
            System.out.println("14. Generate CSV Report (Excel)");
            System.out.println("15. Edit or delete a client");
            System.out.println("16. Exit");
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
                    order.setDescription(readText(scanner, "Work description: "));
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
                case 16:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 16);
    }

   
/** Exports each record type to its own Excel-compatible CSV file. */
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

            if (date.isBefore(LocalDate.now()) || date.getYear() > LocalDate.now().getYear()) {
                System.out.println("The date must be today or later, and cannot be beyond the current year.");
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
