import { javaDateText } from "./JavaDate";

export class FarmAnimal {
  constructor(
    private id: number,
    private breed: string,
    private bornOnDate: Date,
    private isMeat: boolean
  ) {}

  public getId(): number { return this.id; }
  public setId(id: number): void { this.id = id; }
  public getBreed(): string { return this.breed; }
  public setBreed(breed: string): void { this.breed = breed; }
  public getBornOnDate(): Date { return this.bornOnDate; }
  public setBornOnDate(bornOnDate: Date): void { this.bornOnDate = bornOnDate; }
  public isIsMeat(): boolean { return this.isMeat; }
  public setIsMeat(isMeat: boolean): void { this.isMeat = isMeat; }

  public toString(): string {
    return `FarmAnimal{id=${this.id}, breed=${this.breed}, is meat=${this.isMeat}, ` +
      `bornOnDate=${javaDateText(this.bornOnDate)}}, `;
  }
}
