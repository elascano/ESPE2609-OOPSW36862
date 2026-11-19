/** Animal Farm App. @author Mateo Nacimba, @ESPE */
import { Chicken } from "./model/Chicken";
import { Cow } from "./model/Cow";
import { Food } from "./model/Food";
import { Location } from "./model/Location";
import { Pig } from "./model/Pig";
import { Sheep } from "./model/Sheep";

function main(): void {
  let food = new Food("corn");
  const location = new Location("graze");
  const chicken = new Chicken(false, 10, 2, "palish", new Date(2022, 9, 10));
  const cow = new Cow(true, 23.5, 3, "Holstein", new Date(2024, 11, 23), false);
  const pig = new Pig(true, 30.23, 4, "Mangalitsa", new Date(2019, 10, 30), false);
  const sheep = new Sheep(true, new Date(2025, 10, 24), 5, "Manchega", new Date(2024, 8, 27), true);

  console.log("Mateo Nacimba's Farm Animals");
  console.log("=============================");
  console.log("-----------------------------");
  console.log("Chicken:");
  console.log(chicken.toString());
  chicken.layAnEgg();
  chicken.feed(food, 3);
  chicken.sellAnimal(12.5);

  food = new Food("hay");
  console.log("-----------------------------");
  console.log("Cow:");
  console.log(cow.toString());
  cow.feed(food, 2);
  cow.sendToGraze(location, new Date());
  cow.milk();
  cow.milk(23.4);
  cow.milk(14);

  food = new Food("corn");
  console.log("-----------------------------");
  console.log("Pig:");
  console.log(pig.toString());
  pig.feed(food, 12);
  pig.showPigMeatCuts();
  pig.reportWeightGain(32);
  pig.sellAnimal(130.4);

  food = new Food("grass");
  console.log("-----------------------------");
  console.log("Sheep:");
  console.log(sheep.toString());
  sheep.feed(food, 3);
  sheep.sendToGraze(location, new Date());
  sheep.shearSheep();
}

main();
