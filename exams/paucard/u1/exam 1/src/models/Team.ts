
export interface TeamData {
  id: number;
  name: string;
  city: string;
  foundedYear: number;
}

export class Team {
  private _id: number;
  private _name: string;
  private _city: string;
  private _foundedYear: number;

  constructor(id: number, name: string, city: string, foundedYear: number) {
    this._id = id;
    this._name = name;
    this._city = city;
    this._foundedYear = foundedYear;
  }

  get id(): number {
    return this._id;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get city(): string {
    return this._city;
  }

  set city(value: string) {
    this._city = value;
  }

  get foundedYear(): number {
    return this._foundedYear;
  }

  set foundedYear(value: number) {
    this._foundedYear = value;
  }

  toJSON(): TeamData {
    return {
      id: this._id,
      name: this._name,
      city: this._city,
      foundedYear: this._foundedYear,
    };
  }
  static fromJSON(data: TeamData): Team {
    return new Team(data.id, data.name, data.city, data.foundedYear);
  }

  toString(): string {
    return `Team #${this._id} | ${this._name} | ${this._city} | Founded: ${this._foundedYear}`;
  }
}
