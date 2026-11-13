import { Chicken } from "../model/Chicken";
import { Cow } from "../model/Cow";
import { FarmAnimal } from "../model/FarmAnimal";
import { Pig } from "../model/Pig";
import { Sheep } from "../model/Sheep";

/**
 *
 * @author Ostin Torres, CodeLex, @ESPE
 */
export class AnimalFarmApp {

    public static main(args: string[]): void {
        let farmAnimal: FarmAnimal;

        console.log("Chris' Farm Animal");
        console.log("=======================");

        // In JavaScript/TypeScript months go from 0 to 11 (10 - 1 = October)
        farmAnimal = new Chicken(false, 10, 2, "palish", new Date(2022, 10 - 1, 10));
        console.log("chicken --->" + farmAnimal);
        console.log("chicken is a --->" + "class " + farmAnimal.constructor.name);
        console.log("chicken is a --->" + farmAnimal.constructor.name);

        const chicken: Chicken = new Chicken(false, 10, 2, "palish", new Date(2022, 10 - 1, 10));
        console.log("chicken --->" + chicken);
        console.log("chicken is a --->" + chicken.constructor.name);

        const cow: Cow = new Cow(true, 23.5, 3, "Holstein", new Date(2024, 12 - 1, 23), false);
        console.log("cow --->" + cow);
        console.log("cow is a --->" + cow.constructor.name);

        const pig: Pig = new Pig(true, 30.23, 4, "Mangalitsa", new Date(2019, 11 - 1, 30), false);
        console.log("pig --->" + pig);
        console.log("pig is a --->" + pig.constructor.name);

        const sheep: Sheep = new Sheep(true, new Date(2025, 11 - 1, 24), 5, "Manchega", new Date(2024, 9 - 1, 27), true);
        console.log("sheep --->" + sheep);
        console.log("sheep is a --->" + sheep.constructor.name);

        chicken.layAnEgg();
        cow.milk();
        pig.showPigMeatCuts();
        sheep.shearSheep();
    }
}

AnimalFarmApp.main(process.argv.slice(2));
