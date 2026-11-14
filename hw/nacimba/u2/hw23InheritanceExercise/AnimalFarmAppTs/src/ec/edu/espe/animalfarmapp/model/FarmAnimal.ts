/**
 * @author Mateo, Group5, @ESPE
 */
export class FarmAnimal {
  private _id: number;
  private _breed: string;
  private _bornOnDate: Date;

  constructor(id: number, breed: string, bornOnDate: Date) {
    this._id = id;
    this._breed = breed;
    this._bornOnDate = bornOnDate;
  }

  toString(): string {
    const d = this._bornOnDate;
    return `id=${this._id}, breed=${this._breed}, ` +
      `bornOnDate=${d.getDate()}/${d.getMonth() + 1}/${d.getFullYear()}, `;
  }

  getAgeInMonths(): number {
    // TODO: calcular la edad en meses
    return 0;
  }

  get id(): number { return this._id; }
  set id(value: number) { this._id = value; }

  get breed(): string { return this._breed; }
  set breed(value: string) { this._breed = value; }

  get bornOnDate(): Date { return this._bornOnDate; }
  set bornOnDate(value: Date) { this._bornOnDate = value; }
}
