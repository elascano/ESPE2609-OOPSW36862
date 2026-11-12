import { FarmAnimal } from "./FarmAnimal";

export class Chicken extends FarmAnimal {
  constructor(
    private isMolting: boolean,
    private numberOfLaidEggsPerWeek: number,
    id: number,
    breed: string,
    bornOnDate: Date
  ) {
    super(id, breed, bornOnDate, isMolting);
  }

  public isIsMolting(): boolean { return this.isMolting; }
  public setIsMolting(value: boolean): void { this.isMolting = value; }
  public getNumberOfLaidEggsPerWeek(): number { return this.numberOfLaidEggsPerWeek; }
  public setNumberOfLaidEggsPerWeek(value: number): void { this.numberOfLaidEggsPerWeek = value; }

  public override toString(): string {
    return `Chicken{` + super.toString() +
      `isMolting=${this.isMolting}, numberOfLaidEggsPerWeek=${this.numberOfLaidEggsPerWeek}` + "}";
  }
}
