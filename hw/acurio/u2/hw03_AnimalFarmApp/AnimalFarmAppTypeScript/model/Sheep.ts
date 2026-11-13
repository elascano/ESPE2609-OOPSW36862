import { FarmAnimal } from "./FarmAnimal";

export class Sheep extends FarmAnimal {
    private isSheared: boolean;
    private lastShearing: Date;

    constructor(isSheared: boolean, lastShearing: Date,
                id: number, breed: string, bornOnDate: Date,
                isMeat: boolean) {
        super(id, breed, bornOnDate, isMeat);
        this.isSheared = isSheared;
        this.lastShearing = lastShearing;
    }

    public toString(): string {
        return `Sheep{${super.toString()}, isSheared=${this.isSheared}, lastShearing=${this.lastShearing.toLocaleDateString("en-GB")}}`;
    }

    public getIsSheared(): boolean {
        return this.isSheared;
    }

    public setIsSheared(isSheared: boolean): void {
        this.isSheared = isSheared;
    }

    public getLastShearing(): Date {
        return this.lastShearing;
    }

    public setLastShearing(lastShearing: Date): void {
        this.lastShearing = lastShearing;
    }
}
