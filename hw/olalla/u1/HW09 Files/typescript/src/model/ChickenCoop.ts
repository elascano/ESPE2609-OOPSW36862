import { Chicken } from "./Chicken";

export class ChickenCoop {
    currentIndex = 0;

    constructor(
        public chickenCoopId: number,
        public chickens: Chicken[] = [],
        public capacity: number = 10
    ) {
        this.chickens = chickens.slice(0, capacity);
    }

    add(chicken: Chicken): boolean {
        if (this.chickens.length >= this.capacity) {
            console.log("The chicken coop is full.");
            return false;
        }
        this.chickens.push(chicken);
        console.log("Chicken added successfully.");
        return true;
    }

    remove(chickenId: number): boolean {
        const index = this.chickens.findIndex(chicken => chicken.id === chickenId);
        if (index === -1) {
            console.log("Chicken not found.");
            return false;
        }
        this.chickens.splice(index, 1);
        console.log("Chicken removed successfully.");
        return true;
    }

    resetIteration(): void {
        this.currentIndex = 0;
    }

    next(): Chicken | null {
        if (this.currentIndex >= this.chickens.length) {
            return null;
        }
        const chicken = this.chickens[this.currentIndex];
        this.currentIndex++;
        return chicken;
    }
}
