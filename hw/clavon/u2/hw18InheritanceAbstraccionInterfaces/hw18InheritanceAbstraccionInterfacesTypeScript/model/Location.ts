/**
 * @author David Clavón, Infinite loops, @ESPE
 */
export class Location {
    private id: number;
    private description: string;

    constructor(id: number, description: string) {
        this.id = id;
        this.description = description;
    }

    public getId(): number {
        return this.id;
    }

    public setId(id: number): void {
        this.id = id;
    }

    public getDescription(): string {
        return this.description;
    }

    public setDescription(description: string): void {
        this.description = description;
    }

    public toString(): string {
        return `Location{id=${this.id}, description='${this.description}'}`;
    }
}