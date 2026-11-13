import { FarmAnimal } from "./FarmAnimal";

/**
 * @author Dennis, Bytes & Fabric Devs, @ESPE
 */
export class Sheep extends FarmAnimal {
    private isSheared: boolean;
    private lastSheering: Date;

    public toString(): string {
        return "Sheep{" + super.toString() + "isSheared=" + this.isSheared + ", lastSheering=" + this.lastSheering + "}";
    }

    constructor(isSheared: boolean, lastSheering: Date, id: number, breed: string, bornOnDate: Date, isMeat: boolean) {
        super(id, breed, bornOnDate, isMeat);
        this.isSheared = isSheared;
        this.lastSheering = lastSheering;
    }

    /** @return the isSheared */
    public isIsSheared(): boolean {
        return this.isSheared;
    }

    /** @param isSheared the isSheared to set */
    public setIsSheared(isSheared: boolean): void {
        this.isSheared = isSheared;
    }

    /** @return the lastSheering */
    public getLastSheering(): Date {
        return this.lastSheering;
    }

    /** @param lastSheering the lastSheering to set */
    public setLastSheering(lastSheering: Date): void {
        this.lastSheering = lastSheering;
    }
}
