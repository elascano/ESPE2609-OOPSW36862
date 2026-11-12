import { FarmAnimal } from "./FarmAnimal";

/**
 * @author David Clavón, Infinite Loops, @ESPE
 */
export class Pig extends FarmAnimal {
    private weight: number;
    private isForMeat: boolean;

    constructor(
        weight: number,
        isForMeat: boolean,
        id: number,
        breed: string,
        bornOnDate: Date
    ) {
        super(id, breed, bornOnDate);
        this.weight = weight;
        this.isForMeat = isForMeat;
    }

    public override toString(): string {
        return `Pig{${super.toString()}weight=${this.weight}, isForMeat=${this.isForMeat}}`;
    }

    public getWeight(): number {
        return this.weight;
    }

    public setWeight(weight: number): void {
        this.weight = weight;
    }

    public isIsForMeat(): boolean {
        return this.isForMeat;
    }

    public setIsForMeat(isForMeat: boolean): void {
        this.isForMeat = isForMeat;
    }
}