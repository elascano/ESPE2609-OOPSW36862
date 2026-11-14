import { FarmAnimal } from "./farm-animal.ts";
import type { Food } from "./food.ts";

export class Chicken extends FarmAnimal {
  isMolting: boolean;
  eggsLaidThisMonth: number;
  isRaisedForMeat: boolean;

  constructor(
    animalId: number,
    breed: string,
    bornOnDate: Date | null,
    isMolting: boolean,
    eggsLaidThisMonth: number,
    isRaisedForMeat: boolean,
  ) {
    super(animalId, breed, bornOnDate);
    this.isMolting = isMolting;
    this.eggsLaidThisMonth = eggsLaidThisMonth;
    this.isRaisedForMeat = isRaisedForMeat;
  }

  override feed(food: Food, amount: number): string {
    return `Feeding the chicken ${amount} lb of ${food}.`;
  }

  layAnEgg(): string {
    this.layEggs(1);
    return "The chicken is laying an egg.";
  }

  layEggs(amount: number): string {
    if (!Number.isInteger(amount) || amount <= 0) {
      throw new RangeError("The egg count must be a positive integer.");
    }
    this.eggsLaidThisMonth += amount;
    return `The chicken laid ${amount} eggs.`;
  }

  override toString(): string {
    return (
      `${super.toString()}, Molting: ${this.isMolting}, ` +
      `Eggs laid this month: ${this.eggsLaidThisMonth}, ` +
      `Raised for meat: ${this.isRaisedForMeat}`
    );
  }
}
