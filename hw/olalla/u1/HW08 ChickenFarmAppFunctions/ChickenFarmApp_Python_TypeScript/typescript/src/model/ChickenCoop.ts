import { Chicken } from "./Chicken";

export class ChickenCoop {
    private currentIndex = 0;

    constructor(
        private chickenCoopId: number,
        private chickens: Array<Chicken | null>
    ) {}

    public add(chicken: Chicken): void {
        for (let i = 0; i < this.chickens.length; i++) {
            if (this.chickens[i] === null) {
                this.chickens[i] = chicken;
                console.log("Chicken added successfully");
                return;
            }
        }
        console.log("The chicken coop is full");
    }

    public remove(chickenId: number): void {
        for (let i = 0; i < this.chickens.length; i++) {
            const chicken = this.chickens[i];
            if (chicken !== null && chicken.getId() === chickenId) {
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

    public resetIteration(): void {
        this.currentIndex = 0;
    }

    public next(): Chicken | null {
        while (this.currentIndex < this.chickens.length) {
            const chicken = this.chickens[this.currentIndex];
            this.currentIndex++;
            if (chicken !== null) return chicken;
        }
        return null;
    }

    public getChickenCoopId(): number { return this.chickenCoopId; }
    public setChickenCoopId(id: number): void { this.chickenCoopId = id; }
    public getChickens(): Array<Chicken | null> { return this.chickens; }
    public setChickens(chickens: Array<Chicken | null>): void { this.chickens = chickens; }
}
