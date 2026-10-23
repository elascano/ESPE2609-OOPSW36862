/**
 * @author Enrique Ayala, LogicLords,@ESPE
 */
export class Egg {
    _id;
    _size; // 'L', 'M', etc.
    constructor(id, size) {
        this._id = id;
        this._size = size;
    }
    get id() {
        return this._id;
    }
    set id(value) {
        this._id = value;
    }
    get size() {
        return this._size;
    }
    set size(value) {
        this._size = value;
    }
    toString() {
        return `Egg{id=${this._id}, size='${this._size}'}`;
    }
}
