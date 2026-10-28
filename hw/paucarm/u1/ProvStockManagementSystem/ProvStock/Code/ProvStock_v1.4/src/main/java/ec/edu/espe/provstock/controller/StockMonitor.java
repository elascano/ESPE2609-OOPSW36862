package ec.edu.espe.provstock.controller;

import ec.edu.espe.provstock.model.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * Calculates the stock alerts (UC-17, UC-18). (RF08)
 * The alerts are not saved: they are calculated from the stock and the
 * minimum stock, so they disappear by themselves (FR-08.4).
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class StockMonitor {

    //directed association to ProductCatalog (note N8 of the UML Design Specification)
    private final ProductCatalog productCatalog;

    public StockMonitor(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    /**
     * @return true if the product needs an alert
     */
    public boolean checkProduct(Product product) {
        return product != null && product.isActive() && product.isLowStock();
    }

    /**
     * Considers only the active products (Section 3.6).
     */
    public List<Product> getLowStockProducts() {
        List<Product> lowStockProducts = new ArrayList<>();
        for (Product product : productCatalog.getActiveProducts()) {
            if (checkProduct(product)) {
                lowStockProducts.add(product);
            }
        }
        return lowStockProducts;
    }

    public int countAlerts() {
        return getLowStockProducts().size();
    }
}
