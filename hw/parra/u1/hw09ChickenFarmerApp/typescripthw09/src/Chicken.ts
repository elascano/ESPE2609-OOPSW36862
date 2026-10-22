import { Egg } from "./Egg";
import { Poop } from "./Poop";

function formatDate(d: Date): string {
  const mm = String(d.getMonth() + 1).padStart(2, "0");
  const dd = String(d.getDate()).padStart(2, "0");
  return `${d.getFullYear()}-${mm}-${dd}`;
}

export class Chicken {
  ageInYears = 0;

  constructor(
    public id: number,
    public name: string,
    public color: string,
    public bornOnDate: Date,
    public molting: boolean = false,
  ) {}

  doStuff(): void {
    this.cluck();
    this.wander();
    this.drink(1.3);
    console.log("poop-->" + this.poop());
    console.log("egg-->" + this.layAnEgg());
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
    console.log(`The chicken ${this.name} is drinking --> ${amountOfWater}ml of water`);
  }

  private poop(): Poop {
    return new Poop(2, "green", "M");
  }

  private layAnEgg(): Egg {
    return new Egg(2, "S");
  }

  computeAgeInYears(): number {
    const today = new Date();
    let age = today.getFullYear() - this.bornOnDate.getFullYear();
    const beforeBirthday =
      today.getMonth() < this.bornOnDate.getMonth() ||
      (today.getMonth() === this.bornOnDate.getMonth() &&
        today.getDate() < this.bornOnDate.getDate());
    if (beforeBirthday) age--;
    return age;
  }

  setAgeInYears(): void {
    this.ageInYears = this.computeAgeInYears();
  }

  getAgeInYears(): number {
    return this.ageInYears;
  }

  toString(): string {
    return (
      `Chicken{id=${this.id}, name=${this.name}, color=${this.color}, ` +
      `bornOnDate=${formatDate(this.bornOnDate)}, age=${this.ageInYears}, molting=${this.molting}}`
    );
  }
}
