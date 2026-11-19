export class FarmAnimal {
  private _id: number;
  private _breed: string;
  private _bornOnDate: Date;

  constructor(id: number, breed: string, bornOnDate: Date) {
    this._id = id;
    this._breed = breed;
    this._bornOnDate = bornOnDate;
  }

  getAgeInMonths(): number {
    return 0;
  }

  toString(): string {
    return (
      `id=${this._id}, breed=${this._breed}, ` +
      `bornOnDate=${this._bornOnDate.getDate()}/${this._bornOnDate.getMonth() + 1}/${this._bornOnDate.getFullYear()},`
    );
  }

  get id(): number {
    return this._id;
  }

  set id(id: number) {
    this._id = id;
  }

  get breed(): string {
    return this._breed;
  }

  set breed(breed: string) {
    this._breed = breed;
  }

  get bornOnDate(): Date {
    return this._bornOnDate;
  }

  set bornOnDate(bornOnDate: Date) {
    this._bornOnDate = bornOnDate;
  }
}
