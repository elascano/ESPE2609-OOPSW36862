package ec.edu.espe.systemliquorstore.model;

import com.google.gson.annotations.SerializedName;
import ec.edu.espe.systemliquorstore.util.Validation;
import java.util.Locale;

public class SaleItem {
    @SerializedName("codigoProducto")
    private String productCode;
    @SerializedName("nombreProducto")
    private String productName;
    @SerializedName("cantidad")
    private int quantity;
    @SerializedName("costoUnitario")
    private double unitPurchaseCost;
    @SerializedName("precioUnitario")
    private double unitSellingPrice;

    public SaleItem(String productCode, String productName, int quantity,
            double unitPurchaseCost, double unitSellingPrice) {
        if (!Validation.isText(productCode) || !Validation.isText(productName)
                || quantity <= 0 || !Validation.isMoney(unitPurchaseCost)
                || unitPurchaseCost < 0 || !Validation.isMoney(unitSellingPrice)
                || unitSellingPrice <= 0
                || !Validation.isFinite(quantity * unitPurchaseCost)
                || !Validation.isFinite(quantity * unitSellingPrice)) {
            throw new IllegalArgumentException("Los datos del detalle de venta no son validos.");
        }
        this.productCode = productCode.trim().toUpperCase(Locale.ROOT);
        this.productName = productName.trim();
        this.quantity = quantity;
        this.unitPurchaseCost = unitPurchaseCost;
        this.unitSellingPrice = unitSellingPrice;
    }

    public double calculateSubtotal() {
        return Validation.roundMoney(quantity * unitSellingPrice);
    }

    public double calculatePurchaseCost() {
        return Validation.roundMoney(quantity * unitPurchaseCost);
    }

    public boolean isValid() {
        return Validation.isText(productCode) && Validation.isText(productName)
                && quantity > 0 && Validation.isMoney(unitPurchaseCost)
                && unitPurchaseCost >= 0 && Validation.isMoney(unitSellingPrice)
                && unitSellingPrice > 0
                && Validation.isFinite(quantity * unitPurchaseCost)
                && Validation.isFinite(quantity * unitSellingPrice);
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPurchaseCost() {
        return unitPurchaseCost;
    }

    public double getUnitSellingPrice() {
        return unitSellingPrice;
    }
}
