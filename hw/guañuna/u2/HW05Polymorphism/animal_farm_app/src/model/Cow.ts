import { FarmAnimal } from './FarmAnimal';
import { GrazeAnimalI } from './GrazeAnimalI';
import { Food } from './Food';
import { Location } from './Location';

export class Cow extends FarmAnimal implements GrazeAnimalI {
    private isProducingMilk: boolean;
    private litersOfMilkPerWeek: number;

    constructor(isProducingMilk: boolean, litersOfMilkPerWeek: number, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }

    public feed(food: Food, amount: number): void {
        console.log(`Feeding a cow with ---> ${amount} of ${food.toString()}`);
    }

    public senToGraze(location: Location, date: Date): void {
        console.log(`Sending the cow to ---> ${location.toString()} at ${date.toString()}`);
    }

    // Sobrecarga para milk
    public milk(): void;
    public milk(amountOfLiters: number, hour?: Date): void;
    public milk(amountOfLiters?: number, hour?: Date): void {
        if (amountOfLiters === undefined) {
            console.log("Milking the cow as I need");
        } else if (hour !== undefined) {
            this.litersOfMilkPerWeek += amountOfLiters;
            const timeString = hour.toTimeString().substring(0, 5);
            console.log(`Milking the cow for ${amountOfLiters} liters, at ${timeString}`);
        } else {
            this.litersOfMilkPerWeek += amountOfLiters;
            console.log(`Milking the cow: ${amountOfLiters} liters`);
        }
    }

    public getLitersOfMilkPerWeek(): number { return this.litersOfMilkPerWeek; }

    public toString(): string {
        return `Cow{${super.toString()}isProducingMilk=${this.isProducingMilk}, litersOfMilkPerWeek=${this.litersOfMilkPerWeek}}`;
    }
}