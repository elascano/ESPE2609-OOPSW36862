package ec.edu.espe.systemliquorstore.tests;

import ec.edu.espe.systemliquorstore.model.CustomerPayment;
import ec.edu.espe.systemliquorstore.model.DailyCashRecord;
import ec.edu.espe.systemliquorstore.model.Product;
import ec.edu.espe.systemliquorstore.model.Sale;
import ec.edu.espe.systemliquorstore.model.StockMovement;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class US006DailyCashTest {

    private final ArrayList<CustomerPayment> noPayments = new ArrayList<>();

    private Sale completedCashSale(int saleId, String date, int quantity) {
        Product product = new Product("WHI01", "Whisky 750ml", 10.00, 15.00, 5, 2);
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        ArrayList<StockMovement> movements = new ArrayList<>();
        Sale sale = new Sale(saleId, date, "OWNER", "CASH");
        sale.addProduct(product, quantity);
        sale.confirmAge("1990-05-10");
        sale.complete(products, movements);
        return sale;
    }

    @Test
    public void testUS006Case01TotalExpensesAvoidsFloatingPointError() {
        DailyCashRecord record = new DailyCashRecord("2026-10-24", 100.00);
        record.addExpense("Bolsas", 0.10);
        record.addExpense("Limpieza", 0.20);

        assertEquals(0.30, record.getTotalExpenses(), 0.0);
    }

    @Test
    public void testUS006Case02AddExpenseRejectsNullDescription() {
        DailyCashRecord record = new DailyCashRecord("2026-10-24", 100.00);

        assertThrows(IllegalArgumentException.class, () -> record.addExpense(null, 5.00));
        assertTrue(record.getExpenses().isEmpty());
    }

    @Test
    public void testUS006Case03AddExpenseRejectsThreeDecimalAmount() {
        DailyCashRecord record = new DailyCashRecord("2026-10-24", 100.00);

        assertThrows(IllegalArgumentException.class, () -> record.addExpense("Bolsas", 0.001));
    }

    @Test
    public void testUS006Case04GetExpensesReturnsDefensiveCopy() {
        DailyCashRecord record = new DailyCashRecord("2026-10-24", 100.00);
        record.addExpense("Bolsas", 5.00);

        record.getExpenses().clear();

        assertEquals(1, record.getExpenses().size());
    }

    @Test
    public void testUS006Case05RecordCountAcceptsZero() {
        DailyCashRecord record = new DailyCashRecord("2026-10-24", 100.00);

        record.recordCount(0.00);

        assertTrue(record.isCashCountRecorded());
        assertEquals(0.00, record.getCountedCash(), 0.001);
    }

    @Test
    public void testUS006Case06DifferenceIsPositiveWhenCountIsHigher() {
        DailyCashRecord record = new DailyCashRecord("2026-10-24", 100.00);
        record.addExpense("Bolsas", 20.00);
        record.recordCount(90.00);

        assertEquals(10.00, record.calculateDifference(new ArrayList<Sale>(), noPayments), 0.001);
    }

    @Test
    public void testUS006Case07ExpectedCashRejectsNullSales() {
        DailyCashRecord record = new DailyCashRecord("2026-10-24", 100.00);

        assertThrows(IllegalArgumentException.class,
                () -> record.calculateExpectedCash(null, noPayments));
    }

    @Test
    public void testUS006Case08ExpectedCashIncludesCompletedCashSaleOfSameDate() {
        DailyCashRecord record = new DailyCashRecord("2026-10-24", 100.00);
        ArrayList<Sale> sales = new ArrayList<>();
        sales.add(completedCashSale(1, "2026-10-24", 2));

        assertEquals(130.00, record.calculateExpectedCash(sales, noPayments), 0.001);
    }

    @Test
    public void testUS006Case09ExpectedCashIgnoresSaleOfAnotherDate() {
        DailyCashRecord record = new DailyCashRecord("2026-10-24", 100.00);
        ArrayList<Sale> sales = new ArrayList<>();
        sales.add(completedCashSale(1, "2026-10-23", 2));

        assertEquals(100.00, record.calculateExpectedCash(sales, noPayments), 0.001);
    }

    @Test
    public void testUS006Case10ExpenseAboveAvailableCashIsRejected() {
        DailyCashRecord record = new DailyCashRecord("2026-10-24", 10.00);

        assertThrows(IllegalArgumentException.class, () -> record.addExpense("Compra", 50.00));
    }
}