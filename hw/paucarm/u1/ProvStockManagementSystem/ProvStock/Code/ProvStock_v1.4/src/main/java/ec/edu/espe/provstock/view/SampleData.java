package ec.edu.espe.provstock.view;

import ec.edu.espe.provstock.controller.AccessManager;
import ec.edu.espe.provstock.controller.CustomerManager;
import ec.edu.espe.provstock.controller.Inventory;
import ec.edu.espe.provstock.controller.ProductCatalog;
import ec.edu.espe.provstock.controller.PurchasingManager;
import ec.edu.espe.provstock.model.Customer;
import ec.edu.espe.provstock.model.Product;
import ec.edu.espe.provstock.model.ProductInfo;
import ec.edu.espe.provstock.model.Role;
import ec.edu.espe.provstock.model.Supplier;
import ec.edu.espe.provstock.model.User;

/**
 * Sample data so the system can be tested from the first run. It is loaded using
 * the same methods of the control classes, so all the rules are checked.
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class SampleData {

    private static final String INITIAL_INVENTORY = "Inventario inicial";

    private final AccessManager accessManager;
    private final ProductCatalog productCatalog;
    private final Inventory inventory;
    private final CustomerManager customerManager;
    private final PurchasingManager purchasingManager;

    public SampleData(AppContext context) {
        this.accessManager = context.getAccessManager();
        this.productCatalog = context.getProductCatalog();
        this.inventory = context.getInventory();
        this.customerManager = context.getCustomerManager();
        this.purchasingManager = context.getPurchasingManager();
    }

    public void load() {
        accessManager.login(AccessManager.DEFAULT_OWNER_USERNAME, AccessManager.DEFAULT_OWNER_PASSWORD);
        User owner = accessManager.getCurrentUser();
        createUsers();
        createProducts(owner);
        createCustomers();
        createSuppliers();
        accessManager.logout();
    }

    private void createUsers() {
        accessManager.createUser("vendedor", "María López", accessManager.findRole(Role.SALESPERSON),
                "Vendedor2026");
        accessManager.createUser("bodeguero", "Pedro Ramírez", accessManager.findRole(Role.WAREHOUSE_CLERK),
                "Bodega2026");
    }

    private void createProducts(User owner) {
        addProduct("CAB-001", new ProductInfo("Cable THHN 12 AWG", "CABLES", "METER")
                .withDescription("Cable de cobre sólido").withBrand("Electrocables").withLocation("Pasillo A1")
                .withPrices(0.45, 0.70).withMinimumStock(100), 500, owner);
        addProduct("CAB-002", new ProductInfo("Cable gemelo 2x14 AWG", "CABLES", "METER")
                .withDescription("Cable flexible doble").withBrand("Electrocables").withLocation("Pasillo A2")
                .withPrices(0.35, 0.55).withMinimumStock(100), 80, owner);
        addProduct("ILU-001", new ProductInfo("Foco LED 9W luz blanca", "LIGHTING", "UNIT")
                .withDescription("Rosca E27").withBrand("Sylvania").withLocation("Pasillo B1")
                .withPrices(1.20, 2.50).withMinimumStock(20), 60, owner);
        addProduct("INT-001", new ProductInfo("Interruptor simple", "SWITCHES_AND_OUTLETS", "UNIT")
                .withDescription("Blanco, empotrable").withBrand("Veto").withLocation("Pasillo C1")
                .withPrices(1.10, 2.00).withMinimumStock(15), 40, owner);
        addProduct("PRO-001", new ProductInfo("Breaker 1 polo 20A", "PROTECTION_DEVICES", "UNIT")
                .withDescription("Riel DIN").withBrand("Schneider").withLocation("Pasillo D1")
                .withPrices(4.50, 7.80).withMinimumStock(10), 8, owner);
        addProduct("HER-001", new ProductInfo("Alicate universal 8 pulgadas", "TOOLS", "UNIT")
                .withDescription("Mango aislado").withBrand("Stanley").withLocation("Pasillo E1")
                .withPrices(6.00, 10.50).withMinimumStock(5), 12, owner);
    }

    private void addProduct(String code, ProductInfo info, double initialStock, User owner) {
        Product product = new Product(0, code, info);
        productCatalog.registerProduct(product);
        inventory.adjustStock(product, initialStock, INITIAL_INVENTORY, owner);
    }

    private void createCustomers() {
        customerManager.registerCustomer(new Customer(0, Customer.CEDULA, "1712345675", "Juan Pérez",
                "0991234567", "juan.perez@mail.com", "Quito, Av. Amazonas"));
        customerManager.registerCustomer(new Customer(0, Customer.RUC, "1701234567001", "Instalaciones Andes",
                "022345678", "ventas@andes.com", "Sangolquí"));
    }

    private void createSuppliers() {
        purchasingManager.registerSupplier(new Supplier(0, "1790012345001", "Electrocables S.A.",
                "Ana Torres", "022987654", "ventas@electrocables.com", "Quito"));
        purchasingManager.registerSupplier(new Supplier(0, "0992345678001", "Distribuidora Eléctrica del Pacífico",
                "Luis Mora", "042123456", "pedidos@depacifico.com", "Guayaquil"));
    }
}
