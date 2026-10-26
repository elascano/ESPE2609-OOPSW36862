import { Chicken } from "./Chicken.ts";

/**
 * @author Andres Pachacama, Byte Work, @ESPE
 */
export class ChickenCoop {
    chickenCoopId: number;
    private chickens: (Chicken | null)[];   
    private count: number;                  
    private index: number;                  

    constructor(chickenCoopId: number) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = new Array(10).fill(null);   
        this.count = 0;
        this.index = 0;
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
        return null;   // ya no hay mas pollitos
    }

    getCount(): number {
        return this.count;
    }

    getCapacity(): number {
        return this.chickens.length;
    }
}
