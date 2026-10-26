import { Chicken } from "./Chicken";

/**
 * @author Paucar Matias, ByteWorks, @ESPE
 */
export class ChickenCoop {
    // we need to know the maximum, in this case is 10
    public static readonly MAX_CHICKENS = 10;

    private chickenCoopId: number;
    private chickens: (Chicken | null)[];
    private currentIndex: number;
    private numberOfChickens: number;

    constructor(chickenCoopId: number) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = new Array<Chicken | null>(ChickenCoop.MAX_CHICKENS).fill(null);
        this.currentIndex = 0;
        this.numberOfChickens = 0;
    }

    // returns true if the chicken was added, false if the coop is full
    public add(chicken: Chicken): boolean {
        // if we have less than 10 chickens, we can add one more
        // else we can not add any more
        if (this.numberOfChickens < this.chickens.length) {
            this.chickens[this.numberOfChickens] = chicken;
            this.numberOfChickens++;
            return true;
        }
        return false;
    }

    // returns true if the chicken was removed, false if there is no chicken with that id
    public remove(chickenId: number): boolean {
        let position = -1;

        // we look for the position of the chicken with that id
        for (let i = 0; i < this.numberOfChickens; i++) {
            if (this.chickens[i]!.getId() === chickenId) {
                position = i;
                break;
            }
        }

        if (position === -1) {
            return false;
        }

        // we move the next chickens one position to the left
        for (let i = position; i < this.numberOfChickens - 1; i++) {
            this.chickens[i] = this.chickens[i + 1];
        }

        this.chickens[this.numberOfChickens - 1] = null;
        this.numberOfChickens--;
        return true;
    }

    public findById(chickenId: number): Chicken | null {
        for (let i = 0; i < this.numberOfChickens; i++) {
            if (this.chickens[i]!.getId() === chickenId) {
                return this.chickens[i];
            }
        }
        return null;
    }

    public resetIteration(): void {
        this.currentIndex = 0;
    }

    public hasNext(): boolean {
        return this.currentIndex < this.numberOfChickens;
    }

    public next(): Chicken | null {
        if (this.hasNext()) {
            const chicken = this.chickens[this.currentIndex];
            this.currentIndex++;
            return chicken;
        }
        return null;
    }

    public toString(): string {
        const chickensText = this.chickens.map(c => (c === null ? "null" : c.toString())).join(", ");
        return "ChickenCoop{chickenCoopId=" + this.chickenCoopId + ", numberOfChickens="
            + this.numberOfChickens + ", chickens=[" + chickensText + "]}";
    }

    // getters and setters
    public getChickenCoopId(): number {
        return this.chickenCoopId;
    }

    public setChickenCoopId(chickenCoopId: number): void {
        this.chickenCoopId = chickenCoopId;
    }

    public getChickens(): (Chicken | null)[] {
        return this.chickens;
    }

    public getNumberOfChickens(): number {
        return this.numberOfChickens;
    }
}
