import { Egg } from "./Egg";
import { Poop } from "./Poop";

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

    doStuff(): void {
        this.cluck();
        this.wander();
        this.drink(1.3);
        console.log("poop --> " + this.poop());
        console.log("egg --> " + this.layAnEgg());
    }

    private cluck(): void { console.log(`the chicken ${this.name} is clucking ...`); }
    private wander(): void { console.log(`the chicken ${this.name} is wandering ...`); }
    private eat(): void { console.log(`the chicken ${this.name} is eating ...`); }
    private drink(amountOfWater: number): void {
        console.log(`the chicken ${this.name} is drinking --> ${amountOfWater} ml of water`);
    }
    private poop(): Poop { return new Poop(2, "green", "M"); }
    private layAnEgg(): Egg { return new Egg(2, "S"); }

    computeAgeInYears(): number {
        const today = new Date();
        let age = today.getFullYear() - this.bornOnDate.getFullYear();
        if (today.getMonth() < this.bornOnDate.getMonth() ||
            (today.getMonth() === this.bornOnDate.getMonth() &&
             today.getDate() < this.bornOnDate.getDate())) {
            age--;
        }
        return age;
    }

    getId(): number { return this.id; }
    setId(id: number): void { this.id = id; }
    getName(): string { return this.name; }
    setName(name: string): void { this.name = name; }
    getColor(): string { return this.color; }
    setColor(color: string): void { this.color = color; }
    getBorn0nDate(): Date { return this.bornOnDate; }
    setBorn0nDate(bornOnDate: Date): void {
        this.bornOnDate = bornOnDate;
        this.setAgeInYears();
    }
    getAgeInYears(): number { return this.ageInYears; }
    setAgeInYears(ageInYears?: number): void {
        this.ageInYears = ageInYears === undefined ? this.computeAgeInYears() : ageInYears;
    }
    isMolting(): boolean { return this.molting; }
    setMolting(molting: boolean): void { this.molting = molting; }

    toString(): string {
        const born = this.bornOnDate.getFullYear() + "-" +
            String(this.bornOnDate.getMonth() + 1).padStart(2, "0") + "-" +
            String(this.bornOnDate.getDate()).padStart(2, "0");
        return `Chicken{id=${this.id}, name=${this.name}, color=${this.color}, born0nDate=${born}, age=${this.ageInYears}, molting=${this.molting}}`;
    }
}
