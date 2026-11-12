import { FarmAnimal } from "./FarmAnimal";
import { javaDateToString } from "./JavaDate";

export class Sheep extends FarmAnimal {
  constructor(
    private isSheared: boolean,
    private lastSheering: Date,
    id: number,
    breed: string,
    bornOnDate: Date,
    isMeat: boolean
  ) {
    super(id, breed, bornOnDate, isMeat);
  }

  public isIsSheared(): boolean { return this.isSheared; }
  public setIsSheared(value: boolean): void { this.isSheared = value; }
  public getLastSheering(): Date { return this.lastSheering; }
  public setLastSheering(value: Date): void { this.lastSheering = value; }

  public override toString(): string {
    return `Sheep{` + super.toString() +
      `isSheared=${this.isSheared}, lastSheering=${javaDateToString(this.lastSheering)}` + "}";
  }
}
