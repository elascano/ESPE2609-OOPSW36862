"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Cellphone = void 0;
class Cellphone {
    constructor(id, brand, model, price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }
    getId() {
        return this.id;
    }
    getBrand() {
        return this.brand;
    }
    getModel() {
        return this.model;
    }
    getPrice() {
        return this.price;
    }
}
exports.Cellphone = Cellphone;
