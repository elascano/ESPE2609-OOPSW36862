import { FarmAnimal } from "./FarmAnimal";

/**
 * Subclass representing a sheep on the farm.
 */
export class Sheep extends FarmAnimal {
  private lastSheering: Date;

  constructor(id: number, breed: string, bornOn: Date, lastSheering: Date) {
    super(id, breed, bornOn);
    this.lastSheering = lastSheering;
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
