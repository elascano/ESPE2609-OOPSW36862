import type { Food } from "./food.ts";

export abstract class FarmAnimal {
  animalId: number;
  breed: string;
  bornOnDate: Date | null;

  constructor(animalId: number, breed: string, bornOnDate: Date | null) {
    this.animalId = animalId;
    this.breed = breed;
    this.bornOnDate = bornOnDate;
  }

  abstract feed(food: Food, amount: number): string;

  getAgeInMonths(): number {
    const today = new Date();
    const birthDate = this.bornOnDate;

    if (birthDate === null || birthDate > today) {
      return 0;
    }

    const months =
      (today.getFullYear() - birthDate.getFullYear()) * 12 +
      today.getMonth() - birthDate.getMonth();
    return today.getDate() < birthDate.getDate() ? months - 1 : months;
  }

  toString(): string {
    const birthDate = this.bornOnDate?.toLocaleDateString("en-GB") ?? "Unknown";
    return `ID: ${this.animalId}, Breed: ${this.breed}, Birth date: ${birthDate}`;
  }
}
