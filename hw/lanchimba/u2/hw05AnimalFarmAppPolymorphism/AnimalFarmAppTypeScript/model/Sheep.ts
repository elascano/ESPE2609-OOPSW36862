import { FarmAnimal } from "./FarmAnimal";

/**
 * Concrete subclass representing a sheep on the farm.
 */
export class Sheep extends FarmAnimal {
  private lastSheering: Date;

  constructor(id: number, breed: string);
  constructor(id: number, breed: string, bornOn: Date, lastSheering: Date);
  constructor(
    id: number,
    breed: string,
    bornOn?: Date,
    lastSheering: Date = new Date()
  ) {
    if (bornOn === undefined) {
      super(id, breed);
    } else {
      super(id, breed, bornOn);
    }
    this.lastSheering = lastSheering;
  }

  public override computeFoodCost(): number {
    return 30.0 + this.getAgeInMonths() * 0.25;
  }

  public override produce(): string {
    return `Producing wool (last sheared on ${this.formatDate(this.lastSheering)}).`;
  }

  public getLastSheering(): Date {
    return this.lastSheering;
  }

  public setLastSheering(lastSheering: Date): void {
    this.lastSheering = lastSheering;
  }

  public override toString(): string {
    return `Sheep{${super.toString()}, lastSheering=${this.formatDate(this.lastSheering)}}`;
  }
}
