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

public class US009BestSellersTest {
    @Test public void testBestSellers1() {
        System.out.println("unitsSoldInRange - PASS");
        Product product = new Product("P001", "Cerveza", 5.0, 10.0, 50, 10);
        ArrayList<Sale> sales = new ArrayList<>();
        Sale sale = new Sale(1, "2026-10-20", "OWNER", "CASH");
        sale.addProduct(product, 5);
        sale.confirmAge("1990-01-01");
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        sale.complete(products, new ArrayList<>());
        sales.add(sale);
        
        int sold = product.getUnitsSold(sales, "2026-10-01", "2026-10-31");
        assertEquals(5, sold);
    }

    @Test public void testBestSellers2() {
        System.out.println("unitsSoldInRange2 - FAIL");
        Product product = new Product("P001", "Cerveza", 5.0, 10.0, 50, 10);
        ArrayList<Sale> sales = new ArrayList<>();
        Sale sale = new Sale(1, "2026-10-20", "OWNER", "CASH");
        sale.addProduct(product, 5);
        sale.confirmAge("1990-01-01");
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        sale.complete(products, new ArrayList<>());
        sales.add(sale);
        
        int sold = product.getUnitsSold(sales, "2026-10-01", "2026-10-31");
        assertEquals(10, sold);
    }

    @Test public void testBestSellers3() {
        System.out.println("unitsSoldOutsideRange - PASS");
        Product product = new Product("P001", "Cerveza", 5.0, 10.0, 50, 10);
        ArrayList<Sale> sales = new ArrayList<>();
        Sale sale = new Sale(1, "2026-09-15", "OWNER", "CASH");
        sale.addProduct(product, 5);
        sale.confirmAge("1990-01-01");
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        sale.complete(products, new ArrayList<>());
        sales.add(sale);
        
        int sold = product.getUnitsSold(sales, "2026-10-01", "2026-10-31");
        assertEquals(0, sold);
    }

    @Test public void testBestSellers4() {
        System.out.println("unitsSoldOutsideRange2 - FAIL");
        Product product = new Product("P001", "Cerveza", 5.0, 10.0, 50, 10);
        ArrayList<Sale> sales = new ArrayList<>();
        Sale sale = new Sale(1, "2026-09-15", "OWNER", "CASH");
        sale.addProduct(product, 5);
        sale.confirmAge("1990-01-01");
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        sale.complete(products, new ArrayList<>());
        sales.add(sale);
        
        int sold = product.getUnitsSold(sales, "2026-10-01", "2026-10-31");
        assertEquals(5, sold);
    }

    @Test public void testBestSellers5() {
        System.out.println("multipleSalesUnits - PASS");
        Product product = new Product("P001", "Cerveza", 5.0, 10.0, 50, 10);
        ArrayList<Sale> sales = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        
        Sale sale1 = new Sale(1, "2026-10-10", "OWNER", "CASH");
        sale1.addProduct(product, 3);
        sale1.confirmAge("1990-01-01");
        sale1.complete(products, new ArrayList<>());
        sales.add(sale1);
        
        Sale sale2 = new Sale(2, "2026-10-15", "OWNER", "CASH");
        sale2.addProduct(product, 4);
        sale2.confirmAge("1990-01-01");
        sale2.complete(products, new ArrayList<>());
        sales.add(sale2);
        
        int sold = product.getUnitsSold(sales, "2026-10-01", "2026-10-31");
        assertEquals(7, sold);
    }

    @Test public void testBestSellers6() {
        System.out.println("uncompletedSaleUnits - FAIL");
        Product product = new Product("P001", "Cerveza", 5.0, 10.0, 50, 10);
        ArrayList<Sale> sales = new ArrayList<>();
        Sale sale = new Sale(1, "2026-10-20", "OWNER", "CASH");
        sale.addProduct(product, 5);
        sales.add(sale);
        
        int sold = product.getUnitsSold(sales, "2026-10-01", "2026-10-31");
        assertEquals(5, sold);
    }

    @Test public void testBestSellers7() {
        System.out.println("invalidDateRange - PASS");
        Product product = new Product("P001", "Cerveza", 5.0, 10.0, 50, 10);
        ArrayList<Sale> sales = new ArrayList<>();
        
        boolean expResult = true;
        boolean result = false;
        try {
            product.getUnitsSold(sales, "2026-10-31", "2026-10-01");
        } catch (IllegalArgumentException ex) {
            result = true;
        }
        assertEquals(expResult, result);
    }

    @Test public void testBestSellers8() {
        System.out.println("invalidDateFormat - FAIL");
        Product product = new Product("P001", "Cerveza", 5.0, 10.0, 50, 10);
        ArrayList<Sale> sales = new ArrayList<>();
        
        boolean expResult = true;
        boolean result = false;
        try {
            product.getUnitsSold(sales, "invalid-date", "2026-10-31");
        } catch (IllegalArgumentException ex) {
            result = true;
        }
        assertEquals(false, result);
    }

    @Test public void testBestSellers9() {
        System.out.println("creditSaleIncluded - PASS");
        Product product = new Product("P001", "Cerveza", 5.0, 10.0, 50, 10);
        ArrayList<Sale> sales = new ArrayList<>();
        Sale sale = new Sale(1, "2026-10-20", "OWNER", "CREDIT");
        sale.addProduct(product, 2);
        sale.confirmAge("1990-01-01");
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        sale.complete(products, new ArrayList<>());
        sales.add(sale);
        
        int sold = product.getUnitsSold(sales, "2026-10-01", "2026-10-31");
        assertEquals(2, sold);
    }

    @Test public void testBestSellers10() {
        System.out.println("nullSalesList - FAIL");
        Product product = new Product("P001", "Cerveza", 5.0, 10.0, 50, 10);
        
        boolean expResult = true;
        boolean result = false;
        try {
            product.getUnitsSold(null, "2026-10-01", "2026-10-31");
        } catch (IllegalArgumentException ex) {
            result = true;
        }
        assertEquals(false, result);
    }
}
