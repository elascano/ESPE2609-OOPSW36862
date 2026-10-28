package ec.edu.espe.unistock.model;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import java.math.BigDecimal;

public abstract class Product {

    private final int id;
    private final String name;
    private final BigDecimal price;
    private final ProductCategory category;
    private int stock;

    public Product(int id, String name, BigDecimal price, int stock, ProductCategory category) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio.");
        }
        if (price == null || price.signum() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        this.id = id;
        this.name = name.trim();
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public boolean hasStock(int quantity) {
        return stock >= quantity;
    }

    public void decreaseStock(int quantity) {
        if (!hasStock(quantity)) {
            throw new IllegalStateException("Stock insuficiente de: " + name);
        }
        stock -= quantity;
    }

    public void increaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }
        stock += quantity;
    }

    public abstract String getDetails();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public ProductCategory getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Product product && product.id == id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format("[%d] %-10s %-25s $%8.2f  Stock: %-4d %s",
                id, category.getLabel(), name, price, stock, getDetails());
    }
}
