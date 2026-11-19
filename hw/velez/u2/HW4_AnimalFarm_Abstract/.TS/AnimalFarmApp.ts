import { FarmAnimal } from "./model/FarmAnimal";
import { Chicken } from "./model/Chicken";
import { Cow } from "./model/Cow";
import { Pig } from "./model/Pig";
import { Sheep } from "./model/Sheep";

console.log("Yandry's Farm Animal");
console.log("=======================");

let farmAnimal: FarmAnimal = new Chicken(false, 10, 2, "palish", new Date(2022, 9, 10));
console.log(`chicken ---> ${farmAnimal.toString()}`);
console.log(`chicken is a ---> ${farmAnimal.constructor.name}`);

let chicken = new Chicken(false, 10, 2, "palish", new Date(2022, 9, 10));
let cow = new Cow(true, 23.5, 3, "Holstein", new Date(2024, 11, 23), false);
let pig = new Pig(true, 30.23, 4, "Mangalitsa", new Date(2019, 10, 30), false);
let sheep = new Sheep(true, new Date(2025, 10, 24), 5, "Manchega", new Date(2024, 8, 27), true);

console.log(`cow ---> ${cow.toString()}`);
console.log(`pig ---> ${pig.toString()}`);
console.log(`sheep ---> ${sheep.toString()}`);

chicken.layAnEgg();
cow.milk();
pig.showPigMeatCuts();
sheep.shearSheep();