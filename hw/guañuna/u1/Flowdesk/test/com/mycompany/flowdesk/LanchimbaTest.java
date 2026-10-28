package com.mycompany.flowdesk;

import enums.OrderStatus;
import enums.PaymentMethod;
import enums.Role;
import java.util.Scanner;
import models.Category;
import models.OrderDetail;
import models.Payment;
import models.Product;
import models.Service;
import models.User;
import models.WorkOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.Date;


public class LanchimbaTest {

    public LanchimbaTest() {
    }

    @BeforeEach
    public void setUp() {
        User.getAll().clear();
        Category.getAll().clear();
        Product.getAll().clear();
        Service.getAll().clear();
        WorkOrder.getAll().clear();
    }

    @Test
    public void testLoginValidCredentials() {
        User user = new User("U1", "admin", "123", Role.MANAGER);
        String username = "admin";
        String password = "123";

        boolean expResult = true;
        boolean result = user.validateCredentials(username, password);

        assertEquals(expResult, result);
    }

    @Test
    public void testLoginIncorrectPassword() {
        User user = new User("U1", "admin", "123", Role.MANAGER);
        String username = "admin";
        String password = "wrong";

        boolean expResult = false;
        boolean result = user.validateCredentials(username, password);

        assertEquals(expResult, result);
    }

    @Test
    public void testLoginIncorrectUsername() {
        User user = new User("U1", "admin", "123", Role.MANAGER);
        String username = "unknown";
        String password = "123";

        boolean expResult = false;
        boolean result = user.validateCredentials(username, password);

        assertEquals(expResult, result);
    }

    @Test
    public void testLoginBothCredentialsIncorrect() {
        User user = new User("U1", "admin", "123", Role.MANAGER);
        String username = "unknown";
        String password = "wrong";

        boolean expResult = false;
        boolean result = user.validateCredentials(username, password);

        assertEquals(expResult, result);
    }

    @Test
    public void testLoginUsernameCaseSensitive() {
        User user = new User("U1", "admin", "123", Role.MANAGER);
        String username = "ADMIN";
        String password = "123";

        boolean expResult = false;
        boolean result = user.validateCredentials(username, password);

        assertEquals(expResult, result);
    }

    @Test
    public void testLoginPasswordCaseSensitive() {
        User user = new User("U1", "admin", "Pass123", Role.MANAGER);
        String username = "admin";
        String password = "pass123";

        boolean expResult = false;
        boolean result = user.validateCredentials(username, password);

        assertEquals(expResult, result);
    }

    @Test
    public void testFirstLoginRegistersManager() {
        Scanner scanner = new Scanner("manager1\nsecret1\n");

        int expResult = 1;
        User resultUser = User.authenticateFromConsole(Role.MANAGER, scanner);
        int result = User.getAll().size();

        assertEquals(expResult, result);
        assertSame(resultUser, User.getAll().get(0));
        assertEquals(Role.MANAGER, resultUser.getRole());
    }

    @Test
    public void testFirstLoginRegistersEmployee() {
        Scanner scanner = new Scanner("employee1\nsecret2\n");

        int expResult = 1;
        User resultUser = User.authenticateFromConsole(Role.EMPLOYEE, scanner);
        int result = User.getAll().size();

        assertEquals(expResult, result);
        assertSame(resultUser, User.getAll().get(0));
        assertEquals(Role.EMPLOYEE, resultUser.getRole());
    }

    @Test
    public void testExistingUserLoginSuccess() {
        User registeredUser = new User("U1", "cashier", "321", Role.CASHIER);
        User.getAll().add(registeredUser);
        Scanner scanner = new Scanner("cashier\n321\n");

        User expResult = registeredUser;
        User result = User.authenticateFromConsole(Role.CASHIER, scanner);

        assertSame(expResult, result);
    }

    @Test
    public void testExistingUserLoginFailure() {
        User registeredUser = new User("U1", "cashier", "321", Role.CASHIER);
        User.getAll().add(registeredUser);
        Scanner scanner = new Scanner("cashier\n999\n");

        User expResult = null;
        User result = User.authenticateFromConsole(Role.CASHIER, scanner);

        assertEquals(expResult, result);
    }

