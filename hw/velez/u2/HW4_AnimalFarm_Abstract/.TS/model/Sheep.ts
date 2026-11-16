import { FarmAnimal } from "./FarmAnimal";

export class Sheep extends FarmAnimal {
    private isSheared: boolean;
    private lastSheering: Date;

    constructor(isSheared: boolean, lastSheering: Date, id: number, breed: string, bornOnDate: Date, isMeat: boolean) {
        super(id, breed, bornOnDate, isMeat);
        this.isSheared = isSheared;
        this.lastSheering = lastSheering;
    }

    public shearSheep(): void {
        console.log("Sheep is sheared");
    }

    public toString(): string {
        return `Sheep{${super.toString()}, isSheared=${this.isSheared}, lastSheering=${this.lastSheering.toDateString()}}`;
    }
}