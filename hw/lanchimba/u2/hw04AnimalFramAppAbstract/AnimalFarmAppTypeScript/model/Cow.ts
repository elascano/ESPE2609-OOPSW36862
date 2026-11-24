import { FarmAnimal } from "./FarmAnimal";

/**
 * Concrete subclass representing a cow on the farm.
 */
export class Cow extends FarmAnimal {
  private isProducingMilk: boolean;

  constructor(id: number, breed: string, bornOn: Date, isProducingMilk: boolean) {
    super(id, breed, bornOn);
    this.isProducingMilk = isProducingMilk;
  }

  public override feed(): string {
    return `Cow #${this.getId()} (${this.getBreed()}) is grazing on fresh grass and hay.`;
  }

  public override makeSound(): string {
    return "Moo!";
  }

  public override produce(): string {
    if (this.isProducingMilk) {
      return "Producing fresh milk.";
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
