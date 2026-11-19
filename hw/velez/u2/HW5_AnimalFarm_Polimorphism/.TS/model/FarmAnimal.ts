import { Food } from "./Food";

export abstract class FarmAnimal {
    private id: number;
    private breed: string;
    private bornOnDate: Date;
    private isMeat: boolean;

    constructor(id: number, breed: string, bornOnDate: Date, isMeat: boolean) {
        this.id = id;
        this.breed = breed;
        this.bornOnDate = bornOnDate;
        this.isMeat = isMeat;
    }

    public abstract feed(food: Food, amount: number): void;

    public getAgeInMonths(): number {
        return 0;
    }

    public toString(): string {
        return `FarmAnimal{id=${this.id}, breed=${this.breed}, is meat=${this.isMeat}, bornOnDate=${this.bornOnDate.toDateString()}}`;
    }
}