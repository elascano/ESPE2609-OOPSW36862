import { FarmAnimal } from "./FarmAnimal";
import { Food } from "./Food";
import { GrazeAnimalI } from "./GrazeAnimalI";
import { Location } from "./Location";

export class Sheep extends FarmAnimal implements GrazeAnimalI {
    constructor(
        private isSheared: boolean,
        private lastSheering: Date,
        id: number,
        breed: string,
        bornOnDate: Date,
        isMeat: boolean
    ) {
        super(id, breed, bornOnDate, isMeat);
    }

    public feed(food: Food, amount: number): void {
        console.log(`feeding the sheep with ${amount} kg of ${food}`);
    }

    public sendToGraze(location: Location, date: Date): void {
        console.log(`send the sheep to ${location} at ${date.getHours()} : ${date.getMinutes()}`);
    }

    public shearSheep(): void {
        console.log("Sheep is sheared");
    }

    public getLastSheering(): Date {
        return this.lastSheering;
    }

    private formatJavaDate(date: Date): string {
        const weekdays = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
        const months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
        const day = String(date.getDate()).padStart(2, "0");
        const hours = String(date.getHours()).padStart(2, "0");
        const minutes = String(date.getMinutes()).padStart(2, "0");
        const seconds = String(date.getSeconds()).padStart(2, "0");
        return `${weekdays[date.getDay()]} ${months[date.getMonth()]} ${day} ${hours}:${minutes}:${seconds} UTC ${date.getFullYear()}`;
    }

    public override toString(): string {
        return `Sheep{${super.toString()}isSheared=${this.isSheared}, lastSheering=${this.formatJavaDate(this.lastSheering)}}`;
    }
}
