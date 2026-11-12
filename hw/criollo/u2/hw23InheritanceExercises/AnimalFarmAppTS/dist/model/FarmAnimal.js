"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.FarmAnimal = void 0;
class FarmAnimal {
    constructor(id, breed, bornOnDate, isMeat) {
        this.id = id;
        this.breed = breed;
        this.bornOnDate = bornOnDate;
        this.isMeat = isMeat;
    }
    toString() {
        return "FarmAnimal(id=" + this.id + ", breed=" + this.breed + ", bornOnDate " + this.bornOnDate + ", isMeat= " + this.isMeat + ")";
    }
}
exports.FarmAnimal = FarmAnimal;
