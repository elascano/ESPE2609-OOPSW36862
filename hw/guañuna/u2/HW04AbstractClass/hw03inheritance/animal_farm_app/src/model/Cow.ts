import { FarmAnimal } from './FarmAnimal';

export class Cow extends FarmAnimal {
    private isProducingMilk: boolean;
    private litersOfMilkForWeek: number;

    constructor(isProducingMilk: boolean, litersOfMilkForWeek: number, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkForWeek = litersOfMilkForWeek;
    }

    public milk(): void {
        console.log("milking the cow()");
    }

    public getIsProducingMilk(): boolean { return this.isProducingMilk; }
    public setIsProducingMilk(v: boolean): void { this.isProducingMilk = v; }

    public getLitersOfMilkForWeek(): number { return this.litersOfMilkForWeek; }
    public setLitersOfMilkForWeek(v: number): void { this.litersOfMilkForWeek = v; }

    public toString(): string {
        return `Cow{${super.toString()}isProducingMilk=${this.isProducingMilk}, litersOfMilkForWeek=${this.litersOfMilkForWeek}}`;
    }
}