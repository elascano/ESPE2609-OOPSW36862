import { Food } from "./Food";

/**
 * @author Andrés Pachacama, ByteWorks, @ESPE
 */
export abstract class FarmAnimal {
    private _id: number;
    private _breed: string;
    private _bornOnDate: Date;
    private _isMeat: boolean;

    constructor(id: number, breed: string, bornOnDate: Date, isMeat: boolean) {
        this._id = id;
        this._breed = breed;
        this._bornOnDate = bornOnDate;
        this._isMeat = isMeat;
    }

    public abstract feed(food: Food, amount: number): void;

    public getAgeInMonths(): number {
        return 0;
    }

    public toString(): string {
        return "FarmAnimal{" + "id=" + this.id + ", breed=" + this.breed + ", is meat=" + this.isMeat
            + ", bornOnDate=" + this.bornOnDate.getDate() + " / "
            + (this.bornOnDate.getMonth() + 1) + " / " + this.bornOnDate.getFullYear()
            + "}, ";
    }

    public get id(): number {
        return this._id;
    }

    public set id(id: number) {
        this._id = id;
    }

    public get breed(): string {
        return this._breed;
    }

    public set breed(breed: string) {
        this._breed = breed;
    }

    public get bornOnDate(): Date {
        return this._bornOnDate;
    }

    public set bornOnDate(bornOnDate: Date) {
        this._bornOnDate = bornOnDate;
    }

    public get isMeat(): boolean {
        return this._isMeat;
    }

    public set isMeat(isMeat: boolean) {
        this._isMeat = isMeat;
    }
}
