"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ChickenCoop = void 0;
class ChickenCoop {
    _chickenCoopId;
    _chickens;
    _currentIndex;
    constructor(chickenCoopId, chickens) {
        this._chickenCoopId = chickenCoopId;
        this._chickens = chickens ? chickens : new Array(10).fill(null);
        this._currentIndex = 0;
    }
    generateUniqueId() {
        let generatedId;
        let isDuplicate;
        do {
            generatedId = Math.floor(Math.random() * 100) + 1;
            isDuplicate = false;
            for (const c of this._chickens) {
                if (c !== null && c !== undefined && c.id === generatedId) {
                    isDuplicate = true;
                    break;
                }
            }
        } while (isDuplicate);
        return generatedId;
    }
    add(chicken) {
        if (!chicken) {
            return false;
        }
        for (let i = 0; i < this._chickens.length; i++) {
            if (this._chickens[i] === null || this._chickens[i] === undefined) {
                this._chickens[i] = chicken;
                return true;
            }
        }
        return false;
    }
    remove(chickenId) {
        for (let i = 0; i < this._chickens.length; i++) {
            const c = this._chickens[i];
            if (c !== null && c !== undefined && c.id === chickenId) {
                this._chickens[i] = null;
                return true;
            }
        }
        return false;
    }
    resetIteration() {
        this._currentIndex = 0;
    }
    next() {
        while (this._currentIndex < this._chickens.length) {
            const current = this._chickens[this._currentIndex];
            this._currentIndex++;
            if (current !== null && current !== undefined) {
                return current;
            }
        }
        return null;
    }
    getCurrentPosition() {
        return this._currentIndex;
    }
    getCount() {
        let count = 0;
        for (const c of this._chickens) {
            if (c !== null && c !== undefined) {
                count++;
            }
        }
        return count;
    }
    get chickenCoopId() {
        return this._chickenCoopId;
    }
    set chickenCoopId(chickenCoopId) {
        this._chickenCoopId = chickenCoopId;
    }
    get chickens() {
        return this._chickens;
    }
    set chickens(chickens) {
        this._chickens = chickens;
    }
    toString() {
        return `ChickenCoop{chickenCoopId=${this._chickenCoopId}, chickens=${JSON.stringify(this._chickens)}}`;
    }
}
exports.ChickenCoop = ChickenCoop;
