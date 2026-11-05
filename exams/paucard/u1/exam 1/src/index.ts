import * as path from "path";
import * as readline from "readline/promises";
import { Team } from "./models/Team";
import { TeamRepository } from "./repositories/TeamRepository";

const JSON_FILE = path.join(__dirname, "..", "data", "teams.json");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const repository = new TeamRepository(JSON_FILE);
async function askNumber(question: string): Promise<number> {
  while (true) {
    const answer = (await rl.question(question)).trim();
    const value = Number(answer);
    if (answer !== "" && Number.isInteger(value) && value > 0) {
      return value;
    }
    console.log("  Invalid number. Please enter a positive integer.");
  }
}

async function askText(question: string): Promise<string> {
  while (true) {
    const answer = (await rl.question(question)).trim();
    if (answer !== "") {
      return answer;
    }
    console.log("  This field cannot be empty.");
  }
}

async function createTeam(): Promise<void> {
  console.log("\n--- Create a new Team ---");
  const id = await askNumber("Id (mandatory): ");
  if (repository.findById(id)) {
    console.log(`  A team with id ${id} already exists.\n`);
    return;
  }
  const name = await askText("Name: ");
  const city = await askText("City: ");
  const foundedYear = await askNumber("Founded year: ");

  repository.add(new Team(id, name, city, foundedYear));
  console.log("  Team saved to the JSON file.\n");
}

function listTeams(): void {
  console.log("\n--- Teams read from the JSON file ---");
  const teams = repository.load();
  if (teams.length === 0) {
    console.log("  There are no teams stored yet.\n");
    return;
  }
  teams.forEach((team) => console.log("  " + team.toString()));
  console.log(`  Total: ${teams.length} team(s)\n`);
}

async function main(): Promise<void> {
  repository.load();
  let option = "";

  while (option !== "3") {
    console.log("===== TEAM MANAGER =====");
    console.log("1. Create a team");
    console.log("3. Exit");
    option = (await rl.question("Choose an option: ")).trim();

    switch (option) {
      case "1":
        await createTeam();
        break;
      case "2":
        listTeams();
        break;
      case "3":
        console.log("exiting...");
        break;
      default:
        console.log("  Invalid option.\n");
    }
  }
  rl.close();
}

main();
