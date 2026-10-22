import * as fs from "fs";
import * as readline from "readline";
import { Chicken } from "../model/Chicken";
import { ChickenCoop } from "../model/ChickenCoop";
import { Egg } from "../model/Egg";
import { Poop } from "../model/Poop";

const DATA_FILE = "chickens.json";

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

const askQuestion = (query: string): Promise<string> => {
  return new Promise(resolve => rl.question(query, resolve));
};

export class ChickenFarmApp {

  private static loadCoopFromJson(coop: ChickenCoop): void {
    if (!fs.existsSync(DATA_FILE)) {
      return;
    }

    try {
      const data = fs.readFileSync(DATA_FILE, "utf-8");
      const chickensData = JSON.parse(data);

      for (const item of chickensData) {
        const bornDate = new Date(item.bornOnDate);
        const chicken = new Chicken(
          item.id,
          item.name,
          item.color,
          bornDate,
          item.molting ?? false
        );
        chicken.setAgeInYears();
        coop.add(chicken);
      }
      console.log(`--> Data loaded successfully from ${DATA_FILE}`);
    } catch (e: any) {
      console.log(`Error loading data from JSON: ${e.message}`);
    }
  }

  private static saveCoopToJson(coop: ChickenCoop): void {
    const data: any[] = [];

    for (const chicken of coop.chickens) {
      if (chicken !== null) {
        data.push({
          id: chicken.id,
          name: chicken.name,
          color: chicken.color,
          bornOnDate: chicken.bornOnDate.toISOString().split('T')[0],
          ageInYears: chicken.ageInYears,
          molting: chicken.isMolting()
        });
      }
    }

    try {
      fs.writeFileSync(DATA_FILE, JSON.stringify(data, null, 4), "utf-8");
    } catch (e: any) {
      console.log(`Error saving data to JSON: ${e.message}`);
    }
  }

  private static printMenu(): void {
    console.log("---------- MENU ----------");
    console.log("1. Add chicken");
    console.log("2. Remove chicken");
    console.log("3. Next chicken");
    console.log("4. Reset iteration");
    console.log("5. Show coop");
    console.log("6. Run demo");
    console.log("0. Exit");
  }

  private static async readInt(prompt: string, minVal: number, maxVal: number): Promise<number> {
    while (true) {
      const input = await askQuestion(prompt);
      const val = parseInt(input.trim(), 10);
      if (!isNaN(val) && val >= minVal && val <= maxVal) {
        return val;
      }
      console.log(`Enter a number between ${minVal} and ${maxVal}.`);
    }
  }

  private static async readBoolean(prompt: string): Promise<boolean> {
    while (true) {
      const input = (await askQuestion(prompt)).trim().toLowerCase();
      if (["true", "yes", "y", "s", "si"].includes(input)) {
        return true;
      } else if (["false", "no", "n"].includes(input)) {
        return false;
      }
      console.log("Invalid input. Please enter 'yes'/'no' or 'true'/'false'.");
    }
  }


  private static async addChicken(coop: ChickenCoop): Promise<void> {
    const id = await ChickenFarmApp.readInt("Id: ", 0, Number.MAX_SAFE_INTEGER);
    const name = (await askQuestion("Name: ")).trim();
    const color = (await askQuestion("Color: ")).trim();

    console.log("Birth date:");
    const day = await ChickenFarmApp.readInt("  Day (1-31): ", 1, 31);
    const month = await ChickenFarmApp.readInt("  Month (1-12): ", 1, 12);
    const year = await ChickenFarmApp.readInt("  Year (e.g. 2022): ", 1900, 2100);

    
    const molting = await ChickenFarmApp.readBoolean("Is molting? (yes/no): ");

    const bornOnDate = new Date(year, month - 1, day);
    
    const chicken = new Chicken(id, name, color, bornOnDate, molting);
    chicken.setAgeInYears();

    if (coop.add(chicken)) {
        ChickenFarmApp.saveCoopToJson(coop);
    }
}

  private static async removeChicken(coop: ChickenCoop): Promise<void> {
    const id = await ChickenFarmApp.readInt("Id of the chicken to remove: ", 0, Number.MAX_SAFE_INTEGER);
    if (coop.remove(id)) {
      ChickenFarmApp.saveCoopToJson(coop);
    }
  }

  private static runDemo(): void {
    const chicken = new Chicken(1, "Lucy", "brown and withe", new Date(1970, 11, 17));
    const egg = new Egg(1, "M");
    const poop = new Poop(1, "brown", "S");
    const chicken1 = new Chicken(2, "Renata", "white", new Date(1970, 10, 17));

    console.log(`chicken --> ${chicken.toString()}`);
    console.log(`egg --> ${egg.toString()}`);
    console.log(`poop --> ${poop.toString()}`);
    console.log(`chicken 2--> ${chicken1.toString()}`);
    console.log(`The age of the chicken --> ${chicken.name} is ${chicken.computeAgeInYears()}`);

    chicken.doStuff();
    chicken1.doStuff();

    const chickens: (Chicken | null)[] = new Array(10).fill(null);
    chickens[0] = new Chicken(3, "Pepita", "black", new Date(2020, 1, 2));
    chickens[1] = chicken;
    chickens[2] = chicken1;

    for (let i = 0; i < 10; i++) {
      console.log(`chickens --> ${chickens[i] ? chickens[i]!.toString() : "null"}`);
    }

    for (let i = 0; i < chickens.length; i++) {
      const year = 2010 + i;
      chickens[i] = new Chicken(i, `chicken ${i + 1}`, "white ", new Date(year, 0, 1));
    }

    for (let i = 0; i < 10; i++) {
      console.log(`chickens --> ${chickens[i]!.toString()}`);
    }
  }

  public static async main(): Promise<void> {
    console.log("Demian Armas's Chicken Farm App");
    console.log("===============================");

    const coop = new ChickenCoop(1);
    ChickenFarmApp.loadCoopFromJson(coop);

    let option = -1;

    while (option !== 0) {
      ChickenFarmApp.printMenu();
      option = await ChickenFarmApp.readInt("Choose an option: ", 0, 6);
      console.log();

      switch (option) {
        case 1:
          await ChickenFarmApp.addChicken(coop);
          break;
        case 2:
          await ChickenFarmApp.removeChicken(coop);
          break;
        case 3:
          coop.next();
          break;
        case 4:
          coop.resetIteration();
          break;
        case 5:
          console.log(coop.toString());
          break;
        case 6:
          ChickenFarmApp.runDemo();
          break;
        case 0:
          ChickenFarmApp.saveCoopToJson(coop);
          console.log("Exiting and saving data...");
          rl.close();
          break;
      }
      console.log();
    }
  }
}

ChickenFarmApp.main();