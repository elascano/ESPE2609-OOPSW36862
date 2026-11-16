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
    feed(food, amount) {
        console.log(`feeding the sheep with ${amount} kg of ${food}`);
    }
    sendToGraze(location, date) {
        console.log(`send the sheep to ${location} at ${date.getHours()} : ${date.getMinutes()}`);
    }
    toString() {
        return `Sheep{${super.toString()}isSheared=${this.isSheared}, lastSheering=${this.lastSheering.toString()}}`;
    }
    isIsSheared() {
        return this.isSheared;
    }
    setIsSheared(value) {
        this.isSheared = value;
    }
    getLastSheering() {
        return this.lastSheering;
    }
    setLastSheering(value) {
        this.lastSheering = value;
    }
}
exports.Sheep = Sheep;
