import * as readline from "readline/promises";
import * as path from "path";
import { Mask } from "./models/Mask";
import { MaskRepository } from "./services/MaskRepository";

const FILE = path.join(__dirname, "..", "data", "masks.json");

async function main(): Promise<void> {
  const rl = readline.createInterface({ input: process.stdin, output: process.stdout });
  const repo = new MaskRepository(FILE);

  while (true) {
    console.log("\n=== MASK MANAGER ===");
    console.log("1. Add mask\n2. List masks in memory\n3. Save to JSON\n4. Read from JSON\n5. Exit");
    const option = (await rl.question("Choose an option: ")).trim();

    try {
      switch (option) {
        case "1": {
          const id = Number(await rl.question("Id (number): "));
          const name = await rl.question("Name: ");
          const material = await rl.question("Material: ");
          const price = Number(await rl.question("Price: "));
          if (Number.isNaN(id) || Number.isNaN(price)) throw new Error("Id and price must be numbers.");
          repo.add(new Mask(id, name, material, price));
          console.log("Mask added.");
          break;
        }
        case "2": {
          const list = repo.getAll();
          list.length ? list.forEach(m => console.log(m.toString())) : console.log("(empty)");
          break;
        }
        case "3":
          repo.save();
          console.log(`Saved to ${FILE}`);
          break;
        case "4": {
          const list = repo.load();
          console.log(`Read ${list.length} mask(s) from JSON:`);
          list.forEach(m => console.log(m.toString()));
          break;
        }
        case "5":
          rl.close();
          return;
        default:
          console.log("Invalid option.");
      }
    } catch (e) {
      console.log("Error:", (e as Error).message);
    }
  }
}

main();
