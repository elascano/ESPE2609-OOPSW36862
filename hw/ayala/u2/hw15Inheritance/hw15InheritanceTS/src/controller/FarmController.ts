import { Chicken } from "../model/Chicken";
import { FarmAnimal } from "../model/FarmAnimal";

export class FarmController {
  public createFarmAnimal(id: number, breed: string): FarmAnimal {
    return new FarmAnimal(id, breed, new Date());
  }

  public createChicken(
    isMolting: boolean,
    numberOfLastEggsPerWeek: number,
    isMeat: boolean,
    id: number,
    breed: string
  ): Chicken {
    return new Chicken(
      isMolting,
      numberOfLastEggsPerWeek,
      isMeat,
      id,
      breed,
      new Date()
    );
  }
}
