import { FarmAnimal } from '../model/FarmAnimal';
import { Chicken } from '../model/Chicken';
import { Cow } from '../model/Cow';
import { Pig } from '../model/Pig';
import { Sheep } from '../model/Sheep';

function main(): void {
    console.log("Cristopher's Animal Farm");
    console.log("========================");

    const bornOnDate = new Date(2020, 10, 10);
    let farmAnimal: FarmAnimal;

    farmAnimal = new FarmAnimal(1, "Holstein", bornOnDate);
    console.log(`FarmAnimal --> is a ${farmAnimal.constructor.name}`);
    console.log(`FarmAnimal --> ${farmAnimal.constructor.name}`);
    console.log(`farmAnimal ---> ${farmAnimal.toString()}`);
    console.log();

    farmAnimal = new Chicken(true, 10, false, 2, "Paleesh", bornOnDate);
    console.log(`FarmAnimal --> is a ${farmAnimal.constructor.name}`);
    console.log(`FarmAnimal --> ${farmAnimal.constructor.name}`);
    console.log(`chicken ---> ${farmAnimal.toString()}`);
    (farmAnimal as Chicken).layAnEgg();
    console.log();

    farmAnimal = new Cow(true, 100.5, 3, "Holstein", bornOnDate);
    console.log(`FarmAnimal --> is a ${farmAnimal.constructor.name}`);
    console.log(`FarmAnimal --> ${farmAnimal.constructor.name}`);
    console.log(`cow ---> ${farmAnimal.toString()}`);
    (farmAnimal as Cow).milk();
    console.log();

    farmAnimal = new Pig(85.5, true, 4, "Duroc", bornOnDate);
    console.log(`FarmAnimal --> is a ${farmAnimal.constructor.name}`);
    console.log(`FarmAnimal --> ${farmAnimal.constructor.name}`);
    console.log(`pig ---> ${farmAnimal.toString()}`);
    (farmAnimal as Pig).eat();
    console.log();

    farmAnimal = new Sheep(bornOnDate, 5.5, 5, "Merino", bornOnDate);
    console.log(`FarmAnimal --> is a ${farmAnimal.constructor.name}`);
    console.log(`FarmAnimal --> ${farmAnimal.constructor.name}`);
    console.log(`sheep ---> ${farmAnimal.toString()}`);
    (farmAnimal as Sheep).shear();
}

main();