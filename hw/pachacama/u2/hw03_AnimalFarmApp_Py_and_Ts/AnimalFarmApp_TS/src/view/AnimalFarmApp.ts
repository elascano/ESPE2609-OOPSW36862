import { Chicken } from "../model/Chicken.ts";
import { Cow } from "../model/Cow.ts";
import { FarmAnimal } from "../model/FarmAnimal.ts";

/**
 
 * @author Andres Pachacama, Byte Works, ESPE
 */
function main(): void {
    console.log("Andres Farm Animal");
    console.log("==================");

    let farmAnimal: FarmAnimal;
    farmAnimal = new FarmAnimal(1, "Holstein", new Date());
    console.log("My Farm Animal is  -->" + farmAnimal);

    // en JavaScript los meses empiezan en 0: 10 = noviembre
    const chicken = new Chicken(true, 10, false, 2, "palish", new Date(2022, 10, 10));
    console.log("chicken --> " + chicken);

    // 4 = mayo
    const cow = new Cow(25.5, false, 650.0, true, 3, "Holstein", new Date(2021, 4, 15));
    console.log("cow --> " + cow);
}

main();
