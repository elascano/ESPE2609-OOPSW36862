import { Chicken } from './Chicken.js';

/**
 * @author Enrique Ayala, LogicLords,@ESPE
 */
export class ChickenCoop {
    private static readonly MAX_CAPACITY = 10;
    private _chickenCoopId: number;
    private _chickens: (Chicken | null)[];
    private _count: number;
    private _currentIndex: number;

    constructor(chickenCoopId: number) {
        this._chickenCoopId = chickenCoopId;
        this._chickens = new Array(ChickenCoop.MAX_CAPACITY).fill(null);
        this._count = 0;
        this._currentIndex = 0;
    }

    public add(chicken: Chicken): boolean {
        if (this.findIndexById(chicken.id) !== -1) {
            console.log(`There is already a chicken with id ${chicken.id}`);
            return false;
        }
        if (this._count >= ChickenCoop.MAX_CAPACITY) {
            console.log(`The coop is full (maximum ${ChickenCoop.MAX_CAPACITY}).`);
            return false;
        }
        this._chickens[this._count] = chicken;
        this._count++;
        return true;
    }

    public remove(chickenId: number): boolean {
        const index = this.findIndexById(chickenId);
        if (index === -1) {
            console.log(`There is no chicken with id ${chickenId}`);
            return false;
        }
        for (let i = index; i < this._count - 1; i++) {
            this._chickens[i] = this._chickens[i + 1];
        }
        this._chickens[this._count - 1] = null;
        this._count--;
        return true;
    }

    public update(chickenId: number, name: string, color: string, bornOnDate: Date, molting: boolean): boolean {
        const index = this.findIndexById(chickenId);
        if (index === -1) {
            console.log(`There is no chicken with id ${chickenId}`);
            return false;
        }
        const chicken = this._chickens[index];
        if (chicken) {
            chicken.name = name;
            chicken.color = color;
            chicken.bornOnDate = bornOnDate;
            chicken.molting = molting;
            chicken.setAgeInYears();
        }
        return true;
    }

    private findIndexById(chickenId: number): number {
        for (let i = 0; i < this._count; i++) {
            if (this._chickens[i]?.id === chickenId) {
                return i;
            }
        }
        return -1;
    }

    public toArray(): Chicken[] {
        return this._chickens.slice(0, this._count).filter((c): c is Chicken => c !== null);
    }

    public resetIteration(): void {
        this._currentIndex = 0;
    }

    public next(): Chicken | null {
        if (this._currentIndex >= this._count) {
            return null;
        }
        const chicken = this._chickens[this._currentIndex];
        this._currentIndex++;
        return chicken;
    }

    public get chickenCoopId(): number { return this._chickenCoopId; }
    public set chickenCoopId(value: number) { this._chickenCoopId = value; }

    public get count(): number { return this._count; }

    public toString(): string {
        let text = `ChickenCoop{id=${this._chickenCoopId}, chickens=${this._count}`;
        for (let i = 0; i < this._count; i++) {
            text += `\n  ${this._chickens[i]?.toString()}`;
        }
        return text + "}";
    }
}