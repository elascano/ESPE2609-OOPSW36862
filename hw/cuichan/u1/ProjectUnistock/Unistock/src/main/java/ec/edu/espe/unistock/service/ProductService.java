package ec.edu.espe.unistock.service;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import ec.edu.espe.unistock.model.Product;
import ec.edu.espe.unistock.model.ProductCategory;
import ec.edu.espe.unistock.model.Stationery;
import ec.edu.espe.unistock.model.Uniform;
import ec.edu.espe.unistock.structure.FileHandler;
import ec.edu.espe.unistock.structure.SimpleList;

import java.math.BigDecimal;

public class ProductService {

    private final SimpleList<Product> products = new SimpleList<>();
    private int nextId = 1;

    private final UniformService uniformService;
    private final StationeryService stationeryService;

    public ProductService(UniformService uniformService, StationeryService stationeryService) {
        this.uniformService = uniformService;
        this.stationeryService = stationeryService;
    }

    public Product registerUniform(String name, BigDecimal price, int stock, String size, String school) {
        if (!uniformService.existsSize(size)) {
            System.out.println("Error: La talla '" + size + "' no existe en el catálogo de uniformes.");
            return null;
        }
        if (!uniformService.existsSchool(school)) {
            System.out.println("Error: El colegio '" + school + "' no existe en el catálogo de uniformes.");
            return null;
        }

        Product product = new Uniform(nextId++, name, price, stock, size, school);
        products.add(product);
        return product;
    }

    public Product registerStationery(String name, BigDecimal price, int stock, String brand, String type) {
        if (!stationeryService.existsBrand(brand)) {
            System.out.println("Error: La marca '" + brand + "' no existe en el catálogo de papelería.");
            return null;
        }
        if (!stationeryService.existsType(type)) {
            System.out.println("Error: El tipo '" + type + "' no existe en el catálogo de papelería.");
            return null;
        }

        Product product = new Stationery(nextId++, name, price, stock, brand, type);
        products.add(product);
        return product;
    }

    public SimpleList<Product> findByCategory(ProductCategory category) {
        SimpleList<Product> filteredProducts = new SimpleList<>();
        products.resetIteration();
        while (products.hasNext()) {
            Product currentProduct = products.next();
            if (currentProduct.getCategory() == category) {
                filteredProducts.add(currentProduct);
            }
        }
        return filteredProducts;
    }

    public boolean updateStock(int productId, int quantityToChange) {
        Product product = findById(productId);
        if (product == null) {
            return false;
        }
        if (quantityToChange < 0 && Math.abs(quantityToChange) > product.getStock()) {
            throw new IllegalArgumentException("No se puede reducir más stock del disponible.");
        }
        product.increaseStock(quantityToChange);
        return true;
    }

    public boolean removeProduct(int id) {
        Product product = findById(id);
        if (product == null) {
            return false;
        }
        products.remove(product);
        return true;
    }

    public Product findById(int id) {
        products.resetIteration();
        while (products.hasNext()) {
            Product currentProduct = products.next();
            if (currentProduct.getId() == id) {
                return currentProduct;
            }
        }
        return null;
    }

    public SimpleList<Product> searchByName(String keyword) {
        SimpleList<Product> matchingProducts = new SimpleList<>();
        if (keyword == null || keyword.isBlank()) {
            return matchingProducts;
        }
        String normalizedKeyword = keyword.trim().toLowerCase();

        products.resetIteration();
        while (products.hasNext()) {
            Product currentProduct = products.next();
            if (currentProduct.getName().toLowerCase().contains(normalizedKeyword)) {
                matchingProducts.add(currentProduct);
            }
        }
        return matchingProducts;
    }

    public SimpleList<Product> getProducts() {
        return products;
    }

    /**
     * Exporta el catálogo actual de productos a un archivo en formato CSV.
     */
    public void exportReportCSV(String filePath) {
        String header = "ID,Categoría,Nombre,Precio,Stock,Detalles";
        SimpleList<String> rows = new SimpleList<>();

        products.resetIteration();
        while (products.hasNext()) {
            Product p = products.next();
            String details = p.getDetails().replace(",", " "); // Evita romper las columnas del CSV
            String row = String.format("%d,%s,\"%s\",%.2f,%d,\"%s\"",
                    p.getId(),
                    p.getCategory().getLabel(),
                    p.getName(),
                    p.getPrice(),
                    p.getStock(),
                    details);
            rows.add(row);
        }

        FileHandler.exportToCSV(filePath, header, rows);
    }
}