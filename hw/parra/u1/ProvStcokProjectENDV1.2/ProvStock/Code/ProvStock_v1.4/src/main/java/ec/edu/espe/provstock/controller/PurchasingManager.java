package ec.edu.espe.provstock.controller;

import ec.edu.espe.provstock.model.Purchase;
import ec.edu.espe.provstock.model.Role;
import ec.edu.espe.provstock.model.Supplier;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Manages suppliers and registers purchases (UC-14, UC-15). (RF06)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class PurchasingManager {

    private List<Supplier> suppliers;
    private List<Purchase> purchases;
    private int nextPurchaseNumber;

    //directed associations to other control classes (note N8 of the UML Design Specification)
    private final Inventory inventory;
    private final AccessManager accessManager;

    public PurchasingManager(Inventory inventory, AccessManager accessManager) {
        this.suppliers = new ArrayList<>();
        this.purchases = new ArrayList<>();
        this.nextPurchaseNumber = 1;
        this.inventory = inventory;
        this.accessManager = accessManager;
    }

    /**
     * @throws IllegalArgumentException if the data is not valid or the RUC already exists (BR-08)
     */
    public void registerSupplier(Supplier supplier) {
        accessManager.requirePermission(Role.MANAGE_SUPPLIERS);
        if (supplier == null || !supplier.validate()) {
            throw new IllegalArgumentException("Los datos del proveedor no son válidos");
        }
        if (isRegistered(supplier.getRuc())) {
            throw new IllegalArgumentException("Ya existe un proveedor con el RUC " + supplier.getRuc() + ".");
        }
        supplier.setId(suppliers.size() + 1);
        suppliers.add(supplier);
    }

    public void updateSupplier(Supplier supplier) {
        accessManager.requirePermission(Role.MANAGE_SUPPLIERS);
        if (!suppliers.contains(supplier)) {
            throw new IllegalArgumentException("El proveedor no existe");
        }
        if (!supplier.validate()) {
            throw new IllegalArgumentException("Los datos del proveedor no son válidos");
        }
    }

    public void deactivateSupplier(Supplier supplier) {
        accessManager.requirePermission(Role.MANAGE_SUPPLIERS);
        if (!suppliers.contains(supplier)) {
            throw new IllegalArgumentException("El proveedor no existe");
        }
        supplier.deactivate();
    }

    /**
     * Searches active suppliers by name or RUC. An empty text returns all of them.
     */
    public List<Supplier> searchSuppliers(String text) {
        String search = text == null ? "" : text.trim().toLowerCase();
        List<Supplier> result = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            if (supplier.isActive() && (supplier.getName().toLowerCase().contains(search)
                    || supplier.getRuc().contains(search))) {
                result.add(supplier);
            }
        }
        return result;
    }

    /**
     * UC-15 includes UC-11: the stock is updated when the purchase is registered.
     */
    public void registerPurchase(Purchase purchase) {
        accessManager.requirePermission(Role.REGISTER_PURCHASE);
        if (purchase == null || purchase.getNumber() != 0 || !purchase.validate()) {
            throw new IllegalArgumentException("Los datos de la compra no son válidos");
        }
        purchase.calculateTotal();
        purchase.setNumber(nextPurchaseNumber);
        purchase.setDateTime(LocalDateTime.now());
        inventory.registerPurchaseMovements(purchase);
        purchases.add(purchase);
        nextPurchaseNumber++;
    }

    /**
     * Every date that is null is not used.
     */
    public List<Purchase> getPurchases(LocalDate start, LocalDate end) {
        List<Purchase> result = new ArrayList<>();
        for (Purchase purchase : purchases) {
            LocalDate date = purchase.getDateTime().toLocalDate();
            if ((start == null || !date.isBefore(start)) && (end == null || !date.isAfter(end))) {
                result.add(purchase);
            }
        }
        return result;
    }

    /**
     * Replaces the suppliers and purchases with the ones read from the data files.
     * The next purchase number continues after the biggest saved number.
     */
    public void restoreData(List<Supplier> savedSuppliers, List<Purchase> savedPurchases) {
        suppliers = new ArrayList<>(savedSuppliers);
        purchases = new ArrayList<>(savedPurchases);
        nextPurchaseNumber = 1;
        for (Purchase purchase : purchases) {
            if (purchase.getNumber() >= nextPurchaseNumber) {
                nextPurchaseNumber = purchase.getNumber() + 1;
            }
        }
    }

    /**
     * @return all the suppliers, active and inactive (needed to save them in a file)
     */
    public List<Supplier> getAllSuppliers() {
        return Collections.unmodifiableList(suppliers);
    }

    /**
     * @throws NoSuchElementException if no supplier has that RUC
     */
    public Supplier findByRuc(String ruc) {
        for (Supplier supplier : suppliers) {
            if (hasRuc(supplier, ruc)) {
                return supplier;
            }
        }
        throw new NoSuchElementException("No existe el proveedor con RUC " + ruc + ".");
    }

    private boolean isRegistered(String ruc) {
        for (Supplier supplier : suppliers) {
            if (hasRuc(supplier, ruc)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasRuc(Supplier supplier, String ruc) {
        return ruc != null && supplier.getRuc().equals(ruc.trim());
    }
}
