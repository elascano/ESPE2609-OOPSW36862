package ec.edu.espe.provstock.model;

/**
 * One line of a purchase. It can not exist without its purchase (composition). (RF06)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class PurchaseDetail {

    private Product product;
    private double quantity;
    private double unitCost;

    public PurchaseDetail(Product product, double quantity, double unitCost) {
        this.product = product;
        this.quantity = quantity;
        this.unitCost = unitCost;
    }

    public double calculateSubtotal() {
        return Math.round(quantity * unitCost * 100.0) / 100.0;
    }

    /**
     * BR-02: the quantity and the unit cost must be greater than 0.
     */
    public boolean validate() {
        return product != null && product.isActive() && quantity > 0 && unitCost > 0;
    }

    @Override
    public String toString() {
        return "PurchaseDetail{" + "product=" + (product == null ? null : product.getCode()) + ", quantity="
                + quantity + ", unitCost=" + unitCost + ", subtotal=" + calculateSubtotal() + '}';
    }

    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @return the quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the unitCost
     */
    public double getUnitCost() {
        return unitCost;
    }

    /**
     * @param unitCost the unitCost to set
     */
    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }
}
