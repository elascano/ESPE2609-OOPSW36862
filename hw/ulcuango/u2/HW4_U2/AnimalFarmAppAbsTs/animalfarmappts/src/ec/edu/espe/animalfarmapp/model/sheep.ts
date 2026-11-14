import { FarmAnimal } from './farm_animal';

export class Sheep extends FarmAnimal {
    private lastSheering: Date;

    constructor(id: number, bred: string, bornOnDate: Date, lastSheering: Date) {
        super(id, bred, bornOnDate);
        this.lastSheering = lastSheering;
    }

    public toString(): string {
        const superStr = super.toString().replace("}", "");
        return `Sheep{${superStr}, lastSheering=${this.lastSheering}}`;
    }

    public getLastSheering(): Date {
        return this.lastSheering;
    }

    public setLastSheering(lastSheering: Date): void {
        this.lastSheering = lastSheering;
    }
}