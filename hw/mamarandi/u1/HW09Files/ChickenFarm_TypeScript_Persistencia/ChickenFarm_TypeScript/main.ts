import { createInterface } from "node:readline/promises";
import { stdin as input, stdout as output } from "node:process";
import { Chicken } from "./Chicken";
import { ChickenCoop } from "./ChickenCoop";
import { Egg } from "./Egg";
import { Poop } from "./Poop";
import { loadChickens, saveChickens } from "./storage";

async function main(): Promise<void> {
    const rl = createInterface({ input, output });
    try {
        console.log("Marcos Mamarandi Chicken Farm App");
        console.log("=================================");
        const chickens: (Chicken | null)[] = Array(10).fill(null);
        const chicken = new Chicken(2, "Maruja", "white", new Date(1970, 11, 17), false);
        chicken.setAgeInYears();
        const egg = new Egg(1, "M");
        console.log("my chicken is --> " + chicken);
        console.log("egg --> " + egg);
        console.log("egg size --> " + egg.getSize());
        const poop = new Poop(1, "brown", "S");
        console.log("poop --> " + poop);
        const chicken1 = new Chicken(1, "Lucy", "brown and white", new Date(1971, 0, 17), true);
        console.log("chicken 1 --> " + chicken1);
        chicken1.setAgeInYears();
        console.log(`the age of the chicken --> ${chicken.getName()}, is --> ${chicken.computeAgeInYears()}`);
        chicken.doStuff();
        chickens[0] = new Chicken(3, "Pepita", "black", new Date(2020, 2, 2), true);
        chickens[1] = chicken;
        for (const c of chickens) console.log("chickens --> " + c);
        for (let i = 0; i < 10; i++) {
            chickens[i] = new Chicken(i, `chiken${i + 1}`, "white", new Date(2010 + i, 3, 3), true);
            chickens[i]!.setAgeInYears();
        }
        for (const c of chickens) console.log("chikens --> " + c);

        const saved = loadChickens();
        const coopChickens: (Chicken | null)[] = [...saved, ...Array(10 - saved.length).fill(null)];
        console.log(`Loaded ${saved.length} chickens from file`);
        const chickenCoop = new ChickenCoop(1, coopChickens);
        while (true) {
            console.log("\n===== CHICKEN COOP MENU =====");
            console.log("1. Add a chicken");
            console.log("2. Remove a chicken");
            console.log("3. Show chickens");
            console.log("4. Exit");
            const option = (await rl.question("Choose an option: ")).trim();
            switch (option) {
                case "1": {
                    let id: number;
                    do {
                        id = Math.floor(Math.random() * 9000) + 1000;
                    } while (coopChickens.some(c => c !== null && c.getId() === id));
                    const name = await rl.question("Enter chicken name: ");
                    const color = await rl.question("Enter chicken color: ");
                    const year = Number(await rl.question("Enter birth year: "));
                    const month = Number(await rl.question("Enter birth month (1-12): "));
                    const day = Number(await rl.question("Enter birth day: "));
                    const moltingText = (await rl.question("Is the chicken molting? (true/false): ")).trim().toLowerCase();
                    const birthDate = new Date(year, month - 1, day);
                    if (!Number.isInteger(year) || !Number.isInteger(month) || !Number.isInteger(day) ||
                        year < 100 || month < 1 || month > 12 || day < 1 || day > 31 ||
                        birthDate.getFullYear() !== year || birthDate.getMonth() !== month - 1 ||
                        birthDate.getDate() !== day || birthDate > new Date()) {
                        console.log("Invalid birth date");
                        break;
                    }
                    if (moltingText !== "true" && moltingText !== "false") {
                        console.log("Please enter true or false");
                        break;
                    }
                    const newChicken = new Chicken(id, name, color, birthDate, moltingText === "true");
                    newChicken.setAgeInYears();
                    chickenCoop.add(newChicken);
                    if (coopChickens.includes(newChicken)) {
                        saveChickens(coopChickens);
                        console.log("Generated chicken ID: " + id);
                    }
                    break;
                }
                case "2": {
                    const chickenId = Number(await rl.question("Enter the ID of the chicken to remove: "));
                    if (Number.isInteger(chickenId)) { chickenCoop.remove(chickenId); saveChickens(coopChickens); }
                    else console.log("Invalid ID");
                    break;
                }
                case "3": {
                    chickenCoop.resetIteration();
                    let nextChicken: Chicken | null;
                    while ((nextChicken = chickenCoop.next()) !== null) console.log(nextChicken.toString());
                    break;
                }
                case "4":
                    console.log("Exiting Chicken Coop...");
                    return;
                default:
                    console.log("Invalid option");
            }
        }
    } finally {
        rl.close();
    }
}

main().catch(console.error);
