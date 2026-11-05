/** Equivalente de Poop.java. */
export class Poop {
    constructor(private id: number, private color: string, private consistency: string) {}

    getId(): number { return this.id; }
    setId(value: number): void { this.id = value; }
    getColor(): string { return this.color; }
    setColor(value: string): void { this.color = value; }
    getConsistency(): string { return this.consistency; }
    setConsistency(value: string): void { this.consistency = value; }

    toString(): string {
        return `Poop{id=${this.id}, color=${this.color}, consistency=${this.consistency}}`;
    }
}
