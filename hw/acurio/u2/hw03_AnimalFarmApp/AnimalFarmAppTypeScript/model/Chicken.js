"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Chicken = void 0;
const FarmAnimal_1 = require("./FarmAnimal");
class Chicken extends FarmAnimal_1.FarmAnimal {
    constructor(isMolting, numberOfLaidEggsPerWeek, id, breed, bornOnDate, isMeat = false) {
        super(id, breed, bornOnDate, isMeat);
        this.isMolting = isMolting;
        this.numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek;
    }
    toString() {
        return `Chicken{${super.toString()}, isMolting=${this.isMolting}, numberOfLaidEggsPerWeek=${this.numberOfLaidEggsPerWeek}}`;
    }
    getIsMolting() {
        return this.isMolting;
    }
    setIsMolting(isMolting) {
        this.isMolting = isMolting;
    }
    getNumberOfLaidEggsPerWeek() {
        return this.numberOfLaidEggsPerWeek;
    }
    setNumberOfLaidEggsPerWeek(number) {
        this.numberOfLaidEggsPerWeek = number;
    }
}
exports.Chicken = Chicken;
