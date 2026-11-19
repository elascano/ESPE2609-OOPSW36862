import { FarmAnimal } from './FarmAnimal';

export class Pig extends FarmAnimal {
    constructor(id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
    }

    public toString(): string {
        return `Pig{${super.toString()}}`;
    }
}