import * as readline from "readline/promises";
import { MicrophoneError } from "../errors";
import { Microphone, VALID_CONNECTIONS, VALID_TYPES } from "../models/Microphone";
import { MicrophoneService } from "../services/MicrophoneService";

export class ConsoleMenu {
  private readonly input = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
  });

  constructor(private readonly service: MicrophoneService) {}

  async run(): Promise<void> {
    this.loadExistingData();
    let running = true;
    while (running) {
      this.printMenu();
      const choice = (await this.input.question("Choose an option: ")).trim();
      running = await this.handleChoice(choice);
    }
    this.input.close();
  }

  private async handleChoice(choice: string): Promise<boolean> {
    switch (choice) {
      case "1":
        await this.createMicrophone();
        return true;
      case "2":
        this.showMicrophones();
        return true;
      case "3":
        console.log("Goodbye!");
        return false;
      default:
        console.log("Invalid option. Try again.");
        return true;
    }
  }

  private printMenu(): void {
    console.log("\n=== MICROPHONE MANAGER ===");
    console.log("1. Create a microphone");
    console.log("2. Read microphones from JSON file");
    console.log("3. Exit");
  }

  private loadExistingData(): void {
    try {
      this.service.loadFromFile();
    } catch (error) {
      this.printError(error, "Warning");
    }
  }

  private async createMicrophone(): Promise<void> {
    try {
      const microphone = await this.askMicrophoneData();
      this.service.addMicrophone(microphone);
      console.log(`Saved: ${microphone}`);
    } catch (error) {
      this.printError(error, "Error");
    }
  }

  private async askMicrophoneData(): Promise<Microphone> {
    const id = Number(await this.input.question("Id (positive integer): "));
    const brand = await this.input.question("Brand: ");
    const type = await this.input.question(`Type (${VALID_TYPES.join("/")}): `);
    const connection = await this.input.question(`Connection (${VALID_CONNECTIONS.join("/")}): `);
    return new Microphone(id, brand, type, connection);
  }

  private showMicrophones(): void {
    try {
      const microphones = this.service.loadFromFile();
      if (microphones.length === 0) {
        console.log("The JSON file has no microphones yet.");
        return;
      }
      console.log("\n--- Microphones read from JSON ---");
      microphones.forEach((microphone) => console.log(microphone.toString()));
    } catch (error) {
      this.printError(error, "Error");
    }
  }

  private printError(error: unknown, label: string): void {
    const message = error instanceof MicrophoneError ? error.message : "Unexpected error.";
    console.log(`${label}: ${message}`);
  }
}
