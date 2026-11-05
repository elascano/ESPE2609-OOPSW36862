import { Egg } from "./Egg";
import { Poop } from "./Poop";

export class Chicken {
    constructor(
        private id: number,
        private name: string,
        private color: string,
        private bornOnDate: Date,
        private ageInYears: number,
        private molting: boolean
    ) {}

    public doStuff(): void {
        this.cluck();
        this.wander();
        this.drink(1.3);
        console.log("poop--> " + this.poop().toString());
        console.log("egg--> " + this.layAnEgg().toString());
    }

    private cluck(): void {
        console.log(`The chicken ${this.name} is clucking`);
    }

    private wander(): void {
        console.log(`The chicken ${this.name} is wandering`);
    }

    private eat(): void {
        console.log(`The chicken ${this.name} is eating`);
    }

    private drink(amountOfWater: number): void {
        console.log(`The chicken ${this.name} is drinking --> ${amountOfWater} ml of water`);
    }

    private poop(): Poop {
        return new Poop(2, "green", "M");
    }

    private layAnEgg(): Egg {
        return new Egg(2, "S");
    }

    public computeAgeInYears(): number {
        const difference = Date.now() - this.bornOnDate.getTime();
        return Math.floor(difference / (1000 * 60 * 60 * 24 * 365));
    }

    public setAgeInYears(): void {
        this.ageInYears = this.computeAgeInYears();
    }

    public toString(): string {
        return `Chicken{id=${this.id}, name=${this.name}, color=${this.color}, bornOnDate=${this.bornOnDate.toDateString()}, age=${this.ageInYears}, molting=${this.molting}}`;
    }

    public getId(): number { return this.id; }
    public setId(id: number): void { this.id = id; }
    public getName(): string { return this.name; }
    public setName(name: string): void { this.name = name; }
    public getColor(): string { return this.color; }
    public setColor(color: string): void { this.color = color; }
    public getBornOnDate(): Date { return this.bornOnDate; }
    public setBornOnDate(date: Date): void { this.bornOnDate = date; }
    public getAgeInYears(): number { return this.ageInYears; }
    public isMolting(): boolean { return this.molting; }
    public setMolting(molting: boolean): void { this.molting = molting; }
}
