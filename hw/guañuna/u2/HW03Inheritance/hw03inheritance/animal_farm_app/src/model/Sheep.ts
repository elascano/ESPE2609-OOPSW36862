import { FarmAnimal } from './FarmAnimal';

export class Sheep extends FarmAnimal {
    private lastSheering: Date;

    constructor(lastSheering: Date, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.lastSheering = lastSheering;
    }

    public getLastSheering(): Date { return this.lastSheering; }
    public setLastSheering(v: Date): void { this.lastSheering = v; }

    public toString(): string {
        return `Sheep{lastSheering=${this.lastSheering.toLocaleDateString()}, ${super.toString()}}`;
    }
}