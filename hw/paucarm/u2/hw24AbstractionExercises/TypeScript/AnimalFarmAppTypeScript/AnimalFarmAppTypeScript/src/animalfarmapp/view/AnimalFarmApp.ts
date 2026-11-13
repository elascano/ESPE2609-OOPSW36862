import { FarmAnimal } from "../model/FarmAnimal";
import { Chicken } from "../model/Chicken";
import { Cow } from "../model/Cow";
import { Pig } from "../model/Pig";
import { Sheep } from "../model/Sheep";

/**
 * @author Matias Paucar, ByteWorks, @ESPE
 */
export function main(): void {
    let farmAnimal: FarmAnimal;

    console.log("Matias's Farm Animal");
    console.log("=======================");

    // En JavaScript los meses van de 0 a 11 (9 = octubre, 11 = diciembre)
    farmAnimal = new Chicken(false, 10, 2, "palish", new Date(2022, 9, 10));
    console.log("chicken --->" + farmAnimal);
    console.log("chicken is a --->class " + farmAnimal.constructor.name); // getClass()
    console.log("chicken is a --->" + farmAnimal.constructor.name);       // getSimpleName()

    const chicken = new Chicken(false, 10, 2, "palish", new Date(2022, 9, 10));
    console.log("chicken --->" + chicken);
    console.log("chicken is a --->" + chicken.constructor.name);

    const cow = new Cow(true, 23.5, 3, "Holstein", new Date(2024, 11, 23), false);
    console.log("cow --->" + cow);
    console.log("cow is a --->" + cow.constructor.name);

    const pig = new Pig(true, 30.23, 4, "Mangalitsa", new Date(2019, 10, 30), false);
    console.log("pig --->" + pig);
    console.log("pig is a --->" + pig.constructor.name);

    const sheep = new Sheep(true, new Date(2025, 10, 24), 5, "Manchega", new Date(2024, 8, 27), true);
    console.log("sheep --->" + sheep);
    console.log("sheep is a --->" + sheep.constructor.name);

    chicken.layAnEgg();
    cow.milk();
    pig.showPigMeatCuts();
    sheep.shearSheep();
}