    @Test
    public void testCreateCategory() {
        Category category = new Category("C1", "PRINTING", "Printing items");
        category.create();

        int expResult = 1;
        int result = Category.getAll().size();

        assertEquals(expResult, result);
        assertSame(category, Category.getAll().get(0));
    }

    @Test
    public void testSearchExistingCategory() {
        Category category = new Category("C1", "PRINTING", "Printing items");
        category.create();

        Category expResult = category;
        Category result = Category.search("C1");

        assertSame(expResult, result);
    }

    @Test
    public void testSearchCategoryCaseInsensitive() {
        Category category = new Category("CAT-01", "PRINTING", "Printing items");
        category.create();

        Category expResult = category;
        Category result = Category.search("cat-01");

        assertSame(expResult, result);
    }

    @Test
    public void testSearchNonExistentCategory() {
        Category category = new Category("C1", "PRINTING", "Printing items");
        category.create();

        Category expResult = null;
        Category result = Category.search("C99");

        assertEquals(expResult, result);
    }

    @Test
    public void testRegisterProduct() {
        Category category = new Category("C1", "PRINTING", "Printing items");
        Product product = new Product("P1", "Custom Mug", "Ceramic mug", 5.50, category);
        product.register();

        int expResult = 1;
        int result = Product.getAll().size();

        assertEquals(expResult, result);
        assertSame(product, Product.getAll().get(0));
    }

    @Test
    public void testRegisterMultipleProducts() {
        Category category = new Category("C1", "PRINTING", "Printing items");
        Product product1 = new Product("P1", "Flyer", "A4", 10.00, category);
        Product product2 = new Product("P2", "Sticker", "Vinyl", 5.00, category);
        product1.register();
        product2.register();

        int expResult = 2;
        int result = Product.getAll().size();

        assertEquals(expResult, result);
        assertSame(product2, Product.getAll().get(1));
    }

    @Test
    public void testProductPrice() {
        Category category = new Category("C1", "PRINTING", "Printing items");
        Product product = new Product("P1", "Custom Mug", "Ceramic mug", 5.50, category);

        double expResult = 5.50;
        double result = product.getPrice();

        assertEquals(expResult, result, 0.001);
    }

    @Test
    public void testProductCategory() {
        Category category = new Category("C1", "PRINTING", "Printing items");
        Product product = new Product("P1", "Custom Mug", "Ceramic mug", 5.50, category);

        Category expResult = category;
        Category result = product.getCategory();

        assertSame(expResult, result);
    }

    @Test
    public void testRegisterService() {
        Category category = new Category("C2", "DESIGN", "Design services");
        Service service = new Service("S1", "Graphic Design", "Logo", 20.00, category);
        service.register();

        int expResult = 1;
        int result = Service.getAll().size();

        assertEquals(expResult, result);
        assertSame(service, Service.getAll().get(0));
    }

    @Test
    public void testServicePriceAndCategory() {
        Category category = new Category("C2", "DESIGN", "Design services");
        Service service = new Service("S1", "Graphic Design", "Logo", 20.00, category);

        double expResult = 20.00;
        double result = service.getPrice();

        assertEquals(expResult, result, 0.001);
        assertSame(category, service.getCategory());
    }

    @Test
    public void testNewWorkOrderInitialStatus() {
        WorkOrder order = new WorkOrder("WO-101", new Date(25, 10, 2026), OrderStatus.WAITING);

        OrderStatus expResult = OrderStatus.WAITING;
        OrderStatus result = order.getStatus();

        assertEquals(expResult, result);
    }

    @Test
    public void testCreateWorkOrder() {
        WorkOrder order = new WorkOrder("WO-102", new Date(25, 10, 2026), OrderStatus.WAITING);
        order.getDetails().add(new OrderDetail("D1", "Volante", 1, "A4", 5));
        order.create();

        int expResult = 1;
        int result = WorkOrder.getAll().size();

        assertEquals(expResult, result);
        assertSame(order, WorkOrder.getAll().get(0));
    }

