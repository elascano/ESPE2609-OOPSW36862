import { Chicken } from "../model/Chicken";
import { Cow } from "../model/Cow";
import { Food } from "../model/Food";
import { Location } from "../model/Location";
import { Pig } from "../model/Pig";
import { Sheep } from "../model/Sheep";

/**
 * @author Andrés Pachacama, ByteWorks, @ESPE
 */
export function main(): void {
    let food = new Food("corn");
    const location = new Location("graze");
    const chicken = new Chicken(false, 10, 2, "palish", new Date(3922, 10, 10));
    const cow = new Cow(true, 23.5, 3, "Holstein", new Date(3925, 0, 23), false);
    const pig = new Pig(true, 30.23, 4, "Mangalitsa", new Date(3919, 11, 30), false);
    const sheep = new Sheep(true, new Date(3925, 11, 24), 5, "Manchega", new Date(3924, 9, 27), true);

    console.log("Andres's Farm Animal");
    console.log("=======================");
    console.log("Chicken: ");
    console.log(chicken.toString());
    chicken.layAnEgg();
    chicken.feed(food, 3);
    chicken.sellAnimal(12.50);

    food = new Food("hay");
    console.log("Cow:");
    console.log(cow.toString());
    cow.feed(food, 2);
    cow.sendToGraze(location, new Date());
    cow.milk();
    cow.milk(25.4);
    cow.milk(14);
    cow.litersOfMilkPerWeek;

    food = new Food("corn");
    console.log("Pig:");
    console.log(pig.toString());
    pig.feed(food, 12);
    pig.showPigMeatCuts();
    pig.getWeightGainPerWeek(32);
    pig.sellAnimal(45.4);

    food = new Food("grass");
    console.log("Sheep:");
    console.log(sheep.toString());
    sheep.feed(food, 3);
    sheep.lastSheering;
    sheep.sendToGraze(location, new Date());
    sheep.shearSheep();
}
