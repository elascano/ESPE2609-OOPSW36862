import { Chicken } from "../model/Chicken.ts";
import { Cow } from "../model/Cow.ts";
import type { FarmAnimal } from "../model/FarmAnimal.ts";
import { Pig } from "../model/Pig.ts";
import { Sheep } from "../model/Sheep.ts";

//Demian Armas, Logical Lords, @ESPE

const today = new Date();
const chicken = new Chicken(false, 20, false, 1, "Polish", today);
const cow = new Cow(true, 100.5, 2, "Holstein", today);
const pig = new Pig(95.5, true, 3, "Yorkshire", today);
const sheep = new Sheep(today, true, 2.5, 4, "Merino", today);
const animals: FarmAnimal[] = [chicken, cow, pig, sheep];

console.log("Demian's Animal Farm");
console.log("====================");
for (const animal of animals) {
    console.log(animal.toString());
}

console.log(cow.milk());
console.log(chicken.layAnEgg());
console.log(sheep.shear());
