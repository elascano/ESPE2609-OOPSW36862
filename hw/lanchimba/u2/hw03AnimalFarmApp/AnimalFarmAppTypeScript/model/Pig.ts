import { FarmAnimal } from "./FarmAnimal";

/**
 * Subclass representing a pig on the farm.
 */
export class Pig extends FarmAnimal {
  constructor(id: number, breed: string, bornOn: Date) {
    super(id, breed, bornOn);
  }

  public override toString(): string {
    return `Pig{${super.toString()}}`;
  }
}
