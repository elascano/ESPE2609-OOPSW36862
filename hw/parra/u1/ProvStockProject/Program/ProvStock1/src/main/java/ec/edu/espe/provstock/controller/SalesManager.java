package ec.edu.espe.provstock.controller;

import ec.edu.espe.provstock.model.Customer;
import ec.edu.espe.provstock.model.Role;
import ec.edu.espe.provstock.model.Sale;
import ec.edu.espe.provstock.model.SaleDetail;
import ec.edu.espe.provstock.model.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Registers and voids sales, keeps the VAT rate and offers the sales history
 * (UC-08 to UC-10, UC-16). (RF04, RF07)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class SalesManager {

    private List<Sale> sales;
    private int nextSaleNumber;
    private double vatRate = 0.15;

    //dependencies «use»
    private final Inventory inventory;
    private final CustomerManager customerManager;
    private final AccessManager accessManager;

    public SalesManager(Inventory inventory, CustomerManager customerManager, AccessManager accessManager) {
        this.sales = new ArrayList<>();
        this.nextSaleNumber = 1;
        this.inventory = inventory;
        this.customerManager = customerManager;
        this.accessManager = accessManager;
    }

    /**
     * If the customer is null, the sale is for the "Consumidor Final".
     */
    public Sale createSale(Customer customer) {
        requirePermission(Role.REGISTER_SALE);
        if (customer == null) {
            customer = customerManager.getFinalConsumer();
        }
        if (!customer.isActive()) {
            throw new IllegalArgumentException("El cliente está inactivo");
        }
        return new Sale(customer, accessManager.getCurrentUser(), vatRate);
    }

    /**
     * UC-08 includes UC-11: the stock is updated when the sale is registered.
     */
    public boolean registerSale(Sale sale) {
        requirePermission(Role.REGISTER_SALE);
        if (sale == null || sale.getNumber() != 0 || !sale.validate()) {
            return false;
        }
        for (SaleDetail detail : sale.getDetails()) {
            if (!detail.getProduct().isActive() || !detail.getProduct().hasStock(detail.getQuantity())) {
                return false;
            }
        }
        sale.calculateTotal();
        sale.setNumber(nextSaleNumber);
        sale.setDateTime(LocalDateTime.now());
        inventory.registerSaleMovements(sale);
        sales.add(sale);
        nextSaleNumber++;
        return true;
    }

    /**
     * UC-09 includes UC-11: the products return to the stock.
     */
    public void voidSale(Sale sale, String reason) {
        requirePermission(Role.VOID_SALE);
        if (!sales.contains(sale)) {
            throw new IllegalArgumentException("La venta no existe");
        }
        sale.voidSale(reason, accessManager.getCurrentUser());
        inventory.restoreSaleStock(sale);
    }

    public double getVatRate() {
        return vatRate;
    }

    /**
     * The new rate is used only in the new sales; registered sales keep their own rate.
     */
    public void updateVatRate(double newRate) {
        requirePermission(Role.UPDATE_VAT_RATE);
        if (newRate < 0 || newRate >= 1) {
            throw new IllegalArgumentException("La tasa de IVA debe estar entre 0% y 99%");
        }
        vatRate = newRate;
    }

    public List<Sale> getAllSales() {
        return Collections.unmodifiableList(sales);
    }

    /**
     * Every filter that is null is not used.
     */
    public List<Sale> filterSales(LocalDate start, LocalDate end, User salesperson, Customer customer,
            String status) {
        List<Sale> result = new ArrayList<>();
        for (Sale sale : sales) {
            LocalDate date = sale.getDateTime().toLocalDate();
            if (start != null && date.isBefore(start)) {
                continue;
            }
            if (end != null && date.isAfter(end)) {
                continue;
            }
            if (salesperson != null && sale.getRegisteredBy() != salesperson) {
                continue;
            }
            if (customer != null && sale.getCustomer() != customer) {
                continue;
            }
            if (status != null && !status.isBlank() && !sale.getStatus().equals(status)) {
                continue;
            }
            result.add(sale);
        }
        return result;
    }

    /**
     * Replaces the sales and the VAT rate with the ones read from the data files.
     * The next sale number continues after the biggest saved number.
     */
    public void restoreData(List<Sale> savedSales, double savedVatRate) {
        sales = new ArrayList<>(savedSales);
        vatRate = savedVatRate;
        nextSaleNumber = 1;
        for (Sale sale : sales) {
            if (sale.getNumber() >= nextSaleNumber) {
                nextSaleNumber = sale.getNumber() + 1;
            }
        }
    }

    public Sale findSaleByNumber(int number) {
        for (Sale sale : sales) {
            if (sale.getNumber() == number) {
                return sale;
            }
        }
        return null;
    }

    /**
     * Sensitive operations check the permission again (NFR-02, UI-02).
     */
    private void requirePermission(String permission) {
        if (!accessManager.checkPermission(permission)) {
            throw new SecurityException("No tiene permiso para esta operación (" + permission + ")");
        }
    }
}
