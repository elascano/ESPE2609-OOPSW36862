"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Chicken = void 0;
const FarmAnimal_1 = require("./FarmAnimal");
class Chicken extends FarmAnimal_1.FarmAnimal {
    constructor(isMolting, eggs, id, breed, bornOnDate) {
        super(id, breed, bornOnDate, isMolting);
        this.isMolting = isMolting;
        this.numberOfLaidEggsPerWeek = eggs;
    }
    feed(food, amount) {
        console.log(`feeding a chicken with ${amount} pound of ${food}`);
    }
    sellAnimal(price) {
        console.log(`sold the chicken in ${price} dollars.`);
    }
    layAnEgg() {
        this.numberOfLaidEggsPerWeek++;
        console.log("chicken is laying an egg");
    }
    toString() {
        return `Chicken{${super.toString()}isMolting=${this.isMolting}, numberOfLaidEggsPerWeek=${this.numberOfLaidEggsPerWeek}}`;
    }
    isIsMolting() { return this.isMolting; }
    setIsMolting(value) { this.isMolting = value; }
    getNumberOfLaidEggsPerWeek() {
        return this.numberOfLaidEggsPerWeek;
    }
    setNumberOfLaidEggsPerWeek(value) {
        this.numberOfLaidEggsPerWeek = value;
    }
}
exports.Chicken = Chicken;
