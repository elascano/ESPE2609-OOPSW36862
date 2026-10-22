import { Chicken } from './chicken';

export class ChickenCoop {
    private id: number;
    private chickens: Chicken[];

    constructor(id: number = 0) {
        this.id = id;
        this.chickens = [];
    }

    public addChicken(chicken: Chicken): void {
        if (chicken !== null) {
            this.chickens.push(chicken);
            console.log("Chicken '" + chicken.getName() + "' added to coop " + this.id);
        }
    }

    public removeChicken(chickenId: number): void {
        let chickenToRemoveIndex = -1;
        for (let i = 0; i < this.chickens.length; i++) {
            if (this.chickens[i].getId() === chickenId) {
                chickenToRemoveIndex = i;
                break;
            }
        }

        if (chickenToRemoveIndex !== -1) {
            let chickenToRemove = this.chickens[chickenToRemoveIndex];
            this.chickens.splice(chickenToRemoveIndex, 1);
            console.log("Chicken '" + chickenToRemove.getName() + "' removed from coop " + this.id);
        } else {
            console.log("Chicken with ID " + chickenId + " was not found in coop " + this.id);
        }
    }

    public reset(): void {
        this.chickens = [];
        console.log("Coop " + this.id + " has been reset.");
    }

    public countChickens(): number {
        return this.chickens.length;
    }

    public toString(): string {
        let sb = "ChickenCoop{id=" + this.id + ", chickens=[\n";
        for (let chicken of this.chickens) {
            sb += "  " + chicken.toString() + "\n";
        }
        sb += "]}";
        return sb;
    }

    public getId(): number {
        return this.id;
    }

    public setId(id: number): void {
        this.id = id;
    }

    public getChickens(): Chicken[] {
        return this.chickens;
    }

    public setChickens(chickens: Chicken[]): void {
        this.chickens = chickens;
    }
}