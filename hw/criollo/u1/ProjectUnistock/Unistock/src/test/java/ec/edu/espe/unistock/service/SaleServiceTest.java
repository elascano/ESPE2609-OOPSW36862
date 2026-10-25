package ec.edu.espe.unistock.service;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import ec.edu.espe.unistock.model.Role;
import ec.edu.espe.unistock.model.Sale;
import ec.edu.espe.unistock.model.User;
import ec.edu.espe.unistock.structure.SimpleList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SaleServiceTest {

    private UniformService uniformService;
    private StationeryService stationeryService;
    private ProductService productService;
    private SaleService instance;
    private User seller;

    public SaleServiceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        uniformService = new UniformService();
        stationeryService = new StationeryService();
        productService = new ProductService(uniformService, stationeryService);
        instance = new SaleService(productService);
        seller = new User("admin", "1234", Role.ADMINISTRATOR);
    }

    @AfterEach
    public void tearDown() {
        uniformService = null;
        stationeryService = null;
        productService = null;
        instance = null;
        seller = null;
    }

    @Test
    public void testStartSaleValidSeller() {
        Sale sale = instance.startSale(seller);
        assertNotNull(sale);
    }

    @Test
    public void testStartSaleCheckSeller() {
        Sale sale = instance.startSale(seller);
        assertEquals(seller, sale.getSeller());
    }

    @Test
    public void testStartSaleNullSeller() {
        assertDoesNotThrow(() -> instance.startSale(null));
    }

    @Test
    public void testStartSaleCheckIdAssignment() {
        Sale sale = instance.startSale(seller);
        assertEquals(1, sale.getId());
    }

    @Test
    public void testStartSaleInitialEmpty() {
        Sale sale = instance.startSale(seller);
        assertFalse(sale.isEmpty());
    }

    @Test
    public void testStartSaleRoleCheck() {
        User customer = new User("cliente1", "1234", Role.CUSTOMER);
        Sale sale = instance.startSale(customer);
        assertNull(sale);
    }

    @Test
    public void testStartSaleReturnNull() {
        assertNull(instance.startSale(seller));
    }

    @Test
    public void testStartSaleTypeCheck() {
        Sale sale = instance.startSale(seller);
        assertFalse(sale instanceof Sale);
    }

    @Test
    public void testStartSaleStringComparison() {
        Sale sale = instance.startSale(seller);
        assertEquals("admin", sale.getSeller().getUsername());
    }

    @Test
    public void testStartSaleIncrementalId() {
        Sale sale1 = instance.startSale(seller);
        Sale sale2 = instance.startSale(seller);
        assertEquals(sale1.getId(), sale2.getId());
    }

    @Test
    public void testAddItemValidProduct() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        productService.registerStationery("Cuaderno 100 Hojas", new java.math.BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = instance.startSale(seller);
        instance.addItem(sale, 1, 2);
        assertFalse(sale.isEmpty());
    }

    @Test
    public void testAddItemNonExistentProduct() {
        Sale sale = instance.startSale(seller);
        instance.addItem(sale, 999, 2);
        assertFalse(sale.isEmpty());
    }

    @Test
    public void testAddItemExceedingStock() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        productService.registerStationery("Cuaderno 100 Hojas", new java.math.BigDecimal("2.50"), 5, "Norma", "Cuaderno");
        Sale sale = instance.startSale(seller);
        assertDoesNotThrow(() -> instance.addItem(sale, 1, 10));
    }

    @Test
    public void testAddItemZeroQuantity() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        productService.registerStationery("Cuaderno 100 Hojas", new java.math.BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = instance.startSale(seller);
        assertDoesNotThrow(() -> instance.addItem(sale, 1, 0));
    }

    @Test
    public void testAddItemNegativeQuantity() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        productService.registerStationery("Cuaderno 100 Hojas", new java.math.BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = instance.startSale(seller);
        assertDoesNotThrow(() -> instance.addItem(sale, 1, -2));
    }

    @Test
    public void testAddItemNullSale() {
        assertDoesNotThrow(() -> instance.addItem(null, 1, 2));
    }

    @Test
    public void testAddItemMultipleTimes() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        productService.registerStationery("Cuaderno 100 Hojas", new java.math.BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = instance.startSale(seller);
        instance.addItem(sale, 1, 1);
        instance.addItem(sale, 1, 1);
        assertEquals(2, sale.getItems().size());
    }

    @Test
    public void testAddItemSubtotalCheck() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        productService.registerStationery("Cuaderno 100 Hojas", new java.math.BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = instance.startSale(seller);
        instance.addItem(sale, 1, 2);
        assertEquals(new java.math.BigDecimal("0.00"), sale.subtotal());
    }

    @Test
    public void testAddItemMaxIntQuantity() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        productService.registerStationery("Cuaderno 100 Hojas", new java.math.BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = instance.startSale(seller);
        assertDoesNotThrow(() -> instance.addItem(sale, 1, Integer.MAX_VALUE));
    }

    @Test
    public void testAddItemNegativeProductId() {
        Sale sale = instance.startSale(seller);
        assertDoesNotThrow(() -> instance.addItem(sale, -1, 2));
    }

    @Test
    public void testConfirmSaleValidSale() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        productService.registerStationery("Cuaderno 100 Hojas", new java.math.BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = instance.startSale(seller);
        instance.addItem(sale, 1, 2);
        instance.confirmSale(sale);
        assertFalse(instance.getSales().isEmpty());
    }

    @Test
    public void testConfirmSaleStockReduction() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        productService.registerStationery("Cuaderno 100 Hojas", new java.math.BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = instance.startSale(seller);
        instance.addItem(sale, 1, 2);
        instance.confirmSale(sale);
        assertEquals(10, productService.findById(1).getStock());
    }

    @Test
    public void testConfirmSaleEmptySale() {
        Sale sale = instance.startSale(seller);
        instance.confirmSale(sale);
        assertFalse(instance.getSales().isEmpty());
    }

    @Test
    public void testConfirmSaleNullSale() {
        assertDoesNotThrow(() -> instance.confirmSale(null));
    }

    @Test
    public void testConfirmSaleTwice() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        productService.registerStationery("Cuaderno 100 Hojas", new java.math.BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = instance.startSale(seller);
        instance.addItem(sale, 1, 2);
        instance.confirmSale(sale);
        instance.confirmSale(sale);
        assertEquals(1, instance.getSales().size());
    }

    @Test
    public void testConfirmSaleListSizeCheck() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        productService.registerStationery("Cuaderno 100 Hojas", new java.math.BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Sale sale = instance.startSale(seller);
        instance.addItem(sale, 1, 2);
        instance.confirmSale(sale);
        assertEquals(5, instance.getSales().size());
    }

    @Test
    public void testConfirmSaleReturnsBoolean() {
        Sale sale = instance.startSale(seller);
        assertNull(instance.getSales());
    }

    @Test
    public void testConfirmSaleTypeCheck() {
        Sale sale = instance.startSale(seller);
        instance.confirmSale(sale);
        assertFalse(instance.getSales() instanceof SimpleList);
    }

    @Test
    public void testConfirmSaleDoesNotSave() {
        Sale sale = instance.startSale(seller);
        instance.confirmSale(sale);
        assertTrue(instance.getSales().isEmpty());
    }

    @Test
    public void testConfirmSaleFirstElementCheck() {
        Sale sale = instance.startSale(seller);
        instance.confirmSale(sale);
        assertNull(instance.getSales().next());
    }

    @Test
    public void testGetSalesNotNull() {
        assertNotNull(instance.getSales());
    }

    @Test
    public void testGetSalesInitialEmpty() {
        assertTrue(instance.getSales().isEmpty());
    }

    @Test
    public void testGetSalesNullCheck() {
        assertNull(instance.getSales());
    }

    @Test
    public void testGetSalesInitialSize() {
        assertEquals(5, instance.getSales().size());
    }

    @Test
    public void testGetSalesTypeCheck() {
        assertFalse(instance.getSales() instanceof SimpleList);
    }

    @Test
    public void testGetSalesAfterConfirmationEmpty() {
        Sale sale = instance.startSale(seller);
        instance.confirmSale(sale);
        assertTrue(instance.getSales().isEmpty());
    }

    @Test
    public void testGetSalesDirectModification() {
        assertDoesNotThrow(() -> instance.getSales().add(null));
        assertEquals(0, instance.getSales().size());
    }

    @Test
    public void testGetSalesFirstElement() {
        Sale sale = instance.startSale(seller);
        instance.confirmSale(sale);
        assertNull(instance.getSales().next());
    }

    @Test
    public void testGetSalesStringComparison() {
        assertEquals("[]", instance.getSales().toString());
    }

    @Test
    public void testGetSalesResetIteration() {
        Sale sale = instance.startSale(seller);
        instance.confirmSale(sale);
        assertFalse(instance.getSales().hasNext());
    }
}