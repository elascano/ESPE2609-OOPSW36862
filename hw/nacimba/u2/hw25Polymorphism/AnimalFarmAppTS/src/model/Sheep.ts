/** @author Mateo Nacimba, @ESPE */
import { FarmAnimal } from "./FarmAnimal";
import { Food } from "./Food";
import { GrazeAnimal } from "./GrazeAnimal";
import { Location } from "./Location";

export class Sheep extends FarmAnimal implements GrazeAnimal {
  constructor(
    public isSheared: boolean,
    public lastShearing: Date,
    id: number,
    breed: string,
    bornOnDate: Date,
    isMeat: boolean
  ) {
    super(id, breed, bornOnDate, isMeat);
  }

  shearSheep(): void {
    console.log("the sheep has just been sheared");
  }

  feed(food: Food, amount: number): void {
    console.log(`giving the sheep ${amount} kg of ${food} to eat`);
  }

  sendToGraze(location: Location, date: Date): void {
    console.log(`taking the sheep out to ${location} at ${date.getHours()} : ${date.getMinutes()}`);
  }

  toString(): string {
    return `Sheep{${super.toString()}isSheared=${this.isSheared}, lastShearing=${this.lastShearing.toISOString()}}`;
  }
}
