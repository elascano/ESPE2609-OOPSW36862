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

    public feed(food: Food, amount: number): void {
        console.log(`feeding a chicken with --->${amount} of ${food.toString()}`);
    }

    public layAnEgg(): void {
        console.log("Chicken is laying an egg () ");
    }

    public toString(): string {
        return `Chicken{${super.toString()}isMolting=${this.isMolting}, numberOfLaidEggsInAMonth=${this.numberOfLaidEggsInAMonth}, isMeat=${this.isMeat}}`;
    }
}