export class Egg {
    constructor(private id: number, private size: string) {}

    getId(): number { return this.id; }
    setId(id: number): void { this.id = id; }
    getSize(): string { return this.size; }
    setSize(size: string): void { this.size = size; }
    toString(): string { return `Egg{id=${this.id}, size=${this.size}}`; }
}
