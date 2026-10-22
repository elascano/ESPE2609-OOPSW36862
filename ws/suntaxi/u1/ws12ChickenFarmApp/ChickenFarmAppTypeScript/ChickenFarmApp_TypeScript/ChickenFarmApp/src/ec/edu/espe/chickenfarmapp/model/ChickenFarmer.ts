export class ChickenFarmer {
    constructor(public idFarmer: number, public name: string, public level: string) {}

    public toString(): string {
        return `ChickenFarmer(id=${this.idFarmer}, name='${this.name}', level='${this.level}')`;
    }
}
