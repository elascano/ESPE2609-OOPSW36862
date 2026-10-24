"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Chicken = void 0;
const Egg_1 = require("./Egg");
const Poop_1 = require("./Poop");
class Chicken {
    id;
    name;
    color;
    bornOnDate;
    molting;
    ageInYears;
    constructor(id, name, color, bornOnDate, molting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.bornOnDate = bornOnDate;
        this.molting = molting;
        this.ageInYears = this.computeAgeInYears();
    }
    doStuff() {
        this.cluck();
        this.wander();
        this.drink(1.3);
        console.log("poop --> " + this.poop());
        console.log("egg --> " + this.layAnEgg());
    }
    cluck() { console.log(`the chicken ${this.name} is clucking ...`); }
    wander() { console.log(`the chicken ${this.name} is wandering ...`); }
    eat() { console.log(`the chicken ${this.name} is eating ...`); }
    drink(amountOfWater) {
        console.log(`the chicken ${this.name} is drinking --> ${amountOfWater} ml of water`);
    }
    poop() { return new Poop_1.Poop(2, "green", "M"); }
    layAnEgg() { return new Egg_1.Egg(2, "S"); }
    computeAgeInYears() {
        const today = new Date();
        let age = today.getFullYear() - this.bornOnDate.getFullYear();
        if (today.getMonth() < this.bornOnDate.getMonth() ||
            (today.getMonth() === this.bornOnDate.getMonth() &&
                today.getDate() < this.bornOnDate.getDate())) {
            age--;
        }
        return age;
    }
    getId() { return this.id; }
    setId(id) { this.id = id; }
    getName() { return this.name; }
    setName(name) { this.name = name; }
    getColor() { return this.color; }
    setColor(color) { this.color = color; }
    getBorn0nDate() { return this.bornOnDate; }
    setBorn0nDate(bornOnDate) {
        this.bornOnDate = bornOnDate;
        this.setAgeInYears();
    }
    getAgeInYears() { return this.ageInYears; }
    setAgeInYears(ageInYears) {
        this.ageInYears = ageInYears === undefined ? this.computeAgeInYears() : ageInYears;
    }
    isMolting() { return this.molting; }
    setMolting(molting) { this.molting = molting; }
    toString() {
        const born = this.bornOnDate.getFullYear() + "-" +
            String(this.bornOnDate.getMonth() + 1).padStart(2, "0") + "-" +
            String(this.bornOnDate.getDate()).padStart(2, "0");
        return `Chicken{id=${this.id}, name=${this.name}, color=${this.color}, born0nDate=${born}, age=${this.ageInYears}, molting=${this.molting}}`;
    }
}
exports.Chicken = Chicken;
