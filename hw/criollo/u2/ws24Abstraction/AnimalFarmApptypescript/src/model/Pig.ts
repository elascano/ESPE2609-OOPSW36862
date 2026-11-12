import { FarmAnimal } from "./FarmAnimal";

/**
 * @author Dennis, Bytes & Fabric Devs, @ESPE
 */
export class Pig extends FarmAnimal {
    private isWallowing: boolean;
    private weightGainPerWeek: number;

    public toString(): string {
        return "Pig{" + super.toString() + "isWallowing=" + this.isWallowing + ", weightGainPerWeek=" + this.weightGainPerWeek + "}";
    }

    constructor(isWallowing: boolean, weightGainPerWeek: number, id: number, breed: string, bornOnDate: Date, isMeat: boolean) {
        super(id, breed, bornOnDate, isMeat);
        this.isWallowing = isWallowing;
        this.weightGainPerWeek = weightGainPerWeek;
    }

    /** @return the isWallowing */
    public isIsWallowing(): boolean {
        return this.isWallowing;
    }

    /** @param isWallowing the isWallowing to set */
    public setIsWallowing(isWallowing: boolean): void {
        this.isWallowing = isWallowing;
    }

    /** @return the weightGainPerWeek */
    public getWeightGainPerWeek(): number {
        return this.weightGainPerWeek;
    }

    /** @param weightGainPerWeek the weightGainPerWeek to set */
    public setWeightGainPerWeek(weightGainPerWeek: number): void {
        this.weightGainPerWeek = weightGainPerWeek;
    }
}
