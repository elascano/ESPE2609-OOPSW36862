import { Chicken } from "../model/Chicken";
import { Cow } from "../model/Cow";
import { FarmAnimal } from "../model/FarmAnimal";
import { Food } from "../model/Food";

/**
 * @author David Clavón, Infinite loops, @ESPE
 */
export class AnimalFarm {
    public static main(): void {
        let food: Food;
        const bornOnDate = new Date(2022, 9, 12);
        console.log("Hello This is David's Animal Farms!");

        let farmAnimal: FarmAnimal;
        farmAnimal = new Chicken(true, 10, true, 1, "Paleesh", new Date());
        console.log(`FarmAminal is a ---> ${farmAnimal.constructor.name}`);
        console.log(`farm animal ---> ${farmAnimal.toString()}`);

        let cow: Cow;
        cow = new Cow(true, 150.0, 7, "holsteing", bornOnDate);
        console.log(`cow is ${cow.toString()}`);
        console.log(`cow is a  ${cow.constructor.name}`);

        const chicken = new Chicken(true, 20, false, 2, "Paleesh", new Date());

        console.log("==============================================================");
        food = new Food("corn");
        chicken.feed(food, 2);

        chicken.layInEgg();
        food = new Food("grass");
        cow.feed(food, 1);

        const hourMilking = new Date();
        cow.milk(15, hourMilking);

        cow.milk(3.5);
        chicken.layInEgg();
        cow.milk(20.5);

        chicken.feed(food, 1);
        cow.milk();
        cow.milk(1.5);
        cow.milk(100);
    }
}

AnimalFarm.main();