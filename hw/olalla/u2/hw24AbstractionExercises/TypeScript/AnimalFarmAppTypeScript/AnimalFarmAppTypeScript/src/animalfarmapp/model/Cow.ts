import { FarmAnimal } from "./FarmAnimal";

/**
 * @author Matias Paucar, ByteWorks, @ESPE
 */
export class Cow extends FarmAnimal {
    private _isProducingMilk: boolean;
    private _litersOfMilkPerWeek: number;

    constructor(isProducingMilk: boolean, litersOfMilkPerWeek: number,
                id: number, breed: string, bornOnDate: Date, isMeat: boolean) {
        super(id, breed, bornOnDate, isMeat);
        this._isProducingMilk = isProducingMilk;
        this._litersOfMilkPerWeek = litersOfMilkPerWeek;
    }

    public milk(): void {
        console.log("cow is producing milk");
    }

    public override toString(): string {
        return "Cow{" + super.toString() + "isProducingMilk=" + this._isProducingMilk +
            ", litersOfMilkPerWeek=" + this._litersOfMilkPerWeek + "}";
    }

    public get isProducingMilk(): boolean { return this._isProducingMilk; }
    public set isProducingMilk(value: boolean) { this._isProducingMilk = value; }

    public get litersOfMilkPerWeek(): number { return this._litersOfMilkPerWeek; }
    public set litersOfMilkPerWeek(value: number) { this._litersOfMilkPerWeek = value; }
}
