/** Equivalente de Egg.java. */
export class Egg {
    constructor(private id: number, private size: string) {}

    getId(): number { return this.id; }
    setId(value: number): void { this.id = value; }
    getSize(): string { return this.size; }
    setSize(value: string): void { this.size = value; }

    toString(): string {
        return `Egg{id=${this.id}, size=${this.size}}`;
    }
}
