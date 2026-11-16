/** @author Mateo Nacimba, @ESPE */
import { FarmAnimal } from "./FarmAnimal";
import { Food } from "./Food";
import { SellAnimal } from "./SellAnimal";

export class Chicken extends FarmAnimal implements SellAnimal {
  constructor(
    public isMolting: boolean,
    public numberOfLaidEggsPerWeek: number,
    id: number,
    breed: string,
    bornOnDate: Date
  ) {
    super(id, breed, bornOnDate, isMolting);
  }

  feed(food: Food, amount: number): void {
    console.log(`giving the chicken ${amount} pounds of ${food} to eat`);
  }

  sellAnimal(price: number): void {
    console.log(`the chicken was sold for ${price} dollars.`);
  }

  layAnEgg(): void {
    this.numberOfLaidEggsPerWeek++;
    console.log("the chicken just laid an egg");
  }

  toString(): string {
    return `Chicken{${super.toString()}isMolting=${this.isMolting}, numberOfLaidEggsPerWeek=${this.numberOfLaidEggsPerWeek}}`;
  }
}
