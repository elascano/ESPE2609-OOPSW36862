package ec.edu.espe.systemliquorstore.model;

import com.google.gson.annotations.SerializedName;
import ec.edu.espe.systemliquorstore.util.Validation;
import java.util.Locale;

public class StockMovement {
    @SerializedName("idMovimiento")
    private int movementId;
    @SerializedName("fecha")
    private String date;
    @SerializedName("codigoProducto")
    private String productCode;
    @SerializedName("tipoMovimiento")
    private String movementType;
    @SerializedName("cantidad")
    private int quantity;

    public StockMovement(int movementId, String date, String productCode,
            String movementType, int quantity) {
        this.movementId = movementId;
        this.date = date == null ? "" : date.trim();
        this.productCode = productCode == null ? "" : productCode.trim().toUpperCase(Locale.ROOT);
        this.movementType = movementType == null ? "" : movementType.trim().toUpperCase(Locale.ROOT);
        this.quantity = quantity;
        if (this.movementId <= 0 || !Validation.isValidDate(this.date)
                || !Validation.isText(this.productCode)
                || (!this.movementType.equals("ENTRY") && !this.movementType.equals("EXIT"))
                || this.quantity <= 0) {
            throw new IllegalArgumentException("Revise los datos del movimiento de inventario.");
        }
    }

    public boolean isValid() {
        return movementId > 0 && Validation.isValidDate(date)
                && Validation.isText(productCode)
                && (movementType.equals("ENTRY") || movementType.equals("EXIT"))
                && quantity > 0;
    }

    public int getMovementId() {
        return movementId;
    }

    public String getDate() {
        return date;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getMovementType() {
        return movementType;
    }

    public int getQuantity() {
        return quantity;
    }
}
