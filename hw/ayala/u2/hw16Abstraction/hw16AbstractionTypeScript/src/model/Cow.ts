import { FarmAnimal } from "./FarmAnimal";

export class Cow extends FarmAnimal {
  private _isProducingMilk: boolean;
  private _litersOfMilkForWeek: number;

  constructor(
    isProducingMilk: boolean,
    litersOfMilkForWeek: number,
    id: number,
    breed: string,
    bornOnDate: Date,
  ) {
    super(id, breed, bornOnDate);
    this._isProducingMilk = isProducingMilk;
    this._litersOfMilkForWeek = litersOfMilkForWeek;
  }

  milk(): string {
    return "milking the cow";
  }

  toString(): string {
    return `Cow{${super.toString()}isProducingMilk=${this._isProducingMilk}, litersOfMilkForWeek=${this._litersOfMilkForWeek}}`;
  }

  get isProducingMilk(): boolean {
    return this._isProducingMilk;
  }

  set isProducingMilk(isProducingMilk: boolean) {
    this._isProducingMilk = isProducingMilk;
  }

  get litersOfMilkForWeek(): number {
    return this._litersOfMilkForWeek;
  }

  set litersOfMilkForWeek(litersOfMilkForWeek: number) {
    this._litersOfMilkForWeek = litersOfMilkForWeek;
  }
}
