
import { Food } from "../model/Food";
import { Location } from "../model/Location";
import { Chicken } from "../model/Chicken";
import { Cow } from "../model/Cow";
import { Pig } from "../model/Pig";
import { Sheep } from "../model/Sheep";

let food = new Food("corn");
let location = new Location("graze");
let chicken = new Chicken(false, 10, 2, "palish", new Date(2022, 9, 10));
let cow = new Cow(true, 23.5, 3, "Holstein", new Date(2024, 11, 23), false);
let pig = new Pig(true, 30.23, 4, "Mangalitsa", new Date(2019, 10, 30), false);
let sheep = new Sheep(true, new Date(2025, 10, 24), 5, "Manchega", new Date(2024, 8, 27), true);

console.log("Isaac's Farm Animal");
console.log("=======================");

console.log("============");
console.log("Chicken:");
console.log(chicken.toString());
chicken.layAnEgg();
chicken.feed(food, 3);
chicken.sellAnimal(12.50);

food = new Food("hay");
console.log("============");
console.log("Cow:");
console.log(cow.toString());
cow.feed(food, 2);
cow.sendToGraze(location, new Date());
cow.milk();
cow.milk(23.4);
cow.milk(14);
cow.getLitersOfMilkPerWeek();

food = new Food("corn");
console.log("============");
console.log("Pig:");
console.log(pig.toString());
pig.feed(food, 12);
pig.showPigMeatCuts();
pig.getWeightGainPerWeek(32);
pig.sellAnimal(130.4);

food = new Food("grass");
console.log("============");
console.log("Sheep:");
console.log(sheep.toString());
sheep.feed(food, 3);
sheep.getLastSheering();
sheep.sendToGraze(location, new Date());
sheep.shearSheep();
