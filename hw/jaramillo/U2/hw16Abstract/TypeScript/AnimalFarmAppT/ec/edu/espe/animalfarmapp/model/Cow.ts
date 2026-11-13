import { FarmAnimal } from "./FarmAnimal";

export class Cow extends FarmAnimal {
    private isProducingMilk: boolean;
    private litersOfMilkPerWeek: number;

    public milk(): void {
        console.log("milk in the cow ");
    }

    constructor(
        isProducingMilk: boolean,
        litersOfMilkPerWeek: number,
        id: number,
        breed: string,
        bornOnDate: Date
    ) {
        super(id, breed, bornOnDate);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }

    public override toString(): string {
        return `cow{${super.toString()}isProducingMilk=${this.isProducingMilk}, litersOfMilkPerWeek=${this.litersOfMilkPerWeek}}`;
    }

    public isIsProducingMilk(): boolean {
        return this.isProducingMilk;
    }

    public setIsProducingMilk(isProducingMilk: boolean): void {
        this.isProducingMilk = isProducingMilk;
    }

    public getLitersOfMilkPerWeek(): number {
        return this.litersOfMilkPerWeek;
    }

    public setLitersOfMilkPerWeek(litersOfMilkPerWeek: number): void {
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }
}