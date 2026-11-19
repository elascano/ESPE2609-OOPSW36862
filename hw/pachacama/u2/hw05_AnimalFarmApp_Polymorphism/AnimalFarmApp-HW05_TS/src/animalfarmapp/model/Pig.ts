import { FarmAnimal } from "./FarmAnimal";
import { Food } from "./Food";
import { SellAnimal } from "./SellAnimal";

/**
 * @author Andrés Pachacama, ByteWorks, @ESPE
 */
export class Pig extends FarmAnimal implements SellAnimal {
    private _isWallowing: boolean;
    private _weightGainPerWeek: number;

    public showPigMeatCuts(): void {
        const meatCuts: string[] = ["head", "cheek", "picnic", "clear plate", "boston shoulder", "back fat",
            "loin", "ribs", "bacon", "leg", "ham", "hock"];
        console.log("Avalaible cuts for the pig" + "[" + meatCuts.join(", ") + "]");
    }

    public feed(food: Food, amount: number): void {
        console.log("feeding the pig with " + amount + " kg of " + food);
    }

    public sellAnimal(price: number): void {
        console.log("sold the pig in " + price + " dollars.");
    }

    public override toString(): string {
        return "Pig{" + super.toString() + "isWallowing=" + this._isWallowing
            + ", weightGainPerWeek=" + this._weightGainPerWeek + "}";
    }

    constructor(isWallowing: boolean, weightGainPerWeek: number, id: number, breed: string,
                bornOnDate: Date, isMeat: boolean) {
        super(id, breed, bornOnDate, isMeat);
        this._isWallowing = isWallowing;
        this._weightGainPerWeek = weightGainPerWeek;
    }

    public get isWallowing(): boolean {
        return this._isWallowing;
    }

    public set isWallowing(isWallowing: boolean) {
        this._isWallowing = isWallowing;
    }

    public getWeightGainPerWeek(weightGain: number): void {
        console.log("the pig has gained " + weightGain + " kg of weight");
    }

    public setWeightGainPerWeek(weightGainPerWeek: number): void {
        this._weightGainPerWeek = weightGainPerWeek;
    }
}
