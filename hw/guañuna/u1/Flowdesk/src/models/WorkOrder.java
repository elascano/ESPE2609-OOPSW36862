package models;

import enums.OrderStatus;
import utils.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utils.ConsoleInput;
import utils.Validation;
import exceptions.ValidationException;
import exceptions.InvalidPaymentException;

public class WorkOrder {

    private String orderId;
    private Date date;
    private double total;
    private double balance;
    private OrderStatus status;
    private Customer customer;
    private List<OrderDetail> details;
    private List<Payment> payments;
    private User user;

    private static List<WorkOrder> orders = new ArrayList<>();
    private static int orderCounter = 1;

    public WorkOrder() {
        this.details = new ArrayList<>();
        this.payments = new ArrayList<>();
    }

    public WorkOrder(String orderId, Date date, OrderStatus status) {
        this.orderId = orderId;
        this.date = date;
        this.status = status;
        this.details = new ArrayList<>();
        this.payments = new ArrayList<>();
        this.total = 0;
        this.balance = 0;
    }

    public static List<WorkOrder> getAll() { return orders; }

    public static void setAll(List<WorkOrder> newItems) {
        orders = newItems == null ? new ArrayList<>() : newItems;
        int maxId = 0;
        for (WorkOrder wo : orders) {
            try {
                if (wo.orderId != null && wo.orderId.startsWith("WO-")) {
                    int num = Integer.parseInt(wo.orderId.substring(3));
                    if (num > maxId) maxId = num;
                }
            } catch (NumberFormatException ignored) { }
        }
        orderCounter = maxId + 1;
    }

    public void create() {
        Validation.requireText(orderId, "identificador de la orden");
        Validation.requireValue(date, "fecha");
        Validation.requireValue(status, "estado");
        if (details == null || details.isEmpty()) {
            throw new ValidationException("La orden debe tener al menos un detalle.");
        }
        for (WorkOrder existing : orders) {
            if (Validation.sameIdentifier(existing.orderId, orderId)) {
                throw new ValidationException("Ya existe una orden con ese ID.");
            }
        }
        ArrayList<String> detailIds = new ArrayList<>();
        for (OrderDetail detail : details) {
            Validation.requireValue(detail, "detalle");
            detail.validate();
            for (String existingId : detailIds) {
                if (Validation.sameIdentifier(existingId, detail.getDetailId())) {
                    throw new ValidationException("La orden contiene detalles con el mismo ID.");
                }
            }
            detailIds.add(detail.getDetailId());
        }
        calculateTotals();
        orders.add(this);
    }

    public void addPayment(Payment payment) {
        if (payment == null) {
            throw new InvalidPaymentException("El pago no puede estar vacio.");
        }
        calculateTotals();
        payment.validateFor(this);
        payments.add(payment);
        payment.markRegistered();
        calculateTotals();
    }

    public double calculateTotalPaid() {
        double totalPaid = 0;
        for (Payment payment : payments) {
            totalPaid += payment.getAmount();
        }
        return totalPaid;
    }

    public void calculateTotals() {
        double calculatedTotal = 0;
        for (OrderDetail detail : details) {
            Validation.requireValue(detail, "detalle");
            calculatedTotal += detail.calculateSubtotal();
        }
        Validation.requireNonNegativeAmount(calculatedTotal, "total de la orden");
        double paid = calculateTotalPaid();
        Validation.requireNonNegativeAmount(paid, "total pagado");
        double calculatedBalance = calculatedTotal - paid;
        Validation.requireNonNegativeAmount(calculatedBalance, "saldo de la orden");
        this.total = calculatedTotal;
        this.balance = calculatedBalance;
    }

    public void changeStatus(OrderStatus newStatus) {
        Validation.requireValue(newStatus, "estado");
        this.status = newStatus;
    }

    public static void registerFromConsole(Scanner scanner, User employee) {
        System.out.println("\n--- CREAR NUEVA ORDEN DE TRABAJO ---");
        String newId = String.format("WO-%03d", orderCounter++);
        System.out.println("Numero de Orden generado: " + newId);

        String searchTerm = ConsoleInput.readRequiredText(scanner, "Ingrese ID o Cedula del Cliente: ");
        Customer customer = Customer.search(searchTerm);

        if (customer == null) {
            System.out.println("Error: Cliente no encontrado.");
            return;
        }

        System.out.println("Cliente asignado: " + customer.getName());
        
        WorkOrder newOrder = new WorkOrder(newId, Date.now(), OrderStatus.WAITING);
        newOrder.customer = customer;
        newOrder.user = employee;

        int detailCounter = 1;

        while (true) {
            System.out.println("\n1. Agregar Producto");
            System.out.println("2. Agregar Servicio");
            System.out.println("0. Terminar y guardar Orden");
            int option = ConsoleInput.readOption(scanner, "Opcion: ", 0, 2);

            if (option == 0) break;

            OrderDetail detail = option == 1
                    ? readProductDetail(scanner, "D" + detailCounter)
                    : readServiceDetail(scanner, "D" + detailCounter);

            if (detail != null) {
                newOrder.details.add(detail);
                detailCounter++;
                System.out.println("Elemento agregado. Subtotal: $" + detail.calculateSubtotal());
            } else {
                System.out.println("Elemento no valido.");
            }
        }

        newOrder.calculateTotals();
        newOrder.create();

        System.out.println("\n¡Orden " + newOrder.orderId + " creada exitosamente!");
        System.out.println("Total a pagar: $" + newOrder.total);
    }

