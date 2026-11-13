import { FarmAnimal } from './farm_animal';

export class Cow extends FarmAnimal {
    private isProducingMilk: boolean;

    constructor(id: number, bred: string, bornOnDate: Date, isProducingMilk: boolean) {
        super(id, bred, bornOnDate);
        this.isProducingMilk = isProducingMilk;
    }

    public milk(): void {
        console.log("Cow is milking");
    }

    public toString(): string {
        const superStr = super.toString().replace("}", "");
        return `Cow{${superStr}, isProducingMilk=${this.isProducingMilk}}`;
    }

    public getIsProducingMilk(): boolean {
        return this.isProducingMilk;
    }

    public setIsProducingMilk(isProducingMilk: boolean): void {
        this.isProducingMilk = isProducingMilk;
    }
}