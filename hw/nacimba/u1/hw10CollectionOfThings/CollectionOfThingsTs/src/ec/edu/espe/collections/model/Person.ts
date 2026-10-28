/** Formatea un float32 como lo hace Java (ej. 2851.57, 1884.0). */
export function formatFloat(value: number): string {
  const f = Math.fround(value);
  let text = String(f);
  for (let precision = 1; precision <= 9; precision++) {
    text = f.toPrecision(precision);
    if (Math.fround(parseFloat(text)) === f) break;
  }
  const n = parseFloat(text);
  return Number.isInteger(n) ? `${n}.0` : String(n);
}

export class Person {
  private _id: number;
  private _name: string;
  private _salary: number;

  constructor(id: number, name: string, salary: number) {
    this._id = id;
    this._name = name;
    this._salary = Math.fround(salary); // `float` de 32 bits como en Java
  }

  toString(): string {
    return `Person{id=${this._id}, name=${this._name}, salary=${formatFloat(this._salary)}}`;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get salary(): number {
    return this._salary;
  }

  set salary(value: number) {
    this._salary = Math.fround(value);
  }
}
