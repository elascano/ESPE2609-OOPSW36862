package ec.edu.espe.systemliquorstore.model;

import ec.edu.espe.systemliquorstore.util.Validation;
import java.util.Locale;

public class PurchaseOrderItem {
    private String productCode;
    private int quantity;
    private static final Validation validation = new Validation();

    public PurchaseOrderItem(String productCode, int quantity) {
        this.productCode = productCode == null ? "" : productCode.trim().toUpperCase(Locale.ROOT);
        this.quantity = quantity;
        if (!validation.isText(this.productCode) || this.quantity <= 0) {
            throw new IllegalArgumentException("El detalle del pedido requiere un producto y una cantidad positiva.");
        }
    }

    public boolean isValid() {
        return validation.isText(productCode) && quantity > 0;
    }

    public String getProductCode() {
        return productCode;
    }

    public int getQuantity() {
        return quantity;
    }
}
