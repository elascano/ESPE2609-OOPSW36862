import { Egg } from './Egg';
import { Poop } from './Poop';

export class Chicken {
  private ageInYears: number;

  constructor(
    private id: number,
    private name: string,
    private color: string,
    private bornOnDate: Date,
    private molting: boolean
  ) {
    this.ageInYears = this.computeAgeInYears();
  }

  public doStuff(): void {
    this.cluck();
    this.wander();
    this.drink(1.3);
    console.log(`poop --> ${this.poop()}`);
    console.log(`egg --> ${this.layAnEgg()}`);
  }

  private cluck(): void {
    console.log(`The chicken ${this.name} is clucking..`);
  }

  private wander(): void {
    console.log(`The chicken ${this.name} is wandering...`);
  }

  private eat(): void {
    console.log(`The chicken ${this.name} is eating...`);
  }

  private drink(amountOfWater: number): void {
    console.log(`The chicken ${this.name} is drinking ---> ${amountOfWater} ml of water`);
  }

  private poop(): Poop {
    return new Poop(2, 'green', 'M');
  }

  private layAnEgg(): Egg {
    return new Egg(2, '5');
  }

  public computeAgeInYears(): number {
    if (!this.bornOnDate) return 0;

    const today = new Date();
    let age = today.getFullYear() - this.bornOnDate.getFullYear();
    const monthDiff = today.getMonth() - this.bornOnDate.getMonth();

    if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < this.bornOnDate.getDate())) {
      age--;
    }

    return age;
  }

  public getId(): number { return this.id; }
  public setId(id: number): void { this.id = id; }

  public getName(): string { return this.name; }
  public setName(name: string): void { this.name = name; }

  public getColor(): string { return this.color; }
  public setColor(color: string): void { this.color = color; }

  public getBornOnDate(): Date { return this.bornOnDate; }
  public setBornOnDate(bornOnDate: Date): void {
    this.bornOnDate = bornOnDate;
    this.ageInYears = this.computeAgeInYears();
  }

  public getAgeInYears(): number { return this.ageInYears; }

  public isMolting(): boolean { return this.molting; }
  public setMolting(molting: boolean): void { this.molting = molting; }

  public toString(): string {
    return `Chicken{id=${this.id}, name='${this.name}', color='${this.color}', bornOnDate=${this.bornOnDate.toDateString()}, age=${this.ageInYears}, molting=${this.molting}}`;
  }
}