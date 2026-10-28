package ec.edu.espe.provstock.view;

import ec.edu.espe.provstock.controller.AccessManager;
import ec.edu.espe.provstock.controller.SalesManager;
import ec.edu.espe.provstock.controller.StockMonitor;
import ec.edu.espe.provstock.model.Role;
import java.util.ArrayList;
import java.util.List;

/**
 * Main menu (UI-02): it shows only the options that the role of the user is allowed to use.
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class MainMenu {

    private final InputReader input;
    private final AccessManager accessManager;
    private final SalesManager salesManager;
    private final StockMonitor stockMonitor;
    private final ConsoleHelper console;
    private final List<MenuOption> options = new ArrayList<>();

    public MainMenu(AppContext context, ConsoleHelper console) {
        this.input = context.getInput();
        this.accessManager = context.getAccessManager();
        this.salesManager = context.getSalesManager();
        this.stockMonitor = context.getStockMonitor();
        this.console = console;
    }

    /**
     * @param permission the permission needed to see the option, or null if every user can see it
     */
    public void addOption(String permission, String label, Runnable action) {
        options.add(new MenuOption(permission, label, action));
    }

    /**
     * Runs until the user logs out (UC-02).
     */
    public void show() {
        while (true) {
            List<MenuOption> allowed = allowedOptions();
            printMenu(allowed);
            int option = input.readInt("Elija una opción: ");
            if (option == 0) {
                accessManager.logout();
                System.out.println("Sesión cerrada.");
                return;
            }
            if (option < 1 || option > allowed.size()) {
                console.showInvalidOption();
                continue;
            }
            console.runSafely(allowed.get(option - 1).action);
        }
    }

    private List<MenuOption> allowedOptions() {
        List<MenuOption> allowed = new ArrayList<>();
        for (MenuOption option : options) {
            if (option.permission == null || accessManager.checkPermission(option.permission)) {
                allowed.add(option);
            }
        }
        return allowed;
    }

    private void printMenu(List<MenuOption> allowed) {
        System.out.println("\n----------- MENÚ PRINCIPAL -----------");
        System.out.println(header());
        for (int i = 0; i < allowed.size(); i++) {
            System.out.printf("%2d. %s%n", i + 1, allowed.get(i).label);
        }
        System.out.println(" 0. Cerrar sesión");
    }

    private String header() {
        String header = "Usuario: " + accessManager.getCurrentUser().getUsername()
                + " | IVA actual: " + TextFormat.percent(salesManager.getVatRate());
        //UC-01 and UC-18: the roles that receive alerts see the number of active alerts
        if (accessManager.checkPermission(Role.RECEIVE_ALERTS)) {
            header = header + " | Alertas de stock: " + stockMonitor.countAlerts();
        }
        return header;
    }

    /**
     * One option of the main menu and the permission needed to see it.
     */
    private static class MenuOption {

        private final String permission;
        private final String label;
        private final Runnable action;

        MenuOption(String permission, String label, Runnable action) {
            this.permission = permission;
            this.label = label;
            this.action = action;
        }
    }
}
