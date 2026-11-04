import * as fs from "fs";
import * as path from "path";
import { Cathedral } from "../model/Cathedral";

const JSON_INDENTATION = 2;

interface CathedralJson {
  id: number;
  name: string;
  location: string;
  city: string;
}

export class JsonFileStorage {
  constructor(private readonly filePath: string) {}

  save(cathedrals: Cathedral[]): void {
    const cathedralsJson: CathedralJson[] = cathedrals.map((cathedral) => ({
      id: cathedral.getId(),
      name: cathedral.getName(),
      location: cathedral.getLocation(),
      city: cathedral.getCity(),
    }));
    fs.mkdirSync(path.dirname(this.filePath), { recursive: true });
    fs.writeFileSync(this.filePath, JSON.stringify(cathedralsJson, null, JSON_INDENTATION), "utf-8");
  }

  load(): Cathedral[] {
    if (!fs.existsSync(this.filePath)) {
      return [];
    }
    const fileContent = fs.readFileSync(this.filePath, "utf-8");
    if (fileContent.trim() === "") {
      return [];
    }
    const cathedralsJson: CathedralJson[] = JSON.parse(fileContent);
    return cathedralsJson.map(
      (item) => new Cathedral(item.id, item.name, item.location, item.city)
    );
  }
}
