import { FarmAnimal } from "./FarmAnimal";

/**
 * @author Matias Paucar, ByteWorks, @ESPE
 */
export class Chicken extends FarmAnimal {
    private _isMolting: boolean;
    private _numberOfLaidEggsPerWeek: number;
    private _isMeat: boolean;

    constructor(isMolting: boolean, numberOfLaidEggsPerWeek: number, isMeat: boolean,
                id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this._isMolting = isMolting;
        this._numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek;
        this._isMeat = isMeat;
    }

    public override toString(): string {
        return "Chicken{" + super.toString() + "isMolting=" + this._isMolting +
            ", numberOfLaidEggsPerWeek=" + this._numberOfLaidEggsPerWeek +
            ", isMeat=" + this._isMeat + "}";
    }

    public get isMolting(): boolean {
        return this._isMolting;
    }

    public set isMolting(isMolting: boolean) {
        this._isMolting = isMolting;
    }

    public get numberOfLaidEggsPerWeek(): number {
        return this._numberOfLaidEggsPerWeek;
    }

    public set numberOfLaidEggsPerWeek(value: number) {
        this._numberOfLaidEggsPerWeek = value;
    }

    public get isMeat(): boolean {
        return this._isMeat;
    }

    public set isMeat(isMeat: boolean) {
        this._isMeat = isMeat;
    }
}
