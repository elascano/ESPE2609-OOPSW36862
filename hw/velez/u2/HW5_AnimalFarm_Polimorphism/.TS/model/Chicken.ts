import { FarmAnimal } from "./FarmAnimal";
import { SellAnimal } from "./SellAnimal";
import { Food } from "./Food";

export class Chicken extends FarmAnimal implements SellAnimal {
    private isMolting: boolean;
    private numberOfLaidEggsPerWeek: number;

    constructor(isMolting: boolean, numberOfLaidEggsPerWeek: number, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate, false);
        this.isMolting = isMolting;
        this.numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek;
    }

    public feed(food: Food, amount: number): void {
        console.log(`feeding a chicken with ${amount} pound of ${food.toString()}`);
    }

    public sellAnimal(price: number): void {
        console.log(`sold the chicken in ${price} dollars.`);
    }

    public layAnEgg(): void {
        this.numberOfLaidEggsPerWeek++;
        console.log("chicken is laying an egg");
    }

    public toString(): string {
        return `Chicken{${super.toString()}, isMolting=${this.isMolting}, eggsPerWeek=${this.numberOfLaidEggsPerWeek}}`;
    }
}