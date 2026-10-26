package ec.edu.espe.systemliquorstore.tests;

import ec.edu.espe.systemliquorstore.model.Product;
import ec.edu.espe.systemliquorstore.model.Sale;
import org.junit.Test;
import static org.junit.Assert.*;

public class US007GrossProfitTest {
    @Test
    public void testProfit1() {
        System.out.println("calculateGrossProfit1");
        Product product = new Product("P001", "Beer", 8.00, 10.00, 20, 5);
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH");
        instance.addProduct(product, 1);
        double expResult = 2.00;
        double result = instance.calculateGrossProfit();
        assertEquals(expResult, result, 0.001);
    }

    @Test
    public void testProfit2() {
        System.out.println("calculateMargin2");
        Product product = new Product("P001", "Beer", 8.00, 10.00, 20, 5);
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH");
        instance.addProduct(product, 1);
        double expResult = 20.00;
        double result = instance.calculateMargin();
        assertEquals(expResult, result, 0.001);
    }

    @Test
    public void testProfit3() {
        System.out.println("calculateGrossProfit3");
        Product beer = new Product("P001", "Beer", 8.00, 10.00, 20, 5);
        Product wine = new Product("P002", "Wine", 12.00, 15.00, 20, 3);
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH");
        instance.addProduct(beer, 1);
        instance.addProduct(wine, 2);
        double expResult = 8.00;
        double result = instance.calculateGrossProfit();
        assertEquals(expResult, result, 0.001);
    }

    @Test
    public void testProfit4() {
        System.out.println("calculateGrossProfit4");
        Product product = new Product("P001", "Precision product", 0.50, 1.00, 20, 5);
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH");
        instance.addProduct(product, 1);
        double percentage = 0.49999999999999994;
        instance.applyDiscount(percentage);
        double expResult = 0.50;
        double result = instance.calculateGrossProfit();
        assertEquals(expResult, result, 0.001);
    }

    @Test
    public void testProfit5() {
        System.out.println("calculateMargin5");
        Product product = new Product("P001", "Precision product", 0.50, 1.00, 20, 5);
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH");
        instance.addProduct(product, 1);
        double percentage = 0.49999999999999994;
        instance.applyDiscount(percentage);
        double expResult = 50.00;
        double result = instance.calculateMargin();
        assertEquals(expResult, result, 0.001);
    }

    @Test
    public void testProfit6() {
        System.out.println("calculateGrossProfit6");
        Product product = new Product("P001", "Equal price product", 10.00, 10.00, 20, 5);
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH");
        instance.addProduct(product, 1);
        double expResult = 0.00;
        double result = instance.calculateGrossProfit();
        assertEquals(expResult, result, 0.001);
        assertEquals(0.00, instance.calculateMargin(), 0.001);
    }

    @Test
    public void testProfit7() {
        System.out.println("calculateGrossProfit7");
        Product product = new Product("P001", "Loss product", 12.00, 10.00, 20, 5);
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH");
        instance.addProduct(product, 1);
        double expResult = -2.00;
        double result = instance.calculateGrossProfit();
        assertEquals(expResult, result, 0.001);
        assertEquals(-20.00, instance.calculateMargin(), 0.001);
    }

    @Test
    public void testProfit8() {
        System.out.println("calculateGrossProfit8");
        Product product = new Product("P001", "Zero cost product", 0.00, 10.00, 20, 5);
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH");
        instance.addProduct(product, 1);
        double expResult = 10.00;
        double result = instance.calculateGrossProfit();
        assertEquals(expResult, result, 0.001);
        assertEquals(100.00, instance.calculateMargin(), 0.001);
    }

    @Test
    public void testProfit9() {
        System.out.println("calculateGrossProfit9");
        Product product = new Product("P001", "Beer", 8.00, 10.00, 20, 5);
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH");
        instance.addProduct(product, 1);
        product.updatePrices(9.00, 12.00);
        double expResult = 2.00;
        double result = instance.calculateGrossProfit();
        assertEquals(expResult, result, 0.001);
        assertEquals(8.00, instance.getItems().get(0).getUnitPurchaseCost(), 0.001);
    }

    @Test
    public void testProfit10() {
        System.out.println("calculateMargin10");
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH");
        boolean expResult = true;
        boolean result = false;
        try {
            instance.calculateMargin();
        } catch (IllegalStateException exception) {
            result = true;
        }
        assertEquals(expResult, result);
        assertEquals(0.00, instance.calculateTotal(), 0.001);
    }
}
