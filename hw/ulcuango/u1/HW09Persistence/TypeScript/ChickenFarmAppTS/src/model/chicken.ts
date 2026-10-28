import { Poop } from './poop';
import { Egg } from './egg';

export class Chicken {
    private id: number;
    private name: string;
    private color: string;
    private bornOnDate: Date;
    private ageInYear: number;
    private molting: boolean;

    constructor(id: number = 0, name: string = '', color: string = '', bornOnDate: Date = new Date(), ageInYear: number = 0, molting: boolean = false) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.bornOnDate = bornOnDate;
        this.ageInYear = ageInYear;
        this.molting = molting;
    }

    public doStuff(): void {
        this.cluck();
        this.wander();
        this.drink(1.3);
        console.log("poop --> " + this.poop().toString());
        console.log("egg --> " + this.layAnEgg().toString());
    }

    private cluck(): void {
        console.log("The chicken " + this.name + " is clucking");
    }

    private wander(): void {
        console.log("The chicken " + this.name + " is wandering");
    }

    private eat(): void {
        console.log("The chicken " + this.name + " is eating");
    }

    private drink(amountOfWater: number): void {
        console.log("The chicken " + this.name + " is drinking --> " + amountOfWater + " ml of water");
    }

    private poop(): Poop {
        let poopObj = new Poop(2, "green", 'M');
        return poopObj;
    }

    private layAnEgg(): Egg {
        let egg = new Egg(2, 'S');
        return egg;
    }

    public computeAgeInYears(): number {
        let birth = new Date(this.bornOnDate);
        let today = new Date();
        let age = today.getFullYear() - birth.getFullYear();
        let monthDiff = today.getMonth() - birth.getMonth();
        if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birth.getDate())) {
            age--;
        }
        return age;
    }

    public toString(): string {
        return "Chicken{id=" + this.id + ", name=" + this.name + ", color=" + this.color + ", bornOnDate=" + this.bornOnDate + ", age=" + this.ageInYear + ", molting=" + this.molting + "}";
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