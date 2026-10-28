package ec.edu.espe.systemliquorstore.tests;

import ec.edu.espe.systemliquorstore.model.Product;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;

public class US001ProductsInventoryTest {

    private Product createProduct() {
        return new Product("WHI01", "Whisky 750ml", 10.00, 15.00, 5, 2);
    }

    @Test
    public void testUS001Case01ConstructorRejectsNullCode() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product(null, "Whisky", 10.00, 15.00, 5, 2));
    }

    @Test
    public void testUS001Case02ConstructorRejectsNullName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product("WHI01", null, 10.00, 15.00, 5, 2));
    }

    @Test
    public void testUS001Case03ConstructorRejectsNaNPrice() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product("WHI01", "Whisky", 10.00, Double.NaN, 5, 2));
    }

    @Test
    public void testUS001Case04ConstructorRejectsInfiniteCost() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product("WHI01", "Whisky", Double.POSITIVE_INFINITY, 15.00, 5, 2));
    }

    @Test
    public void testUS001Case05ConstructorAcceptsSmallestValidValues() {
        Product product = new Product("WHI01", "Whisky", 0.00, 0.01, 0, 0);

        assertEquals(0.01, product.getSellingPrice(), 0.0001);
        assertEquals(0, product.getStock());
    }

    @Test
    public void testUS001Case06IncreaseStockRejectsIntegerOverflow() {
        Product product = new Product("WHI01", "Whisky", 10.00, 15.00, Integer.MAX_VALUE, 2);

        assertThrows(IllegalArgumentException.class, () -> product.increaseStock(1));
        assertEquals(Integer.MAX_VALUE, product.getStock());
    }

    @Test
    public void testUS001Case07FailedUpdatePricesKeepsOldValues() {
        Product product = createProduct();

        assertThrows(IllegalArgumentException.class, () -> product.updatePrices(5.00, -1.00));

        assertEquals(10.00, product.getPurchaseCost(), 0.001);
        assertEquals(15.00, product.getSellingPrice(), 0.001);
    }

    @Test
    public void testUS001Case08SetNameRejectsNull() {
        Product product = createProduct();

        assertThrows(IllegalArgumentException.class, () -> product.setName(null));
        assertEquals("Whisky 750ml", product.getName());
    }

    @Test
    public void testUS001Case09SellingPriceBelowCostIsAllowed() {
        // A sale can produce a loss; the profit report must show that loss.
        Product product = new Product("WHI01", "Whisky", 20.00, 10.00, 5, 2);
        assertEquals(20.00, product.getPurchaseCost(), 0.001);
        assertEquals(10.00, product.getSellingPrice(), 0.001);
    }

    @Test
    public void testUS001Case10AbsurdlyLargePriceIsRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product("WHI01", "Whisky", 10.00, Double.MAX_VALUE, 5, 2));
    }
}
