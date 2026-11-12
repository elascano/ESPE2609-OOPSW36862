import { FarmAnimal } from "../model/FarmAnimal";
import { Chicken } from "../model/Chicken";
import { Cow } from "../model/Cow";

/**
 * @author Matias Paucar, ByteWorks, @ESPE
 */
export function main(): void {
    console.log(" Matias's Animal Farm");
    console.log("=====================");

    let farmAnimal: FarmAnimal;
    farmAnimal = new FarmAnimal(1, "holstein", new Date());
    console.log("my farm animal is --> " + farmAnimal);

    // En JavaScript los meses van de 0 a 11: 9 = octubre
    const chicken = new Chicken(true, 10, false, 2, "Palish", new Date(2022, 9, 10));
    console.log("chicken --> " + chicken);

    const cow: FarmAnimal = new Cow(true, 100.5, 3, "holstein", new Date());
    console.log("cow --> " + cow);
}
