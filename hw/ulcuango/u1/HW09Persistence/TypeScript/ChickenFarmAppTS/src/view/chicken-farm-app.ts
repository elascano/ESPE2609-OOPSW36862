import * as readline from 'readline';
import { JsonManager } from '../controller/json-manager';
import { Chicken } from '../model/chicken';
import { ChickenCoop } from '../model/chicken-coop';
import { ChickenFarmer } from '../model/chicken-farmer';

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function question(query: string): Promise<string> {
    return new Promise(resolve => rl.question(query, resolve));
}

function calculateAge(birthDate: Date): number {
    let birth = new Date(birthDate);
    let today = new Date();
    let age = today.getFullYear() - birth.getFullYear();
    let monthDiff = today.getMonth() - birth.getMonth();
    if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birth.getDate())) {
        age--;
    }
    return Math.max(0, age);
}

async function main() {
    console.log("Josselyn Ulcuango's Chicken Farm App");
    console.log("=====================================");

    let farmer = new ChickenFarmer("Josselyn");

    let loadedCoops = JsonManager.loadCoops();
    if (loadedCoops != null && loadedCoops.length > 0) {
        farmer.setCoops(loadedCoops);
    } else {
        let defaultCoop = new ChickenCoop(1);
        farmer.addCoop(defaultCoop);
    }

    let option = 0;
    do {
        console.log("\n--- CHICKEN FARM MENU ---");
        console.log("1. Add chickens");
        console.log("2. Show chickens");
        console.log("3. Remove chicken");
        console.log("4. Exit");
        
        let optionStr = await question("Select an option: ");
        option = parseInt(optionStr.trim());

        if (isNaN(option)) {
            console.log("Please enter a valid number.");
            continue;
        }

        switch (option) {
            case 1:
                let amountStr = await question("How many chickens do you want to add?: ");
                let amount = parseInt(amountStr.trim());
                if (isNaN(amount)) {
                    console.log("Please enter a valid number.");
                    break;
                }

                let currentCoop = farmer.getCoops()[0];

                for (let i = 0; i < amount; i++) {
                    console.log("\n--- Chicken #" + (i + 1) + " Data ---");

                    let generatedId = 1;
                    for (let coop of farmer.getCoops()) {
                        let coopChickens = coop.getChickens();
                        if (coopChickens) {
                            for (let c of coopChickens) {
                                if (c.getId() >= generatedId) {
                                    generatedId = c.getId() + 1;
                                }
                            }
                        }
                    }
                    console.log("Assigned ID: " + generatedId);

                    let name = await question("Enter Name: ");
                    let color = await question("Enter Color: ");

                    let bornOnDate: Date | null = null;
                    while (bornOnDate == null) {
                        let dateStr = await question("Enter Birth Date (yyyy-MM-dd): ");
                        let parsedDate = new Date(dateStr.trim());
                        if (!isNaN(parsedDate.getTime())) {
                            bornOnDate = parsedDate;
                        } else {
                            console.log("Invalid format. Please use yyyy-MM-dd.");
                        }
                    }

                    let age = calculateAge(bornOnDate);

                    let moltingStr = await question("Is molting? (true/false): ");
                    let isMolting = moltingStr.trim().toLowerCase() === 'true';

                    let chicken = new Chicken(generatedId, name.trim(), color.trim(), bornOnDate, age, isMolting);
                    currentCoop.addChicken(chicken);

                    console.log("Chicken '" + name.trim() + "' added successfully!");
                }

                JsonManager.saveCoops(farmer.getCoops());
                console.log("\nAll chickens have been registered successfully!");
                break;

            case 2:
                console.log("\n--- List of Chickens in the Farm ---");
                let hasChickens = false;
                for (let coop of farmer.getCoops()) {
                    let coopChickens = coop.getChickens();
                    if (coopChickens) {
                        for (let chicken of coopChickens) {
                            console.log(chicken.toString());
                            hasChickens = true;
                        }
                    }
                }
                if (!hasChickens) {
                    console.log("No chickens registered yet.");
                }
                break;

            case 3:
                let targetIdStr = await question("Enter the ID of the chicken you want to remove: ");
                let targetId = parseInt(targetIdStr.trim());
                if (isNaN(targetId)) {
                    console.log("Please enter a valid ID number.");
                    break;
                }

                let removed = false;
                for (let coop of farmer.getCoops()) {
                    let chickens = coop.getChickens();
                    if (chickens) {
                        for (let j = 0; j < chickens.length; j++) {
                            if (chickens[j].getId() === targetId) {
                                chickens.splice(j, 1);
                                removed = true;
                                break;
                            }
                        }
                    }
                    if (removed) {
                        break;
                    }
                }

                if (removed) {
                    JsonManager.saveCoops(farmer.getCoops());
                    console.log("Chicken with ID " + targetId + " has been successfully removed.");
                } else {
                    console.log("Chicken with ID " + targetId + " was not found.");
                }
                break;

            case 4:
                console.log("\nExiting Chicken Farm App. Goodbye, Josselyn!");
                break;

            default:
                console.log("Invalid option. Please choose between 1 and 4.");
                break;
        }
    } while (option !== 4);

    rl.close();
}

main();