/** @author Mateo Nacimba, @ESPE */
import { FarmAnimal } from "./FarmAnimal";
import { Food } from "./Food";
import { SellAnimal } from "./SellAnimal";

export class Pig extends FarmAnimal implements SellAnimal {
  constructor(
    public isWallowing: boolean,
    public weightGainPerWeek: number,
    id: number,
    breed: string,
    bornOnDate: Date,
    isMeat: boolean
  ) {
    super(id, breed, bornOnDate, isMeat);
  }

  showPigMeatCuts(): void {
    const meatCuts = ["head", "cheek", "picnic", "clear plate", "boston shoulder", "back fat", "loin", "ribs", "bacon", "leg", "ham", "hock"];
    console.log("Meat cuts available from the pig: [" + meatCuts.join(", ") + "]");
  }

  feed(food: Food, amount: number): void {
    console.log(`giving the pig ${amount} kg of ${food} to eat`);
  }

  sellAnimal(price: number): void {
    console.log(`the pig was sold for ${price} dollars.`);
  }

  reportWeightGain(weightGain: number): void {
    console.log(`this week the pig put on ${weightGain} kg`);
  }

  toString(): string {
    return `Pig{${super.toString()}isWallowing=${this.isWallowing}, weightGainPerWeek=${this.weightGainPerWeek}}`;
  }
}
