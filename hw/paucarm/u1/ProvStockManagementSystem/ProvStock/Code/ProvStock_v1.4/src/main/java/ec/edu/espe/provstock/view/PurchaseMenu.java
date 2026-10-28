package ec.edu.espe.provstock.view;

import ec.edu.espe.provstock.controller.AccessManager;
import ec.edu.espe.provstock.controller.PurchasingManager;
import ec.edu.espe.provstock.model.Product;
import ec.edu.espe.provstock.model.Purchase;
import ec.edu.espe.provstock.model.PurchaseDetail;
import ec.edu.espe.provstock.model.Supplier;
import java.util.List;

/**
 * UC-14 Manage suppliers and UC-15 Register purchase. (RF06)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class PurchaseMenu {

    private final InputReader input;
    private final PurchasingManager purchasingManager;
    private final AccessManager accessManager;
    private final ConsoleHelper console;

    public PurchaseMenu(AppContext context, ConsoleHelper console) {
        this.input = context.getInput();
        this.purchasingManager = context.getPurchasingManager();
        this.accessManager = context.getAccessManager();
        this.console = console;
    }

    // =====================================================================
    // UC-14 Manage suppliers
    // =====================================================================
    public void manageSuppliers() {
        while (true) {
            int option = console.showMenu("Gestionar proveedores", "Volver", "Registrar proveedor",
                    "Actualizar proveedor", "Desactivar proveedor", "Buscar proveedores");
            switch (option) {
                case 1 -> console.runSafely(this::registerSupplier);
                case 2 -> console.runSafely(this::updateSupplier);
                case 3 -> console.runSafely(this::deactivateSupplier);
                case 4 -> printSuppliers(purchasingManager.searchSuppliers(
                        input.readOptionalText("Nombre o RUC (Enter para todos): ")));
                case 0 -> {
                    return;
                }
                default -> console.showInvalidOption();
            }
        }
    }

    private void registerSupplier() {
        String ruc = input.readText("RUC: ");
        String name = input.readText("Nombre o razón social: ");
        String contact = input.readOptionalText("Persona de contacto (opcional): ");
        String phone = input.readOptionalText("Teléfono (opcional): ");
        String email = input.readOptionalText("Correo (opcional): ");
        String address = input.readOptionalText("Dirección (opcional): ");

        Supplier supplier = new Supplier(0, ruc, name, contact, phone, email, address);
        if (!supplier.validateRuc()) {
            throw new IllegalArgumentException("El RUC no es válido.");
        }
        if (!supplier.validate()) {
            throw new IllegalArgumentException("Datos no válidos: revise el teléfono y el correo.");
        }
        purchasingManager.registerSupplier(supplier);
        System.out.println("Proveedor registrado con id " + supplier.getId() + ".");
    }

    private void updateSupplier() {
        Supplier supplier = findSupplier();
        String name = input.readTextOrKeep("Nombre", supplier.getName());
        supplier.setContactPerson(input.readTextOrKeep("Persona de contacto", supplier.getContactPerson()));
        String phone = input.readTextOrKeep("Teléfono", supplier.getPhone());
        String email = input.readTextOrKeep("Correo", supplier.getEmail());
        String address = input.readTextOrKeep("Dirección", supplier.getAddress());
        supplier.update(name, phone, email, address);
        purchasingManager.updateSupplier(supplier);
        System.out.println("Proveedor actualizado.");
    }

    private void deactivateSupplier() {
        Supplier supplier = findSupplier();
        if (input.readYesNo("¿Desactivar a " + supplier.getName() + "?")) {
            purchasingManager.deactivateSupplier(supplier);
            System.out.println("Proveedor desactivado.");
        }
    }

    private Supplier findSupplier() {
        return purchasingManager.findByRuc(input.readText("RUC del proveedor: "));
    }

    private void printSuppliers(List<Supplier> suppliers) {
        if (suppliers.isEmpty()) {
            System.out.println("No se encontraron proveedores.");
            return;
        }
        for (Supplier supplier : suppliers) {
            System.out.printf("  [%d] RUC %-14s %-30s %s%n", supplier.getId(), supplier.getRuc(),
                    supplier.getName(), supplier.getContactPerson() == null ? "" : supplier.getContactPerson());
        }
    }

    // =====================================================================
    // UC-15 Register purchase (includes UC-06 and UC-11)
    // =====================================================================
    public void registerPurchase() {
        Supplier supplier = chooseActiveSupplier();
        //UC-15: the supplier invoice number is optional
        String invoice = input.readOptionalText("Número de factura del proveedor (opcional): ");
        Purchase purchase = new Purchase(supplier, accessManager.getCurrentUser(), invoice);
        boolean finished = false;
        while (!finished) {
            finished = runPurchaseOption(purchase, showPurchaseMenu(purchase));
        }
    }

    private Supplier chooseActiveSupplier() {
        printSuppliers(purchasingManager.searchSuppliers(""));
        Supplier supplier = findSupplier();
        if (!supplier.isActive()) {
            throw new IllegalArgumentException("El proveedor está inactivo.");
        }
        return supplier;
    }

    private int showPurchaseMenu(Purchase purchase) {
        String title = "Nueva compra a " + purchase.getSupplier().getName() + " (" + purchase.getDetails().size()
                + " producto(s), total $" + String.format("%.2f", purchase.getTotal()) + ")";
        return console.showMenu(title, "Cancelar compra", "Agregar producto", "Quitar producto", "Ver detalle",
                "Confirmar compra");
    }

    /**
     * @return true when the purchase is confirmed or cancelled
     */
    private boolean runPurchaseOption(Purchase purchase, int option) {
        switch (option) {
            case 1 -> console.runSafely(() -> addProductToPurchase(purchase));
            case 2 -> console.runSafely(() -> removeProductFromPurchase(purchase));
            case 3 -> printPurchase(purchase);
            case 4 -> {
                return confirmPurchase(purchase);
            }
            case 0 -> {
                System.out.println("Compra cancelada.");
                return true;
            }
            default -> console.showInvalidOption();
        }
        return false;
    }

    private void addProductToPurchase(Purchase purchase) {
        //UC-15 includes UC-06: search the product
        Product product = console.searchAndChooseProduct("Buscar producto (Enter para ver todos): ");
        double quantity = input.readPositiveDouble("Cantidad (" + product.getUnitOfMeasure() + "): ");
        double unitCost = input.readDouble("Costo unitario", product.getPurchaseCost());
        purchase.addProduct(product, quantity, unitCost);
        System.out.println("Agregado: " + product.getName() + " x " + quantity);
    }

    private void removeProductFromPurchase(Purchase purchase) {
        purchase.removeProduct(console.findProduct());
        System.out.println("Producto quitado.");
    }

    private boolean confirmPurchase(Purchase purchase) {
        if (purchase.getDetails().isEmpty()) {
            System.out.println("Agregue al menos un producto.");
            return false;
        }
        printPurchase(purchase);
        if (!input.readYesNo("¿Confirmar la compra?")) {
            return false;
        }
        try {
            purchasingManager.registerPurchase(purchase);
        } catch (IllegalArgumentException e) {
            //the purchase stays open, so the user can correct it
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        System.out.println("Compra Nº " + purchase.getNumber() + " registrada. El stock fue actualizado.");
        return true;
    }

    private void printPurchase(Purchase purchase) {
        System.out.println(TextFormat.LINE);
        String invoice = purchase.getSupplierInvoiceNumber();
        System.out.println("Proveedor: " + purchase.getSupplier().getName() + "   Factura: "
                + (invoice.isEmpty() ? "(sin factura)" : invoice));
        System.out.printf("%-10s %-26s %10s %10s %10s%n", "Código", "Producto", "Cantidad", "C. unit.", "Subtotal");
        for (PurchaseDetail detail : purchase.getDetails()) {
            System.out.printf("%-10s %-26s %10.2f %10.2f %10.2f%n", detail.getProduct().getCode(),
                    TextFormat.cut(detail.getProduct().getName(), 26), detail.getQuantity(), detail.getUnitCost(),
                    detail.calculateSubtotal());
        }
        System.out.printf("%60s %10.2f%n", "TOTAL:", purchase.calculateTotal());
        System.out.println(TextFormat.LINE);
    }
}
