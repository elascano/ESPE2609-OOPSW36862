"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Cow = void 0;
const FarmAnimal_1 = require("./FarmAnimal");
class Cow extends FarmAnimal_1.FarmAnimal {
    constructor(isProducingMilk, litersOfMilkPerWeek, id, breed, bornOnDate, isMeat) {
        super(id, breed, bornOnDate, isMeat);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }
    milk() {
        console.log("cow is producing milk");
    }
    toString() {
        return `Cow{${super.toString()}isProducingMilk=${this.isProducingMilk}, litersOfMilkPerWeek=${this.litersOfMilkPerWeek}}`;
    }
    isIsProducingMilk() {
        return this.isProducingMilk;
    }
    setIsProducingMilk(isProducingMilk) {
        this.isProducingMilk = isProducingMilk;
    }
    getLitersOfMilkPerWeek() {
        return this.litersOfMilkPerWeek;
    }
    setLitersOfMilkPerWeek(litersOfMilkPerWeek) {
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }
}
exports.Cow = Cow;
