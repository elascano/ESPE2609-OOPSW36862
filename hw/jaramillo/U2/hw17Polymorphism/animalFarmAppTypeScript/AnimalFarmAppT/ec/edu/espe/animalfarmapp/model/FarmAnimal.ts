import { Food } from './Food';

export abstract class FarmAnimal {
    private id: number;
    private breed: string;
    private bornOnDate: Date;

    constructor(id: number, breed: string, bornOnDate: Date) {
        this.id = id;
        this.breed = breed;
        this.bornOnDate = bornOnDate;
    }

    public abstract feed(food: Food, amountOfFood: number): void;

    public toString(): string {
        return `id = ${this.id}, breed = ${this.breed}, bornOnDate = ${this.bornOnDate.getDate()}/${this.bornOnDate.getMonth() + 1}/${this.bornOnDate.getFullYear()}, `;
    }

    public getAgeInMonths(): number {
        return 0;
    }

    public getId(): number { return this.id; }
    public getBreed(): string { return this.breed; }
    public getBornOnDate(): Date { return this.bornOnDate; }
}