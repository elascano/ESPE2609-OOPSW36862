declare const require: any;
declare const process: any;

const readline = require('readline');
const fs = require('fs');

const FILE_NAME = "chickens.json";

// Formats a Date as YYYY-MM-DD using the LOCAL calendar day (toISOString would use UTC and can shift the day).
function formatDate(date: Date): string {
    const y = date.getFullYear();
    const m = String(date.getMonth() + 1).padStart(2, "0");
    const d = String(date.getDate()).padStart(2, "0");
    return `${y}-${m}-${d}`;
}

// Parses YYYY-MM-DD as a LOCAL date (new Date("YYYY-MM-DD") would be read as UTC and can shift the day).
function parseDate(text: string): Date {
    const [y, m, d] = text.split("-").map(Number);
    return new Date(y, m - 1, d);
}

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function askQuestion(query: string): Promise<string> {
    return new Promise((resolve) => rl.question(query, (ans: string) => resolve(ans)));
}

class Egg {
    private id: number;
    private size: string;

    constructor(id: number, size: string) {
        this.id = id;
        this.size = size;
    }

    public toString(): string {
        return `Egg{id=${this.id}, size=${this.size}}`;
    }
}

class Poop {
    private id: number;
    private color: string;
    private consistency: string;

    constructor(id: number, color: string, consistency: string) {
        this.id = id;
        this.color = color;
        this.consistency = consistency;
    }

    public toString(): string {
        return `Poop{id=${this.id}, color=${this.color}, consistency=${this.consistency}}`;
    }
}

class Chicken {
    private ide: number;
    private name: string;
    private color: string;
    private bornOnDate: Date;
    private ageInYears: number;
    private molting: boolean;

    constructor(ide: number, name: string, color: string, bornOnDate: Date, molting: boolean) {
        this.ide = ide;
        this.name = name;
        this.color = color;
        this.bornOnDate = bornOnDate;
        this.molting = molting;
        this.ageInYears = this.computeAgeInYears();
    }

    public doStuff(): void {
        this.cluck();
        this.wander();
        this.eat();
        this.drink(1.3);
        console.log(`poop --> ${this.poop().toString()}`);
        console.log(`egg --> ${this.layAnEgg().toString()}`);
    }

    private cluck(): void {
        console.log(`The chicken ${this.name} is clucking`);
    }

    private wander(): void {
        console.log(`The chicken ${this.name} is wandering`);
    }

    private eat(): void {
        console.log(`The chicken ${this.name} is eating`);
    }

    private drink(amountOfWater: number): void {
        console.log(`The chicken ${this.name} is drinking ---> ${amountOfWater} ml of water`);
    }

    private poop(): Poop {
        return new Poop(2, "green", "M");
    }

    private layAnEgg(): Egg {
        return new Egg(2, "M");
    }

    public computeAgeInYears(): number {
        const today = new Date();
        let age = today.getFullYear() - this.bornOnDate.getFullYear();
        const m = today.getMonth() - this.bornOnDate.getMonth();
        if (m < 0 || (m === 0 && today.getDate() < this.bornOnDate.getDate())) {
            age--;
        }
        return Math.max(0, age);
    }

    public toDict(): object {
        return {
            ide: this.ide,
            name: this.name,
            color: this.color,
            bornOnDate: formatDate(this.bornOnDate),
            ageInYears: this.ageInYears,
            molting: this.molting
        };
    }

    public toString(): string {
        const dateStr = formatDate(this.bornOnDate);
        return `Chicken{ide=${this.ide}, name=${this.name}, color=${this.color}, bornOnDate=${dateStr}, ageInYears=${this.ageInYears}, molting=${this.molting}}`;
    }

    public getIde(): number { return this.ide; }
    public getName(): string { return this.name; }
    public getColor(): string { return this.color; }
    public getAgeInYears(): number { return this.ageInYears; }
    public isMolting(): boolean { return this.molting; }
}

function saveChickensToJson(chickens: Chicken[]): void {
    try {
        const data = chickens.map(c => c.toDict());
        fs.writeFileSync(FILE_NAME, JSON.stringify(data, null, 4), "utf-8");
    } catch (e) {
        console.log(`Error saving to JSON: ${e}`);
    }
}

function loadChickensFromJson(): Chicken[] {
    if (!fs.existsSync(FILE_NAME)) {
        return [];
    }
    try {
        const data = JSON.parse(fs.readFileSync(FILE_NAME, "utf-8"));
        return data.map((item: any) =>
            new Chicken(item.ide, item.name, item.color, parseDate(item.bornOnDate), item.molting)
        );
    } catch (e) {
        console.log(`Error loading JSON file: ${e}`);
        return [];
    }
}

