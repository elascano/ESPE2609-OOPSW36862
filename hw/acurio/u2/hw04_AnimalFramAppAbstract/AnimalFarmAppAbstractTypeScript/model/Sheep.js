"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Sheep = void 0;
const FarmAnimal_1 = require("./FarmAnimal");
class Sheep extends FarmAnimal_1.FarmAnimal {
    constructor(isSheared, lastSheering, id, breed, bornOnDate, isMeat) {
        super(id, breed, bornOnDate, isMeat);
        this.isSheared = isSheared;
        this.lastSheering = lastSheering;
    }
    shearSheep() {
        console.log("Sheep is sheared");
    }
    toString() {
        return `Sheep{${super.toString()}isSheared=${this.isSheared}, lastSheering=${this.lastSheering.toDateString()}}`;
    }
    isIsSheared() {
        return this.isSheared;
    }
    setIsSheared(isSheared) {
        this.isSheared = isSheared;
    }
    getLastSheering() {
        return this.lastSheering;
    }
    setLastSheering(lastSheering) {
        this.lastSheering = lastSheering;
    }
}
exports.Sheep = Sheep;
