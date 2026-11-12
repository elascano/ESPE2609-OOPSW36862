import { FarmAnimal } from "../model/FarmAnimal";
import { Chicken } from "../model/Chicken";
import { Cow } from "../model/Cow";
import { Pig } from "../model/Pig";
import { Sheep } from "../model/Sheep";

/**
 * @author Leslye Cuichan, Byte Fabric & devs, @ESPE
 */
console.log(" Leslye's Animal Farm!");
console.log("=======================");

const farmAnimal = new FarmAnimal(1, "holstein", new Date());
console.log("My farm animal -->" + farmAnimal.toString());

const chicken = new Chicken(true, 10, false, 2, "Palish", new Date(2022, 9, 10));
console.log("chicken -->" + chicken.toString());

const cow = new Cow(true, 50.5, 3, "Holstein", new Date(2009, 0, 15));
console.log("cow --> " + cow.toString());
cow.milk();

const pig = new Pig(4, "Landrace", new Date(2011, 4, 20));
console.log("pig --> " + pig.toString());

const sheep = new Sheep(new Date(1989, 10, 5), true, true, 5, "Merino", new Date(124, 7, 1));
console.log("sheep --> " + sheep.toString());
sheep.shear();