package ec.edu.espe.unistock.view;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import ec.edu.espe.unistock.model.Product;
import ec.edu.espe.unistock.model.ProductCategory;
import ec.edu.espe.unistock.service.ProductService;
import ec.edu.espe.unistock.structure.SimpleList;

import java.math.BigDecimal;

public class ProductMenuView {

    private final ProductService productService;
    private final ConsoleAdapter console;

    public ProductMenuView(ProductService productService, ConsoleAdapter console) {
        this.productService = productService;
        this.console = console;
    }

    public void registerProduct() {
        System.out.println("1. Uniforme  2. Papelería / Útil escolar");
        int type = console.readInt("Categoría: ");
        String name = console.readText("Nombre: ");
        BigDecimal price = console.readMoney("Precio: ");
        int stock = console.readInt("Stock inicial: ");

        Product product = null;
        if (type == 1) {
            String size = console.readText("Talla: ");
            String school = console.readText("Colegio: ");
            product = productService.registerUniform(name, price, stock, size, school);
        } else if (type == 2) {
            String brand = console.readText("Marca del producto: ");
            String stationeryType = console.readText("Tipo de artículo / material: ");
            product = productService.registerStationery(name, price, stock, brand, stationeryType);
        } else {
            System.out.println("Categoría inválida.");
            return;
        }

        if (product != null) {
            System.out.println("Producto registrado: " + product);
        } else {
            System.out.println("No se pudo registrar el producto. Verifique los catálogos.");
        }
    }

    public void removeProduct() {
        int id = console.readInt("ID del producto a eliminar: ");
        boolean removed = productService.removeProduct(id);
        System.out.println(removed ? "Producto eliminado." : "No se encontró el producto.");
    }

    public void listProducts() {
        System.out.println("\n--- CONSULTA Y BÚSQUEDA DE PRODUCTOS ---");
        System.out.println("1. Listar todos los productos");
        System.out.println("2. Buscar producto por nombre");
        System.out.println("3. Filtrar por categoría");
        int option = console.readInt("Opción: ");

        switch (option) {
            case 1 ->
                printProductList(productService.getProducts());
            case 2 ->
                searchProductsByName();
            case 3 ->
                filterProductsByCategory();
            default ->
                System.out.println("Opción inválida.");
        }
    }

    private void searchProductsByName() {
        String searchKeyword = console.readText("Ingrese el nombre o palabra clave a buscar: ");
        SimpleList<Product> searchResults = productService.searchByName(searchKeyword);
        printProductList(searchResults);
    }

    private void filterProductsByCategory() {
        System.out.println("1. Uniforme   2. Papelería");
        int categoryOption = console.readInt("Categoría: ");
        ProductCategory selectedCategory = (categoryOption == 1)
                ? ProductCategory.UNIFORM
                : ProductCategory.STATIONERY;
        printProductList(productService.findByCategory(selectedCategory));
    }

    public void updateStockManual() {
        System.out.println("\n--- ACTUALIZACIÓN DE INVENTARIO ---");
        int targetProductId = console.readInt("ID del producto a reabastecer: ");
        int quantityToChange = console.readInt("Cantidad a ingresar/ajustar: ");

        boolean isStockUpdated = productService.updateStock(targetProductId, quantityToChange);
        if (isStockUpdated) {
            System.out.println("Stock actualizado correctamente.");
        } else {
            System.out.println("No se encontró el producto especificado.");
        }
    }

    public void printProductList(SimpleList<Product> productList) {
        if (productList.isEmpty()) {
            System.out.println("No se encontraron productos.");
            return;
        }
        productList.resetIteration();
        while (productList.hasNext()) {
            System.out.println(productList.next());
        }
    }
}
