import { FarmController } from "../controller/FarmController";

export class ChickenFarmApp {
  private controller: FarmController;

  constructor() {
    this.controller = new FarmController();
  }

  public run(): void {
    console.log("Enrique Farm App");
    console.log("=============================");

    const farmAnimal = this.controller.createFarmAnimal(1, "Molstein");
    console.log("MY farm Animal is --->" + farmAnimal);

    const chicken = this.controller.createChicken(true, 10, false, 2, "palish");
    console.log("Chicken --->" + chicken);
  }
}

new ChickenFarmApp().run();
