import { FarmAnimal } from "./FarmAnimal";

/**
 * @author Matias Paucar, ByteWorks, @ESPE
 */
export class Chicken extends FarmAnimal {
    private _isMolting: boolean;
    private _numberOfLaidEggsPerWeek: number;

    constructor(isMolting: boolean, numberOfLaidEggsPerWeek: number,
                id: number, breed: string, bornOnDate: Date) {
        // Igual que en Java: se pasa isMolting como isMeat al padre
        super(id, breed, bornOnDate, isMolting);
        this._isMolting = isMolting;
        this._numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek;
    }

    public layAnEgg(): void {
        console.log("chicken is laying an egg");
    }

    public override toString(): string {
        return "Chicken{" + super.toString() + "isMolting=" + this._isMolting +
            ", numberOfLaidEggsPerWeek=" + this._numberOfLaidEggsPerWeek + "}";
    }
    public get isMolting(): boolean { return this._isMolting; }
    public set isMolting(value: boolean) { this._isMolting = value; }

    public get numberOfLaidEggsPerWeek(): number { return this._numberOfLaidEggsPerWeek; }
    public set numberOfLaidEggsPerWeek(value: number) { this._numberOfLaidEggsPerWeek = value; }
}
