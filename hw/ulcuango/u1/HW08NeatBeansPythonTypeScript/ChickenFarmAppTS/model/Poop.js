"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Poop = void 0;
/**
 * @author Josselyn Ulcuango, Codelex, @ESPE
 */
class Poop {
    constructor(id, color, consistency) {
        this.id = id;
        this.color = color;
        this.consistency = consistency;
    }
    toString() {
        return `Poop{id=${this.id}, color=${this.color}, consistency=${this.consistency}}`;
    }
    getId() { return this.id; }
    setId(id) { this.id = id; }
    getColor() { return this.color; }
    setColor(color) { this.color = color; }
    getConsistency() { return this.consistency; }
    setConsistency(consistency) { this.consistency = consistency; }
}
exports.Poop = Poop;
