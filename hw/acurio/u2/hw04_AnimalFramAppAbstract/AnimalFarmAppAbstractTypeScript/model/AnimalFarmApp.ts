import { FarmAnimal } from "./FarmAnimal";
import { Chicken } from "./Chicken";
import { Cow } from "./Cow";
import { Pig } from "./Pig";
import { Sheep } from "./Sheep";

console.log("Isaac Farm Animal");
console.log("=================");

let farmAnimal: FarmAnimal;

farmAnimal = new Chicken(false, 10, 2, "palish", new Date(2022, 9, 10));
console.log("chicken --->" + farmAnimal.toString());
console.log("chicken is a --->class " + farmAnimal.constructor.name);
console.log("chicken is a --->" + farmAnimal.constructor.name);

let chicken = new Chicken(false, 10, 2, "palish", new Date(2022, 9, 10));
console.log("chicken --->" + chicken.toString());
console.log("chicken is a --->" + chicken.constructor.name);

let cow = new Cow(true, 23.5, 3, "Holstein", new Date(2024, 11, 23), false);
console.log("cow --->" + cow.toString());
console.log("cow is a --->" + cow.constructor.name);

let pig = new Pig(true, 30.23, 4, "Mangalitsa", new Date(2019, 10, 30), false);
console.log("pig --->" + pig.toString());
console.log("pig is a --->" + pig.constructor.name);

let sheep = new Sheep(true, new Date(2025, 10, 24), 5,
    "Manchega", new Date(2024, 8, 27), true);
console.log("sheep --->" + sheep.toString());
console.log("sheep is a --->" + sheep.constructor.name);

chicken.layAnEgg();
cow.milk();
pig.showPigMeatCuts();
sheep.shearSheep();
