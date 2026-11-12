import { Chicken } from '../model/Chicken';
import { Cow } from '../model/Cow';
import { FarmAnimal } from '../model/FarmAnimal';

class AnimalFarmApp {
    public static main(): void {
        console.log("Josselyn's Animal Farm!");
        
        let farmAnimal: FarmAnimal = new FarmAnimal(1, "holstein", new Date());
        console.log("my farm animal --> " + farmAnimal);

        let chicken: Chicken = new Chicken(true, 10, true, 2, "Palish", new Date());
        console.log("chicken --> " + chicken);

        let cow: FarmAnimal = new Cow(true, 100.5, 3, "holstein", new Date());
        console.log("cow --> " + cow);
    }
}

AnimalFarmApp.main();