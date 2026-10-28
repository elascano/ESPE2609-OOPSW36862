import { Egg } from "./Egg";
import { Poop } from "./Poop";

export class Chicken {
    private _id: number;
    private _name: string;
    private _color: string;
    private _bornOnDate: Date;
    private _ageInYears: number;
    private _molting: boolean;

    constructor(id: number, name: string, color: string, bornOnDate: Date, molting: boolean = false) {
        this._id = id;
        this._name = name;
        this._color = color;
        this._bornOnDate = bornOnDate;
        this._molting = molting;
        this._ageInYears = this.computeAgeInYears();
    }

    public get id(): number {
        return this._id;
    }

    public set id(value: number) {
        this._id = value;
    }

    public get name(): string {
        return this._name;
    }

    public set name(value: string) {
        this._name = value;
    }

    public get color(): string {
        return this._color;
    }

    public set color(value: string) {
        this._color = value;
    }

    public get bornOnDate(): Date {
        return this._bornOnDate;
    }

    public set bornOnDate(value: Date) {
        this._bornOnDate = value;
        this.setAgeInYears();
    }

    public get ageInYears(): number {
        return this._ageInYears;
    }

    public setAgeInYears(): void {
        this._ageInYears = this.computeAgeInYears();
    }

    public isMolting(): boolean {
        return this._molting;
    }

    public setMolting(value: boolean): void {
        this._molting = value;
    }

    public cluck(): void {
        console.log(`The chicken ${this._name} is clucking ...`);
    }

    public wander(): void {
        console.log(`The chicken ${this._name} is wandering ...`);
    }

    public eat(): void {
        console.log(`The chicken ${this._name} is eating ...`);
    }

    public drink(amountOfWater: number): void {
        console.log(`The chicken ${this._name} is drinking --> ${amountOfWater} ml of water`);
    }

    public static poop(): Poop {
        return new Poop(2, "green", "M");
    }

    public static layAnEgg(): Egg {
        return new Egg(2, "S");
    }

    public computeAgeInYears(): number {
        if (!this._bornOnDate) {
            return 0;
        }

        const today = new Date();
        let age = today.getFullYear() - this._bornOnDate.getFullYear();

        const monthDiff = today.getMonth() - this._bornOnDate.getMonth();
        if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < this._bornOnDate.getDate())) {
            age--;
        }

        return age;
    }

    public doStuff(): void {
        this.cluck();
        this.wander();
        this.eat();
        this.drink(1.3);
        console.log(`poop --> ${Chicken.poop().toString()}`);
        console.log(`egg --> ${Chicken.layAnEgg().toString()}`);
    }

    public toString(): string {
        return `Chicken{id=${this._id}, name='${this._name}', color='${this._color}', bornOnDate=${this._bornOnDate.toISOString().split('T')[0]}, age=${this._ageInYears}, molting=${this._molting}}`;
    }
}