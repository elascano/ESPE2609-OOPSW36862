import { FarmAnimal } from "./FarmAnimal";

export class Chicken extends FarmAnimal {
  private molting: boolean;
  private numberOfLastEggsPerWeek: number;
  private meat: boolean;

  constructor(
    isMolting: boolean,
    numberOfLastEggsPerWeek: number,
    isMeat: boolean,
    id: number,
    breed: string,
    bornOnDate: Date
  ) {
    super(id, breed, bornOnDate);
    this.molting = isMolting;
    this.numberOfLastEggsPerWeek = numberOfLastEggsPerWeek;
    this.meat = isMeat;
  }

  public toString(): string {
    return (
      "Chicken{" +
      super.toString() +
      `isMolting=${this.molting}, ` +
      `numberOfLastEggsPerWeek=${this.numberOfLastEggsPerWeek}, ` +
      `isMeat=${this.meat}` +
      "}"
    );
  }

  public isMolting(): boolean {
    return this.molting;
  }

  public setIsMolting(isMolting: boolean): void {
    this.molting = isMolting;
  }

  public getNumberOfLastEggsPerWeek(): number {
    return this.numberOfLastEggsPerWeek;
  }

  public setNumberOfLastEggsPerWeek(numberOfLastEggsPerWeek: number): void {
    this.numberOfLastEggsPerWeek = numberOfLastEggsPerWeek;
  }

  public isMeat(): boolean {
    return this.meat;
  }

  public setIsMeat(isMeat: boolean): void {
    this.meat = isMeat;
  }
}
