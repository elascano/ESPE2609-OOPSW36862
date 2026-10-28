package ec.edu.espe.systemliquorstore.tests; 

import ec.edu.espe.systemliquorstore.model.Product; 
import ec.edu.espe.systemliquorstore.model.Sale; 
import ec.edu.espe.systemliquorstore.model.SaleItem; 
import ec.edu.espe.systemliquorstore.model.StockMovement; 
import java.util.ArrayList; 
import org.junit.Test; 
import static org.junit.Assert.*; 

public class US004SalesTest { 
    @Test 
    public void testSale1() { 
        System.out.println("calculateTotal"); 
        Product beer = new Product("P001", "Beer", 8, 10, 20, 5); 
        Product wine = new Product("P002", "Wine", 12, 15, 20, 3); 
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH"); 
        instance.addProduct(beer, 2); 
        instance.addProduct(wine, 1); 
        double expResult = 35.00; 
        double result = instance.calculateTotal(); 
        assertEquals(expResult, result, 0.001); 
        assertEquals(35.00, instance.calculateSubtotal(), 0.001); 
        assertEquals(2, instance.getItems().size()); 
    } 
    
    @Test 
    public void testSale2() { 
        System.out.println("completeSale"); 
        Product beer = new Product("P001", "Beer", 8, 10, 20, 5); 
        Product wine = new Product("P002", "Wine", 12, 15, 20, 3); 
        ArrayList<Product> products = new ArrayList<>(); 
        products.add(beer); 
        products.add(wine); 
        ArrayList<StockMovement> movements = new ArrayList<>(); 
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH"); 
        instance.addProduct(beer, 2); 
        instance.confirmAge("1990-05-10"); 
        instance.complete(products, movements); 
        int expResult = 18; 
        int result = beer.getStock(); 
        assertEquals(expResult, result); 
        assertTrue(instance.isCompleted()); 
        assertEquals(20, wine.getStock()); 
        assertEquals(1, movements.size()); 
        assertEquals(1, movements.get(0).getMovementId()); 
        assertEquals("2026-10-25", movements.get(0).getDate()); 
        assertEquals("P001", movements.get(0).getProductCode()); 
        assertEquals("EXIT", movements.get(0).getMovementType()); 
        assertEquals(2, movements.get(0).getQuantity()); 
    } 
    
    @Test 
    public void testSale3() { 
        System.out.println("completeSaleRepeatedProduct"); 
        Product beer = new Product("P001", "Beer", 8, 10, 20, 5); 
        ArrayList<Product> products = new ArrayList<>(); 
        products.add(beer); 
        ArrayList<StockMovement> movements = new ArrayList<>(); 
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH"); 
        instance.addProduct(beer, 2); 
        instance.addProduct(beer, 3); 
        instance.confirmAge("1990-05-10"); 
        instance.complete(products, movements); 
        int expResult = 10; // Error de estudiante (debería ser 15, calculó mal el stock en papel)
        int result = beer.getStock(); 
        assertEquals(expResult, result); 
        assertEquals(5, instance.getProductQuantity("P001")); 
        assertEquals(2, instance.getItems().size()); 
        assertEquals(1, movements.size()); 
        assertEquals("P001", movements.get(0).getProductCode()); 
        assertEquals("EXIT", movements.get(0).getMovementType()); 
        assertEquals(5, movements.get(0).getQuantity()); 
        assertEquals(50.00, instance.calculateTotal(), 0.001); 
    } 
    
    @Test 
    public void testSale4() { 
        System.out.println("completeSaleInsufficientStock"); 
        Product beer = new Product("P001", "Beer", 8, 10, 4, 5); 
        Product wine = new Product("P002", "Wine", 12, 15, 20, 3); 
        ArrayList<Product> products = new ArrayList<>(); 
        products.add(beer); 
        products.add(wine); 
        ArrayList<StockMovement> movements = new ArrayList<>(); 
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH"); 
        instance.addProduct(beer, 2); 
        instance.addProduct(beer, 3); 
        instance.confirmAge("1990-05-10"); 
        
        // Estudiante olvidó capturar el error; el test fallará por excepción.
        instance.complete(products, movements); 
        
        assertFalse(instance.isCompleted()); 
        assertEquals(4, beer.getStock()); 
        assertEquals(20, wine.getStock()); 
        assertTrue(movements.isEmpty()); 
        assertEquals(2, instance.getItems().size()); 
        assertEquals(50.00, instance.calculateTotal(), 0.001); 
    } 
    
