package ec.edu.espe.unistock.service;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import ec.edu.espe.unistock.model.Product;
import ec.edu.espe.unistock.model.Role;
import ec.edu.espe.unistock.model.Sale;
import ec.edu.espe.unistock.model.SaleDetail;
import ec.edu.espe.unistock.model.User;
import ec.edu.espe.unistock.structure.SimpleList;

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
        sale.addItem(new SaleDetail(product, quantity));
    }

    public void confirmSale(Sale sale) {
        if (sale.isEmpty()) {
            throw new IllegalStateException("La venta no tiene productos.");
        }
        SimpleList<SaleDetail> items = sale.getItems();
        items.resetIteration();
        while (items.hasNext()) {
            SaleDetail item = items.next();
            item.getProduct().decreaseStock(item.getQuantity());
        }
        sales.add(sale);
    }

    public SimpleList<Sale> getSales() {
        return sales;
    }
}
