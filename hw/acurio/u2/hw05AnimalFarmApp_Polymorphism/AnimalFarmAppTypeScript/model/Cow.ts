
import { FarmAnimal } from "./FarmAnimal";
import { Food } from "./Food";
import { Location } from "./Location";
import { GrazeAnimalI } from "./GrazeAnimalI";

export class Cow extends FarmAnimal implements GrazeAnimalI {
    private isProducingMilk: boolean;
    private litersOfMilkPerWeek: number;

    constructor(isProducingMilk: boolean, liters: number, id: number,
                breed: string, bornOnDate: Date, isMeat: boolean) {
        super(id, breed, bornOnDate, isMeat);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkPerWeek = liters;
    }

    milk(liters?: number): void {
        if (liters !== undefined) {
            this.litersOfMilkPerWeek += liters;
            console.log(`milking the cow... we got --->${liters} liters today`);
        }

        console.log(`this cow has produced --->${this.litersOfMilkPerWeek}liters per week`);
    }

    feed(food: Food, amount: number): void {
        console.log(`feeding the cow with ${amount} of ${food}`);
    }

    sendToGraze(location: Location, date: Date): void {
        console.log(`send the cow to ${location} at ${date.getHours()} : ${date.getMinutes()}`);
    }

    toString(): string {
        return `Cow{${super.toString()}isProducingMilk=${this.isProducingMilk}, litersOfMilkPerWeek=${this.litersOfMilkPerWeek}}`;
    }

    isIsProducingMilk(): boolean {
        return this.isProducingMilk;
    }

    setIsProducingMilk(value: boolean): void {
        this.isProducingMilk = value;
    }

    getLitersOfMilkPerWeek(): number {
        return this.litersOfMilkPerWeek;
    }

    setLitersOfMilkPerWeek(value: number): void {
        this.litersOfMilkPerWeek = value;
    }
}
