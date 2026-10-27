package data;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import models.*;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.net.URISyntaxException;
import exceptions.PersistenceException;
import exceptions.ValidationException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    
    private static final JsonSerializer<CatalogItem> itemSerializer = (src, typeOfSrc, context) -> {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        jsonObject.addProperty("type", src.getItemType());
        return jsonObject;
    };

    private static final JsonDeserializer<CatalogItem> itemDeserializer = (json, typeOfT, context) -> {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        if ("PRODUCTO".equals(type)) return context.deserialize(jsonObject, Product.class);
        if ("SERVICIO".equals(type)) return context.deserialize(jsonObject, Service.class);
        throw new JsonParseException("Tipo desconocido: " + type);
    };

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(CatalogItem.class, itemSerializer)
            .registerTypeAdapter(CatalogItem.class, itemDeserializer)
            .create();

    public static Path getDataDirectory() {
        String configuredDirectory = System.getProperty("flowdesk.dataDirectory");
        if (configuredDirectory != null && !configuredDirectory.isBlank()) {
            return Path.of(configuredDirectory).toAbsolutePath().normalize();
        }
        try {
            Path codeLocation = Path.of(DataManager.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI());
            Path projectDirectory = findProjectDirectory(codeLocation);
            if (projectDirectory != null) {
                return projectDirectory.resolve("datos_flowdesk");
            }
        } catch (URISyntaxException exception) {
            throw new PersistenceException("No se pudo localizar la carpeta de datos.", exception);
        }
        Path workingDirectory = Path.of(System.getProperty("user.dir")).toAbsolutePath().normalize();
        Path projectDirectory = findProjectDirectory(workingDirectory);
        return (projectDirectory == null ? workingDirectory : projectDirectory).resolve("datos_flowdesk");
    }

    private static Path findProjectDirectory(Path location) {
        Path current = Files.isDirectory(location) ? location : location.getParent();
        while (current != null) {
            if (Files.isRegularFile(current.resolve("pom.xml")) && Files.isDirectory(current.resolve("src"))) {
                return current;
            }
            Path nestedProject = current.resolve("06Code/Flowdesk");
            if (Files.isRegularFile(nestedProject.resolve("pom.xml"))) {
                return nestedProject;
            }
            current = current.getParent();
        }
        return null;
    }

    public static void init() {
        try {
            Files.createDirectories(getDataDirectory());
        } catch (IOException exception) {
            throw new PersistenceException("No se pudo crear la carpeta de datos.", exception);
        }
    }

    public static void saveUsers() {
        init();
        Path temporaryFile = null;
        try {
            Path directory = getDataDirectory();
            temporaryFile = Files.createTempFile(directory, "users-", ".tmp");
            try (Writer writer = Files.newBufferedWriter(temporaryFile, StandardCharsets.UTF_8)) {
                gson.toJson(User.getAll(), writer);
            }
            Path destination = directory.resolve("users.json");
            try {
                Files.move(temporaryFile, destination, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
            } catch (AtomicMoveNotSupportedException exception) {
                Files.move(temporaryFile, destination, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException exception) {
            throw new PersistenceException("No se pudieron guardar las cuentas. Intente nuevamente.", exception);
        } finally {
            if (temporaryFile != null) temporaryFile.toFile().delete();
        }
    }

    public static void loadUsers() {
        init();
        Path file = getDataDirectory().resolve("users.json");
        if (!Files.exists(file)) {
            User.setAll(new ArrayList<>());
            return;
        }
        ArrayList<User> previousUsers = new ArrayList<>(User.getAll());
        try (Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            Type usersType = new TypeToken<ArrayList<User>>(){}.getType();
            ArrayList<User> loadedUsers = gson.fromJson(reader, usersType);
            if (loadedUsers == null || loadedUsers.contains(null)) {
                throw new ValidationException("El archivo de cuentas no es valido.");
            }
            User.setAll(new ArrayList<>());
            for (User user : loadedUsers) {
                user.register();
            }
        } catch (IOException | JsonParseException | ValidationException exception) {
            User.setAll(previousUsers);
            throw new PersistenceException("No se pudieron cargar las cuentas. Revise el archivo de usuarios.", exception);
        }
    }

    public static <T> void saveList(List<T> items, String fileName) {
        init();
        try (Writer writer = new FileWriter(getDataDirectory().resolve(fileName + ".json").toFile(), StandardCharsets.UTF_8)) {
            gson.toJson(items, writer);
        } catch (IOException e) {
            throw new PersistenceException("Error al guardar " + fileName + ".json", e);
        }
    }

    public static <T> List<T> loadList(String fileName, Type listType) {
        init();
        File file = getDataDirectory().resolve(fileName + ".json").toFile();
        if (!file.exists()) return new ArrayList<>();
        try (Reader reader = new FileReader(file, StandardCharsets.UTF_8)) {
            List<T> items = gson.fromJson(reader, listType);
            return items != null ? items : new ArrayList<>();
        } catch (IOException | JsonParseException e) {
            throw new PersistenceException("Error crítico al cargar " + fileName + ".json. Evitando sobrescritura.", e);
        }
    }

    public static void saveAll() {
        saveUsers();
        saveList(Customer.getAll(), "customers");
        saveList(Category.getAll(), "categories");
        saveList(Product.getAll(), "products");
        saveList(Service.getAll(), "services");
        saveList(WorkOrder.getAll(), "orders");
        saveList(CashMovement.getAll(), "movements");
    }

    public static void loadAll() {
        loadUsers();
        Type customersType = new TypeToken<ArrayList<Customer>>(){}.getType();
        Customer.setAll(loadList("customers", customersType));

        Type categoriesType = new TypeToken<ArrayList<Category>>(){}.getType();
        Category.setAll(loadList("categories", categoriesType));

        Type productsType = new TypeToken<ArrayList<Product>>(){}.getType();
        Product.setAll(loadList("products", productsType));

        Type servicesType = new TypeToken<ArrayList<Service>>(){}.getType();
        Service.setAll(loadList("services", servicesType));

        Type ordersType = new TypeToken<ArrayList<WorkOrder>>(){}.getType();
        WorkOrder.setAll(loadList("orders", ordersType));

        Type movementsType = new TypeToken<ArrayList<CashMovement>>(){}.getType();
        CashMovement.setAll(loadList("movements", movementsType));
    }

    public static void saveCashRegister(CashRegister cashRegister) {
        init();
        try (Writer writer = new FileWriter(getDataDirectory().resolve("cash_register.json").toFile(), StandardCharsets.UTF_8)) {
            gson.toJson(cashRegister, writer);
        } catch (IOException e) {
            throw new PersistenceException("Error al guardar cash_register.json", e);
        }
    }

    public static CashRegister loadCashRegister() {
        init();
        File file = getDataDirectory().resolve("cash_register.json").toFile();
        if (!file.exists()) return new CashRegister();
        try (Reader reader = new FileReader(file, StandardCharsets.UTF_8)) {
            CashRegister cashRegister = gson.fromJson(reader, CashRegister.class);
            return cashRegister != null ? cashRegister : new CashRegister();
        } catch (IOException | JsonParseException e) {
            throw new PersistenceException("Error crítico al cargar cash_register.json. Evitando sobrescritura.", e);
        }
    }
}