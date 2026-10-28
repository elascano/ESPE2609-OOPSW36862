package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utils.ConsoleInput;
import utils.Validation;
import exceptions.ValidationException;

public class Customer {
    private String customerId;
    private String name;
    private String phone;
    private String idNumber;
    private String address;

    private static List<Customer> customers = new ArrayList<>();

    public Customer(String customerId, String name, String phone, String idNumber, String address) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.idNumber = idNumber;
        this.address = address;
        validate();
    }

    public void register() {
        validate();
        for (Customer existing : customers) {
            if (Validation.sameIdentifier(existing.customerId, customerId)) {
                throw new ValidationException("Ya existe un cliente con ese ID.");
            }
            if (Validation.sameIdentifier(existing.idNumber, idNumber)) {
                throw new ValidationException("Ya existe un cliente con esa cedula.");
            }
        }
        customers.add(this);
    }

    void validate() {
        Validation.requireText(customerId, "identificador del cliente");
        Validation.requireText(name, "nombre del cliente");
        Validation.requireText(phone, "telefono");
        Validation.requireText(idNumber, "cedula");
        Validation.requireText(address, "direccion");
    }

    public Customer() { }

    public static void setAll(List<Customer> newItems) {
        customers = newItems;
    }

    public static Customer search(String searchTerm) {
        for (Customer c : customers) {
            if (Validation.sameIdentifier(c.getCustomerId(), searchTerm) || Validation.sameIdentifier(c.getIdNumber(), searchTerm)) {
                return c;
            }
        }
        return null;
    }

    public static List<Customer> getAll() {
        return customers;
    }

    public static void showManagementMenu(Scanner scanner) {
        boolean goBack = false;
        while (!goBack) {
            System.out.println("\n--- GESTION DE CLIENTES ---");
            System.out.println("1. Registrar nuevo Cliente");
            System.out.println("2. Buscar Cliente");
            System.out.println("3. Listar todos los Clientes");
            System.out.println("0. Volver al menu principal");
            int option = ConsoleInput.readOption(scanner, "Opcion: ", 0, 3);

            switch (option) {
                case 1: ConsoleInput.runAction(() -> registerFromConsole(scanner)); break;
                case 2: ConsoleInput.runAction(() -> searchFromConsole(scanner)); break;
                case 3: listFromConsole(); break;
                case 0: goBack = true; break;
                default: System.out.println("Opcion no valida.");
            }
        }
    }

    private static void registerFromConsole(Scanner scanner) {
        System.out.println("\n--- REGISTRAR NUEVO CLIENTE ---");
        String id = ConsoleInput.readRequiredText(scanner, "ID del Cliente: ");

        if (search(id) != null) {
            System.out.println("Error: Ya existe un cliente con ese ID.");
            return;
        }

        String name = ConsoleInput.readRequiredText(scanner, "Nombre completo: ");
        String phone = ConsoleInput.readRequiredText(scanner, "Telefono: ");
        String nationalId = ConsoleInput.readRequiredText(scanner, "Cedula/DNI: ");
        String address = ConsoleInput.readRequiredText(scanner, "Direccion: ");

        Customer newCustomer = new Customer(id, name, phone, nationalId, address);
        newCustomer.register();
        System.out.println("¡Cliente registrado con exito!");
    }

    private static void searchFromConsole(Scanner scanner) {
        System.out.println("\n--- BUSCAR CLIENTE ---");
        String searchTerm = ConsoleInput.readRequiredText(scanner, "Ingrese el ID o la Cedula del cliente: ");

        Customer match = search(searchTerm);

        if (match != null) {
            System.out.println("\n--- CLIENTE ENCONTRADO ---");
            System.out.println("ID: " + match.getCustomerId());
            System.out.println("Nombre: " + match.getName());
            System.out.println("Telefono: " + match.getPhone());
            System.out.println("Cedula: " + match.getIdNumber());
            System.out.println("Direccion: " + match.getAddress());
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private static void listFromConsole() {
        System.out.println("\n--- LISTA DE CLIENTES ---");
        if (customers.isEmpty()) {
            System.out.println("No hay clientes registrados aun.");
        } else {
            for (Customer c : customers) {
                System.out.println("- [" + c.getCustomerId() + "] " + c.getName() + " | Tel: " + c.getPhone());
            }
        }
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getIdNumber() { return idNumber; }
    public String getAddress() { return address; }
}
