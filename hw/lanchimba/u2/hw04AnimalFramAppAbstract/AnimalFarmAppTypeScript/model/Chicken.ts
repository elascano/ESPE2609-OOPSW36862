import { FarmAnimal } from "./FarmAnimal";

/**
 * Concrete subclass representing a chicken on the farm.
 */
export class Chicken extends FarmAnimal {
  private isMolting: boolean;

  constructor(id: number, breed: string, bornOn: Date, isMolting: boolean) {
    super(id, breed, bornOn);
    this.isMolting = isMolting;
  }

  public override feed(): string {
    return `Chicken #${this.getId()} (${this.getBreed()}) is pecking grains and seeds.`;
  }

  public override makeSound(): string {
    return "Cluck, cluck!";
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
