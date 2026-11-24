import { FarmAnimal } from "./FarmAnimal";

/**
 * Concrete subclass representing a pig on the farm.
 */
export class Pig extends FarmAnimal {
  constructor(id: number, breed: string, bornOn: Date) {
    super(id, breed, bornOn);
  }

  public override feed(): string {
    return `Pig #${this.getId()} (${this.getBreed()}) is eating corn, vegetables, and farm feed.`;
  }

  public override makeSound(): string {
    return "Oink, oink!";
  }

  public override produce(): string {
    return "Growing for farm meat production.";
  }

  public override toString(): string {
    return `Pig{${super.toString()}}`;
  }
}
