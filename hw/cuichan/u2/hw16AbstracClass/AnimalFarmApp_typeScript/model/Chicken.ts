import { FarmAnimal } from './FarmAnimal';

export class Chicken extends FarmAnimal {
    private isMolting: boolean;
    private numberOfLaidEggsInMonth: number;
    private isMeat: boolean;

    constructor(isMolting: boolean, numberOfLaidEggsInMonth: number, isMeat: boolean, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.isMolting = isMolting;
        this.numberOfLaidEggsInMonth = numberOfLaidEggsInMonth;
        this.isMeat = isMeat;
    }

    public feed(): void {
        console.log(`Chicken (ID: ${this.id}) is eating grains.`);
    }

    public layAnEgg(): void {
        console.log("The chicken is laying an Egg");
        this.numberOfLaidEggsInMonth++;
    }

    public toString(): string {
        return `Chicken{${super.toString()},isMolting=${this.isMolting}, numberOfLaidEggsInMonth=${this.numberOfLaidEggsInMonth}, isMeat=${this.isMeat}}`;
    }
}