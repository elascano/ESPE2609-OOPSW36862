import { FarmAnimal } from "./FarmAnimal.ts";

//Demian Armas, Logical Lords, @ESPE

export class Cow extends FarmAnimal {
    isProducingMilk: boolean;
    litersOfMilkForWeek: number;

    constructor(
        isProducingMilk: boolean,
        litersOfMilkForWeek: number,
        id: number,
        breed: string,
        bornOnDate: Date
    ) {
        super(id, breed, bornOnDate);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkForWeek = litersOfMilkForWeek;
    }

    milk(): string {
        return "The cow is being milked.";
    }

    override toString(): string {
        return `Cow{${super.toString()}, isProducingMilk=${this.isProducingMilk}, ` +
            `litersOfMilkForWeek=${this.litersOfMilkForWeek}}`;
    }
}
