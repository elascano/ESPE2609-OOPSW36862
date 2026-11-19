import { FarmAnimal } from './FarmAnimal';
import { Food } from './Food';

export class Sheep extends FarmAnimal {
    private lastSheering: Date;
    private isProducingWool: boolean;
    private isMeat: boolean;

    constructor(lastSheering: Date, isProducingWool: boolean, isMeat: boolean, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.lastSheering = lastSheering;
        this.isProducingWool = isProducingWool;
        this.isMeat = isMeat;
    }

    public feed(food: Food, amountOfFood: number): void {
        console.log(`Sheep ${this.getId()} is eating ${amountOfFood} units of ${food.getDescription()}`);
    }

    public toString(): string {
        return `Sheep{${super.toString()} lastSheering=${this.lastSheering.toISOString().split('T')[0]}, isProducingWool=${this.isProducingWool}, isMeat=${this.isMeat}}`;
    }
}