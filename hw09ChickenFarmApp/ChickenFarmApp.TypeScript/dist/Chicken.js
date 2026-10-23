import { Egg } from './Egg.js';
import { Poop } from './Poop.js';
/**
 * @author Enrique Ayala, LogicLords,@ESPE
 */
export class Chicken {
    _id;
    _name;
    _color;
    _bornOnDate;
    _ageInYears;
    _molting;
    _computeAgeInYears;
    constructor(id, name, color, bornOnDate, molting) {
        this._id = id;
        this._name = name;
        this._color = color;
        this._bornOnDate = bornOnDate;
        this._molting = molting;
        this._ageInYears = this.computeAgeInYears();
        this._computeAgeInYears = this._ageInYears;
    }
    doStuff() {
        this.cluck();
        this.eat();
        this.wander();
        this.drink(1.3);
        const p = this.poop();
        console.log(`poop -->${p.toString()}`);
        console.log(`egg${this.layAnEgg().toString()}`);
    }
    cluck() {
        console.log(`the chicken is clicking${this._name}is clucking...`);
    }
    wander() {
        console.log(`The chicken${this._name}is wandering...`);
    }
    eat() {
        console.log(`The chicken${this._name}is eating...`);
    }
    drink(amountOfWater) {
        console.log(`The chicken${this._name}is wandering-->${amountOfWater}ml of water`);
    }
    poop() {
        return new Poop(2, "green", 'M');
    }
    layAnEgg() {
        return new Egg(2, 'L');
    }
    computeAgeInYears() {
        const today = new Date();
        let age = today.getFullYear() - this._bornOnDate.getFullYear();
        const monthDiff = today.getMonth() - this._bornOnDate.getMonth();
        if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < this._bornOnDate.getDate())) {
            age--;
        }
        return age;
    }
    // Getters y Setters
    get id() { return this._id; }
    set id(value) { this._id = value; }
    get name() { return this._name; }
    set name(value) { this._name = value; }
    get color() { return this._color; }
    set color(value) { this._color = value; }
    get bornOnDate() { return this._bornOnDate; }
    set bornOnDate(value) { this._bornOnDate = value; }
    get ageInYears() { return this._ageInYears; }
    setAgeInYears() { this._ageInYears = this.computeAgeInYears(); }
    get molting() { return this._molting; }
    set molting(value) { this._molting = value; }
    get computeAgeInYearsAttr() { return this._computeAgeInYears; }
    set computeAgeInYearsAttr(value) { this._computeAgeInYears = value; }
    toString() {
        const formattedDate = this._bornOnDate.toISOString().split('T')[0];
        return `Chicken{id=${this._id}, name='${this._name}', color='${this._color}', bornOnDate=${formattedDate}, age=${this._ageInYears}, molting=${this._molting}}`;
    }
}
