import { FarmAnimal } from "./FarmAnimal";

export class Sheep extends FarmAnimal {
    lastSheering: Date;
    isProducingWool: boolean;
    isMeat: boolean;

    constructor(lastSheering: Date, isProducingWool: boolean, isMeat: boolean, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.lastSheering = lastSheering;
        this.isProducingWool = isProducingWool;
        this.isMeat = isMeat;
    }

    shear(): void {
        console.log("shearing the sheep");
    }

    toString(): string {
        const dateStr = this.lastSheering instanceof Date ? this.lastSheering.toLocaleDateString() : String(this.lastSheering);
        return `Sheep{${super.toString()}lastSheering=${dateStr}, isProducingWool=${this.isProducingWool}, isMeat=${this.isMeat}}`;
    }
}