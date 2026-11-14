/**
 * @author Mateo, Group5, @ESPE
 */
// "abstract": esta clase no se puede instanciar con new, solo heredar
export abstract class FarmAnimal {
  private _id: number;
  private _breed: string;
  private _bornOnDate: Date;
  private _isMeat: boolean;

  constructor(id: number, breed: string, bornOnDate: Date, isMeat: boolean) {
    this._id = id;
    this._breed = breed;
    this._bornOnDate = bornOnDate;
    this._isMeat = isMeat;
  }

  toString(): string {
    const d = this._bornOnDate;
    return `FarmAnimal{id=${this._id}, breed=${this._breed}, ` +
      `is meat=${this._isMeat}, ` +
      `bornOnDate=${d.getDate()} / ${d.getMonth() + 1} / ${d.getFullYear()}}, `;
  }

  get id(): number { return this._id; }
  set id(value: number) { this._id = value; }

  get breed(): string { return this._breed; }
  set breed(value: string) { this._breed = value; }

  get bornOnDate(): Date { return this._bornOnDate; }
  set bornOnDate(value: Date) { this._bornOnDate = value; }

  get isMeat(): boolean { return this._isMeat; }
  set isMeat(value: boolean) { this._isMeat = value; }
}
