"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Location = void 0;
class Location {
    constructor(description) {
        this.description = description;
    }
    toString() {
        return `Location{description=${this.description}}`;
    }
    getDescription() {
        return this.description;
    }
    setDescription(description) {
        this.description = description;
    }
}
exports.Location = Location;
