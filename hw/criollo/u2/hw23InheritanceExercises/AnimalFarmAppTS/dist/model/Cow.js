"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Cow = void 0;
const FarmAnimal_1 = require("./FarmAnimal");
class Cow extends FarmAnimal_1.FarmAnimal {
    constructor(id, breed, bornOnDate, isMeat, isProducingMilk, litersOfMilkPerWeek) {
        super(id, breed, bornOnDate, isMeat);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }
    toString() {
        return super.toString() + ", Cow(isProducingMilk= " + this.isProducingMilk + ", litersOfMilkPerWeek= " + this.litersOfMilkPerWeek + ")";
    }
}
exports.Cow = Cow;
