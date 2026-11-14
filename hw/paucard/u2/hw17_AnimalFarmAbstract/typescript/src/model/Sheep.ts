import { formatDate } from "../util";
import { FarmAnimal } from "./FarmAnimal";

export class Sheep extends FarmAnimal {
  private _isSheared: boolean;
  private _lastSheering: Date;

  constructor(
    isSheared: boolean,
    lastSheering: Date,
    id: number,
    breed: string,
    bornOnDate: Date,
    isMeat: boolean
  ) {
    super(id, breed, bornOnDate, isMeat);
    this._isSheared = isSheared;
    this._lastSheering = lastSheering;
  }

  shearSheep(): void {
    console.log("Sheep is sheared");
  }

  override toString(): string {
    return (
      `Sheep{${super.toString()}isSheared=${this.isSheared}, ` +
      `lastSheering=${formatDate(this.lastSheering)}}`
    );
  }

  get isSheared(): boolean {
    return this._isSheared;
  }
  set isSheared(value: boolean) {
    this._isSheared = value;
  }

  get lastSheering(): Date {
    return this._lastSheering;
  }
  set lastSheering(value: Date) {
    this._lastSheering = value;
  }
}
