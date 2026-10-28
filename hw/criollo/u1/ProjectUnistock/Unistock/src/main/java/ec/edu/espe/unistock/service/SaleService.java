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
import ec.edu.espe.unistock.structure.FileHandler;
import ec.edu.espe.unistock.structure.SimpleList;

public class SaleService {

    private static final String FILE_PATH = "sales.json";
    private final ProductService productService;
    private final SimpleList<Sale> sales;
    private int nextId = 1;

    public SaleService(ProductService productService) {
        this.productService = productService;
        SimpleList<Sale> loadedSales = FileHandler.loadListFromJson(FILE_PATH, Sale.class);
        if (loadedSales == null || loadedSales.isEmpty()) {
            this.sales = new SimpleList<>();
        } else {
            this.sales = loadedSales;
            this.sales.resetIteration();
            while (this.sales.hasNext()) {
                Sale s = this.sales.next();
                if (s.getId() >= nextId) {
                    nextId = s.getId() + 1;
                }
            }
        }
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
        FileHandler.saveListToJson(FILE_PATH, sales);
    }

    public SimpleList<Sale> getSales() {
        return sales;
    }

    /**
     * Exporta el historial de ventas registradas a un archivo en formato CSV.
     */
    public void exportReportCSV(String filePath) {
        String header = "ID_Venta,Vendedor,Fecha,Total";
        SimpleList<String> rows = new SimpleList<>();

        sales.resetIteration();
        while (sales.hasNext()) {
            Sale s = sales.next();
            String row = String.format("%d,%s,%s,%.2f",
                    s.getId(),
                    s.getSeller().getUsername(), s.getDate(), s.getTotal()); 
            rows.add(row);
        }

        FileHandler.exportToCSV(filePath, header, rows);
    }
}