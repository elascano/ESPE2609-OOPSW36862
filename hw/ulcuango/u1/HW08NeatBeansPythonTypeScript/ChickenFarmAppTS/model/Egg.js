"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Egg = void 0;
/**
 * @author Josselyn Ulcuango, Codelex, @ESPE
 */
class Egg {
    constructor(id, size) {
        this.id = id;
        this.size = size;
    }
    toString() {
        return `Egg{id=${this.id}, size=${this.size}}`;
    }
    getId() { return this.id; }
    setId(id) { this.id = id; }
    getSize() { return this.size; }
    setSize(size) { this.size = size; }
}
exports.Egg = Egg;
