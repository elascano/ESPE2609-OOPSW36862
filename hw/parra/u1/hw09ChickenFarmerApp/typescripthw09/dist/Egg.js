"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Egg = void 0;
class Egg {
    id;
    size;
    constructor(id, size) {
        this.id = id;
        this.size = size;
    }
    toString() {
        return `Egg{id=${this.id}, size=${this.size}}`;
    }
}
exports.Egg = Egg;
