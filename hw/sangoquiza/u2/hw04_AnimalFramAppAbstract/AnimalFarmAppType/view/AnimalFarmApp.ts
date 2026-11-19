import { FarmAnimal } from "../model/FarmAnimal";
import { Chicken } from "../model/Chicken";
import { Cow } from "../model/Cow";
import { Food } from "../model/Food";

/**
 * Aplicación principal traducida de ec.edu.espe.view.AnimalFarmApp
 * @author Daniel Sangoquiza, SaborCode
 */
class AnimalFarmApp {
    public static main(): void {
        console.log("Hello. This is Alejandro Animal Farm");

        let farmAnimal: FarmAnimal;

        // Calendar.OCTOBER en Java es mes 9 (0-indexado en JS/TS)
        const bornOnDate: Date = new Date(2020, 9, 10);

        let food: Food = new Food("corn");

        farmAnimal = new Chicken(true, 4, true, 3, "Poultry", bornOnDate);

        console.log(`farmAnimal is a --> class ${farmAnimal.constructor.name}`);
        console.log(`My farm animal --> ${farmAnimal.toString()}`);

        const chicken: Chicken = new Chicken(true, 10, true, 2, "Paleesh", new Date());

        console.log(`Chicken --> ${chicken.toString()}`);
        chicken.layAnEgg();

        console.log(`farmAnimal is a --> class ${chicken.constructor.name}`);
        console.log(`farmAnimal is a --> ${chicken.constructor.name}`);

        const cow: Cow = new Cow(true, 100.5, 3, "holstein", new Date());

        console.log(`Cow --> ${cow.toString()}`);
        console.log(`Cow is --> class ${cow.constructor.name}`);
        console.log(`Cow is --> ${cow.constructor.name}`);

        cow.milk();

        const chicken1: FarmAnimal = new Chicken(true, 4, true, 0, "palish", bornOnDate);
        console.log(`Chicken --> ${chicken1.toString()}`);

        const cow1: Cow = new Cow(true, 0.0, 0, "Molestein", bornOnDate);

        console.log(`Cow is --> class ${cow1.constructor.name}`);

        cow1.milk();

        chicken.feed(food, 2);

        food = new Food("grass");

        cow1.feed(food, 1);

        cow1.milk();
        cow1.milk(3.5);
        cow1.milk(100);
        cow1.milk(20.5);

        console.log(`This cow liters of milk per week: ${cow1.getLitersOfMilkPerWeek()}`);
    }
}

// Ejecución de la aplicación
AnimalFarmApp.main();