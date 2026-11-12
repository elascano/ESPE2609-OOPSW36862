import { FarmAnimal } from "./FarmAnimal";

/**
 * @author Matias Paucar, ByteWorks, @ESPE
 */
export class Cow extends FarmAnimal {
    private _isProducingMilk: boolean;
    private _litersOfMilkPerWeek: number;
    private _isMeat: boolean;

    constructor(isMeat: boolean, id: number, breed: string, bornOnDate: Date);
    constructor(isProducingMilk: boolean, litersOfMilkPerWeek: number,
                id: number, breed: string, bornOnDate: Date);

    constructor(flag: boolean, a: number, b: number | string,
                c: string | Date, d?: Date) {
        const isMilkVersion = d !== undefined;
        super(
            isMilkVersion ? (b as number) : a,     
            isMilkVersion ? (c as string) : (b as string),
            isMilkVersion ? d : (c as Date)        
        );
        if (isMilkVersion) {
            this._isProducingMilk = flag;
            this._litersOfMilkPerWeek = a;
            this._isMeat = false;
        } else {
            this._isMeat = flag;
            this._isProducingMilk = false;
            this._litersOfMilkPerWeek = 0;
        }
    }

    public override toString(): string {
        return "Cow{" + super.toString() + "isProducingMilk=" + this._isProducingMilk +
            ", litersOfMilkPerWeek=" + this._litersOfMilkPerWeek + "}";
    }

    public milk(): void {
        console.log("Milking the cow ");
    }

    public get isProducingMilk(): boolean {
        return this._isProducingMilk;
    }

    public set isProducingMilk(value: boolean) {
        this._isProducingMilk = value;
    }

    public get litersOfMilkPerWeek(): number {
        return this._litersOfMilkPerWeek;
    }

    public set litersOfMilkPerWeek(value: number) {
        this._litersOfMilkPerWeek = value;
    }

    public get isMeat(): boolean {
        return this._isMeat;
    }
}
