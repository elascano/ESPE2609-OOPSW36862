package ec.edu.espe.systemliquorstore.model;

import com.google.gson.annotations.SerializedName;
import ec.edu.espe.systemliquorstore.util.Validation;
import java.util.Locale;

public class PurchaseOrderItem {
    @SerializedName("codigoProducto")
    private String productCode;
    @SerializedName("cantidad")
    private int quantity;

    public PurchaseOrderItem(String productCode, int quantity) {
        this.productCode = productCode == null ? "" : productCode.trim().toUpperCase(Locale.ROOT);
        this.quantity = quantity;
        if (!Validation.isText(this.productCode) || this.quantity <= 0) {
            throw new IllegalArgumentException("El detalle del pedido requiere un producto y una cantidad positiva.");
        }
    }

    public boolean isValid() {
        return Validation.isText(productCode) && quantity > 0;
    }

    public String getProductCode() {
        return productCode;
    }

    public int getQuantity() {
        return quantity;
    }
}
