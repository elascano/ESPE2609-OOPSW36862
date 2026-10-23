import * as readline from 'readline';
import { Chicken } from './Chicken.js';
import { ChickenCoop } from './ChickenCoop.js';
import { ChickenJsonStorage } from './ChickenJsonStorage.js';
/**
 * @author Enrique Ayala, LogicLords,@ESPE
 */
const FILE_PATH = "chickens.json";
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});
function askQuestion(query) {
    return new Promise(resolve => rl.question(query, resolve));
}
async function readDate() {
    const year = parseInt(await askQuestion("Birth year (e.g. 2022): "));
    const month = parseInt(await askQuestion("Birth month (1-12): "));
    const day = parseInt(await askQuestion("Birth day (1-31): "));
    return new Date(year, month - 1, day);
}
async function readMolting() {
    const answer = (await askQuestion("Is it molting? (y/n): ")).trim().toLowerCase();
    return answer === 'y';
}
async function addChicken(coop, storage) {
    const id = parseInt(await askQuestion("Id: "));
    const name = await askQuestion("Name: ");
    const color = await askQuestion("Color: ");
    const bornOnDate = await readDate();
    const molting = await readMolting();
    const chicken = new Chicken(id, name, color, bornOnDate, molting);
    if (coop.add(chicken)) {
        storage.save(coop);
        console.log("Chicken added.");
    }
}
async function removeChicken(coop, storage) {
    const id = parseInt(await askQuestion("Id of the chicken to remove: "));
    if (coop.remove(id)) {
        storage.save(coop);
        console.log("Chicken removed.");
    }
}
async function updateChicken(coop, storage) {
    const id = parseInt(await askQuestion("Id of the chicken to update: "));
    const name = await askQuestion("New name: ");
    const color = await askQuestion("New color: ");
    const bornOnDate = await readDate();
    const molting = await readMolting();
    if (coop.update(id, name, color, bornOnDate, molting)) {
        storage.save(coop);
        console.log("Chicken updated.");
    }
}
async function main() {
    console.log("Enrique Ayala Chicken Farm App");
    console.log("==============================");
    const coop = new ChickenCoop(1);
    const storage = new ChickenJsonStorage(FILE_PATH);
    storage.load(coop);
    let option = -1;
    do {
        console.log("\n1. Add chicken");
        console.log("2. Remove chicken");
        console.log("3. Update chicken");
        console.log("4. Next chicken");
        console.log("5. Reset iteration");
        console.log("6. Show coop");
        console.log("0. Exit");
        const input = await askQuestion("Choose an option: ");
        option = parseInt(input);
        switch (option) {
            case 1:
                await addChicken(coop, storage);
                break;
            case 2:
                await removeChicken(coop, storage);
                break;
            case 3:
                await updateChicken(coop, storage);
                break;
            case 4:
                const nextChicken = coop.next();
                if (!nextChicken) {
                    console.log("No more chickens. Use option 5 to start again.");
                }
                else {
                    console.log(nextChicken.toString());
                    nextChicken.doStuff();
                }
                break;
            case 5:
                coop.resetIteration();
                console.log("Iteration reset.");
                break;
            case 6:
                console.log(coop.toString());
                break;
            case 0:
                console.log("Bye!");
                break;
            default:
                console.log("Invalid option.");
                break;
        }
    } while (option !== 0);
    rl.close();
}
main();
