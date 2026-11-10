import * as fs from "fs";
import * as path from "path";
import { Mask } from "../models/Mask";

/** Keeps masks in an array (ArrayList equivalent) and persists them to a JSON file. */
export class MaskRepository {
  private masks: Mask[] = [];

  constructor(private readonly filePath: string) {}

  add(mask: Mask): void {
    if (this.masks.some(m => m.id === mask.id)) {
      throw new Error(`A mask with id ${mask.id} already exists.`);
    }
    this.masks.push(mask);
  }

  getAll(): Mask[] {
    return [...this.masks];
  }

  /** Writes the whole array to the JSON file. */
  save(): void {
    fs.mkdirSync(path.dirname(this.filePath), { recursive: true });
    fs.writeFileSync(this.filePath, JSON.stringify(this.masks, null, 2), "utf-8");
  }

  /** Reads the JSON file and rebuilds the array of Mask objects. */
  load(): Mask[] {
    if (!fs.existsSync(this.filePath)) {
      throw new Error(`File not found: ${this.filePath}`);
    }
    const raw = JSON.parse(fs.readFileSync(this.filePath, "utf-8")) as any[];
    this.masks = raw.map(Mask.fromJSON);
    return this.getAll();
  }
}
