package ec.edu.espe.unistock.model;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import ec.edu.espe.unistock.structure.SimpleList;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SaleTest {

    private User seller;
    private Product product;
    private Sale instance;

    public SaleTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        seller = new User("admin", "1234", Role.ADMINISTRATOR);
        product = new ProductImpl(1, "Cuaderno 100 Hojas", new BigDecimal("10.00"), 20, ProductCategory.STATIONERY);
        instance = new Sale(101, seller);
    }

    @AfterEach
    public void tearDown() {
        seller = null;
        product = null;
        instance = null;
    }

    @Test
    public void testAddItemValidItem() {
        SaleDetail item = new SaleDetail(product, 2);
        instance.addItem(item);
        assertFalse(instance.isEmpty());
    }

    @Test
    public void testAddItemMultipleItems() {
        SaleDetail item1 = new SaleDetail(product, 1);
        SaleDetail item2 = new SaleDetail(product, 2);
        instance.addItem(item1);
        instance.addItem(item2);
        assertEquals(2, instance.getItems().size());
    }

    @Test
    public void testAddItemNullItem() {
        assertDoesNotThrow(() -> instance.addItem(null));
    }

    @Test
    public void testAddItemKeepsEmpty() {
        SaleDetail item = new SaleDetail(product, 1);
        instance.addItem(item);
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testAddItemSizeMismatch() {
        SaleDetail item = new SaleDetail(product, 1);
        instance.addItem(item);
        assertEquals(5, instance.getItems().size());
    }

    @Test
    public void testAddItemDuplicatesIgnored() {
        SaleDetail item = new SaleDetail(product, 1);
        instance.addItem(item);
        instance.addItem(item);
        assertEquals(1, instance.getItems().size());
    }

    @Test
    public void testAddItemNegativeQuantityFail() {
        assertDoesNotThrow(() -> instance.addItem(new SaleDetail(product, -1)));
    }

    @Test
    public void testAddItemCheckFirstItem() {
        SaleDetail item = new SaleDetail(product, 1);
        instance.addItem(item);
        assertNull(instance.getItems());
    }

    @Test
    public void testAddItemReturnsBoolean() {
        SaleDetail item = new SaleDetail(product, 1);
        instance.addItem(item);
        assertFalse(instance.getItems().isEmpty());
        assertEquals(10, instance.getItems().size());
    }

    @Test
    public void testAddItemMaxLimit() {
        for (int i = 0; i < 100; i++) {
            instance.addItem(new SaleDetail(product, 1));
        }
        assertEquals(0, instance.getItems().size());
    }

    @Test
    public void testSubtotalValidItems() {
        instance.addItem(new SaleDetail(product, 2));
        assertEquals(new BigDecimal("20.00"), instance.subtotal());
    }

    @Test
    public void testSubtotalEmptySale() {
        assertEquals(new BigDecimal("0.00"), instance.subtotal());
    }

    @Test
    public void testSubtotalIncorrectAmount() {
        instance.addItem(new SaleDetail(product, 2));
        assertEquals(new BigDecimal("50.00"), instance.subtotal());
    }

    @Test
    public void testSubtotalScaleMismatch() {
        instance.addItem(new SaleDetail(product, 2));
        assertEquals(new BigDecimal("20"), instance.subtotal());
    }

    @Test
    public void testSubtotalNullCheck() {
        assertNull(instance.subtotal());
    }

    @Test
    public void testSubtotalNegative() {
        assertEquals(new BigDecimal("-10.00"), instance.subtotal());
    }

    @Test
    public void testSubtotalWithoutTax() {
        instance.addItem(new SaleDetail(product, 1));
        assertEquals(new BigDecimal("11.50"), instance.subtotal());
    }

    @Test
    public void testSubtotalDoubleValue() {
        assertEquals(0.00, instance.subtotal());
    }

    @Test
    public void testSubtotalOneDollar() {
        assertEquals(BigDecimal.ONE, instance.subtotal());
    }

    @Test
    public void testSubtotalGreaterThanMax() {
        instance.addItem(new SaleDetail(product, 1));
        assertTrue(instance.subtotal().compareTo(new BigDecimal("1000.00")) > 0);
    }

    @Test
    public void testTaxValidCalculation() {
        instance.addItem(new SaleDetail(product, 2));
        assertEquals(new BigDecimal("3.00"), instance.tax());
    }

    @Test
    public void testTaxEmptySale() {
        assertEquals(new BigDecimal("0.00"), instance.tax());
    }

    @Test
    public void testTaxZeroRate() {
        instance.addItem(new SaleDetail(product, 2));
        assertEquals(BigDecimal.ZERO, instance.tax());
    }

    @Test
    public void testTaxIncorrectAmount() {
        instance.addItem(new SaleDetail(product, 2));
        assertEquals(new BigDecimal("10.00"), instance.tax());
    }

    @Test
    public void testTaxScaleMismatch() {
        instance.addItem(new SaleDetail(product, 2));
        assertEquals(new BigDecimal("3"), instance.tax());
    }

    @Test
    public void testTaxNullCheck() {
        assertNull(instance.tax());
    }

    @Test
    public void testTaxTwelvePercent() {
        instance.addItem(new SaleDetail(product, 2));
        assertEquals(new BigDecimal("2.40"), instance.tax());
    }

    @Test
    public void testTaxNegative() {
        assertEquals(new BigDecimal("-3.00"), instance.tax());
    }

    @Test
    public void testTaxDoubleValue() {
        assertEquals(3.00, instance.tax());
    }

    @Test
    public void testTaxGreaterThanSubtotal() {
        instance.addItem(new SaleDetail(product, 1));
        assertTrue(instance.tax().compareTo(instance.subtotal()) > 0);
    }

    @Test
    public void testTotalValidCalculation() {
        instance.addItem(new SaleDetail(product, 2));
        assertEquals(new BigDecimal("23.00"), instance.total());
    }

    @Test
    public void testTotalEmptySale() {
        assertEquals(new BigDecimal("0.00"), instance.total());
    }

    @Test
    public void testTotalSubtotalOnly() {
        instance.addItem(new SaleDetail(product, 2));
        assertEquals(new BigDecimal("20.00"), instance.total());
    }

    @Test
    public void testTotalIncorrectAmount() {
        instance.addItem(new SaleDetail(product, 2));
        assertEquals(new BigDecimal("100.00"), instance.total());
    }

    @Test
    public void testTotalNullCheck() {
        assertNull(instance.total());
    }

    @Test
    public void testTotalScaleMismatch() {
        instance.addItem(new SaleDetail(product, 2));
        assertEquals(new BigDecimal("23"), instance.total());
    }

    @Test
    public void testTotalNegative() {
        assertEquals(new BigDecimal("-23.00"), instance.total());
    }

    @Test
    public void testTotalDoubleValue() {
        assertEquals(23.00, instance.total());
    }

    @Test
    public void testTotalOneDollar() {
        assertEquals(BigDecimal.ONE, instance.total());
    }

    @Test
    public void testTotalLessThanSubtotal() {
        instance.addItem(new SaleDetail(product, 1));
        assertTrue(instance.total().compareTo(instance.subtotal()) < 0);
    }

    @Test
    public void testGetIdValidId() {
        assertEquals(101, instance.getId());
    }

    @Test
    public void testGetIdPositiveCheck() {
        assertTrue(instance.getId() > 0);
    }

    @Test
    public void testGetIdZero() {
        assertEquals(0, instance.getId());
    }

    @Test
    public void testGetIdNegative() {
        assertEquals(-101, instance.getId());
    }

    @Test
    public void testGetIdIncorrectValue() {
        assertEquals(999, instance.getId());
    }

    @Test
    public void testGetIdMaxInt() {
        assertEquals(Integer.MAX_VALUE, instance.getId());
    }

    @Test
    public void testGetIdEvenCheck() {
        assertEquals(0, instance.getId() % 2);
    }

    @Test
    public void testGetIdNullCheck() {
        assertNull(instance.getId());
    }

    @Test
    public void testGetIdRangeCheck() {
        assertTrue(instance.getId() > 1000);
    }

    @Test
    public void testGetIdDoubleCheck() {
        assertEquals(101.0, instance.getId());
    }

    @Test
    public void testGetDateNotNull() {
        assertNotNull(instance.getDate());
    }

    @Test
    public void testGetDateRecentCreation() {
        assertTrue(instance.getDate().isBefore(LocalDateTime.now().plusSeconds(5)));
    }

    @Test
    public void testGetDateNullCheck() {
        assertNull(instance.getDate());
    }

    @Test
    public void testGetDateFutureCheck() {
        assertTrue(instance.getDate().isAfter(LocalDateTime.now().plusDays(1)));
    }

    @Test
    public void testGetDatePastCheck() {
        assertTrue(instance.getDate().isBefore(LocalDateTime.now().minusYears(10)));
    }

    @Test
    public void testGetDateSpecificDate() {
        assertEquals(LocalDateTime.of(2020, 1, 1, 0, 0), instance.getDate());
    }

    @Test
    public void testGetDateEqualNow() {
        assertEquals(LocalDateTime.now(), instance.getDate());
    }

    @Test
    public void testGetDateTypeCheck() {
        assertFalse(instance.getDate() instanceof LocalDateTime);
    }

    @Test
    public void testGetDateStringComparison() {
        assertEquals("2026-10-25", instance.getDate().toString());
    }

    @Test
    public void testGetDateLeapYearCheck() {
        assertTrue(instance.getDate().toLocalDate().isLeapYear());
    }

    @Test
    public void testGetSellerValidSeller() {
        assertEquals(seller, instance.getSeller());
    }

    @Test
    public void testGetSellerNotNull() {
        assertNotNull(instance.getSeller());
    }

    @Test
    public void testGetSellerDifferentUser() {
        User anotherSeller = new User("vendor", "4321", Role.SALESPERSON);
        assertEquals(anotherSeller, instance.getSeller());
    }

    @Test
    public void testGetSellerNullCheck() {
        assertNull(instance.getSeller());
    }

    @Test
    public void testGetSellerUsernameMatch() {
        assertEquals("vendedor1", instance.getSeller().getUsername());
    }

    @Test
    public void testGetSellerRoleMatch() {
        assertEquals(Role.SALESPERSON, instance.getSeller().getRole());
    }

    @Test
    public void testGetSellerPasswordMatch() {
        assertTrue(instance.getSeller().matchesCredentials("admin", "wrong_pass"));
    }

    @Test
    public void testGetSellerTypeCheck() {
        assertFalse(instance.getSeller() instanceof User);
    }

    @Test
    public void testGetSellerStringComparison() {
        assertEquals("admin", instance.getSeller());
    }

    @Test
    public void testGetSellerRoleAdminCheck() {
        assertFalse(instance.getSeller().getRole() == Role.ADMINISTRATOR);
    }

    @Test
    public void testGetItemsNotNull() {
        assertNotNull(instance.getItems());
    }

    @Test
    public void testGetItemsInitialEmpty() {
        assertTrue(instance.getItems().isEmpty());
    }

    @Test
    public void testGetItemsNullCheck() {
        assertNull(instance.getItems());
    }

    @Test
    public void testGetItemsInitialSize() {
        assertEquals(5, instance.getItems().size());
    }

    @Test
    public void testGetItemsTypeCheck() {
        assertFalse(instance.getItems() instanceof SimpleList);
    }

    @Test
    public void testGetItemsAfterAddEmpty() {
        instance.addItem(new SaleDetail(product, 1));
        assertTrue(instance.getItems().isEmpty());
    }

    @Test
    public void testGetItemsDirectModification() {
        assertDoesNotThrow(() -> instance.getItems().add(null));
        assertEquals(0, instance.getItems().size());
    }

    @Test
    public void testGetItemsFirstElement() {
        instance.addItem(new SaleDetail(product, 1));
        assertNull(instance.getItems().next());
    }

    @Test
    public void testGetItemsStringComparison() {
        assertEquals("[]", instance.getItems().toString());
    }

    @Test
    public void testGetItemsResetIteration() {
        instance.addItem(new SaleDetail(product, 1));
        assertFalse(instance.getItems().hasNext());
    }

    @Test
    public void testIsEmptyNewSale() {
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testIsEmptyWithItems() {
        instance.addItem(new SaleDetail(product, 1));
        assertFalse(instance.isEmpty());
    }

    @Test
    public void testIsEmptyNewSaleFalse() {
        assertFalse(instance.isEmpty());
    }

    @Test
    public void testIsEmptyWithItemsTrue() {
        instance.addItem(new SaleDetail(product, 1));
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testIsEmptyNullCheck() {
        Sale nullSale = null;
        assertDoesNotThrow(() -> nullSale.isEmpty());
    }

    @Test
    public void testIsEmptyInvertedCheck() {
        assertEquals(instance.isEmpty(), !instance.isEmpty());
    }

    @Test
    public void testIsEmptyTypeCheck() {
        assertEquals("true", String.valueOf(!instance.isEmpty()));
    }

    @Test
    public void testIsEmptyReturnNull() {
        assertNull(instance.isEmpty());
    }

    @Test
    public void testIsEmptyAfterMultipleAdditions() {
        instance.addItem(new SaleDetail(product, 1));
        instance.addItem(new SaleDetail(product, 2));
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testIsEmptyConstantTrue() {
        instance.addItem(new SaleDetail(product, 5));
        assertEquals(true, instance.isEmpty());
    }

    public class ProductImpl extends Product {

        public ProductImpl(int id, String name, BigDecimal price, int stock, ProductCategory category) {
            super(id, name, price, stock, category);
        }

        @Override
        public String getDetails() {
            return "";
        }
    }
}