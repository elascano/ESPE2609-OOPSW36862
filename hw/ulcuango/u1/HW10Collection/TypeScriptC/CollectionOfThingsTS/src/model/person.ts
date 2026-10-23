export class Person {
    private id: number;
    private name: string;
    private salary: number;

    constructor(id: number, name: string, salary: number) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public toString(): string {
        return `Person{id=${this.getId()}, name=${this.getName()}, salary=${this.getSalary()}}`;
    }

    public getId(): number {
        return this.id;
    }

    public setId(id: number): void {
        this.id = id;
    }

    public getName(): string {
        return this.name;
    }

    public setName(name: string): void {
        this.name = name;
    }

    public getSalary(): number {
        return this.salary;
    }

    public setSalary(salary: number): void {
        this.salary = salary;
    }
}