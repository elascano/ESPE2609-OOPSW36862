"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Chicken = void 0;
const Poop_1 = require("./Poop");
const Egg_1 = require("./Egg");
class Chicken {
    _id;
    _name;
    _color;
    _bornOnDate;
    _ageInYears;
    _molting;
    constructor(id, name, color, bornOnDate, age, molting) {
        this._id = id;
        this._name = name;
        this._color = color;
        this._bornOnDate = bornOnDate;
        this._ageInYears = this.computeAgeInYears();
        this._molting = molting;
    }
    doStuff() {
        this.cluck();
        this.wander();
        this.drink(1.3);
        console.log(`poop --> ${this.poop().toString()}`);
        console.log(`egg --> ${this.layAnEgg().toString()}`);
    }
    cluck() {
        console.log(`The chicken ${this._name} is clucking...`);
    }
    wander() {
        console.log(`The chicken ${this._name} is wandering...`);
    }
    eat() {
        console.log(`The chicken ${this._name} is eating...`);
    }
    drink(amount0Water) {
        console.log(`The chicken ${this._name} is drinking --> ${amount0Water} ml of water`);
    }
    poop() {
        return new Poop_1.Poop(2, 'green', 'M');
    }
    layAnEgg() {
        return new Egg_1.Egg(2, '5');
    }
    computeAgeInYears() {
        if (!this._bornOnDate) {
            return 0;
        }
        const today = new Date();
        const birth = new Date(this._bornOnDate);
        let age = today.getFullYear() - birth.getFullYear();
        const monthDiff = today.getMonth() - birth.getMonth();
        if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birth.getDate())) {
            age--;
        }
        return age;
    }
    get id() {
        return this._id;
    }
    set id(id) {
        this._id = id;
    }
    get name() {
        return this._name;
    }
    set name(name) {
        this._name = name;
    }
    get color() {
        return this._color;
    }
    set color(color) {
        this._color = color;
    }
    get bornOnDate() {
        return this._bornOnDate;
    }
    set bornOnDate(bornOnDate) {
        this._bornOnDate = bornOnDate;
        this._ageInYears = this.computeAgeInYears();
    }
    get ageInYears() {
        return this._ageInYears;
    }
    updateAge() {
        this._ageInYears = this.computeAgeInYears();
    }
    isMolting() {
        return this._molting;
    }
    set molting(molting) {
        this._molting = molting;
    }
    toString() {
        return `Chicken{id=${this._id}, name=${this._name}, color=${this._color}, bornOnDate=${this._bornOnDate}, age=${this._ageInYears}, molting=${this._molting}}`;
    }
}
exports.Chicken = Chicken;
