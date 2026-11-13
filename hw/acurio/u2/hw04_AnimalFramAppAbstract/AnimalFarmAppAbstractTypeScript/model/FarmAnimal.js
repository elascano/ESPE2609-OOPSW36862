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
        return `FarmAnimal{id=${this.getId()}, breed=${this.getBreed()}, is meat=${this.isIsMeat()}, bornOnDate=${this.bornOnDate.getDate()} / ${this.bornOnDate.getMonth() + 1} / ${this.bornOnDate.getFullYear()}}, `;
    }
    getId() {
        return this.id;
    }
    setId(id) {
        this.id = id;
    }
    getBreed() {
        return this.breed;
    }
    setBreed(breed) {
        this.breed = breed;
    }
    getBornOnDate() {
        return this.bornOnDate;
    }
    setBornOnDate(bornOnDate) {
        this.bornOnDate = bornOnDate;
    }
    isIsMeat() {
        return this.isMeat;
    }
    setIsMeat(isMeat) {
        this.isMeat = isMeat;
    }
}
exports.FarmAnimal = FarmAnimal;
