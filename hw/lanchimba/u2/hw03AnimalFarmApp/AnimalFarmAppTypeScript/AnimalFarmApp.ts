import { Chicken } from "./model/Chicken";
import { Cow } from "./model/Cow";
import { FarmAnimal } from "./model/FarmAnimal";
import { Pig } from "./model/Pig";
import { Sheep } from "./model/Sheep";

/**
 * Main application view for Animal Farm App (TypeScript).
 */
function main(): void {
  const farmAnimals: FarmAnimal[] = [
    new Chicken(1, "Rhode Island Red", new Date(2025, 4, 10), false),
    new Chicken(2, "Leghorn", new Date(2024, 10, 20), true),
    new Cow(3, "Holstein", new Date(2022, 2, 15), true),
    new Cow(4, "Jersey", new Date(2023, 7, 1), false),
    new Pig(5, "Yorkshire", new Date(2025, 0, 12)),
    new Pig(6, "Duroc", new Date(2024, 5, 30)),
    new Sheep(7, "Merino", new Date(2023, 3, 18), new Date(2026, 4, 10)),
    new Sheep(8, "Suffolk", new Date(2024, 8, 5), new Date(2026, 7, 22)),
  ];

  console.log("=== ESPE Animal Farm App (TypeScript) ===");
  console.log(`Total registered animals: ${farmAnimals.length}`);
  console.log("------------------------------------------------------------");

  for (const animal of farmAnimals) {
    console.log(animal.toString());
    console.log(`  -> Age in months: ${animal.getAgeInMonths()} months`);
  }
}

main();
