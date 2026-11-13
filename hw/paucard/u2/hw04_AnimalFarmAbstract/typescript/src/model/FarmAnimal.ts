import { formatDate } from "../util";

/** Clase abstracta base para todos los animales de la granja. */
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
    return (
      `FarmAnimal{id=${this.id}, breed=${this.breed}, is meat=${this.isMeat}, ` +
      `bornOnDate=${formatDate(this.bornOnDate)}}, `
    );
  }

  get id(): number {
    return this._id;
  }
  set id(value: number) {
    this._id = value;
  }

  get breed(): string {
    return this._breed;
  }
  set breed(value: string) {
    this._breed = value;
  }

  get bornOnDate(): Date {
    return this._bornOnDate;
  }
  set bornOnDate(value: Date) {
    this._bornOnDate = value;
  }

  get isMeat(): boolean {
    return this._isMeat;
  }
  set isMeat(value: boolean) {
    this._isMeat = value;
  }
}
