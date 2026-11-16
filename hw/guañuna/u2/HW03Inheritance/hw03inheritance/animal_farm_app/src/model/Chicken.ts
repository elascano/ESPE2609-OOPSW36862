import { FarmAnimal } from './FarmAnimal';

export class Chicken extends FarmAnimal {
    private isMolting: boolean;

    constructor(isMolting: boolean, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.isMolting = isMolting;
    }

    public getIsMolting(): boolean { return this.isMolting; }
    public setIsMolting(v: boolean): void { this.isMolting = v; }

    public toString(): string {
        return `Chicken{isMolting=${this.isMolting}, ${super.toString()}}`;
    }
}