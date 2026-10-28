export class Egg {
    constructor(public id: number, public size: string) {}

    toString(): string {
        return `Egg{id=${this.id}, size=${this.size}}`;
    }
}