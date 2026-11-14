import { FarmAnimal } from './farm_animal';

export class Pig extends FarmAnimal {
    constructor(id: number, bred: string, bornOnDate: Date) {
        super(id, bred, bornOnDate);
    }

    public toString(): string {
        const superStr = super.toString().replace("}", "");
        return `Pig{${superStr}}`;
    }
}