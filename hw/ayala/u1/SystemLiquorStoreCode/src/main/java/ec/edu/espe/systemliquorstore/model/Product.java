package ec.edu.espe.systemliquorstore.model;

import ec.edu.espe.systemliquorstore.util.Validation;
import java.util.ArrayList;
import java.util.Locale;

public class Product {
    private String productCode;
    private String name;
    private double purchaseCost;
    private double sellingPrice;
    private int stock;
    private int minimumStock;
    private static final Validation validation = new Validation();

    public Product(String productCode, String name, double purchaseCost,
            double sellingPrice, int stock, int minimumStock) {
        this.productCode = productCode == null ? "" : productCode.trim().toUpperCase(Locale.ROOT);
        this.name = name == null ? "" : name.trim();
        this.purchaseCost = purchaseCost;
        this.sellingPrice = sellingPrice;
        this.stock = stock;
        this.minimumStock = minimumStock;
        if (!validation.isText(this.productCode) || !validation.isText(this.name)
                || !validation.isMoney(this.purchaseCost) || this.purchaseCost < 0
                || !validation.isMoney(this.sellingPrice) || this.sellingPrice <= 0
                || this.stock < 0 || this.minimumStock < 0) {
            throw new IllegalArgumentException("Revise el codigo, nombre, precios y cantidades del producto.");
        }
    }

    public void increaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad de entrada debe ser mayor que cero.");
        }
        if ((long) stock + quantity > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("La entrada supera la cantidad maxima de inventario.");
        }
        stock += quantity;
    }

    public void decreaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad de salida debe ser mayor que cero.");
        }
        if (quantity > stock) {
            throw new IllegalArgumentException("No hay suficiente inventario de " + name + ".");
        }
        stock -= quantity;
    }

    public boolean isLowStock() {
        return stock <= minimumStock;
    }

    public void setMinimumStock(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("El inventario minimo no puede ser negativo.");
        }
        minimumStock = value;
    }

    public void updatePrices(double cost, double price) {
        if (!validation.isMoney(cost) || cost < 0
                || !validation.isMoney(price) || price <= 0) {
            throw new IllegalArgumentException("El costo debe ser cero o mayor y el precio mayor que cero, con hasta dos decimales.");
        }
        purchaseCost = cost;
        sellingPrice = price;
    }

    public StockMovement createStockMovement(int id, String date, String type, int quantity) {
        return new StockMovement(id, date, productCode, type, quantity);
    }

    public int getUnitsSold(ArrayList<Sale> sales, String startDate, String endDate) {
        if (sales == null || !validation.isValidDate(startDate)
                || !validation.isValidDate(endDate) || startDate.compareTo(endDate) > 0) {
            throw new IllegalArgumentException("Revise la lista de ventas y el intervalo de fechas.");
        }
        long total = 0;
        for (Sale sale : sales) {
            if (sale == null) {
                throw new IllegalArgumentException("La lista contiene una venta invalida.");
            }
            if (sale.isCompleted() && sale.getDate().compareTo(startDate) >= 0
                    && sale.getDate().compareTo(endDate) <= 0) {
                total += sale.getProductQuantity(productCode);
                if (total > Integer.MAX_VALUE) {
                    throw new IllegalArgumentException("La cantidad vendida supera el limite permitido.");
                }
            }
        }
        return (int) total;
    }

    public boolean isValid() {
        return validation.isText(productCode) && validation.isText(name)
                && validation.isMoney(purchaseCost) && purchaseCost >= 0
                && validation.isMoney(sellingPrice) && sellingPrice > 0
                && stock >= 0 && minimumStock >= 0;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!validation.isText(name)) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacio.");
        }
        this.name = name.trim();
    }

    public double getPurchaseCost() {
        return purchaseCost;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public int getStock() {
        return stock;
    }

    public int getMinimumStock() {
        return minimumStock;
    }
}
