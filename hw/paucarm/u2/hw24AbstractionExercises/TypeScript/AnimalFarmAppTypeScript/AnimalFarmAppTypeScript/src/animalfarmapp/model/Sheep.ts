import { FarmAnimal } from "./FarmAnimal";

/**
 * @author Matias Paucar, ByteWorks, @ESPE
 */
export class Sheep extends FarmAnimal {
    private _isSheared: boolean;
    private _lastSheering: Date;

    constructor(isSheared: boolean, lastSheering: Date,
                id: number, breed: string, bornOnDate: Date, isMeat: boolean) {
        super(id, breed, bornOnDate, isMeat);
        this._isSheared = isSheared;
        this._lastSheering = lastSheering;
    }

    public shearSheep(): void {
        console.log("Sheep is sheared");
    }

    public override toString(): string {
        return "Sheep{" + super.toString() + "isSheared=" + this._isSheared +
            ", lastSheering=" + FarmAnimal.formatDate(this._lastSheering) + "}";
    }

    // ---------- getters y setters ----------
    public get isSheared(): boolean { return this._isSheared; }
    public set isSheared(value: boolean) { this._isSheared = value; }

    public get lastSheering(): Date { return this._lastSheering; }
    public set lastSheering(value: Date) { this._lastSheering = value; }
}
