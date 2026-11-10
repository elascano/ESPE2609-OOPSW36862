import * as readline from "node:readline";
import { fileURLToPath } from "node:url";
import * as path from "node:path";
import { Vehicle } from "../model/Vehicle.ts";
import { JsonFileManager } from "../controller/JsonFileManager.ts";

const FILE = path.resolve(path.dirname(fileURLToPath(import.meta.url)), "../../data/vehicles.json");

type Prompt = (label: string) => Promise<string>;

function makePrompt(rl: readline.Interface): Prompt {
  const lines = rl[Symbol.asyncIterator]();
  return async (label: string) => {
    process.stdout.write(label);
    const { value, done } = await lines.next();
    if (done) {
      console.log();
      process.exit(0);
    }
    if (!process.stdin.isTTY) console.log(value);
    return value as string;
  };
}

async function askInt(ask: Prompt, label: string, min = 1): Promise<number> {
  while (true) {
    const n = Number((await ask(label)).trim());
    if (Number.isInteger(n) && n >= min) return n;
    console.log(`  Invalid value. Enter an integer >= ${min}.`);
  }
}

async function askText(ask: Prompt, label: string): Promise<string> {
  while (true) {
    const s = (await ask(label)).trim();
    if (s !== "") return s;
    console.log("  This field cannot be empty.");
  }
}

function printList(title: string, vehicles: Vehicle[]): void {
  console.log(`\n=== ${title} (${vehicles.length}) ===`);
  if (vehicles.length === 0) console.log("  (no vehicles)");
  vehicles.forEach((v, i) => console.log(`${i + 1}. ${v.toString()}`));
}

async function main(): Promise<void> {
  const rl = readline.createInterface({ input: process.stdin, terminal: false });
  const ask = makePrompt(rl);
  const manager = new JsonFileManager(FILE);
  const vehicles: Vehicle[] = manager.load();

  while (true) {
    console.log("\n--- VEHICLE MENU ---");
    console.log("1. Add vehicle");
    console.log("2. Save to JSON file");
    console.log("3. Read from JSON file");
    console.log("0. Exit");
    const option = (await ask("Option: ")).trim();

    if (option === "1") {
      const id = await askInt(ask, "ID (integer): ");
      if (vehicles.some((v) => v.getId() === id)) {
        console.log("  That ID already exists.");
        continue;
      }
      const brand = await askText(ask, "Brand: ");
      const model = await askText(ask, "Model: ");
      const year = await askInt(ask, "Year: ", 1886);
      vehicles.push(new Vehicle(id, brand, model, year));
      console.log("  Vehicle added to the list.");
    } else if (option === "2") {
      manager.save(vehicles);
      console.log(`  ${vehicles.length} vehicle(s) saved to ${path.relative(process.cwd(), FILE)}`);
    } else if (option === "3") {
      printList("Vehicles read from JSON file", manager.load());
    } else if (option === "0") {
      break;
    } else {
      console.log("  Invalid option.");
    }
  }
  rl.close();
}

main();
