import * as readline from "readline";
import { Chicken } from "../model/chicken";
import { ChickenCoop } from "../model/chickenCoop";
import { Egg } from "../model/egg";
import { Poop } from "../model/poop";
import * as path from "path";
import { ChickenFiles } from "../controller/chickenFiles";

// ------------------------- Input helper -------------------------

class Input {
    private rl: readline.Interface;
    private queue: string[] = [];
    private waiting: ((line: string | null) => void) | null = null;
    private closed = false;

    constructor() {
        this.rl = readline.createInterface({ input: process.stdin });
        this.rl.on("line", (line: string) => {
            if (this.waiting) {
                const resolve = this.waiting;
                this.waiting = null;
                resolve(line);
            } else {
                this.queue.push(line);
            }
        });
        this.rl.on("close", () => {
            this.closed = true;
            if (this.waiting) {
                const resolve = this.waiting;
                this.waiting = null;
                resolve(null);
            }
        });
    }

    async ask(message: string): Promise<string> {
        process.stdout.write(message);
        let line: string | null;
        if (this.queue.length > 0) {
            line = this.queue.shift()!;
        } else if (this.closed) {
            line = null;
        } else {
            line = await new Promise<string | null>(resolve => (this.waiting = resolve));
        }
        if (line === null) {
            console.log("\nInput closed. Exiting...");
            process.exit(0);
        }
        return line.trim();
    }

    close(): void {
        this.rl.close();
    }
}

const input = new Input();

async function readInt(message: string): Promise<number> {
    while (true) {
        const text = await input.ask(message);
        if (/^-?\d+$/.test(text)) {
            return parseInt(text, 10);
        }
        console.log("Invalid number. Please try again.");
    }
}

async function readIntInRange(message: string, min: number, max: number): Promise<number> {
    while (true) {
        const value = await readInt(message);
        if (value >= min && value <= max) {
            return value;
        }
        console.log(`The number must be between ${min} and ${max}.`);
    }
}

async function readNonEmpty(message: string): Promise<string> {
    while (true) {
        const value = await input.ask(message);
        if (value !== "") {
            return value;
        }
        console.log("This field cannot be empty.");
    }
}

function parseDate(text: string): Date | null {
    const match = /^(\d{2})\/(\d{2})\/(\d{4})$/.exec(text);
    if (!match) {
        return null;
    }
    const day = parseInt(match[1], 10);
    const month = parseInt(match[2], 10);
    const year = parseInt(match[3], 10);
    const date = new Date(year, month - 1, day);
    if (date.getFullYear() !== year || date.getMonth() !== month - 1 || date.getDate() !== day) {
        return null;
    }
    return date;
}

async function readPastDate(message: string): Promise<Date> {
    while (true) {
        const text = await input.ask(message);
        const date = parseDate(text);
        if (date === null) {
            console.log("Invalid date. Use the format dd/MM/yyyy (e.g. 15/04/2024).");
        } else if (date.getTime() > Date.now()) {
            console.log("The birth date cannot be in the future.");
        } else {
            return date;
        }
    }
}

async function readYesNo(message: string): Promise<boolean> {
    while (true) {
        const value = (await input.ask(message)).toLowerCase();
        if (value === "y") return true;
        if (value === "n") return false;
        console.log("Please answer 'y' or 'n'.");
    }
}

async function readConsistency(message: string): Promise<string> {
    while (true) {
        const value = (await input.ask(message)).toUpperCase();
        if (value === "S" || value === "M" || value === "L") {
            return value;
        }
        console.log("Invalid value. Use S, M or L.");
    }
}

// ------------------------- Menu actions -------------------------


function loadSavedChickens(coop: ChickenCoop, files: ChickenFiles): void {
    const saved = files.loadAll();
    if (saved.length === 0) {
        return;
    }
    const array: (Chicken | null)[] = new Array<Chicken | null>(ChickenCoop.CAPACITY).fill(null);
    const count = Math.min(saved.length, array.length);
    for (let i = 0; i < count; i++) {
        array[i] = saved[i];
    }
    coop.setChickens(array);
    console.log(`Loaded ${count} chicken(s) from file.`);
}

