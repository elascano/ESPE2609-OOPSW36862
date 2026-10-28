package ec.edu.espe.systemliquorstore.tests;

import ec.edu.espe.systemliquorstore.model.Product;
import ec.edu.espe.systemliquorstore.model.Sale;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Enrique Ayala, Logical Lords, @ESPE
 */
public class US008DiscountTest {
    @Test public void testDiscount1() {
        System.out.println("applyValidDiscount - PASS");
        Product product = new Product("P001", "Vino", 10.0, 20.0, 20, 5);
        Sale sale = new Sale(1, "2026-10-25", "OWNER", "CASH");
        sale.addProduct(product, 2); 
        sale.applyDiscount(10.0); 
        assertEquals(40.0, sale.calculateSubtotal(), 0.001);
        assertEquals(4.0, sale.getDiscountAmount(), 0.001);
        assertEquals(36.0, sale.calculateTotal(), 0.001);
    }

    @Test public void testDiscount2() {
        System.out.println("applyZeroDiscount - FAIL");
        Product product = new Product("P001", "Vino", 10.0, 20.0, 20, 5);
        Sale sale = new Sale(1, "2026-10-25", "OWNER", "CASH");
        sale.addProduct(product, 2);
        sale.applyDiscount(0.0);
        assertEquals(10.0, sale.getDiscountAmount(), 0.001);
    }

    @Test public void testDiscount3() {
        System.out.println("applyNegativeDiscount - PASS");
        Sale sale = new Sale(1, "2026-10-25", "OWNER", "CASH");
        boolean expResult = true;
        boolean result = false;
        try {
            sale.applyDiscount(-5.0);
        } catch (IllegalArgumentException ex) {
            result = true;
        }
        assertEquals(expResult, result);
    }

    @Test public void testDiscount4() {
        System.out.println("applyExcessiveDiscount - FAIL");
        Sale sale = new Sale(1, "2026-10-25", "OWNER", "CASH");
        boolean expResult = true;
        boolean result = false;
        try {
            sale.applyDiscount(150.0);
        } catch (IllegalArgumentException ex) {
            result = true;
        }
        assertEquals(false, result);
    }

    @Test public void testDiscount5() {
        System.out.println("changeDiscount - PASS");
        Product product = new Product("P001", "Vino", 10.0, 20.0, 20, 5);
        Sale sale = new Sale(1, "2026-10-25", "OWNER", "CASH");
        sale.addProduct(product, 1);
        sale.applyDiscount(10.0);
        sale.applyDiscount(20.0);
        assertEquals(4.0, sale.getDiscountAmount(), 0.001);
        assertEquals(16.0, sale.calculateTotal(), 0.001);
    }

    @Test public void testDiscount6() {
        System.out.println("marginAfterDiscount - FAIL");
        Product product = new Product("P001", "Vino", 10.0, 20.0, 20, 5);
        Sale sale = new Sale(1, "2026-10-25", "OWNER", "CASH");
        sale.addProduct(product, 1); 
        sale.applyDiscount(10.0); 
        assertEquals(50.0, sale.calculateMargin(), 0.001);
    }

    @Test public void testDiscount7() {
        System.out.println("roundingDiscount - PASS");
        Product product = new Product("P001", "Licor", 5.0, 10.55, 20, 5);
        Sale sale = new Sale(1, "2026-10-25", "OWNER", "CASH");
        sale.addProduct(product, 1);
        sale.applyDiscount(15.0); 
        assertEquals(1.58, sale.getDiscountAmount(), 0.001);
        assertEquals(8.97, sale.calculateTotal(), 0.001);
    }

    @Test public void testDiscount8() {
        System.out.println("discountOnMultipleItems - FAIL");
        Product p1 = new Product("P001", "Item1", 5.0, 10.0, 20, 5);
        Product p2 = new Product("P002", "Item2", 5.0, 20.0, 20, 5);
        Sale sale = new Sale(1, "2026-10-25", "OWNER", "CASH");
        sale.addProduct(p1, 1);
        sale.addProduct(p2, 1); 
        sale.applyDiscount(10.0); 
        assertEquals(30.0, sale.calculateTotal(), 0.001);
    }

    @Test public void testDiscount9() {
        System.out.println("applyDiscountCompletedSale - PASS");
        Product product = new Product("P001", "Vino", 10.0, 20.0, 20, 5);
        Sale sale = new Sale(1, "2026-10-25", "OWNER", "CASH");
        sale.addProduct(product, 1);
        sale.confirmAge("1990-01-01");
        
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        sale.complete(products, new ArrayList<>());
        
        boolean result = false;
        try {
            sale.applyDiscount(10.0);
        } catch (IllegalStateException ex) {
            result = true;
        }
        assertTrue(result);
    }

    @Test public void testDiscount10() {
        System.out.println("totalIsZeroError - FAIL");
        Product product = new Product("P001", "Vino", 10.0, 20.0, 20, 5);
        Sale sale = new Sale(1, "2026-10-25", "OWNER", "CASH");
        sale.addProduct(product, 1);
        sale.applyDiscount(99.0);
        assertEquals(0.0, sale.calculateTotal(), 0.001);
    }
}
