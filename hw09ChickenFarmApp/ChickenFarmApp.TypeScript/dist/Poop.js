/**
 * @author Enrique Ayala, LogicLords,@ESPE
 */
export class Poop {
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
    set id(value) {
        this._id = value;
    }
    get color() {
        return this._color;
    }
    set color(value) {
        this._color = value;
    }
    get consistency() {
        return this._consistency;
    }
    set consistency(value) {
        this._consistency = value;
    }
    toString() {
        return `Poop{id=${this._id}, color='${this._color}', consistency='${this._consistency}'}`;
    }
}
