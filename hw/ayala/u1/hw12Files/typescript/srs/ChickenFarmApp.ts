import * as fs from "node:fs";
import * as path from "node:path";
import * as readline from "node:readline";
import { Chicken } from "./model/Chicken";
import { ChickenCoop } from "./model/ChickenCoop";
import { Egg } from "./model/Egg";
import { Poop } from "./model/Poop";

const FILE_NAME = path.join(__dirname, "..", "chickens.json");
const CAPACITY = 10;
const rl = readline.createInterface({ input: process.stdin, output: process.stdout });

function ask(question: string): Promise<string> {
    return new Promise(resolve => rl.question(question, answer => resolve(answer.trim())));
}

async function readInt(message: string): Promise<number> {
    while (true) {
        const input = await ask(message);
        const value = Number(input);
        if (input !== "" && Number.isInteger(value)) return value;
        console.log("Enter a whole number.");
    }
}

async function readText(message: string): Promise<string> {
    while (true) {
        const value = await ask(message);
        if (value !== "") return value;
        console.log("This field cannot be empty.");
    }
}

async function readBoolean(message: string): Promise<boolean> {
    while (true) {
        const value = (await ask(message)).toLowerCase();
        if (["yes", "true", "y", "si", "sí"].includes(value)) return true;
        if (["no", "false", "n"].includes(value)) return false;
        console.log("Enter yes or no.");
    }
}

async function readBirthDate(): Promise<string> {
    while (true) {
        const year = await readInt("Enter birth year: ");
        const month = await readInt("Enter birth month (1-12): ");
        const day = await readInt("Enter birth day (1-31): ");
        const value = `${year.toString().padStart(4, "0")}-${month.toString().padStart(2, "0")}-${day.toString().padStart(2, "0")}`;
        const born = new Date(`${value}T00:00:00`);
        const valid = !Number.isNaN(born.getTime()) &&
            born.getFullYear() === year && born.getMonth() + 1 === month &&
            born.getDate() === day;
        if (!valid) {
            console.log("Invalid birth date. Try again.");
            continue;
        }
        const today = new Date();
        today.setHours(0, 0, 0, 0);
        if (born > today) {
            console.log("Birth date cannot be in the future.");
            continue;
        }
        return value;
    }
}

function generateUniqueId(chickens: Chicken[]): number {
    const existingIds = new Set(chickens.map(chicken => chicken.id));
    while (true) {
        const id = Math.floor(Math.random() * 9000) + 1000;
        if (!existingIds.has(id)) return id;
    }
}

function loadCoopFromJson(): Chicken[] {
    if (!fs.existsSync(FILE_NAME)) {
        console.log("No saved file. Starting empty.");
        return [];
    }
    try {
        const raw = fs.readFileSync(FILE_NAME, "utf8");
        const data: unknown = JSON.parse(raw);
        if (!Array.isArray(data)) throw new Error("JSON must contain an array.");
        const chickens: Chicken[] = [];
        const seenIds = new Set<number>();
        for (const item of data) {
            if (chickens.length >= CAPACITY) break;
            const row = item as Record<string, unknown>;
            const id = Number(row.id);
            const name = String(row.name);
            const color = String(row.color);
            const bornOnDate = String(row.bornOnDate);
            const molting = row.molting === true || row.molting === "true";
            if (!Number.isInteger(id) || !name || !color ||
                !/^\d{4}-\d{2}-\d{2}$/.test(bornOnDate)) continue;
            if (seenIds.has(id)) continue;
            const chicken = new Chicken(id, name, color, bornOnDate, Number(row.ageInYears ?? 0), molting);
            chickens.push(chicken);
            seenIds.add(id);
        }
        console.log(`Loaded ${chickens.length} chicken(s) from JSON.`);
        return chickens;
    } catch (error) {
        console.log(`Error loading JSON: ${String(error)}`);
        return [];
    }
}

function saveCoopToJson(chickens: Chicken[]): void {
    try {
        const data = chickens.map(chicken => {
            chicken.setAgeInYears();
            return {
                id: chicken.id,
                name: chicken.name,
                color: chicken.color,
                bornOnDate: chicken.bornOnDate,
                ageInYears: chicken.ageInYears,
                molting: chicken.molting
            };
        });
        fs.writeFileSync(FILE_NAME, JSON.stringify(data, null, 2), "utf8");
        console.log("Data saved to chickens.json");
    } catch (error) {
        console.log(`Error saving JSON: ${String(error)}`);
    }
}

async function addChickens(coop: ChickenCoop): Promise<void> {
    const freeSpaces = CAPACITY - coop.chickens.length;
    if (freeSpaces <= 0) {
        console.log("The coop is full.");
        return;
    }
    console.log(`Available spaces: ${freeSpaces}`);
    let quantity = 0;
    while (quantity < 1 || quantity > freeSpaces) {
        quantity = await readInt("How many chickens do you want to add? ");
        if (quantity < 1 || quantity > freeSpaces) {
            console.log(`Enter a quantity from 1 to ${freeSpaces}.`);
        }
    }

    for (let index = 0; index < quantity; index++) {
        console.log(`\n--- Chicken ${index + 1} ---`);
        const id = generateUniqueId(coop.chickens);
        console.log(`Generated chicken ID: ${id}`);
        const name = await readText("Enter chicken name: ");
        const color = await readText("Enter chicken color: ");
        const bornOnDate = await readBirthDate();
        const molting = await readBoolean("Is the chicken molting? (yes/no): ");
        const chicken = new Chicken(id, name, color, bornOnDate, 0, molting);
        coop.add(chicken);
        console.log(`Chicken added: ${chicken.toString()}`);
    }
    saveCoopToJson(coop.chickens);
}

async function removeChicken(coop: ChickenCoop): Promise<void> {
    const id = await readInt("Enter the ID of the chicken to remove: ");
    if (coop.remove(id)) saveCoopToJson(coop.chickens);
}

function showChickens(chickens: Chicken[]): void {
    console.log("\n===== CHICKENS IN THE COOP =====");
    for (const chicken of chickens) console.log(chicken.toString());
    console.log(`Total chickens: ${chickens.length}`);
}

async function main(): Promise<void> {
    console.log("Renata Olalla's Chicken Farm App");
    console.log("=================================");
    console.log(`egg --> ${new Egg(1, "M")}`);
    console.log(`poop --> ${new Poop(1, "brown", "S")}`);

    const coop = new ChickenCoop(1, loadCoopFromJson(), CAPACITY);
    while (true) {
        console.log("\n===== CHICKEN COOP MENU =====");
        console.log("1. Add a chicken");
        console.log("2. Remove a chicken");
        console.log("3. Show chickens");
        console.log("4. Exit");
        const option = await readInt("Choose an option: ");
        if (option === 1) await addChickens(coop);
        else if (option === 2) await removeChicken(coop);
        else if (option === 3) showChickens(coop.chickens);
        else if (option === 4) {
            saveCoopToJson(coop.chickens);
            console.log("Exiting and saving data...");
            break;
        } else console.log("Invalid option.");
    }
    rl.close();
}

main().catch(error => {
    console.error(error);
    rl.close();
});