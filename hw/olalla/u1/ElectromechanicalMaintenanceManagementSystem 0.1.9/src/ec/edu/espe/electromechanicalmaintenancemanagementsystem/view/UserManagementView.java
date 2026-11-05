package ec.edu.espe.electromechanicalmaintenancemanagementsystem.view;

/** Section to view, edit and delete clients and technicians. Every change is saved immediately. */
public class UserManagementView {
    private final ClientView clientView;
    private final TechnicianView technicianView;
    private final ConsoleInput input;
    private final Runnable persist;

    public UserManagementView(ClientView clientView, TechnicianView technicianView,
            ConsoleInput input, Runnable persist) {
        this.clientView = clientView;
        this.technicianView = technicianView;
        this.input = input;
        this.persist = persist;
    }

    public void run() {
        int option;
        do {
            printMenu();
            option = input.readIntInRange("Choose an option: ", 0, 6);
            execute(option);
            persist.run();
        } while (option != 0);
    }

    private void printMenu() {
        System.out.println("\n----- CLIENTS AND TECHNICIANS -----");
        System.out.println("1. View clients");
        System.out.println("2. View technicians");
        System.out.println("3. Edit a client");
        System.out.println("4. Edit a technician");
        System.out.println("5. Delete a client");
        System.out.println("6. Delete a technician");
        System.out.println("0. Back to main menu");
    }

    private void execute(int option) {
        switch (option) {
            case 1: clientView.list(); break;
            case 2: technicianView.list(); break;
            case 3: clientView.edit(); break;
            case 4: technicianView.edit(); break;
            case 5: clientView.delete(); break;
            case 6: technicianView.delete(); break;
            default: break;
        }
    }
}
