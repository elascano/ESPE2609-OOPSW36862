import { FarmAnimal } from "./FarmAnimal";

/**
 * Concrete subclass representing a cow on the farm.
 */
export class Cow extends FarmAnimal {
  private isProducingMilk: boolean;

  constructor(id: number, breed: string);
  constructor(id: number, breed: string, bornOn: Date, isProducingMilk: boolean);
  constructor(
    id: number,
    breed: string,
    bornOn?: Date,
    isProducingMilk: boolean = false
  ) {
    if (bornOn === undefined) {
      super(id, breed);
    } else {
      super(id, breed, bornOn);
    }
    this.isProducingMilk = isProducingMilk;
  }

  public override computeFoodCost(): number {
    return this.isProducingMilk ? 120.0 : 85.0;
  }

  public override produce(): string {
    if (this.isProducingMilk) {
      return "Producing fresh dairy milk.";
    }
    return "Not producing milk at the moment.";
  }

  public getIsProducingMilk(): boolean {
    return this.isProducingMilk;
  }

  public setIsProducingMilk(isProducingMilk: boolean): void {
    this.isProducingMilk = isProducingMilk;
  }

  public override toString(): string {
    return `Cow{${super.toString()}, isProducingMilk=${this.isProducingMilk}}`;
  }
}
