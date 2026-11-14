import { FarmAnimal } from "./FarmAnimal";

export class Cow extends FarmAnimal {
    private isProducingMilk: boolean;
    private litersOfMilkPerWeek: number;

    constructor(isProducingMilk: boolean, litersOfMilkPerWeek: number,
                id: number, breed: string, bornOnDate: Date, isMeat: boolean) {
        super(id, breed, bornOnDate, isMeat);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }

    public milk(): void {
        console.log("cow is producing milk");
    }

    public toString(): string {
        return `Cow{${super.toString()}isProducingMilk=${this.isProducingMilk}, litersOfMilkPerWeek=${this.litersOfMilkPerWeek}}`;
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
