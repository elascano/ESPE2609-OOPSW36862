package ec.edu.espe.unistock.model;

import ec.edu.espe.unistock.structure.SimpleList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class SaleTest {

    private User testSeller;
    private Sale testSale;
    private Product productNorma;
    private Product productFaber;

    private Product createConcreteProduct(int id, String name, BigDecimal price, int stock) {
        return new Product(id, name, price, stock, ProductCategory.STATIONERY) {
            @Override
            public String getDetails() {
                return "Detalles Prueba";
            }
        };
    }

    @BeforeEach
    void setUp() {
        testSeller = new User("vendedor1", "pass123", Role.SALESPERSON);
        testSale = new Sale(1001, testSeller);
        productNorma = createConcreteProduct(1, "Cuaderno Norma", new BigDecimal("2.50"), 50);
        productFaber = createConcreteProduct(2, "Lapiz FaberCastell", new BigDecimal("0.50"), 100);
    }

    @Test
    void testConstructorValidParameters() {
        Sale sale = new Sale(1, testSeller);
        assertNotNull(sale);
    }

    @Test
    void testConstructorAssignsIdCorrectly() {
        assertEquals(1001, testSale.getId());
    }

    @Test
    void testConstructorAssignsSellerCorrectly() {
        assertEquals(testSeller, testSale.getSeller());
    }

    @Test
    void testConstructorInitializesDate() {
        assertNotNull(testSale.getDate());
    }

    @Test
    void testConstructorInitializesEmptyItems() {
        assertTrue(testSale.isEmpty());
    }

    @Test
    void testConstructorAllowsNullSeller() {
        Sale sale = new Sale(2, null);
        assertNull(sale.getSeller());
    }

    @Test
    void testConstructorNegativeIdAllowed() {
        Sale sale = new Sale(-1, testSeller);
        assertEquals(-1, sale.getId());
    }

    @Test
    void testConstructorZeroIdAllowed() {
        Sale sale = new Sale(0, testSeller);
        assertEquals(0, sale.getId());
    }

    @Test
    void testConstructorTaxRateConstant() {
        assertEquals(new BigDecimal("0.15"), Sale.TAX_RATE);
    }

    @Test
    void testConstructorRecentDateGeneration() {
        LocalDateTime now = LocalDateTime.now();
        assertTrue(testSale.getDate().isBefore(now.plusSeconds(1)) && testSale.getDate().isAfter(now.minusSeconds(5)));
    }

    @Test
    void testAddItemSingleItem() {
        testSale.addItem(new SaleDetail(productNorma, 2));
        assertEquals(1, testSale.getItems().size());
    }

    @Test
    void testAddItemMultipleItems() {
        testSale.addItem(new SaleDetail(productNorma, 2));
        testSale.addItem(new SaleDetail(productFaber, 5));
        assertEquals(2, testSale.getItems().size());
    }

    @Test
    void testAddItemChangesIsEmptyStatus() {
        testSale.addItem(new SaleDetail(productNorma, 1));
        assertFalse(testSale.isEmpty());
    }

    @Test
    void testAddItemSameProductMultipleTimes() {
        testSale.addItem(new SaleDetail(productNorma, 1));
        testSale.addItem(new SaleDetail(productNorma, 2));
        assertEquals(2, testSale.getItems().size());
    }

    @Test
    void testAddItemUniformItem() {
        Product uniform = new Uniform(10, "Camisa Formal", new BigDecimal("12.00"), 10, "M", "San Gabriel");
        testSale.addItem(new SaleDetail(uniform, 1));
        assertFalse(testSale.isEmpty());
    }

    @Test
    void testAddItemStationeryItem() {
        Product stationery = new Stationery(20, "Compas Staedtler", new BigDecimal("3.50"), 10, "Staedtler", "Dibujo");
        testSale.addItem(new SaleDetail(stationery, 2));
        assertEquals(1, testSale.getItems().size());
    }

    @Test
    void testAddItemUpdatesSubtotal() {
        testSale.addItem(new SaleDetail(productNorma, 2));
        assertEquals(new BigDecimal("5.00"), testSale.subtotal());
    }

    @Test
    void testAddItemNullItemAllowedInList() {
        testSale.addItem(null);
        assertEquals(1, testSale.getItems().size());
    }

    @Test
    void testAddItemPreservesInsertionOrder() {
        SaleDetail detail1 = new SaleDetail(productNorma, 1);
        SaleDetail detail2 = new SaleDetail(productFaber, 2);
        testSale.addItem(detail1);
        testSale.addItem(detail2);
        testSale.getItems().resetIteration();
        assertEquals(detail1, testSale.getItems().next());
        assertEquals(detail2, testSale.getItems().next());
    }

    @Test
    void testAddItemIncrementsItemCountSequentially() {
        assertEquals(0, testSale.getItems().size());
        testSale.addItem(new SaleDetail(productNorma, 1));
        assertEquals(1, testSale.getItems().size());
        testSale.addItem(new SaleDetail(productFaber, 1));
        assertEquals(2, testSale.getItems().size());
    }

    @Test
    void testSubtotalWhenEmptyReturnsZero() {
        assertEquals(new BigDecimal("0.00"), testSale.subtotal());
    }

    @Test
    void testSubtotalSingleItemCalculation() {
        testSale.addItem(new SaleDetail(productNorma, 2));
        assertEquals(new BigDecimal("5.00"), testSale.subtotal());
    }

    @Test
    void testSubtotalMultipleItemsCalculation() {
        testSale.addItem(new SaleDetail(productNorma, 2));
        testSale.addItem(new SaleDetail(productFaber, 4));
        assertEquals(new BigDecimal("7.00"), testSale.subtotal());
    }

    @Test
    void testSubtotalDecimalRoundingHalfUp() {
        Product p = createConcreteProduct(3, "Borrador Artesco", new BigDecimal("0.333"), 50);
        testSale.addItem(new SaleDetail(p, 1));
        assertEquals(new BigDecimal("0.33"), testSale.subtotal());
    }

    @Test
    void testSubtotalZeroPriceItem() {
        Product freeItem = createConcreteProduct(4, "Muestra Pelikan", BigDecimal.ZERO, 10);
        testSale.addItem(new SaleDetail(freeItem, 5));
        assertEquals(new BigDecimal("0.00"), testSale.subtotal());
    }

    @Test
    void testSubtotalConsistentCall() {
        testSale.addItem(new SaleDetail(productNorma, 1));
        assertEquals(testSale.subtotal(), testSale.subtotal());
    }

    @Test
    void testSubtotalScaleIsTwo() {
        testSale.addItem(new SaleDetail(productNorma, 1));
        assertEquals(2, testSale.subtotal().scale());
    }

    @Test
    void testSubtotalNotNull() {
        assertNotNull(testSale.subtotal());
    }

    @Test
    void testSubtotalUniformProduct() {
        Product uniform = new Uniform(10, "Buzo Deportivo", new BigDecimal("15.50"), 5, "L", "Mejia");
        testSale.addItem(new SaleDetail(uniform, 2));
        assertEquals(new BigDecimal("31.00"), testSale.subtotal());
    }

    @Test
    void testSubtotalLargeAmount() {
        testSale.addItem(new SaleDetail(productNorma, 1000));
        assertEquals(new BigDecimal("2500.00"), testSale.subtotal());
    }

    @Test
    void testTaxWhenSubtotalZeroReturnsZero() {
        assertEquals(new BigDecimal("0.00"), testSale.tax());
    }

    @Test
    void testTaxCalculationFifteenPercent() {
        testSale.addItem(new SaleDetail(productNorma, 4));
        assertEquals(new BigDecimal("1.50"), testSale.tax());
    }

    @Test
    void testTaxRoundingHalfUp() {
        Product p = createConcreteProduct(3, "Item", new BigDecimal("10.05"), 10);
        testSale.addItem(new SaleDetail(p, 1));
        assertEquals(new BigDecimal("1.51"), testSale.tax());
    }

    @Test
    void testTaxScaleIsTwo() {
        testSale.addItem(new SaleDetail(productNorma, 1));
        assertEquals(2, testSale.tax().scale());
    }

    @Test
    void testTaxNotNull() {
        assertNotNull(testSale.tax());
    }

    @Test
    void testTaxConsistentCall() {
        testSale.addItem(new SaleDetail(productNorma, 2));
        assertEquals(testSale.tax(), testSale.tax());
    }

    @Test
    void testTaxMultipleItems() {
        testSale.addItem(new SaleDetail(productNorma, 2));
        testSale.addItem(new SaleDetail(productFaber, 10));
        assertEquals(new BigDecimal("1.50"), testSale.tax());
    }

    @Test
    void testTaxZeroPriceItem() {
        Product freeItem = createConcreteProduct(4, "Gratis", BigDecimal.ZERO, 10);
        testSale.addItem(new SaleDetail(freeItem, 1));
        assertEquals(new BigDecimal("0.00"), testSale.tax());
    }

    @Test
    void testTaxUniformItem() {
        Product uniform = new Uniform(10, "Pantalon Vestir", new BigDecimal("20.00"), 5, "32", "San Gabriel");
        testSale.addItem(new SaleDetail(uniform, 1));
        assertEquals(new BigDecimal("3.00"), testSale.tax());
    }

    @Test
    void testTaxSmallSubtotalRounding() {
        Product p = createConcreteProduct(5, "Item Mini", new BigDecimal("0.10"), 10);
        testSale.addItem(new SaleDetail(p, 1));
        assertEquals(new BigDecimal("0.02"), testSale.tax());
    }

    @Test
    void testTotalWhenEmptyReturnsZero() {
        assertEquals(new BigDecimal("0.00"), testSale.total());
    }

    @Test
    void testTotalEqualsSubtotalPlusTax() {
        testSale.addItem(new SaleDetail(productNorma, 4));
        BigDecimal expected = testSale.subtotal().add(testSale.tax());
        assertEquals(expected, testSale.total());
    }

    @Test
    void testTotalExactCalculation() {
        testSale.addItem(new SaleDetail(productNorma, 4));
        assertEquals(new BigDecimal("11.50"), testSale.total());
    }

    @Test
    void testTotalGetTotalMatchesTotal() {
        testSale.addItem(new SaleDetail(productNorma, 2));
        assertEquals(testSale.total(), testSale.getTotal());
    }

    @Test
    void testTotalScaleIsTwo() {
        testSale.addItem(new SaleDetail(productNorma, 1));
        assertEquals(2, testSale.total().scale());
    }

    @Test
    void testTotalNotNull() {
        assertNotNull(testSale.total());
    }

    @Test
    void testGetTotalNotNull() {
        assertNotNull(testSale.getTotal());
    }

    @Test
    void testTotalConsistentCall() {
        testSale.addItem(new SaleDetail(productNorma, 1));
        assertEquals(testSale.total(), testSale.total());
    }

    @Test
    void testTotalMultipleProducts() {
        testSale.addItem(new SaleDetail(productNorma, 2));
        testSale.addItem(new SaleDetail(productFaber, 10));
        assertEquals(new BigDecimal("11.50"), testSale.total());
    }

    @Test
    void testTotalUniformProduct() {
        Product uniform = new Uniform(10, "Camisa Formal", new BigDecimal("10.00"), 5, "M", "Mejia");
        testSale.addItem(new SaleDetail(uniform, 1));
        assertEquals(new BigDecimal("11.50"), testSale.total());
    }

    @Test
    void testGetIdPositiveValue() {
        assertEquals(1001, testSale.getId());
    }

    @Test
    void testGetIdZero() {
        Sale sale = new Sale(0, testSeller);
        assertEquals(0, sale.getId());
    }

    @Test
    void testGetIdNegative() {
        Sale sale = new Sale(-10, testSeller);
        assertEquals(-10, sale.getId());
    }

    @Test
    void testGetIdLargeValue() {
        Sale sale = new Sale(99999, testSeller);
        assertEquals(99999, sale.getId());
    }

    @Test
    void testGetIdImmutable() {
        int id = testSale.getId();
        testSale.addItem(new SaleDetail(productNorma, 1));
        assertEquals(id, testSale.getId());
    }

    @Test
    void testGetIdConsistentCall() {
        assertEquals(testSale.getId(), testSale.getId());
    }

    @Test
    void testGetIdDifferentBetweenSales() {
        Sale sale2 = new Sale(1002, testSeller);
        assertNotEquals(testSale.getId(), sale2.getId());
    }

    @Test
    void testGetIdUniquePerInstance() {
        Sale sale2 = new Sale(1001, testSeller);
        assertEquals(testSale.getId(), sale2.getId());
    }

    @Test
    void testGetIdPrimitiveIntType() {
        int id = testSale.getId();
        assertTrue(id > 0);
    }

    @Test
    void testGetIdUnchangedAfterTotalCalculation() {
        testSale.addItem(new SaleDetail(productNorma, 1));
        testSale.total();
        assertEquals(1001, testSale.getId());
    }

    @Test
    void testGetDateNotNull() {
        assertNotNull(testSale.getDate());
    }

    @Test
    void testGetDateReturnsCurrentOrPastTime() {
        assertTrue(testSale.getDate().isBefore(LocalDateTime.now().plusSeconds(1)));
    }

    @Test
    void testGetDateImmutable() {
        LocalDateTime date = testSale.getDate();
        assertEquals(date, testSale.getDate());
    }

    @Test
    void testGetDateConsistentCall() {
        assertEquals(testSale.getDate(), testSale.getDate());
    }

    @Test
    void testGetDateUnchangedAfterAddItem() {
        LocalDateTime dateBefore = testSale.getDate();
        testSale.addItem(new SaleDetail(productNorma, 1));
        assertEquals(dateBefore, testSale.getDate());
    }

    @Test
    void testGetDateChronologicalOrder() throws InterruptedException {
        Sale sale1 = new Sale(1, testSeller);
        Thread.sleep(10);
        Sale sale2 = new Sale(2, testSeller);
        assertTrue(sale1.getDate().isBefore(sale2.getDate()) || sale1.getDate().isEqual(sale2.getDate()));
    }

    @Test
    void testGetDateInstanceOfLocalDateTime() {
        assertTrue(testSale.getDate() instanceof LocalDateTime);
    }

    @Test
    void testGetDateYearMatchesCurrentYear() {
        assertEquals(LocalDateTime.now().getYear(), testSale.getDate().getYear());
    }

    @Test
    void testGetDateMonthMatchesCurrentMonth() {
        assertEquals(LocalDateTime.now().getMonth(), testSale.getDate().getMonth());
    }

    @Test
    void testGetDateDayMatchesCurrentDay() {
        assertEquals(LocalDateTime.now().getDayOfMonth(), testSale.getDate().getDayOfMonth());
    }

    @Test
    void testGetSellerReturnsCorrectSeller() {
        assertEquals(testSeller, testSale.getSeller());
    }

    @Test
    void testGetSellerNullIfAssignedNull() {
        Sale sale = new Sale(1, null);
        assertNull(sale.getSeller());
    }

    @Test
    void testGetSellerSameReference() {
        assertSame(testSeller, testSale.getSeller());
    }

    @Test
    void testGetSellerImmutable() {
        User seller = testSale.getSeller();
        assertEquals("vendedor1", seller.getUsername());
    }

    @Test
    void testGetSellerConsistentCall() {
        assertEquals(testSale.getSeller(), testSale.getSeller());
    }

    @Test
    void testGetSellerAdminRole() {
        User admin = new User("admin", "pass", Role.ADMINISTRATOR);
        Sale sale = new Sale(2, admin);
        assertEquals(Role.ADMINISTRATOR, sale.getSeller().getRole());
    }

    @Test
    void testGetSellerCustomerRole() {
        User customer = new User("cliente", "pass", Role.CUSTOMER);
        Sale sale = new Sale(3, customer);
        assertEquals(Role.CUSTOMER, sale.getSeller().getRole());
    }

    @Test
    void testGetSellerUsernameMatch() {
        assertEquals("vendedor1", testSale.getSeller().getUsername());
    }

    @Test
    void testGetSellerNotNull() {
        assertNotNull(testSale.getSeller());
    }

    @Test
    void testGetSellerUnchangedAfterAddItem() {
        testSale.addItem(new SaleDetail(productNorma, 1));
        assertEquals(testSeller, testSale.getSeller());
    }

    @Test
    void testGetItemsReturnsSimpleList() {
        assertNotNull(testSale.getItems());
    }

    @Test
    void testGetItemsInitialStateEmpty() {
        assertTrue(testSale.getItems().isEmpty());
    }

    @Test
    void testGetItemsReflectsAddedElements() {
        testSale.addItem(new SaleDetail(productNorma, 1));
        assertEquals(1, testSale.getItems().size());
    }

    @Test
    void testGetItemsSameReference() {
        SimpleList<SaleDetail> items1 = testSale.getItems();
        SimpleList<SaleDetail> items2 = testSale.getItems();
        assertSame(items1, items2);
    }

    @Test
    void testGetItemsMultipleElements() {
        testSale.addItem(new SaleDetail(productNorma, 1));
        testSale.addItem(new SaleDetail(productFaber, 2));
        assertEquals(2, testSale.getItems().size());
    }

    @Test
    void testGetItemsAllowsIteration() {
        testSale.addItem(new SaleDetail(productNorma, 1));
        SimpleList<SaleDetail> items = testSale.getItems();
        items.resetIteration();
        assertTrue(items.hasNext());
        assertNotNull(items.next());
    }

    @Test
    void testGetItemsConsistentCall() {
        assertEquals(testSale.getItems(), testSale.getItems());
    }

    @Test
    void testGetItemsSizeMatches() {
        testSale.addItem(new SaleDetail(productNorma, 5));
        assertEquals(1, testSale.getItems().size());
    }

    @Test
    void testGetItemsContainsSaleDetailInstances() {
        testSale.addItem(new SaleDetail(productNorma, 1));
        testSale.getItems().resetIteration();
        assertTrue(testSale.getItems().next() instanceof SaleDetail);
    }

    @Test
    void testGetItemsNotNullAfterOperations() {
        testSale.addItem(new SaleDetail(productNorma, 1));
        testSale.subtotal();
        assertNotNull(testSale.getItems());
    }

    @Test
    void testIsEmptyTrueInitially() {
        assertTrue(testSale.isEmpty());
    }

    @Test
    void testIsEmptyFalseAfterAddItem() {
        testSale.addItem(new SaleDetail(productNorma, 1));
        assertFalse(testSale.isEmpty());
    }

    @Test
    void testIsEmptyConsistentWithItemsSize() {
        assertEquals(testSale.getItems().isEmpty(), testSale.isEmpty());
    }

    @Test
    void testIsEmptyMultipleCalls() {
        assertTrue(testSale.isEmpty());
        assertTrue(testSale.isEmpty());
    }

    @Test
    void testIsEmptyAfterMultipleAdditions() {
        testSale.addItem(new SaleDetail(productNorma, 1));
        testSale.addItem(new SaleDetail(productFaber, 2));
        assertFalse(testSale.isEmpty());
    }

    @Test
    void testIsEmptyConsistentCall() {
        assertEquals(testSale.isEmpty(), testSale.isEmpty());
    }

    @Test
    void testIsEmptyAfterSubtotalCalculation() {
        testSale.subtotal();
        assertTrue(testSale.isEmpty());
    }

    @Test
    void testIsEmptyAfterTotalCalculation() {
        testSale.total();
        assertTrue(testSale.isEmpty());
    }

    @Test
    void testIsEmptyUniformItemAdded() {
        Product uniform = new Uniform(10, "Falda", new BigDecimal("12.00"), 5, "S", "San Gabriel");
        testSale.addItem(new SaleDetail(uniform, 1));
        assertFalse(testSale.isEmpty());
    }

    @Test
    void testIsEmptyStationeryItemAdded() {
        Product stationery = new Stationery(20, "Marcador BIC", new BigDecimal("0.75"), 10, "BIC", "Marcador");
        testSale.addItem(new SaleDetail(stationery, 1));
        assertFalse(testSale.isEmpty());
    }
}