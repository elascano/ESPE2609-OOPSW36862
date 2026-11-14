export class Location {
  locationId: number;
  description: string;

  constructor(locationId: number, description: string) {
    this.locationId = locationId;
    this.description = description;
  }

  toString(): string {
    return this.description;
  }
}
