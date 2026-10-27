package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utils.ConsoleInput;
import utils.Validation;
import exceptions.ValidationException;

public class Service extends CatalogItem {

    private static List<Service> services = new ArrayList<>();

    public Service(String serviceId, String name, String description, double price, Category category) {
        super(serviceId, name, description, price, category);
        validate();
    }

    public Service() { super(); }

    @Override
    public String getItemType() {
        return "SERVICIO";
    }

    public static void setAll(List<Service> newItems) {
        services = newItems;
    }

    public void register() {
        validate();
        for (Service existing : services) {
            if (Validation.sameIdentifier(existing.getId(), this.id)) {
                throw new ValidationException("Ya existe un servicio con ese ID.");
            }
        }
        services.add(this);
    }

    public void validate() {
        validateCommon();
    }

    public static List<Service> getAll() {
        return services;
    }

    public static void registerFromConsole(Scanner scanner) {
        if (Category.getAll().isEmpty()) {
            System.out.println("Primero debe crear al menos una categoria.");
            return;
        }

        String id = ConsoleInput.readRequiredText(scanner, "ID Servicio: ");
        String name = ConsoleInput.readRequiredText(scanner, "Nombre: ");
        String description = ConsoleInput.readRequiredText(scanner, "Descripcion: ");
        double price = ConsoleInput.readAmount(scanner, "Precio: ");

        System.out.println("Seleccione Categoria:");
        for (Category c : Category.getAll()) System.out.println(c.toString());
        Category category = Category.search(ConsoleInput.readRequiredText(scanner, "ID Categoria: "));

        if (category != null) {
            Service s = new Service(id, name, description, price, category);
            s.register();
            System.out.println("Servicio registrado.");
        } else {
            System.out.println("Categoria no encontrada.");
        }
    }

    public static void listFromConsole() {
        System.out.println("\n--- SERVICIOS ---");
        if (services.isEmpty()) {
            System.out.println("No hay servicios registrados.");
        } else {
            for (Service s : services) {
                System.out.println("- [" + s.getId() + "] " + s.getName() + " | Cat: " + s.getCategory().getName() + " | $" + s.getPrice());
            }
        }
    }
}