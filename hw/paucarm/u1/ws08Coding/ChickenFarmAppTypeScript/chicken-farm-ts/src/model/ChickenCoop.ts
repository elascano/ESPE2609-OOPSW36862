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

    public add(chicken: Chicken): void {
        // if we have less than 10 chickens, we can add one more
        // else we can not add any more
        if (this.numberOfChickens < this.chickens.length) {
            this.chickens[this.numberOfChickens] = chicken;
            this.numberOfChickens++;
            console.log("The chicken " + chicken.getName() + " was added to the coop");
        } else {
            console.log("The coop is full, we can not add any more chickens");
        }
    }

    public remove(chickenId: number): void {
        let position = -1;

        // we look for the position of the chicken with that id
        for (let i = 0; i < this.numberOfChickens; i++) {
            if (this.chickens[i]!.getId() === chickenId) {
                position = i;
                break;
            }
        }

        if (position === -1) {
            console.log("There is no chicken with the id " + chickenId);
            return;
        }

        const removedName = this.chickens[position]!.getName();

        // we move the next chickens one position to the left
        for (let i = position; i < this.numberOfChickens - 1; i++) {
            this.chickens[i] = this.chickens[i + 1];
        }

        this.chickens[this.numberOfChickens - 1] = null;
        this.numberOfChickens--;
        console.log("The chicken " + removedName + " was removed from the coop");
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
