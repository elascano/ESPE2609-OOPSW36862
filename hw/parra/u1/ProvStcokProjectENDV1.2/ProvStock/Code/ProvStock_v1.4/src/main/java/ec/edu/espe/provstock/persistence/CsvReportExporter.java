package ec.edu.espe.provstock.persistence;

import ec.edu.espe.provstock.model.InventoryReport;
import ec.edu.espe.provstock.model.Product;
import ec.edu.espe.provstock.model.Purchase;
import ec.edu.espe.provstock.model.PurchaseReport;
import ec.edu.espe.provstock.model.Sale;
import ec.edu.espe.provstock.model.SalesReport;
import ec.edu.espe.provstock.model.SlowMovingProductsReport;
import ec.edu.espe.provstock.utils.CsvWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * UC-20: writes the four reports as CSV files that can be opened with Excel.
 * The report classes only calculate their data; the CSV format is kept here,
 * so a change in the columns of a file changes only this class (SRP).
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class CsvReportExporter {

    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final String[] EMPTY_ROW = {};

    public void export(SalesReport report, String filePath) {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"REPORTE DE VENTAS"});
        rows.add(period(report.getStartDate(), report.getEndDate()));
        rows.add(EMPTY_ROW);
        rows.add(new String[]{"Numero", "Fecha", "Cliente", "Identificacion", "Vendedor", "Forma de pago",
            "Subtotal", "IVA", "Total", "Estado"});
        for (Sale sale : report.getSales()) {
            rows.add(new String[]{String.valueOf(sale.getNumber()), sale.getDateTime().format(DATE_TIME),
                sale.getCustomer().getName(), sale.getCustomer().getIdentificationNumber(),
                sale.getRegisteredBy().getUsername(), sale.getPaymentMethod(), CsvWriter.money(sale.getSubtotal()),
                CsvWriter.money(sale.getVatAmount()), CsvWriter.money(sale.getTotal()), sale.getStatus()});
        }
        rows.add(EMPTY_ROW);
        rows.add(new String[]{"Ventas registradas", String.valueOf(report.getSalesCount())});
        rows.add(new String[]{"Total vendido", CsvWriter.money(report.getTotalSold())});
        rows.add(new String[]{"IVA recaudado", CsvWriter.money(report.getVatCollected())});
        write(filePath, rows);
    }

    public void export(InventoryReport report, String filePath) {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"REPORTE DE INVENTARIO"});
        rows.add(EMPTY_ROW);
        rows.add(new String[]{"Codigo", "Producto", "Categoria", "Unidad", "Ubicacion", "Stock", "Stock minimo",
            "Costo", "Valor", "Stock bajo"});
        for (Product product : report.getProducts()) {
            rows.add(new String[]{product.getCode(), product.getName(), product.getCategory(),
                product.getUnitOfMeasure(), product.getLocation(), CsvWriter.money(product.getCurrentStock()),
                CsvWriter.money(product.getMinimumStock()), CsvWriter.money(product.getPurchaseCost()),
                CsvWriter.money(product.getStockValue()), product.isLowStock() ? "SI" : "NO"});
        }
        rows.add(EMPTY_ROW);
        rows.add(new String[]{"Productos", String.valueOf(report.getProducts().size())});
        rows.add(new String[]{"Valor total del stock", CsvWriter.money(report.getTotalStockValue())});
        write(filePath, rows);
    }

    public void export(PurchaseReport report, String filePath) {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"REPORTE DE COMPRAS POR PROVEEDOR"});
        rows.add(period(report.getStartDate(), report.getEndDate()));
        rows.add(EMPTY_ROW);
        rows.add(new String[]{"Numero", "Fecha", "Proveedor", "RUC", "Factura", "Registrada por", "Total"});
        for (Purchase purchase : report.getPurchases()) {
            rows.add(new String[]{String.valueOf(purchase.getNumber()), purchase.getDateTime().format(DATE_TIME),
                purchase.getSupplier().getName(), purchase.getSupplier().getRuc(),
                purchase.getSupplierInvoiceNumber(), purchase.getRegisteredBy().getUsername(),
                CsvWriter.money(purchase.getTotal())});
        }
        rows.add(EMPTY_ROW);
        rows.add(new String[]{"Compras", String.valueOf(report.getPurchases().size())});
        rows.add(new String[]{"Costo total", CsvWriter.money(report.getTotalCost())});
        write(filePath, rows);
    }

    public void export(SlowMovingProductsReport report, String filePath) {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"REPORTE DE PRODUCTOS SIN MOVIMIENTO"});
        rows.add(period(report.getStartDate(), report.getEndDate()));
        rows.add(EMPTY_ROW);
        rows.add(new String[]{"Codigo", "Producto", "Unidad", "Ubicacion", "Stock", "Valor"});
        List<Product> productsWithoutSales = report.getProductsWithoutSales();
        for (Product product : productsWithoutSales) {
            rows.add(new String[]{product.getCode(), product.getName(), product.getUnitOfMeasure(),
                product.getLocation(), CsvWriter.money(product.getCurrentStock()),
                CsvWriter.money(product.getStockValue())});
        }
        rows.add(EMPTY_ROW);
        rows.add(new String[]{"Productos sin ventas", String.valueOf(productsWithoutSales.size())});
        rows.add(new String[]{"Valor inmovilizado", CsvWriter.money(report.getImmobilizedValue())});
        write(filePath, rows);
    }

    private String[] period(Object startDate, Object endDate) {
        return new String[]{"Desde", String.valueOf(startDate), "Hasta", String.valueOf(endDate)};
    }

    private void write(String filePath, List<String[]> rows) {
        try {
            CsvWriter.write(filePath, rows);
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo crear el CSV: " + e.getMessage(), e);
        }
    }
}
