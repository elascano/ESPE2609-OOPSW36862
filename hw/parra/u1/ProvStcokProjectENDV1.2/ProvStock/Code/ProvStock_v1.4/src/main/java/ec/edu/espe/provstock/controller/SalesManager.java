package ec.edu.espe.provstock.controller;

import ec.edu.espe.provstock.model.Customer;
import ec.edu.espe.provstock.model.Product;
import ec.edu.espe.provstock.model.Role;
import ec.edu.espe.provstock.model.Sale;
import ec.edu.espe.provstock.model.SaleDetail;
import ec.edu.espe.provstock.model.SaleFilter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

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

    //directed associations to other control classes (note N8 of the UML Design Specification)
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
        accessManager.requirePermission(Role.REGISTER_SALE);
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
    public void registerSale(Sale sale) {
        accessManager.requirePermission(Role.REGISTER_SALE);
        if (sale == null || sale.getNumber() != 0 || !sale.validate()) {
            throw new IllegalArgumentException("Los datos de la venta no son válidos");
        }
        checkProductsAvailable(sale);
        sale.calculateTotal();
        sale.setNumber(nextSaleNumber);
        sale.setDateTime(LocalDateTime.now());
        inventory.registerSaleMovements(sale);
        sales.add(sale);
        nextSaleNumber++;
    }

    private void checkProductsAvailable(Sale sale) {
        for (SaleDetail detail : sale.getDetails()) {
            Product product = detail.getProduct();
            if (!product.isActive() || !product.hasStock(detail.getQuantity())) {
                throw new IllegalArgumentException("El producto " + product.getName()
                        + " está inactivo o no tiene stock suficiente");
            }
        }
    }

    /**
     * UC-09 includes UC-11: the products return to the stock.
     */
    public void voidSale(Sale sale, String reason) {
        accessManager.requirePermission(Role.VOID_SALE);
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
        accessManager.requirePermission(Role.UPDATE_VAT_RATE);
        if (newRate < 0 || newRate >= 1) {
            throw new IllegalArgumentException("La tasa de IVA debe estar entre 0% y 99%");
        }
        vatRate = newRate;
    }

    public List<Sale> getAllSales() {
        return Collections.unmodifiableList(sales);
    }

    /**
     * UC-16: the sales that pass every filter that was set (FR-07.2).
     */
    public List<Sale> filterSales(SaleFilter filter) {
        List<Sale> result = new ArrayList<>();
        for (Sale sale : sales) {
            if (filter.matches(sale)) {
                result.add(sale);
            }
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

    /**
     * @throws NoSuchElementException if there is no sale with that number
     */
    public Sale findSaleByNumber(int number) {
        for (Sale sale : sales) {
            if (sale.getNumber() == number) {
                return sale;
            }
        }
        throw new NoSuchElementException("No existe esa venta.");
    }
}
