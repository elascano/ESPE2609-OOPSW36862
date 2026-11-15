import { Chicken } from '../model/Chicken';
import { Cow } from '../model/Cow';
import { FarmAnimal } from '../model/FarmAnimal';

function main() {
    console.log("Julian's Farm Animal");
    console.log("====================");

    const today = new Date();

    let farmAnimal = new Chicken(true, 0, true, 0, "Palish", today);
    console.log(`farm animal -->${farmAnimal.toString()}`);
    console.log(`farm Animal is a -->${farmAnimal.constructor.name}`); 
    console.log(`farmAnimal is a -->${farmAnimal.constructor.name}`);

    let myFarmAnimal = new Chicken(false, 20, false, 1, "layer", today);
    console.log(`my Farm Animal -->${myFarmAnimal.toString()}`);

    let chicken = new Chicken(true, 10, true, 2, "Paleesh", today);
    console.log(`Cchicken -->${chicken.toString()}`);
    console.log(`chicken is a -->${chicken.constructor.name}`);

    let cow = new Cow(true, 100.5, 3, "holstein", today);
    console.log(`Cow -->${cow.toString()}`);
    console.log(`Cor is a -->${cow.constructor.name}`);
}

main();