import { Poop } from './Poop';
import { Egg } from './Egg';

export class Chicken {
  private _id: number;
  private _name: string;
  private _color: string;
  private _bornOnDate: Date | null;
  private _ageInYears: number;
  private _molting: boolean;

  constructor(
    id: number,
    name: string,
    color: string,
    bornOnDate: Date | null,
    age: number,
    molting: boolean
  ) {
    this._id = id;
    this._name = name;
    this._color = color;
    this._bornOnDate = bornOnDate;
    this._ageInYears = this.computeAgeInYears();
    this._molting = molting;
  }

  public doStuff(): void {
    this.cluck();
    this.wander();
    this.drink(1.3);
    console.log(`poop --> ${this.poop().toString()}`);
    console.log(`egg --> ${this.layAnEgg().toString()}`);
  }

  private cluck(): void {
    console.log(`The chicken ${this._name} is clucking...`);
  }

  private wander(): void {
    console.log(`The chicken ${this._name} is wandering...`);
  }

  private eat(): void {
    console.log(`The chicken ${this._name} is eating...`);
  }

  private drink(amount0Water: number): void {
    console.log(`The chicken ${this._name} is drinking --> ${amount0Water} ml of water`);
  }

  private poop(): Poop {
    return new Poop(2, 'green', 'M');
  }

  private layAnEgg(): Egg {
    return new Egg(2, '5');
  }

  public computeAgeInYears(): number {
    if (!this._bornOnDate) {
      return 0;
    }

    const today = new Date();
    const birth = new Date(this._bornOnDate);

    let age = today.getFullYear() - birth.getFullYear();
    const monthDiff = today.getMonth() - birth.getMonth();

    if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birth.getDate())) {
      age--;
    }

    return age;
  }

  public get id(): number {
    return this._id;
  }

  public set id(id: number) {
    this._id = id;
  }

  public get name(): string {
    return this._name;
  }

  public set name(name: string) {
    this._name = name;
  }

  public get color(): string {
    return this._color;
  }

  public set color(color: string) {
    this._color = color;
  }

  public get bornOnDate(): Date | null {
    return this._bornOnDate;
  }

  public set bornOnDate(bornOnDate: Date | null) {
    this._bornOnDate = bornOnDate;
    this._ageInYears = this.computeAgeInYears();
  }

  public get ageInYears(): number {
    return this._ageInYears;
  }

  public updateAge(): void {
    this._ageInYears = this.computeAgeInYears();
  }

  public isMolting(): boolean {
    return this._molting;
  }

  public set molting(molting: boolean) {
    this._molting = molting;
  }

  public toString(): string {
    return `Chicken{id=${this._id}, name=${this._name}, color=${this._color}, bornOnDate=${this._bornOnDate}, age=${this._ageInYears}, molting=${this._molting}}`;
  }
}