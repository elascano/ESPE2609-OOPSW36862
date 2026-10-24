package com.mycompany.unistock.service;

import com.mycompany.unistock.model.Product;
import com.mycompany.unistock.model.Role;
import com.mycompany.unistock.model.Sale;
import com.mycompany.unistock.model.SaleItem;
import com.mycompany.unistock.model.User;
import com.mycompany.unistock.structure.SimpleList;

public class SaleService {

    private final ProductService productService;
    private final SimpleList<Sale> sales = new SimpleList<>();
    private int nextId = 1;

    public SaleService(ProductService productService) {
        this.productService = productService;
    }

    public Sale startSale(User seller) {
        Role role = seller.getRole();
        if (!role.canRegisterSales()) {
            throw new SecurityException("Su rol no permite registrar ventas.");
        }
        return new Sale(nextId++, seller);
    }

    public void addItem(Sale sale, int productId, int quantity) {
        Product product = productService.findById(productId);
        if (product == null) {
            throw new IllegalArgumentException("El producto no existe.");
        }
        if (!product.hasStock(quantity)) {
            throw new IllegalStateException("Stock insuficiente. Disponible: " + product.getStock());
        }
        sale.addItem(new SaleItem(product, quantity));
    }

    public void confirmSale(Sale sale) {
        if (sale.isEmpty()) {
            throw new IllegalStateException("La venta no tiene productos.");
        }
        SimpleList<SaleItem> items = sale.getItems();
        items.resetIteration();
        while (items.hasNext()) {
            SaleItem item = items.next();
            item.getProduct().decreaseStock(item.getQuantity());
        }
        sales.add(sale);
    }

    public SimpleList<Sale> getSales() {
        return sales;
    }
}
