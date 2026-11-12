import { FarmAnimal } from "./FarmAnimal";

export class Pig extends FarmAnimal {
    private isWallowing: boolean;
    private weightGainPerWeek: number;

    constructor(isWallowing: boolean, weightGainPerWeek: number,
                id: number, breed: string, bornOnDate: Date,
                isMeat: boolean) {
        super(id, breed, bornOnDate, isMeat);
        this.isWallowing = isWallowing;
        this.weightGainPerWeek = weightGainPerWeek;
    }

    public toString(): string {
        return `Pig{${super.toString()}, isWallowing=${this.isWallowing}, weightGainPerWeek=${this.weightGainPerWeek}}`;
    }

    public getIsWallowing(): boolean {
        return this.isWallowing;
    }

    public setIsWallowing(isWallowing: boolean): void {
        this.isWallowing = isWallowing;
    }

    public getWeightGainPerWeek(): number {
        return this.weightGainPerWeek;
    }

    public setWeightGainPerWeek(weight: number): void {
        this.weightGainPerWeek = weight;
    }
}
