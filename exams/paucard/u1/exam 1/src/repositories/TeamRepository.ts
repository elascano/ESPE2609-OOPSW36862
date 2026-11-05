declare const require: any;

const fs = require("fs");
const path = require("path");
import { Team, TeamData } from "../models/Team";

export class TeamRepository {
  private teams: Team[] = [];

  constructor(private readonly filePath: string) {}

  load(): Team[] {
    if (!fs.existsSync(this.filePath)) {
      this.teams = [];
      return this.teams;
    }

    const content = fs.readFileSync(this.filePath, "utf-8").trim();
    if (content === "") {
      this.teams = [];
      return this.teams;
    }

    try {
      const rawList: TeamData[] = JSON.parse(content);
      this.teams = rawList.map((item) => Team.fromJSON(item));
    } catch (error) {
      console.error("The JSON file is corrupted or has an invalid format.");
      this.teams = [];
    }
    return this.teams;
  }
  save(): void {
    fs.mkdirSync(path.dirname(this.filePath), { recursive: true });
    fs.writeFileSync(
      this.filePath,
      JSON.stringify(this.teams, null, 2),
      "utf-8"
    );
  }

  add(team: Team): void {
    if (this.findById(team.id)) {
      throw new Error(`A team with id ${team.id} already exists.`);
    }
    this.teams.push(team);
    this.save();
  }

  findById(id: number): Team | undefined {
    return this.teams.find((t) => t.id === id);
  }

  getAll(): Team[] {
    return this.teams;
  }
}
