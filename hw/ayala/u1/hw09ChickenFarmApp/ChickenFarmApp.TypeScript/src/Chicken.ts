import { Egg } from './Egg.js';
import { Poop } from './Poop.js';

/**
 * @author Enrique Ayala, LogicLords,@ESPE
 */
export class Chicken {
    private _id: number;
    private _name: string;
    private _color: string;
    private _bornOnDate: Date;
    private _ageInYears: number;
    private _molting: boolean;
    private _computeAgeInYears: number;

    constructor(id: number, name: string, color: string, bornOnDate: Date, molting: boolean) {
        this._id = id;
        this._name = name;
        this._color = color;
        this._bornOnDate = bornOnDate;
        this._molting = molting;
        this._ageInYears = this.computeAgeInYears();
        this._computeAgeInYears = this._ageInYears;
    }

    public doStuff(): void {
        this.cluck();
        this.eat();
        this.wander();
        this.drink(1.3);
        const p = this.poop();
        console.log(`poop -->${p.toString()}`);
        console.log(`egg${this.layAnEgg().toString()}`);
    }

    private cluck(): void {
        console.log(`the chicken is clicking${this._name}is clucking...`);
    }

    private wander(): void {
        console.log(`The chicken${this._name}is wandering...`);
    }

    private eat(): void {
        console.log(`The chicken${this._name}is eating...`);
    }

    private drink(amountOfWater: number): void {
        console.log(`The chicken${this._name}is wandering-->${amountOfWater}ml of water`);
    }

    private poop(): Poop {
        return new Poop(2, "green", 'M');
    }

    private layAnEgg(): Egg {
        return new Egg(2, 'L');
    }

    public computeAgeInYears(): number {
        const today = new Date();
        let age = today.getFullYear() - this._bornOnDate.getFullYear();
        const monthDiff = today.getMonth() - this._bornOnDate.getMonth();
        
        if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < this._bornOnDate.getDate())) {
            age--;
        }
        return age;
    }

    // Getters y Setters
    public get id(): number { return this._id; }
    public set id(value: number) { this._id = value; }

    public get name(): string { return this._name; }
    public set name(value: string) { this._name = value; }

    public get color(): string { return this._color; }
    public set color(value: string) { this._color = value; }

    public get bornOnDate(): Date { return this._bornOnDate; }
    public set bornOnDate(value: Date) { this._bornOnDate = value; }

    public get ageInYears(): number { return this._ageInYears; }
    public setAgeInYears(): void { this._ageInYears = this.computeAgeInYears(); }

    public get molting(): boolean { return this._molting; }
    public set molting(value: boolean) { this._molting = value; }

    public get computeAgeInYearsAttr(): number { return this._computeAgeInYears; }
    public set computeAgeInYearsAttr(value: number) { this._computeAgeInYears = value; }

    public toString(): string {
        const formattedDate = this._bornOnDate.toISOString().split('T')[0];
        return `Chicken{id=${this._id}, name='${this._name}', color='${this._color}', bornOnDate=${formattedDate}, age=${this._ageInYears}, molting=${this._molting}}`;
    }
}