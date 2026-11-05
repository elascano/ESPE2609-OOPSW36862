import * as fs from "node:fs";
import * as path from "node:path";
import { Vehicle } from "../model/Vehicle.ts";

export class JsonFileManager {
  private filePath: string;

  constructor(filePath: string) {
    this.filePath = filePath;
  }

  save(vehicles: Vehicle[]): void {
    fs.mkdirSync(path.dirname(this.filePath), { recursive: true });
    fs.writeFileSync(this.filePath, JSON.stringify(vehicles, null, 2), "utf-8");
  }

  load(): Vehicle[] {
    if (!fs.existsSync(this.filePath)) return [];
    const text = fs.readFileSync(this.filePath, "utf-8").trim();
    if (text === "") return [];
    const raw = JSON.parse(text) as Array<{ id: number; brand: string; model: string; year: number }>;
    return raw.map((o) => Vehicle.fromJSON(o));
  }
}
