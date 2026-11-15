import { FarmAnimal } from "./farm-animal.ts";
import type { Food } from "./food.ts";

export class Sheep extends FarmAnimal {
  lastShearing: Date;
  isProducingWool: boolean;
  kgOfWoolForMonth: number;

  constructor(
    lastShearing: Date,
    isProducingWool: boolean,
    kgOfWoolForMonth: number,
    animalId: number,
    breed: string,
    bornOnDate: Date | null,
  ) {
    super(animalId, breed, bornOnDate);
    this.lastShearing = lastShearing;
    this.isProducingWool = isProducingWool;
    this.kgOfWoolForMonth = kgOfWoolForMonth;
  }

  override feed(food: Food, amount: number): string {
    return `Feeding the sheep ${amount} lb of ${food}.`;
  }

  shear(): string;
  shear(kilograms: number): string;
  shear(kilograms?: number): string {
    if (kilograms === undefined) {
      return "The sheep is being sheared.";
    }
    if (!Number.isFinite(kilograms) || kilograms <= 0) {
      throw new RangeError("The wool weight must be positive and finite.");
    }
    this.kgOfWoolForMonth += kilograms;
    this.lastShearing = new Date();
    return `Collected ${kilograms} kg of wool.`;
  }

  override toString(): string {
    const lastShearing = this.lastShearing.toLocaleDateString("en-GB");
    return (
      `${super.toString()}, Last shearing: ${lastShearing}, ` +
      `Producing wool: ${this.isProducingWool}, ` +
      `Kilograms of wool this month: ${this.kgOfWoolForMonth}`
    );
  }
}
