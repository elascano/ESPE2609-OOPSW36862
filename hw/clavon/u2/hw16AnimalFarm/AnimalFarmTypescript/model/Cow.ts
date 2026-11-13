import { FarmAnimal } from "./FarmAnimal";

/**
 * @author David Clavón, Infinite Loops, @ESPE
 */
export class Cow extends FarmAnimal {
    private isProducingMilk: boolean;
    private litersOfMilkForWeek: number;

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

    public milk(): void {
        console.log("milking the cow");
    }

    public override toString(): string {
        return `Cow{${super.toString()}isProducingMilk=${this.isProducingMilk}, litersOfMilkForWeek=${this.litersOfMilkForWeek}}`;
    }

    public isIsProducingMilk(): boolean {
        return this.isProducingMilk;
    }

    public setIsProducingMilk(isProducingMilk: boolean): void {
        this.isProducingMilk = isProducingMilk;
    }

    public getLitersOfMilkForWeek(): number {
        return this.litersOfMilkForWeek;
    }

    public setLitersOfMilkForWeek(litersOfMilkForWeek: number): void {
        this.litersOfMilkForWeek = litersOfMilkForWeek;
    }
}