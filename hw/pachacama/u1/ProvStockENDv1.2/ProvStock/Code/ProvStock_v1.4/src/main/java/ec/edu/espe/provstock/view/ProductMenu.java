package ec.edu.espe.provstock.view;

import ec.edu.espe.provstock.controller.AccessManager;
import ec.edu.espe.provstock.controller.Inventory;
import ec.edu.espe.provstock.controller.ProductCatalog;
import ec.edu.espe.provstock.model.Product;
import ec.edu.espe.provstock.model.ProductInfo;

/**
 * UC-06 Search products and UC-07 Manage products. (RF03)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class ProductMenu {

    private static final String INITIAL_INVENTORY = "Inventario inicial";

    private final InputReader input;
    private final ProductCatalog productCatalog;
    private final Inventory inventory;
    private final AccessManager accessManager;
    private final ConsoleHelper console;

    public ProductMenu(AppContext context, ConsoleHelper console) {
        this.input = context.getInput();
        this.productCatalog = context.getProductCatalog();
        this.inventory = context.getInventory();
        this.accessManager = context.getAccessManager();
        this.console = console;
    }

    public void searchProducts() {
        String text = input.readOptionalText("Texto a buscar (código, nombre, marca; Enter para todos): ");
        String category = input.readOptionalOption("Categoría:", Product.CATEGORIES);
        console.printProducts(productCatalog.search(text, category));
    }

    public void manageProducts() {
        while (true) {
            int option = console.showMenu("Gestionar productos", "Volver", "Registrar producto",
                    "Actualizar producto", "Desactivar producto", "Listar productos activos");
            switch (option) {
                case 1 -> console.runSafely(this::registerProduct);
                case 2 -> console.runSafely(this::updateProduct);
                case 3 -> console.runSafely(this::deactivateProduct);
                case 4 -> console.printProducts(productCatalog.getActiveProducts());
                case 0 -> {
                    return;
                }
                default -> console.showInvalidOption();
            }
        }
    }

    private void registerProduct() {
        String code = input.readText("Código: ").toUpperCase();
        //BR-08: the code is checked before asking for the rest of the data
        if (productCatalog.isRegistered(code)) {
            throw new IllegalArgumentException("Ya existe un producto con el código " + code + ".");
        }
        Product product = new Product(0, code, readNewProductInfo());
        double initialStock = input.readNonNegativeDouble("Stock inicial: ");
        productCatalog.registerProduct(product);
        //AS-05, FR-05.4: the initial stock is loaded with an adjustment
        if (initialStock > 0) {
            inventory.adjustStock(product, initialStock, INITIAL_INVENTORY, accessManager.getCurrentUser());
        }
        System.out.println("Producto registrado con id " + product.getId() + ".");
    }

    private ProductInfo readNewProductInfo() {
        String name = input.readText("Nombre: ");
        String description = input.readOptionalText("Descripción (opcional): ");
        String category = input.readOption("Categoría:", Product.CATEGORIES);
        String brand = input.readOptionalText("Marca (opcional): ");
        String unit = input.readOption("Unidad de medida:", Product.UNITS_OF_MEASURE);
        String location = input.readOptionalText("Ubicación en bodega (opcional): ");
        double purchaseCost = input.readNonNegativeDouble("Costo de compra: ");
        double salePrice = input.readPositiveDouble("Precio de venta: ");
        double minimumStock = input.readNonNegativeDouble("Stock mínimo: ");
        return new ProductInfo(name, category, unit).withDescription(description).withBrand(brand)
                .withLocation(location).withPrices(purchaseCost, salePrice).withMinimumStock(minimumStock);
    }

    private void updateProduct() {
        Product product = console.findProduct();
        System.out.println(console.formatProduct(product));
        System.out.println("Presione Enter para mantener el valor actual.");
        String name = input.readTextOrKeep("Nombre", product.getName());
        String description = input.readTextOrKeep("Descripción", product.getDescription());
        String category = readCategoryOrKeep(product.getCategory());
        String brand = input.readTextOrKeep("Marca", product.getBrand());
        String unit = readUnitOrKeep(product.getUnitOfMeasure());
        String location = input.readTextOrKeep("Ubicación", product.getLocation());
        double purchaseCost = input.readDouble("Costo de compra", product.getPurchaseCost());
        double salePrice = input.readDouble("Precio de venta", product.getSalePrice());
        double minimumStock = input.readDouble("Stock mínimo", product.getMinimumStock());

        product.update(new ProductInfo(name, category, unit).withDescription(description).withBrand(brand)
                .withLocation(location).withPrices(purchaseCost, salePrice).withMinimumStock(minimumStock));
        productCatalog.updateProduct(product);
        System.out.println("Producto actualizado. (El stock solo cambia con ventas, compras o ajustes.)");
    }

    private String readCategoryOrKeep(String category) {
        if (input.readYesNo("¿Cambiar la categoría (actual: " + category + ")?")) {
            return input.readOption("Categoría:", Product.CATEGORIES);
        }
        return category;
    }

    private String readUnitOrKeep(String unit) {
        if (input.readYesNo("¿Cambiar la unidad (actual: " + unit + ")?")) {
            return input.readOption("Unidad de medida:", Product.UNITS_OF_MEASURE);
        }
        return unit;
    }

    private void deactivateProduct() {
        Product product = console.findProduct();
        if (input.readYesNo("¿Desactivar " + product.getName() + "?")) {
            productCatalog.deactivateProduct(product);
            System.out.println("Producto desactivado.");
        }
    }
}
