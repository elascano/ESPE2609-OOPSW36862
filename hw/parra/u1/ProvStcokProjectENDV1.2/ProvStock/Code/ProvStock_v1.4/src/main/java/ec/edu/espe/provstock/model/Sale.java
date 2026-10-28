package ec.edu.espe.provstock.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A sale to a customer registered by a salesperson (Table B4). (RF04)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class Sale {

    //N2: allowed values of paymentMethod and status
    public static final String[] PAYMENT_METHODS = {"CASH", "TRANSFER", "CARD"};
    public static final String REGISTERED = "REGISTERED";
    public static final String VOIDED = "VOIDED";

    private int number;
    private LocalDateTime dateTime;
    private User registeredBy;
    private Customer customer;
    private List<SaleDetail> details;
    private String paymentMethod;
    private double vatRate;
    private double subtotal;
    private double vatAmount;
    private double total;
    private String status;
    private String voidReason;
    private LocalDateTime voidDateTime;
    private User voidedBy;

    /**
     * A new sale has number 0. SalesManager gives it its number when it is registered.
     */
    public Sale(Customer customer, User registeredBy, double vatRate) {
        this.number = 0;
        this.customer = customer;
        this.registeredBy = registeredBy;
        this.vatRate = vatRate;
        this.details = new ArrayList<>();
        this.paymentMethod = "CASH";
        this.status = REGISTERED;
    }

    /**
     * Rejects inactive products and quantities greater than the stock. If the product
     * is already in the sale, the quantities are added (FR-03.5, BR-03).
     */
    public void addProduct(Product product, double quantity) {
        checkEditable();
        checkProductAndQuantity(product, quantity);
        double newQuantity = quantityOf(product) + quantity;
        if (!product.hasStock(newQuantity)) {
            throw new IllegalArgumentException("Stock insuficiente de " + product.getName()
                    + ". Disponible: " + product.getCurrentStock());
        }
        if (contains(product)) {
            getDetail(product).setQuantity(newQuantity);
        } else {
            details.add(new SaleDetail(product, quantity, product.getSalePrice()));
        }
        calculateTotal();
    }

    private void checkProductAndQuantity(Product product, double quantity) {
        if (product == null || !product.isActive()) {
            throw new IllegalArgumentException("El producto no existe o está inactivo");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
        }
    }

    /**
     * Adds a line read from the data files, with the price it had on the day of the sale.
     * It does not check the stock or if the product is active, because the sale already
     * happened. It only works while the sale has no number (BR-04). Only FileManager uses it.
     */
    public void restoreDetail(Product product, double quantity, double unitPrice) {
        checkEditable();
        details.add(new SaleDetail(product, quantity, unitPrice));
    }

    public void removeProduct(Product product) {
        checkEditable();
        details.remove(getDetail(product));
        calculateTotal();
    }

    public double calculateSubtotal() {
        double sum = 0;
        for (SaleDetail detail : details) {
            sum = sum + detail.calculateSubtotal();
        }
        subtotal = round(sum);
        return subtotal;
    }

    public double calculateVat() {
        calculateSubtotal();
        vatAmount = round(subtotal * vatRate);
        return vatAmount;
    }

    public double calculateTotal() {
        calculateVat();
        total = round(subtotal + vatAmount);
        return total;
    }

    /**
     * BR-05, FR-04.8: a voided sale keeps its data and saves who voided it, when and why.
     */
    public void voidSale(String reason, User owner) {
        if (number == 0) {
            throw new IllegalStateException("La venta todavía no está registrada");
        }
        if (isVoided()) {
            throw new IllegalStateException("La venta ya está anulada");
        }
        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException("El motivo de anulación es obligatorio");
        }
        if (owner == null) {
            throw new IllegalArgumentException("Se necesita el usuario que anula la venta");
        }
        this.status = VOIDED;
        this.voidReason = reason;
        this.voidDateTime = LocalDateTime.now();
        this.voidedBy = owner;
    }

    /**
     * Marks as voided a sale read from the data files, keeping the original date of the
     * void. Only FileManager uses it.
     */
    public void restoreVoid(String reason, LocalDateTime voidDateTime, User voidedBy) {
        this.status = VOIDED;
        this.voidReason = reason;
        this.voidDateTime = voidDateTime;
        this.voidedBy = voidedBy;
    }

    public boolean isVoided() {
        return VOIDED.equals(status);
    }

    public boolean validate() {
        if (customer == null || !customer.isActive() || registeredBy == null) {
            return false;
        }
        if (details.isEmpty() || vatRate < 0) {
            return false;
        }
        if (!Arrays.asList(PAYMENT_METHODS).contains(paymentMethod)) {
            return false;
        }
        for (SaleDetail detail : details) {
            if (!detail.validate()) {
                return false;
            }
        }
        return true;
    }

    /**
     * BR-04: a registered sale (with number) can not be edited.
     */
    private void checkEditable() {
        if (number != 0) {
            throw new IllegalStateException("La venta " + number + " ya está registrada y no puede editarse");
        }
    }

    private boolean contains(Product product) {
        for (SaleDetail detail : details) {
            if (detail.getProduct() == product) {
                return true;
            }
        }
        return false;
    }

    private double quantityOf(Product product) {
        return contains(product) ? getDetail(product).getQuantity() : 0;
    }

    private SaleDetail getDetail(Product product) {
        for (SaleDetail detail : details) {
            if (detail.getProduct() == product) {
                return detail;
            }
        }
        throw new IllegalArgumentException("El producto no está en la venta");
    }

    private static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return "Sale{" + "number=" + number + ", dateTime=" + dateTime + ", registeredBy="
                + (registeredBy == null ? null : registeredBy.getUsername())
                + ", customer=" + (customer == null ? null : customer.getName()) + ", details=" + details
                + ", paymentMethod=" + paymentMethod + ", vatRate=" + vatRate + ", subtotal=" + subtotal
                + ", vatAmount=" + vatAmount + ", total=" + total + ", status=" + status + '}';
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
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        checkEditable();
        this.customer = customer;
    }

    /**
     * @return a read-only list of the details
     */
    public List<SaleDetail> getDetails() {
        return Collections.unmodifiableList(details);
    }

    /**
     * @return the paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * @param paymentMethod the paymentMethod to set
     */
    public void setPaymentMethod(String paymentMethod) {
        checkEditable();
        this.paymentMethod = paymentMethod;
    }

    /**
     * @return the vatRate
     */
    public double getVatRate() {
        return vatRate;
    }

    /**
     * @return the subtotal
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * @return the vatAmount
     */
    public double getVatAmount() {
        return vatAmount;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return the voidReason
     */
    public String getVoidReason() {
        return voidReason;
    }

    /**
     * @return the voidDateTime
     */
    public LocalDateTime getVoidDateTime() {
        return voidDateTime;
    }

    /**
     * @return the voidedBy
     */
    public User getVoidedBy() {
        return voidedBy;
    }
}
