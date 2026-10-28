package ec.edu.espe.systemliquorstore.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Jsonstorage {
    private final Path filePath;
    private final Gson gson;

    public Jsonstorage(String filePath) {
        this.filePath = Paths.get(filePath).toAbsolutePath().normalize();
        gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    }

    public boolean exists() {
        return !Files.notExists(filePath);
    }

    public String getFilePath() {
        return filePath.toString();
    }

    public void save(Storedata data) throws IOException {
        String json = encode(data);
        Files.createDirectories(filePath.getParent());
        Path temporary = Files.createTempFile(filePath.getParent(), "liquorstore-", ".tmp");
        try {
            Files.writeString(temporary, json, StandardCharsets.UTF_8);
            try {
                Files.move(temporary, filePath, StandardCopyOption.ATOMIC_MOVE,
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (AtomicMoveNotSupportedException exception) {
                Files.move(temporary, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
        } finally {
            Files.deleteIfExists(temporary);
        }
    }

    public Storedata load() throws IOException {
        return decode(Files.readString(filePath, StandardCharsets.UTF_8));
    }

    public Storedata copy(Storedata data) throws IOException {
        return decode(encode(data));
    }

    public String backupDamagedFile() throws IOException {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS"));
        int number = 1;
        while (true) {
            Path backup = Paths.get(filePath.toString() + "." + date + "-" + number + ".bak");
            try {
                Files.copy(filePath, backup);
                return backup.toString();
            } catch (FileAlreadyExistsException exception) {
                number++;
            }
        }
    }

    private String encode(Storedata data) throws IOException {
        validateData(data);
        try {
            String json = gson.toJson(data);
            validateSchema(readJson(json));
            return json;
        } catch (RuntimeException exception) {
            throw new IOException("No se pudieron preparar los datos para guardarlos.", exception);
        }
    }

    private Storedata decode(String json) throws IOException {
        try {
            JsonElement element = readJson(json);
            validateSchema(element);
            Storedata data = gson.fromJson(element, Storedata.class);
            validateData(data);
            return data;
        } catch (RuntimeException exception) {
            throw new IOException("El archivo contiene un formato o datos JSON no válidos.", exception);
        }
    }

    private void validateData(Storedata data) throws IOException {
        if (data == null) {
            throw new IOException("No existe un conjunto de datos para guardar o cargar.");
        }
        String error = data.getValidationError();
        if (!error.isEmpty()) {
            throw new IOException(error);
        }
    }

    private JsonElement readJson(String json) throws IOException {
        try (JsonReader reader = new JsonReader(new StringReader(json))) {
            reader.setLenient(false);
            JsonElement element = readValue(reader, 0);
            if (reader.peek() != JsonToken.END_DOCUMENT) {
                throw new IOException("El archivo contiene texto adicional después del objeto JSON.");
            }
            return element;
        }
    }

    private JsonElement readValue(JsonReader reader, int depth) throws IOException {
        if (depth > 32) {
            throw new IOException("El archivo contiene demasiados niveles de datos.");
        }
        switch (reader.peek()) {
            case BEGIN_OBJECT:
                JsonObject object = new JsonObject();
                reader.beginObject();
                while (reader.hasNext()) {
                    String name = reader.nextName();
                    if (object.has(name)) {
                        throw new IOException("El archivo repite el campo " + name + ".");
                    }
                    object.add(name, readValue(reader, depth + 1));
                }
                reader.endObject();
                return object;
            case BEGIN_ARRAY:
                JsonArray array = new JsonArray();
                reader.beginArray();
                while (reader.hasNext()) {
                    array.add(readValue(reader, depth + 1));
                }
                reader.endArray();
                return array;
            case STRING:
                return new JsonPrimitive(reader.nextString());
            case NUMBER:
                return JsonParser.parseString(reader.nextString());
            case BOOLEAN:
                return new JsonPrimitive(reader.nextBoolean());
            case NULL:
                reader.nextNull();
                return JsonNull.INSTANCE;
            default:
                throw new IOException("El archivo no contiene un valor JSON válido.");
        }
    }

    private void validateSchema(JsonElement element) throws IOException {
        JsonObject root = requireObject(element, "archivo principal");
        checkFields(root, "products:a,movements:a,suppliers:a,orders:a,sales:a,customers:a,payments:a,cashRecords:a");
        checkRecords(root, "products", "productCode:s,name:s,purchaseCost:n,sellingPrice:n,stock:i,minimumStock:i");
        checkRecords(root, "movements", "movementId:i,date:s,productCode:s,movementType:s,quantity:i");
        checkRecords(root, "suppliers", "supplierId:i,name:s,contact:s");
        checkRecords(root, "customers", "customerId:i,name:s");
        checkRecords(root, "payments", "paymentId:i,customerId:i,date:s,amount:n");
        checkRecords(root, "orders", "orderId:i,supplierId:i,orderDate:s,status:s,receivedDate:s,items:a");
        for (JsonElement order : root.getAsJsonArray("orders")) {
            checkRecords(order.getAsJsonObject(), "items", "productCode:s,quantity:i");
        }
        checkRecords(root, "sales", "saleId:i,date:s,seller:s,paymentType:s,customerId:i,items:a,subtotal:n,discountPercentage:n,discountAmount:n,totalAmount:n,ageVerified:b,completed:b");
        for (JsonElement sale : root.getAsJsonArray("sales")) {
            checkRecords(sale.getAsJsonObject(), "items", "productCode:s,productName:s,quantity:i,unitPurchaseCost:n,unitSellingPrice:n");
        }
        checkRecords(root, "cashRecords", "date:s,openingCash:n,countedCash:n,cashCountRecorded:b,expenses:a");
        for (JsonElement record : root.getAsJsonArray("cashRecords")) {
            checkRecords(record.getAsJsonObject(), "expenses", "description:s,amount:n");
        }
    }

    private JsonObject requireObject(JsonElement value, String name) throws IOException {
        if (value == null || !value.isJsonObject()) {
            throw new IOException("Se esperaba un objeto JSON en " + name + ".");
        }
        return value.getAsJsonObject();
    }

    private void checkRecords(JsonObject parent, String name, String fields) throws IOException {
        int index = 0;
        for (JsonElement record : parent.getAsJsonArray(name)) {
            checkFields(requireObject(record, name + "[" + index + "]"), fields);
            index++;
        }
    }

    private void checkFields(JsonObject object, String fields) throws IOException {
        String[] definitions = fields.split(",");
        if (object.size() != definitions.length) {
            throw new IOException("Faltan campos obligatorios o existen campos desconocidos en un registro.");
        }
        for (String definition : definitions) {
            String[] parts = definition.split(":");
            String name = parts[0];
            JsonElement value = object.get(name);
            if (value == null || value.isJsonNull()) {
                throw new IOException("Falta el campo obligatorio " + name + ".");
            }
            if (parts[1].equals("a")) {
                if (!value.isJsonArray()) {
                    throw new IOException("El campo " + name + " debe ser una lista.");
                }
            } else {
                checkPrimitive(value, name, parts[1]);
            }
        }
    }

    private void checkPrimitive(JsonElement value, String name, String type) throws IOException {
        if (!value.isJsonPrimitive()) {
            throw new IOException("El campo " + name + " tiene un tipo de dato incorrecto.");
        }
        JsonPrimitive primitive = value.getAsJsonPrimitive();
        boolean valid;
        switch (type) {
            case "s":
                valid = primitive.isString();
                break;
            case "b":
                valid = primitive.isBoolean();
                break;
            case "i":
                valid = isInteger(primitive);
                break;
            case "n":
                valid = primitive.isNumber() && Double.isFinite(primitive.getAsDouble());
                break;
            default:
                valid = false;
                break;
        }
        if (!valid) {
            throw new IOException("El campo " + name + " tiene un tipo de dato incorrecto.");
        }
    }

    private boolean isInteger(JsonPrimitive value) {
        if (!value.isNumber() || !value.getAsString().matches("-?(0|[1-9][0-9]*)")) {
            return false;
        }
        try {
            Integer.parseInt(value.getAsString());
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}