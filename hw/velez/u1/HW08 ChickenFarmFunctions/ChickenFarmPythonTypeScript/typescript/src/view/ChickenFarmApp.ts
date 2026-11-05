import * as readline from "node:readline";
import { Chicken } from "../model/Chicken";
import { ChickenCoop } from "../model/ChickenCoop";
import { Egg } from "../model/Egg";
import { Poop } from "../model/Poop";

const rl = readline.createInterface({ input: process.stdin, output: process.stdout });

function ask(message: string): Promise<string> {
    return new Promise(resolve => rl.question(message, answer => resolve(answer.trim())));
}

async function readInt(message: string): Promise<number> {
    while (true) {
        const input = await ask(message);
        const value = Number(input);
        if (input !== "" && Number.isInteger(value)) return value;
        console.log("Invalid input. Enter a whole number.");
    }
}

async function readText(message: string): Promise<string> {
    while (true) {
        const value = await ask(message);
        if (value.length > 0) return value;
        console.log("This field cannot be empty.");
    }
}

async function readBoolean(message: string): Promise<boolean> {
    while (true) {
        const value = (await ask(message)).toLowerCase();
        if (value === "true") return true;
        if (value === "false") return false;
        console.log("Enter true or false.");
    }
}

async function readBirthDate(): Promise<Date> {
    while (true) {
        const year = await readInt("Enter birth year: ");
        const month = await readInt("Enter birth month (1-12): ");
        const day = await readInt("Enter birth day: ");
        const birthDate = new Date(year, month - 1, day);
        const valid = birthDate.getFullYear() === year &&
            birthDate.getMonth() === month - 1 &&
            birthDate.getDate() === day;
        if (!valid) {
            console.log("Invalid date. Check the year, month and day.");
            continue;
        }
        const today = new Date();
        today.setHours(0, 0, 0, 0);
        if (birthDate > today) {
            console.log("The birth date cannot be in the future.");
            continue;
        }
        return birthDate;
    }
}

function generateUniqueId(chickens: Array<Chicken | null>): number {
    while (true) {
        const id = Math.floor(Math.random() * 9000) + 1000;
        if (!chickens.some(chicken => chicken !== null && chicken.getId() === id)) return id;
    }
}

async function addChicken(coop: ChickenCoop): Promise<void> {
    const id = generateUniqueId(coop.getChickens());
    console.log("\nAdding a new chicken");
    console.log(`Generated chicken ID: ${id}`);
    const name = await readText("Enter chicken name: ");
    const color = await readText("Enter chicken color: ");
    const birthDate = await readBirthDate();
    const molting = await readBoolean("Is the chicken molting? (true/false): ");
    const chicken = new Chicken(id, name, color, birthDate, 0, molting);
    chicken.setAgeInYears();
    coop.add(chicken);
    console.log("Chicken added successfully.");
    console.log(chicken.toString());
}

async function removeChicken(coop: ChickenCoop): Promise<void> {
    const id = await readInt("Enter the ID of the chicken to remove: ");
    coop.remove(id);
}

function showChickens(coop: ChickenCoop): void {
    console.log("\n===== CHICKENS IN THE COOP =====");
    coop.resetIteration();
    let found = false;
    let chicken = coop.next();
    while (chicken !== null) {
        console.log(chicken.toString());
        found = true;
        chicken = coop.next();
    }
    if (!found) console.log("There are no chickens in the coop.");
}

async function main(): Promise<void> {
    console.log("Renata Olalla's Chicken Farm App");
    console.log("=================================");

    const poop = new Poop(1, "brown", "S");
    const egg = new Egg(1, "M");
    const chicken = new Chicken(1, "Lucy", "brown and white", new Date(2024, 8, 15), 0, true);
    const chicken1 = new Chicken(2, "Renata", "white", new Date(2024, 8, 15), 0, false);

    chicken.setAgeInYears();
    chicken1.setAgeInYears();

    console.log("chicken --> " + chicken.toString());
    console.log("egg --> " + egg.toString());
    console.log("egg size --> " + egg.getSize());
    console.log("poop --> " + poop.toString());
    console.log("chicken1 --> " + chicken1.toString());
    console.log(`The age of ${chicken.getName()} is ${chicken.computeAgeInYears()} years.`);
    chicken.doStuff();

    const chickens: Array<Chicken | null> = Array(10).fill(null);
    chickens[0] = new Chicken(3, "Pepita", "black", new Date(2020, 2, 2), 0, true);
    chickens[1] = chicken;
    console.log("\nInitial chickens array:");
    chickens.forEach((item, i) => console.log(`chickens[${i}] --> ${item?.toString() ?? "null"}`));

    for (let i = 0; i < chickens.length; i++) {
        const birthYear = 2010 + i;
        chickens[i] = new Chicken(i + 1, `Chicken ${i + 1}`, "white", new Date(birthYear, 3, 3), 0, true);
        chickens[i]!.setAgeInYears();
    }

    console.log("\nGenerated chickens array:");
    chickens.forEach((item, i) => console.log(`chickens[${i}] --> ${item?.toString() ?? "null"}`));
    console.log(`Length of chickens array --> ${chickens.length}`);

    const coopChickens: Array<Chicken | null> = Array(10).fill(null);
    const coop = new ChickenCoop(1, coopChickens);

    let option = 0;
    while (option !== 4) {
        console.log("\n===== CHICKEN COOP MENU =====");
        console.log("1. Add a chicken");
        console.log("2. Remove a chicken");
        console.log("3. Show chickens");
        console.log("4. Exit");
        option = await readInt("Choose an option: ");

        switch (option) {
            case 1: await addChicken(coop); break;
            case 2: await removeChicken(coop); break;
            case 3: showChickens(coop); break;
            case 4: console.log("Exiting Chicken Coop..."); break;
            default: console.log("Invalid option. Choose a number from 1 to 4.");
        }
    }
    rl.close();
}

main().catch(error => {
    console.error(error);
    rl.close();
});
