import { Egg } from "./Egg.ts";
import { Poop } from "./Poop.ts";

/**
 * @author Andres Pachacama, Byte Work, @ESPE
 */
export class Chicken {
    id: number;
    name: string;
    color: string;
    bornOnDate: Date;
    ageInYears: number;
    molting: boolean;

    constructor(id: number, name: string, color: string, bornOnDate: Date, ageInYears: number, molting: boolean) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.bornOnDate = bornOnDate;
        this.ageInYears = ageInYears;
        this.molting = molting;
    }

    doStuff(): void {
        this.cluck();
        this.wander();
        this.drink(1.3);
        console.log("poop ---> " + this.poop());
        console.log("egg ---> " + this.layAnEgg());
    }

    private cluck(): void {
        console.log("The chicken " + this.name + " is clucking ...");
    }

    private wander(): void {
        console.log("The chicken " + this.name + " is wandering ...");
    }

    private eat(): void {
        console.log("The chicken " + this.name + " is eating ...");
    }

    private drink(amountOfWater: number): void {
        console.log("The chicken " + this.name + " is drinking --> " + amountOfWater + "ml of water");
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


        if (today.getMonth() < this.bornOnDate.getMonth()) {
            age--;
        } else if (today.getMonth() === this.bornOnDate.getMonth()
                && today.getDate() < this.bornOnDate.getDate()) {
            age--;
        }

        return age;
    }

    setAgeInYears(): void {
        this.ageInYears = this.computeAgeInYears();
    }

    toString(): string {
        return "\n Chicken{id=" + this.id + ", name=" + this.name + ", color=" + this.color
            + ", bornOnDate=" + this.bornOnDate.toDateString() + ", age=" + this.ageInYears
            + ", molting=" + this.molting + "}";
    }
}
