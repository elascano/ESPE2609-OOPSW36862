/** Gallinero de 10 espacios, equivalente a ChickenCoop.java. */
import { Chicken } from "./Chicken";

export class ChickenCoop {
    private static readonly CAPACITY = 10;
    private chickenCoopId: number;
    private chickens: (Chicken | null)[];
    private currentIndex: number;

    constructor(chickenCoopId: number) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = Array(ChickenCoop.CAPACITY).fill(null) as (Chicken | null)[];
        this.currentIndex = 0;
    }

    add(chicken: Chicken): boolean {
        for (const existing of this.chickens) {
            if (existing !== null && existing.getId() === chicken.getId()) {
                console.log(`There is already a chicken with id ${chicken.getId()}.`);
                return false;
            }
        }

        for (let i = 0; i < this.chickens.length; i++) {
            if (this.chickens[i] === null) {
                this.chickens[i] = chicken;
                console.log(`Chicken added: ${chicken.getName()}`);
                return true;
            }
        }

        console.log(`The coop is full (maximum ${this.chickens.length}).`);
        return false;
    }

    remove(chickenId: number): boolean {
        for (let i = 0; i < this.chickens.length; i++) {
            const chicken = this.chickens[i];
            if (chicken !== null && chicken.getId() === chickenId) {
                const name = chicken.getName();
                for (let j = i; j < this.chickens.length - 1; j++) {
                    this.chickens[j] = this.chickens[j + 1];
                }
                this.chickens[this.chickens.length - 1] = null;
                console.log(`Chicken removed: ${name}`);
                return true;
            }
        }
        console.log(`No chicken found with id ${chickenId}.`);
        return false;
    }

    resetIteration(): void {
        this.currentIndex = 0;
        console.log("Iteration reset.");
    }

    next(): void {
        const chicken = this.chickens[this.currentIndex];
        if (this.currentIndex < this.chickens.length && chicken !== null && chicken !== undefined) {
            console.log(chicken.toString());
            this.currentIndex++;
        } else {
            console.log("No more chickens. Reset the iteration to start over.");
        }
    }

    getChickenCoopId(): number { return this.chickenCoopId; }
    setChickenCoopId(value: number): void { this.chickenCoopId = value; }
    getChickens(): (Chicken | null)[] { return this.chickens; }
    setChickens(chickens: (Chicken | null)[]): void {
        if (chickens.length !== ChickenCoop.CAPACITY) {
            throw new Error("The coop must have exactly 10 positions");
        }
        this.chickens = chickens;
    }
    getCurrentIndex(): number { return this.currentIndex; }
    setCurrentIndex(value: number): void { this.currentIndex = value; }

    toString(): string {
        const contents = this.chickens.map(chicken => chicken === null ? "null" : chicken.toString()).join(", ");
        return `ChickenCoop{chickenCoopId=${this.chickenCoopId}, ` +
            `chickens=[${contents}], currentIndex=${this.currentIndex}}`;
    }
}
