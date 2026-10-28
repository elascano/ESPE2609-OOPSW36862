package ec.edu.espe.provstock.view;

import ec.edu.espe.provstock.controller.ProductCatalog;
import ec.edu.espe.provstock.model.Product;
import ec.edu.espe.provstock.persistence.FileManager;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Behavior shared by all the menus: show a submenu, run an action safely,
 * and find or list products.
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class ConsoleHelper {

    private final InputReader input;
    private final ProductCatalog productCatalog;
    private final FileManager fileManager;

    public ConsoleHelper(AppContext context) {
        this.input = context.getInput();
        this.productCatalog = context.getProductCatalog();
        this.fileManager = context.getFileManager();
    }

    /**
     * Prints a submenu and returns the option chosen by the user.
     */
    public int showMenu(String title, String backLabel, String... options) {
        System.out.println("\n--- " + title + " ---");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.println("0. " + backLabel);
        return input.readInt("Elija una opción: ");
    }

    public void showInvalidOption() {
        System.out.println("Opción no válida.");
    }

    /**
     * If an action fails (invalid data, no permission, etc.) the message is shown
     * and the program goes back to the menu.
     */
    public void runSafely(Runnable action) {
        try {
            action.run();
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            //persistence: after every operation the JSON files are updated with the current data
            saveData();
        }
    }

    private void saveData() {
        try {
            fileManager.saveAll();
        } catch (RuntimeException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    /**
     * @throws java.util.NoSuchElementException if the product does not exist
     */
    public Product findProduct() {
        return productCatalog.findByCode(input.readText("Código del producto: "));
    }

    public void printProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No se encontraron productos.");
            return;
        }
        System.out.printf("%-10s %-28s %-16s %10s %-6s %9s  %-10s%n", "Código", "Nombre", "Categoría", "Stock",
                "Unidad", "Precio", "Ubicación");
        for (Product product : products) {
            System.out.println(formatProduct(product));
        }
    }

    public String formatProduct(Product product) {
        //UC-06: the list shows the location of the product in the warehouse
        return String.format("%-10s %-28s %-16s %10.2f %-6s %9.2f  %-10s%s", product.getCode(),
                TextFormat.cut(product.getName(), 28), TextFormat.cut(product.getCategory(), 16),
                product.getCurrentStock(), product.getUnitOfMeasure(), product.getSalePrice(),
                TextFormat.location(product), product.isLowStock() ? "  (stock bajo)" : "");
    }

    /**
     * UC-06 included by UC-08 and UC-15: if the search finds one product it is used,
     * otherwise the user types the code.
     */
    public Product searchAndChooseProduct(String message) {
        List<Product> found = productCatalog.search(input.readOptionalText(message), null);
        if (found.isEmpty()) {
            throw new NoSuchElementException("No se encontraron productos.");
        }
        printProducts(found);
        return found.size() == 1 ? found.get(0) : findProduct();
    }
}
