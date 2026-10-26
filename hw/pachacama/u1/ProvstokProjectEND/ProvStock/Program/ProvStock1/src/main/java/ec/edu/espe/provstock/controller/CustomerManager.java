package ec.edu.espe.provstock.controller;

import ec.edu.espe.provstock.model.Customer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
     * @return false if the data is not valid or the identification already exists (BR-08)
     */
    public boolean registerCustomer(Customer customer) {
        if (customer == null || !customer.validate()) {
            return false;
        }
        if (findByIdentification(customer.getIdentificationNumber()) != null) {
            return false;
        }
        customer.setId(customers.size() + 1);
        customers.add(customer);
        return true;
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

    public Customer findByIdentification(String number) {
        if (number == null) {
            return null;
        }
        for (Customer customer : customers) {
            if (customer.getIdentificationNumber().equalsIgnoreCase(number.trim())) {
                return customer;
            }
        }
        return null;
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
        if (getFinalConsumer() == null) {
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
