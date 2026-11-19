/** @author Mateo Nacimba, @ESPE */
import { FarmAnimal } from "./FarmAnimal";
import { Food } from "./Food";
import { GrazeAnimal } from "./GrazeAnimal";
import { Location } from "./Location";

export class Cow extends FarmAnimal implements GrazeAnimal {
  constructor(
    public isProducingMilk: boolean,
    public litersOfMilkPerWeek: number,
    id: number,
    breed: string,
    bornOnDate: Date,
    isMeat: boolean
  ) {
    super(id, breed, bornOnDate, isMeat);
  }

  /** Without arguments just reports the total; with liters adds today's yield. */
  milk(liters?: number): void {
    if (liters !== undefined) {
      this.litersOfMilkPerWeek += liters;
      console.log(`milking the cow... today's yield ---> ${liters} liters`);
    }
    console.log(`this cow has given a total of ---> ${this.litersOfMilkPerWeek} liters this week`);
  }

  feed(food: Food, amount: number): void {
    console.log(`giving the cow ${amount} units of ${food} to eat`);
  }

  sendToGraze(location: Location, date: Date): void {
    console.log(`taking the cow out to ${location} at ${date.getHours()} : ${date.getMinutes()}`);
  }

  toString(): string {
    return `Cow{${super.toString()}isProducingMilk=${this.isProducingMilk}, litersOfMilkPerWeek=${this.litersOfMilkPerWeek}}`;
  }
}
