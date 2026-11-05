package ec.edu.espe.electromechanicalsystem.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Base class that reads and writes a list of records as a JSON array file.
 * Subclasses only describe how one record is converted to and from JSON.
 */
public abstract class JsonFileStore<T> {
    private final Path filePath;

    protected JsonFileStore(String fileName) {
        this.filePath = Path.of(fileName);
    }

    /** Converts one record to a JSON object (see {@link JsonUtil#object}). */
    protected abstract String toJson(T item);

    /** Builds a record from the text of a JSON object, or returns null if it is incomplete. */
    protected abstract T fromJson(String object);

    public List<T> load() {
        List<T> items = new ArrayList<>();
        if (!Files.exists(filePath)) {
            return items;
        }
        try {
            String json = Files.readString(filePath, StandardCharsets.UTF_8);
            for (String object : JsonUtil.splitObjects(json)) {
                T item = fromJson(object);
                if (item == null) {
                    System.out.println("A record in " + fileName() + " was skipped because it is incomplete or invalid.");
                } else {
                    items.add(item);
                }
            }
        } catch (IOException exception) {
            System.out.println("Could not load " + fileName() + ": " + exception.getMessage());
        }
        return items;
    }

    public void save(List<T> items) {
        String body = items.stream().map(this::toJson).collect(Collectors.joining(",\n"));
        String json = "[\n" + body + (items.isEmpty() ? "" : "\n") + "]\n";
        try {
            Path parent = filePath.toAbsolutePath().getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            Files.writeString(filePath, json, StandardCharsets.UTF_8);
        } catch (IOException exception) {
            System.out.println("Could not save " + fileName() + ": " + exception.getMessage());
        }
    }

    private String fileName() {
        return filePath.getFileName().toString();
    }
}
