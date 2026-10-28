package ec.edu.espe.provstock.controller;

import ec.edu.espe.provstock.model.Purchase;
import ec.edu.espe.provstock.model.Role;
import ec.edu.espe.provstock.model.Supplier;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages suppliers and registers purchases (UC-14, UC-15). (RF06)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class PurchasingManager {

    private List<Supplier> suppliers;
    private List<Purchase> purchases;
    private int nextPurchaseNumber;

    //dependencies «use»
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
     * @return false if the data is not valid or the RUC already exists (BR-08)
     */
    public boolean registerSupplier(Supplier supplier) {
        requirePermission(Role.MANAGE_SUPPLIERS);
        if (supplier == null || !supplier.validate()) {
            return false;
        }
        if (findByRuc(supplier.getRuc()) != null) {
            return false;
        }
        supplier.setId(suppliers.size() + 1);
        suppliers.add(supplier);
        return true;
    }

    public void updateSupplier(Supplier supplier) {
        requirePermission(Role.MANAGE_SUPPLIERS);
        if (!suppliers.contains(supplier)) {
            throw new IllegalArgumentException("El proveedor no existe");
        }
        if (!supplier.validate()) {
            throw new IllegalArgumentException("Los datos del proveedor no son válidos");
        }
    }

    public void deactivateSupplier(Supplier supplier) {
        requirePermission(Role.MANAGE_SUPPLIERS);
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
    public boolean registerPurchase(Purchase purchase) {
        requirePermission(Role.REGISTER_PURCHASE);
        if (purchase == null || purchase.getNumber() != 0 || !purchase.validate()) {
            return false;
        }
        purchase.calculateTotal();
        purchase.setNumber(nextPurchaseNumber);
        purchase.setDateTime(LocalDateTime.now());
        inventory.registerPurchaseMovements(purchase);
        purchases.add(purchase);
        nextPurchaseNumber++;
        return true;
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

    public Supplier findByRuc(String ruc) {
        if (ruc == null) {
            return null;
        }
        for (Supplier supplier : suppliers) {
            if (supplier.getRuc().equals(ruc.trim())) {
                return supplier;
            }
        }
        return null;
    }

    private void requirePermission(String permission) {
        if (!accessManager.checkPermission(permission)) {
            throw new SecurityException("No tiene permiso para esta operación (" + permission + ")");
        }
    }
}
