import * as readline from "readline";
import { Chicken } from "../model/Chicken";
import { ChickenCoop } from "../model/ChickenCoop";

/**
 * @author Paucar Matias, ByteWorks, @ESPE
 */

// this is like the Scanner of Java: it reads what the customer writes
const rl = readline.createInterface({ input: process.stdin });
const lines = rl[Symbol.asyncIterator]();

// counters to generate the ids automatically
let nextChickenId = 1;
let nextCoopId = 1;

// shows the message and waits until the customer writes a line
async function readLine(message: string): Promise<string> {
    process.stdout.write(message);
    const result = await lines.next();
    if (result.done) {
        // there is nothing more to read
        process.exit(0);
    }
    return result.value;
}

// reads a number and asks again if the customer writes letters
async function readInt(message: string): Promise<number> {
    while (true) {
        const text = (await readLine(message)).trim();
        if (/^-?\d+$/.test(text)) {
            return parseInt(text, 10);
        }
        console.log("Please enter a valid number");
    }
}

async function main(): Promise<void> {
    console.log("Matias Paucar's Chicken Farm App");
    console.log("#################################");

    const coopId = nextCoopId;
    nextCoopId++;
    const coop = new ChickenCoop(coopId);
    console.log("Chicken coop id: " + coopId);

    let option: number;

    do {
        console.log("\n----------- MENU -----------");
        console.log("1. Add chickens");
        console.log("2. Remove a chicken");
        console.log("3. List chickens");
        console.log("0. Exit");
        option = await readInt("Choose an option: ");

        switch (option) {
            case 1:
                await addChickens(coop);
                break;
            case 2:
                await removeChickens(coop);
                break;
            case 3:
                showChickens(coop);
                break;
            case 0:
                console.log("Goodbye!");
                break;
            default:
                console.log("Invalid option, try again");
        }
    } while (option !== 0);

    rl.close();
}

// option 1: the customer says how many chickens to add
async function addChickens(coop: ChickenCoop): Promise<void> {
    const freeSpaces = ChickenCoop.MAX_CHICKENS - coop.getNumberOfChickens();

    if (freeSpaces === 0) {
        console.log("The coop is full, we can not add any more chickens");
        return;
    }

    let quantityToAdd: number;
    do {
        quantityToAdd = await readInt("How many chickens do you want to add? (1-" + freeSpaces + "): ");
        if (quantityToAdd < 1 || quantityToAdd > freeSpaces) {
            console.log("Invalid quantity, it must be between 1 and " + freeSpaces);
        }
    } while (quantityToAdd < 1 || quantityToAdd > freeSpaces);

    for (let i = 0; i < quantityToAdd; i++) {
        console.log("\n--- Chicken " + (i + 1) + " ---");
        const chicken = await readChicken();
        coop.add(chicken);
    }
}

// option 2: the customer says how many chickens to remove
async function removeChickens(coop: ChickenCoop): Promise<void> {
    if (coop.getNumberOfChickens() === 0) {
        console.log("The coop is empty, there are no chickens to remove");
        return;
    }

    showChickens(coop);

    const maximum = coop.getNumberOfChickens();
    let quantityToRemove: number;
    do {
        quantityToRemove = await readInt("How many chickens do you want to remove? (1-" + maximum + "): ");
        if (quantityToRemove < 1 || quantityToRemove > maximum) {
            console.log("Invalid quantity, it must be between 1 and " + maximum);
        }
    } while (quantityToRemove < 1 || quantityToRemove > maximum);

    for (let i = 0; i < quantityToRemove; i++) {
        const idToRemove = await readInt("Enter the id of the chicken to remove: ");
        coop.remove(idToRemove);
    }
}

// option 3: shows all the chickens of the coop
function showChickens(coop: ChickenCoop): void {
    if (coop.getNumberOfChickens() === 0) {
        console.log("The coop is empty");
        return;
    }

    console.log("\n=== Chickens in the coop ===");
    coop.resetIteration();
    while (coop.hasNext()) {
        const current = coop.next()!;
        console.log("chickens --> " + current.toString());
        console.log("Chicken's age: " + current.computeAgeInYears());
        current.doStuff();
    }
    console.log("number of chickens --> " + coop.getNumberOfChickens());
}

// asks the customer all the data of one chicken
async function readChicken(): Promise<Chicken> {
    const id = nextChickenId;
    nextChickenId++;
    console.log("Chicken id: " + id);

    const name = await readLine("Name: ");
    const color = await readLine("Color: ");

    // we ask again if the date does not exist (for example 31 of February)
    let bornOnDate: Date;
    while (true) {
        const year = await readInt("Year of birth (example 2024): ");
        const month = await readInt("Month of birth (1-12): ");
        const day = await readInt("Day of birth: ");

        // month - 1 because in JavaScript the months start in 0 (like Java)
        bornOnDate = new Date(Date.UTC(year, month - 1, day));
        if (bornOnDate.getUTCFullYear() === year
            && bornOnDate.getUTCMonth() === month - 1
            && bornOnDate.getUTCDate() === day) {
            break;
        }
        console.log("That date does not exist, please enter it again");
    }

    const molting = (await readLine("Is it molting? (true/false): ")).trim().toLowerCase() === "true";

    return new Chicken(id, name, color, bornOnDate, molting);
}

main();
