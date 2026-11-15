export class Location {
    constructor(private description: string) {}

    public toString(): string {
        return `Location{description=${this.description}}`;
    }
}
