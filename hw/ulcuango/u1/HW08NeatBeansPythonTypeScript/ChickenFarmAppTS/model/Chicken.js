"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Chicken = void 0;
const Egg_1 = require("./Egg");
const Poop_1 = require("./Poop");
/**
 * @author Josselyn Ulcuango, Codelex, @ESPE
 */
class Chicken {
    constructor(id, name, color, bornOnDate, age, molting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.bornOnDate = bornOnDate;
        this.ageInYear = age;
        this.molting = molting;
    }
    doStuff() {
        this.cluck();
        this.wander();
        this.drink(1.3);
        console.log(`poop --> ${this.poop().toString()}`);
        console.log(`egg --> ${this.layAnEgg().toString()}`);
    }
    cluck() {
        console.log(`The chicken ${this.name} is clucking`);
    }
    wander() {
        console.log(`The chicken ${this.name} is wandering`);
    }
    drink(amountOfWater) {
        console.log(`The chicken ${this.name} is drinking --> ${amountOfWater} ml of water`);
    }
    poop() {
        return new Poop_1.Poop(2, "green", "M");
    }
    layAnEgg() {
        return new Egg_1.Egg(2, "S");
    }
    computeAgeInYears() {
        const today = new Date();
        const birth = this.bornOnDate;
        let age = today.getFullYear() - birth.getFullYear();
        const m = today.getMonth() - birth.getMonth();
        if (m < 0 || (m === 0 && today.getDate() < birth.getDate())) {
            age--;
        }
        return age;
    }
    toString() {
        return `Chicken{id=${this.id}, name=${this.name}, color=${this.color}, bornOnDate=${this.bornOnDate.toDateString()}, age=${this.ageInYear}, molting=${this.molting}}`;
    }
    getId() { return this.id; }
    setId(id) { this.id = id; }
    getName() { return this.name; }
    setName(name) { this.name = name; }
    getColor() { return this.color; }
    setColor(color) { this.color = color; }
    getBornOnDate() { return this.bornOnDate; }
    setBornOnDate(bornOnDate) { this.bornOnDate = bornOnDate; }
    getAgeInYear() { return this.ageInYear; }
    setAgeInYear(age) { this.ageInYear = age; }
    isMolting() { return this.molting; }
    setMolting(molting) { this.molting = molting; }
}
exports.Chicken = Chicken;
