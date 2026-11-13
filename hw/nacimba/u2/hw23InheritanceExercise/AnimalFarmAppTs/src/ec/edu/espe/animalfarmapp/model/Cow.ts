/**
 * @author Mateo, Group5, @ESPE
 */
import { FarmAnimal } from './FarmAnimal';

export class Cow extends FarmAnimal {
  private _isProducingMilk: boolean;
  private _litersOfMilkPerWeek: number;

  constructor(
    isProducingMilk: boolean,
    litersOfMilkPerWeek: number,
    id: number,
    breed: string,
    bornOnDate: Date
  ) {
    super(id, breed, bornOnDate);
    this._isProducingMilk = isProducingMilk;
    this._litersOfMilkPerWeek = litersOfMilkPerWeek;
  }

  override toString(): string {
    return 'Cow{' + super.toString() +
      `isProducingMilk=${this._isProducingMilk}, ` +
      `litersOfMilkPerWeek=${this._litersOfMilkPerWeek}` +
      '}';
  }

  milk(): void {
    console.log('Milking the cow ');
  }

  get isProducingMilk(): boolean { return this._isProducingMilk; }
  set isProducingMilk(value: boolean) { this._isProducingMilk = value; }

  get litersOfMilkPerWeek(): number { return this._litersOfMilkPerWeek; }
  set litersOfMilkPerWeek(value: number) { this._litersOfMilkPerWeek = value; }
}
