"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Poop = void 0;
class Poop {
    _id;
    _color;
    _consistency;
    constructor(id, color, consistency) {
        this._id = id;
        this._color = color;
        this._consistency = consistency;
    }
    get id() {
        return this._id;
    }
    set id(id) {
        this._id = id;
    }
    get color() {
        return this._color;
    }
    set color(color) {
        this._color = color;
    }
    get consistency() {
        return this._consistency;
    }
    set consistency(consistency) {
        this._consistency = consistency;
    }
    toString() {
        return `Poop{id=${this._id}, color=${this._color}, consistency=${this._consistency}}`;
    }
}
exports.Poop = Poop;
