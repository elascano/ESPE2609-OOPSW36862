import { FarmAnimal } from "./FarmAnimal";
import { Food } from "./Food";
import { SellAnimal } from "./SellAnimal";

export class Chicken extends FarmAnimal implements SellAnimal {
    constructor(
        private isMolting: boolean,
        private numberOfLaidEggsPerWeek: number,
        id: number,
        breed: string,
        bornOnDate: Date
    ) {
        super(id, breed, bornOnDate, isMolting);
    }

    public feed(food: Food, amount: number): void {
        console.log(`feeding a chicken with ${amount} pound of ${food}`);
    }

    public sellAnimal(price: number): void {
        console.log(`sold the chicken in ${price} dollars.`);
    }

    public layAnEgg(): void {
        this.numberOfLaidEggsPerWeek++;
        console.log("chicken is laying an egg");
    }

    public override toString(): string {
        return `Chicken{${super.toString()}isMolting=${this.isMolting}, numberOfLaidEggsPerWeek=${this.numberOfLaidEggsPerWeek}}`;
    }
}
