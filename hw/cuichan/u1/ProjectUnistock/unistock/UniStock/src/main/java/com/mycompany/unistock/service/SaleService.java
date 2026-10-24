package unistock.service;

import unistock.model.Product;
import unistock.model.Role;
import unistock.model.Sale;
import unistock.model.SaleItem;
import unistock.model.User;
import unistock.structure.SimpleList;

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
