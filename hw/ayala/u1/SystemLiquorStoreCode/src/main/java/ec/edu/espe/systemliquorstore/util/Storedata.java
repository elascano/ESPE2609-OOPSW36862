package ec.edu.espe.systemliquorstore.util;

import ec.edu.espe.systemliquorstore.model.*;
import java.util.ArrayList;
import java.util.Locale;

public class Storedata {
    private ArrayList<Product> products;
    private ArrayList<StockMovement> movements;
    private ArrayList<Supplier> suppliers;
    private ArrayList<PurchaseOrder> orders;
    private ArrayList<Sale> sales;
    private ArrayList<Customer> customers;
    private ArrayList<CustomerPayment> payments;
    private ArrayList<DailyCashRecord> cashRecords;

    public Storedata() {
        products = new ArrayList<>();
        movements = new ArrayList<>();
        suppliers = new ArrayList<>();
        orders = new ArrayList<>();
        sales = new ArrayList<>();
        customers = new ArrayList<>();
        payments = new ArrayList<>();
        cashRecords = new ArrayList<>();
    }

    public Storedata(ArrayList<Product> products, ArrayList<StockMovement> movements,
            ArrayList<Supplier> suppliers, ArrayList<PurchaseOrder> orders,
            ArrayList<Sale> sales, ArrayList<Customer> customers,
            ArrayList<CustomerPayment> payments, ArrayList<DailyCashRecord> cashRecords) {
        this.products = products;
        this.movements = movements;
        this.suppliers = suppliers;
        this.orders = orders;
        this.sales = sales;
        this.customers = customers;
        this.payments = payments;
        this.cashRecords = cashRecords;
    }

    public boolean isValid() {
        return getValidationError().isEmpty();
    }

    public String getValidationError() {
        if (products == null || movements == null || suppliers == null || orders == null
                || sales == null || customers == null || payments == null || cashRecords == null) {
            return "El archivo debe contener las ocho listas de datos.";
        }
        try {
            String error = validateProducts();
            if (!error.isEmpty()) {
                return error;
            }
            error = validateSuppliers();
            if (!error.isEmpty()) {
                return error;
            }
            error = validateCustomers();
            if (!error.isEmpty()) {
                return error;
            }
            error = validateMovements();
            if (!error.isEmpty()) {
                return error;
            }
            error = validateOrders();
            if (!error.isEmpty()) {
                return error;
            }
            error = validateSales();
            if (!error.isEmpty()) {
                return error;
            }
            error = validatePayments();
            if (!error.isEmpty()) {
                return error;
            }
            error = validateCashRecords();
            if (!error.isEmpty()) {
                return error;
            }
            for (Customer customer : customers) {
                customer.getOutstandingBalance(sales, payments);
            }
            return "";
        } catch (RuntimeException exception) {
            return "Los datos contienen campos inválidos, saldos incoherentes o valores fuera del límite permitido.";
        }
    }

