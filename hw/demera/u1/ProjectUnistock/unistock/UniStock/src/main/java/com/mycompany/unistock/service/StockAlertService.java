package com.mycompany.unistock.service;

import com.mycompany.unistock.model.Product;
import com.mycompany.unistock.structure.SimpleList;

/**
 * Service to manage low stock alerts and dynamic threshold updates.
 *
 * @author Leslye Cuichan, Infinite loops, @ESPE
 */
public class StockAlertService {

    private int threshold;

    public StockAlertService() {
        this.threshold = 5;//valor of stock
    }

    public StockAlertService(int threshold) {
        this.threshold = threshold;
    }

    public SimpleList<Product> getLowStockProducts(SimpleList<Product> allProducts) {
        SimpleList<Product> lowStockProducts = new SimpleList<>();

        if (allProducts == null || allProducts.isEmpty()) {
            return lowStockProducts;
        }

        allProducts.resetIteration();
        while (allProducts.hasNext()) {
            Product product = allProducts.next();
            if (product != null && product.getStock() <= threshold) {
                lowStockProducts.add(product);
            }
        }

        return lowStockProducts;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}