/**
 * Clase Food traducida de ec.edu.espe.model.Food
 * @author Daniel Sangoquiza, SaborCode
 */
export class Food {
    private description: string;

    constructor(description: string) {
        this.description = description;
    }

    public toString(): string {
        return this.description;
    }

    public getDescription(): string {
        return this.description;
    }

    public setDescription(description: string): void {
        this.description = description;
    }
}