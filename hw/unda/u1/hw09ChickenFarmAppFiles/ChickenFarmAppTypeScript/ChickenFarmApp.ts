import * as readline from "node:readline";
import { fileURLToPath } from "node:url";
import { Chicken } from "../model/Chicken.ts";
import { ChickenCoop } from "../model/ChickenCoop.ts";
import { FileManager } from "../utils/FileManager.ts";

/**
 * @author Mateo Unda, CodeLex, @ESPE
 */

const WIDTH = 60;                       
const FILE_NAME = "chickens.csv";       
const FILE_PATH = fileURLToPath(new URL("../../" + FILE_NAME, import.meta.url));


const keyboard = readline.createInterface({ input: process.stdin, output: process.stdout });
const lines = keyboard[Symbol.asyncIterator]();

let coop: ChickenCoop;                  
let unsavedChanges = false;             

async function main(): Promise<void> {
    loadAtStart();

    let option: number;
    do {
        printMenu();
        option = await readInt(" Choose an option: ");

        switch (option) {
            case 1:
                await insertChickens();
                break;
            case 2:
                saveChickens();
                break;
            case 3:
                readSavedChickens();
                break;
            case 4:
                showCoop();
                break;
            case 5:
                await removeChicken();
                break;
            case 6:
                await walkThroughCoop();
                break;
            case 0:
                await exitApp();
                break;
            default:
                console.log(" [X] Invalid option, choose a number from the menu");
        }

        if (option !== 0) {
            await pause();
        }
    } while (option !== 0);

    keyboard.close();
}

// =========================== MENU ===========================

function printMenu(): void {
    console.log();
    printHeader();

    let status: string;
    if (unsavedChanges) {
        status = "unsaved changes (*)";
    } else {
        status = "everything saved";
    }
    console.log(" Coop #" + coop.chickenCoopId
        + "   |   chickens: " + coop.getCount() + "/" + coop.getCapacity()
        + "   |   " + status);
    console.log("-".repeat(WIDTH));
    console.log("   [1]  Insert chickens");
    console.log("   [2]  Save chickens to file");
    console.log("   [3]  Read saved chickens");
    console.log("   [4]  Show chickens in the coop");
    console.log("   [5]  Remove a chicken");
    console.log("   [6]  Walk through the coop (next / reset)");
    console.log("   [0]  Exit");
    console.log("-".repeat(WIDTH));
}

async function pause(): Promise<void> {
    console.log();
    await readText(" Press Enter to go back to the menu...");
}

// =========================== OPTIONS ===========================

function loadAtStart(): void {
    try {
        coop = FileManager.load(FILE_PATH, 1);
        if (coop.isEmpty()) {
            console.log(" No saved chickens yet, starting with an empty coop.");
        } else {
            console.log(" [OK] " + coop.getCount() + " chickens loaded from " + FILE_NAME);
        }
    } catch (error) {
        coop = new ChickenCoop(1);
        console.log(" [X] The file could not be read: " + error);
    }
}

// [1]
async function insertChickens(): Promise<void> {
    printSection("INSERT CHICKENS");
    let addAnother = true;

    while (addAnother) {
        if (coop.isFull()) {
            console.log(" [X] The coop is full (" + coop.getCapacity() + " chickens)");
            return;
        }

        console.log();
        console.log(" Chicken #" + (coop.getCount() + 1));
        const chicken = await readChicken();
        coop.add(chicken);
        unsavedChanges = true;
        console.log(" [OK] " + chicken.name + " was added to the coop ("
            + coop.getCount() + "/" + coop.getCapacity() + ")");

        addAnother = await askYesNo(" Add another chicken? (y/n): ");
    }
    console.log(" Remember to save them with option [2]");
}

