import { FarmAnimal } from "./FarmAnimal";

export class Cow extends FarmAnimal {
  constructor(
    private isProducingMilk: boolean,
    private litersOfMilkPerWeek: number,
    id: number,
    breed: string,
    bornOnDate: Date,
    isMeat: boolean
  ) {
    super(id, breed, bornOnDate, isMeat);
  }

  public isIsProducingMilk(): boolean { return this.isProducingMilk; }
  public setIsProducingMilk(value: boolean): void { this.isProducingMilk = value; }
  public getLitersOfMilkPerWeek(): number { return this.litersOfMilkPerWeek; }
  public setLitersOfMilkPerWeek(value: number): void { this.litersOfMilkPerWeek = value; }

  public override toString(): string {
    return `Cow{` + super.toString() +
      `isProducingMilk=${this.isProducingMilk}, litersOfMilkPerWeek=${this.litersOfMilkPerWeek}` + "}";
  }
}
