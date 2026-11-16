import { FarmAnimal } from './FarmAnimal';
import { GrazeAnimalI } from './GrazeAnimalI';
import { Food } from './Food';
import { Location } from './Location';

export class Pig extends FarmAnimal implements GrazeAnimalI {
    private weightInKg: number;
    private isHungry: boolean;

    constructor(weightInKg: number, isHungry: boolean, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.weightInKg = weightInKg;
        this.isHungry = isHungry;
    }

    public feed(food: Food, amount: number): void {
        console.log(`Feeding a pig with ---> ${amount} of ${food.toString()}`);
    }

    public senToGraze(location: Location, date: Date): void {
        console.log(`Sending the pig to ---> ${location.toString()} at ${date.toString()}`);
    }

    // Sobrecarga para eat
    public eat(): void;
    public eat(kgOfFood: number): void;
    public eat(kgOfFood?: number): void {
        if (kgOfFood === undefined) {
            console.log("Pig is eating slop. Just maintaining weight.");
        } else {
            this.weightInKg += (kgOfFood * 0.8);
            this.isHungry = false;
            console.log(`Pig ate ${kgOfFood}kg of food. New weight: ${this.weightInKg.toFixed(2)} kg. Hungry? ${this.isHungry}`);
        }
    }

    public toString(): string {
        return `Pig{${super.toString()}weightInKg=${this.weightInKg}, isHungry=${this.isHungry}}`;
    }
}