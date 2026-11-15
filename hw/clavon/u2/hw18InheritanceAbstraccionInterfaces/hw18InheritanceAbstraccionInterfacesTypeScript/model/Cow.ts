import { FarmAnimal } from "./FarmAnimal";
import { GrazeAnimal } from "./GrazeAnimal";
import { Food } from "./Food";
import { Location } from "./Location";

/**
 * @author David Clavón, Infinite loops, @ESPE
 */
export class Cow extends FarmAnimal implements GrazeAnimal {
    private isProducingMilk: boolean;
    private litersOfMilkPerWeek: number;
    private vaccines: unknown[] = [];

    constructor(
        isProducingMilk: boolean,
        litersOfMilkPerWeek: number,
        id: number,
        breed: string,
        bornOnDate: Date
    ) {
        super(id, breed, bornOnDate);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }

    public graze(location: Location): void {
        console.log(`Sending the cow to ${location.toString()}`);
    }

    public override feed(food: Food, amountOfFood: number): void {
        console.log(`Feeding a cow in the Stable with ---> ${amountOfFood}of${food.toString()}`);
    }

    public isIsProducingMilk(): boolean {
        return this.isProducingMilk;
    }

    public setIsProducingMilk(isProducingMilk: boolean): void {
        this.isProducingMilk = isProducingMilk;
    }

    public getLitersOfMilkPerWeek(): number {
        return this.litersOfMilkPerWeek;
    }

    public setLitersOfMilkPerWeek(litersOfMilkPerWeek: number): void {
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }

    public milk(): void;
    public milk(liters: number): void;
    public milk(amountOfLiters: number, hour: Date): void;
    public milk(amount?: number, hour?: Date): void {
        if (amount === undefined && hour === undefined) {
            console.log("Milking the Cow I need");
        } else if (amount !== undefined && hour !== undefined) {
            this.litersOfMilkPerWeek += amount;
            console.log(`milking the cow for ${amount}liters, at ${hour.getHours()}:${hour.getMinutes()}`);
        } else if (amount !== undefined) {
            this.litersOfMilkPerWeek += amount;
            if (Number.isInteger(amount)) {
                console.log(`Milking the Cow obtend  ${amount}`);
            } else {
                console.log(`miking the cow... we got ${amount} today`);
                console.log(`this cow has produce:  ${amount}`);
            }
        }
    }

    public override toString(): string {
        return `Cow{${super.toString()}isProducingMilk=${this.isProducingMilk}, litersOfMilkPerWeek=${this.litersOfMilkPerWeek}}`;
    }
}