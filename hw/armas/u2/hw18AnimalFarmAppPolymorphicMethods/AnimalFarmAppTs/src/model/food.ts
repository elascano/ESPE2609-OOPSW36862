export class Food {
  description: string;

  constructor(description: string) {
    this.description = description;
  }

  toString(): string {
    return this.description;
  }
}
