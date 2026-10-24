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
const ChickenCoop_1 = require("./ChickenCoop");
const readline = __importStar(require("node:readline"));
async function main() {
    const chickens = new Array(100).fill(null);
    const coop = new ChickenCoop_1.ChickenCoop(1, chickens);
    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout
    });
    const ask = (question) => new Promise(resolve => rl.question(question, resolve));
    let option = 0;
    do {
        console.log("\nHello, Dennis Criollos's Chicken Farm App");
        console.log("------------------------------------------");
        console.log("-----------------Welcome -----------------");
        console.log("----------What you will do today----------");
        console.log("1. Add chicken");
        console.log("2. Save Chicken Coop");
        console.log("3. Remove chicken");
        console.log("4. Show Chicken Coop");
        console.log("5. Exit");
        const input = await ask("Press any Option-----> ");
        option = Number(input);
        switch (option) {
            case 1:
                await coop.add(ask);
                break;
            case 2:
                coop.saveChickenCoop();
                break;
            case 3:
                await coop.remove(ask);
                break;
            case 4:
                coop.showChickenCoop();
                break;
            case 5:
                console.log("Exiting Chicken Farm App...");
                break;
            default:
                console.log("Invalid option. Try again.");
        }
    } while (option !== 5);
    rl.close();
}
main().catch(error => {
    console.error("Error running Chicken Farm App:", error);
});
//# sourceMappingURL=ChickenFarmApp.js.map