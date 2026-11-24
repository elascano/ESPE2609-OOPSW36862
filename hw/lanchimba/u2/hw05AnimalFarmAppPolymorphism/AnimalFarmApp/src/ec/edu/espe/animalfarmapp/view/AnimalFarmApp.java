package ec.edu.espe.animalfarmapp.view;

import ec.edu.espe.animalfarmapp.model.Chicken;
import ec.edu.espe.animalfarmapp.model.Cow;
import ec.edu.espe.animalfarmapp.model.FarmAnimal;
import ec.edu.espe.animalfarmapp.model.IFarmAnimal;
import ec.edu.espe.animalfarmapp.model.Pig;
import ec.edu.espe.animalfarmapp.model.Sheep;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Main application view demonstrating Polymorphism (Overriding, Overloading,
 * and Subtyping via Interface and Abstract Class) in Java.
 *
 * @author Lanchimba
 */
public class AnimalFarmApp {

    public static void main(String[] args) {
        /*
         * Substitutability Principle (Liskov Substitution):
         * A Chicken IS-A FarmAnimal, so a Chicken instance can be added to List<FarmAnimal>.
         * Conversely, a FarmAnimal is NOT guaranteed to be a Chicken, so a FarmAnimal
         * cannot be assigned to List<Chicken> (nor is List<FarmAnimal> assignable to List<Chicken>).
         * Example of invalid assignment:
         *   List<Chicken> chickens = new ArrayList<>();
         *   FarmAnimal anyAnimal = new Cow(99, "Holstein");
         *   chickens.add(anyAnimal); // Compilation error: incompatible types
         */
        List<FarmAnimal> farmAnimals = new ArrayList<>();

        farmAnimals.add(new Chicken(1, "Rhode Island Red", LocalDate.of(2025, 5, 10), false));
        farmAnimals.add(new Chicken(2, "Leghorn", LocalDate.of(2024, 11, 20), true));
        farmAnimals.add(new Cow(3, "Holstein", LocalDate.of(2022, 3, 15), true));
        farmAnimals.add(new Cow(4, "Jersey", LocalDate.of(2023, 8, 1), false));
        farmAnimals.add(new Pig(5, "Yorkshire", LocalDate.of(2025, 1, 12)));
        // Overloaded constructor with minimal parameters (bornOn defaults to today):
        farmAnimals.add(new Pig(6, "Duroc"));
        farmAnimals.add(new Sheep(7, "Merino", LocalDate.of(2023, 4, 18), LocalDate.of(2026, 5, 10)));
        farmAnimals.add(new Sheep(8, "Suffolk", LocalDate.of(2024, 9, 5), LocalDate.of(2026, 8, 22)));

        System.out.println("=== ESPE Animal Farm App - Polymorphism (Java) ===");
        System.out.println("Total registered animals: " + farmAnimals.size());
        System.out.println("------------------------------------------------------------");

        float totalMonthlyCost = 0.0f;

        // Dynamic polymorphism: each concrete subclass executes its own overridden
        // methods at runtime with zero conditionals (no if / instanceof).
        for (FarmAnimal animal : farmAnimals) {
            float cost = animal.computeFoodCost();
            totalMonthlyCost += cost;

            System.out.println(animal);
            System.out.println("  -> Age: " + animal.getAgeInMonths() + " months");
            System.out.println("  -> Feed (default): " + animal.feed());
            System.out.println("  -> Feed (overloaded): " + animal.feed("balanced farm mix", 2.5f));
            System.out.println("  -> Production: " + animal.produce());
            System.out.printf(Locale.US, "  -> Monthly food cost: $%.2f%n", cost);
        }

        System.out.println("------------------------------------------------------------");
        System.out.printf(Locale.US, "Total monthly farm food cost: $%.2f%n", totalMonthlyCost);

        // Interface-based polymorphism using an IFarmAnimal reference:
        IFarmAnimal interfaceRef = farmAnimals.get(2);
        System.out.println("\n=== Interface Reference (IFarmAnimal) ===");
        System.out.println("Feed: " + interfaceRef.feed());
        System.out.println("Production: " + interfaceRef.produce());
        System.out.printf(Locale.US, "Monthly food cost: $%.2f%n", interfaceRef.computeFoodCost());
    }
}
