import { FarmAnimal } from '../model/FarmAnimal';
import { Chicken } from '../model/Chicken';
import { Cow } from '../model/Cow';
import { Pig } from '../model/Pig';
import { Sheep } from '../model/Sheep';

/**
 * @author Leslye Cuichan, Byte Fabric & devs, @ESPE
 */
class AnimalFarmApp {
    public static main(): void {
        console.log("Leslye's Farm Animal");
        console.log("====================");

        const bornOnDate = new Date();
        const breed = "Palish";

        let farmAnimal: FarmAnimal;
        farmAnimal = new Chicken(true, 0, true, 0, breed, bornOnDate);
        console.log("farm animal -->" + farmAnimal.toString());
        console.log("farm Animal is a -->" + farmAnimal.constructor.name);

        farmAnimal = new Chicken(false, 20, false, 1, "layer", new Date());
        console.log("farmAnimal is a -->" + farmAnimal.constructor.name);
        console.log("my Farm Animal -->" + farmAnimal.toString());

        const chicken = new Chicken(true, 10, true, 2, "Paleesh", new Date());
        console.log("Chicken -->" + chicken.toString());
        console.log("chicken is a -->" + chicken.constructor.name);

        const cow = new Cow(true, 100.5, 3, "holstein", new Date());
        console.log("Cow -->" + cow.toString());
        console.log("Cow is a -->" + cow.constructor.name);
        cow.milk();

        const pig = new Pig(4, "Landrace", new Date(2011, 4, 20), 85.0, true);
        console.log("Pig -->" + pig.toString());
        console.log("Pig is a -->" + pig.constructor.name);

        const sheep = new Sheep(new Date(2024, 7, 1), true, true, 5, "Merino", new Date(1989, 10, 5));
        console.log("Sheep -->" + sheep.toString());
        console.log("Sheep is a -->" + sheep.constructor.name);

        chicken.layAnEgg();
        cow.milk();
    }
}

AnimalFarmApp.main();