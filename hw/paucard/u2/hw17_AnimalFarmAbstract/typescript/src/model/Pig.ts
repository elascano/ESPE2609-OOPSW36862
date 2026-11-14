import { FarmAnimal } from "./FarmAnimal";

export class Pig extends FarmAnimal {
  static readonly MEAT_CUTS: readonly string[] = [
    "head", "cheek", "picnic", "clear plate", "boston shoulder", "back fat",
    "loin", "ribs", "bacon", "leg", "ham", "hock",
  ];

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

  showPigMeatCuts(): void {
    console.log("Avalaible cuts for the pig[" + Pig.MEAT_CUTS.join(", ") + "]");
  }

  override toString(): string {
    return (
      `Pig{${super.toString()}isWallowing=${this.isWallowing}, ` +
      `weightGainPerWeek=${this.weightGainPerWeek}}`
    );
  }

  get isWallowing(): boolean {
    return this._isWallowing;
  }
  set isWallowing(value: boolean) {
    this._isWallowing = value;
  }

  get weightGainPerWeek(): number {
    return this._weightGainPerWeek;
  }
  set weightGainPerWeek(value: number) {
    this._weightGainPerWeek = value;
  }
}
