import { FarmAnimal } from './FarmAnimal';
import { GrazeAnimal } from './GrazeAnimal';
import { Food } from './Food';
import { Location } from './Location';

export class Cow extends FarmAnimal implements GrazeAnimal {
    private isProducingMilk: boolean;
    private litersOfMilkPerWeek: number;

    constructor(isProducingMilk: boolean, litersOfMilkPerWeek: number, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }

    public sendtoGraze(location: Location): void {
        console.log(`Sending the cow to eat grass at ${location}`);
    }

    public feed(food: Food, amountOfFood: number): void {
        console.log(`feeding the cow with ${amountOfFood} of ${food}`);
    }

    public milk(amountOfLiters: number = 0, hour?: Date): void {
        if (amountOfLiters === 0 && !hour) {
            console.log("Milking the Cow I need");
        } else if (hour) {
            this.litersOfMilkPerWeek += amountOfLiters;
            console.log(`milking the cow for ${amountOfLiters}liters, at ${hour.getHours()}:${hour.getMinutes()}`);
        } else {
            this.litersOfMilkPerWeek += amountOfLiters;
            console.log(`Milking the Cow obtend  ${amountOfLiters}liters today\n`);
        }
    }

    public toString(): string {
        return `Cow{${super.toString()}isProducingMilk=${this.isProducingMilk}, litersOfMilkPerWeek=${this.litersOfMilkPerWeek}}`;
    }
}