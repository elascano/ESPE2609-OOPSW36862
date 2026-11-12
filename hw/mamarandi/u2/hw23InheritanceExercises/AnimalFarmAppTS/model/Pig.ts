import { FarmAnimal } from "./FarmAnimal";

export class Pig extends FarmAnimal {
  constructor(
    private isWallowing: boolean,
    private weightGainPerWeek: number,
    id: number,
    breed: string,
    bornOnDate: Date,
    isMeat: boolean
  ) {
    super(id, breed, bornOnDate, isMeat);
  }

  public isIsWallowing(): boolean { return this.isWallowing; }
  public setIsWallowing(value: boolean): void { this.isWallowing = value; }
  public getWeightGainPerWeek(): number { return this.weightGainPerWeek; }
  public setWeightGainPerWeek(value: number): void { this.weightGainPerWeek = value; }

  public override toString(): string {
    return `Pig{` + super.toString() +
      `isWallowing=${this.isWallowing}, weightGainPerWeek=${this.weightGainPerWeek}` + "}";
  }
}
