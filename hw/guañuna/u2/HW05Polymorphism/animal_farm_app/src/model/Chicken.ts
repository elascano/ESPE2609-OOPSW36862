import { FarmAnimal } from './FarmAnimal';
import { GrazeAnimalI } from './GrazeAnimalI';
import { Food } from './Food';
import { Location } from './Location';

export class Chicken extends FarmAnimal implements GrazeAnimalI {
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
        console.log(`Feeding a chicken with ---> ${amount} pounds of ${food.toString()}`);
    }

    public senToGraze(location: Location, date: Date): void {
        console.log(`Sending the chicken to ---> ${location.toString()} at ${date.toString()}`);
    }

    // Sobrecarga en TS: firmas arriba, implementación abajo
    public layAnEgg(): void;
    public layAnEgg(amount: number): void;
    public layAnEgg(amount: number = 1): void {
        this.numberOfLaidEggsInAMonth += amount;
        console.log(`Chicken is laying ${amount} eggs. Total this month: ${this.numberOfLaidEggsInAMonth}`);
    }

    public toString(): string {
        return `Chicken{${super.toString()}isMolting=${this.isMolting}, numberOfLaidEggsInAMonth=${this.numberOfLaidEggsInAMonth}, isMeat=${this.isMeat}}`;
    }
}