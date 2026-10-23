/**
 * @author Enrique Ayala, LogicLords,@ESPE
 */
export class ChickenCoop {
    static MAX_CAPACITY = 10;
    _chickenCoopId;
    _chickens;
    _count;
    _currentIndex;
    constructor(chickenCoopId) {
        this._chickenCoopId = chickenCoopId;
        this._chickens = new Array(ChickenCoop.MAX_CAPACITY).fill(null);
        this._count = 0;
        this._currentIndex = 0;
    }
    add(chicken) {
        if (this.findIndexById(chicken.id) !== -1) {
            console.log(`There is already a chicken with id ${chicken.id}`);
            return false;
        }
        if (this._count >= ChickenCoop.MAX_CAPACITY) {
            console.log(`The coop is full (maximum ${ChickenCoop.MAX_CAPACITY}).`);
            return false;
        }
        this._chickens[this._count] = chicken;
        this._count++;
        return true;
    }
    remove(chickenId) {
        const index = this.findIndexById(chickenId);
        if (index === -1) {
            console.log(`There is no chicken with id ${chickenId}`);
            return false;
        }
        for (let i = index; i < this._count - 1; i++) {
            this._chickens[i] = this._chickens[i + 1];
        }
        this._chickens[this._count - 1] = null;
        this._count--;
        return true;
    }
    update(chickenId, name, color, bornOnDate, molting) {
        const index = this.findIndexById(chickenId);
        if (index === -1) {
            console.log(`There is no chicken with id ${chickenId}`);
            return false;
        }
        const chicken = this._chickens[index];
        if (chicken) {
            chicken.name = name;
            chicken.color = color;
            chicken.bornOnDate = bornOnDate;
            chicken.molting = molting;
            chicken.setAgeInYears();
        }
        return true;
    }
    findIndexById(chickenId) {
        for (let i = 0; i < this._count; i++) {
            if (this._chickens[i]?.id === chickenId) {
                return i;
            }
        }
        return -1;
    }
    toArray() {
        return this._chickens.slice(0, this._count).filter((c) => c !== null);
    }
    resetIteration() {
        this._currentIndex = 0;
    }
    next() {
        if (this._currentIndex >= this._count) {
            return null;
        }
        const chicken = this._chickens[this._currentIndex];
        this._currentIndex++;
        return chicken;
    }
    get chickenCoopId() { return this._chickenCoopId; }
    set chickenCoopId(value) { this._chickenCoopId = value; }
    get count() { return this._count; }
    toString() {
        let text = `ChickenCoop{id=${this._chickenCoopId}, chickens=${this._count}`;
        for (let i = 0; i < this._count; i++) {
            text += `\n  ${this._chickens[i]?.toString()}`;
        }
        return text + "}";
    }
}
