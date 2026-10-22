"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Chicken = void 0;
const Egg_1 = require("./Egg");
const Poop_1 = require("./Poop");
function formatDate(d) {
    const mm = String(d.getMonth() + 1).padStart(2, "0");
    const dd = String(d.getDate()).padStart(2, "0");
    return `${d.getFullYear()}-${mm}-${dd}`;
}
class Chicken {
    id;
    name;
    color;
    bornOnDate;
    molting;
    ageInYears = 0;
    constructor(id, name, color, bornOnDate, molting = false) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.bornOnDate = bornOnDate;
        this.molting = molting;
    }
    doStuff() {
        this.cluck();
        this.wander();
        this.drink(1.3);
        console.log("poop-->" + this.poop());
        console.log("egg-->" + this.layAnEgg());
    }
    cluck() {
        console.log(`The chicken ${this.name} is clucking ...`);
    }
    wander() {
        console.log(`The chicken ${this.name} is wandering ...`);
    }
    eat() {
        console.log(`The chicken ${this.name} is eating ...`);
    }
    drink(amountOfWater) {
        console.log(`The chicken ${this.name} is drinking --> ${amountOfWater}ml of water`);
    }
    poop() {
        return new Poop_1.Poop(2, "green", "M");
    }
    layAnEgg() {
        return new Egg_1.Egg(2, "S");
    }
    computeAgeInYears() {
        const today = new Date();
        let age = today.getFullYear() - this.bornOnDate.getFullYear();
        const beforeBirthday = today.getMonth() < this.bornOnDate.getMonth() ||
            (today.getMonth() === this.bornOnDate.getMonth() &&
                today.getDate() < this.bornOnDate.getDate());
        if (beforeBirthday)
            age--;
        return age;
    }
    setAgeInYears() {
        this.ageInYears = this.computeAgeInYears();
    }
    getAgeInYears() {
        return this.ageInYears;
    }
    toString() {
        return (`Chicken{id=${this.id}, name=${this.name}, color=${this.color}, ` +
            `bornOnDate=${formatDate(this.bornOnDate)}, age=${this.ageInYears}, molting=${this.molting}}`);
    }
}
exports.Chicken = Chicken;
