export class Poop {
    private _id: number;
    private _color: string;
    private _consistency: string;

    constructor(id: number, color: string, consistency: string) {
        this._id = id;
        this._color = color;
        this._consistency = consistency;
    }

    public get id(): number {
        return this._id;
    }

    public set id(value: number) {
        this._id = value;
    }

    public get color(): string {
        return this._color;
    }

    public set color(value: string) {
        this._color = value;
    }

    public get consistency(): string {
        return this._consistency;
    }

    public set consistency(value: string) {
        this._consistency = value;
    }

    public toString(): string {
        return `Poop{id=${this._id}, color='${this._color}', consistency='${this._consistency}'}`;
    }
}