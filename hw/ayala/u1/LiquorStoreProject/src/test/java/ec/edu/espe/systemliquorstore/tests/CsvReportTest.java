package ec.edu.espe.systemliquorstore.tests;

import ec.edu.espe.systemliquorstore.model.*;
import ec.edu.espe.systemliquorstore.report.CsvReport;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import static org.junit.Assert.*;

public class CsvReportTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void inventoryEscapesCommasAndQuotes() throws IOException {
        StoreData data = new StoreData();
        data.getProducts().add(new Product("P1", "Ron, \"Especial\"", 5, 8, 2, 2));
        CsvReport report = new CsvReport(temporaryFolder.newFolder().toString());

        Path file = report.exportInventory(data);
        String csv = Files.readString(file, StandardCharsets.UTF_8);

        assertTrue(csv.contains("\"Ron, \"\"Especial\"\"\""));
        assertTrue(csv.contains("\"5.00\",\"8.00\""));
        assertTrue(csv.contains("\"Sí\""));
    }

    @Test
    public void salesReportFiltersDatesAndKeepsDiscountedTotal() throws IOException {
        StoreData data = new StoreData();
        Product product = new Product("P1", "Vino", 5, 10, 10, 2);
        data.getProducts().add(product);
        Sale sale = new Sale(1, "2026-10-25", "OWNER", "CASH");
        sale.addProduct(product, 2);
        sale.applyDiscount(10);
        sale.confirmAge("1990-01-01");
        sale.complete(data.getProducts(), data.getMovements());
        data.getSales().add(sale);
        CsvReport report = new CsvReport(temporaryFolder.newFolder().toString());

        Path file = report.exportSales(data, "2026-10-25", "2026-10-25");
        String csv = Files.readString(file, StandardCharsets.UTF_8);

        assertTrue(csv.contains("\"20.00\",\"2.00\",\"18.00\",\"8.00\""));
        assertTrue(csv.contains("\"Propietario\",\"Contado\""));
        report.exportSales(data, "2026-10-01", "2026-10-24");
        assertEquals(1, Files.readAllLines(file, StandardCharsets.UTF_8).size());
    }

    @Test
    public void invalidDateRangeIsRejected() throws IOException {
        CsvReport report = new CsvReport(temporaryFolder.newFolder().toString());

        assertThrows(IllegalArgumentException.class,
                () -> report.exportSales(new StoreData(), "2026-10-25", "2026-10-01"));
    }

    @Test
    public void emptyCustomersProduceOnlyHeader() throws IOException {
        CsvReport report = new CsvReport(temporaryFolder.newFolder().toString());

        Path file = report.exportCustomerBalances(new StoreData());

        assertEquals(1, Files.readAllLines(file, StandardCharsets.UTF_8).size());
    }
}
