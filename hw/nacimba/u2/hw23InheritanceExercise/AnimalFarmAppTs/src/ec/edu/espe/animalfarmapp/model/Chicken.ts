/**
 * @author Mateo, Group5, @ESPE
 */
import { FarmAnimal } from './FarmAnimal';

export class Chicken extends FarmAnimal {
  private _isMolting: boolean;
  private _numberOfLaidEggsInAMonth: number;
  private _isMeat: boolean;

  constructor(
    isMolting: boolean,
    numberOfLaidEggsInAMonth: number,
    isMeat: boolean,
    id: number,
    breed: string,
    bornOnDate: Date
  ) {
    super(id, breed, bornOnDate);
    this._isMolting = isMolting;
    this._numberOfLaidEggsInAMonth = numberOfLaidEggsInAMonth;
    this._isMeat = isMeat;
  }

  override toString(): string {
    return 'Chicken{' + super.toString() +
      `isMolting=${this._isMolting}, ` +
      `numberOfLaidEggsInAMonth=${this._numberOfLaidEggsInAMonth}, ` +
      `isMeat=${this._isMeat}` +
      '}';
  }

  get isMolting(): boolean { return this._isMolting; }
  set isMolting(value: boolean) { this._isMolting = value; }

  get numberOfLaidEggsInAMonth(): number { return this._numberOfLaidEggsInAMonth; }
  set numberOfLaidEggsInAMonth(value: number) { this._numberOfLaidEggsInAMonth = value; }

  get isMeat(): boolean { return this._isMeat; }
  set isMeat(value: boolean) { this._isMeat = value; }
}
