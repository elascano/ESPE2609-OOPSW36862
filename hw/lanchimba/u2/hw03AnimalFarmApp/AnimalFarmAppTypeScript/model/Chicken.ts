import { FarmAnimal } from "./FarmAnimal";

/**
 * Subclass representing a chicken on the farm.
 */
export class Chicken extends FarmAnimal {
  private isMolting: boolean;

  constructor(id: number, breed: string, bornOn: Date, isMolting: boolean) {
    super(id, breed, bornOn);
    this.isMolting = isMolting;
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
