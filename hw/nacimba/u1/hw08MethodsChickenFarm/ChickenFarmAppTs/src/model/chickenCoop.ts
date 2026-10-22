import { Chicken } from "./chicken";

export class ChickenCoop {
    static readonly CAPACITY = 10;

    chickenCoopId: number;
    chickens: (Chicken | null)[];
    chickenCount: number;
    private iteratorIndex: number;
    private nextId: number;

    constructor(chickenCoopId: number) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = new Array<Chicken | null>(ChickenCoop.CAPACITY).fill(null);
        this.chickenCount = 0;
        this.iteratorIndex = 0;
        this.nextId = 1;
    }

    /** Generates a unique, auto-incremental id for a new chicken. */
    generateId(): number {
        return this.nextId++;
    }

    /** Adds a chicken in the first free slot. Returns true if added. */
    add(chicken: Chicken | null): boolean {
        if (chicken === null) {
            console.log("Cannot add a null chicken.");
            return false;
        }
        if (this.isFull()) {
            console.log(`The chicken coop is full. Cannot add more chickens. (Max ${this.chickens.length})`);
            return false;
        }

        for (let i = 0; i < this.chickens.length; i++) {
            if (this.chickens[i] === null) {
                this.chickens[i] = chicken;
                this.chickenCount++;
                console.log(`Chicken added successfully. Total chickens: ${this.chickenCount}`);
                return true;
            }
        }
        return false;
    }

    /** Removes the chicken with the given id. Returns true if removed. */
    remove(chickenId: number): boolean {
        for (let i = 0; i < this.chickens.length; i++) {
            const chicken = this.chickens[i];
            if (chicken !== null && chicken.id === chickenId) {
                this.chickens[i] = null;
                this.chickenCount--;
                console.log(`Chicken with ID ${chickenId} removed.`);
                return true;
            }
        }
        console.log(`Chicken with ID ${chickenId} not found.`);
        return false;
    }

    existsId(chickenId: number): boolean {
        return this.chickens.some(c => c !== null && c.id === chickenId);
    }

    resetIteration(): void {
        this.iteratorIndex = 0;
    }

    next(): Chicken | null {
        while (this.iteratorIndex < this.chickens.length) {
            const chicken = this.chickens[this.iteratorIndex];
            this.iteratorIndex++;
            if (chicken !== null) {
                return chicken;
            }
        }
        return null;
    }

    isFull(): boolean {
        return this.chickenCount >= this.chickens.length;
    }

    getChickenCount(): number {
        return this.chickenCount;
    }

    setChickens(chickens: (Chicken | null)[]): void {
        this.chickens = chickens;
        this.chickenCount = chickens.filter(c => c !== null).length;
        this.iteratorIndex = 0;
        for (const c of chickens) {
            if (c !== null && c.id >= this.nextId) {
                this.nextId = c.id + 1;
            }
        }
    }

    toString(): string {
        return `ChickenCoop{chickenCoopId=${this.chickenCoopId}, chickens=[${this.chickens.join(", ")}]}`;
    }
}
