import { FarmAnimal } from "./FarmAnimal";

export class Cow extends FarmAnimal {
  private _isProducingMilk: boolean;
  private _litersOfMilkPerWeek: number;

  constructor(
    isProducingMilk: boolean,
    litersOfMilkPerWeek: number,
    id: number,
    breed: string,
    bornOnDate: Date,
    isMeat: boolean
  ) {
    super(id, breed, bornOnDate, isMeat);
    this._isProducingMilk = isProducingMilk;
    this._litersOfMilkPerWeek = litersOfMilkPerWeek;
  }

  milk(): void {
    console.log("cow is producing milk");
  }

  override toString(): string {
    return (
      `Cow{${super.toString()}isProducingMilk=${this.isProducingMilk}, ` +
      `litersOfMilkPerWeek=${this.litersOfMilkPerWeek}}`
    );
  }

  get isProducingMilk(): boolean {
    return this._isProducingMilk;
  }
  set isProducingMilk(value: boolean) {
    this._isProducingMilk = value;
  }

  get litersOfMilkPerWeek(): number {
    return this._litersOfMilkPerWeek;
  }
  set litersOfMilkPerWeek(value: number) {
    this._litersOfMilkPerWeek = value;
  }
}
