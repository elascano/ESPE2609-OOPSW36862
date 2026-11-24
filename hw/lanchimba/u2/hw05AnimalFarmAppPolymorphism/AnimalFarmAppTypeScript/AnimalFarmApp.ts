import { Chicken } from "./model/Chicken";
import { Cow } from "./model/Cow";
import { FarmAnimal } from "./model/FarmAnimal";
import { IFarmAnimal } from "./model/IFarmAnimal";
import { Pig } from "./model/Pig";
import { Sheep } from "./model/Sheep";

/**
 * Main application view demonstrating Polymorphism (Overriding, Overloading,
 * and Subtyping via Interface and Abstract Class) in TypeScript.
 */
function main(): void {
  /*
   * Substitutability Principle (Liskov Substitution):
   * A Chicken IS-A FarmAnimal, so a Chicken instance fits inside FarmAnimal[].
   * Conversely, a FarmAnimal is NOT guaranteed to be a Chicken, so a FarmAnimal
   * cannot be assigned to Chicken[] (TypeScript rejects it at compile time):
   *   const chickens: Chicken[] = [];
   *   const anyAnimal: FarmAnimal = new Cow(99, "Holstein");
   *   chickens.push(anyAnimal); // TS2345: Argument of type 'FarmAnimal' is not assignable to parameter of type 'Chicken'
   */
  const farmAnimals: FarmAnimal[] = [
    new Chicken(1, "Rhode Island Red", new Date(2025, 4, 10), false),
    new Chicken(2, "Leghorn", new Date(2024, 10, 20), true),
    new Cow(3, "Holstein", new Date(2022, 2, 15), true),
    new Cow(4, "Jersey", new Date(2023, 7, 1), false),
    new Pig(5, "Yorkshire", new Date(2025, 0, 12)),
    // Overloaded constructor with minimal parameters (bornOn defaults to today):
    new Pig(6, "Duroc"),
    new Sheep(7, "Merino", new Date(2023, 3, 18), new Date(2026, 4, 10)),
    new Sheep(8, "Suffolk", new Date(2024, 8, 5), new Date(2026, 7, 22)),
  ];

  console.log("=== ESPE Animal Farm App - Polymorphism (TypeScript) ===");
  console.log(`Total registered animals: ${farmAnimals.length}`);
  console.log("------------------------------------------------------------");

  let totalMonthlyCost = 0;

  // Dynamic polymorphism: each concrete subclass executes its own overridden
  // methods at runtime with zero conditionals (no if / instanceof).
  for (const animal of farmAnimals) {
    const cost = animal.computeFoodCost();
    totalMonthlyCost += cost;

    console.log(animal.toString());
    console.log(`  -> Age: ${animal.getAgeInMonths()} months`);
    console.log(`  -> Feed (default): ${animal.feed()}`);
    console.log(`  -> Feed (overloaded): ${animal.feed("balanced farm mix", 2.5)}`);
    console.log(`  -> Production: ${animal.produce()}`);
    console.log(`  -> Monthly food cost: $${cost.toFixed(2)}`);
  }

  console.log("------------------------------------------------------------");
  console.log(`Total monthly farm food cost: $${totalMonthlyCost.toFixed(2)}`);

  // Interface-based polymorphism using an IFarmAnimal reference:
  const interfaceRef: IFarmAnimal = farmAnimals[2];
  console.log("\n=== Interface Reference (IFarmAnimal) ===");
  console.log(`Feed: ${interfaceRef.feed()}`);
  console.log(`Production: ${interfaceRef.produce()}`);
  console.log(`Monthly food cost: $${interfaceRef.computeFoodCost().toFixed(2)}`);
}

main();
