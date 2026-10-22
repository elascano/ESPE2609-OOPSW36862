import { Chicken } from "./Chicken";

export class ChickenCoop {
    public static readonly CAPACITY: number = 10;
    private _chickenCoopId: number;
    private _chickens: (Chicken | null)[];
    private _currentIndex: number;

    constructor(chickenCoopId: number) {
        this._chickenCoopId = chickenCoopId;
        this._chickens = new Array(ChickenCoop.CAPACITY).fill(null);
        this._currentIndex = 0;
    }

    public get chickenCoopId(): number {
        return this._chickenCoopId;
    }

    public set chickenCoopId(value: number) {
        this._chickenCoopId = value;
    }

    public get chickens(): (Chicken | null)[] {
        return this._chickens;
    }

    public get currentIndex(): number {
        return this._currentIndex;
    }

    public add(chicken: Chicken): boolean {
        for (const existing of this._chickens) {
            if (existing !== null && existing.id === chicken.id) {
                console.log(`There is already a chicken with id ${chicken.id}.`);
                return false;
            }
        }

        for (let i = 0; i < this._chickens.length; i++) {
            if (this._chickens[i] === null) {
                this._chickens[i] = chicken;
                console.log(`Chicken added: ${chicken.name}`);
                return true;
            }
        }

        console.log(`The coop is full (maximum ${this._chickens.length}).`);
        return false;
    }

    public remove(chickenId: number): boolean {
        for (let i = 0; i < this._chickens.length; i++) {
            if (this._chickens[i] !== null && this._chickens[i]!.id === chickenId) {
                const name = this._chickens[i]!.name;
                for (let j = i; j < this._chickens.length - 1; j++) {
                    this._chickens[j] = this._chickens[j + 1];
                }
                this._chickens[this._chickens.length - 1] = null;
                console.log(`Chicken removed: ${name}`);
                return true;
            }
        }

        console.log(`No chicken found with id ${chickenId}.`);
        return false;
    }

    public resetIteration(): void {
        this._currentIndex = 0;
        console.log("Iteration reset.");
    }

    public next(): void {
        if (this._currentIndex < this._chickens.length && this._chickens[this._currentIndex] !== null) {
            console.log(this._chickens[this._currentIndex]!.toString());
            this._currentIndex++;
        } else {
            console.log("No more chickens. Reset the iteration to start over.");
        }
    }

    public toString(): string {
        const activeChickens = this._chickens.filter(c => c !== null).map(c => c!.toString());
        return `ChickenCoop{chickenCoopId=${this._chickenCoopId}, chickens=[${activeChickens.join(", ")}], currentIndex=${this._currentIndex}}`;
    }
}