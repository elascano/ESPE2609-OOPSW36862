"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Pig = void 0;
const FarmAnimal_1 = require("./FarmAnimal");
class Pig extends FarmAnimal_1.FarmAnimal {
    constructor(isWallowing, weightGain, id, breed, bornOnDate, isMeat) {
        super(id, breed, bornOnDate, isMeat);
        this.isWallowing = isWallowing;
        this.weightGainPerWeek = weightGain;
    }
    showPigMeatCuts() {
        const cuts = ["head", "cheek", "picnic", "clear plate",
            "boston shoulder", "back fat", "loin", "ribs",
            "bacon", "leg", "ham", "hock"];
        console.log("Avalaible cuts for the pig[" + cuts.join(", ") + "]");
    }
    feed(food, amount) {
        console.log(`feeding the pig with ${amount} kg of ${food}`);
    }
    sellAnimal(price) {
        console.log(`sold the pig in ${price} dollars.`);
    }
    getWeightGainPerWeek(weightGain) {
        console.log(`the pig has gained ${weightGain} kg of weight`);
    }
    toString() {
        return `Pig{${super.toString()}isWallowing=${this.isWallowing}, weightGainPerWeek=${this.weightGainPerWeek}}`;
    }
    isIsWallowing() {
        return this.isWallowing;
    }
    setIsWallowing(value) {
        this.isWallowing = value;
    }
    setWeightGainPerWeek(value) {
        this.weightGainPerWeek = value;
    }
}
exports.Pig = Pig;
