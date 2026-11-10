export class Vehicle {
  private id: number;
  private brand: string;
  private model: string;
  private year: number;

  constructor(id: number, brand: string, model: string, year: number) {
    this.id = id;
    this.brand = brand;
    this.model = model;
    this.year = year;
  }

  getId(): number { return this.id; }
  getBrand(): string { return this.brand; }
  getModel(): string { return this.model; }
  getYear(): number { return this.year; }

  setBrand(brand: string): void { this.brand = brand; }
  setModel(model: string): void { this.model = model; }
  setYear(year: number): void { this.year = year; }

  toJSON(): { id: number; brand: string; model: string; year: number } {
    return { id: this.id, brand: this.brand, model: this.model, year: this.year };
  }

  static fromJSON(o: { id: number; brand: string; model: string; year: number }): Vehicle {
    return new Vehicle(o.id, o.brand, o.model, o.year);
  }

  toString(): string {
    return `ID: ${this.id} | Brand: ${this.brand} | Model: ${this.model} | Year: ${this.year}`;
  }
}
