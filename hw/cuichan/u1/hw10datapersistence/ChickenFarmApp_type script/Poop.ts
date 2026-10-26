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

  public set id(id: number) {
    this._id = id;
  }

  public get color(): string {
    return this._color;
  }

  public set color(color: string) {
    this._color = color;
  }

  public get consistency(): string {
    return this._consistency;
  }

  public set consistency(consistency: string) {
    this._consistency = consistency;
  }

  public toString(): string {
    return `Poop{id=${this._id}, color=${this._color}, consistency=${this._consistency}}`;
  }
}