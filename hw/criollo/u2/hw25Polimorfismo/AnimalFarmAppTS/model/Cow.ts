import { FarmAnimal } from "./FarmAnimal";
import { Food } from "./Food";
import { GrazeAnimalI } from "./GrazeAnimalI";
import { Location } from "./Location";

export class Cow extends FarmAnimal implements GrazeAnimalI {
    constructor(
        private isProducingMilk: boolean,
        private litersOfMilkPerWeek: number,
        id: number,
        breed: string,
        bornOnDate: Date,
        isMeat: boolean
    ) {
        super(id, breed, bornOnDate, isMeat);
    }

    public feed(food: Food, amount: number): void {
        console.log(`feeding the cow with ${amount} of ${food}`);
    }

    public sendToGraze(location: Location, date: Date): void {
        console.log(`send the cow to ${location} at ${date.getHours()} : ${date.getMinutes()}`);
    }

    public milk(liters?: number): void {
        if (liters === undefined) {
            console.log(`this cow has produced --->${this.litersOfMilkPerWeek}liters per week`);
            return;
        }
        this.litersOfMilkPerWeek += liters;
        console.log(`milking the cow... we got --->${liters} liters today`);
        console.log(`this cow has produced --->${this.litersOfMilkPerWeek}liters per week`);
    }

    public getLitersOfMilkPerWeek(): number {
        return this.litersOfMilkPerWeek;
    }

    public override toString(): string {
        return `Cow{${super.toString()}isProducingMilk=${this.isProducingMilk}, litersOfMilkPerWeek=${this.litersOfMilkPerWeek}}`;
    }
}
