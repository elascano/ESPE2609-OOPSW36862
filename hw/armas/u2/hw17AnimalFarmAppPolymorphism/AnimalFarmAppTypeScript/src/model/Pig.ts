//Demian Armas, Logical Lords, @ESPE

import { FarmAnimal } from "./FarmAnimal.ts";

export class Pig extends FarmAnimal {
    weight: number;
    isForMeat: boolean;

    constructor(weight: number, isForMeat: boolean, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.weight = weight;
        this.isForMeat = isForMeat;
    }

    override toString(): string {
        return `Pig{${super.toString()}, weight=${this.weight}, isForMeat=${this.isForMeat}}`;
    }
}
