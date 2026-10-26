package ec.edu.espe.provstock.model;

/**
 * One line of a sale. It can not exist without its sale (composition). (RF04)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class SaleDetail {

    private Product product;
    private double quantity;
    private double unitPrice;

    public SaleDetail(Product product, double quantity, double unitPrice) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public double calculateSubtotal() {
        return Math.round(quantity * unitPrice * 100.0) / 100.0;
    }

    public boolean validate() {
        return product != null && quantity > 0 && unitPrice >= 0;
    }

    @Override
    public String toString() {
        return "SaleDetail{" + "product=" + (product == null ? null : product.getCode()) + ", quantity=" + quantity
                + ", unitPrice=" + unitPrice + ", subtotal=" + calculateSubtotal() + '}';
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
     * @return the unitPrice
     */
    public double getUnitPrice() {
        return unitPrice;
    }
}
