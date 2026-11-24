import { FarmAnimal } from "./FarmAnimal";

/**
 * Subclass representing a cow on the farm.
 */
export class Cow extends FarmAnimal {
  private isProducingMilk: boolean;

  constructor(id: number, breed: string, bornOn: Date, isProducingMilk: boolean) {
    super(id, breed, bornOn);
    this.isProducingMilk = isProducingMilk;
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
