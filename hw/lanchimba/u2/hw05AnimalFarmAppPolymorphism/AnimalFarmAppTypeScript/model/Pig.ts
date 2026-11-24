import { FarmAnimal } from "./FarmAnimal";

/**
 * Concrete subclass representing a pig on the farm.
 */
export class Pig extends FarmAnimal {
  constructor(id: number, breed: string);
  constructor(id: number, breed: string, bornOn: Date);
  constructor(id: number, breed: string, bornOn?: Date) {
    if (bornOn === undefined) {
      super(id, breed);
    } else {
      super(id, breed, bornOn);
    }
  }

  public override computeFoodCost(): number {
    return 45.0 + this.getAgeInMonths() * 0.5;
  }

  public override produce(): string {
    return "Growing for farm meat production.";
  }

  public override toString(): string {
    return `Pig{${super.toString()}}`;
  }
}
