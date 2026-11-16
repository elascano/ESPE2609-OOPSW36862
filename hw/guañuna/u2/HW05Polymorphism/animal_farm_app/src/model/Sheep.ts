import { FarmAnimal } from './FarmAnimal';
import { GrazeAnimalI } from './GrazeAnimalI';
import { Food } from './Food';
import { Location } from './Location';

export class Sheep extends FarmAnimal implements GrazeAnimalI {
    private lastSheering: Date;
    private woolWeightInKg: number;

    constructor(lastSheering: Date, woolWeightInKg: number, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.lastSheering = lastSheering;
        this.woolWeightInKg = woolWeightInKg;
    }

    public feed(food: Food, amount: number): void {
        console.log(`Feeding a sheep with ---> ${amount} of ${food.toString()}`);
    }

    public senToGraze(location: Location, date: Date): void {
        console.log(`Sending the sheep to ---> ${location.toString()} at ${date.toString()}`);
    }

    // Sobrecarga para shear
    public shear(): void;
    public shear(kgOfWool: number): void;
    public shear(kgOfWool?: number): void {
        if (kgOfWool === undefined) {
            console.log(`Shearing the sheep casually. Wool remains at: ${this.woolWeightInKg} kg.`);
        } else {
            this.woolWeightInKg += kgOfWool;
            console.log(`Shearing the sheep for ${kgOfWool} kg of wool. Total wool collected: ${this.woolWeightInKg} kg.`);
        }
    }

    public toString(): string {
        return `Sheep{${super.toString()}lastSheering=${this.lastSheering.getDate()}/${this.lastSheering.getMonth() + 1}/${this.lastSheering.getFullYear()}, woolWeightInKg=${this.woolWeightInKg}}`;
    }
}