import { FarmAnimal } from '../model/FarmAnimal';
import { Chicken } from '../model/Chicken';
import { Cow } from '../model/Cow';
import { Pig } from '../model/Pig';
import { Sheep } from '../model/Sheep';

function main(): void {
    console.log("Cristopher's Farm Animals");
    console.log("=================================");

    const today = new Date();

    const farmAnimal = new FarmAnimal(1, "Holstein", today);
    console.log(`My Farm Animal is ---> ${farmAnimal.toString()}`);

    const chicken = new Chicken(true, 2, "polish", today);
    console.log(`Chicken --> ${chicken.toString()}`);

    const cow = new Cow(true, 3, "Angus", today);
    console.log(`Cow --> ${cow.toString()}`);

    const pig = new Pig(4, "Duroc", today);
    console.log(`Pig --> ${pig.toString()}`);

    const sheep = new Sheep(today, 5, "Merino", today);
    console.log(`Sheep --> ${sheep.toString()}`);
}

main();