// [2]
function saveChickens(): void {
    printSection("SAVE CHICKENS TO FILE");
    try {
        FileManager.save(coop, FILE_PATH);
        unsavedChanges = false;
        console.log(" [OK] " + coop.getCount() + " chickens saved in:");
        console.log("      " + FileManager.fullPath(FILE_PATH));
    } catch (error) {
        console.log(" [X] The chickens could not be saved: " + error);
    }
}

// [3]
function readSavedChickens(): void {
    if (!FileManager.exists(FILE_PATH)) {
        printSection("SAVED CHICKENS (" + FILE_NAME + ")");
        console.log(" There is no saved file yet, use option [2] first");
        return;
    }
    try {
        const savedCoop = FileManager.load(FILE_PATH, coop.chickenCoopId);
        printChickenTable(savedCoop, "SAVED CHICKENS (" + FILE_NAME + ")");
        if (unsavedChanges) {
            console.log(" (*) The coop has changes that are not saved in the file yet");
        }
    } catch (error) {
        console.log(" [X] The file could not be read: " + error);
    }
}

// [4]
function showCoop(): void {
    printChickenTable(coop, "CHICKENS IN THE COOP");
    console.log();
    console.log(" coop --> " + coop);     // the toString of V2
}

// [5]
async function removeChicken(): Promise<void> {
    printChickenTable(coop, "REMOVE A CHICKEN");
    if (coop.isEmpty()) {
        return;
    }

    const id = await readInt(" Enter the id of the chicken to remove: ");
    const before = coop.getCount();
    coop.remove(id);

    if (coop.getCount() < before) {        // if count went down, it was removed
        unsavedChanges = true;
        console.log(" [OK] The chicken with id " + id + " was removed");
        console.log(" Remember to save the change with option [2]");
    } else {
        console.log(" [X] There is no chicken with id " + id);
    }
}

// [6]
async function walkThroughCoop(): Promise<void> {
    printSection("WALK THROUGH THE COOP");
    if (coop.isEmpty()) {
        console.log(" The coop is empty");
        return;
    }

    coop.resetIteration();
    console.log(" resetIteration()  ->  we start at the first chicken");
    console.log(" [n] next()   [r] resetIteration()   [b] back to the menu");

    let option: string;
    do {
        option = (await readText(" > ")).toLowerCase();
        switch (option) {
            case "n": {
                const chicken = coop.next();
                if (chicken !== null) {
                    console.log("   next()            ->  " + chicken.name + " (id " + chicken.id + ")");
                } else {
                    console.log("   next()            ->  null (no more chickens, press r)");
                }
                break;
            }
            case "r":
                coop.resetIteration();
                console.log("   resetIteration()  ->  back to the first chicken");
                break;
            case "b":
                break;
            default:
                console.log("   [X] Use n, r or b");
        }
    } while (option !== "b");
}

async function exitApp(): Promise<void> {
    if (unsavedChanges) {
        console.log();
        const save = await askYesNo(" You have unsaved changes. Save them before leaving? (y/n): ");
        if (save) {
            saveChickens();
        }
    }
    printSection("GOODBYE");
    console.log(" Thanks for using the Chicken Farm App!");
    console.log("=".repeat(WIDTH));
}

// =========================== TABLE ===========================

function printChickenTable(chickenCoop: ChickenCoop, title: string): void {
    printSection(title);

    if (chickenCoop.isEmpty()) {
        console.log(" The coop is empty");
        return;
    }

    console.log(" " + "ID".padEnd(4) + "| " + "NAME".padEnd(11) + "| " + "COLOR".padEnd(11)
        + "| " + "BORN".padEnd(11) + "| " + "AGE".padEnd(4) + "| MOLT");
    console.log(" ----+------------+------------+------------+-----+-----");

    chickenCoop.resetIteration();
    let chicken = chickenCoop.next();
    while (chicken !== null) {
        let molting: string;
        if (chicken.molting) {
            molting = "yes";
        } else {
            molting = "no";
        }
        console.log(" " + String(chicken.id).padEnd(4)
            + "| " + chicken.name.slice(0, 10).padEnd(11)
            + "| " + chicken.color.slice(0, 10).padEnd(11)
            + "| " + formatDate(chicken.bornOnDate).padEnd(11)
            + "| " + String(chicken.ageInYears).padEnd(4)
            + "| " + molting);
        chicken = chickenCoop.next();
    }

    console.log(" Total: " + chickenCoop.getCount() + "/" + chickenCoop.getCapacity() + " chickens");
}

