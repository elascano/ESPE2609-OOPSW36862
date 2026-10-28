package ec.edu.espe.systemliquorstore.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ec.edu.espe.systemliquorstore.model.StoreData;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class JsonStorage {
    private final Path directory;
    private final Gson gson;
    private static final String[] FILE_NAMES = {
        "products.json", "stock_movements.json", "suppliers.json", "purchase_orders.json",
        "sales.json", "customers.json", "customer_payments.json", "cash_records.json"
    };
    private static final String[] LIST_NAMES = {
        "productos", "movimientos", "proveedores", "pedidos", "ventas", "clientes", "pagos", "cajas"
    };

    public JsonStorage(String directory) {
        this.directory = Paths.get(directory).toAbsolutePath().normalize();
        gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    }

    public boolean exists() {
        for (String name : FILE_NAMES) {
            if (Files.exists(directory.resolve(name))) {
                return true;
            }
        }
        return false;
    }

    public String getFilePath() {
        return directory.toString();
    }

    public StoreData load() throws IOException {
        JsonObject root = new JsonObject();
        try {
            for (int i = 0; i < FILE_NAMES.length; i++) {
                String json = Files.readString(directory.resolve(FILE_NAMES[i]), StandardCharsets.UTF_8);
                JsonElement list = JsonParser.parseString(json);
                if (!list.isJsonArray()) {
                    throw new IOException("Se esperaba una lista en " + FILE_NAMES[i] + ".");
                }
                root.add(LIST_NAMES[i], list);
            }
            translateValues(root, false);
            StoreData data = gson.fromJson(root, StoreData.class);
            validateData(data);
            return data;
        } catch (IOException exception) {
            throw new IOException("No se pudieron leer los archivos JSON: " + exception.getMessage(), exception);
        } catch (RuntimeException exception) {
            throw new IOException("Los archivos JSON contienen datos o un formato incorrecto.", exception);
        }
    }

    public void save(StoreData data) throws IOException {
        validateData(data);
        JsonObject root = gson.toJsonTree(data).getAsJsonObject();
        translateValues(root, true);
        Path[] temporaryFiles = new Path[FILE_NAMES.length];
        String[] previousContents = new String[FILE_NAMES.length];
        int replaced = 0;
        try {
            Files.createDirectories(directory);
            // Prepare all files before replacing the saved information.
            for (int i = 0; i < FILE_NAMES.length; i++) {
                Path file = directory.resolve(FILE_NAMES[i]);
                if (Files.exists(file)) {
                    previousContents[i] = Files.readString(file, StandardCharsets.UTF_8);
                }
                temporaryFiles[i] = Files.createTempFile(directory, "store-", ".tmp");
                Files.writeString(temporaryFiles[i], gson.toJson(root.get(LIST_NAMES[i])) + "\n",
                        StandardCharsets.UTF_8);
            }
            for (int i = 0; i < FILE_NAMES.length; i++) {
                Files.move(temporaryFiles[i], directory.resolve(FILE_NAMES[i]), StandardCopyOption.REPLACE_EXISTING);
                replaced++;
            }
        } catch (IOException exception) {
            for (int i = 0; i < replaced; i++) {
                try {
                    Path file = directory.resolve(FILE_NAMES[i]);
                    if (previousContents[i] == null) {
                        Files.deleteIfExists(file);
                    } else {
                        Files.writeString(file, previousContents[i], StandardCharsets.UTF_8);
                    }
                } catch (IOException restoreException) {
                    exception.addSuppressed(restoreException);
                }
            }
            throw new IOException("No se pudieron guardar los archivos JSON. Revise la carpeta de datos.", exception);
        } finally {
            for (Path file : temporaryFiles) {
                if (file != null) {
                    try {
                        Files.deleteIfExists(file);
                    } catch (IOException exception) {
                        // A temporary file never replaces a data file on startup.
                    }
                }
            }
        }
    }

    public StoreData copy(StoreData data) throws IOException {
        validateData(data);
        return gson.fromJson(gson.toJson(data), StoreData.class);
    }

    private void validateData(StoreData data) throws IOException {
        if (data == null) {
            throw new IOException("No hay datos para guardar.");
        }
        String error = data.getValidationError();
        if (!error.isEmpty()) {
            throw new IOException(error);
        }
    }

    // Translate system labels; names are kept exactly as the owner entered them.
    private void translateValues(JsonElement element, boolean toSpanish) {
        if (element.isJsonArray()) {
            for (JsonElement item : element.getAsJsonArray()) {
                translateValues(item, toSpanish);
            }
        } else if (element.isJsonObject()) {
            JsonObject object = element.getAsJsonObject();
            String[] fields = {"tipoMovimiento", "estado", "vendedor", "tipoPago"};
            String[] english = {"ENTRY", "EXIT", "PENDING", "RECEIVED", "OWNER", "EMPLOYEE", "CASH", "CREDIT"};
            String[] spanish = {"ENTRADA", "SALIDA", "PENDIENTE", "RECIBIDO", "PROPIETARIO", "EMPLEADO", "CONTADO", "CREDITO"};
            for (String field : fields) {
                if (object.has(field) && object.get(field).isJsonPrimitive()) {
                    String value = object.get(field).getAsString();
                    for (int i = 0; i < english.length; i++) {
                        if (value.equals(toSpanish ? english[i] : spanish[i])) {
                            object.addProperty(field, toSpanish ? spanish[i] : english[i]);
                            break;
                        }
                    }
                }
            }
            for (JsonElement value : object.asMap().values()) {
                translateValues(value, toSpanish);
            }
        }
    }
}