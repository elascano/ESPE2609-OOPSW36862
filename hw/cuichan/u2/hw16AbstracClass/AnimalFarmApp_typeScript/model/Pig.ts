import { FarmAnimal } from './FarmAnimal';

export class Pig extends FarmAnimal {
    private weight: number;
    private isMeat: boolean;

    constructor(id: number, breed: string, bornOn: Date, weight: number, isMeat: boolean) {
        super(id, breed, bornOn);
        this.weight = weight;
        this.isMeat = isMeat;
    }

    public feed(): void {
        console.log(`Pig (ID: ${this.id}) is eating feed.`);
    }

    public toString(): string {
        return `Pig{${super.toString()}, weight=${this.weight}, isMeat=${this.isMeat}}`;
    }
}