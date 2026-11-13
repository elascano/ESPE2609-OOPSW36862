"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Chicken = void 0;
const FarmAnimal_1 = require("./FarmAnimal");
class Chicken extends FarmAnimal_1.FarmAnimal {
    constructor(isMolting, numberOfLaidEggsPerWeek, id, breed, bornOnDate) {
        super(id, breed, bornOnDate, isMolting);
        this.isMolting = isMolting;
        this.numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek;
    }
    layAnEgg() {
        console.log("chicken is laying an egg");
    }
    toString() {
        return `Chicken{${super.toString()}isMolting=${this.isMolting}, numberOfLaidEggsPerWeek=${this.numberOfLaidEggsPerWeek}}`;
    }
    isIsMolting() {
        return this.isMolting;
    }
    setIsMolting(isMolting) {
        this.isMolting = isMolting;
    }
    getNumberOfLaidEggsPerWeek() {
        return this.numberOfLaidEggsPerWeek;
    }
    setNumberOfLaidEggsPerWeek(numberOfLaidEggsPerWeek) {
        this.numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek;
    }
}
exports.Chicken = Chicken;
