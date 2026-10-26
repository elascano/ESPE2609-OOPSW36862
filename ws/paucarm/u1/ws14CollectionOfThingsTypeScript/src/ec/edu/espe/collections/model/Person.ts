/**
 * @author Paucar Matias, ByteWorks, @ESPE
 */
export class Person {
    private id: number;
    private name: string;
    private salary: number;

    constructor(id: number, name: string, salary: number) {
        this.id = id;
        this.name = name;
        this.salary = salary;
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

    public toString(): string {
        return "id=" + this.id + ", name=" + this.name + ", \tsalary=" + Person.formatFloat(this.salary);
    }

    // En Java un float entero se imprime como 1004.0; en JS saldría 1004
    private static formatFloat(value: number): string {
        return Number.isInteger(value) ? value.toFixed(1) : String(value);
    }
}
