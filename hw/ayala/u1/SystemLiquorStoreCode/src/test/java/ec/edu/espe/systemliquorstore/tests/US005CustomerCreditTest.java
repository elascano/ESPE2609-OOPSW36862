package ec.edu.espe.systemliquorstore.tests;

import ec.edu.espe.systemliquorstore.model.Customer;
import ec.edu.espe.systemliquorstore.model.CustomerPayment;
import ec.edu.espe.systemliquorstore.model.Product;
import ec.edu.espe.systemliquorstore.model.Sale;
import ec.edu.espe.systemliquorstore.model.StockMovement;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class US005CustomerCreditTest {
    @Test
    public void testCredit1() {
        System.out.println("customerBalance1");
        Customer instance = new Customer(1, "Customer One");
        ArrayList<Sale> sales = new ArrayList<>();
        ArrayList<CustomerPayment> payments = new ArrayList<>();

        double expResult = 0.00;
        double result = instance.getOutstandingBalance(sales, payments);
        assertEquals(expResult, result, 0.001);
    }

    @Test
    public void testCredit2() {
        System.out.println("customerBalance2");
        Customer instance = new Customer(1, "Customer One");
        ArrayList<Sale> sales = new ArrayList<>();
        ArrayList<CustomerPayment> payments = new ArrayList<>();
        Product product = new Product("P001", "Beer", 8.00, 10.00, 20, 5);
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        ArrayList<StockMovement> movements = new ArrayList<>();
        Sale firstSale = instance.createCreditSale(1, "2026-10-25", "OWNER");
        firstSale.addProduct(product, 3);
        firstSale.confirmAge("1990-05-10");
        firstSale.complete(products, movements);
        sales.add(firstSale);
        Sale secondSale = instance.createCreditSale(2, "2026-10-25", "OWNER");
        secondSale.addProduct(product, 2);
        secondSale.confirmAge("1990-05-10");
        secondSale.complete(products, movements);
        sales.add(secondSale);
        Sale draft = instance.createCreditSale(3, "2026-10-25", "OWNER");
        draft.addProduct(product, 1);
        sales.add(draft);

        double expResult = 50.00;
        double result = instance.getOutstandingBalance(sales, payments);
        assertEquals(expResult, result, 0.001);
        assertEquals(false, draft.isCompleted());
        assertEquals(15, product.getStock());
    }

    @Test
    public void testCredit3() {
        System.out.println("customerBalance3");
        Customer instance = new Customer(1, "Customer One");
        ArrayList<Sale> sales = new ArrayList<>();
        ArrayList<CustomerPayment> payments = new ArrayList<>();
        Product product = new Product("P001", "Beer", 8.00, 10.00, 20, 5);
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        ArrayList<StockMovement> movements = new ArrayList<>();
        Sale cashSale = new Sale(1, "2026-10-25", "OWNER", "CASH");
        cashSale.assignCustomer(instance);
        cashSale.addProduct(product, 2);
        cashSale.confirmAge("1990-05-10");
        cashSale.complete(products, movements);
        sales.add(cashSale);

        double expResult = 0.00;
        double result = instance.getOutstandingBalance(sales, payments);
        assertEquals(expResult, result, 0.001);
        assertEquals(true, cashSale.isCompleted());
        assertEquals(1, cashSale.getCustomerId());
    }

    @Test
    public void testCredit4() {
        System.out.println("customerBalance4");
        Customer instance = new Customer(1, "Customer One");
        ArrayList<Sale> sales = new ArrayList<>();
        ArrayList<CustomerPayment> payments = new ArrayList<>();
        Product product = new Product("P001", "Beer", 8.00, 10.00, 20, 5);
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        ArrayList<StockMovement> movements = new ArrayList<>();
        Sale sale = instance.createCreditSale(1, "2026-10-25", "OWNER");
        sale.addProduct(product, 3);
        sale.confirmAge("1990-05-10");
        sale.complete(products, movements);
        sales.add(sale);
        CustomerPayment payment = instance.createPayment(1, "2026-10-25", 10.00);
        instance.registerPayment(payment, sales, payments);

        double expResult = 20.00;
        double result = instance.getOutstandingBalance(sales, payments);
        assertEquals(expResult, result, 0.001);
        assertEquals(1, payments.size());
        assertSame(payment, payments.get(0));
        assertEquals(10.00, payments.get(0).getAmount(), 0.001);
    }

    @Test
    public void testCredit5() {
        System.out.println("customerBalance5");
        Customer instance = new Customer(1, "Customer One");
        ArrayList<Sale> sales = new ArrayList<>();
        ArrayList<CustomerPayment> payments = new ArrayList<>();
        Product product = new Product("P001", "Beer", 8.00, 10.00, 20, 5);
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        ArrayList<StockMovement> movements = new ArrayList<>();
        Sale sale = instance.createCreditSale(1, "2026-10-25", "OWNER");
        sale.addProduct(product, 3);
        sale.confirmAge("1990-05-10");
        sale.complete(products, movements);
        sales.add(sale);
        CustomerPayment payment = instance.createPayment(1, "2026-10-25", 30.00);
        instance.registerPayment(payment, sales, payments);

        double expResult = 0.00;
        double result = instance.getOutstandingBalance(sales, payments);
        assertEquals(expResult, result, 0.001);
        assertEquals(1, payments.size());
    }

    @Test
    public void testCredit6() {
        System.out.println("customerBalance6");
        Customer instance = new Customer(1, "Customer One");
        ArrayList<Sale> sales = new ArrayList<>();
        ArrayList<CustomerPayment> payments = new ArrayList<>();
        Product product = new Product("P001", "Beer", 8.00, 10.00, 20, 5);
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        ArrayList<StockMovement> movements = new ArrayList<>();
        Sale sale = instance.createCreditSale(1, "2026-10-25", "OWNER");
        sale.addProduct(product, 3);
        sale.confirmAge("1990-05-10");
        sale.complete(products, movements);
        sales.add(sale);
        CustomerPayment payment = instance.createPayment(1, "2026-10-25", 30.01);
        boolean expResult = true;
        boolean result = false;

        try {
            instance.registerPayment(payment, sales, payments);
        } catch (IllegalArgumentException exception) {
            result = true;
        }

        assertEquals(expResult, result);
        assertEquals(30.00, instance.getOutstandingBalance(sales, payments), 0.001);
        assertEquals(0, payments.size());
        assertEquals(1, sales.size());
        assertEquals(17, product.getStock());
        assertEquals(1, movements.size());
    }

    @Test
    public void testCredit7() {
        System.out.println("customerBalance7");
        Customer instance = new Customer(1, "Customer One");
        ArrayList<Sale> sales = new ArrayList<>();
        ArrayList<CustomerPayment> payments = new ArrayList<>();
        boolean expResult = true;
        boolean result = false;

        try {
            instance.createPayment(1, "2026-10-25", 0.00);
        } catch (IllegalArgumentException exception) {
            result = true;
        }

        assertEquals(expResult, result);
        assertEquals(0, payments.size());
        assertEquals(0.00, instance.getOutstandingBalance(sales, payments), 0.001);
    }

    @Test
    public void testCredit8() {
        System.out.println("customerBalance8");
        Customer instance = new Customer(1, "Customer One");
        ArrayList<Sale> sales = new ArrayList<>();
        ArrayList<CustomerPayment> payments = new ArrayList<>();
        Product product = new Product("P001", "Beer", 8.00, 10.00, 20, 5);
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        ArrayList<StockMovement> movements = new ArrayList<>();
        Customer secondCustomer = new Customer(2, "Customer Two");
        Sale firstSale = instance.createCreditSale(1, "2026-10-25", "OWNER");
        firstSale.addProduct(product, 3);
        firstSale.confirmAge("1990-05-10");
        firstSale.complete(products, movements);
        sales.add(firstSale);
        Sale secondSale = secondCustomer.createCreditSale(2, "2026-10-25", "OWNER");
        secondSale.addProduct(product, 2);
        secondSale.confirmAge("1990-05-10");
        secondSale.complete(products, movements);
        sales.add(secondSale);
        CustomerPayment payment = secondCustomer.createPayment(1, "2026-10-25", 5.00);
        boolean expResult = true;
        boolean result = false;

        try {
            instance.registerPayment(payment, sales, payments);
        } catch (IllegalArgumentException exception) {
            result = true;
        }

        assertEquals(expResult, result);
        assertEquals(30.00, instance.getOutstandingBalance(sales, payments), 0.001);
        assertEquals(20.00, secondCustomer.getOutstandingBalance(sales, payments), 0.001);
        assertEquals(0, payments.size());
        assertEquals(2, sales.size());
        assertEquals(15, product.getStock());
        assertEquals(2, movements.size());
    }

    @Test
    public void testCredit9() {
        System.out.println("customerBalance9");
        Customer instance = new Customer(1, "Customer One");
        ArrayList<Sale> sales = new ArrayList<>();
        ArrayList<CustomerPayment> payments = new ArrayList<>();
        Product product = new Product("P001", "Beer", 8.00, 10.00, 20, 5);
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        ArrayList<StockMovement> movements = new ArrayList<>();
        Sale sale = instance.createCreditSale(1, "2026-10-25", "OWNER");
        sale.addProduct(product, 3);
        sale.confirmAge("1990-05-10");
        sale.complete(products, movements);
        sales.add(sale);
        CustomerPayment firstPayment = instance.createPayment(1, "2026-10-25", 5.00);
        instance.registerPayment(firstPayment, sales, payments);
        CustomerPayment secondPayment = instance.createPayment(1, "2026-10-25", 4.00);
        boolean expResult = true;
        boolean result = false;

        try {
            instance.registerPayment(secondPayment, sales, payments);
        } catch (IllegalArgumentException exception) {
            result = true;
        }

        assertEquals(expResult, result);
        assertEquals(25.00, instance.getOutstandingBalance(sales, payments), 0.001);
        assertEquals(1, payments.size());
        assertSame(firstPayment, payments.get(0));
        assertEquals(5.00, payments.get(0).getAmount(), 0.001);
    }

    @Test
    public void testCredit10() {
        System.out.println("customerBalance10");
        Customer instance = new Customer(1, "Customer One");
        ArrayList<Sale> sales = new ArrayList<>();
        ArrayList<CustomerPayment> payments = new ArrayList<>();
        Product product = new Product("P001", "Beer", 8.00, 10.00, 20, 5);
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        ArrayList<StockMovement> movements = new ArrayList<>();
        Customer secondCustomer = new Customer(2, "Customer Two");
        Sale firstSale = instance.createCreditSale(1, "2026-10-25", "OWNER");
        firstSale.addProduct(product, 3);
        firstSale.confirmAge("1990-05-10");
        firstSale.complete(products, movements);
        sales.add(firstSale);
        Sale secondSale = secondCustomer.createCreditSale(2, "2026-10-25", "OWNER");
        secondSale.addProduct(product, 2);
        secondSale.confirmAge("1990-05-10");
        secondSale.complete(products, movements);
        sales.add(secondSale);
        CustomerPayment firstPayment = instance.createPayment(1, "2026-10-25", 5.00);
        CustomerPayment secondPayment = secondCustomer.createPayment(2, "2026-10-25", 7.00);
        instance.registerPayment(firstPayment, sales, payments);
        secondCustomer.registerPayment(secondPayment, sales, payments);

        double expResult = 25.00;
        double result = instance.getOutstandingBalance(sales, payments);
        assertEquals(expResult, result, 0.001);
        double secondExpResult = 13.00;
        double secondResult = secondCustomer.getOutstandingBalance(sales, payments);
        assertEquals(secondExpResult, secondResult, 0.001);
        assertEquals(2, payments.size());
        assertEquals(1, payments.get(0).getCustomerId());
        assertEquals(2, payments.get(1).getCustomerId());
    }

}
