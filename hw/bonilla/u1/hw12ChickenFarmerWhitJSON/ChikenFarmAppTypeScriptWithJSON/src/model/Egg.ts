export class Egg {
  constructor(private id: number, private size: string) {}

  public getId(): number {
    return this.id;
  }

  public setId(id: number): void {
    this.id = id;
  }

  public getSize(): string {
    return this.size;
  }

  public setSize(size: string): void {
    this.size = size;
  }

  public toString(): string {
    return `Egg{id=${this.id}, size='${this.size}'}`;
  }
}