import { FarmAnimal } from "./FarmAnimal";

/**
 * Concrete subclass representing a sheep on the farm.
 */
export class Sheep extends FarmAnimal {
  private lastSheering: Date;

  constructor(id: number, breed: string, bornOn: Date, lastSheering: Date) {
    super(id, breed, bornOn);
    this.lastSheering = lastSheering;
  }

  public override feed(): string {
    return `Sheep #${this.getId()} (${this.getBreed()}) is grazing on pasture grass and clover.`;
  }

  public override makeSound(): string {
    return "Baa, baa!";
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
