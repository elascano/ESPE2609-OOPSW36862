"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Pencil = void 0;
class Pencil {
    id;
    brand;
    color;
    hardness;
    constructor(id, brand, color, hardness) {
        if (!Number.isSafeInteger(id) || id <= 0) {
            throw new Error('ID must be a positive whole number.');
        }
        if (!brand.trim() || !color.trim() || !hardness.trim()) {
            throw new Error('All attributes are required.');
        }
        this.id = id;
        this.brand = brand.trim();
        this.color = color.trim();
        this.hardness = hardness.trim();
    }
}
exports.Pencil = Pencil;
