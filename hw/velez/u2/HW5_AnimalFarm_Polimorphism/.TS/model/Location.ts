export class Location {
    private description: string;

    constructor(description: string) {
        this.description = description;
    }

    public getDescription(): string {
        return this.description;
    }

    public setDescription(description: string): void {
        this.description = description;
    }

    public toString(): string {
        return `Location{description=${this.description}}`;
    }
}