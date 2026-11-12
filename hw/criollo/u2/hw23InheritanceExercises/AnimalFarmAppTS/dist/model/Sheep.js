"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Sheep = void 0;
const FarmAnimal_1 = require("./FarmAnimal");
class Sheep extends FarmAnimal_1.FarmAnimal {
    constructor(id, breed, bornOnDate, isMeat, isSheared, lastSheering) {
        super(id, breed, bornOnDate, isMeat);
        this.isSheared = isSheared;
        this.lastSheering = lastSheering;
    }
    toString() {
        return super.toString() + ", Sheep(isSheared= " + this.isSheared + ", lastSheering= " + this.lastSheering + ")";
    }
}
exports.Sheep = Sheep;
