export class Cathedral {
  constructor(
    private readonly id: number,
    private readonly name: string,
    private readonly location: string,
    private readonly city: string
  ) {}

  getId(): number {
    return this.id;
  }

  getName(): string {
    return this.name;
  }

  getLocation(): string {
    return this.location;
  }

  getCity(): string {
    return this.city;
  }

  describe(): string {
    return `#${this.id} | ${this.name} | ${this.location} | ${this.city}`;
  }
}
