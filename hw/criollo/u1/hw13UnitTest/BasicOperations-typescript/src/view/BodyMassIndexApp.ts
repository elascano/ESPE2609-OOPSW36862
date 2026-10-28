import { createInterface } from "node:readline";
import { stdin, stdout } from "node:process";
import { BodyMassIndex } from "../model/BodyMassIndex.js";

type LineReader = AsyncIterator<string>;

/** Keeps asking until the user types a valid number (accepts decimal comma). */
async function readNumber(lines: LineReader, prompt: string): Promise<number | null> {
  for (;;) {
    stdout.write(prompt);
    const { value, done } = await lines.next();
    if (done) return null; // stdin closed
    const raw = value.trim().replace(",", ".");
    const parsed = raw === "" ? Number.NaN : Number(raw);
    if (!Number.isNaN(parsed)) return parsed;
    console.log("Error --> Please enter a valid number");
  }
}

async function main(): Promise<void> {
  console.log("Body Mass Index - BMI");

  const bodyMassIndex = new BodyMassIndex();
  // The async iterator works both interactively and with piped input.
  const rl = createInterface({ input: stdin });
  const lines = rl[Symbol.asyncIterator]();

  try {
    const weightKg = await readNumber(lines, "Enter the weight in kg: ");
    const heightM = weightKg === null ? null : await readNumber(lines, "Enter the height in m: ");
    if (weightKg === null || heightM === null) return;

    try {
      const bmi = bodyMassIndex.calculateBmi(weightKg, heightM);
      console.log(`Your BMI is --> ${bmi}`);
      console.log(`Category --> ${bodyMassIndex.classify(bmi)}`);
    } catch (error) {
      if (error instanceof RangeError) {
        console.log(`Error --> ${error.message}`);
      } else {
        throw error;
      }
    }
  } finally {
    rl.close();
  }
}

main();
