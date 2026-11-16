import { FarmAnimal } from "./FarmAnimal";
import { GrazeAnimal } from "./GrazeAnimal";
import { Food } from "./Food";
import { Location } from "./Location";

export class Cow extends FarmAnimal implements GrazeAnimal {
    private isProducingMilk: boolean;
    private litersOfMilkPerWeek: number;

    constructor(isProducingMilk: boolean, litersOfMilkPerWeek: number, id: number, breed: string, bornOnDate: Date, isMeat: boolean) {
        super(id, breed, bornOnDate, isMeat);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }

    public milk(): void;
    public milk(liters: number): void;
    public milk(liters?: number): void {
        if (liters === undefined) {
            console.log(`this cow has produced --->${this.litersOfMilkPerWeek}liters per week`);
        } else {
            this.litersOfMilkPerWeek += liters;
            console.log(`milking the cow... we got --->${liters} liters today`);
            console.log(`this cow has produced --->${this.litersOfMilkPerWeek}liters per week`);
        }
    }

    public feed(food: Food, amount: number): void {
        console.log(`feeding the cow with ${amount} of ${food.toString()}`);
    }

    public sendToGraze(location: Location, date: Date): void {
        console.log(`send the cow to ${location.toString()} at ${date.getHours()} : ${date.getMinutes()}`);
    }

    public toString(): string {
        return `Cow{${super.toString()}, isProducingMilk=${this.isProducingMilk}, litersPerWeek=${this.litersOfMilkPerWeek}}`;
    }
}