import { Food } from "./Food";

/**
 * Clase abstracta FarmAnimal traducida de ec.edu.espe.model.FarmAnimal
 * @author Daniel Sangoquiza, SaborCode
 */
export abstract class FarmAnimal {
    private id: number;
    private breed: string;
    private bornOnDate: Date;

    /**
     * Feed the animals with an amount of food
     * @param food the type of food per type of animal
     * @param amountOfFood amount of food
     */
    public abstract feed(food: Food, amountOfFood: number): void;

    constructor(id: number, breed: string, bornOnDate: Date) {
        this.id = id;
        this.breed = breed;
        this.bornOnDate = bornOnDate;
    }

    public toString(): string {
        const dateObj = this.bornOnDate instanceof Date ? this.bornOnDate : new Date(this.bornOnDate);
        const day = dateObj.getDate() + 1;
        const month = dateObj.getMonth();
        const year = dateObj.getFullYear();

        return `id = ${this.id}, breed = ${this.breed}, bornOnDate = ${day}/${month}/${year}, `;
    }

    public getAgeInMonths(): number {
        return 0;
    }

    // --- Getters y Setters ---
    public getId(): number {
        return this.id;
    }

    public setId(id: number): void {
        this.id = id;
    }

    public getBreed(): string {
        return this.breed;
    }

    public setBreed(breed: string): void {
        this.breed = breed;
    }

    public getBornOnDate(): Date {
        return this.bornOnDate;
    }

    public setBornOnDate(bornOnDate: Date): void {
        this.bornOnDate = bornOnDate;
    }
}