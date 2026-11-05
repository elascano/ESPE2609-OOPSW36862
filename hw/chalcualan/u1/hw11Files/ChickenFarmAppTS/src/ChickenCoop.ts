
import { Chicken } from "./Chicken";
import * as fs from "node:fs";
import * as readline from "node:readline";

export class ChickenCoop {
    public chickenCoopId: number;
    public chickens: (Chicken | null)[];
    public currentIndex: number;

    constructor(chickenCoopId: number = 1, chickens?: (Chicken | null)[]) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = chickens ?? new Array<Chicken | null>(10).fill(null);
        this.currentIndex = 0;
    }

    public async add(ask: (question: string) => Promise<string>): Promise<void> {
    const position = this.chickens.findIndex(chicken => chicken === null);

    if (position === -1) {
        console.log("The chicken coop is full.");
        return;
    }

    console.log("\n--- ADD CHICKEN ---");

    const id = Number(await ask("Enter chicken ID: "));
    const name = await ask("Enter chicken name: ");
    const color = await ask("Enter chicken color: ");
    const dateText = await ask("Enter birth date (YYYY-MM-DD): ");
    const moltingText = (await ask("Is the chicken molting? (true/false): "))
        .toLowerCase();

    if (!Number.isInteger(id) || !Number.isFinite(id)) {
        console.log("Invalid chicken ID.");
        return;
    }

    if (!/^\d{4}-\d{2}-\d{2}$/.test(dateText)) {
        console.log("Invalid date format. Use YYYY-MM-DD.");
        return;
    }

    const bornOnDate = new Date(`${dateText}T00:00:00`);

    if (Number.isNaN(bornOnDate.getTime()) ||
        bornOnDate.toISOString().slice(0, 10) !== dateText) {
        console.log("Invalid birth date.");
        return;
    }

    if (moltingText !== "true" && moltingText !== "false") {
        console.log("Please enter true or false.");
        return;
    }

    if (this.chickens.some(chicken => chicken?.id === id)) {
        console.log("A chicken with this ID already exists.");
        return;
    }

    const molting = moltingText === "true";

    this.chickens[position] = new Chicken(id, name, color, bornOnDate, molting);

    console.log("Chicken added successfully!");
}

public async remove(ask: (question: string) => Promise<string>): Promise<void> {
    const input = await ask("Enter chicken ID to remove: ");
    const chickenId = Number(input);

    if (!Number.isInteger(chickenId)) {
        console.log("The chicken ID must be a number.");
        return;
    }
    for (let i = 0; i < this.chickens.length; i++) {
        const chicken = this.chickens[i];
        if (chicken != null && chicken.id === chickenId) {
            console.log("Removing chicken: " + chicken.name);
            this.chickens[i] = null;
            console.log("Chicken removed successfully!");
            return;
        }
    }
    console.log("Chicken not found.");
}

    public saveChickenCoop(): void {
        try {
            fs.writeFileSync("chickenCoop.txt", this.toString(), "utf-8");
            console.log("Chicken Coop saved successfully!");
        } catch (error) {
            console.log("Error saving Chicken Coop:", error);
        }
    }

    public showChickenCoop(): void {
        console.log("\n===== CHICKEN COOP =====");
        console.log("Coop ID: " + this.chickenCoopId);
        let found = false;
        for (const chicken of this.chickens) {
            if (chicken !== null) {
                console.log(chicken.toString());
                found = true;
            }
        }

        if (!found) {
            console.log("There are no chickens in the coop.");
        }
    }

    public resetIteration(): void {
        this.currentIndex = 0;
    }

    public next(): Chicken | null {
        while (this.currentIndex < this.chickens.length) {
            const chicken = this.chickens[this.currentIndex];
            this.currentIndex++;

            if (chicken !== null && chicken !== undefined) {
                return chicken;
            }
        }

        return null;
    }

    public toString(): string {
        let result = "ChickenCoop{" + "chickenCoopId=" + this.chickenCoopId + ", chickens=[\n";
        for (const chicken of this.chickens) {
            if (chicken !== null) {
                result += "    " + chicken.toString() + "\n";
            }
        }
        result += "]}";
        return result;
    }
}
