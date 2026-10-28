package ec.edu.espe.provstock.view;

import ec.edu.espe.provstock.controller.AccessManager;
import ec.edu.espe.provstock.controller.CustomerManager;
import ec.edu.espe.provstock.model.Customer;
import ec.edu.espe.provstock.model.Role;
import java.util.ArrayList;
import java.util.List;

/**
 * UC-05 Manage customers. (RF02)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class CustomerMenu {

    private final InputReader input;
    private final CustomerManager customerManager;
    private final AccessManager accessManager;
    private final ConsoleHelper console;

    public CustomerMenu(AppContext context, ConsoleHelper console) {
        this.input = context.getInput();
        this.customerManager = context.getCustomerManager();
        this.accessManager = context.getAccessManager();
        this.console = console;
    }

    public void manageCustomers() {
        while (true) {
            int option = console.showMenu("Gestionar clientes", "Volver", "Registrar cliente",
                    "Actualizar cliente", "Desactivar cliente", "Buscar clientes (identificación o nombre)");
            switch (option) {
                case 1 -> console.runSafely(this::registerCustomer);
                case 2 -> console.runSafely(this::updateCustomer);
                case 3 -> deactivateIfAllowed();
                case 4 -> searchCustomers();
                case 0 -> {
                    return;
                }
                default -> console.showInvalidOption();
            }
        }
    }

    /**
     * Also used by UC-08 (alternative flow 2a) to register a customer in the middle of a sale.
     *
     * @throws IllegalArgumentException if the data is not valid or the customer already exists
     */
    public Customer registerCustomer() {
        String type = input.readOption("Tipo de identificación:", Customer.IDENTIFICATION_TYPES);
        String number = input.readText("Número de identificación: ");
        String name = input.readText("Nombre o razón social: ");
        String phone = input.readOptionalText("Teléfono (opcional): ");
        String email = input.readOptionalText("Correo (opcional): ");
        String address = input.readOptionalText("Dirección (opcional): ");

        Customer customer = new Customer(0, type, number, name, phone, email, address);
        checkData(customer);
        customerManager.registerCustomer(customer);
        System.out.println("Cliente registrado con id " + customer.getId() + ".");
        return customer;
    }

    private void checkData(Customer customer) {
        if (!customer.validateIdentification()) {
            throw new IllegalArgumentException("La identificación no es válida para el tipo "
                    + customer.getIdentificationType() + ".");
        }
        if (!customer.validate()) {
            throw new IllegalArgumentException("Datos no válidos: revise el teléfono y el correo.");
        }
    }

    private void updateCustomer() {
        Customer customer = findCustomer();
        System.out.println(formatCustomer(customer));
        String name = input.readTextOrKeep("Nombre", customer.getName());
        String phone = input.readTextOrKeep("Teléfono", customer.getPhone());
        String email = input.readTextOrKeep("Correo", customer.getEmail());
        String address = input.readTextOrKeep("Dirección", customer.getAddress());
        customer.update(name, phone, email, address);
        customerManager.updateCustomer(customer);
        System.out.println("Cliente actualizado.");
    }

    private void deactivateIfAllowed() {
        if (accessManager.checkPermission(Role.DEACTIVATE_CUSTOMERS)) {
            console.runSafely(this::deactivateCustomer);
        } else {
            System.out.println("Solo el propietario puede desactivar clientes.");
        }
    }

    private void deactivateCustomer() {
        Customer customer = findCustomer();
        if (input.readYesNo("¿Desactivar a " + customer.getName() + "?")) {
            customerManager.deactivateCustomer(customer);
            System.out.println("Cliente desactivado.");
        }
    }

    private Customer findCustomer() {
        return customerManager.findByIdentification(input.readText("Identificación del cliente: "));
    }

    /**
     * UC-05 A1: the user searches by identification number or by part of the name.
     */
    private void searchCustomers() {
        String text = input.readOptionalText("Identificación o nombre (Enter para ver todos): ");
        List<Customer> customers = findCustomers(text);
        if (customers.isEmpty()) {
            System.out.println("No se encontraron clientes.");
        }
        for (Customer customer : customers) {
            System.out.println(formatCustomer(customer));
        }
    }

    private List<Customer> findCustomers(String text) {
        if (!customerManager.isRegistered(text)) {
            return customerManager.searchByName(text);
        }
        List<Customer> customers = new ArrayList<>();
        customers.add(customerManager.findByIdentification(text));
        return customers;
    }

    private String formatCustomer(Customer customer) {
        return String.format("  [%d] %-24s %-30s %s", customer.getId(), customer.getIdentification(),
                customer.getName(), customer.getEmail() == null ? "" : customer.getEmail());
    }
}
