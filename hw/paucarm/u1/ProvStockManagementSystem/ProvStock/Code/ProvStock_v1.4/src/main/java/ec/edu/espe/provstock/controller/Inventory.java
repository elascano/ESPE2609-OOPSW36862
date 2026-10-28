package ec.edu.espe.provstock.controller;

import ec.edu.espe.provstock.model.Product;
import ec.edu.espe.provstock.model.Purchase;
import ec.edu.espe.provstock.model.PurchaseDetail;
import ec.edu.espe.provstock.model.Role;
import ec.edu.espe.provstock.model.Sale;
import ec.edu.espe.provstock.model.SaleDetail;
import ec.edu.espe.provstock.model.StockMovement;
import ec.edu.espe.provstock.model.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The only class that changes the stock; it records every movement (UC-11 to UC-13). (RF05)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class Inventory {

    private List<StockMovement> movements;

    //directed associations to other control classes (note N8 of the UML Design Specification)
    private final StockMonitor stockMonitor;
    private final ProductCatalog productCatalog;

    public Inventory(StockMonitor stockMonitor, ProductCatalog productCatalog) {
        this.movements = new ArrayList<>();
        this.stockMonitor = stockMonitor;
        this.productCatalog = productCatalog;
    }

    /**
     * Takes out of the stock the products of a sale.
     */
    public void registerSaleMovements(Sale sale) {
        //first we check all the products, so the stock is not changed only in part
        for (SaleDetail detail : sale.getDetails()) {
            if (!detail.getProduct().hasStock(detail.getQuantity())) {
                throw new IllegalArgumentException("Stock insuficiente de " + detail.getProduct().getName());
            }
        }
        for (SaleDetail detail : sale.getDetails()) {
            applyMovement(new StockMovement(movements.size() + 1, detail.getProduct(), StockMovement.SALE,
                    -detail.getQuantity(), sale, null, sale.getRegisteredBy(), null));
        }
    }

    /**
     * Adds to the stock the products of a purchase.
     */
    public void registerPurchaseMovements(Purchase purchase) {
        for (PurchaseDetail detail : purchase.getDetails()) {
            applyMovement(new StockMovement(movements.size() + 1, detail.getProduct(), StockMovement.PURCHASE,
                    detail.getQuantity(), null, purchase, purchase.getRegisteredBy(), null));
        }
    }

    /**
     * Returns to the stock the products of a voided sale.
     */
    public void restoreSaleStock(Sale sale) {
        if (!sale.isVoided()) {
            throw new IllegalStateException("Solo se restaura el stock de una venta anulada");
        }
        for (SaleDetail detail : sale.getDetails()) {
            applyMovement(new StockMovement(movements.size() + 1, detail.getProduct(), StockMovement.VOIDED_SALE,
                    detail.getQuantity(), sale, null, sale.getVoidedBy(), sale.getVoidReason()));
        }
    }

    /**
     * Manual adjustment (UC-13). A positive quantity adds stock and a negative one takes it out.
     * The initial stock is loaded with the reason "Inventario inicial" (AS-05, FR-05.4).
     */
    public void adjustStock(Product product, double quantity, String reason, User user) {
        //sensitive operation: the permission is checked again (NFR-02)
        if (user == null || !user.hasPermission(Role.ADJUST_STOCK)) {
            throw new SecurityException("No tiene permiso para ajustar el stock");
        }
        if (product == null || !product.isActive() || !productCatalog.isRegistered(product.getCode())
                || productCatalog.findByCode(product.getCode()) != product) {
            throw new IllegalArgumentException("El producto no existe o está inactivo");
        }
        if (quantity == 0) {
            throw new IllegalArgumentException("La cantidad del ajuste no puede ser 0");
        }
        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException("El motivo del ajuste es obligatorio");
        }
        applyMovement(new StockMovement(movements.size() + 1, product, StockMovement.ADJUSTMENT,
                quantity, null, null, user, reason));
    }

    /**
     * Rebuilds the stock of every product from the movements read from the data files.
     * The products must start with stock 0; each movement is applied again in order, so
     * the stock is saved only once: as the sum of its movements (FR-03.3).
     */
    public void restoreMovements(List<StockMovement> savedMovements) {
        movements = new ArrayList<>();
        for (StockMovement movement : savedMovements) {
            //N4: Inventory is the only class that calls Product.updateStock()
            movement.getProduct().updateStock(movement.getQuantity());
            movements.add(movement);
        }
    }

    /**
     * @return all the movements (needed to save them in a file)
     */
    public List<StockMovement> getAllMovements() {
        return Collections.unmodifiableList(movements);
    }

    public List<Product> getStockOverview() {
        return productCatalog.getActiveProducts();
    }

    public List<StockMovement> getMovementsByProduct(Product product) {
        List<StockMovement> result = new ArrayList<>();
        for (StockMovement movement : movements) {
            if (movement.getProduct() == product) {
                result.add(movement);
            }
        }
        return result;
    }

    /**
     * UC-11 includes UC-17: after every change the minimum stock is checked.
     */
    private void applyMovement(StockMovement movement) {
        if (!movement.validate()) {
            throw new IllegalArgumentException("El movimiento de stock no es válido");
        }
        //N4: Inventory is the only class that calls Product.updateStock()
        movement.getProduct().updateStock(movement.getQuantity());
        movements.add(movement);
        stockMonitor.checkProduct(movement.getProduct());
    }
}
