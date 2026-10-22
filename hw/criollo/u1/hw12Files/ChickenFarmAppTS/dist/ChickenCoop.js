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
exports.ChickenCoop = void 0;
const Chicken_1 = require("./Chicken");
const fs = __importStar(require("node:fs"));
class ChickenCoop {
    constructor(chickenCoopId = 1, chickens) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = chickens ?? new Array(10).fill(null);
        this.currentIndex = 0;
    }
    async add(ask) {
        const position = this.chickens.findIndex(chicken => chicken === null);
        if (position === -1) {
            console.log("The chicken coop is full.");
            return;
        }
        console.log("\n--- ADD CHICKEN ---");
        const id = Number(await ask("Enter chicken ID: "));
        const name = await ask("Enter chicken name: ");
        const color = await ask("Enter chicken color: ");
        const dateText = await ask("Enter birth date (YYYY-MM-DD): ");
        const moltingText = (await ask("Is the chicken molting? (true/false): "))
            .toLowerCase();
        if (!Number.isInteger(id) || !Number.isFinite(id)) {
            console.log("Invalid chicken ID.");
            return;
        }
        if (!/^\d{4}-\d{2}-\d{2}$/.test(dateText)) {
            console.log("Invalid date format. Use YYYY-MM-DD.");
            return;
        }
        const bornOnDate = new Date(`${dateText}T00:00:00`);
        if (Number.isNaN(bornOnDate.getTime()) ||
            bornOnDate.toISOString().slice(0, 10) !== dateText) {
            console.log("Invalid birth date.");
            return;
        }
        if (moltingText !== "true" && moltingText !== "false") {
            console.log("Please enter true or false.");
            return;
        }
        if (this.chickens.some(chicken => chicken?.id === id)) {
            console.log("A chicken with this ID already exists.");
            return;
        }
        const molting = moltingText === "true";
        this.chickens[position] = new Chicken_1.Chicken(id, name, color, bornOnDate, molting);
        console.log("Chicken added successfully!");
    }
    async remove(ask) {
        const input = await ask("Enter chicken ID to remove: ");
        const chickenId = Number(input);
        if (!Number.isInteger(chickenId)) {
            console.log("The chicken ID must be a number.");
            return;
        }
        for (let i = 0; i < this.chickens.length; i++) {
            const chicken = this.chickens[i];
            if (chicken != null && chicken.id === chickenId) {
                console.log("Removing chicken: " + chicken.name);
                this.chickens[i] = null;
                console.log("Chicken removed successfully!");
                return;
            }
        }
        console.log("Chicken not found.");
    }
    saveChickenCoop() {
        try {
            fs.writeFileSync("chickenCoop.txt", this.toString(), "utf-8");
            console.log("Chicken Coop saved successfully!");
        }
        catch (error) {
            console.log("Error saving Chicken Coop:", error);
        }
    }
    showChickenCoop() {
        console.log("\n===== CHICKEN COOP =====");
        console.log("Coop ID: " + this.chickenCoopId);
        let found = false;
        for (const chicken of this.chickens) {
            if (chicken !== null) {
                console.log(chicken.toString());
                found = true;
            }
        }
        if (!found) {
            console.log("There are no chickens in the coop.");
        }
    }
    resetIteration() {
        this.currentIndex = 0;
    }
    next() {
        while (this.currentIndex < this.chickens.length) {
            const chicken = this.chickens[this.currentIndex];
            this.currentIndex++;
            if (chicken !== null && chicken !== undefined) {
                return chicken;
            }
        }
        return null;
    }
    toString() {
        let result = "ChickenCoop{" + "chickenCoopId=" + this.chickenCoopId + ", chickens=[\n";
        for (const chicken of this.chickens) {
            if (chicken !== null) {
                result += "    " + chicken.toString() + "\n";
            }
        }
        result += "]}";
        return result;
    }
}
exports.ChickenCoop = ChickenCoop;
//# sourceMappingURL=ChickenCoop.js.map