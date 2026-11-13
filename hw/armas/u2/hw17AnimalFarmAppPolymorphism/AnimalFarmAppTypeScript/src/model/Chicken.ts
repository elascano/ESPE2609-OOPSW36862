import { FarmAnimal } from "./FarmAnimal.ts";

//Demian Armas, Logical Lords, @ESPE

export class Chicken extends FarmAnimal {
    isMolting: boolean;
    numberOfLaidEggInMonth: number;
    isMeat: boolean;

    constructor(
        isMolting: boolean,
        numberOfLaidEggInMonth: number,
        isMeat: boolean,
        id: number,
        breed: string,
        bornOnDate: Date
    ) {
        super(id, breed, bornOnDate);
        this.isMolting = isMolting;
        this.numberOfLaidEggInMonth = numberOfLaidEggInMonth;
        this.isMeat = isMeat;
    }

    layAnEgg(): string {
        return "The chicken is laying an egg.";
    }

    override toString(): string {
        return `Chicken{${super.toString()}, isMolting=${this.isMolting}, ` +
            `numberOfLaidEggInMonth=${this.numberOfLaidEggInMonth}, isMeat=${this.isMeat}}`;
    }
}
