export class Person {
  constructor(
    public id: number,
    public name: string,
    public salary: number
  ) {}

  toString(): string {
    return `Person{id=${this.id}, name=${this.name}, salary=${this.salary}}`;
  }
}
