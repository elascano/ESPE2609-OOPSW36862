import { FarmAnimal } from "./FarmAnimal";

/**
 * Concrete subclass representing a chicken on the farm.
 */
export class Chicken extends FarmAnimal {
  private isMolting: boolean;

  constructor(id: number, breed: string);
  constructor(id: number, breed: string, bornOn: Date, isMolting: boolean);
  constructor(
    id: number,
    breed: string,
    bornOn?: Date,
    isMolting: boolean = false
  ) {
    if (bornOn === undefined) {
      super(id, breed);
    } else {
      super(id, breed, bornOn);
    }
    this.isMolting = isMolting;
  }

  public override computeFoodCost(): number {
    return this.isMolting ? 10.5 : 8.5;
  }

  public override produce(): string {
    if (!this.isMolting) {
      return "Laying fresh eggs.";
    }
    return "Not laying eggs right now (currently molting).";
  }

  public getIsMolting(): boolean {
    return this.isMolting;
  }

  public setIsMolting(isMolting: boolean): void {
    this.isMolting = isMolting;
  }

  public override toString(): string {
    return `Chicken{${super.toString()}, isMolting=${this.isMolting}}`;
  }
}
