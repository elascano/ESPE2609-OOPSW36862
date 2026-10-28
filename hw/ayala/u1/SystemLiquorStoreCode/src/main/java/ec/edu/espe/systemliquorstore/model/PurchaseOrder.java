package ec.edu.espe.systemliquorstore.model;

import ec.edu.espe.systemliquorstore.util.Validation;
import java.util.ArrayList;

public class PurchaseOrder {
    private int orderId;
    private int supplierId;
    private String orderDate;
    private String status;
    private String receivedDate;
    private ArrayList<PurchaseOrderItem> items;
    private static final Validation validation = new Validation();

    public PurchaseOrder(int orderId, int supplierId, String orderDate) {
        if (orderId <= 0 || supplierId <= 0 || !validation.isValidDate(orderDate)) {
            throw new IllegalArgumentException("Revise el identificador, proveedor y fecha del pedido.");
        }
        this.orderId = orderId;
        this.supplierId = supplierId;
        this.orderDate = orderDate;
        status = "PENDING";
        receivedDate = "";
        items = new ArrayList<PurchaseOrderItem>();
    }

    public PurchaseOrderItem addProduct(Product product, int quantity) {
        if (!isPending()) {
            throw new IllegalStateException("No se puede modificar un pedido ya recibido.");
        }
        if (product == null || !product.isValid()) {
            throw new IllegalArgumentException("Seleccione un producto registrado y valido.");
        }
        PurchaseOrderItem item = new PurchaseOrderItem(product.getProductCode(), quantity);
        items.add(item);
        return item;
    }

    public void receive(ArrayList<Product> products, ArrayList<StockMovement> movements, String date) {
        if (!isPending()) {
            throw new IllegalStateException("Este pedido ya fue recibido y no puede aumentar el inventario otra vez.");
        }
        if (!isValid()) {
            throw new IllegalStateException("El pedido debe tener al menos un detalle valido.");
        }
        if (products == null || movements == null || !validation.isValidDate(date)
                || date.compareTo(orderDate) < 0) {
            throw new IllegalArgumentException("Revise el inventario y la fecha de recepcion del pedido.");
        }
        ArrayList<Product> affectedProducts = new ArrayList<Product>();
        for (PurchaseOrderItem item : items) {
            Product product = findProduct(products, item.getProductCode());
            if (product == null || !product.isValid()) {
                throw new IllegalArgumentException("No existe un producto valido para el codigo " + item.getProductCode() + ".");
            }
            if (!affectedProducts.contains(product)) {
                affectedProducts.add(product);
            }
        }
        for (Product product : affectedProducts) {
            int quantity = getOrderedQuantity(product.getProductCode());
            if ((long) product.getStock() + quantity > Integer.MAX_VALUE) {
                throw new IllegalArgumentException("La recepcion supera el inventario maximo de " + product.getName() + ".");
            }
        }
        int largestId = 0;
        for (StockMovement movement : movements) {
            if (movement == null || !movement.isValid()) {
                throw new IllegalArgumentException("La lista contiene un movimiento de inventario invalido.");
            }
            if (movement.getMovementId() > largestId) {
                largestId = movement.getMovementId();
            }
        }
        if ((long) largestId + affectedProducts.size() > Integer.MAX_VALUE) {
            throw new IllegalStateException("No quedan identificadores disponibles para registrar la recepcion.");
        }
        ArrayList<StockMovement> newMovements = new ArrayList<StockMovement>();
        int nextId = largestId;
        for (Product product : affectedProducts) {
            nextId++;
            int quantity = getOrderedQuantity(product.getProductCode());
            newMovements.add(product.createStockMovement(nextId, date, "ENTRY", quantity));
        }
        for (int i = 0; i < affectedProducts.size(); i++) {
            affectedProducts.get(i).increaseStock(newMovements.get(i).getQuantity());
        }
        movements.addAll(newMovements);
        receivedDate = date;
        status = "RECEIVED";
    }

    public boolean isPending() {
        return status.equals("PENDING");
    }

    public boolean isValid() {
        if (orderId <= 0 || supplierId <= 0 || !validation.isValidDate(orderDate)
                || items == null || items.isEmpty()) {
            return false;
        }
        if (status.equals("PENDING")) {
            if (!receivedDate.isEmpty()) {
                return false;
            }
        } else if (status.equals("RECEIVED")) {
            if (!validation.isValidDate(receivedDate) || receivedDate.compareTo(orderDate) < 0) {
                return false;
            }
        } else {
            return false;
        }
        for (PurchaseOrderItem item : items) {
            if (item == null || !item.isValid()) {
                return false;
            }
        }
        return true;
    }

    private Product findProduct(ArrayList<Product> products, String productCode) {
        Product found = null;
        for (Product product : products) {
            if (product == null) {
                throw new IllegalArgumentException("La lista de productos contiene un registro invalido.");
            }
            if (product.getProductCode().equalsIgnoreCase(productCode)) {
                if (found != null) {
                    throw new IllegalArgumentException("El codigo " + productCode + " esta duplicado en el inventario.");
                }
                found = product;
            }
        }
        return found;
    }

    private int getOrderedQuantity(String productCode) {
        long total = 0;
        for (PurchaseOrderItem item : items) {
            if (item.getProductCode().equalsIgnoreCase(productCode)) {
                total += item.getQuantity();
                if (total > Integer.MAX_VALUE) {
                    throw new IllegalArgumentException("La cantidad solicitada supera el limite permitido para " + productCode + ".");
                }
            }
        }
        return (int) total;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public ArrayList<PurchaseOrderItem> getItems() {
        return new ArrayList<PurchaseOrderItem>(items);
    }
}
