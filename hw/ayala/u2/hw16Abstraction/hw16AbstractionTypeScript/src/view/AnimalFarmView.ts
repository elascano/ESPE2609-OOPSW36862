import { FarmAnimal } from "../model/FarmAnimal";

export class AnimalFarmView {
  showTitle(): void {
    console.log("Enrique's Anima Farm");
    console.log("========================");
  }

  showAnimal(label: string, animal: FarmAnimal): void {
    console.log(`${label} --->${animal}`);
  }

  showAnimalType(label: string, animal: FarmAnimal): void {
    console.log(`${label} --> is a ${animal.constructor.name}`);
  }

  showAnimalClass(label: string, animal: FarmAnimal): void {
    console.log(`${label} --> class ${animal.constructor.name}`);
  }

  showMessage(message: string): void {
    console.log(message);
  }
}
