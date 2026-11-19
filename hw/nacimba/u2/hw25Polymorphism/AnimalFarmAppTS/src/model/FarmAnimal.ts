/** @author Mateo Nacimba, @ESPE */
import { Food } from "./Food";

export abstract class FarmAnimal {
  constructor(
    public id: number,
    public breed: string,
    public bornOnDate: Date,
    public isMeat: boolean
  ) {}

  /** Feed the animal with an amount of food. */
  abstract feed(food: Food, amount: number): void;

  getAgeInMonths(): number {
    return 0;
  }

  toString(): string {
    const d = this.bornOnDate;
    return (
      `FarmAnimal{id=${this.id}, breed=${this.breed}, is meat=${this.isMeat}, ` +
      `bornOnDate=${d.getDate()} / ${d.getMonth() + 1} / ${d.getFullYear()}}, `
    );
  }
}
