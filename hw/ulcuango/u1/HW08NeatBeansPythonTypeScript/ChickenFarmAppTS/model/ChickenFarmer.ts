import { ChickenCoop } from "./ChickenCoop";

/**
 * @author Josselyn Ulcuango, Codelex, @ESPE
 */
export class ChickenFarmer {
    private id: number;
    private name: string;
    private chickenCoop: ChickenCoop;

    constructor(id: number, name: string, chickenCoop: ChickenCoop) {
        this.id = id;
        this.name = name;
        this.chickenCoop = chickenCoop;
    }

    public feedChickens(): void {
        console.log(`Farmer ${this.name} is feeding the chickens in coop ${this.chickenCoop.getId()}`);
    }

    public collectEggs(): void {
        console.log(`Farmer ${this.name} is collecting eggs from coop ${this.chickenCoop.getId()}`);
    }

    public toString(): string {
        return `ChickenFarmer{id=${this.id}, name=${this.name}, chickenCoopId=${this.chickenCoop.getId()}}`;
    }

    public getId(): number { return this.id; }
    public setId(id: number): void { this.id = id; }
    public getName(): string { return this.name; }
    public setName(name: string): void { this.name = name; }
    public getChickenCoop(): ChickenCoop { return this.chickenCoop; }
    public setChickenCoop(chickenCoop: ChickenCoop): void { this.chickenCoop = chickenCoop; }
}