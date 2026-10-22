import { Egg } from './Egg';
import { Poop } from './Poop';

export class Chicken {
  private static nextEggId: number = 1;
  private static nextPoopId: number = 1;

  private id: number;
  private name: string;
  private color: string;
  private bornOnDate: Date;
  private ageInYears: number;
  private molting: boolean;

  constructor(id: number, name: string, color: string, bornOnDate: Date) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.bornOnDate = bornOnDate;
    this.ageInYears = this.computeAgeInYears();
    this.molting = false;
  }

  public doStuff(amountOfWater: number = 1.3): void {
    this.cluck();
    this.wander();
    this.eat();
    this.drink(amountOfWater);
    console.log(`poop --> ${Chicken.poop()}`);
    console.log(`egg --> ${Chicken.layAnEgg()}`);
  }

  private cluck(): void {
    console.log(`The chicken ${this.name} is clucking ...`);
  }

  private wander(): void {
    console.log(`The chicken ${this.name} is wandering ...`);
  }

  private eat(): void {
    console.log(`The chicken ${this.name} is eating ...`);
  }

  private drink(amountOfWater: number): void {
    console.log(`The chicken ${this.name} is drinking --> ${amountOfWater} ml of water`);
  }

  public static poop(): Poop {
    return new Poop(Chicken.nextPoopId++, 'green', 'M');
  }

  public static layAnEgg(): Egg {
    return new Egg(Chicken.nextEggId++, 'S');
  }

  public computeAgeInYears(): number {
    const today = new Date();
    let age = today.getFullYear() - this.bornOnDate.getFullYear();
    const monthDiff = today.getMonth() - this.bornOnDate.getMonth();

    if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < this.bornOnDate.getDate())) {
      age--;
    }

    return age;
  }

  private formatDate(date: Date): string {
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${date.getFullYear()}-${month}-${day}`;
  }

  public getId(): number {
    return this.id;
  }

  public setId(id: number): void {
    this.id = id;
  }

  public getName(): string {
    return this.name;
  }

  public setName(name: string): void {
    this.name = name;
  }

  public getColor(): string {
    return this.color;
  }

  public setColor(color: string): void {
    this.color = color;
  }

  public getBornOnDate(): Date {
    return this.bornOnDate;
  }

  public setBornOnDate(bornOnDate: Date): void {
    this.bornOnDate = bornOnDate;
    this.ageInYears = this.computeAgeInYears();
  }

  public getAgeInYears(): number {
    return this.ageInYears;
  }

  public setAgeInYears(ageInYears: number): void {
    this.ageInYears = ageInYears;
  }

  public isMolting(): boolean {
    return this.molting;
  }

  public setMolting(molting: boolean): void {
    this.molting = molting;
  }

  public toString(): string {
    return `Chicken{id=${this.id}, name=${this.name}, color=${this.color}, bornOnDate=${this.formatDate(this.bornOnDate)}, age=${this.ageInYears}, molting=${this.molting}}`;
  }
}