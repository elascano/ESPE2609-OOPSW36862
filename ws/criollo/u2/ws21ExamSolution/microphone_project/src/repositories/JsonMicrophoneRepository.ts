import * as fs from "fs";
import * as path from "path";
import { InvalidMicrophoneDataError, StorageError } from "../errors";
import { Microphone, MicrophoneData } from "../models/Microphone";

export class JsonMicrophoneRepository {
  constructor(private readonly filePath: string) {}

  saveAll(microphones: Microphone[]): void {
    try {
      fs.mkdirSync(path.dirname(this.filePath), { recursive: true });
      fs.writeFileSync(this.filePath, JSON.stringify(microphones, null, 4), "utf-8");
    } catch (error) {
      throw new StorageError(`Could not write ${this.filePath}: ${this.describe(error)}`);
    }
  }

  loadAll(): Microphone[] {
    if (!fs.existsSync(this.filePath)) {
      return [];
    }
    return this.buildMicrophones(this.readRecords());
  }

  private readRecords(): unknown {
    try {
      return JSON.parse(fs.readFileSync(this.filePath, "utf-8"));
    } catch (error) {
      throw new StorageError(`Could not read ${this.filePath}: ${this.describe(error)}`);
    }
  }

  private buildMicrophones(records: unknown): Microphone[] {
    if (!Array.isArray(records)) {
      throw new InvalidMicrophoneDataError("The JSON file must contain a list.");
    }
    return records.map((record: Partial<MicrophoneData>) => Microphone.fromData(record));
  }

  private describe(error: unknown): string {
    return error instanceof Error ? error.message : String(error);
  }
}
