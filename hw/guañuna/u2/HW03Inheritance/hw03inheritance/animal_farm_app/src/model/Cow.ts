import { FarmAnimal } from './FarmAnimal';

export class Cow extends FarmAnimal {
    private isProducingMilk: boolean;

    constructor(isProducingMilk: boolean, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.isProducingMilk = isProducingMilk;
    }

    public getIsProducingMilk(): boolean { return this.isProducingMilk; }
    public setIsProducingMilk(v: boolean): void { this.isProducingMilk = v; }

    public toString(): string {
        return `Cow{isProducingMilk=${this.isProducingMilk}, ${super.toString()}}`;
    }
}