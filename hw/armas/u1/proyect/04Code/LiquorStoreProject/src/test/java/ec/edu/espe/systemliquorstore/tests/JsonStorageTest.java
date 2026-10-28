package ec.edu.espe.systemliquorstore.tests;

import ec.edu.espe.systemliquorstore.model.*;
import ec.edu.espe.systemliquorstore.persistence.JsonStorage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import static org.junit.Assert.*;

public class JsonStorageTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private StoreData createData() {
        StoreData data = new StoreData();
        data.getProducts().add(new Product("P1", "Ron añejo", 5, 8, 10, 2));
        data.getSuppliers().add(new Supplier(1, "Distribuidora", "0991234567"));
        data.getCustomers().add(new Customer(1, "María"));
        data.getMovements().add(new StockMovement(1, "2026-10-25", "P1", "ENTRY", 10));
        return data;
    }

    @Test
    public void saveAndLoadSeparateFiles() throws IOException {
        Path directory = temporaryFolder.newFolder().toPath();
        JsonStorage storage = new JsonStorage(directory.toString());

        storage.save(createData());
        StoreData result = storage.load();

        assertEquals("Ron añejo", result.getProducts().get(0).getName());
        assertEquals("María", result.getCustomers().get(0).getName());
        assertEquals("ENTRY", result.getMovements().get(0).getMovementType());
        assertEquals(1, result.getSuppliers().size());
        assertTrue(Files.exists(directory.resolve("cash_records.json")));
        assertTrue(Files.exists(directory.resolve("purchase_orders.json")));
        String json = Files.readString(directory.resolve("stock_movements.json"), StandardCharsets.UTF_8);
        assertTrue(json.contains("tipoMovimiento"));
        assertTrue(json.contains("ENTRADA"));
    }

    @Test
    public void brokenJsonIsReportedAndNotOverwritten() throws IOException {
        Path directory = temporaryFolder.newFolder().toPath();
        JsonStorage storage = new JsonStorage(directory.toString());
        storage.save(createData());
        Path file = directory.resolve("customers.json");
        Files.writeString(file, "{ broken", StandardCharsets.UTF_8);

        assertThrows(IOException.class, () -> storage.load());
        assertEquals("{ broken", Files.readString(file, StandardCharsets.UTF_8));
    }

    @Test
    public void missingFileIsReported() throws IOException {
        Path directory = temporaryFolder.newFolder().toPath();
        JsonStorage storage = new JsonStorage(directory.toString());
        storage.save(createData());
        Files.delete(directory.resolve("customers.json"));

        assertThrows(IOException.class, () -> storage.load());
    }

    @Test
    public void invalidDataDoesNotReplaceSavedData() throws IOException {
        Path directory = temporaryFolder.newFolder().toPath();
        JsonStorage storage = new JsonStorage(directory.toString());
        StoreData data = createData();
        storage.save(data);
        data.getCustomers().add(new Customer(1, "Repetido"));

        assertThrows(IOException.class, () -> storage.save(data));
        assertEquals(1, storage.load().getCustomers().size());
    }

    @Test
    public void saveFailureKeepsPreviousFiles() throws IOException {
        Path directory = temporaryFolder.newFolder().toPath();
        JsonStorage storage = new JsonStorage(directory.toString());
        StoreData data = createData();
        storage.save(data);
        Path productsFile = directory.resolve("products.json");
        String original = Files.readString(productsFile, StandardCharsets.UTF_8);
        Files.delete(directory.resolve("cash_records.json"));
        Files.createDirectory(directory.resolve("cash_records.json"));
        data.getProducts().get(0).setName("Nombre cambiado");

        assertThrows(IOException.class, () -> storage.save(data));
        assertEquals(original, Files.readString(productsFile, StandardCharsets.UTF_8));
    }
}
