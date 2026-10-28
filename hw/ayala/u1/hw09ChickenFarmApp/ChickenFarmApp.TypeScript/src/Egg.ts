/**
 * @author Enrique Ayala, LogicLords,@ESPE
 */
export class Egg {
    private _id: number;
    private _size: string; // 'L', 'M', etc.

    constructor(id: number, size: string) {
        this._id = id;
        this._size = size;
    }

    public get id(): number {
        return this._id;
    }

    public set id(value: number) {
        this._id = value;
    }

    public get size(): string {
        return this._size;
    }

    public set size(value: string) {
        this._size = value;
    }

    public toString(): string {
        return `Egg{id=${this._id}, size='${this._size}'}`;
    }
}