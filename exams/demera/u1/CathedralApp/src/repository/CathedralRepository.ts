import { Cathedral } from "../model/Cathedral";
import { JsonFileStorage } from "../storage/JsonFileStorage";

export class CathedralRepository {
  private cathedrals: Cathedral[] = [];

  constructor(private readonly storage: JsonFileStorage) {}

  add(cathedral: Cathedral): void {
    if (this.existsById(cathedral.getId())) {
      throw new Error(`A cathedral with id ${cathedral.getId()} already exists.`);
    }
    this.cathedrals.push(cathedral);
    this.storage.save(this.cathedrals);
  }

  loadFromFile(): Cathedral[] {
    this.cathedrals = this.storage.load();
    return [...this.cathedrals];
  }

  private existsById(id: number): boolean {
    return this.cathedrals.some((cathedral) => cathedral.getId() === id);
  }
}
