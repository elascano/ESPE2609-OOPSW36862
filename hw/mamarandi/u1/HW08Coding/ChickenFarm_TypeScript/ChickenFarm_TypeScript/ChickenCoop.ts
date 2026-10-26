import { Chicken } from "./Chicken";

export class ChickenCoop {
    private currentIndex: number = 0;

    constructor(private chickenCoopId: number, private chickens: (Chicken | null)[]) {}

    add(chicken: Chicken): void {
        for (let i = 0; i < this.chickens.length; i++) {
            if (this.chickens[i] === null) {
                this.chickens[i] = chicken;
                console.log("Chicken added successfully");
                return;
            }
        }
        console.log("The chicken coop is full");
    }

    remove(chickenId: number): void {
        for (let i = 0; i < this.chickens.length; i++) {
            if (this.chickens[i] !== null && this.chickens[i]!.getId() === chickenId) {
                for (let j = i; j < this.chickens.length - 1; j++) {
                    this.chickens[j] = this.chickens[j + 1];
                }
                this.chickens[this.chickens.length - 1] = null;
                console.log("Chicken removed successfully");
                return;
            }
        }
        console.log("Chicken not found");
    }

    resetIteration(): void { this.currentIndex = 0; }
    next(): Chicken | null {
        while (this.currentIndex < this.chickens.length) {
            const chicken = this.chickens[this.currentIndex++];
            if (chicken !== null) return chicken;
        }
        return null;
    }

    getChickenCoopId(): number { return this.chickenCoopId; }
    setChickenCoopId(id: number): void { this.chickenCoopId = id; }
    getChickens(): (Chicken | null)[] { return this.chickens; }
    setChickens(chickens: (Chicken | null)[]): void {
        this.chickens = chickens;
        this.resetIteration();
    }
    toString(): string {
        return `ChickenCoop{chickenCoopId=${this.chickenCoopId}, chickens=${this.chickens}}`;
    }
}
