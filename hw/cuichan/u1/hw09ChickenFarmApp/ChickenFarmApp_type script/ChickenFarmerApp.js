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
exports.ChickenFarmApp = void 0;
const Chicken_1 = require("./Chicken");
const ChickenCoop_1 = require("./ChickenCoop");
const Egg_1 = require("./Egg");
const Poop_1 = require("./Poop");
const readline = __importStar(require("readline"));
class ChickenFarmApp {
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
            const parsed = parseInt(input, 10);
            if (!isNaN(parsed)) {
                return parsed;
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
        const name = await this.readString('Enter Name: ');
        const color = await this.readString('Enter Color: ');
        const isMolting = await this.readBoolean('Is molting? (yes/no): ');
        const newChicken = new Chicken_1.Chicken(id, name, color, new Date(), 0, isMolting);
        coop.add(newChicken);
        console.log('Chicken successfully registered.');
    }
    static async removeChickenFromCoop(coop) {
        console.log('\n--- DELETE CHICKEN ---');
        const removeId = await this.readInt('Enter ID to delete: ');
        coop.remove(removeId);
        console.log('Delete request completed.');
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
        console.log("Leslye Cuichan's Chicken Farm App");
        console.log('================================');
        let chicken = new Chicken_1.Chicken(1, 'Lucy', 'brown and white', new Date(2000, 9, 20), 0, true);
        let egg = new Egg_1.Egg(1, 'M');
        let poop = new Poop_1.Poop(1, 'brown', 'S');
        let chicken1 = new Chicken_1.Chicken(2, 'Lucy', 'white', new Date(), 0, false);
        console.log(`chicken --> ${chicken.toString()}`);
        console.log(`egg --> ${egg.toString()}`);
        console.log(`poop --> ${poop.toString()}`);
        console.log(`chicken 2 --> ${chicken1.toString()}`);
        let localAge = chicken.computeAgeInYears();
        console.log(`the age of the chicken --> ${chicken.name}, is --> ${localAge}`);
        console.log(`the age of the chicken ---> ${chicken.name}, is ---> ${chicken.ageInYears}`);
        chicken.doStuff();
        let poop2 = new Poop_1.Poop(2, 'green', 'N');
        console.log(`poop--> ${poop2.toString()}`);
        let egg2 = new Egg_1.Egg(2, 'O');
        console.log(`egg--> ${egg2.toString()}`);
        let coop = new ChickenCoop_1.ChickenCoop(1);
        for (let i = 0; i < 10; i++) {
            console.log(`chickens --> ${coop.next()}`);
        }
        let chickens = new Array(10);
        let ageForTheLoop = 0;
        for (let i = 0; i < chickens.length; i++) {
            ageForTheLoop = 2010 + i;
            chickens[i] = new Chicken_1.Chicken(i, `chicken ${i + 1}`, 'white ', new Date(ageForTheLoop, 3, 3), 0, true);
            coop.add(chickens[i]);
        }
        for (let i = 0; i < 10; i++) {
            console.log(`chickens --> ${chickens[i]?.toString()}`);
        }
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
                    console.log('\nClosing application...');
                    break;
                default:
                    console.log('\nInvalid option. Try again.');
                    break;
            }
        } while (option !== 6);
    }
}
exports.ChickenFarmApp = ChickenFarmApp;
ChickenFarmApp.main();
