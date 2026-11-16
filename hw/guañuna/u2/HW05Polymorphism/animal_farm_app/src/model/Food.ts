export class Food {
    private description: string;

    constructor(description: string) {
        this.description = description;
    }

    public getDescription(): string { return this.description; }
    public setDescription(v: string): void { this.description = v; }

    public toString(): string {
        return this.description;
    }
}