import { FarmAnimal } from "../model/FarmAnimal.ts";
import { Chicken } from "../model/Chicken.ts";
import { Cow } from "../model/Cow.ts";

export class AnimalFarmApp {
    public static main(): void {
        console.log("David's Farm Animal");
        console.log("====================");
        const bornOnDate: Date = new Date();
        const breed: string = "Palish";

        let farmAnimal: FarmAnimal;

        farmAnimal = new Chicken(true, 0, true, 0, breed, bornOnDate);
        console.log("farm animal --> " + farmAnimal.toString());
        console.log("farm Animal is a --> " + farmAnimal.constructor.name);

        farmAnimal = new Chicken(false, 20, false, 1, "layer", new Date());
        console.log("farmAnimal is a -->" + farmAnimal.constructor.name);

        console.log("my Farm Animal -->" + farmAnimal.toString());

        const chicken: Chicken = new Chicken(true, 10, true, 2, "Palish", new Date());
        console.log("Chicken -->" + chicken.toString());
        console.log("chicken is a -->" + chicken.constructor.name);

        const cow: Cow = new Cow(true, 100.5, 3, "holstein", new Date());
        console.log("Cow -->" + cow.toString());
        console.log("Cow is a -->" + cow.constructor.name);
        cow.milk();
        chicken.layAnEgg();
    }
}

AnimalFarmApp.main();