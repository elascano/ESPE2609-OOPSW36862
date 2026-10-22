import { Chicken } from "./Models";

export class ChickenCoop {
    private chickenCoopId: number;
    private chickens: (Chicken | null)[];
    private currentIndex: number;

    constructor(chickenCoopId: number = 0, chickens?: (Chicken | null)[]) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = chickens ?? new Array<Chicken | null>(10).fill(null);
        this.currentIndex = 0;
    }

    /** Adds a chicken to the first available slot (null) in the array. */
    add(chicken: Chicken): void {
        for (let i = 0; i < this.chickens.length; i++) {
            if (this.chickens[i] === null || this.chickens[i] === undefined) {
                this.chickens[i] = chicken;
                return;
            }
        }
        console.log("The chicken coop is full. Cannot add more chickens.");
    }

    /** Removes a chicken by matching its ID. */
    remove(chickenId: number): void {
        for (let i = 0; i < this.chickens.length; i++) {
            const chicken = this.chickens[i];
            if (chicken && chicken.id === chickenId) {
                this.chickens[i] = null;
                return;
            }
        }
        console.log(`Chicken with ID ${chickenId} was not found.`);
    }

    /** Resets the iteration index back to 0. */
    resetIteration(): void {
        this.currentIndex = 0;
    }

    /** Returns the current chicken and moves the index forward. */
    next(): Chicken | null {
        while (this.currentIndex < this.chickens.length) {
            const current = this.chickens[this.currentIndex];
            this.currentIndex++;
            if (current) {
                return current;
            }
        }
        return null;
    }

    toString(): string {
        return `ChickenCoop{chickenCoopId=${this.chickenCoopId}, chickens=[${this.chickens.join(", ")}]}`;
    }

    getChickenCoopId(): number { return this.chickenCoopId; }
    setChickenCoopId(id: number): void { this.chickenCoopId = id; }
    getChickens(): (Chicken | null)[] { return this.chickens; }
    setChickens(chickens: (Chicken | null)[]): void { this.chickens = chickens; }
}