import { FarmAnimal } from "./farm-animal.ts";
import type { Food } from "./food.ts";
import type { GrazeAnimal } from "./graze-animal.ts";
import type { Location } from "./location.ts";

export class Cow extends FarmAnimal implements GrazeAnimal {
  isProducingMilk: boolean;
  litersOfMilkPerWeek: number;

  constructor(
    animalId: number,
    breed: string,
    bornOnDate: Date | null,
    isProducingMilk: boolean,
    litersOfMilkPerWeek: number,
  ) {
    super(animalId, breed, bornOnDate);
    this.isProducingMilk = isProducingMilk;
    this.litersOfMilkPerWeek = litersOfMilkPerWeek;
  }

  override feed(food: Food, amount: number): string {
    return `Feeding the cow ${amount} lb of ${food}.`;
  }

  graze(location: Location): string {
    return `The cow is grazing in ${location}.`;
  }

  milk(): string;
  milk(amountOfLiters: number): string;
  milk(amountOfLiters?: number): string {
    if (amountOfLiters === undefined) {
      return "The cow is being milked.";
    }

    this.litersOfMilkPerWeek += amountOfLiters;
    return `Milking the cow: ${amountOfLiters} liters.`;
  }

  override toString(): string {
    return (
      `${super.toString()}, Producing milk: ${this.isProducingMilk}, ` +
      `Liters of milk per week: ${this.litersOfMilkPerWeek}`
    );
  }
}
