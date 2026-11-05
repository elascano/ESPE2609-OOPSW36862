import { Cathedral } from "../model/Cathedral";
import { CathedralRepository } from "../repository/CathedralRepository";
import { ConsoleInput } from "./ConsoleInput";

enum MenuOption {
  Register = "1",
  ShowFromFile = "2",
  Exit = "0",
}

export class CathedralMenu {
  constructor(
    private readonly repository: CathedralRepository,
    private readonly input: ConsoleInput
  ) {}

  async run(): Promise<void> {
    let isRunning = true;
    while (isRunning) {
      this.printOptions();
      const option = await this.input.askText("Choose an option: ");
      isRunning = await this.handle(option);
    }
    this.input.close();
  }

  private printOptions(): void {
    console.log("\n=== CATHEDRAL MANAGER ===");
    console.log("1. Register a cathedral");
    console.log("2. Read cathedrals from JSON file");
    console.log("0. Exit");
  }

  private async handle(option: string): Promise<boolean> {
    switch (option) {
      case MenuOption.Register:
        await this.registerCathedral();
        return true;
      case MenuOption.ShowFromFile:
        this.showCathedralsFromFile();
        return true;
      case MenuOption.Exit:
        console.log("Goodbye!");
        return false;
      default:
        console.log("Invalid option.");
        return true;
    }
  }

  private async registerCathedral(): Promise<void> {
    const id = await this.input.askPositiveInteger("Id: ");
    const name = await this.input.askText("Name: ");
    const location = await this.input.askText("Location: ");
    const city = await this.input.askText("City: ");

    try {
      this.repository.add(new Cathedral(id, name, location, city));
      console.log("Cathedral saved to the JSON file.");
    } catch (error) {
      console.log((error as Error).message);
    }
  }

  private showCathedralsFromFile(): void {
    const cathedrals = this.repository.loadFromFile();
    if (cathedrals.length === 0) {
      console.log("The JSON file has no cathedrals yet.");
      return;
    }
    console.log("\n--- Cathedrals read from JSON file ---");
    cathedrals.forEach((cathedral) => console.log(cathedral.describe()));
  }
}
