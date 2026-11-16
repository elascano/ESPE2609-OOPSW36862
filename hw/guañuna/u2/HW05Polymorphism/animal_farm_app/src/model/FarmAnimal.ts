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

    public abstract feed(food: Food, amount: number): void;

    public getAgeInMonths(): number {
        if (!this.bornOnDate) return 0;
        const today = new Date();
        let months = (today.getFullYear() - this.bornOnDate.getFullYear()) * 12;
        months += today.getMonth() - this.bornOnDate.getMonth();
        return months;
    }

    public getId(): number { return this.id; }
    public setId(id: number): void { this.id = id; }

    public getBreed(): string { return this.breed; }
    public setBreed(breed: string): void { this.breed = breed; }

    public getBornOnDate(): Date { return this.bornOnDate; }
    public setBornOnDate(bornOnDate: Date): void { this.bornOnDate = bornOnDate; }

    public toString(): string {
        return `id = ${this.id}, breed = ${this.breed}, bornOnDate = ${this.bornOnDate.getDate()}/${this.bornOnDate.getMonth() + 1}/${this.bornOnDate.getFullYear()}, `;
    }
}