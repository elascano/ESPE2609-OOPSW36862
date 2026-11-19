import { FarmAnimal } from './FarmAnimal';
import { Food } from './Food';

export class Pig extends FarmAnimal {
    private weight: number;
    private isMeat: boolean;

    constructor(id: number, breed: string, bornOn: Date, weight: number, isMeat: boolean) {
        super(id, breed, bornOn);
        this.weight = weight;
        this.isMeat = isMeat;
    }

    public feed(food: Food, amountOfFood: number): void {
        console.log(`Pig ${this.getId()} is eating ${amountOfFood} units of ${food.getDescription()}`);
    }

    public toString(): string {
        return `Pig{${super.toString()} weight=${this.weight}, isMeat=${this.isMeat}}`;
    }
}