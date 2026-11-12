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
exports.ConsoleMenu = void 0;
const readline = __importStar(require("readline/promises"));
const errors_1 = require("../errors");
const Microphone_1 = require("../models/Microphone");
class ConsoleMenu {
    constructor(service) {
        this.service = service;
        this.input = readline.createInterface({
            input: process.stdin,
            output: process.stdout,
        });
    }
    async run() {
        this.loadExistingData();
        let running = true;
        while (running) {
            this.printMenu();
            const choice = (await this.input.question("Choose an option: ")).trim();
            running = await this.handleChoice(choice);
        }
        this.input.close();
    }
    async handleChoice(choice) {
        switch (choice) {
            case "1":
                await this.createMicrophone();
                return true;
            case "2":
                this.showMicrophones();
                return true;
            case "3":
                console.log("Goodbye!");
                return false;
            default:
                console.log("Invalid option. Try again.");
                return true;
        }
    }
    printMenu() {
        console.log("\n=== MICROPHONE MANAGER ===");
        console.log("1. Create a microphone");
        console.log("2. Read microphones from JSON file");
        console.log("3. Exit");
    }
    loadExistingData() {
        try {
            this.service.loadFromFile();
        }
        catch (error) {
            this.printError(error, "Warning");
        }
    }
    async createMicrophone() {
        try {
            const microphone = await this.askMicrophoneData();
            this.service.addMicrophone(microphone);
            console.log(`Saved: ${microphone}`);
        }
        catch (error) {
            this.printError(error, "Error");
        }
    }
    async askMicrophoneData() {
        const id = Number(await this.input.question("Id (positive integer): "));
        const brand = await this.input.question("Brand: ");
        const type = await this.input.question(`Type (${Microphone_1.VALID_TYPES.join("/")}): `);
        const connection = await this.input.question(`Connection (${Microphone_1.VALID_CONNECTIONS.join("/")}): `);
        return new Microphone_1.Microphone(id, brand, type, connection);
    }
    showMicrophones() {
        try {
            const microphones = this.service.loadFromFile();
            if (microphones.length === 0) {
                console.log("The JSON file has no microphones yet.");
                return;
            }
            console.log("\n--- Microphones read from JSON ---");
            microphones.forEach((microphone) => console.log(microphone.toString()));
        }
        catch (error) {
            this.printError(error, "Error");
        }
    }
    printError(error, label) {
        const message = error instanceof errors_1.MicrophoneError ? error.message : "Unexpected error.";
        console.log(`${label}: ${message}`);
    }
}
exports.ConsoleMenu = ConsoleMenu;
