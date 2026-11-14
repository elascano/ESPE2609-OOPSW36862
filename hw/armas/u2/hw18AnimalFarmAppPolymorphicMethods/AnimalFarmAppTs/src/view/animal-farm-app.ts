import { Chicken } from "../model/chicken.ts";
import { Cow } from "../model/cow.ts";
import type { FarmAnimal } from "../model/farm-animal.ts";
import { Food } from "../model/food.ts";
import { Location } from "../model/location.ts";
import { Pig } from "../model/pig.ts";
import { Sheep } from "../model/sheep.ts";

class AnimalFarmApp {
  static main(): void {
    const bornOnDate = new Date(2026, 10, 12);
    const chicken = new Chicken(1, "Polish", bornOnDate, true, 4, false);
    const cow = new Cow(2, "Holstein", bornOnDate, true, 0);
    const sheep = new Sheep(bornOnDate, true, 2.5, 3, "Merino", bornOnDate);
    const pig = new Pig(80.5, true, 4, "Yorkshire", bornOnDate);

    const feedingSchedule: { animal: FarmAnimal; food: Food; amount: number }[] = [
      { animal: chicken, food: new Food("corn"), amount: 1 },
      { animal: cow, food: new Food("grass"), amount: 5 },
      { animal: sheep, food: new Food("hay"), amount: 3 },
      { animal: pig, food: new Food("pellets"), amount: 4 },
    ];

    console.log("Animal Farm - Polymorphic Methods");
    for (const { animal, food, amount } of feedingSchedule) {
      console.log(`${animal.constructor.name}: ${animal}`);
      console.log(animal.feed(food, amount));
    }

    console.log(chicken.layAnEgg());
    console.log(chicken.layEggs(3));
    console.log(`Total eggs laid this month: ${chicken.eggsLaidThisMonth}`);

    console.log(cow.graze(new Location(1, "the pasture")));
    console.log(sheep.shear());
    console.log(sheep.shear(1.5));
    console.log(sheep.shear(2));
    console.log(`Total wool collected this month: ${sheep.kgOfWoolForMonth.toFixed(1)} kg`);

    console.log(pig.recordCut("pork loin", 2.5));
    console.log(pig.recordCut("ribs", 1.5));
    console.log(`Total pork cuts recorded: ${pig.totalCutWeight.toFixed(1)} kg`);

    console.log(cow.milk());
    console.log(cow.milk(3.5));
    console.log(cow.milk(100));
    console.log(`Total liters of milk per week: ${cow.litersOfMilkPerWeek}`);
  }
}

AnimalFarmApp.main();
