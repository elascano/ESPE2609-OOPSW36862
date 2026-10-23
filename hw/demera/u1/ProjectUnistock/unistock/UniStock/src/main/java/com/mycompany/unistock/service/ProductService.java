package unistock.service;

import unistock.model.Product;
import unistock.model.Stationery;
import unistock.model.Uniform;
import unistock.structure.SimpleList;

import java.math.BigDecimal;

public class ProductService {

    private final SimpleList<Product> products = new SimpleList<>();
    private int nextId = 1;

    public Product registerUniform(String name, BigDecimal price, int stock, String size, String school) {
        Product product = new Uniform(nextId++, name, price, stock, size, school);
        products.add(product);
        return product;
    }

    public Product registerStationery(String name, BigDecimal price, int stock, String brand, String type) {
        Product product = new Stationery(nextId++, name, price, stock, brand, type);
        products.add(product);
        return product;
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
            Product product = products.next();
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public SimpleList<Product> getProducts() {
        return products;
    }
}
