import { FarmAnimal } from "./FarmAnimal";

/**
 * @author Matias Paucar, ByteWorks, @ESPE
 */
export class Pig extends FarmAnimal {
    private _isWallowing: boolean;
    private _weightGainPerWeek: number;

    constructor(isWallowing: boolean, weightGainPerWeek: number,
                id: number, breed: string, bornOnDate: Date, isMeat: boolean) {
        super(id, breed, bornOnDate, isMeat);
        this._isWallowing = isWallowing;
        this._weightGainPerWeek = weightGainPerWeek;
    }

    public showPigMeatCuts(): void {
        const meatCuts: string[] = ["head", "cheek", "picnic", "clear plate", "boston shoulder",
            "back fat", "loin", "ribs", "bacon", "leg", "ham", "hock"];
        console.log("Avalaible cuts for the pig" + "[" + meatCuts.join(", ") + "]");
    }

    public override toString(): string {
        return "Pig{" + super.toString() + "isWallowing=" + this._isWallowing +
            ", weightGainPerWeek=" + this._weightGainPerWeek + "}";
    }

    public get isWallowing(): boolean { return this._isWallowing; }
    public set isWallowing(value: boolean) { this._isWallowing = value; }

    public get weightGainPerWeek(): number { return this._weightGainPerWeek; }
    public set weightGainPerWeek(value: number) { this._weightGainPerWeek = value; }
}
