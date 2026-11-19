import { FarmAnimal } from "../model/FarmAnimal.ts";
import { Chicken } from "../model/Chicken.ts";
import { Cow } from "../model/Cow.ts";
import { Pig } from "../model/Pig.ts";
import { Sheep } from "../model/Sheep.ts";

/**
 * @author Angel Bonilla, Infinite Loops, @ESPE
 */
class AnimalFarm {
    public static main(): void {
        console.log("Hello This is Angel's Animal Farm");

        const farmAnimal: FarmAnimal = new FarmAnimal(1, "holstein", new Date());
        console.log("my farm animal ---> " + farmAnimal.toString());

        const chicken: Chicken = new Chicken(true, 10, true, 2, "Paleesh", new Date());
        console.log("chicken ---> " + chicken.toString());

        const cow: FarmAnimal = new Cow(true, 100.5, 3, "holstein", new Date(2022, 7, 20));
        console.log("cow --> " + cow.toString());

        const pig: Pig = new Pig(85.5, true, 4, "Landrace", new Date());
        console.log("pig --> " + pig.toString());

        const sheep: Sheep = new Sheep(new Date(), true, 4.2, 5, "Merino", new Date());
        console.log("sheep --> " + sheep.toString());
    }
}

AnimalFarm.main();