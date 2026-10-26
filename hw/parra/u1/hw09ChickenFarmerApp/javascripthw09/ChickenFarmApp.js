const readline = require("readline/promises");
const Chicken = require("./Chicken");
const ChickenCoop = require("./ChickenCoop");
const Egg = require("./Egg");
const Poop = require("./Poop");

const rl = readline.createInterface({ input: process.stdin, output: process.stdout });

function printMenu() {
  console.log("---------- MENU ----------");
  console.log("1. Add chicken");
  console.log("2. Remove chicken");
  console.log("3. Next chicken");
  console.log("4. Reset iteration");
  console.log("5. Show coop");
  console.log("6. Run demo");
  console.log("0. Exit");
}

async function readInt(message, min, max) {
  while (true) {
    const text = (await rl.question(message)).trim();
    if (!/^-?\d+$/.test(text)) {
      console.log("Invalid number. Try again.");
      continue;
    }
    const value = parseInt(text, 10);
    if (value >= min && value <= max) return value;
    console.log(`Enter a number between ${min} and ${max}.`);
  }
}

async function addChicken(coop) {
  const id = await readInt("Id: ", 0, 2147483647);
  const name = (await rl.question("Name: ")).trim();
  const color = (await rl.question("Color: ")).trim();

  console.log("Birth date:");
  let bornOnDate;
  while (true) {
    const day = await readInt("  Day (1-31): ", 1, 31);
    const month = await readInt("  Month (1-12): ", 1, 12);
    const year = await readInt("  Year (e.g. 2022): ", 1900, 2100);
    const candidate = new Date(year, month - 1, day);
    if (candidate.getMonth() === month - 1 && candidate.getDate() === day) {
      bornOnDate = candidate;
      break;
    }
    console.log("That date does not exist. Try again.");
  }

  const chicken = new Chicken(id, name, color, bornOnDate);
  chicken.setAgeInYears();
  coop.add(chicken);
}

async function removeChicken(coop) {
  const id = await readInt("Id of the chicken to remove: ", 0, 2147483647);
  coop.remove(id);
}

function runDemo() {
  const chicken = new Chicken(1, "Lucy", "brown and white", new Date(1970, 11, 17));
  chicken.setAgeInYears();
  const egg = new Egg(1, "M");
  const poop = new Poop(1, "brown", "S");
  const chicken1 = new Chicken(2, "Renata", "white", new Date(1970, 10, 17));
  chicken1.setAgeInYears();

  console.log("chicken -->" + chicken);
  console.log("egg -->" + egg);
  console.log("poop -->" + poop);
  console.log("chicken 2-->" + chicken1);
  console.log(`The age of the chicken --> ${chicken.name} is ${chicken.computeAgeInYears()}`);

  chicken.doStuff();
  chicken1.doStuff();

  const chickens = new Array(10).fill(null);
  chickens[0] = new Chicken(3, "Pepita", "black", new Date(2020, 1, 2));
  chickens[1] = chicken;
  chickens[2] = chicken1;

  for (let i = 0; i < 10; i++) {
    console.log("chickens -->" + chickens[i]);
  }

  for (let i = 0; i < chickens.length; i++) {
    const ageForTheLoop = 2010 + i;
    chickens[i] = new Chicken(i, `chicken ${i + 1}`, "white ", new Date(ageForTheLoop, 0, 1));
    chickens[i].setAgeInYears();
  }

  for (let i = 0; i < 10; i++) {
    console.log("chickens --> " + chickens[i]);
  }
}

async function main() {
  console.log("Carlos Parra's Chicken Farm App");
  console.log("===============================");

  const coop = new ChickenCoop(1);
  let option;

  do {
    printMenu();
    option = await readInt("Choose an option: ", 0, 6);
    console.log();

    switch (option) {
      case 1:
        await addChicken(coop);
        break;
      case 2:
        await removeChicken(coop);
        break;
      case 3:
        coop.next();
        break;
      case 4:
        coop.resetIteration();
        break;
      case 5:
        console.log(String(coop));
        break;
      case 6:
        runDemo();
        break;
      case 0:
        console.log("Bye!");
        break;
    }
    console.log();
  } while (option !== 0);

  rl.close();
}

main();
