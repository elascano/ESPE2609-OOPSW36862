import { FarmAnimal } from "./FarmAnimal";
import { GrazeAnimal } from "./GrazeAnimal";
import { Food } from "./Food";
import { Location } from "./Location";

export class Sheep extends FarmAnimal implements GrazeAnimal {
    private isSheared: boolean;
    private lastSheering: Date;

    constructor(isSheared: boolean, lastSheering: Date, id: number, breed: string, bornOnDate: Date, isMeat: boolean) {
        super(id, breed, bornOnDate, isMeat);
        this.isSheared = isSheared;
        this.lastSheering = lastSheering;
    }

    public shearSheep(): void {
        console.log("Sheep is sheared");
    }

    public feed(food: Food, amount: number): void {
        console.log(`feeding the sheep with ${amount} kg of ${food.toString()}`);
    }

    public sendToGraze(location: Location, date: Date): void {
        console.log(`send the sheep to ${location.toString()} at ${date.getHours()} : ${date.getMinutes()}`);
    }

    public toString(): string {
        return `Sheep{${super.toString()}, isSheared=${this.isSheared}, lastSheering=${this.lastSheering.toDateString()}}`;
    }
}