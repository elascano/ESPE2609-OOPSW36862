package com.computerstore.controller;

import com.computerstore.model.Customer;
import com.computerstore.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/computerstore")
public class CustomerController {

    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getCustomers() {
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception err) {
            return error(HttpStatus.INTERNAL_SERVER_ERROR, err);
        }
    }

    @GetMapping("/customers/analytics")
    public ResponseEntity<?> getAnalytics() {
        try {
            List<Customer> customers = repository.findAll();
            Map<String, Object> result = new LinkedHashMap<>();
            result.put("totalSpent", calculateTotalSpent(customers));
            result.put("averageSpent", calculateAverageSpent(customers));
            result.put("highestSpender", findHighestSpender(customers));
            return ResponseEntity.ok(result);
        } catch (Exception err) {
            return error(HttpStatus.INTERNAL_SERVER_ERROR, err);
        }
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Integer id) {
        try {
            Optional<Customer> found = repository.findByCustomerId(id);
            if (found.isEmpty()) {
                return message(HttpStatus.NOT_FOUND, "Customer not found");
            }
            return ResponseEntity.ok(found.get());
        } catch (Exception err) {
            return error(HttpStatus.INTERNAL_SERVER_ERROR, err);
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<?> createCustomer(@RequestBody Customer body) {
        try {
            Customer newCustomer = new Customer();
            newCustomer.setId(body.getId());
            newCustomer.setName(body.getName());
            newCustomer.setAge(body.getAge());
            newCustomer.setMoneySpent(body.getMoneySpent());
            return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(newCustomer));
        } catch (Exception err) {
            return error(HttpStatus.BAD_REQUEST, err);
        }
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Integer id, @RequestBody Customer body) {
        try {
            Optional<Customer> found = repository.findByCustomerId(id);
            if (found.isEmpty()) {
                return message(HttpStatus.NOT_FOUND, "Customer not found");
            }
            Customer customer = found.get();
            if (body.getId() != null) customer.setId(body.getId());
            if (body.getName() != null) customer.setName(body.getName());
            if (body.getAge() != null) customer.setAge(body.getAge());
            if (body.getMoneySpent() != null) customer.setMoneySpent(body.getMoneySpent());
            return ResponseEntity.ok(repository.save(customer));
        } catch (Exception err) {
            return error(HttpStatus.BAD_REQUEST, err);
        }
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
        try {
            Optional<Customer> found = repository.findByCustomerId(id);
            if (found.isEmpty()) {
                return message(HttpStatus.NOT_FOUND, "Customer not found");
            }
            repository.delete(found.get());
            return message(HttpStatus.OK, "Customer deleted");
        } catch (Exception err) {
            return error(HttpStatus.INTERNAL_SERVER_ERROR, err);
        }
    }

    private double calculateTotalSpent(List<Customer> customers) {
        return customers.stream()
                .mapToDouble(c -> c.getMoneySpent() == null ? 0 : c.getMoneySpent())
                .sum();
    }

    private double calculateAverageSpent(List<Customer> customers) {
        if (customers.isEmpty()) return 0;
        return calculateTotalSpent(customers) / customers.size();
    }

    private Customer findHighestSpender(List<Customer> customers) {
        if (customers.isEmpty()) return null;
        Customer highest = customers.get(0);
        for (Customer current : customers) {
            double currentSpent = current.getMoneySpent() == null ? 0 : current.getMoneySpent();
            double highestSpent = highest.getMoneySpent() == null ? 0 : highest.getMoneySpent();
            if (currentSpent > highestSpent) highest = current;
        }
        return highest;
    }

    private ResponseEntity<Map<String, String>> message(HttpStatus status, String text) {
        return ResponseEntity.status(status).body(Map.of("message", text));
    }

    private ResponseEntity<Map<String, String>> error(HttpStatus status, Exception err) {
        return message(status, String.valueOf(err.getMessage()));
    }
}
