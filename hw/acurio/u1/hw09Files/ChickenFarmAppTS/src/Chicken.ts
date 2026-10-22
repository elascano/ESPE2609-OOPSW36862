import { Egg } from "./Egg";
import { Poop } from "./Poop";

export class Chicken {
    public id: number;
    public name: string;
    public color: string;
    public bornOnDate: Date;
    public ageInYears: number;
    public molting: boolean;

    constructor(id: number, name: string, color: string, bornOnDate: Date, molting: boolean) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.bornOnDate = bornOnDate;
        this.ageInYears = 0;
        this.molting = molting;
    }

    public doStuff(): void {
        this.cluck();
        this.wander();
        this.eat();
        this.drink(1.3);

        console.log("poop ----" + this.poop());
        console.log("egg----" + this.layAnEgg());
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
        console.log("The chicken " + this.name + " is drinking " + amountOfWater + " ml of water" );
    }

    private poop(): Poop {
        return new Poop(2, "green", "M");
    }

    private layAnEgg(): Egg {
        return new Egg(2, "S");
    }

    public computeAgeInYears(): number {
        const today = new Date();

        let age = today.getFullYear() - this.bornOnDate.getFullYear();

        const currentMonth = today.getMonth();
        const birthMonth = this.bornOnDate.getMonth();

        if (currentMonth < birthMonth || (currentMonth === birthMonth && today.getDate() < this.bornOnDate.getDate())) {
            age--;
        }

        this.ageInYears = age;
        return this.ageInYears;
    }

    public toString(): string {
        return "Chicken{" + "id=" + this.id + ", name=" + this.name + ", color=" + this.color + ", bornOnDate=" + this.bornOnDate.toISOString().split("T")[0] + ", age=" + this.computeAgeInYears() + ", molting=" + this.molting + '}';
    }
}