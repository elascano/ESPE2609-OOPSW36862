package ec.edu.espe.customerapp.controller;

/**
 *
 * @author David Clavón, Infinite Loops, @ESPE
 */

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.edu.espe.customerapp.model.Customer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.bson.Document;

public class CustomerController {
    private final MongoCollection<Document> collection;
    private final Gson gson;

    public CustomerController() {
        String uri = "mongodb+srv://davidclavon2007_db_user:de27yC1b@cluster0.ys86oqp.mongodb.net/oop";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("oop");
        this.collection = database.getCollection("Customer");
        this.gson = new Gson();
    }

    private List<Customer> fetchCustomers() {
        List<Customer> customers = new ArrayList<>();
        for (Document doc : collection.find()) {
            int id = doc.get("id") instanceof Number ? ((Number) doc.get("id")).intValue() : 0;
            String name = doc.getString("name") != null ? doc.getString("name") : "";
            int age = doc.get("age") instanceof Number ? ((Number) doc.get("age")).intValue() : 0;
            double moneySpent = doc.get("moneySpent") instanceof Number ? ((Number) doc.get("moneySpent")).doubleValue() : 0.0;
            customers.add(new Customer(id, name, age, moneySpent));
        }
        return customers;
    }

    public String getAllCustomersJson() {
        List<Customer> customers = fetchCustomers();
        return gson.toJson(customers);
    }

    public String getCustomerStatsJson() {
        List<Customer> customers = fetchCustomers();

        if (customers.isEmpty()) {
            Map<String, String> error = new LinkedHashMap<>();
            error.put("message", "No hay clientes registrados para calcular estadísticas.");
            return gson.toJson(error);
        }

        double totalSpent = customers.stream()
                .mapToDouble(Customer::getMoneySpent)
                .sum();
        double averageSpent = totalSpent / customers.size();

        Customer topSpender = customers.stream()
                .max(Comparator.comparingDouble(Customer::getMoneySpent))
                .get();

        double totalAge = customers.stream()
                .mapToInt(Customer::getAge)
                .sum();
        double averageAge = totalAge / customers.size();

        Customer oldestCustomer = customers.stream()
                .max(Comparator.comparingInt(Customer::getAge))
                .get();

        Customer youngestCustomer = customers.stream()
                .min(Comparator.comparingInt(Customer::getAge))
                .get();

        Map<String, Object> topSpenderMap = new LinkedHashMap<>();
        topSpenderMap.put("id", topSpender.getId());
        topSpenderMap.put("name", topSpender.getName());
        topSpenderMap.put("moneySpent", topSpender.getMoneySpent());

        Map<String, Object> oldestMap = new LinkedHashMap<>();
        oldestMap.put("id", oldestCustomer.getId());
        oldestMap.put("name", oldestCustomer.getName());
        oldestMap.put("age", oldestCustomer.getAge());

        Map<String, Object> youngestMap = new LinkedHashMap<>();
        youngestMap.put("id", youngestCustomer.getId());
        youngestMap.put("name", youngestCustomer.getName());
        youngestMap.put("age", youngestCustomer.getAge());

        Map<String, Object> stats = new LinkedHashMap<>();
        stats.put("totalCustomers", customers.size());
        stats.put("totalSpent", Math.round(totalSpent * 100.0) / 100.0);
        stats.put("averageSpent", Math.round(averageSpent * 100.0) / 100.0);
        stats.put("topSpender", topSpenderMap);
        stats.put("averageAge", Math.round(averageAge * 100.0) / 100.0);
        stats.put("oldestCustomer", oldestMap);
        stats.put("youngestCustomer", youngestMap);

        return gson.toJson(stats);
    }
}