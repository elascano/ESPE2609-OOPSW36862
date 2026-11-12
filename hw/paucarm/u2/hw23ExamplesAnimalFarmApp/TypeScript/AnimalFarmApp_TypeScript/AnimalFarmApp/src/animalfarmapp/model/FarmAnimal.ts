/**
 * @author Matias Paucar, ByteWorks, @ESPE
 */
export class FarmAnimal {
    private _id: number;
    private _breed: string;
    private _bornOnDate: Date;

    constructor(id: number, breed: string, bornOnDate: Date) {
        this._id = id;
        this._breed = breed;
        this._bornOnDate = bornOnDate;
    }

    public getAgeInMonths(): number {
        const today = new Date();
        let months = (today.getFullYear() - this._bornOnDate.getFullYear()) * 12
            + (today.getMonth() - this._bornOnDate.getMonth());
        if (today.getDate() < this._bornOnDate.getDate()) {
            months--;
        }
        return Math.max(months, 0);
    }

    public toString(): string {
        return "id=" + this._id + ", breed=" + this._breed +
            ", bornOnDate=" + this._bornOnDate.getDate() +
            "/" + (this._bornOnDate.getMonth() + 1) +
            "/" + this._bornOnDate.getFullYear() + ", ";
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
}
