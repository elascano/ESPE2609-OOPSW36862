export class Poop {
    constructor(private id: number, private color: string, private consistency: string) {}

    getId(): number { return this.id; }
    setId(id: number): void { this.id = id; }
    getColor(): string { return this.color; }
    setColor(color: string): void { this.color = color; }
    getConsistency(): string { return this.consistency; }
    setConsistency(consistency: string): void { this.consistency = consistency; }
    toString(): string {
        return `Poop{id=${this.id}, color=${this.color}, consistency=${this.consistency}}`;
    }
}
