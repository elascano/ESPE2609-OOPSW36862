export class Pencil {
  id: number;
  brand: string;
  color: string;
  hardness: string;

  constructor(id: number, brand: string, color: string, hardness: string) {
    if (!Number.isSafeInteger(id) || id <= 0) {
      throw new Error('ID must be a positive whole number.');
    }
    if (!brand.trim() || !color.trim() || !hardness.trim()) {
      throw new Error('All attributes are required.');
    }
    this.id = id;
    this.brand = brand.trim();
    this.color = color.trim();
    this.hardness = hardness.trim();
  }
}
