/** Mask entity: id (mandatory) + three more attributes. */
export class Mask {
  constructor(
    public readonly id: number,
    public name: string,
    public material: string,
    public price: number
  ) {}

  /** Rebuilds a Mask from a plain object read from JSON. */
  static fromJSON(obj: any): Mask {
    return new Mask(Number(obj.id), String(obj.name), String(obj.material), Number(obj.price));
  }

  toString(): string {
    return `Mask #${this.id} | ${this.name} | ${this.material} | $${this.price.toFixed(2)}`;
  }
}
