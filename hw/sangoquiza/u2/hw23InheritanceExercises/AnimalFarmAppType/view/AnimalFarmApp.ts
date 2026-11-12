import { FarmAnimal } from "../model/FarmAnimal";
import { Chicken } from "../model/Chicken";
import { Cow } from "../model/Cow";

/**
 * Aplicación principal traducida de ec.edu.espe.animalfarmapp.view.AnimalFarmApp
 * @author Daniel Sangoquiza, SaborCode, @ESPE
 */
class AnimalFarmApp {
    public static main(): void {
        console.log("Daniel Sangoquiza's Farm Animal");
        console.log("=======================");

        const farmAnimal: FarmAnimal = new FarmAnimal(1, "Holstein", new Date(), false);
        console.log(`My Farm Animal is--->${farmAnimal.toString()}`);

        // Nota: En JavaScript/TypeScript los meses en Date empiezan en 0 (0 = Enero, 9 = Octubre, 11 = Diciembre)
        const chicken: Chicken = new Chicken(false, 10, 2, "palish", new Date(2022, 9, 10));
        console.log(`chicken --->${chicken.toString()}`);

        const cow: Cow = new Cow(true, 23.5, 3, "Holstein", new Date(2024, 11, 23), false);
        console.log(`cow --->${cow.toString()}`);
    }
}

// Ejecución de la aplicación
AnimalFarmApp.main();