import { FarmAnimal } from "./FarmAnimal";
import { Food } from "./Food";
import { SellAnimal } from "./SellAnimal";

/**
 * @author Andrés Pachacama, ByteWorks, @ESPE
 */
export class Chicken extends FarmAnimal implements SellAnimal {
    private _isMolting: boolean;
    private _numberOfLaidEggsPerWeek: number;

    public feed(food: Food, amount: number): void {
        console.log("feeding a chicken with " + amount + " pound of " + food);
    }

    public sellAnimal(price: number): void {
        console.log("sold the chicken in " + price + " dollars.");
    }

    public layAnEgg(): void {
        this._numberOfLaidEggsPerWeek++;
        console.log("chicken is laying an egg");
    }

    constructor(isMolting: boolean, numberOfLaidEggsPerWeek: number, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate, isMolting);
        this._isMolting = isMolting;
        this._numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek;
    }

    public override toString(): string {
        return "Chicken{" + super.toString() + "isMolting=" + this._isMolting
            + ", numberOfLaidEggsPerWeek=" + this._numberOfLaidEggsPerWeek + "}";
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

    public set numberOfLaidEggsPerWeek(numberOfLaidEggsPerWeek: number) {
        this._numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek;
    }
}
