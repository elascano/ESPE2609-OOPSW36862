package ec.edu.espe.unistock.service;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import ec.edu.espe.unistock.model.Product;
import ec.edu.espe.unistock.structure.SimpleList;

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
