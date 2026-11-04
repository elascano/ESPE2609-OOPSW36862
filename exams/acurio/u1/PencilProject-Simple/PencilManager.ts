import { Pencil } from './Pencil';

declare function require(name: string): any;
const fs = require('node:fs');

export class PencilManager {
  private pencils: Array<Pencil> = [];
  private filePath: string;

  constructor(filePath: string) {
    this.filePath = filePath;
  }

  add(pencil: Pencil): void {
    if (this.pencils.some(existing => existing.id === pencil.id)) {
      throw new Error('This ID already exists.');
    }
    this.pencils.push(pencil);
    try {
      this.save();
    } catch (error) {
      this.pencils.pop();
      throw error;
    }
  }

  save(): void {
    fs.writeFileSync(this.filePath, JSON.stringify(this.pencils, null, 2) + '\n');
  }

  read(): Array<Pencil> {
    if (!fs.existsSync(this.filePath)) {
      this.pencils = [];
      return [];
    }
    const records = JSON.parse(fs.readFileSync(this.filePath, 'utf8'));
    if (!Array.isArray(records)) throw new Error('The JSON file must contain an array.');
    const loaded = records.map((record: Pencil) => {
      if (!record || typeof record.brand !== 'string' ||
          typeof record.color !== 'string' || typeof record.hardness !== 'string') {
        throw new Error('Invalid pencil in the JSON file.');
      }
      return new Pencil(record.id, record.brand, record.color, record.hardness);
    });
    if (new Set(loaded.map(pencil => pencil.id)).size !== loaded.length) {
      throw new Error('The JSON file contains duplicate IDs.');
    }
    this.pencils = loaded;
    return [...this.pencils];
  }
}
