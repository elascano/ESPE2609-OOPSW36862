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

    abstract feed(food: Food, amount: number): void;

    getAgeInMonths(): number {
        return 0;
    }

    toString(): string {
        const date = this.bornOnDate;
        return `FarmAnimal{id=${this.id}, breed=${this.breed}, is meat=${this.isMeat}, bornOnDate=${date.getDate()} / ${date.getMonth() + 1} / ${date.getFullYear()}}, `;
    }

    getId(): number { return this.id; }
    setId(id: number): void { this.id = id; }

    getBreed(): string { return this.breed; }
    setBreed(breed: string): void { this.breed = breed; }

    getBornOnDate(): Date { return this.bornOnDate; }
    setBornOnDate(date: Date): void { this.bornOnDate = date; }

    isIsMeat(): boolean { return this.isMeat; }
    setIsMeat(isMeat: boolean): void { this.isMeat = isMeat; }
}
