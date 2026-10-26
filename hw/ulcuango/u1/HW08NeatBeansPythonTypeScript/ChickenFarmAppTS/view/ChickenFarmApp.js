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
const Chicken_1 = require("../model/Chicken");
const Egg_1 = require("../model/Egg");
const Poop_1 = require("../model/Poop");
const ChickenCoop_1 = require("../model/ChickenCoop");
const ChickenFarmer_1 = require("../model/ChickenFarmer");
const readlineSync = __importStar(require("readline-sync"));
/**
 * @author Josselyn Ulcuango, Codelex, @ESPE
 */
class ChickenFarmApp {
    static main() {
        const coop = new ChickenCoop_1.ChickenCoop(1);
        const farmer = new ChickenFarmer_1.ChickenFarmer(1, "Josselyn", coop);
        console.log("Josselyn Ulcuango's Chicken Farm App");
        console.log("=====================================");
        farmer.feedChickens();
        farmer.collectEggs();
        const chicken = new Chicken_1.Chicken(1, "Lucy", "brown and white", new Date(2024, 8, 15), 0, true);
        const egg = new Egg_1.Egg(1, 'M');
        const poop = new Poop_1.Poop(1, "brown", 'S');
        const chicken1 = new Chicken_1.Chicken(1, "Renata", "white", new Date(2024, 8, 15), 0, false);
        console.log(`chicken --> ${chicken.toString()}`);
        console.log(`egg --> ${egg.toString()}`);
        console.log(`poop --> ${poop.toString()}`);
        console.log(`chicken1 --> ${chicken1.toString()}`);
        chicken.doStuff();
        for (let i = 1; i <= 10; i++) {
            const birthYear = 2009 + i;
            const c = new Chicken_1.Chicken(i, `chicken ${i}`, "white", new Date(birthYear, 3, 3), 0, true);
            c.setAgeInYear(c.computeAgeInYears());
            coop.addChicken(c);
        }
        let option = 0;
        let nextId = 11;
        do {
            console.log("\n=====================================");
            console.log("         CHICKEN FARM MENU           ");
            console.log("=====================================");
            console.log("1. Add new chickens");
            console.log("2. View registered chickens and actions");
            console.log("3. Remove a chicken");
            console.log("4. Exit");
            const input = readlineSync.question("Select an option: ");
            option = parseInt(input);
            if (isNaN(option)) {
                console.log("Please enter a valid number.");
                continue;
            }
            switch (option) {
                case 1:
                    const qtyInput = readlineSync.question("\nHow many chickens do you want to add?: ");
                    const quantity = parseInt(qtyInput);
                    if (!isNaN(quantity)) {
                        for (let i = 0; i < quantity; i++) {
                            console.log(`\n--- Registering Chicken ${i + 1} of ${quantity} ---`);
                            const name = readlineSync.question("Enter the chicken's name: ");
                            const color = readlineSync.question("Enter the chicken's color: ");
                            const year = parseInt(readlineSync.question("Enter birth year (e.g., 2024): "));
                            const month = parseInt(readlineSync.question("Enter birth month (1-12): ")) - 1;
                            const day = parseInt(readlineSync.question("Enter birth day (1-31): "));
                            const bornDate = new Date(year, month, day);
                            const moltingInput = readlineSync.question("Is it molting? (true/false): ");
                            const molting = moltingInput.toLowerCase() === 'true';
                            const newChicken = new Chicken_1.Chicken(nextId, name, color, bornDate, 0, molting);
                            newChicken.setAgeInYear(newChicken.computeAgeInYears());
                            coop.addChicken(newChicken);
                            console.log(`Chicken '${name}' successfully added with ID: ${nextId}!`);
                            nextId++;
                        }
                    }
                    else {
                        console.log("Invalid quantity.");
                    }
                    break;
                case 2:
                    console.log("\n--- CURRENT CHICKENS LIST AND ACTIONS ---");
                    farmer.feedChickens();
                    farmer.collectEggs();
                    const chickenList = coop.getChickens();
                    if (chickenList.length === 0) {
                        console.log("There are no chickens registered on the farm.");
                    }
                    else {
                        chickenList.forEach((c, index) => {
                            c.setAgeInYear(c.computeAgeInYears());
                            console.log(`\n[Index ${index}] chickens --> ${c.toString()}`);
                            console.log(`--> Action of ${c.getName()}:`);
                            c.doStuff();
                        });
                        console.log(`\nlength of chickens --> ${chickenList.length}`);
                    }
                    break;
                case 3:
                    console.log("\n--- REMOVE A CHICKEN ---");
                    const currentChickens = coop.getChickens();
                    if (currentChickens.length === 0) {
                        console.log("There are no chickens available to remove.");
                    }
                    else {
                        currentChickens.forEach((c, index) => {
                            console.log(`Index [${index}] -> Name: ${c.getName()} (ID: ${c.getId()})`);
                        });
                        const indexInput = parseInt(readlineSync.question("Enter the index of the chicken you want to remove: "));
                        if (!isNaN(indexInput) && indexInput >= 0 && indexInput < currentChickens.length) {
                            const targetChicken = currentChickens[indexInput];
                            coop.removeChicken(targetChicken.getId());
                            console.log("Chicken has been successfully removed from the farm!");
                        }
                        else {
                            console.log("Index out of range or invalid input.");
                        }
                    }
                    break;
                case 4:
                    console.log("\nExiting Chicken Farm App. Goodbye, Josselyn!");
                    break;
                default:
                    console.log("Invalid option. Please choose between 1 and 4.");
            }
        } while (option !== 4);
    }
}
ChickenFarmApp.main();
