import * as readline from "readline";
import { FileManager } from "../controller/FileManager";
import { Chicken } from "../model/Chicken";
import { ChickenCoop } from "../model/ChickenCoop";

/**
 * @author Paucar Matias, ByteWorks, @ESPE
 */

// this is like the Scanner of Java: it reads what the customer writes
const rl = readline.createInterface({ input: process.stdin });

// the lines the customer writes are kept here until the program asks for them
const pendingLines: string[] = [];
const waitingReaders: Array<(line: string) => void> = [];
let inputClosed = false;

rl.on("line", (line: string) => {
    const reader = waitingReaders.shift();
    if (reader) {
        reader(line);
    } else {
        pendingLines.push(line);
    }
});

rl.on("close", () => {
    inputClosed = true;
    if (waitingReaders.length > 0) {
        // there is nothing more to read
        process.exit(0);
    }
});

const COOP_ID = 1;

// the coop is loaded from the file when the program starts
let coop: ChickenCoop;

// counter to generate the ids automatically
let nextChickenId = 1;

// shows the message and waits until the customer writes a line
function readLine(message: string): Promise<string> {
    process.stdout.write(message);
    const line = pendingLines.shift();
    if (line !== undefined) {
        return Promise.resolve(line);
    }
    if (inputClosed) {
        // there is nothing more to read
        process.exit(0);
    }
    return new Promise<string>((resolve) => {
        waitingReaders.push(resolve);
    });
}

// reads a text that can not be empty
async function readText(message: string): Promise<string> {
    while (true) {
        const text = (await readLine(message)).trim();
        if (text !== "") {
            return text;
        }
        console.log("This field is required");
    }
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

    coop = FileManager.load(COOP_ID);
    updateNextChickenId();
    console.log("Chicken coop id: " + COOP_ID);
    console.log(coop.getNumberOfChickens() + " chicken(s) loaded from " + FileManager.FILE_NAME);

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
                await addChickens();
                break;
            case 2:
                await removeChickens();
                break;
            case 3:
                showChickens();
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
async function addChickens(): Promise<void> {
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
        if (coop.add(chicken)) {
            console.log("The chicken " + chicken.getName() + " was added to the coop");
        } else {
            console.log("The coop is full, we can not add any more chickens");
        }
    }

    // persistence: the file is updated with the old chickens and the new ones
    FileManager.save(coop);
    console.log("The file " + FileManager.FILE_NAME + " was updated");
}

// option 2: the customer says how many chickens to remove
async function removeChickens(): Promise<void> {
    if (coop.getNumberOfChickens() === 0) {
        console.log("The coop is empty, there are no chickens to remove");
        return;
    }

    showChickens();

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
        const chicken = coop.findById(idToRemove);
        if (chicken !== null && coop.remove(idToRemove)) {
            console.log("The chicken " + chicken.getName() + " was removed from the coop");
        } else {
            console.log("There is no chicken with the id " + idToRemove);
        }
    }

    // persistence: the file is updated without the removed chickens
    FileManager.save(coop);
    console.log("The file " + FileManager.FILE_NAME + " was updated");
}

// option 3: shows the chickens that are saved in the file
function showChickens(): void {
    // persistence: we read the file again to show what is really saved
    coop = FileManager.load(COOP_ID);

    if (coop.getNumberOfChickens() === 0) {
        console.log("The coop is empty");
        return;
    }

    console.log("\n=== Chickens in the coop (" + FileManager.FILE_NAME + ") ===");
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

    const name = await readText("Name: ");
    const color = await readText("Color: ");

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

// the next id is the biggest id of the file + 1, so the ids are never repeated
function updateNextChickenId(): void {
    let maxId = 0;
    coop.resetIteration();
    while (coop.hasNext()) {
        const chicken = coop.next()!;
        if (chicken.getId() > maxId) {
            maxId = chicken.getId();
        }
    }
    nextChickenId = maxId + 1;
}

main();
