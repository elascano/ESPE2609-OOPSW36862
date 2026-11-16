import { FarmAnimal } from './FarmAnimal';

export class Pig extends FarmAnimal {
    private weightInKg: number;
    private isHungry: boolean;

    constructor(weightInKg: number, isHungry: boolean, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.weightInKg = weightInKg;
        this.isHungry = isHungry;
    }

    public eat(): void {
        console.log("pig is eating slop()");
    }

    public getWeightInKg(): number { return this.weightInKg; }
    public setWeightInKg(v: number): void { this.weightInKg = v; }

    public getIsHungry(): boolean { return this.isHungry; }
    public setIsHungry(v: boolean): void { this.isHungry = v; }

    public toString(): string {
        return `Pig{${super.toString()}weightInKg=${this.weightInKg}, isHungry=${this.isHungry}}`;
    }
}