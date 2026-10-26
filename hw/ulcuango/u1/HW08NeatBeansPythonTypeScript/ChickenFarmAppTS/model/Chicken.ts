import { Egg } from "./Egg";
import { Poop } from "./Poop";

/**
 * @author Josselyn Ulcuango, Codelex, @ESPE
 */
export class Chicken {
    private id: number;
    private name: string;
    private color: string;
    private bornOnDate: Date;
    private ageInYear: number;
    private molting: boolean;

    constructor(id: number, name: string, color: string, bornOnDate: Date, age: number, molting: boolean) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.bornOnDate = bornOnDate;
        this.ageInYear = age;
        this.molting = molting;
    }

    public doStuff(): void {
        this.cluck();
        this.wander();
        this.drink(1.3);
        console.log(`poop --> ${this.poop().toString()}`);
        console.log(`egg --> ${this.layAnEgg().toString()}`);
    }

    private cluck(): void {
        console.log(`The chicken ${this.name} is clucking`);
    }

    private wander(): void {
        console.log(`The chicken ${this.name} is wandering`);
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
        const today = new Date();
        const birth = this.bornOnDate;
        let age = today.getFullYear() - birth.getFullYear();
        const m = today.getMonth() - birth.getMonth();
        if (m < 0 || (m === 0 && today.getDate() < birth.getDate())) {
            age--;
        }
        return age;
    }

    public toString(): string {
        return `Chicken{id=${this.id}, name=${this.name}, color=${this.color}, bornOnDate=${this.bornOnDate.toDateString()}, age=${this.ageInYear}, molting=${this.molting}}`;
    }

    public getId(): number { return this.id; }
    public setId(id: number): void { this.id = id; }
    public getName(): string { return this.name; }
    public setName(name: string): void { this.name = name; }
    public getColor(): string { return this.color; }
    public setColor(color: string): void { this.color = color; }
    public getBornOnDate(): Date { return this.bornOnDate; }
    public setBornOnDate(bornOnDate: Date): void { this.bornOnDate = bornOnDate; }
    public getAgeInYear(): number { return this.ageInYear; }
    public setAgeInYear(age: number): void { this.ageInYear = age; }
    public isMolting(): boolean { return this.molting; }
    public setMolting(molting: boolean): void { this.molting = molting; }
}