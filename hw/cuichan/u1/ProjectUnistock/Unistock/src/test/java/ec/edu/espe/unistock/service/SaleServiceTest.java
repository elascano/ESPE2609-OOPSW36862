package ec.edu.espe.unistock.service;

import ec.edu.espe.unistock.model.Product;
import ec.edu.espe.unistock.model.Role;
import ec.edu.espe.unistock.model.Sale;
import ec.edu.espe.unistock.model.User;
import ec.edu.espe.unistock.structure.FileHandler;
import ec.edu.espe.unistock.structure.SimpleList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SaleServiceTest {

    private static final String FILE_PATH = "sales.json";
    private static final String TEST_DIR = "testSaleServiceOutput";
    private ProductService productService;
    private UniformService uniformService;
    private StationeryService stationeryService;
    private SaleService saleService;
    private User sellerUser;
    private User adminUser;
    private User customerUser;

    @BeforeEach
    void setUp() {
        File jsonFile = new File(FILE_PATH);
        if (jsonFile.exists()) {
            jsonFile.delete();
        }
        File dir = new File(TEST_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        uniformService = new UniformService();
        stationeryService = new StationeryService();
        productService = new ProductService(uniformService, stationeryService);
        saleService = new SaleService(productService);

        sellerUser = new User("vendedor1", "pass123", Role.SALESPERSON);
        adminUser = new User("admin1", "admin123", Role.ADMINISTRATOR);
        customerUser = new User("cliente1", "cliente123", Role.CUSTOMER);
    }

    @AfterEach
    void tearDown() {
        File jsonFile = new File(FILE_PATH);
        if (jsonFile.exists()) {
            jsonFile.delete();
        }
        File dir = new File(TEST_DIR);
        if (dir.exists()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
            dir.delete();
        }
    }

    @Test
    void testConstructorInitializesEmptySalesWhenFileMissing() {
        assertNotNull(saleService.getSales());
        assertTrue(saleService.getSales().isEmpty());
    }

    @Test
    void testConstructorLoadsExistingSalesFromFile() {
        Sale sale = saleService.startSale(sellerUser);
        Product product = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 20, "Norma", "Cuaderno");
        saleService.addItem(sale, product.getId(), 2);
        saleService.confirmSale(sale);

        SaleService newSaleService = new SaleService(productService);
        assertEquals(1, newSaleService.getSales().size());
    }

    @Test
    void testConstructorCalculatesNextIdFromExistingSales() {
        Sale sale = saleService.startSale(sellerUser);
        Product product = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 20, "Norma", "Cuaderno");
        saleService.addItem(sale, product.getId(), 1);
        saleService.confirmSale(sale);

        SaleService newSaleService = new SaleService(productService);
        Sale nextSale = newSaleService.startSale(sellerUser);
        assertEquals(2, nextSale.getId());
    }

    @Test
    void testConstructorAcceptsProductService() {
        assertNotNull(saleService);
    }

    @Test
    void testConstructorWithMultipleSavedSales() {
        Product product = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 20, "Norma", "Cuaderno");

        Sale s1 = saleService.startSale(sellerUser);
        saleService.addItem(s1, product.getId(), 1);
        saleService.confirmSale(s1);

        Sale s2 = saleService.startSale(sellerUser);
        saleService.addItem(s2, product.getId(), 1);
        saleService.confirmSale(s2);

        SaleService loadedService = new SaleService(productService);
        assertEquals(2, loadedService.getSales().size());
    }

    @Test
    void testConstructorNextIdContinuity() {
        Product product = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 20, "Norma", "Cuaderno");
        Sale s1 = saleService.startSale(sellerUser);
        saleService.addItem(s1, product.getId(), 1);
        saleService.confirmSale(s1);

        SaleService service2 = new SaleService(productService);
        Sale s2 = service2.startSale(sellerUser);
        saleService.addItem(s2, product.getId(), 1);
        service2.confirmSale(s2);

        SaleService service3 = new SaleService(productService);
        Sale s3 = service3.startSale(sellerUser);
        assertEquals(3, s3.getId());
    }

    @Test
    void testConstructorHandlesEmptyJsonArray() {
        FileHandler.saveListToJson(FILE_PATH, new SimpleList<Sale>());
        SaleService service = new SaleService(productService);
        assertTrue(service.getSales().isEmpty());
    }

    @Test
    void testConstructorFirstSaleIdIsOne() {
        Sale sale = saleService.startSale(sellerUser);
        assertEquals(1, sale.getId());
    }

    @Test
    void testConstructorNullProductServiceAllowed() {
        assertDoesNotThrow(() -> new SaleService(null));
    }

    @Test
    void testConstructorDoesNotOverwriteExistingFileOnCreation() {
        Product product = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 20, "Norma", "Cuaderno");
        Sale s = saleService.startSale(sellerUser);
        saleService.addItem(s, product.getId(), 1);
        saleService.confirmSale(s);

        new SaleService(productService);
        assertTrue(new File(FILE_PATH).exists());
    }

    @Test
    void testStartSaleSalespersonAllowed() {
        Sale sale = saleService.startSale(sellerUser);
        assertNotNull(sale);
        assertEquals(sellerUser, sale.getSeller());
    }

    @Test
    void testStartSaleAdministratorAllowed() {
        Sale sale = saleService.startSale(adminUser);
        assertNotNull(sale);
        assertEquals(adminUser, sale.getSeller());
    }

    @Test
    void testStartSaleCustomerDeniedThrowsException() {
        SecurityException ex = assertThrows(SecurityException.class, () -> saleService.startSale(customerUser));
        assertTrue(ex.getMessage().contains("no permite registrar ventas"));
    }

    @Test
    void testStartSaleIncrementsNextId() {
        Sale s1 = saleService.startSale(sellerUser);
        Sale s2 = saleService.startSale(sellerUser);
        assertEquals(1, s1.getId());
        assertEquals(2, s2.getId());
    }

    @Test
    void testStartSaleInitializesEmptyItems() {
        Sale sale = saleService.startSale(sellerUser);
        assertTrue(sale.isEmpty());
    }

    @Test
    void testStartSaleInitializesDate() {
        Sale sale = saleService.startSale(sellerUser);
        assertNotNull(sale.getDate());
    }

    @Test
    void testStartSaleMultipleSalespersons() {
        User seller2 = new User("vendedor2", "pass", Role.SALESPERSON);
        Sale s1 = saleService.startSale(sellerUser);
        Sale s2 = saleService.startSale(seller2);
        assertEquals("vendedor1", s1.getSeller().getUsername());
        assertEquals("vendedor2", s2.getSeller().getUsername());
    }

    @Test
    void testStartSaleNullSellerThrowsException() {
        assertThrows(NullPointerException.class, () -> saleService.startSale(null));
    }

    @Test
    void testStartSaleDoesNotAddToSalesListImmediately() {
        saleService.startSale(sellerUser);
        assertTrue(saleService.getSales().isEmpty());
    }

    @Test
    void testStartSaleSequentialIds() {
        Sale s1 = saleService.startSale(sellerUser);
        Sale s2 = saleService.startSale(sellerUser);
        Sale s3 = saleService.startSale(sellerUser);
        assertEquals(1, s1.getId());
        assertEquals(2, s2.getId());
        assertEquals(3, s3.getId());
    }

    @Test
    void testAddItemValidProductAndStock() {
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = saleService.startSale(sellerUser);
        saleService.addItem(sale, p.getId(), 2);
        assertFalse(sale.isEmpty());
        assertEquals(1, sale.getItems().size());
    }

    @Test
    void testAddItemNonExistentProductThrowsException() {
        Sale sale = saleService.startSale(sellerUser);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> saleService.addItem(sale, 999, 1));
        assertTrue(ex.getMessage().contains("El producto no existe"));
    }

    @Test
    void testAddItemInsufficientStockThrowsException() {
        Product p = productService.registerStationery("Lapiz FaberCastell", new BigDecimal("0.50"), 5, "FaberCastell", "Lapiz");
        Sale sale = saleService.startSale(sellerUser);
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> saleService.addItem(sale, p.getId(), 10));
        assertTrue(ex.getMessage().contains("Stock insuficiente"));
    }

    @Test
    void testAddItemExactStockAllowed() {
        Product p = productService.registerStationery("Regla Artesco", new BigDecimal("1.00"), 5, "Artesco", "Regla");
        Sale sale = saleService.startSale(sellerUser);
        assertDoesNotThrow(() -> saleService.addItem(sale, p.getId(), 5));
    }

    @Test
    void testAddItemMultipleDifferentProducts() {
        Product p1 = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Product p2 = productService.registerUniform("Camisa Formal", new BigDecimal("12.00"), 5, "M", "San Gabriel");
        Sale sale = saleService.startSale(sellerUser);
        saleService.addItem(sale, p1.getId(), 2);
        saleService.addItem(sale, p2.getId(), 1);
        assertEquals(2, sale.getItems().size());
    }

    @Test
    void testAddItemUpdatesSaleSubtotal() {
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = saleService.startSale(sellerUser);
        saleService.addItem(sale, p.getId(), 4);
        assertEquals(new BigDecimal("10.00"), sale.subtotal());
    }

    @Test
    void testAddItemZeroQuantityThrowsException() {
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = saleService.startSale(sellerUser);
        assertThrows(IllegalArgumentException.class, () -> saleService.addItem(sale, p.getId(), 0));
    }

    @Test
    void testAddItemNegativeQuantityThrowsException() {
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = saleService.startSale(sellerUser);
        assertThrows(IllegalArgumentException.class, () -> saleService.addItem(sale, p.getId(), -2));
    }

    @Test
    void testAddItemNullSaleThrowsException() {
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertThrows(NullPointerException.class, () -> saleService.addItem(null, p.getId(), 1));
    }

    @Test
    void testAddItemDoesNotDecreaseProductStockImmediately() {
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = saleService.startSale(sellerUser);
        saleService.addItem(sale, p.getId(), 3);
        assertEquals(10, p.getStock());
    }

    @Test
    void testConfirmSaleValidSale() {
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = saleService.startSale(sellerUser);
        saleService.addItem(sale, p.getId(), 3);

        saleService.confirmSale(sale);

        assertEquals(1, saleService.getSales().size());
        assertEquals(7, p.getStock());
    }

    @Test
    void testConfirmSaleEmptySaleThrowsException() {
        Sale sale = saleService.startSale(sellerUser);
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> saleService.confirmSale(sale));
        assertTrue(ex.getMessage().contains("no tiene productos"));
    }

    @Test
    void testConfirmSaleDecreasesMultipleProductsStock() {
        Product p1 = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Product p2 = productService.registerUniform("Camisa Formal", new BigDecimal("12.00"), 5, "M", "San Gabriel");
        Sale sale = saleService.startSale(sellerUser);
        saleService.addItem(sale, p1.getId(), 2);
        saleService.addItem(sale, p2.getId(), 3);

        saleService.confirmSale(sale);

        assertEquals(8, p1.getStock());
        assertEquals(2, p2.getStock());
    }

    @Test
    void testConfirmSalePersistsToJsonFile() {
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = saleService.startSale(sellerUser);
        saleService.addItem(sale, p.getId(), 1);

        saleService.confirmSale(sale);

        File file = new File(FILE_PATH);
        assertTrue(file.exists() && file.length() > 0);
    }

    @Test
    void testConfirmSaleNullSaleThrowsException() {
        assertThrows(NullPointerException.class, () -> saleService.confirmSale(null));
    }

    @Test
    void testConfirmSaleReducesStockToZero() {
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 5, "Norma", "Cuaderno");
        Sale sale = saleService.startSale(sellerUser);
        saleService.addItem(sale, p.getId(), 5);

        saleService.confirmSale(sale);

        assertEquals(0, p.getStock());
    }

    @Test
    void testConfirmSaleMultipleSequentialSales() {
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");

        Sale s1 = saleService.startSale(sellerUser);
        saleService.addItem(s1, p.getId(), 2);
        saleService.confirmSale(s1);

        Sale s2 = saleService.startSale(sellerUser);
        saleService.addItem(s2, p.getId(), 3);
        saleService.confirmSale(s2);

        assertEquals(2, saleService.getSales().size());
        assertEquals(5, p.getStock());
    }

    @Test
    void testConfirmSaleRetainsSaleDetailsInList() {
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = saleService.startSale(sellerUser);
        saleService.addItem(sale, p.getId(), 1);

        saleService.confirmSale(sale);

        Sale savedSale = saleService.getSales().next();
        assertEquals(sale.getId(), savedSale.getId());
    }

    @Test
    void testConfirmSaleWithUniformProduct() {
        Product p = productService.registerUniform("Pantalon Formal", new BigDecimal("15.00"), 10, "32", "Mejia");
        Sale sale = saleService.startSale(sellerUser);
        saleService.addItem(sale, p.getId(), 4);

        saleService.confirmSale(sale);

        assertEquals(6, p.getStock());
    }

    @Test
    void testConfirmSaleTwiceAddsDuplicateToSalesList() {
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = saleService.startSale(sellerUser);
        saleService.addItem(sale, p.getId(), 1);

        saleService.confirmSale(sale);
        saleService.confirmSale(sale);

        assertEquals(2, saleService.getSales().size());
    }

    @Test
    void testGetSalesInitiallyEmpty() {
        assertTrue(saleService.getSales().isEmpty());
    }

    @Test
    void testGetSalesReflectsConfirmedSales() {
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = saleService.startSale(sellerUser);
        saleService.addItem(sale, p.getId(), 1);
        saleService.confirmSale(sale);

        assertEquals(1, saleService.getSales().size());
    }

    @Test
    void testGetSalesSameReference() {
        assertSame(saleService.getSales(), saleService.getSales());
    }

    @Test
    void testGetSalesNotNull() {
        assertNotNull(saleService.getSales());
    }

    @Test
    void testGetSalesAllowsIteration() {
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = saleService.startSale(sellerUser);
        saleService.addItem(sale, p.getId(), 1);
        saleService.confirmSale(sale);

        SimpleList<Sale> sales = saleService.getSales();
        sales.resetIteration();
        assertTrue(sales.hasNext());
        assertNotNull(sales.next());
    }

    @Test
    void testGetSalesConsistentCall() {
        assertEquals(saleService.getSales(), saleService.getSales());
    }

    @Test
    void testGetSalesUnconfirmedSaleNotIncluded() {
        saleService.startSale(sellerUser);
        assertTrue(saleService.getSales().isEmpty());
    }

    @Test
    void testGetSalesMultipleSalesCount() {
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        for (int i = 0; i < 3; i++) {
            Sale s = saleService.startSale(sellerUser);
            saleService.addItem(s, p.getId(), 1);
            saleService.confirmSale(s);
        }
        assertEquals(3, saleService.getSales().size());
    }

    @Test
    void testGetSalesOrderPreserved() {
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale s1 = saleService.startSale(sellerUser);
        saleService.addItem(s1, p.getId(), 1);
        saleService.confirmSale(s1);

        Sale s2 = saleService.startSale(sellerUser);
        saleService.addItem(s2, p.getId(), 1);
        saleService.confirmSale(s2);

        SimpleList<Sale> sales = saleService.getSales();
        sales.resetIteration();
        assertEquals(s1.getId(), sales.next().getId());
        assertEquals(s2.getId(), sales.next().getId());
    }

    @Test
    void testGetSalesReturnsSimpleListInstance() {
        assertTrue(saleService.getSales() instanceof SimpleList);
    }

    @Test
    void testExportReportCsvValidFileGeneration() throws IOException {
        String path = TEST_DIR + "/ventas.csv";
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = saleService.startSale(sellerUser);
        saleService.addItem(sale, p.getId(), 2);
        saleService.confirmSale(sale);

        saleService.exportReportCSV(path);

        File file = new File(path);
        assertTrue(file.exists());
        List<String> lines = Files.readAllLines(Path.of(path));
        assertEquals(2, lines.size());
        assertEquals("ID_Venta,Vendedor,Fecha,Total", lines.get(0));
    }

    @Test
    void testExportReportCsvAutoAppendsExtension() {
        String path = TEST_DIR + "/ventasReport";
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = saleService.startSale(sellerUser);
        saleService.addItem(sale, p.getId(), 1);
        saleService.confirmSale(sale);

        saleService.exportReportCSV(path);

        assertTrue(new File(path + ".csv").exists());
    }

    @Test
    void testExportReportCsvContainsSellerUsername() throws IOException {
        String path = TEST_DIR + "/sellerReport.csv";
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = saleService.startSale(sellerUser);
        saleService.addItem(sale, p.getId(), 1);
        saleService.confirmSale(sale);

        saleService.exportReportCSV(path);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertTrue(lines.get(1).contains("vendedor1"));
    }

    @Test
    void testExportReportCsvContainsSaleTotal() throws IOException {
        String path = TEST_DIR + "/totalReport.csv";
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = saleService.startSale(sellerUser);
        saleService.addItem(sale, p.getId(), 4);
        saleService.confirmSale(sale);

        saleService.exportReportCSV(path);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertTrue(lines.get(1).contains("11.50"));
    }

    @Test
    void testExportReportCsvEmptySalesList() throws IOException {
        String path = TEST_DIR + "/emptyVentas.csv";
        saleService.exportReportCSV(path);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertEquals(1, lines.size());
        assertEquals("ID_Venta,Vendedor,Fecha,Total", lines.get(0));
    }

    @Test
    void testExportReportCsvMultipleSalesRows() throws IOException {
        String path = TEST_DIR + "/multiVentas.csv";
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");

        Sale s1 = saleService.startSale(sellerUser);
        saleService.addItem(s1, p.getId(), 1);
        saleService.confirmSale(s1);

        Sale s2 = saleService.startSale(sellerUser);
        saleService.addItem(s2, p.getId(), 2);
        saleService.confirmSale(s2);

        saleService.exportReportCSV(path);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertEquals(3, lines.size());
    }

    @Test
    void testExportReportCsvNullPathDoesNotCrash() {
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = saleService.startSale(sellerUser);
        saleService.addItem(sale, p.getId(), 1);
        saleService.confirmSale(sale);

        assertDoesNotThrow(() -> saleService.exportReportCSV(null));
    }

    @Test
    void testExportReportCsvBlankPathDoesNotCrash() {
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = saleService.startSale(sellerUser);
        saleService.addItem(sale, p.getId(), 1);
        saleService.confirmSale(sale);

        assertDoesNotThrow(() -> saleService.exportReportCSV("   "));
    }

    @Test
    void testExportReportCsvOverwritesExistingFile() throws IOException {
        String path = TEST_DIR + "/overwriteVentas.csv";
        Product p = productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");

        Sale s1 = saleService.startSale(sellerUser);
        saleService.addItem(s1, p.getId(), 1);
        saleService.confirmSale(s1);
        saleService.exportReportCSV(path);

        Sale s2 = saleService.startSale(sellerUser);
        saleService.addItem(s2, p.getId(), 1);
        saleService.confirmSale(s2);
        saleService.exportReportCSV(path);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertEquals(3, lines.size());
    }

    @Test
    void testExportReportCsvContainsHeaderLine() throws IOException {
        String path = TEST_DIR + "/headerCheck.csv";
        saleService.exportReportCSV(path);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertFalse(lines.isEmpty());
        assertTrue(lines.get(0).startsWith("ID_Venta"));
    }
}