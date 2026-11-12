import { FarmAnimal } from "./FarmAnimal";

export class Cow extends FarmAnimal {
    isProducingMilk: boolean;
    litersOfMilkPerWeek: number;

    constructor(isProducingMilk: boolean, litersOfMilkPerWeek: number, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }

    milk(): void {
        console.log("milk in the cow ");
    }

    toString(): string {
        return `Cow{${super.toString()},isProducingMilk=${this.isProducingMilk}, litersOfMilkPerWeek=${this.litersOfMilkPerWeek}}`;
    }
}