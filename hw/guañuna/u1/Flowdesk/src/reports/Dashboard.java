package reports;

import enums.OrderStatus;
import models.WorkOrder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dashboard {
    private List<WorkOrder> orders;
    private Map<OrderStatus, Integer> quantityByStatus;

    public Dashboard() {
        this.quantityByStatus = new HashMap<>();
    }

    public void setOrders(List<WorkOrder> orders) {
        this.orders = orders;
    }

    public void showDashboard() {
        System.out.println("\n\n>>> CARGANDO PANEL DE CONTROL <<<");

        if (orders == null || orders.isEmpty()) {
            System.out.println("[!] ERROR: La lista de ordenes esta vacia.");
            System.out.println("[!] Asegurate de crear una orden primero.");
            return;
        }

        quantityByStatus.clear();
        for (OrderStatus status : OrderStatus.values()) {
            quantityByStatus.put(status, 0);
        }

        for (WorkOrder order : orders) {
            OrderStatus status = order.getStatus();
            quantityByStatus.put(status, quantityByStatus.get(status) + 1);
        }

        System.out.println("\n=============================================");
        System.out.println("         PANEL DE CONTROL - ESTADO DE ORDENES       ");
        System.out.println("=============================================");
        System.out.println("Total de ordenes en el sistema: " + orders.size());
        System.out.println("---------------------------------------------");

        for (Map.Entry<OrderStatus, Integer> entry : quantityByStatus.entrySet()) {
            System.out.println(" - " + entry.getKey() + ": " + entry.getValue() + " orden(es)");
        }
        System.out.println("=============================================");

        System.out.println("\n--- ORDENES PENDIENTES (EN ESPERA / EN PROCESO) ---");
        boolean hasPendingOrders = false;
        for (WorkOrder wo : orders) {
            if (wo.getStatus() == OrderStatus.WAITING || wo.getStatus() == OrderStatus.IN_PROGRESS) {
                System.out.println("- [" + wo.getOrderId() + "] Cliente: " + wo.getCustomer().getName() + " | Estado: " + wo.getStatus());
                hasPendingOrders = true;
            }
        }
        if (!hasPendingOrders) {
            System.out.println("No hay ordenes pendientes. ¡Todo al dia!");
        }

        System.out.println("\n--- HISTORIAL COMPLETO DE ORDENES ---");
        for (WorkOrder wo : orders) {
            System.out.println("- [" + wo.getOrderId() + "] Fecha: " + wo.getDate().toString() +
                               " | Cliente: " + wo.getCustomer().getName() +
                               " | Estado: " + wo.getStatus() +
                               " | Total: $" + wo.getTotal() +
                               " | Saldo: $" + wo.getBalance());
        }

        System.out.println("=============================================");
        System.out.println("\n>>> FIN DEL PANEL DE CONTROL <<<\n");
    }

    public List<WorkOrder> getOrders() { return orders; }
    public Map<OrderStatus, Integer> getQuantityByStatus() { return quantityByStatus; }
}
