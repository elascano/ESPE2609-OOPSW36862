package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utils.ConsoleInput;
import utils.Validation;
import exceptions.ValidationException;

public class Category {
    private String categoryId;
    private String name;
    private String description;

    private static List<Category> categories = new ArrayList<>();

    public Category(String categoryId, String name, String description) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        validate();
    }

    public Category() { }

    public static void setAll(List<Category> newItems) {
        categories = newItems;
    }

    public void create() {
        validate();
        for (Category existing : categories) {
            if (Validation.sameIdentifier(existing.categoryId, categoryId)) {
                throw new ValidationException("Ya existe una categoria con ese ID.");
            }
        }
        categories.add(this);
    }

    void validate() {
        Validation.requireText(categoryId, "identificador de categoria");
        Validation.requireText(name, "nombre de categoria");
        Validation.requireText(description, "descripcion");
    }

    public static List<Category> getAll() {
        return categories;
    }

    public static Category search(String id) {
        for (Category c : categories) {
            if (Validation.sameIdentifier(c.getCategoryId(), id)) return c;
        }
        return null;
    }

    public static void registerFromConsole(Scanner scanner) {
        String id = ConsoleInput.readRequiredText(scanner, "ID Categoria: ");
        String name = ConsoleInput.readRequiredText(scanner, "Nombre Categoria: ");
        String description = ConsoleInput.readRequiredText(scanner, "Descripcion: ");

        Category newCategory = new Category(id, name, description);
        newCategory.create();
        System.out.println("Categoria creada exitosamente.");
    }

    public String getCategoryId() { return categoryId; }
    public String getName() { return name; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return "[" + categoryId + "] " + name;
    }
}