    private static OrderDetail readProductDetail(Scanner scanner, String detailId) {
        if (Product.getAll().isEmpty()) {
            System.out.println("No hay productos.");
            return null;
        }
        Product.listFromConsole();
        String productId = ConsoleInput.readRequiredText(scanner, "Ingrese ID del Producto: ");
        for (Product product : Product.getAll()) {
            if (Validation.sameIdentifier(product.getId(), productId)) {
                int quantity = ConsoleInput.readPositiveInt(scanner, "Cantidad: ");
                OrderDetail detail = new OrderDetail(detailId, product.getName(), quantity,
                        product.getDescription(), product.getPrice());
                detail.setProduct(product);
                return detail;
            }
        }
        return null;
    }

    private static OrderDetail readServiceDetail(Scanner scanner, String detailId) {
        if (Service.getAll().isEmpty()) {
            System.out.println("No hay servicios.");
            return null;
        }
        Service.listFromConsole();
        String serviceId = ConsoleInput.readRequiredText(scanner, "Ingrese ID del Servicio: ");
        for (Service service : Service.getAll()) {
            if (Validation.sameIdentifier(service.getId(), serviceId)) {
                int quantity = ConsoleInput.readPositiveInt(scanner, "Cantidad (Horas/Veces): ");
                double price = ConsoleInput.readAmount(scanner, "Precio Unitario a cobrar: $");
                OrderDetail detail = new OrderDetail(detailId, service.getName(), quantity,
                        service.getDescription(), price);
                detail.setService(service);
                return detail;
            }
        }
        return null;
    }

    public static void listFromConsole() {
        System.out.println("\n--- HISTORIAL DE ORDENES ---");
        if (orders.isEmpty()) {
            System.out.println("No hay ordenes registradas.");
        } else {
            for (WorkOrder wo : orders) {
                String customerName = wo.customer == null ? "Sin cliente" : wo.customer.getName();
                System.out.println("- [" + wo.orderId + "] Fecha: " + wo.date
                        + " | Cliente: " + customerName
                        + " | Estado: " + wo.status
                        + " | Total: $" + wo.total
                        + " | Saldo: $" + wo.balance);
            }
        }
    }

    public static void updateStatusFromConsole(Scanner scanner) {
        System.out.println("\n--- ACTUALIZAR ESTADO DE ORDEN ---");
        if (orders.isEmpty()) {
            System.out.println("No hay ordenes registradas para actualizar.");
            return;
        }

        String searchId = ConsoleInput.readRequiredText(scanner, "Ingrese el numero de orden (ej. WO-001): ");
        WorkOrder matchingOrder = null;

        for (WorkOrder wo : orders) {
            if (Validation.sameIdentifier(wo.getOrderId(), searchId)) {
                matchingOrder = wo;
                break;
            }
        }

        if (matchingOrder == null) {
            System.out.println("Error: Orden no encontrada.");
            return;
        }

        System.out.println("Orden encontrada: " + matchingOrder.getOrderId());
        System.out.println("Estado actual: " + matchingOrder.getStatus());
        System.out.println("\nSeleccione el nuevo estado:");
        System.out.println("1. En espera");
        System.out.println("2. Aprobada");
        System.out.println("3. En proceso");
        System.out.println("4. Completada");
        System.out.println("5. Entregada");
        System.out.println("0. Volver");
        int option = ConsoleInput.readOption(scanner, "Opcion: ", 0, 5);
        if (option == 0) return;
        
        OrderStatus newStatus = switch (option) {
            case 1 -> OrderStatus.WAITING;
            case 2 -> OrderStatus.APPROVED;
            case 3 -> OrderStatus.IN_PROGRESS;
            case 4 -> OrderStatus.COMPLETED;
            case 5 -> OrderStatus.DELIVERED;
            default -> null;
        };

        if (newStatus == null) {
            System.out.println("Opcion no valida.");
            return;
        }

        matchingOrder.changeStatus(newStatus);
        System.out.println("¡Estado actualizado exitosamente a: " + matchingOrder.getStatus() + "!");
    }

    public String getOrderId() { return orderId; }
    public Date getDate() { return date; }
    public double getTotal() { return total; }
    public double getBalance() { return balance; }
    public OrderStatus getStatus() { return status; }
    public Customer getCustomer() { return customer; }
    public List<OrderDetail> getDetails() { return details; }
    public List<Payment> getPayments() { return payments; }
}