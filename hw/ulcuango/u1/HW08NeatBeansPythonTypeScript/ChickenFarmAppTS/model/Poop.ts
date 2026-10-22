/**
 * @author Josselyn Ulcuango, Codelex, @ESPE
 */
export class Poop {
    private id: number;
    private color: string;
    private consistency: string;

    constructor(id: number, color: string, consistency: string) {
        this.id = id;
        this.color = color;
        this.consistency = consistency;
    }

    public toString(): string {
        return `Poop{id=${this.id}, color=${this.color}, consistency=${this.consistency}}`;
    }

    public getId(): number { return this.id; }
    public setId(id: number): void { this.id = id; }
    public getColor(): string { return this.color; }
    public setColor(color: string): void { this.color = color; }
    public getConsistency(): string { return this.consistency; }
    public setConsistency(consistency: string): void { this.consistency = consistency; }
}