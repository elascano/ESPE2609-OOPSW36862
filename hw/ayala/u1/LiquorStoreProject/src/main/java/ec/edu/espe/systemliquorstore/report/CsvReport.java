package ec.edu.espe.systemliquorstore.report;

import ec.edu.espe.systemliquorstore.model.*;
import ec.edu.espe.systemliquorstore.util.Validation;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

public class CsvReport {
    private final Path directory;

    public CsvReport(String directory) {
        this.directory = Paths.get(directory).toAbsolutePath().normalize();
    }

    public Path exportInventory(StoreData data) throws IOException {
        Files.createDirectories(directory);
        Path file = directory.resolve("inventory.csv");
        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            writer.write('\uFEFF');
            writeRow(writer, "Código", "Producto", "Costo de compra", "Precio de venta", "Existencias", "Mínimo", "Stock bajo");
            for (Product product : data.getProducts()) {
                writeRow(writer, product.getProductCode(), product.getName(), money(product.getPurchaseCost()),
                        money(product.getSellingPrice()), String.valueOf(product.getStock()),
                        String.valueOf(product.getMinimumStock()), product.isLowStock() ? "Sí" : "No");
            }
        }
        return file;
    }

    public Path exportSales(StoreData data, String start, String end) throws IOException {
        if (!Validation.isValidDate(start) || !Validation.isValidDate(end) || start.compareTo(end) > 0) {
            throw new IllegalArgumentException("Revise las fechas del reporte.");
        }
        Files.createDirectories(directory);
        Path file = directory.resolve("sales.csv");
        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            writer.write('\uFEFF');
            writeRow(writer, "Venta", "Fecha", "Vendedor", "Forma de pago", "Cliente", "Subtotal", "Descuento", "Total", "Ganancia bruta");
            for (Sale sale : data.getSales()) {
                if (sale.isCompleted() && sale.getDate().compareTo(start) >= 0 && sale.getDate().compareTo(end) <= 0) {
                    String customerName = "Consumidor final";
                    for (Customer customer : data.getCustomers()) {
                        if (customer.getCustomerId() == sale.getCustomerId()) {
                            customerName = customer.getName();
                        }
                    }
                    writeRow(writer, String.valueOf(sale.getSaleId()), sale.getDate(),
                            "OWNER".equals(sale.getSeller()) ? "Propietario" : "Empleado",
                            "CASH".equals(sale.getPaymentType()) ? "Contado" : "Crédito", customerName,
                            money(sale.getSubtotal()), money(sale.getDiscountAmount()), money(sale.getTotalAmount()),
                            money(sale.calculateGrossProfit()));
                }
            }
        }
        return file;
    }

    public Path exportCustomerBalances(StoreData data) throws IOException {
        Files.createDirectories(directory);
        Path file = directory.resolve("customer_balances.csv");
        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            writer.write('\uFEFF');
            writeRow(writer, "Cliente", "Nombre", "Saldo pendiente");
            for (Customer customer : data.getCustomers()) {
                writeRow(writer, String.valueOf(customer.getCustomerId()), customer.getName(),
                        money(customer.getOutstandingBalance(data.getSales(), data.getPayments())));
            }
        }
        return file;
    }

    private String money(double amount) {
        return String.format(Locale.US, "%.2f", amount);
    }

    private void writeRow(BufferedWriter writer, String... values) throws IOException {
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                writer.write(",");
            }
            writer.write("\"" + values[i].replace("\"", "\"\"") + "\"");
        }
        writer.newLine();
    }
}
