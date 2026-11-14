/**
 * @author Mateo, Group5, @ESPE
 */
import { FarmAnimal } from './FarmAnimal';

export class Sheep extends FarmAnimal {
  private _isSheared: boolean;
  private _lastSheering: Date;

  constructor(
    isSheared: boolean,
    lastSheering: Date,
    id: number,
    breed: string,
    bornOnDate: Date
  ) {
    super(id, breed, bornOnDate);
    this._isSheared = isSheared;
    this._lastSheering = lastSheering;
  }

  override toString(): string {
    return 'Sheep{' + super.toString() +
      `isSheared=${this._isSheared}, ` +
      `lastSheering=${this._lastSheering.getDate()}/${this._lastSheering.getMonth() + 1}/${this._lastSheering.getFullYear()}` +
      '}';
  }

  get isSheared(): boolean { return this._isSheared; }
  set isSheared(value: boolean) { this._isSheared = value; }

  get lastSheering(): Date { return this._lastSheering; }
  set lastSheering(value: Date) { this._lastSheering = value; }
}
