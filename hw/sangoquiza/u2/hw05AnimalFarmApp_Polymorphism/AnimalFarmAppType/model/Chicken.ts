import { FarmAnimal } from "./FarmAnimal";
import { Food } from "./Food";

/**
 * Clase Chicken traducida de ec.edu.espe.model.Chicken
 * @author Daniel Sangoquiza, SaborCode
 */
export class Chicken extends FarmAnimal {
    private isMolting: boolean;
    private numberOfLaidEggsInAMonth: number;
    private isMeat: boolean;

    constructor(
        isMolting: boolean,
        numberOfLaidEggsInAMonth: number,
        isMeat: boolean,
        id: number,
        breed: string,
        bornOnDate: Date
    ) {
        super(id, breed, bornOnDate);
        this.isMolting = isMolting;
        this.numberOfLaidEggsInAMonth = numberOfLaidEggsInAMonth;
        this.isMeat = isMeat;
    }

    public override feed(food: Food, amountOfFood: number): void {
        console.log(`feeding a chicken with --->${amountOfFood}of ${food.toString()}`);
    }

    public layAnEgg(): void {
        console.log("Chicken is laying an egg () ");
    }

    public override toString(): string {
        return `Chicken{${super.toString()}isMolting=${this.isMolting}, numberOfLaidEggsInAMonth=${this.numberOfLaidEggsInAMonth}, isMeat=${this.isMeat}}`;
    }

    // --- Getters y Setters ---
    public isIsMolting(): boolean {
        return this.isMolting;
    }

    public setIsMolting(isMolting: boolean): void {
        this.isMolting = isMolting;
    }

    public getNumberOfLaidEggsInAMonth(): number {
        return this.numberOfLaidEggsInAMonth;
    }

    public setNumberOfLaidEggsInAMonth(numberOfLaidEggsInAMonth: number): void {
        this.numberOfLaidEggsInAMonth = numberOfLaidEggsInAMonth;
    }

    public isIsMeat(): boolean {
        return this.isMeat;
    }

    public setIsMeat(isMeat: boolean): void {
        this.isMeat = isMeat;
    }
}