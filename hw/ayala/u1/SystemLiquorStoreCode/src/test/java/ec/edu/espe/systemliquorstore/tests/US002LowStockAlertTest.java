package ec.edu.espe.systemliquorstore.tests;

import ec.edu.espe.systemliquorstore.model.Product;
import ec.edu.espe.systemliquorstore.model.StockMovement;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class US002LowStockAlertTest {

    private Product createProduct(int stock, int minimumStock) {
        return new Product("WHI01", "Whisky 750ml", 10.00, 15.00, stock, minimumStock);
    }

    @Test
    public void testUS002Case01OneUnitAboveMinimumIsNotLow() {
        assertFalse(createProduct(4, 3).isLowStock());
    }

    @Test
    public void testUS002Case02DecreasingToZeroTriggersAlert() {
        Product product = createProduct(5, 3);

        product.decreaseStock(5);

        assertEquals(0, product.getStock());
        assertTrue(product.isLowStock());
    }

    @Test
    public void testUS002Case03FailedDecreaseDoesNotChangeAlert() {
        Product product = createProduct(5, 3);

        assertThrows(IllegalArgumentException.class, () -> product.decreaseStock(6));

        assertEquals(5, product.getStock());
        assertFalse(product.isLowStock());
    }

    @Test
    public void testUS002Case04LoweringMinimumClearsAlert() {
        Product product = createProduct(2, 3);
        assertTrue(product.isLowStock());

        product.setMinimumStock(1);

        assertFalse(product.isLowStock());
    }

    @Test
    public void testUS002Case05MovementRejectsZeroId() {
        assertThrows(IllegalArgumentException.class,
                () -> new StockMovement(0, "2026-10-24", "WHI01", "ENTRY", 1));
    }

    @Test
    public void testUS002Case06MovementRejectsZeroQuantity() {
        assertThrows(IllegalArgumentException.class,
                () -> new StockMovement(1, "2026-10-24", "WHI01", "ENTRY", 0));
    }

    @Test
    public void testUS002Case07MovementNormalizesTypeAndCode() {
        StockMovement movement = new StockMovement(1, "2026-10-24", " whi01 ", " exit ", 2);

        assertEquals("WHI01", movement.getProductCode());
        assertEquals("EXIT", movement.getMovementType());
    }

    @Test
    public void testUS002Case08MovementRejectsImpossibleDate() {
        assertThrows(IllegalArgumentException.class,
                () -> new StockMovement(1, "2026-02-30", "WHI01", "ENTRY", 1));
    }

    @Test
    public void testUS002Case09FarFutureMovementDateIsRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new StockMovement(1, "2999-12-31", "WHI01", "ENTRY", 1));
    }

    @Test
    public void testUS002Case10ExitMovementAboveStockIsRejected() {
        Product product = createProduct(5, 2);

        assertThrows(IllegalArgumentException.class,
                () -> product.createStockMovement(1, "2026-10-24", "EXIT", 100));
    }
}