
export class Food {
    private description: string;

    constructor(description: string) {
        this.description = description;
    }

    toString(): string {
        return this.description;
    }

    getDescription(): string {
        return this.description;
    }

    setDescription(description: string): void {
        this.description = description;
    }
}
