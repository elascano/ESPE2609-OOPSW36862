import { FarmAnimal } from "./FarmAnimal";
import { Food } from "./Food";
import { GrazeAnimalI } from "./GrazeAnimalI";
import { Location } from "./Location";

/**
 * @author Andrés Pachacama, ByteWorks, @ESPE
 */
export class Sheep extends FarmAnimal implements GrazeAnimalI {
    private static readonly WEEKDAYS = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
    private static readonly MONTHS = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

    private _isSheared: boolean;
    private _lastSheering: Date;

    public shearSheep(): void {
        console.log("Sheep is sheared");
    }

    public feed(food: Food, amount: number): void {
        console.log("feeding the sheep with " + amount + " kg of " + food);
    }

    public sendToGraze(location: Location, date: Date): void {
        console.log("send the sheep to " + location + " at " + date.getHours() + " : " + date.getMinutes());
    }

    public override toString(): string {
        return "Sheep{" + super.toString() + "isSheared=" + this._isSheared
            + ", lastSheering=" + Sheep.formatDateTime(this._lastSheering) + "}";
    }

    constructor(isSheared: boolean, lastSheering: Date, id: number, breed: string,
                bornOnDate: Date, isMeat: boolean) {
        super(id, breed, bornOnDate, isMeat);
        this._isSheared = isSheared;
        this._lastSheering = lastSheering;
    }

    private static formatDateTime(value: Date): string {
        const twoDigits = (n: number): string => String(n).padStart(2, "0");
        const offsetMinutes = -value.getTimezoneOffset();
        const sign = offsetMinutes >= 0 ? "+" : "-";
        const zone = "GMT" + sign + twoDigits(Math.floor(Math.abs(offsetMinutes) / 60))
            + ":" + twoDigits(Math.abs(offsetMinutes) % 60);
        return Sheep.WEEKDAYS[value.getDay()] + " " + Sheep.MONTHS[value.getMonth()] + " "
            + twoDigits(value.getDate()) + " " + twoDigits(value.getHours()) + ":"
            + twoDigits(value.getMinutes()) + ":" + twoDigits(value.getSeconds()) + " "
            + zone + " " + value.getFullYear();
    }

    public get isSheared(): boolean {
        return this._isSheared;
    }

    public set isSheared(isSheared: boolean) {
        this._isSheared = isSheared;
    }

    public get lastSheering(): Date {
        return this._lastSheering;
    }

    public set lastSheering(lastSheering: Date) {
        this._lastSheering = lastSheering;
    }
}
