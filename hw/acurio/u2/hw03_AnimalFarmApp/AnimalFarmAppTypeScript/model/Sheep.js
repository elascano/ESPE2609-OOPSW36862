"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Sheep = void 0;
const FarmAnimal_1 = require("./FarmAnimal");
class Sheep extends FarmAnimal_1.FarmAnimal {
    constructor(isSheared, lastShearing, id, breed, bornOnDate, isMeat) {
        super(id, breed, bornOnDate, isMeat);
        this.isSheared = isSheared;
        this.lastShearing = lastShearing;
    }
    toString() {
        return `Sheep{${super.toString()}, isSheared=${this.isSheared}, lastShearing=${this.lastShearing.toLocaleDateString("en-GB")}}`;
    }
    getIsSheared() {
        return this.isSheared;
    }
    setIsSheared(isSheared) {
        this.isSheared = isSheared;
    }
    getLastShearing() {
        return this.lastShearing;
    }
    setLastShearing(lastShearing) {
        this.lastShearing = lastShearing;
    }
}
exports.Sheep = Sheep;
