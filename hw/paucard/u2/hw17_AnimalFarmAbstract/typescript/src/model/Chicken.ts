import { FarmAnimal } from "./FarmAnimal";

export class Chicken extends FarmAnimal {
  private _isMolting: boolean;
  private _numberOfLaidEggsPerWeek: number;

  constructor(
    isMolting: boolean,
    numberOfLaidEggsPerWeek: number,
    id: number,
    breed: string,
    bornOnDate: Date
  ) {
    // Se mantiene el comportamiento del original en Java: `isMolting`
    // también se envía como `isMeat` a la clase base.
    super(id, breed, bornOnDate, isMolting);
    this._isMolting = isMolting;
    this._numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek;
  }

  layAnEgg(): void {
    console.log("chicken is laying an egg");
  }

  override toString(): string {
    return (
      `Chicken{${super.toString()}isMolting=${this.isMolting}, ` +
      `numberOfLaidEggsPerWeek=${this.numberOfLaidEggsPerWeek}}`
    );
  }

  get isMolting(): boolean {
    return this._isMolting;
  }
  set isMolting(value: boolean) {
    this._isMolting = value;
  }

  get numberOfLaidEggsPerWeek(): number {
    return this._numberOfLaidEggsPerWeek;
  }
  set numberOfLaidEggsPerWeek(value: number) {
    this._numberOfLaidEggsPerWeek = value;
  }
}