    @Test
    public void testNewWorkOrderHasEmptyDetailsAndPayments() {
        WorkOrder order = new WorkOrder("WO-103", new Date(25, 10, 2026), OrderStatus.WAITING);

        int expResult = 0;
        int result = order.getDetails().size() + order.getPayments().size();

        assertEquals(expResult, result);
    }

    @Test
    public void testCalculateOrderDetailSubtotal() {
        OrderDetail detail = new OrderDetail("D1", "Flyer", 2, "A4", 10.00);

        double expResult = 20.00;
        double result = detail.calculateSubtotal();

        assertEquals(expResult, result, 0.001);
    }

    @Test
    public void testCalculateOrderTotalSingleDetail() {
        WorkOrder order = new WorkOrder("WO-104", new Date(25, 10, 2026), OrderStatus.WAITING);
        order.getDetails().add(new OrderDetail("D1", "Flyer", 3, "A4", 4.00));
        order.calculateTotals();

        double expResult = 12.00;
        double result = order.getTotal();

        assertEquals(expResult, result, 0.001);
    }

    @Test
    public void testCalculateOrderTotalMultipleDetails() {
        WorkOrder order = new WorkOrder("WO-105", new Date(25, 10, 2026), OrderStatus.WAITING);
        order.getDetails().add(new OrderDetail("D1", "Flyer", 2, "A4", 10.00));
        order.getDetails().add(new OrderDetail("D2", "Sticker", 1, "Vinyl", 5.50));
        order.calculateTotals();

        double expResult = 25.50;
        double result = order.getTotal();

        assertEquals(expResult, result, 0.001);
    }

    @Test
    public void testCalculateEmptyOrderTotal() {
        WorkOrder order = new WorkOrder("WO-106", new Date(25, 10, 2026), OrderStatus.WAITING);
        order.calculateTotals();

        double expResult = 0.00;
        double result = order.getTotal();

        assertEquals(expResult, result, 0.001);
    }

    @Test
    public void testChangeWorkOrderStatus() {
        WorkOrder order = new WorkOrder("WO-107", new Date(25, 10, 2026), OrderStatus.WAITING);
        order.changeStatus(OrderStatus.APPROVED);

        OrderStatus expResult = OrderStatus.APPROVED;
        OrderStatus result = order.getStatus();

        assertEquals(expResult, result);
    }

    @Test
    public void testPartialPaymentReducesBalance() {
        WorkOrder order = new WorkOrder(
                "WO-108",
                new Date(25, 10, 2026),
                OrderStatus.WAITING
        );

        order.getDetails().add(
                new OrderDetail("D1", "Banner", 1, "Vinyl", 50.00)
        );

        order.calculateTotals();

        Payment payment = new Payment(
                "PAY-1",
                20.00,
                new Date(25, 10, 2026),
                PaymentMethod.CASH
        );

        payment.setWorkOrder(order);
        payment.registerPayment();

        double expResult = 30.00;
        double result = order.getBalance();

        assertEquals(expResult, result, 0.001);
        assertEquals(1, order.getPayments().size());
    }

    @Test
    public void testFullPaymentClearsBalance() {
        WorkOrder order = new WorkOrder(
                "WO-109",
                new Date(25, 10, 2026),
                OrderStatus.WAITING
        );

        order.getDetails().add(
                new OrderDetail("D1", "Banner", 1, "Vinyl", 50.00)
        );

        order.calculateTotals();

        Payment payment = new Payment(
                "PAY-2",
                50.00,
                new Date(25, 10, 2026),
                PaymentMethod.TRANSFER
        );

        payment.setWorkOrder(order);
        payment.registerPayment();

        double expResult = 0.00;
        double result = order.getBalance();

        assertEquals(expResult, result, 0.001);
        assertEquals(50.00, order.calculateTotalPaid(), 0.001);
    }
}
