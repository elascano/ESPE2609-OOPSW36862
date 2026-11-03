/** Versión TypeScript de ChickenFarmApp.java. */
import * as fs from "node:fs";
import * as path from "node:path";
import { createInterface } from "node:readline/promises";
import { Chicken, parseISODate } from "../model/Chicken";
import { ChickenCoop } from "../model/ChickenCoop";
import { Egg } from "../model/Egg";
import { Poop } from "../model/Poop";

const FILE_NAME = path.join(process.cwd(), "chickens.json");
const scanner = createInterface({ input: process.stdin, output: process.stdout });

export function loadCoopFromJson(coop: ChickenCoop): void {
    if (!fs.existsSync(FILE_NAME)) return;
    try {
        const data: unknown = JSON.parse(fs.readFileSync(FILE_NAME, "utf8"));
        if (!Array.isArray(data)) throw new Error("JSON must be an array");
        for (const item of data) {
            coop.add(Chicken.fromJSON(item));
        }
        console.log("--> Data loaded successfully from chickens.json");
    } catch (error) {
        console.log(`Error loading data from JSON: ${error instanceof Error ? error.message : String(error)}`);
    }
}

export function saveCoopToJson(coop: ChickenCoop): void {
    const data = coop.getChickens().filter((chicken): chicken is Chicken => chicken !== null)
        .map(chicken => chicken.toJSON());
    try {
        fs.writeFileSync(FILE_NAME, JSON.stringify(data, null, 2) + "\n", "utf8");
    } catch (error) {
        console.log(`Error saving data to JSON: ${error instanceof Error ? error.message : String(error)}`);
    }
}

function printMenu(): void {
    console.log("---------- MENU ----------");
    console.log("1. Add chicken");
    console.log("2. Remove chicken");
    console.log("3. Next chicken");
    console.log("4. Reset iteration");
    console.log("5. Show coop");
    console.log("6. Run demo");
    console.log("0. Exit");
}

async function readInt(message: string, min: number, max: number): Promise<number> {
    while (true) {
        const input = (await scanner.question(message)).trim();
        if (!/^-?\d+$/.test(input)) {
            console.log("Invalid number. Try again.");
            continue;
        }
        const value = Number(input);
        if (Number.isSafeInteger(value) && value >= min && value <= max) return value;
        console.log(`Enter a number between ${min} and ${max}.`);
    }
}

async function readBoolean(message: string): Promise<boolean> {
    while (true) {
        const input = (await scanner.question(message)).trim().toLowerCase();
        if (["true", "yes", "y", "s", "si"].includes(input)) return true;
        if (["false", "no", "n"].includes(input)) return false;
        console.log("Invalid input. Please enter 'yes'/'no' or 'true'/'false'.");
    }
}

async function addChicken(coop: ChickenCoop): Promise<void> {
    const id = await readInt("Id: ", 0, 2147483647);
    const name = (await scanner.question("Name: ")).trim();
    const color = (await scanner.question("Color: ")).trim();
    let bornOnDate: Date;
    while (true) {
        console.log("Birth date:");
        const day = await readInt("  Day (1-31): ", 1, 31);
        const month = await readInt("  Month (1-12): ", 1, 12);
        const year = await readInt("  Year (e.g. 2022): ", 1900, 2100);
        try {
            bornOnDate = parseISODate(`${year}-${String(month).padStart(2, "0")}-${String(day).padStart(2, "0")}`);
            break;
        } catch {
            console.log("Invalid calendar date. Try again.");
        }
    }
    const molting = await readBoolean("Is molting? (yes/no): ");
    const chicken = new Chicken(id, name, color, bornOnDate);
    chicken.setMolting(molting);
    chicken.setAgeInYears();
    if (coop.add(chicken)) saveCoopToJson(coop);
}

async function removeChicken(coop: ChickenCoop): Promise<void> {
    const id = await readInt("Id of the chicken to remove: ", 0, 2147483647);
    if (coop.remove(id)) saveCoopToJson(coop);
}

function runDemo(): void {
    const chicken = new Chicken(1, "Lucy", "brown and withe", parseISODate("1970-12-17"));
    const egg = new Egg(1, "M");
    const poop = new Poop(1, "brown", "S");
    const chicken1 = new Chicken(2, "Renata", "white", parseISODate("1970-11-17"));

    console.log(`chicken -->${chicken.toString()}`);
    console.log(`egg -->${egg.toString()}`);
    console.log(`poop -->${poop.toString()}`);
    console.log(`chicken 2-->${chicken1.toString()}`);
    console.log(`The age of the chicken --> ${chicken.getName()} is ${chicken.computeAgeInYears()}`);
    chicken.doStuff();
    chicken1.doStuff();

    const chickens: (Chicken | null)[] = Array(10).fill(null);
    chickens[0] = new Chicken(3, "Pepita", "black", parseISODate("2020-02-02"));
    chickens[1] = chicken;
    chickens[2] = chicken1;
    for (const item of chickens) {
        console.log(`chickens -->${item === null ? "null" : item.toString()}`);
    }

    for (let i = 0; i < 10; i++) {
        chickens[i] = new Chicken(i, `chicken ${i + 1}`, "white ", parseISODate(`${2010 + i}-01-01`));
    }
    for (const item of chickens) {
        console.log(`chickens --> ${item?.toString() ?? "null"}`);
    }
}

export async function main(): Promise<void> {
    console.log("Isaac Acurio Chicken Farm App");
    console.log("===============================");
    const coop = new ChickenCoop(1);
    loadCoopFromJson(coop);

    try {
        while (true) {
            printMenu();
            const option = await readInt("Choose an option: ", 0, 6);
            console.log();
            switch (option) {
                case 1: await addChicken(coop); break;
                case 2: await removeChicken(coop); break;
                case 3: coop.next(); break;
                case 4: coop.resetIteration(); break;
                case 5: console.log(coop.toString()); break;
                case 6: runDemo(); break;
                case 0:
                    saveCoopToJson(coop);
                    console.log("Exiting and saving data...");
                    return;
            }
            console.log();
        }
    } finally {
        scanner.close();
    }
}
