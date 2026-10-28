package ec.edu.espe.systemliquorstore.model;

import ec.edu.espe.systemliquorstore.util.Validation;
import java.util.Locale;

public class SaleItem {
    private String productCode;
    private String productName;
    private int quantity;
    private double unitPurchaseCost;
    private double unitSellingPrice;
    private static final Validation validation = new Validation();

    public SaleItem(String productCode, String productName, int quantity,
            double unitPurchaseCost, double unitSellingPrice) {
        if (!validation.isText(productCode) || !validation.isText(productName)
                || quantity <= 0 || !validation.isMoney(unitPurchaseCost)
                || unitPurchaseCost < 0 || !validation.isMoney(unitSellingPrice)
                || unitSellingPrice <= 0
                || !validation.isFinite(quantity * unitPurchaseCost)
                || !validation.isFinite(quantity * unitSellingPrice)) {
            throw new IllegalArgumentException("Los datos del detalle de venta no son validos.");
        }
        this.productCode = productCode.trim().toUpperCase(Locale.ROOT);
        this.productName = productName.trim();
        this.quantity = quantity;
        this.unitPurchaseCost = unitPurchaseCost;
        this.unitSellingPrice = unitSellingPrice;
    }

    public double calculateSubtotal() {
        return validation.roundMoney(quantity * unitSellingPrice);
    }

    public double calculatePurchaseCost() {
        return validation.roundMoney(quantity * unitPurchaseCost);
    }

    public boolean isValid() {
        return validation.isText(productCode) && validation.isText(productName)
                && quantity > 0 && validation.isMoney(unitPurchaseCost)
                && unitPurchaseCost >= 0 && validation.isMoney(unitSellingPrice)
                && unitSellingPrice > 0
                && validation.isFinite(quantity * unitPurchaseCost)
                && validation.isFinite(quantity * unitSellingPrice);
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
