/**
 * @author Josselyn Ulcuango, Codelex, @ESPE
 */
export class Egg {
    private id: number;
    private size: string;

    constructor(id: number, size: string) {
        this.id = id;
        this.size = size;
    }

    public toString(): string {
        return `Egg{id=${this.id}, size=${this.size}}`;
    }

    public getId(): number { return this.id; }
    public setId(id: number): void { this.id = id; }
    public getSize(): string { return this.size; }
    public setSize(size: string): void { this.size = size; }
}