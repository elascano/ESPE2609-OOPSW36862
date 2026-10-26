import { Chicken } from './Chicken';
import { ChickenCoop } from './ChickenCoop';
import { Egg } from './Egg';
import { Poop } from './Poop';
import * as readline from 'readline';

export class ChickenFarmApp {
  private static promptInput(query: string): Promise<string> {
    const rl = readline.createInterface({
      input: process.stdin,
      output: process.stdout
    });
    return new Promise((resolve) => {
      rl.question(query, (answer: string) => {
        rl.close();
        resolve(answer.trim());
      });
    });
  }

  private static async readInt(prompt: string): Promise<number> {
    while (true) {
      const input = await this.promptInput(prompt);
      const parsed = parseInt(input, 10);
      if (!isNaN(parsed)) {
        return parsed;
      }
      console.log('Error: Enter a valid integer.');
    }
  }

  private static async readString(prompt: string): Promise<string> {
    while (true) {
      const input = await this.promptInput(prompt);
      if (input.length > 0) {
        return input;
      }
      console.log('Error: Value cannot be empty.');
    }
  }

  private static async readBoolean(prompt: string): Promise<boolean> {
    while (true) {
      const input = (await this.promptInput(prompt)).toLowerCase();
      if (input === 'true' || input === 'y' || input === 'yes') {
        return true;
      } else if (input === 'false' || input === 'n' || input === 'no') {
        return false;
      }
      console.log("Error: Answer 'yes' or 'no'.");
    }
  }

  private static showMenu(): void {
    console.log('\n---MENU---');
    console.log('1. Register new chicken');
    console.log('2. Delete chicken by ID');
    console.log('3. Display chicken');
    console.log('4. Inspect next chicken');
    console.log('5. View coop status');
    console.log('6. Exit');
  }

  private static async addChickenToCoop(coop: ChickenCoop): Promise<void> {
    console.log('\n--- REGISTER CHICKEN ---');
    const id = await this.readInt('Enter ID: ');
    const name = await this.readString('Enter Name: ');
    const color = await this.readString('Enter Color: ');
    const isMolting = await this.readBoolean('Is molting? (yes/no): ');

    const newChicken = new Chicken(id, name, color, new Date(), 0, isMolting);

    coop.add(newChicken);
    console.log('Chicken successfully registered.');
  }

  private static async removeChickenFromCoop(coop: ChickenCoop): Promise<void> {
    console.log('\n--- DELETE CHICKEN ---');
    const removeId = await this.readInt('Enter ID to delete: ');
    coop.remove(removeId);
    console.log('Delete request completed.');
  }

  private static showAllChickens(coop: ChickenCoop): void {
    console.log('\n--- REGISTERED CHICKENS ---');
    coop.resetIteration();
    let current = coop.next();
    if (current === null) {
      console.log('No chickens found.');
    } else {
      while (current !== null) {
        console.log(`-> ${current.toString()}`);
        current = coop.next();
      }
    }
  }

  private static showNextChicken(coop: ChickenCoop): void {
    console.log('\n--- NEXT ENTRY ---');
    const nextChicken = coop.next();
    if (nextChicken !== null) {
      console.log(`-> ${nextChicken.toString()}`);
    } else {
      console.log('End of list reached.');
    }
  }

  public static async main(): Promise<void> {
    console.log("Leslye Cuichan's Chicken Farm App");
    console.log('================================');

    let chicken = new Chicken(1, 'Lucy', 'brown and white', new Date(2000, 9, 20), 0, true);
    let egg = new Egg(1, 'M');
    let poop = new Poop(1, 'brown', 'S');
    let chicken1 = new Chicken(2, 'Lucy', 'white', new Date(), 0, false);

    console.log(`chicken --> ${chicken.toString()}`);
    console.log(`egg --> ${egg.toString()}`);
    console.log(`poop --> ${poop.toString()}`);
    console.log(`chicken 2 --> ${chicken1.toString()}`);

    let localAge = chicken.computeAgeInYears();
    console.log(`the age of the chicken --> ${chicken.name}, is --> ${localAge}`);
    console.log(`the age of the chicken ---> ${chicken.name}, is ---> ${chicken.ageInYears}`);

    chicken.doStuff();

    let poop2 = new Poop(2, 'green', 'N');
    console.log(`poop--> ${poop2.toString()}`);

    let egg2 = new Egg(2, 'O');
    console.log(`egg--> ${egg2.toString()}`);

    let coop = new ChickenCoop(1);

    for (let i = 0; i < 10; i++) {
      console.log(`chickens --> ${coop.next()}`);
    }

    let chickens: (Chicken | null)[] = new Array(10);

    let ageForTheLoop = 0;
    for (let i = 0; i < chickens.length; i++) {
      ageForTheLoop = 2010 + i;
      chickens[i] = new Chicken(i, `chicken ${i + 1}`, 'white ', new Date(ageForTheLoop, 3, 3), 0, true);
      coop.add(chickens[i]!);
    }

    for (let i = 0; i < 10; i++) {
      console.log(`chickens --> ${chickens[i]?.toString()}`);
    }

    let option = 0;

    do {
      this.showMenu();
      option = await this.readInt('Select an option: ');

      switch (option) {
        case 1:
          await this.addChickenToCoop(coop);
          break;
        case 2:
          await this.removeChickenFromCoop(coop);
          break;
        case 3:
          this.showAllChickens(coop);
          break;
        case 4:
          this.showNextChicken(coop);
          break;
        case 5:
          console.log('\n--- CURRENT COOP DETAILS ---');
          console.log(coop.toString());
          break;
        case 6:
          console.log('\nClosing application...');
          break;
        default:
          console.log('\nInvalid option. Try again.');
          break;
      }
    } while (option !== 6);
  }
}
ChickenFarmApp.main();