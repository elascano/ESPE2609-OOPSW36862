import { Chicken } from "../model/Chicken";
import { Cow } from "../model/Cow";
import { FarmAnimal } from "../model/FarmAnimal";
import { AnimalFarmView } from "../view/AnimalFarmView";

export class AnimalFarmController {
  private view: AnimalFarmView;

  constructor(view: AnimalFarmView) {
    this.view = view;
  }

  run(): void {
    this.view.showTitle();
    const bornOnDate = new Date(2020, 10, 10);

    let farmAnimal: FarmAnimal = new FarmAnimal(1, "holstein", new Date());
    this.view.showAnimal("my farm animal", farmAnimal);
    this.view.showAnimalType("FarmAnimal", farmAnimal);
    this.view.showAnimalClass("FarmAnimal", farmAnimal);

    farmAnimal = new Chicken(true, 10, 2, "Paleesh", new Date(), true);
    this.view.showAnimalType("FarmAnimal", farmAnimal);

    const chicken = new Chicken(true, 10, 2, "Paleesh", new Date(), true);
    this.view.showAnimal("chicken", chicken);

    let cow: FarmAnimal = new Cow(true, 100.5, 3, "holstein", new Date(2123, 8, 20));

    cow = new Chicken(true, 4, 0, "paleesh", bornOnDate, true);
    this.view.showAnimal("cow", cow);

    const cow2 = new Cow(true, 0, 0, "palich", bornOnDate);
    this.view.showMessage(cow2.milk());
    this.view.showMessage(chicken.layAnEgg());
  }
}
