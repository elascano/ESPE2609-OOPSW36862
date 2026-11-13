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

        farmAnimal = new FarmAnimal(1, "Holstein", new Date(), false);
        console.log("My Farm Animal is--->" + farmAnimal);

        // In JavaScript/TypeScript months go from 0 to 11 (10 - 1 = October)
        const chicken: Chicken = new Chicken(false, 10, 2, "palish", new Date(2022, 10 - 1, 10));
        console.log("chicken --->" + chicken);

        const cow: Cow = new Cow(true, 23.5, 3, "Holstein", new Date(2024, 12 - 1, 23), false);
        console.log("cow --->" + cow);

        const pig: Pig = new Pig(true, 30.23, 4, "Mangalitsa", new Date(2019, 11 - 1, 30), false);
        console.log("pig --->" + pig);

        const sheep: Sheep = new Sheep(true, new Date(2025, 11 - 1, 24), 5, "Manchega", new Date(2024, 9 - 1, 27), true);
        console.log("sheep --->" + sheep);
    }
}

AnimalFarmApp.main(process.argv.slice(2));
