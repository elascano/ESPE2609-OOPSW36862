import { FarmAnimal } from './FarmAnimal';

export class Cow extends FarmAnimal {
    private isProducingMilk: boolean;
    private litersOfMilkPerWeek: number;

    constructor(isProducingMilk: boolean, litersOfMilkPerWeek: number, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }

    public feed(): void {
        console.log(`Cow (ID: ${this.id}) is eating grass.`);
    }

    public milk(): void {
        console.log("milk in the cow ");
    }

    public toString(): string {
        return `Cow{${super.toString()}, isProducingMilk=${this.isProducingMilk}, litersOfMilkPerWeek=${this.litersOfMilkPerWeek}}`;
    }
}