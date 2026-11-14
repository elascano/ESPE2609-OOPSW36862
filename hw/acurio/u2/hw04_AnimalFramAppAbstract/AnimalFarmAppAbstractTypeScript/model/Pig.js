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
    showPigMeatCuts() {
        let meatCuts = ["head", "cheek", "picnic", "clear plate",
            "boston shoulder", "back fat", "loin", "ribs", "bacon", "leg", "ham", "hock"];
        console.log("Avalaible cuts for the pig[" + meatCuts.join(", ") + "]");
    }
    toString() {
        return `Pig{${super.toString()}isWallowing=${this.isWallowing}, weightGainPerWeek=${this.weightGainPerWeek}}`;
    }
    isIsWallowing() {
        return this.isWallowing;
    }
    setIsWallowing(isWallowing) {
        this.isWallowing = isWallowing;
    }
    getWeightGainPerWeek() {
        return this.weightGainPerWeek;
    }
    setWeightGainPerWeek(weightGainPerWeek) {
        this.weightGainPerWeek = weightGainPerWeek;
    }
}
exports.Pig = Pig;
