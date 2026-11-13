import { FarmAnimal } from "./FarmAnimal";

export class Chicken extends FarmAnimal {
  private _isMolting: boolean;
  private _numberOfLaidEggInMonth: number;
  private _isMeat: boolean;

  constructor(
    isMolting: boolean,
    numberOfLaidEggInMonth: number,
    id: number,
    breed: string,
    bornOnDate: Date,
    isMeat: boolean = false,
  ) {
    super(id, breed, bornOnDate);
    this._isMolting = isMolting;
    this._numberOfLaidEggInMonth = numberOfLaidEggInMonth;
    this._isMeat = isMeat;
  }

  layAnEgg(): string {
    return "chicken is laying an egg()";
  }

  toString(): string {
    return `Chicken{${super.toString()}isMolting=${this._isMolting}, numberOfLaidEggInMonth=${this._numberOfLaidEggInMonth}, isMeat=${this._isMeat}}`;
  }

  get isMolting(): boolean {
    return this._isMolting;
  }

  set isMolting(isMolting: boolean) {
    this._isMolting = isMolting;
  }

  get numberOfLaidEggInMonth(): number {
    return this._numberOfLaidEggInMonth;
  }

  set numberOfLaidEggInMonth(numberOfLaidEggInMonth: number) {
    this._numberOfLaidEggInMonth = numberOfLaidEggInMonth;
  }

  get isMeat(): boolean {
    return this._isMeat;
  }

  set isMeat(isMeat: boolean) {
    this._isMeat = isMeat;
  }
}
