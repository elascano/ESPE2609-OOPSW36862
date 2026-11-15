import { Chicken } from "../model/Chicken";
import { Cow } from "../model/Cow";
import { FarmAnimal } from "../model/FarmAnimal";
import { Pig } from "../model/Pig";
import { Sheep } from "../model/Sheep";

/**
 * @author Dennis, Byte & Fabric Devs, @ESPE
 */
export class AnimalFarmApp {

    public static main(): void {
        let farmAnimal: FarmAnimal;

        console.log("Dennis's Farm Animal");
        console.log("=======================");

        // En JS el mes empieza en 0 (igual que java.util.Date): 10 = noviembre
        farmAnimal = new Chicken(false, 10, 2, "palish", new Date(2022, 10, 10));
        console.log("chicken --->" + farmAnimal);
        console.log("chicken is a --->class " + farmAnimal.constructor.name);
        console.log("chicken is a --->" + farmAnimal.constructor.name);

        const chicken = new Chicken(false, 10, 2, "palish", new Date(2022, 10, 10));
        console.log("chicken --->" + chicken);
        console.log("chicken is a --->" + chicken.constructor.name);

        const cow = new Cow(true, 23.5, 3, "Holstein", new Date(2024, 12, 23), false);
        console.log("cow --->" + cow);
        console.log("cow is a --->" + cow.constructor.name);

        const pig = new Pig(true, 30.23, 4, "Mangalitsa", new Date(2019, 11, 30), false);
        console.log("pig --->" + pig);
        console.log("pig is a --->" + pig.constructor.name);

        const sheep = new Sheep(true, new Date(2025, 11, 24), 5, "Manchega", new Date(2024, 9, 27), true);
        console.log("sheep --->" + sheep);
        console.log("sheep is a --->" + sheep.constructor.name);

        chicken.layAnEgg();
        cow.milk();
    }
}

AnimalFarmApp.main();
