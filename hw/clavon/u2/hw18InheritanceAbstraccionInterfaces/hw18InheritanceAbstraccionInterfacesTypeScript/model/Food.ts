/**
 * @author David Clavón, Infinite loops, @ESPE
 */
export class Food {
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
        return this.description;
    }
}