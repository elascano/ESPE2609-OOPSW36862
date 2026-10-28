package ec.edu.espe.systemliquorstore.service;

import ec.edu.espe.systemliquorstore.exception.BusinessRuleException;
import ec.edu.espe.systemliquorstore.model.*;
import java.util.ArrayList;

public class StoreService {
    // Aggregation: these lists belong to the store and can exist without this service.
    private final StoreData data;

    public StoreService(StoreData data) {
        if (data == null) {
            throw new IllegalArgumentException("Los datos de la tienda son necesarios.");
        }
        this.data = data;
    }

    public ArrayList<Product> getProducts() {
        return new ArrayList<>(data.getProducts());
    }

    public Product findProduct(String code) {
        for (Product product : data.getProducts()) {
            if (code != null && product.getProductCode().equalsIgnoreCase(code.trim())) {
                return product;
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        if (product == null || !product.isValid()) {
            throw new IllegalArgumentException("Revise los datos del producto.");
        }
        if (findProduct(product.getProductCode()) != null) {
            throw new BusinessRuleException("Ya existe un producto con ese código.");
        }
        data.getProducts().add(product);
    }

    public void updateProduct(String code, String name, double cost, double price, int minimumStock) {
        Product product = findProduct(code);
        if (product == null) {
            throw new BusinessRuleException("El producto no existe.");
        }
        // Validate all values before changing the stored product.
        Product checked = new Product(code, name, cost, price, product.getStock(), minimumStock);
        product.setName(checked.getName());
        product.updatePrices(cost, price);
        product.setMinimumStock(minimumStock);
    }

    public void deleteProduct(String code) {
        Product product = findProduct(code);
        if (product == null) {
            throw new BusinessRuleException("El producto no existe.");
        }
        for (Sale sale : data.getSales()) {
            if (sale.getProductQuantity(product.getProductCode()) > 0) {
                throw new BusinessRuleException("No se puede eliminar un producto que aparece en ventas.");
            }
        }
        for (PurchaseOrder order : data.getOrders()) {
            for (PurchaseOrderItem item : order.getItems()) {
                if (item.getProductCode().equals(product.getProductCode())) {
                    throw new BusinessRuleException("No se puede eliminar un producto que aparece en pedidos.");
                }
            }
        }
        for (int i = data.getMovements().size() - 1; i >= 0; i--) {
            if (data.getMovements().get(i).getProductCode().equals(product.getProductCode())) {
                data.getMovements().remove(i);
            }
        }
        data.getProducts().remove(product);
    }

    public ArrayList<Supplier> getSuppliers() {
        return new ArrayList<>(data.getSuppliers());
    }

    public Supplier findSupplier(int id) {
        for (Supplier supplier : data.getSuppliers()) {
            if (supplier.getSupplierId() == id) {
                return supplier;
            }
        }
        return null;
    }

    public void addSupplier(Supplier supplier) {
        if (supplier == null || !supplier.isValid()) {
            throw new IllegalArgumentException("Revise los datos del proveedor.");
        }
        if (findSupplier(supplier.getSupplierId()) != null) {
            throw new BusinessRuleException("El identificador del proveedor ya existe.");
        }
        data.getSuppliers().add(supplier);
    }

    public void updateSupplier(int id, String name, String contact) {
        Supplier supplier = findSupplier(id);
        if (supplier == null) {
            throw new BusinessRuleException("El proveedor no existe.");
        }
        Supplier checked = new Supplier(id, name, contact);
        supplier.setName(checked.getName());
        supplier.setContact(checked.getContact());
    }

    public void deleteSupplier(int id) {
        Supplier supplier = findSupplier(id);
        if (supplier == null) {
            throw new BusinessRuleException("El proveedor no existe.");
        }
        for (PurchaseOrder order : data.getOrders()) {
            if (order.getSupplierId() == id) {
                throw new BusinessRuleException("El proveedor tiene pedidos registrados y no se puede eliminar.");
            }
        }
        data.getSuppliers().remove(supplier);
    }

    public ArrayList<Customer> getCustomers() {
        return new ArrayList<>(data.getCustomers());
    }

    public Customer findCustomer(int id) {
        for (Customer customer : data.getCustomers()) {
            if (customer.getCustomerId() == id) {
                return customer;
            }
        }
        return null;
    }

    public void addCustomer(Customer customer) {
        if (customer == null || !customer.isValid()) {
            throw new IllegalArgumentException("Revise los datos del cliente.");
        }
        if (findCustomer(customer.getCustomerId()) != null) {
            throw new BusinessRuleException("El identificador del cliente ya existe.");
        }
        data.getCustomers().add(customer);
    }

    public void updateCustomer(int id, String name) {
        Customer customer = findCustomer(id);
        if (customer == null) {
            throw new BusinessRuleException("El cliente no existe.");
        }
        customer.setName(name);
    }

    public void deleteCustomer(int id) {
        Customer customer = findCustomer(id);
        if (customer == null) {
            throw new BusinessRuleException("El cliente no existe.");
        }
        for (Sale sale : data.getSales()) {
            if (sale.getCustomerId() == id) {
                throw new BusinessRuleException("El cliente tiene ventas registradas y no se puede eliminar.");
            }
        }
        for (CustomerPayment payment : data.getPayments()) {
            if (payment.getCustomerId() == id) {
                throw new BusinessRuleException("El cliente tiene pagos registrados y no se puede eliminar.");
            }
        }
        data.getCustomers().remove(customer);
    }
}
