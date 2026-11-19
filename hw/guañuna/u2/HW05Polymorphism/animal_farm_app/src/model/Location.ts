export class Location {
    private id: number;
    private description: string;

    constructor(id: number, description: string) {
        this.id = id;
        this.description = description;
    }

    public getId(): number { return this.id; }
    public setId(v: number): void { this.id = v; }

    public getDescription(): string { return this.description; }
    public setDescription(v: string): void { this.description = v; }

    public toString(): string {
        return `Location{id=${this.id}, description=${this.description}}`;
    }
}