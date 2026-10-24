package com.mycompany.unistock.model;

import java.math.BigDecimal;

public class SaleItem {

    private final Product product;
    private final int quantity;

    public SaleItem(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public BigDecimal subtotal() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
