import { Chicken } from "./Chicken";
import { ChickenCoop } from "./ChickenCoop";
import * as readline from "node:readline";

async function main(): Promise<void> {
    const chickens: (Chicken | null)[] =
        new Array<Chicken | null>(100).fill(null);

    const coop = new ChickenCoop(1, chickens);

    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout
    });

    const ask = (question: string): Promise<string> => new Promise(resolve => rl.question(question, resolve));

    let option = 0;

    do {
        console.log("\nHello, Francisco Demera's Chicken Farm App");
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