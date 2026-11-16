import { FarmAnimal } from "./FarmAnimal";
import { Food } from "./Food";
import { GrazeAnimalI } from "./GrazeAnimalI";
import { Location } from "./Location";

/**
 * @author Andrés Pachacama, ByteWorks, @ESPE
 */
export class Cow extends FarmAnimal implements GrazeAnimalI {
    private _isProducingMilk: boolean;
    private _litersOfMilkPerWeek: number;

    public milk(): void;
    public milk(liters: number): void;
    public milk(liters?: number): void {
        if (liters === undefined) {
            console.log("this cow has produced --->" + this._litersOfMilkPerWeek + "liters per week");
            return;
        }
        this._litersOfMilkPerWeek += liters;
        console.log("milking the cow... we got --->" + liters + " liters today");
        console.log("this cow has produced --->" + this._litersOfMilkPerWeek + "liters per week");
    }

    public feed(food: Food, amount: number): void {
        console.log("feeding the cow with " + amount + " of " + food);
    }

    constructor(isProducingMilk: boolean, litersOfMilkPerWeek: number, id: number, breed: string,
                bornOnDate: Date, isMeat: boolean) {
        super(id, breed, bornOnDate, isMeat);
        this._isProducingMilk = isProducingMilk;
        this._litersOfMilkPerWeek = litersOfMilkPerWeek;
    }

    public sendToGraze(location: Location, date: Date): void {
        console.log("send the cow to " + location + " at " + date.getHours() + " : " + date.getMinutes());
    }

    public override toString(): string {
        return "Cow{" + super.toString() + "isProducingMilk=" + this._isProducingMilk
            + ", litersOfMilkPerWeek=" + this._litersOfMilkPerWeek + "}";
    }

    public get isProducingMilk(): boolean {
        return this._isProducingMilk;
    }

    public set isProducingMilk(isProducingMilk: boolean) {
        this._isProducingMilk = isProducingMilk;
    }

    public get litersOfMilkPerWeek(): number {
        return this._litersOfMilkPerWeek;
    }

    public set litersOfMilkPerWeek(litersOfMilkPerWeek: number) {
        this._litersOfMilkPerWeek = litersOfMilkPerWeek;
    }
}
