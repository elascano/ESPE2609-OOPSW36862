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
    getAgeInMonths() {
        return 0;
    }
    toString() {
        const date = this.bornOnDate;
        return `FarmAnimal{id=${this.id}, breed=${this.breed}, is meat=${this.isMeat}, bornOnDate=${date.getDate()} / ${date.getMonth() + 1} / ${date.getFullYear()}}, `;
    }
    getId() { return this.id; }
    setId(id) { this.id = id; }
    getBreed() { return this.breed; }
    setBreed(breed) { this.breed = breed; }
    getBornOnDate() { return this.bornOnDate; }
    setBornOnDate(date) { this.bornOnDate = date; }
    isIsMeat() { return this.isMeat; }
    setIsMeat(isMeat) { this.isMeat = isMeat; }
}
exports.FarmAnimal = FarmAnimal;
