export class Egg {
    constructor(public idEgg: number, public color: string) {}

    public toString(): string {
        return `Egg(id=${this.idEgg}, color='${this.color}')`;
    }
}