async function addChickens(coop: ChickenCoop, files: ChickenFiles): Promise<void> {
    if (coop.isFull()) {
        console.log(`The chicken coop is full! (Limit of ${ChickenCoop.CAPACITY} chickens reached).`);
        return;
    }

    const remaining = ChickenCoop.CAPACITY - coop.getChickenCount();
    const quantity = await readIntInRange(
        `How many chickens do you want to add? (1-${remaining}): `, 1, remaining);

    for (let i = 1; i <= quantity; i++) {
        console.log(`\n--- Register chicken ${i} of ${quantity} ---`);

        const id = coop.generateId();
        console.log(`Generated ID: ${id}`);

        const name = await readNonEmpty("Enter chicken name: ");
        const color = await readNonEmpty("Enter chicken color: ");
        const bornOnDate = await readPastDate("Enter birth date (dd/MM/yyyy): ");
        const molting = await readYesNo("Is the chicken molting? (y/n): ");

        console.log("-- Poop data --");
        const poopColor = await readNonEmpty("Enter poop color: ");
        const consistency = await readConsistency(
            "Enter poop consistency (S = solid, M = medium, L = liquid): ");

        const chickenPoop = new Poop(id, poopColor, consistency);
        const newChicken = new Chicken(id, name, color, bornOnDate, molting, chickenPoop);
        
        if (coop.add(newChicken)) {
            files.add(newChicken);
        }
    }
}

async function removeChicken(coop: ChickenCoop, files: ChickenFiles): Promise<void> {
    if (coop.getChickenCount() === 0) {
        console.log("The coop is empty. There is nothing to remove.");
        return;
    }

    listChickens(coop);
    const id = await readInt("Enter the ID of the chicken to remove: ");

    if (!coop.existsId(id)) {
        console.log(`Chicken with ID ${id} not found.`);
        return;
    }

    if (await readYesNo(`Are you sure you want to remove chicken ${id}? (y/n): `)) {
         if (coop.remove(id)) {
            files.delete(id);
        }
    } else {
        console.log("Operation cancelled.");
    }
}

function listChickens(coop: ChickenCoop): void {
    console.log("\n--- List of Chickens in the Coop ---");
    coop.resetIteration();
    let current = coop.next();

    if (current === null) {
        console.log("The coop is empty.");
    } else {
        while (current !== null) {
            console.log(current.toString());
            current = coop.next();
        }
    }
    console.log(`Total chickens in the coop: ${coop.getChickenCount()} / ${ChickenCoop.CAPACITY}`);
}

// ------------------------- Main -------------------------

async function main(): Promise<void> {
    console.log("Mateo Nacimba Chicken Farm App");
    console.log("===============================");

    const chicken = new Chicken(1, "Lucy", "brown and white", new Date(2024, 3, 15), true);
    const egg = new Egg(1, "M");
    const poop = new Poop(1, "brown", "S");
    const chicken1 = new Chicken(2, "Renata", "white", new Date(2007, 6, 13), true);

    console.log("chicken --> " + chicken);
    console.log("egg --> " + egg);
    console.log("poop --> " + poop);
    console.log("chicken2 --> " + chicken1);

    const localAge = chicken.computeAgeInYears();
    console.log(`The age of the chicken --> ${chicken.name}, is --> ${localAge}`);

    chicken.doStuff();

    console.log("\n===============================");
    console.log("CHICKEN COOP MANAGEMENT");
    console.log("===============================");

        const coop = new ChickenCoop(1);
    const files = new ChickenFiles(path.join(__dirname, "..", "..", "chickens.csv"));
    loadSavedChickens(coop, files);

    let option = -1;
    while (option !== 0) {
        console.log("\n----------- MENU -----------");
        console.log("1. Add chickens");
        console.log("2. Remove a chicken");
        console.log("3. List chickens");
        console.log("0. Exit");
        option = await readInt("Choose an option: ");

        switch (option) {
            case 1:
                await addChickens(coop, files);
                break;
            case 2:
                await removeChicken(coop, files);
                break;
            case 3:
                listChickens(coop);
                break;
            case 0:
                console.log("Goodbye!");
                break;
            default:
                console.log("Invalid option. Try again.");
        }
    }

    input.close();
}

main();