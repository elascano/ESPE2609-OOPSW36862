import { FarmAnimal } from './FarmAnimal';

export class Sheep extends FarmAnimal {
    private lastSheering: Date;
    private woolWeightInKg: number;

    constructor(lastSheering: Date, woolWeightInKg: number, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.lastSheering = lastSheering;
        this.woolWeightInKg = woolWeightInKg;
    }

    public shear(): void {
        console.log("shearing the sheep()");
    }

    public getLastSheering(): Date { return this.lastSheering; }
    public setLastSheering(v: Date): void { this.lastSheering = v; }

    public getWoolWeightInKg(): number { return this.woolWeightInKg; }
    public setWoolWeightInKg(v: number): void { this.woolWeightInKg = v; }

    public toString(): string {
        return `Sheep{${super.toString()}lastSheering=${this.lastSheering.getDate()}/${this.lastSheering.getMonth() + 1}/${this.lastSheering.getFullYear()}, woolWeightInKg=${this.woolWeightInKg}}`;
    }
}