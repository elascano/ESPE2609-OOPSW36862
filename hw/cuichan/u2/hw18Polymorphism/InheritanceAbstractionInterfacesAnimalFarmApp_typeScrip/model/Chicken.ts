import { FarmAnimal } from './FarmAnimal';
import { Food } from './Food';

export class Chicken extends FarmAnimal {
    private isMolting: boolean;
    private numberOfLaidEggsInAMonth: number;
    private isMeat: boolean;

    constructor(isMolting: boolean, numberOfLaidEggsInAMonth: number, isMeat: boolean, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.isMolting = isMolting;
        this.numberOfLaidEggsInAMonth = numberOfLaidEggsInAMonth;
        this.isMeat = isMeat;
    }

    public feed(food: Food, amountOfFood: number): void {
        console.log(`Feeding a Chicken with ${amountOfFood} pound of ${food}`);
    }

    public layInEgg(): void {
        console.log("Chicken is laying egg");
    }

    public toString(): string {
        return `Chicken{${super.toString()}isMolting=${this.isMolting}, numberOfLaidEggsInAMonth=${this.numberOfLaidEggsInAMonth}, isMeat=${this.isMeat}}`;
    }
}