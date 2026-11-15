import { FarmAnimal } from './FarmAnimal';
import { GrazeAnimalI } from './GrazeAnimalI';
import { Location } from './Location';
import { Food } from './Food';

export class Cow extends FarmAnimal implements GrazeAnimalI {
    private isProducingMilk: boolean;
    private litersOfMilkPerWeek: number;

    constructor(isProducingMilk: boolean, litersOfMilkPerWeek: number, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }

    public sendToGraze(location: Location, date: Date): void {
        console.log(`Sending the cow to ---> ${location.toString()} at ${date.toString()}`);
    }

    public feed(food: Food, amount: number): void {
        console.log(`Feeding a cow with ---> ${amount} of ${food.toString()}`);
    }

    public milk(): void;
    public milk(amountOfLiters: number): void;
    public milk(amountOfLiters: number, hour: Date): void;

    public milk(amountOfLiters?: number, hour?: Date): void {
        if (amountOfLiters !== undefined && hour !== undefined) {
            this.litersOfMilkPerWeek += amountOfLiters;
            const formattedTime = `${hour.getHours().toString().padStart(2, '0')}:${hour.getMinutes().toString().padStart(2, '0')}`;
            console.log(`Milking the cow for ${amountOfLiters} liters, at ${formattedTime}`);
        } else if (amountOfLiters !== undefined) {
            this.litersOfMilkPerWeek += amountOfLiters;
            console.log(`Milking the cow: ${amountOfLiters} liters`);
        } else {
            console.log("Milking the cow as I need");
        }
    }

    public isProducingMilkStat(): boolean { return this.isProducingMilk; }
    public getLitersOfMilkPerWeek(): number { return this.litersOfMilkPerWeek; }

    public toString(): string {
        return `Cow{${super.toString()}, isProducingMilk=${this.isProducingMilk}, litersOfMilkPerWeek=${this.litersOfMilkPerWeek}}`;
    }
}