    private String validateProducts() {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product == null || !product.isValid() || !isCanonicalCode(product.getProductCode())) {
                return "Existe un producto inválido o un código sin normalizar.";
            }
            for (int j = 0; j < i; j++) {
                if (products.get(j).getProductCode().equals(product.getProductCode())) {
                    return "El código de producto " + product.getProductCode() + " está repetido.";
                }
            }
        }
        return "";
    }

    private String validateSuppliers() {
        for (int i = 0; i < suppliers.size(); i++) {
            Supplier supplier = suppliers.get(i);
            if (supplier == null || !supplier.isValid()) {
                return "Existe un proveedor inválido.";
            }
            for (int j = 0; j < i; j++) {
                if (suppliers.get(j).getSupplierId() == supplier.getSupplierId()) {
                    return "El identificador de proveedor " + supplier.getSupplierId() + " está repetido.";
                }
            }
        }
        return "";
    }

    private String validateCustomers() {
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            if (customer == null || !customer.isValid()) {
                return "Existe un cliente inválido.";
            }
            for (int j = 0; j < i; j++) {
                if (customers.get(j).getCustomerId() == customer.getCustomerId()) {
                    return "El identificador de cliente " + customer.getCustomerId() + " está repetido.";
                }
            }
        }
        return "";
    }

    private String validateMovements() {
        for (int i = 0; i < movements.size(); i++) {
            StockMovement movement = movements.get(i);
            if (movement == null || !movement.isValid()
                    || !hasProduct(movement.getProductCode())) {
                return "Existe un movimiento inválido o asociado a un producto inexistente.";
            }
            for (int j = 0; j < i; j++) {
                if (movements.get(j).getMovementId() == movement.getMovementId()) {
                    return "El identificador de movimiento " + movement.getMovementId() + " está repetido.";
                }
            }
        }
        return "";
    }

    private String validateOrders() {
        for (int i = 0; i < orders.size(); i++) {
            PurchaseOrder order = orders.get(i);
            if (order == null || !order.isValid() || !hasSupplier(order.getSupplierId())) {
                return "Existe un pedido inválido o asociado a un proveedor inexistente.";
            }
            for (int j = 0; j < i; j++) {
                if (orders.get(j).getOrderId() == order.getOrderId()) {
                    return "El identificador de pedido " + order.getOrderId() + " está repetido.";
                }
            }
            for (PurchaseOrderItem item : order.getItems()) {
                if (!hasProduct(item.getProductCode())) {
                    return "El pedido " + order.getOrderId() + " contiene un producto inexistente.";
                }
            }
        }
        return "";
    }

    private String validateSales() {
        for (int i = 0; i < sales.size(); i++) {
            Sale sale = sales.get(i);
            if (sale == null || !sale.isValid() || !sale.isCompleted()) {
                return "El historial solo puede contener ventas válidas y completadas.";
            }
            if (sale.getCustomerId() != 0 && !hasCustomer(sale.getCustomerId())) {
                return "La venta " + sale.getSaleId() + " está asociada a un cliente inexistente.";
            }
            for (int j = 0; j < i; j++) {
                if (sales.get(j).getSaleId() == sale.getSaleId()) {
                    return "El identificador de venta " + sale.getSaleId() + " está repetido.";
                }
            }
            for (SaleItem item : sale.getItems()) {
                if (!hasProduct(item.getProductCode())) {
                    return "La venta " + sale.getSaleId() + " contiene un producto inexistente.";
                }
            }
        }
        return "";
    }

    private String validatePayments() {
        for (int i = 0; i < payments.size(); i++) {
            CustomerPayment payment = payments.get(i);
            if (payment == null || !payment.isValid() || !hasCustomer(payment.getCustomerId())) {
                return "Existe un pago inválido o asociado a un cliente inexistente.";
            }
            for (int j = 0; j < i; j++) {
                if (payments.get(j).getPaymentId() == payment.getPaymentId()) {
                    return "El identificador de pago " + payment.getPaymentId() + " está repetido.";
                }
            }
        }
        return "";
    }

    private String validateCashRecords() {
        for (int i = 0; i < cashRecords.size(); i++) {
            DailyCashRecord record = cashRecords.get(i);
            if (record == null || !record.isValid()) {
                return "Existe un registro de caja inválido.";
            }
            for (int j = 0; j < i; j++) {
                if (cashRecords.get(j).getDate().equals(record.getDate())) {
                    return "La fecha de caja " + record.getDate() + " está repetida.";
                }
            }
            record.calculateExpectedCash(sales, payments);
        }
        return "";
    }

    private boolean isCanonicalCode(String code) {
        return code != null && !code.isEmpty()
                && code.equals(code.trim().toUpperCase(Locale.ROOT));
    }

    private boolean hasProduct(String code) {
        if (!isCanonicalCode(code)) {
            return false;
        }
        for (Product product : products) {
            if (product.getProductCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasSupplier(int id) {
        for (Supplier supplier : suppliers) {
            if (supplier.getSupplierId() == id) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCustomer(int id) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == id) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<StockMovement> getMovements() {
        return movements;
    }

    public ArrayList<Supplier> getSuppliers() {
        return suppliers;
    }

    public ArrayList<PurchaseOrder> getOrders() {
        return orders;
    }

    public ArrayList<Sale> getSales() {
        return sales;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<CustomerPayment> getPayments() {
        return payments;
    }

    public ArrayList<DailyCashRecord> getCashRecords() {
        return cashRecords;
    }
}
