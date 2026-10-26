import { Egg } from "./egg";
import { Poop } from "./poop";

function formatDate(date: Date): string {
    const y = date.getFullYear();
    const m = String(date.getMonth() + 1).padStart(2, "0");
    const d = String(date.getDate()).padStart(2, "0");
    return `${y}-${m}-${d}`;
}

export class Chicken {
    id: number;
    name: string;
    color: string;
    bornOnDate: Date;
    ageInYears: number;
    molting: boolean;
    poop: Poop | null;

    constructor(
        id: number,
        name: string,
        color: string,
        bornOnDate: Date,
        molting: boolean,
        poop: Poop | null = null
    ) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.bornOnDate = bornOnDate;
        this.ageInYears = this.computeAgeInYears();
        this.molting = molting;
        this.poop = poop;
    }

    doStuff(): void {
        this.cluck();
        this.wander();
        this.eat();
        this.drink(1.3);
        console.log("poop --> " + this.makePoop());
        console.log("egg --> " + this.layAnEgg());
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
        console.log(`The chicken ${this.name} is drinking ${amountOfWater} ml of water ...`);
    }

    private makePoop(): Poop {
        if (this.poop === null) {
            this.poop = new Poop(this.id, "green", "M");
        }
        return this.poop;
    }

    private layAnEgg(): Egg {
        return new Egg(this.id, "S");
    }

    computeAgeInYears(): number {
        const today = new Date();
        const birth = this.bornOnDate;

        let age = today.getFullYear() - birth.getFullYear();

        if (
            today.getMonth() < birth.getMonth() ||
            (today.getMonth() === birth.getMonth() && today.getDate() < birth.getDate())
        ) {
            age--;
        }
        return age;
    }

    /** Recalculates the age using the current date and the birth date. */
    updateAgeInYears(): void {
        this.ageInYears = this.computeAgeInYears();
    }

    setBornOnDate(bornOnDate: Date): void {
        this.bornOnDate = bornOnDate;
        this.ageInYears = this.computeAgeInYears();
    }

    toString(): string {
        return `Chicken{id=${this.id}, name=${this.name}, color=${this.color}, ` +
            `bornOnDate=${formatDate(this.bornOnDate)}, age=${this.ageInYears}, ` +
            `molting=${this.molting}, poop=${this.poop}}`;
    }
}
