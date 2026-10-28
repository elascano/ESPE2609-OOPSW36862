package ec.edu.espe.provstock.controller;

import ec.edu.espe.provstock.model.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Registers, updates, searches and deactivates products (UC-06, UC-07). (RF03)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class ProductCatalog {

    private List<Product> products;

    public ProductCatalog() {
        products = new ArrayList<>();
    }

    /**
     * @throws IllegalArgumentException if the data is not valid or the code already exists (BR-08)
     */
    public void registerProduct(Product product) {
        if (product == null || !product.validate()) {
            throw new IllegalArgumentException("Los datos del producto no son válidos");
        }
        if (isRegistered(product.getCode())) {
            throw new IllegalArgumentException("Ya existe un producto con el código " + product.getCode() + ".");
        }
        product.setId(products.size() + 1);
        products.add(product);
    }

    public void updateProduct(Product product) {
        if (!products.contains(product)) {
            throw new IllegalArgumentException("El producto no existe");
        }
        if (!product.validate()) {
            throw new IllegalArgumentException("Los datos del producto no son válidos");
        }
    }

    public void deactivateProduct(Product product) {
        if (!products.contains(product)) {
            throw new IllegalArgumentException("El producto no existe");
        }
        product.deactivate();
    }

    /**
     * @throws NoSuchElementException if no product has that code
     */
    public Product findByCode(String code) {
        for (Product product : products) {
            if (hasCode(product, code)) {
                return product;
            }
        }
        throw new NoSuchElementException("No existe el producto " + code + ".");
    }

    public boolean isRegistered(String code) {
        for (Product product : products) {
            if (hasCode(product, code)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasCode(Product product, String code) {
        return code != null && product.getCode().equalsIgnoreCase(code.trim());
    }

    /**
     * Searches active products by code, name, description or brand.
     * An empty text or category means "all".
     */
    public List<Product> search(String text, String category) {
        String search = text == null ? "" : text.trim().toLowerCase();
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (!product.isActive()) {
                continue;
            }
            if (category != null && !category.isBlank() && !product.getCategory().equals(category)) {
                continue;
            }
            String data = (product.getCode() + " " + product.getName() + " " + product.getDescription()
                    + " " + product.getBrand()).toLowerCase();
            if (data.contains(search)) {
                result.add(product);
            }
        }
        return result;
    }

    /**
     * Replaces the products with the ones read from the data files. Their stock starts
     * in 0 and Inventory rebuilds it from the saved movements (N4).
     */
    public void restoreProducts(List<Product> savedProducts) {
        products = new ArrayList<>(savedProducts);
    }

    /**
     * @return all the products, active and inactive (needed to save them in a file)
     */
    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    public List<Product> getActiveProducts() {
        return search("", null);
    }
}
