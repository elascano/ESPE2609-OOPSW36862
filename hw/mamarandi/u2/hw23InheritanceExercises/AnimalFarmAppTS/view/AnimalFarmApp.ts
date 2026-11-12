import { FarmAnimal } from "../model/FarmAnimal";
import { Chicken } from "../model/Chicken";
import { Cow } from "../model/Cow";
import { Pig } from "../model/Pig";
import { Sheep } from "../model/Sheep";
import { javaDate } from "../model/JavaDate";

function main(): void {
  console.log("Francisco's Farm Animal");
  console.log("=======================");

  const farmAnimal = new FarmAnimal(1, "Holstein", new Date(), false);
  console.log("My Farm Animal is--->" + farmAnimal.toString());

  const chicken = new Chicken(false, 10, 2, "palish", javaDate(2022, 10, 10));
  console.log("chicken --->" + chicken.toString());

  const cow = new Cow(true, 23.5, 3, "Holstein", javaDate(2024, 12, 23), false);
  console.log("cow --->" + cow.toString());

  const pig = new Pig(true, 30.23, 4, "Mangalitsa", javaDate(2019, 11, 30), false);
  console.log("pig --->" + pig.toString());

  const sheep = new Sheep(true, javaDate(2025, 11, 24), 5, "Manchega", javaDate(2024, 9, 27), true);
  console.log("sheep --->" + sheep.toString());
}

main();
