import { FarmAnimal } from "./FarmAnimal";

/**
 * @author David Clavón, Infinite Loops, @ESPE
 */
export class Sheep extends FarmAnimal {
    private lastSheering: Date;
    private isProducingWool: boolean;
    private kgOfWoolForMonth: number;

    constructor(
        lastSheering: Date,
        isProducingWool: boolean,
        kgOfWoolForMonth: number,
        id: number,
        breed: string,
        bornOnDate: Date
    ) {
        super(id, breed, bornOnDate);
        this.lastSheering = lastSheering;
        this.isProducingWool = isProducingWool;
        this.kgOfWoolForMonth = kgOfWoolForMonth;
    }

    public shear(): void {
        console.log("shearing the sheep");
    }

    public override toString(): string {
        const formattedSheering = `${this.lastSheering.getDate()}/${this.lastSheering.getMonth() + 1}/${this.lastSheering.getFullYear()}`;
        return `Sheep{${super.toString()}lastSheering=${formattedSheering}, isProducingWool=${this.isProducingWool}, kgOfWoolForMonth=${this.kgOfWoolForMonth}}`;
    }

    public getLastSheering(): Date {
        return this.lastSheering;
    }

    public setLastSheering(lastSheering: Date): void {
        this.lastSheering = lastSheering;
    }

    public isIsProducingWool(): boolean {
        return this.isProducingWool;
    }

    public setIsProducingWool(isProducingWool: boolean): void {
        this.isProducingWool = isProducingWool;
    }

    public getKgOfWoolForMonth(): number {
        return this.kgOfWoolForMonth;
    }

    public setKgOfWoolForMonth(kgOfWoolForMonth: number): void {
        this.kgOfWoolForMonth = kgOfWoolForMonth;
    }
}