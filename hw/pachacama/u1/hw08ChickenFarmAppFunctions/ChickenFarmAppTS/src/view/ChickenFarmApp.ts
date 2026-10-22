import * as readline from "node:readline";
import { Chicken } from "../model/Chicken.ts";
import { ChickenCoop } from "../model/ChickenCoop.ts";
import { Egg } from "../model/Egg.ts";
import { Poop } from "../model/Poop.ts";

/**
 * @author Andres Pachacama, Byte Work, @ESPE
 */

const WIDTH = 60;   


const keyboard = readline.createInterface({ input: process.stdin, output: process.stdout });
const lines = keyboard[Symbol.asyncIterator]();

async function main(): Promise<void> {
    const coop = new ChickenCoop(1);

    printHeader("ANDRES PACHACAMA'S CHICKEN FARM APP");


    await addChickens(coop);
    printChickens(coop);
    await removeChickens(coop);
    iterationDemo(coop);

    printSection("GOODBYE");
    console.log(" Thanks for using the Chicken Farm App!");
    console.log(repeat("=", WIDTH));

    keyboard.close();
}


async function addChickens(coop: ChickenCoop): Promise<void> {
    printSection("ADD CHICKENS");
    let addAnother = true;

    while (addAnother) {
        if (coop.getCount() === coop.getCapacity()) {
            console.log(" [X] The coop is full (" + coop.getCapacity() + " chickens)");
            return;
        }

        console.log();
        console.log(" Chicken #" + (coop.getCount() + 1));
        const chicken = await readChicken();
        coop.add(chicken);
        console.log(" [OK] " + chicken.name + " was added to the coop ("
            + coop.getCount() + "/" + coop.getCapacity() + ")");

        addAnother = await askYesNo(" Add another chicken? (y/n): ");
    }
}

function printChickens(coop: ChickenCoop): void {
    printSection("CHICKENS IN THE COOP");

    if (coop.getCount() === 0) {
        console.log(" The coop is empty");
        return;
    }

    console.log(" " + "ID".padEnd(4) + "| " + "NAME".padEnd(12) + "| " + "COLOR".padEnd(16)
        + "| " + "AGE".padEnd(4) + "| " + "MOLTING".padEnd(7));
    console.log(" ----+-------------+-----------------+-----+--------");

    coop.resetIteration();
    let chicken = coop.next();
    while (chicken !== null) {
        let molting: string;
        if (chicken.molting) {
            molting = "yes";
        } else {
            molting = "no";
        }
        console.log(" " + String(chicken.id).padEnd(4)
            + "| " + chicken.name.slice(0, 11).padEnd(12)
            + "| " + chicken.color.slice(0, 15).padEnd(16)
            + "| " + String(chicken.ageInYears).padEnd(4)
            + "| " + molting.padEnd(7));
        chicken = coop.next();
    }

    console.log(" Total: " + coop.getCount() + "/" + coop.getCapacity() + " chickens");
}

async function removeChickens(coop: ChickenCoop): Promise<void> {
    printSection("REMOVE CHICKENS");
    let removeAnother = await askYesNo(" Do you want to remove a chicken? (y/n): ");

    while (removeAnother) {
        if (coop.getCount() === 0) {
            console.log(" [X] The coop is empty, there is nothing to remove");
            return;
        }

        const id = await readInt(" Enter the id of the chicken to remove: ");
        const before = coop.getCount();
        coop.remove(id);

        if (coop.getCount() < before) {        // si bajo el count, si se borro
            console.log(" [OK] The chicken with id " + id + " was removed");
            printChickens(coop);
        } else {
            console.log(" [X] There is no chicken with id " + id);
        }

        removeAnother = await askYesNo(" Remove another chicken? (y/n): ");
    }
}

