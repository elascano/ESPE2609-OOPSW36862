
import { FarmAnimal } from "./FarmAnimal";
import { Food } from "./Food";
import { Location } from "./Location";
import { GrazeAnimalI } from "./GrazeAnimalI";

export class Sheep extends FarmAnimal implements GrazeAnimalI {
    private isSheared: boolean;
    private lastSheering: Date;

    constructor(isSheared: boolean, lastSheering: Date, id: number,
                breed: string, bornOnDate: Date, isMeat: boolean) {
        super(id, breed, bornOnDate, isMeat);
        this.isSheared = isSheared;
        this.lastSheering = lastSheering;
    }

    shearSheep(): void {
        console.log("Sheep is sheared");
    }

    feed(food: Food, amount: number): void {
        console.log(`feeding the sheep with ${amount} kg of ${food}`);
    }

    sendToGraze(location: Location, date: Date): void {
        console.log(`send the sheep to ${location} at ${date.getHours()} : ${date.getMinutes()}`);
    }

    toString(): string {
        return `Sheep{${super.toString()}isSheared=${this.isSheared}, lastSheering=${this.lastSheering.toString()}}`;
    }

    isIsSheared(): boolean {
        return this.isSheared;
    }

    setIsSheared(value: boolean): void {
        this.isSheared = value;
    }

    getLastSheering(): Date {
        return this.lastSheering;
    }

    setLastSheering(value: Date): void {
        this.lastSheering = value;
    }
}