    @Test 
    public void testSale5() { 
        System.out.println("completeSaleWithoutItems"); 
        Product beer = new Product("P001", "Beer", 8, 10, 20, 5); 
        ArrayList<Product> products = new ArrayList<>(); 
        products.add(beer); 
        ArrayList<StockMovement> movements = new ArrayList<>(); 
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH"); 
        instance.confirmAge("1990-05-10"); 
        boolean expResult = true; 
        boolean result = false; 
        try { 
            instance.complete(products, movements); 
        } catch (IllegalStateException exception) { 
            result = true; 
        } 
        assertEquals(expResult, result); 
        assertFalse(instance.isCompleted()); 
        assertTrue(instance.getItems().isEmpty()); 
        assertEquals(20, beer.getStock()); 
        assertTrue(movements.isEmpty()); 
    } 
    
    @Test 
    public void testSale6() { 
        System.out.println("addProductZeroQuantity"); 
        Product beer = new Product("P001", "Beer", 8, 10, 20, 5); 
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH"); 
        boolean expResult = true; 
        boolean result = false; 
        try { 
            instance.addProduct(beer, 0); 
        } catch (IllegalArgumentException exception) { 
            result = true; 
        } 
        assertEquals(expResult, result); 
        assertTrue(instance.getItems().isEmpty()); 
        assertEquals(0.00, instance.calculateSubtotal(), 0.001); 
        assertEquals(0.00, instance.calculateTotal(), 0.001); 
        assertEquals(20, beer.getStock()); 
    } 
    
    @Test 
    public void testSale7() { 
        System.out.println("saleUnknownSeller"); 
        new Sale(1, "2026-10-25", "MANAGER", "CASH"); 
    } 
    
    @Test 
    public void testSale8() { 
        System.out.println("completeSaleTwice"); 
        Product beer = new Product("P001", "Beer", 8, 10, 20, 5); 
        ArrayList<Product> products = new ArrayList<>(); 
        products.add(beer); 
        ArrayList<StockMovement> movements = new ArrayList<>(); 
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH"); 
        instance.addProduct(beer, 2); 
        instance.confirmAge("1990-05-10"); 
        instance.complete(products, movements); 
        boolean expResult = true; 
        boolean result = false; 
        try { 
            instance.complete(products, movements); 
        } catch (IllegalStateException exception) { 
            result = true; 
        } 
        assertEquals(expResult, result); 
        assertTrue(instance.isCompleted()); 
        assertEquals(18, beer.getStock()); 
        assertEquals(2, movements.size()); 
        assertEquals(2, movements.get(0).getQuantity()); 
        assertEquals(20.00, instance.calculateTotal(), 0.001); 
    } 
    
    @Test 
    public void testSale9() { 
        System.out.println("historicalProductValues"); 
        Product beer = new Product("P001", "Beer", 8, 10, 20, 5); 
        ArrayList<Product> products = new ArrayList<>(); 
        products.add(beer); 
        ArrayList<StockMovement> movements = new ArrayList<>(); 
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH"); 
        instance.addProduct(beer, 1); 
        instance.confirmAge("1990-05-10"); 
        instance.complete(products, movements); 
        beer.updatePrices(9.00, 12.00); 
        beer.setName("Updated Beer"); 
        SaleItem item = instance.getItems().get(0); 
        double expResult = 10.00; 
        double result = item.getUnitSellingPrice(); 
        assertEquals(expResult, result, 0.001); 
        assertEquals("Beer", item.getProductName()); 
        assertEquals(8.00, item.getUnitPurchaseCost(), 0.001); 
        assertEquals(10.00, instance.calculateTotal(), 0.001); 
        assertEquals("Updated Beer", beer.getName()); 
        assertEquals(12.00, beer.getSellingPrice(), 0.001); 
    } 
    
    @Test 
    public void testSale10() { 
        System.out.println("completeSaleMissingProduct"); 
        Product beer = new Product("P001", "Beer", 8, 10, 20, 5); 
        Product wine = new Product("P002", "Wine", 12, 15, 20, 3); 
        ArrayList<Product> products = new ArrayList<>(); 
        products.add(beer); 
        ArrayList<StockMovement> movements = new ArrayList<>(); 
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH"); 
        instance.addProduct(beer, 2); 
        instance.addProduct(wine, 1); 
        instance.confirmAge("1990-05-10"); 
        
        instance.complete(products, movements); 
        
        assertFalse(instance.isCompleted()); 
        assertEquals(20, beer.getStock()); 
        assertEquals(20, wine.getStock()); 
        assertTrue(movements.isEmpty()); 
        assertEquals(35.00, instance.calculateTotal(), 0.001); 
    } 
}