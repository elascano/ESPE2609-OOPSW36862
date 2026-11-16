
import { FarmAnimal } from "./FarmAnimal";
import { Food } from "./Food";
import { SellAnimal } from "./SellAnimal";

export class Chicken extends FarmAnimal implements SellAnimal {
    private isMolting: boolean;
    private numberOfLaidEggsPerWeek: number;

    constructor(isMolting: boolean, eggs: number, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate, isMolting);
        this.isMolting = isMolting;
        this.numberOfLaidEggsPerWeek = eggs;
    }

    feed(food: Food, amount: number): void {
        console.log(`feeding a chicken with ${amount} pound of ${food}`);
    }

    sellAnimal(price: number): void {
        console.log(`sold the chicken in ${price} dollars.`);
    }

    layAnEgg(): void {
        this.numberOfLaidEggsPerWeek++;
        console.log("chicken is laying an egg");
    }

    toString(): string {
        return `Chicken{${super.toString()}isMolting=${this.isMolting}, numberOfLaidEggsPerWeek=${this.numberOfLaidEggsPerWeek}}`;
    }

    isIsMolting(): boolean { return this.isMolting; }
    setIsMolting(value: boolean): void { this.isMolting = value; }

    getNumberOfLaidEggsPerWeek(): number {
        return this.numberOfLaidEggsPerWeek;
    }

    setNumberOfLaidEggsPerWeek(value: number): void {
        this.numberOfLaidEggsPerWeek = value;
    }
}
