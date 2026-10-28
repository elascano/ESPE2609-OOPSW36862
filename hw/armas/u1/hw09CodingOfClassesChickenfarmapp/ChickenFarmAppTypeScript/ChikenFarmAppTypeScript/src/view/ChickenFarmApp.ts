import * as readline from 'readline/promises';
import { stdin as input, stdout as output } from 'process';
import { Chicken } from '../model/Chicken';
import { ChickenCoop } from '../model/ChickenCoop';

export class ChickenFarmApp {
  private rl = readline.createInterface({ input, output });

  public async main(): Promise<void> {
    console.log("Demian Armas's Chicken Farm App");
    console.log('===============================');

    const coop = new ChickenCoop(1);
    let option: number;

    do {
      this.printMenu();
      option = await this.readInt('Choose an option: ', 0, 6);

      switch (option) {
        case 1:
          await this.addChicken(coop);
          break;
        case 2:
          await this.removeChicken(coop);
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
          this.runDemo();
          break;
        case 0:
          console.log('Goodbye!');
          break;
        default:
          console.log('Invalid option.');
          break;
      }
    } while (option !== 0);

    this.rl.close();
  }

  private printMenu(): void {
    console.log('---------- MENU ----------');
    console.log('1. Add chicken');
    console.log('2. Remove chicken');
    console.log('3. Next chicken');
    console.log('4. Reset iteration');
    console.log('5. Show coop');
    console.log('6. Run demo');
    console.log('0. Exit');
  }

  private async addChicken(coop: ChickenCoop): Promise<void> {
    const id = await this.readInt('Id: ', 0, Number.MAX_SAFE_INTEGER);
    const name = await this.readString('Name: ');
    const color = await this.readString('Color: ');

    console.log('Birth date:');
    const day = await this.readInt('  Day (1-31): ', 1, 31);
    const month = await this.readInt('  Month (1-12): ', 1, 12);
    const year = await this.readInt('  Year (e.g. 2022): ', 1900, 2100);

    const bornOnDate = new Date(year, month - 1, day);
    if (bornOnDate.getMonth() !== month - 1) {
      console.log('That date does not exist. The chicken was not added.');
      return;
    }

    const chicken = new Chicken(id, name, color, bornOnDate);
    chicken.setAgeInYears(chicken.computeAgeInYears());
    coop.add(chicken);
  }

  private async removeChicken(coop: ChickenCoop): Promise<void> {
    const id = await this.readInt('Id of the chicken to remove: ', 0, Number.MAX_SAFE_INTEGER);
    coop.remove(id);
  }

  private runDemo(): void {
    const chicken1 = new Chicken(1, 'Lucy', 'brown and withe', new Date(2023, 0, 1));
    const egg = Chicken.layAnEgg();
    const poop = Chicken.poop();

    console.log(`chicken --> ${chicken1}`);
    console.log(`egg --> ${egg}`);
    console.log(`poop --> ${poop}`);

    const chicken2 = new Chicken(2, 'Renata', 'white', new Date(2021, 4, 10));
    console.log(`chicken 2--> ${chicken2}`);
    console.log(`The age of the chicken ${chicken2.getName()} is ${chicken2.computeAgeInYears()}`);

    chicken1.doStuff(200.0);

    const chickens: Chicken[] = [
      new Chicken(3, 'Pepita', 'black', new Date(2022, 2, 15)),
      new Chicken(4, 'Lucy', 'white', new Date(2023, 7, 20)),
    ];

    console.log('chickens -->');
    for (const chicken of chickens) {
      console.log(`chicken ${chicken}`);
    }

    console.log('chickens -->');
    for (const chicken of chickens) {
      console.log(`The age of the chicken ${chicken.getName()} is ${chicken.computeAgeInYears()}`);
    }
  }

  private async readInt(prompt: string, min: number, max: number): Promise<number> {
    while (true) {
      const text = (await this.rl.question(prompt)).trim();
      const value = Number(text);
      if (text !== '' && Number.isInteger(value)) {
        if (value >= min && value <= max) {
          return value;
        }
        console.log(`Enter a number between ${min} and ${max}.`);
      } else {
        console.log('Invalid number. Try again.');
      }
    }
  }

  private async readString(prompt: string): Promise<string> {
    while (true) {
      const text = (await this.rl.question(prompt)).trim();
      if (text.length > 0) {
        return text;
      }
      console.log('Field cannot be empty.');
    }
  }
}

new ChickenFarmApp().main();