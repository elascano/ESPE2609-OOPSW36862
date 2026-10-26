import { Chicken } from "./Chicken.ts";

const CAPACITY = 10;   // max chickens per coop

/**
 * @author Andres Pachacama, Byte Work, @ESPE
 */
export class ChickenCoop {
    chickenCoopId: number;
    private chickens: (Chicken | null)[];   
    private count: number;                  
    private index: number;                  


    constructor(chickenCoopId: number, chickens: (Chicken | null)[] = []) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = new Array(CAPACITY).fill(null);   // 10 empty nests
        this.count = 0;
        this.index = 0;

        for (const chicken of chickens) {
            if (chicken !== null) {
                this.add(chicken);
            }
        }
    }


    add(chicken: Chicken): void {
        if (this.count < this.chickens.length) {
            this.chickens[this.count] = chicken;
            this.count++;
        }
    }

    remove(chickenId: number): void {
        for (let i = 0; i < this.count; i++) {
            if (this.chickens[i]!.id === chickenId) {
                for (let j = i; j < this.count - 1; j++) {
                    this.chickens[j] = this.chickens[j + 1];
                }
                this.chickens[this.count - 1] = null;
                this.count--;
                return;
            }
        }
    }

    resetIteration(): void {
        this.index = 0;
    }

    next(): Chicken | null {
        if (this.index < this.count) {
            const chicken = this.chickens[this.index];
            this.index++;
            return chicken;
        }
        return null;   
    }



    exists(chickenId: number): boolean {
        for (let i = 0; i < this.count; i++) {
            if (this.chickens[i]!.id === chickenId) {
                return true;
            }
        }
        return false;
    }

    isFull(): boolean {
        return this.count === this.chickens.length;
    }

    isEmpty(): boolean {
        return this.count === 0;
    }

    getCount(): number {
        return this.count;
    }

    getCapacity(): number {
        return this.chickens.length;
    }

    // ===================== toString (V2) =====================

    toString(): string {
        let localChickens = "";

        for (const chicken of this.chickens) {
            if (chicken !== null) {          
                localChickens += chicken;
                localChickens += "\t";
            }
        }

        return "ChickenCoop{chickenCoopId=" + this.chickenCoopId + ", chickens=\n" + localChickens + "}";
    }
}
