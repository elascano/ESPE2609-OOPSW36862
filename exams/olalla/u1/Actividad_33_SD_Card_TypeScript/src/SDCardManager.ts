import * as fs from "fs";
import * as path from "path";
import { SDCard } from "./SDCard";

/**
 * Gestiona una colección dinámica de tarjetas SD (Array<SDCard>,
 * equivalente a un ArrayList en TypeScript) y su persistencia JSON.
 */
export class SDCardManager {
  private cards: SDCard[] = [];

  constructor(private readonly jsonFilePath: string) {}

  addCard(card: SDCard): void {
    if (this.cards.some(existing => existing.id === card.id)) {
      throw new Error(`Ya existe una tarjeta con id ${card.id}.`);
    }
    this.cards.push(card);
  }

  getAllCards(): SDCard[] {
    return [...this.cards];
  }

  saveToJson(): void {
    const directory = path.dirname(this.jsonFilePath);
    fs.mkdirSync(directory, { recursive: true });
    fs.writeFileSync(
      this.jsonFilePath,
      JSON.stringify(this.cards, null, 2),
      "utf-8"
    );
  }

  readFromJson(): SDCard[] {
    if (!fs.existsSync(this.jsonFilePath)) {
      this.cards = [];
      return this.getAllCards();
    }

    const rawData: unknown = JSON.parse(
      fs.readFileSync(this.jsonFilePath, "utf-8")
    );
    if (!Array.isArray(rawData)) {
      throw new Error("El archivo JSON debe contener un arreglo.");
    }

    this.cards = rawData.map((item: any) =>
      new SDCard(
        Number(item.id),
        Number(item.capacityGB),
        String(item.brand),
        String(item.speedClass)
      )
    );
    return this.getAllCards();
  }
}
