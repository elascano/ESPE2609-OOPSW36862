import * as readline from 'readline/promises';
import { stdin as input, stdout as output } from 'process';
import { ChickenCoop } from '../model/ChickenCoop';
import { Chicken } from '../model/Chicken';

export class ChickenFarmApp {
  private rl = readline.createInterface({ input, output });

  public async start(): Promise<void> {
    const coop = new ChickenCoop(101);
    let option = 0;

    console.log('=========================================');
    console.log("  David Clavon's Chicken Farm App (TS)");
    console.log('=========================================');

    do {
      this.showMenu(coop.getChickenCoopId());
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
          coop.resetIteration();
          console.log('\nCoop iterator reset to the first position.');
          break;
        case 6:
          console.log('\n--- CHICKEN COOP STATUS ---');
          console.log(coop.toString());
          break;
        case 7:
          console.log('\nExiting Chicken Farm App...');
          break;
        default:
          console.log('\nInvalid option. Please try again.');
          break;
      }
    } while (option !== 7);

    this.rl.close();
  }

  private showMenu(coopId: number): void {
    console.log(`\n--- CHICKEN COOP MENU (ID: ${coopId}) ---`);
    console.log('1. Add a chicken');
    console.log('2. Remove a chicken by ID');
    console.log('3. View all chickens');
    console.log('4. View next chicken');
    console.log('5. Reset iteration reader');
    console.log('6. Display full coop status');
    console.log('7. Exit');
  }

  private async addChickenToCoop(coop: ChickenCoop): Promise<void> {
    console.log('\n--- REGISTER NEW CHICKEN ---');
    const id = await this.readInt('Enter chicken ID: ');
    const name = await this.readString('Enter Name: ');
    const color = await this.readString('Enter Color: ');
    const isMolting = await this.readBoolean('Is molting? (yes/no or true/false): ');

    const newChicken = new Chicken(id, name, color, new Date(), isMolting);
    if (coop.add(newChicken)) {
      console.log(`Chicken ${name} successfully added to the coop.`);
    } else {
      console.log('Could not add chicken. The coop is full or the object is invalid.');
    }
  }

  private async removeChickenFromCoop(coop: ChickenCoop): Promise<void> {
    console.log('\n--- REMOVE CHICKEN ---');
    const removeId = await this.readInt('Enter chicken ID to remove: ');
    if (coop.remove(removeId)) {
      console.log(`Chicken with ID ${removeId} was successfully removed.`);
    } else {
      console.log(`No chicken found with ID ${removeId}.`);
    }
  }

  private showAllChickens(coop: ChickenCoop): void {
    console.log('\n--- DISPLAYING ALL REGISTERED CHICKENS ---');
    coop.resetIteration();
    let current = coop.next();
    if (current === null) {
      console.log('The coop is empty.');
    } else {
      while (current !== null) {
        console.log('-> ' + current.toString());
        current = coop.next();
      }
    }
  }

  private showNextChicken(coop: ChickenCoop): void {
    console.log('\n--- NAVIGATE TO NEXT CHICKEN ---');
    const nextChicken = coop.next();
    if (nextChicken !== null) {
      const position = coop.getCurrentPosition();
      const total = coop.getCount();
      console.log(`Viewing Chicken ${position} of ${total}:`);
      console.log('-> ' + nextChicken.toString());
    } else {
      console.log('Reached the end of the list (or you need to reset the iteration).');
    }
  }

  private async readInt(prompt: string): Promise<number> {
    while (true) {
      const inputVal = await this.rl.question(prompt);
      const parsed = parseInt(inputVal.trim(), 10);
      if (!isNaN(parsed)) {
        return parsed;
      }
      console.log('Error: Please enter a valid integer number.');
    }
  }

  private async readString(prompt: string): Promise<string> {
    while (true) {
      const inputVal = (await this.rl.question(prompt)).trim();
      if (inputVal.length > 0) {
        return inputVal;
      }
      console.log('Error: Field cannot be empty.');
    }
  }

  private async readBoolean(prompt: string): Promise<boolean> {
    while (true) {
      const inputVal = (await this.rl.question(prompt)).trim().toLowerCase();
      if (['true', 'y', 'yes'].includes(inputVal)) {
        return true;
      } else if (['false', 'n', 'no'].includes(inputVal)) {
        return false;
      } else {
        console.log("Error: Please enter a valid response ('yes'/'no' or 'true'/'false').");
      }
    }
  }
}


new ChickenFarmApp().start();