function formatDate(date: Date): string {
    const year = String(date.getFullYear()).padStart(4, "0");
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");
    return year + "-" + month + "-" + day;
}

// =========================== KEYBOARD ===========================

async function readChicken(): Promise<Chicken> {
    let id = await readInt("   id .................. ");
    while (coop.exists(id)) {
        console.log("   [X] There is already a chicken with id " + id);
        id = await readInt("   id .................. ");
    }
    const name = await readRequiredText("   name ................ ");
    const color = await readRequiredText("   color ............... ");
    const bornOnDate = await readDate();
    const molting = await askYesNo("   molting? (y/n) ...... ");

    const chicken = new Chicken(id, name, color, bornOnDate, 0, molting);
    chicken.setAgeInYears();
    return chicken;
}

async function readDate(): Promise<Date> {
    while (true) {
        const year = await readInt("   birth year .......... ");
        const month = await readInt("   birth month (1-12) .. ");
        const day = await readInt("   birth day ........... ");

        const bornOnDate = new Date(year, month - 1, day);   // in JavaScript the months go from 0 to 11
        bornOnDate.setFullYear(year);                         // so years before 100 are not changed to 19xx

        // if the date does not exist (ex. February 30), JavaScript "moves" it to the next month
        if (bornOnDate.getMonth() !== month - 1 || bornOnDate.getDate() !== day) {
            console.log("   [X] That date does not exist, try again");
        } else if (bornOnDate > new Date()) {
            console.log("   [X] The chicken can not be born in the future, try again");
        } else {
            return bornOnDate;
        }
    }
}

async function readText(message: string): Promise<string> {
    keyboard.setPrompt(message);    // the question that is shown
    keyboard.prompt();
    const line = await lines.next();
    if (line.done) {                // the keyboard was closed (ex. Ctrl+C)
        process.exit(0);
    }
    return line.value.trim();
}

async function readRequiredText(message: string): Promise<string> {
    let text = await readText(message);
    while (text === "") {
        console.log("   [X] This field can not be empty");
        text = await readText(message);
    }
    return text;
}

async function readInt(message: string): Promise<number> {
    while (true) {
        const text = await readText(message);
        const number = Number(text);
        if (text !== "" && Number.isInteger(number)) {
            return number;
        }
        console.log("   [X] Please enter a whole number");
    }
}

async function askYesNo(message: string): Promise<boolean> {
    while (true) {
        const answer = (await readText(message)).toLowerCase();
        if (answer === "y") {
            return true;
        }
        if (answer === "n") {
            return false;
        }
        console.log("   [X] Please answer y or n");
    }
}

// =========================== DRAWING ===========================

function printHeader(): void {
    printBoxLine();
    printBoxText("Mateo Unda'S CHICKEN FARM APP");
    printBoxText("version 3.0");
    printBoxLine();
}

function printBoxLine(): void {
    console.log("+" + "=".repeat(WIDTH - 2) + "+");
}

function printBoxText(text: string): void {
    const inside = WIDTH - 2;
    const left = Math.floor((inside - text.length) / 2);
    const right = inside - text.length - left;
    console.log("|" + " ".repeat(left) + text + " ".repeat(right) + "|");
}

function printSection(title: string): void {
    const text = " " + title + " ";
    const left = Math.floor((WIDTH - text.length) / 2);
    const right = WIDTH - text.length - left;

    console.log();
    console.log("-".repeat(left) + text + "-".repeat(right));
}

await main();
