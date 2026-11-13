import { FarmAnimal } from "./FarmAnimal";
import { Chicken } from "./Chicken";
import { Cow } from "./Cow";
import { Pig } from "./Pig";
import { Sheep } from "./Sheep";

console.log("Farm Animals");
console.log("============");

let farmAnimal = new FarmAnimal(1, "Holstein", new Date(), false);
console.log("My Farm Animal is --->", farmAnimal.toString());

let chicken = new Chicken(false, 10, 2, "Palish",
                          new Date(2022, 9, 10));
console.log("Chicken --->", chicken.toString());

let cow = new Cow(true, 23.5, 3, "Holstein",
                  new Date(2024, 11, 23), false);
console.log("Cow --->", cow.toString());

let pig = new Pig(true, 30.23, 4, "Mangalitsa",
                  new Date(2019, 10, 30), false);
console.log("Pig --->", pig.toString());

let sheep = new Sheep(true, new Date(2025, 10, 24), 5,
                      "Manchega", new Date(2024, 8, 27), true);
console.log("Sheep --->", sheep.toString());
