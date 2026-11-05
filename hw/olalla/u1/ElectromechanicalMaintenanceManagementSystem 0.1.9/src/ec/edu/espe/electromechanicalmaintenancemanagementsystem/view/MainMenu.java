package ec.edu.espe.electromechanicalmaintenancemanagementsystem.view;

import ec.edu.espe.electromechanicalsystem.controller.*;
import java.util.LinkedHashMap;
import java.util.Map;

/** Builds the application, shows the main menu and runs the chosen action. Data is saved after every action. */
public class MainMenu {
    private static final int EXIT = 0;

    private final ConsoleInput input;
    private final DataRepository repository;
    private final Map<Integer, MenuItem> items = new LinkedHashMap<>();

    public MainMenu(ConsoleInput input, DataRepository repository) {
        this.input = input;
        this.repository = repository;
        buildMenu();
    }

    public void run() {
        int option;
        do {
            printMenu();
            option = input.readInt("Choose an option: ");
            execute(option);
            repository.saveAll();
        } while (option != EXIT);
    }

    private void buildMenu() {
        SystemState state = repository.getState();

        ClientController clientController = new ClientController(state);
        TechnicianController technicianController = new TechnicianController(state);
        EquipmentController equipmentController = new EquipmentController(state);
        MaintenanceController maintenanceController = new MaintenanceController(state);
        WorkOrderController workOrderController =
                new WorkOrderController(state, equipmentController, maintenanceController);

        ClientView clientView = new ClientView(clientController, input);
        TechnicianView technicianView = new TechnicianView(technicianController, input);
        EquipmentView equipmentView = new EquipmentView(
                equipmentController, clientController, workOrderController, clientView, input);
        MaintenanceView maintenanceView = new MaintenanceView(maintenanceController, equipmentView, input);
        WorkOrderView workOrderView = new WorkOrderView(workOrderController, equipmentView, technicianView, input);
        RecordsView recordsView = new RecordsView(state);
        UserManagementView userManagementView =
                new UserManagementView(clientView, technicianView, input, repository::saveAll);

        add(1, "Register client", clientView::register);
        add(2, "Register equipment for a client", equipmentView::register);
        add(3, "Register technician", technicianView::register);
        add(4, "Schedule maintenance", maintenanceView::schedule);
        add(5, "Create work order", workOrderView::create);
        add(6, "Assign technician to work order", workOrderView::assignTechnician);
        add(7, "Add task to work order", workOrderView::addTask);
        add(8, "Register part and safety regulation", workOrderView::registerPartAndRegulation);
        add(9, "Execute task", workOrderView::executeTask);
        add(10, "Close work order and generate report", workOrderView::closeOrder);
        add(11, "Record maintenance history", maintenanceView::recordHistory);
        add(12, "Show all records", recordsView::showAll);
        add(13, "Remove equipment from client", clientView::removeEquipment);
        add(14, "Generate CSV reports (Excel)", recordsView::exportCsv);
        add(15, "Manage clients and technicians (view / edit / delete)", userManagementView::run);
        add(16, "Search clients or equipment", () -> search(clientView, equipmentView));
        add(17, "Update equipment information", equipmentView::update);
        add(18, "Show equipment maintenance history", equipmentView::showMaintenanceHistory);
        add(19, "Consult equipment by QR code or serial number", equipmentView::consultByQrOrSerial);
        add(20, "Show upcoming maintenance alerts", maintenanceView::showUpcoming);
    }

    private void add(int number, String label, Runnable action) {
        items.put(number, new MenuItem(label, action));
    }

    private void printMenu() {
        System.out.println("\n===== ELECTROMECHANICAL MAINTENANCE MANAGEMENT SYSTEM =====");
        items.forEach((number, item) -> System.out.println(number + ". " + item.label));
        System.out.println(EXIT + ". Exit");
    }

    private void execute(int option) {
        if (option == EXIT) {
            System.out.println("Goodbye!");
            return;
        }
        MenuItem item = items.get(option);
        if (item == null) {
            System.out.println("Invalid option.");
            return;
        }
        item.action.run();
    }

    private void search(ClientView clientView, EquipmentView equipmentView) {
        System.out.println("1. Search client by ID, name or phone");
        System.out.println("2. Search equipment by ID, name or serial number");
        if (input.readIntInRange("Choose: ", 1, 2) == 1) {
            clientView.search();
        } else {
            equipmentView.search();
        }
    }

    private static final class MenuItem {
        private final String label;
        private final Runnable action;

        private MenuItem(String label, Runnable action) {
            this.label = label;
            this.action = action;
        }
    }
}
