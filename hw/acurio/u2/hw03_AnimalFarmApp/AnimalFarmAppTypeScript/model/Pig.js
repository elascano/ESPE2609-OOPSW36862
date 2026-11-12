"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Pig = void 0;
const FarmAnimal_1 = require("./FarmAnimal");
class Pig extends FarmAnimal_1.FarmAnimal {
    constructor(isWallowing, weightGainPerWeek, id, breed, bornOnDate, isMeat) {
        super(id, breed, bornOnDate, isMeat);
        this.isWallowing = isWallowing;
        this.weightGainPerWeek = weightGainPerWeek;
    }
    toString() {
        return `Pig{${super.toString()}, isWallowing=${this.isWallowing}, weightGainPerWeek=${this.weightGainPerWeek}}`;
    }
    getIsWallowing() {
        return this.isWallowing;
    }
    setIsWallowing(isWallowing) {
        this.isWallowing = isWallowing;
    }
    getWeightGainPerWeek() {
        return this.weightGainPerWeek;
    }
    setWeightGainPerWeek(weight) {
        this.weightGainPerWeek = weight;
    }
}
exports.Pig = Pig;
