import * as readline from 'readline';
import { ChickenCoop } from './ChickenCoop.js';
import { Persistencia } from './Persistencia.js';

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

async function main() {
    console.log("Chicken Farm App (TypeScript Version)");
    console.log("===================================");

    let coop = Persistencia.cargar();
    if (!coop) {
        coop = new ChickenCoop();
    }

    let option = 0;
    while (option !== 5) {
        console.log("\n=== CHICKEN COOP MENU ===");
        console.log("1. Add chicken");
        console.log("2. Remove chicken");
        console.log("3. Show all chickens");
        console.log("4. Save data");
        console.log("5. Exit");

        const entrada = await new Promise<string>((resolve) => rl.question("Choose an option: ", resolve));
        option = parseInt(entrada, 10);

        if (isNaN(option)) {
            console.log("Invalid option. Enter a number:");
            continue;
        }

        switch (option) {
            case 1:
                await coop.add(rl);
                break;
            case 2:
                await coop.remove(rl);
                break;
            case 3:
                console.log("\n=== CHICKENS IN THE COOP ===");
                // Suponiendo que agregas un método getChickens() a ChickenCoop
                (coop as any).chickens.forEach((pollo: any) => console.log(pollo.toString()));
                console.log("============================");
                break;
            case 4:
                Persistencia.guardar(coop);
                break;
            case 5:
                console.log("Saving data and exiting menu...");
                Persistencia.guardar(coop);
                rl.close();
                break;
            default:
                console.log("Invalid option.");
        }
    }
}

main();