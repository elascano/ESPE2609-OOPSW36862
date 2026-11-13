import { Chicken, Cow, FarmAnimal, Pig, Sheep } from "../model";

/** Crea una fecha con mes 1-12 (JavaScript usa 0-11 internamente). */
function dateOf(year: number, month: number, day: number): Date {
  return new Date(year, month - 1, day);
}

function main(): void {
  console.log("Farm Animal");
  console.log("=======================");

  // Polimorfismo: una variable del tipo abstracto apuntando a una subclase.
  const farmAnimal: FarmAnimal = new Chicken(false, 10, 2, "palish", dateOf(2022, 10, 10));
  console.log("chicken --->" + farmAnimal);
  console.log("chicken is a --->class " + farmAnimal.constructor.name);
  console.log("chicken is a --->" + farmAnimal.constructor.name);

  const chicken = new Chicken(false, 10, 2, "palish", dateOf(2022, 10, 10));
  console.log("chicken --->" + chicken);
  console.log("chicken is a --->" + chicken.constructor.name);

  const cow = new Cow(true, 23.5, 3, "Holstein", dateOf(2024, 12, 23), false);
  console.log("cow --->" + cow);
  console.log("cow is a --->" + cow.constructor.name);

  const pig = new Pig(true, 30.23, 4, "Mangalitsa", dateOf(2019, 11, 30), false);
  console.log("pig --->" + pig);
  console.log("pig is a --->" + pig.constructor.name);

  const sheep = new Sheep(true, dateOf(2025, 11, 24), 5, "Manchega", dateOf(2024, 9, 27), true);
  console.log("sheep --->" + sheep);
  console.log("sheep is a --->" + sheep.constructor.name);

  chicken.layAnEgg();
  cow.milk();
  pig.showPigMeatCuts();
  sheep.shearSheep();
}

main();
