import { Food } from "./Food";

export abstract class FarmAnimal {
    constructor(
        protected id: number,
        protected breed: string,
        protected bornOnDate: Date,
        protected isMeat: boolean
    ) {}

    public abstract feed(food: Food, amount: number): void;

    public getAgeInMonths(): number {
        const today = new Date();
        return (today.getFullYear() - this.bornOnDate.getFullYear()) * 12
            + today.getMonth() - this.bornOnDate.getMonth();
    }

    public toString(): string {
        const day = this.bornOnDate.getDate();
        const month = this.bornOnDate.getMonth() + 1;
        const year = this.bornOnDate.getFullYear();
        return `FarmAnimal{id=${this.id}, breed=${this.breed}, is meat=${this.isMeat}, bornOnDate=${day} / ${month} / ${year}}, `;
    }
}
