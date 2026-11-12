"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Chicken = void 0;
const FarmAnimal_1 = require("./FarmAnimal");
class Chicken extends FarmAnimal_1.FarmAnimal {
    constructor(id, breed, bornOnDate, isMeat, isMolting, numberOfLaidEggsPerWeek) {
        super(id, breed, bornOnDate, isMeat);
        this.isMolting = isMolting;
        this.numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek;
    }
    toString() {
        return super.toString() + ", Chicken(isMolting=" + this.isMolting + ", numberOfEggsLaidPerWeek= " + this.numberOfLaidEggsPerWeek + ")";
    }
}
exports.Chicken = Chicken;
