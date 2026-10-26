"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || (function () {
    var ownKeys = function(o) {
        ownKeys = Object.getOwnPropertyNames || function (o) {
            var ar = [];
            for (var k in o) if (Object.prototype.hasOwnProperty.call(o, k)) ar[ar.length] = k;
            return ar;
        };
        return ownKeys(o);
    };
    return function (mod) {
        if (mod && mod.__esModule) return mod;
        var result = {};
        if (mod != null) for (var k = ownKeys(mod), i = 0; i < k.length; i++) if (k[i] !== "default") __createBinding(result, mod, k[i]);
        __setModuleDefault(result, mod);
        return result;
    };
})();
Object.defineProperty(exports, "__esModule", { value: true });
exports.ChickenFarmerApp = void 0;
const Chicken_1 = require("./Chicken");
const ChickenCoop_1 = require("./ChickenCoop");
const readline = __importStar(require("readline"));
const fs = __importStar(require("fs"));
const FILE_NAME = 'chickens.json';
class ChickenFarmerApp {
    static promptInput(query) {
        const rl = readline.createInterface({
            input: process.stdin,
            output: process.stdout
        });
        return new Promise((resolve) => {
            rl.question(query, (answer) => {
                rl.close();
                resolve(answer.trim());
            });
        });
    }
    static async readInt(prompt) {
        while (true) {
            const input = await this.promptInput(prompt);
            if (/^-?\d+$/.test(input)) {
                return parseInt(input, 10);
            }
            console.log('Error: Enter a valid integer.');
        }
    }
    static async readString(prompt) {
        while (true) {
            const input = await this.promptInput(prompt);
            if (input.length > 0) {
                return input;
            }
            console.log('Error: Value cannot be empty.');
        }
    }
    static async readBoolean(prompt) {
        while (true) {
            const input = (await this.promptInput(prompt)).toLowerCase();
            if (input === 'true' || input === 'y' || input === 'yes') {
                return true;
            }
            else if (input === 'false' || input === 'n' || input === 'no') {
                return false;
            }
            console.log("Error: Answer 'yes' or 'no'.");
        }
    }
    static saveChickens(coop) {
        try {
            const chickensArray = coop.chickens;
            const chickenList = [];
            for (const chicken of chickensArray) {
                if (chicken !== null && chicken !== undefined) {
                    let dateStr;
                    try {
                        dateStr = chicken.bornOnDate ? new Date(chicken.bornOnDate).toISOString() : new Date().toISOString();
                    }
                    catch {
                        dateStr = new Date().toISOString();
                    }
                    chickenList.push({
                        id: chicken.id,
                        name: chicken.name,
                        color: chicken.color,
                        bornOnDate: dateStr,
                        ageInYears: chicken.ageInYears,
                        molting: chicken.isMolting()
                    });
                }
            }
            fs.writeFileSync(FILE_NAME, JSON.stringify(chickenList, null, 2), 'utf-8');
            return true;
        }
        catch (error) {
            console.log(`Error saving data: ${error}`);
            return false;
        }
    }
    static loadChickens(coop) {
        if (!fs.existsSync(FILE_NAME)) {
            return;
        }
        try {
            const fileData = fs.readFileSync(FILE_NAME, 'utf-8');
            if (!fileData.trim())
                return;
            const chickenList = JSON.parse(fileData);
            if (Array.isArray(chickenList)) {
                for (const item of chickenList) {
                    try {
                        const birthDate = new Date(item.bornOnDate || item.born_on_date);
                        if (isNaN(birthDate.getTime())) {
                            console.log(`Warning: Invalid date for chicken ID ${item.id}. Skipping...`);
                            continue;
                        }
                        const existingChicken = coop.chickens.find((c) => c !== null && c !== undefined && c.id === item.id);
                        if (existingChicken) {
                            console.log(`Warning: Duplicate chicken ID ${item.id} found in file. Skipping...`);
                            continue;
                        }
                        const age = item.ageInYears !== undefined ? item.ageInYears : item.age_in_years;
                        const chicken = new Chicken_1.Chicken(item.id, item.name, item.color, birthDate, age, item.molting);
                        if (!coop.add(chicken)) {
                            console.log(`Warning: Could not load chicken ID ${item.id}. Coop capacity reached.`);
                        }
                    }
                    catch (itemError) {
                        console.log(`Error parsing a chicken record: ${itemError}`);
                    }
                }
            }
        }
        catch (error) {
            console.log(`Error loading file: ${error}`);
        }
    }
    static showMenu() {
        console.log('\n---MENU---');
        console.log('1. Register new chicken');
        console.log('2. Delete chicken by ID');
        console.log('3. Display chicken');
        console.log('4. Inspect next chicken');
        console.log('5. View coop status');
        console.log('6. Exit');
    }
    static async addChickenToCoop(coop) {
        console.log('\n--- REGISTER CHICKEN ---');
        const id = await this.readInt('Enter ID: ');
        const idExists = coop.chickens.some((c) => c !== null && c !== undefined && c.id === id);
        if (idExists) {
            console.log(`Error: A chicken with ID ${id} already exists.`);
            return;
        }
        const name = await this.readString('Enter Name: ');
        const color = await this.readString('Enter Color: ');
        const isMolting = await this.readBoolean('Is molting? (yes/no): ');
        const newChicken = new Chicken_1.Chicken(id, name, color, new Date(), 0, isMolting);
        if (coop.add(newChicken)) {
            if (this.saveChickens(coop)) {
                console.log('Chicken successfully registered and saved.');
            }
            else {
                console.log('Chicken added to coop, but failed to save to file.');
            }
        }
        else {
            console.log('Error: Coop is full.');
        }
    }
    static async removeChickenFromCoop(coop) {
        console.log('\n--- DELETE CHICKEN ---');
        const removeId = await this.readInt('Enter ID to delete: ');
        if (coop.remove(removeId)) {
            if (this.saveChickens(coop)) {
                console.log('Delete request completed and updated in file.');
            }
            else {
                console.log('Chicken removed from coop, but failed to update file.');
            }
        }
        else {
            console.log(`Error: Chicken with ID ${removeId} not found.`);
        }
    }
    static showAllChickens(coop) {
        console.log('\n--- REGISTERED CHICKENS ---');
        coop.resetIteration();
        let current = coop.next();
        if (current === null) {
            console.log('No chickens found.');
        }
        else {
            while (current !== null) {
                console.log(`-> ${current.toString()}`);
                current = coop.next();
            }
        }
    }
    static showNextChicken(coop) {
        console.log('\n--- NEXT ENTRY ---');
        const nextChicken = coop.next();
        if (nextChicken !== null) {
            console.log(`-> ${nextChicken.toString()}`);
        }
        else {
            console.log('End of list reached.');
        }
    }
    static async main() {
        console.log("Hello Chicken Farmers. this is Leslye Cuichan`s Farm");
        const coop = new ChickenCoop_1.ChickenCoop(1);
        this.loadChickens(coop);
        let option = 0;
        do {
            this.showMenu();
            option = await this.readInt('Select an option: ');
            switch (option) {
                case 1:
                    await this.addChickenToCoop(coop);
                    break;
                case 2:
                    await this.removeChickenFromCoop(coop);
                    break;
                case 3:
                    this.showAllChickens(coop);
                    break;
                case 4:
                    this.showNextChicken(coop);
                    break;
                case 5:
                    console.log('\n--- CURRENT COOP DETAILS ---');
                    console.log(coop.toString());
                    break;
                case 6:
                    this.saveChickens(coop);
                    console.log('One more line for saying Good Bye');
                    console.log('Good job!');
                    break;
                default:
                    console.log('\nInvalid option. Try again.');
                    break;
            }
        } while (option !== 6);
    }
}
exports.ChickenFarmerApp = ChickenFarmerApp;
if (require.main === module) {
    ChickenFarmerApp.main();
}