function iterationDemo(coop: ChickenCoop): void {
    printSection("ITERATION DEMO");

    coop.resetIteration();
    console.log(" resetIteration()  ->  back to the first chicken");

    let chicken = coop.next();
    while (chicken !== null) {
        console.log(" next()            ->  " + chicken.name);
        chicken = coop.next();
    }
    console.log(" next()            ->  (no more chickens)");

    coop.resetIteration();
    console.log(" resetIteration()  ->  back to the first chicken");

    chicken = coop.next();
    if (chicken !== null) {
        console.log(" next()            ->  " + chicken.name);
    } else {
        console.log(" next()            ->  (the coop is empty)");
    }
}

// =================== LEER DATOS DEL TECLADO ===================

async function readChicken(): Promise<Chicken> {
    const id = await readInt("   id .................. ");
    const name = await readText("   name ................ ");
    const color = await readText("   color ............... ");
    const bornOnDate = await readDate();
    const molting = await askYesNo("   molting? (y/n) ...... ");

    const chicken = new Chicken(id, name, color, bornOnDate, molting);
    chicken.setAgeInYears();
    return chicken;
}

async function readDate(): Promise<Date> {
    while (true) {
        const year = await readInt("   birth year .......... ");
        const month = await readInt("   birth month (1-12) .. ");
        const day = await readInt("   birth day ........... ");

        const bornOnDate = new Date(year, month - 1, day);   // en JavaScript los meses van de 0 a 11
        // si la fecha no existe (ej. 30 de febrero), JavaScript la "corre" al mes siguiente
        if (bornOnDate.getMonth() === month - 1 && bornOnDate.getDate() === day) {
            return bornOnDate;
        }
        console.log("   [X] That date does not exist, try again");
    }
}

async function readText(message: string): Promise<string> {
    keyboard.setPrompt(message);   // la pregunta que se muestra
    keyboard.prompt();
    const line = await lines.next();
    if (line.done) {             // se cerro el teclado (ej. Ctrl+C)
        process.exit(0);
    }
    return line.value.trim();
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

// =================== DIBUJAR EN PANTALLA ===================

function printHeader(title: string): void {
    const inside = WIDTH - 2;
    const left = Math.floor((inside - title.length) / 2);
    const right = inside - title.length - left;

    console.log("+" + repeat("=", inside) + "+");
    console.log("|" + repeat(" ", left) + title + repeat(" ", right) + "|");
    console.log("+" + repeat("=", inside) + "+");
}

function printSection(title: string): void {
    const text = " " + title + " ";
    const left = Math.floor((WIDTH - text.length) / 2);
    const right = WIDTH - text.length - left;

    console.log();
    console.log(repeat("-", left) + text + repeat("-", right));
}

function repeat(character: string, times: number): string {
    return character.repeat(times);
}

// =================== PRUEBAS DE CLASE ===================

function classPractice(): void {
    const chicken = new Chicken(2, "Maruja", "white", new Date(1970, 10, 17), false);
    chicken.setAgeInYears();
    const egg = new Egg(1, "M");

    console.log("my chicken is ---> " + chicken);
    console.log("egg ---> " + egg);
    console.log("egg size ---> " + egg.size);

    const poop = new Poop(1, "brown", "S");
    console.log("poop ---> " + poop);

    const chicken1 = new Chicken(1, "Lucy", "brown and white", new Date(1971, 0, 17), true);
    chicken1.setAgeInYears();
    console.log("chicken 1 ---> " + chicken1);

    const localAge = chicken.computeAgeInYears();
    console.log("the age of the chicken ---> " + chicken.name + ", is ---> " + localAge);

    chicken.doStuff();

    const chickens: (Chicken | null)[] = new Array(10).fill(null);
    chickens[0] = new Chicken(3, "Pepita", "black", new Date(2020, 2, 2), true);
    chickens[1] = chicken;

    for (let i = 0; i < 10; i++) {
        console.log("chickens --> " + chickens[i]);
    }

    for (let i = 0; i < chickens.length; i++) {
        chickens[i] = new Chicken(i, "chicken " + (i + 1), "white", new Date(2020, 3, 3), true);
    }
    for (let i = 0; i < 10; i++) {
        console.log("chickens --> " + chickens[i]);
    }
}

await main();
