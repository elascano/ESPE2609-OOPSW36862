package ec.edu.espe.provstock.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Merchandise bought from a supplier (Table B5). (RF06)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class Purchase {

    private int number;
    private LocalDateTime dateTime;
    private User registeredBy;
    private Supplier supplier;
    private String supplierInvoiceNumber;
    private List<PurchaseDetail> details;
    private double total;

    /**
     * A new purchase has number 0. PurchasingManager gives it its number when it is registered.
     * The supplier invoice number is optional (UC-15): null is saved as an empty text.
     */
    public Purchase(Supplier supplier, User registeredBy, String supplierInvoiceNumber) {
        this.number = 0;
        this.supplier = supplier;
        this.registeredBy = registeredBy;
        this.supplierInvoiceNumber = supplierInvoiceNumber == null ? "" : supplierInvoiceNumber.trim();
        this.details = new ArrayList<>();
    }

    /**
     * Rejects inactive products (FR-03.5). If the product is already in the purchase,
     * the quantities are added and the last unit cost is kept.
     */
    public void addProduct(Product product, double quantity, double unitCost) {
        checkEditable();
        if (product == null || !product.isActive()) {
            throw new IllegalArgumentException("El producto no existe o está inactivo");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
        }
        if (unitCost <= 0) {
            throw new IllegalArgumentException("El costo unitario debe ser mayor que 0");
        }

        if (contains(product)) {
            PurchaseDetail existingDetail = getDetail(product);
            existingDetail.setQuantity(existingDetail.getQuantity() + quantity);
            existingDetail.setUnitCost(unitCost);
        } else {
            details.add(new PurchaseDetail(product, quantity, unitCost));
        }
        calculateTotal();
    }

    /**
     * Adds a line read from the data files. It does not check if the product is still
     * active, because the purchase already happened. It only works while the purchase has
     * no number (BR-04). Only FileManager uses it.
     */
    public void restoreDetail(Product product, double quantity, double unitCost) {
        checkEditable();
        details.add(new PurchaseDetail(product, quantity, unitCost));
    }

    public void removeProduct(Product product) {
        checkEditable();
        details.remove(getDetail(product));
        calculateTotal();
    }

    public double calculateTotal() {
        double sum = 0;
        for (PurchaseDetail detail : details) {
            sum = sum + detail.calculateSubtotal();
        }
        total = Math.round(sum * 100.0) / 100.0;
        return total;
    }

    /**
     * Rejects inactive suppliers and inactive products (FR-03.5, Table B5).
     * The supplier invoice number is optional, so it is not checked here (UC-15).
     */
    public boolean validate() {
        if (supplier == null || !supplier.isActive() || registeredBy == null) {
            return false;
        }
        if (details.isEmpty()) {
            return false;
        }
        for (PurchaseDetail detail : details) {
            if (!detail.validate()) {
                return false;
            }
        }
        return true;
    }

    /**
     * BR-04: a registered purchase (with number) can not be edited.
     */
    private void checkEditable() {
        if (number != 0) {
            throw new IllegalStateException("La compra " + number + " ya está registrada y no puede editarse");
        }
    }

    private boolean contains(Product product) {
        for (PurchaseDetail detail : details) {
            if (detail.getProduct() == product) {
                return true;
            }
        }
        return false;
    }

    private PurchaseDetail getDetail(Product product) {
        for (PurchaseDetail detail : details) {
            if (detail.getProduct() == product) {
                return detail;
            }
        }
        throw new IllegalArgumentException("El producto no está en la compra");
    }

    @Override
    public String toString() {
        return "Purchase{" + "number=" + number + ", dateTime=" + dateTime + ", registeredBy="
                + (registeredBy == null ? null : registeredBy.getUsername())
                + ", supplier=" + (supplier == null ? null : supplier.getName())
                + ", supplierInvoiceNumber=" + supplierInvoiceNumber + ", details=" + details + ", total=" + total + '}';
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return the dateTime
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * @return the registeredBy
     */
    public User getRegisteredBy() {
        return registeredBy;
    }

    /**
     * @return the supplier
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * @return the supplierInvoiceNumber
     */
    public String getSupplierInvoiceNumber() {
        return supplierInvoiceNumber;
    }

    /**
     * @return a read-only list of the details
     */
    public List<PurchaseDetail> getDetails() {
        return Collections.unmodifiableList(details);
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }
}
