import { FarmAnimal } from "./farm-animal.ts";
import type { Food } from "./food.ts";

export class Pig extends FarmAnimal {
  weight: number;
  isForMeat: boolean;
  totalCutWeight = 0;

  constructor(
    weight: number,
    isForMeat: boolean,
    animalId: number,
    breed: string,
    bornOnDate: Date | null,
  ) {
    super(animalId, breed, bornOnDate);
    this.weight = weight;
    this.isForMeat = isForMeat;
  }

  override feed(food: Food, amount: number): string {
    return `Feeding the pig ${amount} lb of ${food}.`;
  }

  recordCut(cutName: string, kilograms: number): string {
    if (!cutName.trim() || !Number.isFinite(kilograms) || kilograms <= 0) {
      throw new RangeError("A cut needs a name and a positive, finite weight.");
    }
    this.totalCutWeight += kilograms;
    return `Recorded ${kilograms} kg of ${cutName.trim()}.`;
  }

  override toString(): string {
    return (
      `${super.toString()}, Weight: ${this.weight} kg, ` +
      `Raised for meat: ${this.isForMeat}`
    );
  }
}
