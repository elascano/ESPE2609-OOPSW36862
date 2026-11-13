import { FarmAnimal } from "./FarmAnimal";

export class Pig extends FarmAnimal {
    private isWallowing: boolean;
    private weightGainPerWeek: number;

    constructor(isWallowing: boolean, weightGainPerWeek: number,
                id: number, breed: string, bornOnDate: Date, isMeat: boolean) {
        super(id, breed, bornOnDate, isMeat);
        this.isWallowing = isWallowing;
        this.weightGainPerWeek = weightGainPerWeek;
    }

    public showPigMeatCuts(): void {
        let meatCuts: string[] = ["head", "cheek", "picnic", "clear plate",
            "boston shoulder", "back fat", "loin", "ribs", "bacon", "leg", "ham", "hock"];
        console.log("Avalaible cuts for the pig[" + meatCuts.join(", ") + "]");
    }

    public toString(): string {
        return `Pig{${super.toString()}isWallowing=${this.isWallowing}, weightGainPerWeek=${this.weightGainPerWeek}}`;
    }

    public isIsWallowing(): boolean {
        return this.isWallowing;
    }

    public setIsWallowing(isWallowing: boolean): void {
        this.isWallowing = isWallowing;
    }

    public getWeightGainPerWeek(): number {
        return this.weightGainPerWeek;
    }

    public setWeightGainPerWeek(weightGainPerWeek: number): void {
        this.weightGainPerWeek = weightGainPerWeek;
    }
}
