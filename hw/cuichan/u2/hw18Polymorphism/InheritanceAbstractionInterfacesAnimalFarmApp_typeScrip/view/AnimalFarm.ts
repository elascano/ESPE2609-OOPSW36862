
import { Chicken } from '../model/Chicken';
import { Sheep } from '../model/Sheep';
import { Pig } from '../model/Pig';
import { Cow } from '../model/Cow';
import { Food } from '../model/Food';
import { Location } from '../model/Location';
import { FarmAnimal } from '../model/FarmAnimal';

function main() {
const bornOnDate = new Date(2022, 8, 12); 
    console.log("Hello This is Leslye's Animal Farms!");

    let farmAnimal: FarmAnimal = new Chicken(true, 10, true, 1, "Palish", new Date());
    console.log("FarmAminal is a ---> " + farmAnimal.constructor.name);
    console.log("farm animal ---> " + farmAnimal);

    let cow: Cow = new Cow(true, 150.0, 7, "holsteing", bornOnDate);
    console.log("cow is " + cow);
    console.log("cow is a   " + cow.constructor.name);

    let chicken: Chicken = new Chicken(true, 20, false, 2, "Palish", new Date());
    console.log("My Chicken " + chicken);

    let location: Location = new Location(2, "PASTURE 1");
    let farmAnimalForFeeding: FarmAnimal;

    let sheep: Sheep = new Sheep(new Date(), true, false, 301, "Merino", new Date());
    console.log("My Sheep " + sheep);

    let pig: Pig = new Pig(401, "Landrace", new Date(), 110.5, true);
    console.log("My Pig " + pig);

    farmAnimalForFeeding = new Chicken(true, 10, true, 5, "Palish", new Date());
    console.log("cow is a " + farmAnimalForFeeding.constructor.name);

    farmAnimalForFeeding = new Cow(true, 100.5, 3, "holstein", new Date());
    console.log("cow is a " + farmAnimalForFeeding.constructor.name);

    farmAnimalForFeeding = new Chicken(true, 4, true, 4, "PALEESH", new Date());
    console.log("cow is a " + farmAnimalForFeeding.constructor.name);

    cow.milk(3.4);

    let food = new Food("feed");
    cow.milk(3.5);
    chicken.layInEgg();
    cow.milk(20.5);
    cow.milk();

    chicken.feed(food, 2);
    cow.sendtoGraze(location);

    sheep.feed(food, 3);
    sheep.setBornOnDate(bornOnDate);
    pig.feed(food, 5);
}

main();