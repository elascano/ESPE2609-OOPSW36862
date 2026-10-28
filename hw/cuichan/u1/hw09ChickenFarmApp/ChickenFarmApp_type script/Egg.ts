export class Egg {
  private _id: number;
  private _size: string;

  constructor(id: number, size: string) {
    this._id = id;
    this._size = size;
  }

  public get id(): number {
    return this._id;
  }

  public set id(id: number) {
    this._id = id;
  }

  public get size(): string {
    return this._size;
  }

  public set size(size: string) {
    this._size = size;
  }

  public toString(): string {
    return `Egg{id=${this.id}, size=${this.size}}`;
  }
}