/**
 * @author Mateo, Group5, @ESPE
 */
import { FarmAnimal } from './FarmAnimal';

export class Pig extends FarmAnimal {
  private _isWallowing: boolean;
  private _weightGainPerWeek: number;

  constructor(
    isWallowing: boolean,
    weightGainPerWeek: number,
    id: number,
    breed: string,
    bornOnDate: Date,
    isMeat: boolean
  ) {
    super(id, breed, bornOnDate, isMeat);
    this._isWallowing = isWallowing;
    this._weightGainPerWeek = weightGainPerWeek;
  }

  override toString(): string {
    return 'Pig{' + super.toString() +
      `isWallowing=${this._isWallowing}, ` +
      `weightGainPerWeek=${this._weightGainPerWeek}` +
      '}';
  }

  get isWallowing(): boolean { return this._isWallowing; }
  set isWallowing(value: boolean) { this._isWallowing = value; }

  get weightGainPerWeek(): number { return this._weightGainPerWeek; }
  set weightGainPerWeek(value: number) { this._weightGainPerWeek = value; }
}
