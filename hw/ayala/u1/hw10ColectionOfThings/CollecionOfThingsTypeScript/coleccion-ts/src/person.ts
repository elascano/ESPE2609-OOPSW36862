/**
 * @author Enrique Ayala, Byte and LogicalLords, @ESPE
 */

export class Person {
  private _id: number;
  private _name: string;
  private _salary: number;

  constructor(id: number, name: string, salary: number) {
    this._id = id;
    this._name = name;
    this._salary = salary;
  }

  get id(): number {
    return this._id;
  }

  set id(id: number) {
    this._id = id;
  }

  get name(): string {
    return this._name;
  }

  set name(name: string) {
    this._name = name;
  }

  get salary(): number {
    return this._salary;
  }

  set salary(salary: number) {
    this._salary = salary;
  }

  toString(): string {
    return `id=${this._id}, name=${this._name}, \tsalary=${this._salary}`;
  }
}