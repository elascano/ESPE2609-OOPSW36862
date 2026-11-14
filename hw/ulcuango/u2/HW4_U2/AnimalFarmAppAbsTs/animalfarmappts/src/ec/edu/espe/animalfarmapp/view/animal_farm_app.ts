import { FarmAnimal } from '../model/farm_animal';
import { Chicken } from '../model/chicken';
import { Cow } from '../model/cow';
import { Pig } from '../model/pig';
import { Sheep } from '../model/sheep';

class AnimalFarmApp {
    public static main(): void {
        console.log("Josselyn' Farm Animal");
        console.log("==================");

        const farmAnimal = new FarmAnimal(1, "Holstein", new Date());
        console.log("My farm Animal is --> " + farmAnimal.toString());
        console.log("My farm Animal is a --> " + farmAnimal.constructor.name);

        const chicken = new Chicken(true, 10, false, 2, "Palish", new Date(2022, 9, 10)); // Mes 9 = Octubre en JS (0-indexado)
        console.log("Chicken --> " + chicken.toString());
        console.log("Farm animal is a  --> " + farmAnimal.constructor.name);
        console.log("Chicken is a --> " + chicken.constructor.name);

        const cow = new Cow(3, "Lola", new Date(), true);
        console.log("Cow --> " + cow.toString());
        console.log("cow is  a --> " + chicken.constructor.name);

        const pig = new Pig(4, "Pumba", new Date());
        console.log("Pig --> " + pig.toString());

        const sheep = new Sheep(5, "Cotton", new Date(), new Date());
        console.log("Sheep --> " + sheep.toString());

        cow.milk();
        chicken.layAnEgg();
    }
}

AnimalFarmApp.main();