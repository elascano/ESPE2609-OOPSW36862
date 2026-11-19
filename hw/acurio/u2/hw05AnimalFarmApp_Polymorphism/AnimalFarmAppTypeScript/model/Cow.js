"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Cow = void 0;
const FarmAnimal_1 = require("./FarmAnimal");
class Cow extends FarmAnimal_1.FarmAnimal {
    constructor(isProducingMilk, liters, id, breed, bornOnDate, isMeat) {
        super(id, breed, bornOnDate, isMeat);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkPerWeek = liters;
    }
    milk(liters) {
        if (liters !== undefined) {
            this.litersOfMilkPerWeek += liters;
            console.log(`milking the cow... we got --->${liters} liters today`);
        }
        console.log(`this cow has produced --->${this.litersOfMilkPerWeek}liters per week`);
    }
    feed(food, amount) {
        console.log(`feeding the cow with ${amount} of ${food}`);
    }
    sendToGraze(location, date) {
        console.log(`send the cow to ${location} at ${date.getHours()} : ${date.getMinutes()}`);
    }
    toString() {
        return `Cow{${super.toString()}isProducingMilk=${this.isProducingMilk}, litersOfMilkPerWeek=${this.litersOfMilkPerWeek}}`;
    }
    isIsProducingMilk() {
        return this.isProducingMilk;
    }
    setIsProducingMilk(value) {
        this.isProducingMilk = value;
    }
    getLitersOfMilkPerWeek() {
        return this.litersOfMilkPerWeek;
    }
    setLitersOfMilkPerWeek(value) {
        this.litersOfMilkPerWeek = value;
    }
}
exports.Cow = Cow;
