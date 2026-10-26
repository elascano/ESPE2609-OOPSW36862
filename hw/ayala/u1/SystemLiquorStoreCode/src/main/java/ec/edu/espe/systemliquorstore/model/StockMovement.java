package ec.edu.espe.systemliquorstore.model;

import ec.edu.espe.systemliquorstore.util.Validation;
import java.util.Locale;

public class StockMovement {
    private int movementId;
    private String date;
    private String productCode;
    private String movementType;
    private int quantity;
    private static final Validation validation = new Validation();

    public StockMovement(int movementId, String date, String productCode,
            String movementType, int quantity) {
        this.movementId = movementId;
        this.date = date == null ? "" : date.trim();
        this.productCode = productCode == null ? "" : productCode.trim().toUpperCase(Locale.ROOT);
        this.movementType = movementType == null ? "" : movementType.trim().toUpperCase(Locale.ROOT);
        this.quantity = quantity;
        if (this.movementId <= 0 || !validation.isValidDate(this.date)
                || !validation.isText(this.productCode)
                || (!this.movementType.equals("ENTRY") && !this.movementType.equals("EXIT"))
                || this.quantity <= 0) {
            throw new IllegalArgumentException("Revise los datos del movimiento de inventario.");
        }
    }

    public boolean isValid() {
        return movementId > 0 && validation.isValidDate(date)
                && validation.isText(productCode)
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
        return "ENTRY";
    }

    public int getQuantity() {
        return quantity;
    }
}
