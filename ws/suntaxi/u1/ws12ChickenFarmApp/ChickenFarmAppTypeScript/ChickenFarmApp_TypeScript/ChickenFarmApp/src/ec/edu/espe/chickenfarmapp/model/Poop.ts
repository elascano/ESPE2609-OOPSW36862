export class Poop {
    constructor(public idPoop: number, public weight: number) {}

    public toString(): string {
        return `Poop(id=${this.idPoop}, weight=${this.weight}g)`;
    }
}
