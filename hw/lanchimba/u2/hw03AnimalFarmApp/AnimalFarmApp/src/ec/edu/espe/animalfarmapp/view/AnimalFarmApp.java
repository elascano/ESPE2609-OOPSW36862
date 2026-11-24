package ec.edu.espe.animalfarmapp.view;

import ec.edu.espe.animalfarmapp.model.Chicken;
import ec.edu.espe.animalfarmapp.model.Cow;
import ec.edu.espe.animalfarmapp.model.FarmAnimal;
import ec.edu.espe.animalfarmapp.model.Pig;
import ec.edu.espe.animalfarmapp.model.Sheep;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Main application view for Animal Farm App (Java).
 *
 * @author Lanchimba
 */
public class AnimalFarmApp {

    public static void main(String[] args) {
        List<FarmAnimal> farmAnimals = new ArrayList<>();

        farmAnimals.add(new Chicken(1, "Rhode Island Red", LocalDate.of(2025, 5, 10), false));
        farmAnimals.add(new Chicken(2, "Leghorn", LocalDate.of(2024, 11, 20), true));
        farmAnimals.add(new Cow(3, "Holstein", LocalDate.of(2022, 3, 15), true));
        farmAnimals.add(new Cow(4, "Jersey", LocalDate.of(2023, 8, 1), false));
        farmAnimals.add(new Pig(5, "Yorkshire", LocalDate.of(2025, 1, 12)));
        farmAnimals.add(new Pig(6, "Duroc", LocalDate.of(2024, 6, 30)));
        farmAnimals.add(new Sheep(7, "Merino", LocalDate.of(2023, 4, 18), LocalDate.of(2026, 5, 10)));
        farmAnimals.add(new Sheep(8, "Suffolk", LocalDate.of(2024, 9, 5), LocalDate.of(2026, 8, 22)));

        System.out.println("=== ESPE Animal Farm App (Java) ===");
        System.out.println("Total registered animals: " + farmAnimals.size());
        System.out.println("------------------------------------------------------------");

        for (FarmAnimal animal : farmAnimals) {
            System.out.println(animal);
            System.out.println("  -> Age in months: " + animal.getAgeInMonths() + " months");
        }
    }
}
