package ec.edu.espe.provstock.controller;

import ec.edu.espe.provstock.model.Customer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Registers, updates, searches and deactivates customers (UC-05). (RF02)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class CustomerManager {

    private List<Customer> customers;

    public CustomerManager() {
        customers = new ArrayList<>();
        //FR-02.6: the "Consumidor Final" always exists
        customers.add(new Customer(1, Customer.RUC, Customer.FINAL_CONSUMER_ID, "CONSUMIDOR FINAL",
                "", "", ""));
    }

    /**
     * @throws IllegalArgumentException if the data is not valid or the identification already exists (BR-08)
     */
    public void registerCustomer(Customer customer) {
        if (customer == null || !customer.validate()) {
            throw new IllegalArgumentException("Los datos del cliente no son válidos");
        }
        if (isRegistered(customer.getIdentificationNumber())) {
            throw new IllegalArgumentException("Ya existe un cliente con la identificación "
                    + customer.getIdentificationNumber() + ".");
        }
        customer.setId(customers.size() + 1);
        customers.add(customer);
    }

    public void updateCustomer(Customer customer) {
        if (!customers.contains(customer)) {
            throw new IllegalArgumentException("El cliente no existe");
        }
        if (!customer.validate()) {
            throw new IllegalArgumentException("Los datos del cliente no son válidos");
        }
    }

    public void deactivateCustomer(Customer customer) {
        if (!customers.contains(customer)) {
            throw new IllegalArgumentException("El cliente no existe");
        }
        customer.deactivate();
    }

    /**
     * @throws NoSuchElementException if no customer has that identification number
     */
    public Customer findByIdentification(String number) {
        for (Customer customer : customers) {
            if (hasIdentification(customer, number)) {
                return customer;
            }
        }
        throw new NoSuchElementException("No existe el cliente " + number + ".");
    }

    public boolean isRegistered(String number) {
        for (Customer customer : customers) {
            if (hasIdentification(customer, number)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasIdentification(Customer customer, String number) {
        return number != null && customer.getIdentificationNumber().equalsIgnoreCase(number.trim());
    }

    /**
     * Searches active customers whose name contains the text. An empty text returns all of them.
     */
    public List<Customer> searchByName(String text) {
        String search = text == null ? "" : text.trim().toLowerCase();
        List<Customer> result = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.isActive() && customer.getName().toLowerCase().contains(search)) {
                result.add(customer);
            }
        }
        return result;
    }

    /**
     * Replaces the customers with the ones read from the data files.
     * The "Consumidor Final" is added again if the file did not have it (FR-02.6).
     */
    public void restoreCustomers(List<Customer> savedCustomers) {
        customers = new ArrayList<>(savedCustomers);
        if (!isRegistered(Customer.FINAL_CONSUMER_ID)) {
            customers.add(0, new Customer(customers.size() + 1, Customer.RUC, Customer.FINAL_CONSUMER_ID,
                    "CONSUMIDOR FINAL", "", "", ""));
        }
    }

    /**
     * @return all the customers, active and inactive (needed to save them in a file)
     */
    public List<Customer> getAllCustomers() {
        return Collections.unmodifiableList(customers);
    }

    public Customer getFinalConsumer() {
        return findByIdentification(Customer.FINAL_CONSUMER_ID);
    }
}
