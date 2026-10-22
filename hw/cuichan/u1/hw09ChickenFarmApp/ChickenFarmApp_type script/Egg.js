"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Egg = void 0;
class Egg {
    _id;
    _size;
    constructor(id, size) {
        this._id = id;
        this._size = size;
    }
    get id() {
        return this._id;
    }
    set id(id) {
        this._id = id;
    }
    get size() {
        return this._size;
    }
    set size(size) {
        this._size = size;
    }
    toString() {
        return `Egg{id=${this.id}, size=${this.size}}`;
    }
}
exports.Egg = Egg;
