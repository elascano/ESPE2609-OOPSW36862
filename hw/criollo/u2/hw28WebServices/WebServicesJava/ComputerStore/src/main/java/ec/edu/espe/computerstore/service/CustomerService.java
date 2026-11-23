package ec.edu.espe.computerstore.service;

import ec.edu.espe.computerstore.model.Customer;
import ec.edu.espe.computerstore.repository.CustomerRepository;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dennis Criollo, Bytes & Fabric Devs, @ESPE
 */
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findCustomerById(int id) {
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> updateCustomerById(int id, Customer updatedData) {
        return customerRepository.findById(id).map(existingCustomer -> {
            if (updatedData.getName() != null) {
                existingCustomer.setName(updatedData.getName());
            }
            if (updatedData.getAge() != 0) {
                existingCustomer.setAge(updatedData.getAge());
            }
            if (updatedData.getMoneySpent() != 0.0f) {
                existingCustomer.setMoneySpent(updatedData.getMoneySpent());
            }
            return customerRepository.save(existingCustomer);
        });
    }

    public boolean deleteCustomerById(int id) {
        if (customerRepository.findById(id).isEmpty()) {
            return false;
        }
        customerRepository.deleteById(id);
        return true;
    }

    public float calculateTotalSpent(List<Customer> customers) {
        float totalSpent = 0.0f;
        for (Customer customer : customers) {
            totalSpent += customer.getMoneySpent();
        }
        return totalSpent;
    }

    public float calculateAverageSpent(List<Customer> customers) {
        if (customers.isEmpty()) {
            return 0.0f;
        }
        return calculateTotalSpent(customers) / customers.size();
    }

    public Customer findHighestSpender(List<Customer> customers) {
        return customers.stream()
                .max(Comparator.comparingDouble(Customer::getMoneySpent))
                .orElse(null);
    }

    public Map<String, Object> getSpendingAnalytics() {
        List<Customer> customers = findAllCustomers();
        Map<String, Object> analytics = new HashMap<>();
        analytics.put("totalSpent", calculateTotalSpent(customers));
        analytics.put("averageSpent", calculateAverageSpent(customers));
        analytics.put("highestSpender", findHighestSpender(customers));
        return analytics;
    }
}