async function readInt(promptText: string, min: number, max: number): Promise<number> {
    while (true) {
        const input = (await askQuestion(promptText)).trim();
        const value = Number(input);
        if (input !== "" && Number.isInteger(value) && value >= min && value <= max) {
            return value;
        }
        console.log(`Error: Please enter an integer number between ${min} and ${max}.`);
    }
}

async function main() {
    const chickens: Chicken[] = loadChickensFromJson();
    let nextId = chickens.reduce((max, c) => Math.max(max, c.getIde()), 0) + 1;
    let option = 0;

    console.log("==================================");
    console.log("    Yandry Velez Chicken Farm     ");
    console.log("==================================");
    console.log(`[Info] Loaded ${chickens.length} chicken(s) from ${FILE_NAME}`);

    do {
        console.log("\n--- MAIN MENU ---");
        console.log("1. Add a new chicken");
        console.log("2. View all chickens (description and actions)");
        console.log("3. Delete a chicken");
        console.log("4. Exit");

        option = await readInt("Select an option (1-4): ", 1, 4);

        switch (option) {
            case 1:
                let addAnother = "";
                do {
                    console.log(`\n--- Enter chicken details (Automatic ID assigned: ${nextId}) ---`);

                    let name = (await askQuestion("Name: ")).trim();
                    while (!name) {
                        name = (await askQuestion("Name cannot be empty. Enter the name: ")).trim();
                    }

                    let color = (await askQuestion("Color: ")).trim();
                    while (!color) {
                        color = (await askQuestion("Color cannot be empty. Enter the color: ")).trim();
                    }

                    const currentYear = new Date().getFullYear();
                    let bornOnDate: Date;
                    while (true) {
                        const year = await readInt(`Birth year (2000-${currentYear}): `, 2000, currentYear);
                        const month = await readInt("Birth month (1-12): ", 1, 12);
                        const day = await readInt("Birth day (1-31): ", 1, 31);

                        // new Date(2024, 1, 31) silently rolls over to March 2, so check the day really exists.
                        bornOnDate = new Date(year, month - 1, day);
                        if (bornOnDate.getMonth() === month - 1 && bornOnDate.getDate() === day) {
                            break;
                        }
                        console.log("Invalid calendar date. Enter the birth date again.");
                    }

                    let moltingInput = (await askQuestion("Is it molting? (y/n): ")).trim().toLowerCase();
                    while (!["y", "yes", "n", "no"].includes(moltingInput)) {
                        moltingInput = (await askQuestion("Invalid input. Type 'y' for yes or 'n' for no: ")).trim().toLowerCase();
                    }
                    const molting = moltingInput === "y" || moltingInput === "yes";

                    const newChicken = new Chicken(nextId, name, color, bornOnDate, molting);
                    chickens.push(newChicken);
                    saveChickensToJson(chickens);

                    console.log(`--> Chicken '${name}' registered with ID #${nextId} and saved to ${FILE_NAME}!`);
                    nextId++;

                    addAnother = (await askQuestion("\nDo you want to add another chicken right now? (y/n): ")).trim().toLowerCase();
                } while (addAnother === "y" || addAnother === "yes");
                break;

            case 2:
                console.log("\n================ CHICKEN LIST ================");
                if (chickens.length === 0) {
                    console.log("There are no chickens registered on the farm yet.");
                } else {
                    for (const c of chickens) {
                        console.log(`\nChicken ID #${c.getIde()}:`);
                        console.log(`  -> Object (toString): ${c.toString()}`);
                        console.log(`  -> Summary: Name: ${c.getName()} | Color: ${c.getColor()} | Age: ${c.getAgeInYears()} years | Molting: ${c.isMolting() ? "Yes" : "No"}`);
                        console.log("  -> What is it doing?");
                        c.doStuff();
                        console.log("-------------------------------------------------");
                    }
                }
                break;

            case 3:
                console.log("\n================ DELETE A CHICKEN ================");
                if (chickens.length === 0) {
                    console.log("There are no chickens available to delete.");
                } else {
                    console.log("Current chickens:");
                    for (const c of chickens) {
                        console.log(`  - ID #${c.getIde()}: ${c.getName()} (${c.getColor()})`);
                    }
                    const idToDelete = await readInt("Enter the ID of the chicken you want to delete: ", 1, nextId - 1);
                    const index = chickens.findIndex(c => c.getIde() === idToDelete);

                    if (index !== -1) {
                        const removed = chickens.splice(index, 1)[0];
                        saveChickensToJson(chickens);
                        console.log(`--> Chicken '${removed.getName()}' (ID #${idToDelete}) was deleted from ${FILE_NAME}!`);
                    } else {
                        console.log(`--> Error: No chicken found with ID #${idToDelete}.`);
                    }
                }
                break;

            case 4:
                saveChickensToJson(chickens);
                console.log(`All data saved to ${FILE_NAME}. Exiting the farm simulator... Goodbye!`);
                break;
        }
    } while (option !== 4);

    rl.close();
}

main();
