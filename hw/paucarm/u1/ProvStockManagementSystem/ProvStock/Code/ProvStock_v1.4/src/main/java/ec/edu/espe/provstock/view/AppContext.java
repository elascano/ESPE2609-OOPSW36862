package ec.edu.espe.provstock.view;

import ec.edu.espe.provstock.controller.AccessManager;
import ec.edu.espe.provstock.controller.CustomerManager;
import ec.edu.espe.provstock.controller.Inventory;
import ec.edu.espe.provstock.controller.ProductCatalog;
import ec.edu.espe.provstock.controller.PurchasingManager;
import ec.edu.espe.provstock.controller.ReportGenerator;
import ec.edu.espe.provstock.controller.SalesManager;
import ec.edu.espe.provstock.controller.StockMonitor;
import ec.edu.espe.provstock.persistence.FileManager;

/**
 * Creates the control classes once and gives them to the menus, so each menu
 * receives one object instead of a long list of parameters.
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class AppContext {

    private final InputReader input = new InputReader();
    private final ProductCatalog productCatalog = new ProductCatalog();
    private final StockMonitor stockMonitor = new StockMonitor(productCatalog);
    private final Inventory inventory = new Inventory(stockMonitor, productCatalog);
    private final AccessManager accessManager = new AccessManager();
    private final CustomerManager customerManager = new CustomerManager();
    private final SalesManager salesManager = new SalesManager(inventory, customerManager, accessManager);
    private final PurchasingManager purchasingManager = new PurchasingManager(inventory, accessManager);
    private final FileManager fileManager = new FileManager(accessManager, customerManager, productCatalog,
            inventory, salesManager, purchasingManager);
    private final ReportGenerator reportGenerator = new ReportGenerator(salesManager, purchasingManager,
            productCatalog, fileManager);

    public InputReader getInput() {
        return input;
    }

    public ProductCatalog getProductCatalog() {
        return productCatalog;
    }

    public StockMonitor getStockMonitor() {
        return stockMonitor;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public AccessManager getAccessManager() {
        return accessManager;
    }

    public CustomerManager getCustomerManager() {
        return customerManager;
    }

    public SalesManager getSalesManager() {
        return salesManager;
    }

    public PurchasingManager getPurchasingManager() {
        return purchasingManager;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public ReportGenerator getReportGenerator() {
        return reportGenerator;
    }
}
