package ec.edu.espe.provstock.controller;

import ec.edu.espe.provstock.model.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
     * @return false if the data is not valid or the code already exists (BR-08)
     */
    public boolean registerProduct(Product product) {
        if (product == null || !product.validate()) {
            return false;
        }
        if (findByCode(product.getCode()) != null) {
            return false;
        }
        product.setId(products.size() + 1);
        products.add(product);
        return true;
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

    public Product findByCode(String code) {
        if (code == null) {
            return null;
        }
        for (Product product : products) {
            if (product.getCode().equalsIgnoreCase(code.trim())) {
                return product;
            }
        }
        return null;
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
