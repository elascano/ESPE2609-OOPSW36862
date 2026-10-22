import { Chicken } from "./Chicken";

/**
 * @author Josselyn Ulcuango, Codelex, @ESPE
 */
export class ChickenCoop {
    private id: number;
    private chickens: Chicken[];

    constructor(id: number) {
        this.id = id;
        this.chickens = [];
    }

    public addChicken(chicken: Chicken): void {
        if (chicken !== null && chicken !== undefined) {
            this.chickens.push(chicken);
            console.log(`Chicken '${chicken.getName()}' added to coop ${this.id}`);
        }
    }

    public removeChicken(chickenId: number): void {
        const index = this.chickens.findIndex(c => c.getId() === chickenId);
        if (index !== -1) {
            const removed = this.chickens.splice(index, 1)[0];
            console.log(`Chicken '${removed.getName()}' removed from coop ${this.id}`);
        } else {
            console.log(`Chicken with ID ${chickenId} was not found in coop ${this.id}`);
        }
    }

    public reset(): void {
        this.chickens = [];
        console.log(`Coop ${this.id} has been reset.`);
    }

    public countChickens(): number {
        return this.chickens.length;
    }

    public toString(): string {
        let chickensStr = this.chickens.map(c => `  ${c.toString()}`).join("\n");
        return `ChickenCoop{id=${this.id}, chickens=[\n${chickensStr}\n]}`;
    }

    public getId(): number { return this.id; }
    public setId(id: number): void { this.id = id; }
    public getChickens(): Chicken[] { return this.chickens; }
    public setChickens(chickens: Chicken[]): void { this.chickens = chickens; }
}