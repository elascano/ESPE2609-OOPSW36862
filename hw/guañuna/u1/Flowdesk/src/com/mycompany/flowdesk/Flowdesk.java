package com.mycompany.flowdesk;

import data.DataManager;
import enums.Role;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Scanner;
import models.*;
import reports.Dashboard;
import reports.Report;
import utils.ConsoleInput;
import exceptions.PersistenceException;
import com.google.gson.JsonParseException;

public class Flowdesk {
    private static final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    private static final Dashboard dashboard = new Dashboard();
    private static CashRegister cashRegister;
    private static boolean exitRequested;

    public static void main(String[] args) {
        configureConsole();
        try {
            DataManager.loadAll();
            cashRegister = DataManager.loadCashRegister();
        } catch (PersistenceException | JsonParseException exception) {
            System.out.println("No se pudo iniciar el sistema. Revise los archivos de datos y los permisos de acceso.");
            return;
        }
        Runtime.getRuntime().addShutdownHook(new Thread(Flowdesk::saveData));
        System.out.println("Puede escribir :cancelar para abandonar una operacion.");
        try {
            while (!exitRequested) {
                ConsoleInput.runAction(Flowdesk::startSession);
            }
        } catch (NoSuchElementException exception) {
            System.out.println("\nEntrada finalizada. Saliendo del programa...");
        }
    }

    private static void configureConsole() {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));
    }

    private static void saveData() {
        System.out.println("\n[Guardando datos en el disco...]");
        try {
            DataManager.saveAll();
            DataManager.saveCashRegister(cashRegister);
            System.out.println("[Datos guardados exitosamente.]");
        } catch (PersistenceException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    private static void startSession() {
        System.out.println("\n--- FLOWDESK - SISTEMA DE GESTION ---");
        System.out.println("1. Gerente");
        System.out.println("2. Cajero");
        System.out.println("3. Empleado");
        System.out.println("4. Salir del programa");
        int option = ConsoleInput.readOption(scanner, "Seleccione su rol: ", 1, 4);
        if (option == 4) {
            exitRequested = true;
            return;
        }
        Role role = switch (option) {
            case 1 -> Role.MANAGER;
            case 2 -> Role.CASHIER;
            default -> Role.EMPLOYEE;
        };
        User user = User.authenticateFromConsole(role, scanner, DataManager::saveUsers);
        if (user != null) {
            user.login();
            showRoleSession(user);
        }
    }

    static void showRoleSession(User user) {
        if (user.getRole() == null) {
            System.out.println("El usuario no tiene un rol asignado.");
            return;
        }
        int logoutOption = switch (user.getRole()) {
            case EMPLOYEE -> 6;
            case CASHIER -> 5;
            case MANAGER -> 3;
        };
        boolean loggedOut = false;
        while (!loggedOut && !exitRequested) {
            try {
                showRoleMenu(user);
                int option = ConsoleInput.readOption(scanner, "Opcion: ", 1, logoutOption + 1);
                if (option >= logoutOption) {
                    user.logout();
                    loggedOut = true;
                    exitRequested = option == logoutOption + 1;
                } else {
                    ConsoleInput.runAction(() -> executeRoleOption(user, option));
                }
            } catch (java.util.concurrent.CancellationException exception) {
                user.logout();
                loggedOut = true;
            }
        }
    }

    private static void showRoleMenu(User user) {
        System.out.println("\n--- MENU DE: " + user.getRole() + " | Usuario: " + user.getUsername() + " ---");
        switch (user.getRole()) {
            case EMPLOYEE -> {
                System.out.println("1. Gestionar Clientes");
                System.out.println("2. Gestionar Productos y Servicios");
                System.out.println("3. Crear Nueva Orden de Trabajo");
                System.out.println("4. Actualizar Estado de Orden");
                System.out.println("5. Ver Panel de control de Ordenes");
                System.out.println("6. Cerrar Sesion");
                System.out.println("7. Salir del programa");
            }
            case CASHIER -> {
                System.out.println("1. Registrar Movimiento de Caja Manual");
                System.out.println("2. Registrar Pago de Orden");
                System.out.println("3. Ver Estado de Caja");
                System.out.println("4. Historial de Movimientos");
                System.out.println("5. Cerrar Sesion");
                System.out.println("6. Salir del programa");
            }
            case MANAGER -> {
                System.out.println("1. Generar Reporte");
                System.out.println("2. Ver Estado de Caja");
                System.out.println("3. Cerrar Sesion");
                System.out.println("4. Salir del programa");
            }
        }
    }

    private static void executeRoleOption(User user, int option) {
        switch (user.getRole()) {
            case EMPLOYEE -> executeEmployeeOption(user, option);
            case CASHIER -> executeCashierOption(option);
            case MANAGER -> executeManagerOption(option);
        }
    }

    private static void executeEmployeeOption(User user, int option) {
        switch (option) {
            case 1 -> Customer.showManagementMenu(scanner);
            case 2 -> showCatalogMenu(scanner);
            case 3 -> WorkOrder.registerFromConsole(scanner, user);
            case 4 -> WorkOrder.updateStatusFromConsole(scanner);
            case 5 -> showDashboard();
        }
    }

    private static void executeCashierOption(int option) {
        switch (option) {
            case 1 -> CashMovement.registerFromConsole(scanner, cashRegister);
            case 2 -> Payment.registerFromConsole(scanner, cashRegister);
            case 3 -> cashRegister.printStatus();
            case 4 -> CashMovement.listFromConsole();
        }
    }

    private static void executeManagerOption(int option) {
        switch (option) {
            case 1 -> Report.generateFromConsole(scanner, cashRegister);
            case 2 -> cashRegister.printStatus();
        }
    }

    private static void showDashboard() {
        dashboard.setOrders(WorkOrder.getAll());
        dashboard.showDashboard();
    }

    public static void showCatalogMenu(Scanner input) {
        while (true) {
            System.out.println("\n--- GESTION DE CATALOGO ---");
            System.out.println("1. Registrar nueva Categoria");
            System.out.println("2. Registrar Producto");
            System.out.println("3. Registrar Servicio");
            System.out.println("4. Listar Productos");
            System.out.println("5. Listar Servicios");
            System.out.println("0. Volver");
            int option = ConsoleInput.readOption(input, "Opcion: ", 0, 5);
            if (option == 0) return;
            ConsoleInput.runAction(() -> executeCatalogOption(input, option));
        }
    }

    private static void executeCatalogOption(Scanner input, int option) {
        switch (option) {
            case 1 -> Category.registerFromConsole(input);
            case 2 -> Product.registerFromConsole(input);
            case 3 -> Service.registerFromConsole(input);
            case 4 -> Product.listFromConsole();
            case 5 -> Service.listFromConsole();
        }
    }
}
