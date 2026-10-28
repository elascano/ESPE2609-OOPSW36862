package models;

import utils.Validation;

public abstract class CatalogItem {
    protected String id;
    protected String name;
    protected String description;
    protected double price;
    protected Category category;

    public CatalogItem(String id, String name, String description, double price, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public CatalogItem() { }

    // Método polimórfico solicitado
    public abstract String getItemType();

    // Lógica de validación común
    protected void validateCommon() {
        Validation.requireText(id, "identificador");
        Validation.requireText(name, "nombre");
        Validation.requireText(description, "descripcion");
        Validation.requireNonNegativeAmount(price, "precio");
        Validation.requireValue(category, "categoria");
        category.validate();
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public Category getCategory() { return category; }

    // Setters necesarios para Gson (si los usas)
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
    public void setCategory(Category category) { this.category = category; }
}