"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Pig = void 0;
const FarmAnimal_1 = require("./FarmAnimal");
class Pig extends FarmAnimal_1.FarmAnimal {
    constructor(id, breed, bornOnDate, isMeat, isWallowing, weightGainPerWeek) {
        super(id, breed, bornOnDate, isMeat);
        this.isWallowing = isWallowing;
        this.weightGainPerWeek = weightGainPerWeek;
    }
    toString() {
        return super.toString() + ", Pig(isWallowing= " + this.isWallowing + ", weightGainPerWeek= " + this.weightGainPerWeek + ")";
    }
}
exports.Pig = Pig;
