
export class Location {
    private description: string;

    constructor(description: string) {
        this.description = description;
    }

    toString(): string {
        return `Location{description=${this.description}}`;
    }

    getDescription(): string {
        return this.description;
    }

    setDescription(description: string): void {
        this.description = description;
    }
}
