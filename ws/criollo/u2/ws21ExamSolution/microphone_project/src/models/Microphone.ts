import { InvalidMicrophoneDataError } from "../errors";

export const VALID_TYPES = ["dynamic", "condenser", "ribbon"] as const;
export const VALID_CONNECTIONS = ["xlr", "usb", "wireless"] as const;

export type MicrophoneType = (typeof VALID_TYPES)[number];
export type ConnectionType = (typeof VALID_CONNECTIONS)[number];

export interface MicrophoneData {
  id: number;
  brand: string;
  type: string;
  connection: string;
}

export class Microphone {
  private readonly _id: number;
  private readonly _brand: string;
  private readonly _type: MicrophoneType;
  private readonly _connection: ConnectionType;

  constructor(id: number, brand: string, type: string, connection: string) {
    this._id = Microphone.validateId(id);
    this._brand = Microphone.validateBrand(brand);
    this._type = Microphone.validateOption(type, VALID_TYPES, "Type");
    this._connection = Microphone.validateOption(connection, VALID_CONNECTIONS, "Connection");
  }

  get id(): number {
    return this._id;
  }

  get brand(): string {
    return this._brand;
  }

  get type(): MicrophoneType {
    return this._type;
  }

  get connection(): ConnectionType {
    return this._connection;
  }

  toJSON(): MicrophoneData {
    return {
      id: this._id,
      brand: this._brand,
      type: this._type,
      connection: this._connection,
    };
  }

  static fromData(data: Partial<MicrophoneData>): Microphone {
    const { id, brand, type, connection } = data;
    if (id === undefined || brand === undefined || type === undefined || connection === undefined) {
      throw new InvalidMicrophoneDataError("Microphone data has missing fields.");
    }
    return new Microphone(id, brand, type, connection);
  }

  toString(): string {
    return `Microphone #${this._id} | ${this._brand} | ${this._type} | ${this._connection}`;
  }

  private static validateId(id: number): number {
    if (!Number.isInteger(id) || id <= 0) {
      throw new InvalidMicrophoneDataError("Id must be a positive integer.");
    }
    return id;
  }

  private static validateBrand(brand: string): string {
    if (typeof brand !== "string" || brand.trim() === "") {
      throw new InvalidMicrophoneDataError("Brand cannot be empty.");
    }
    return brand.trim();
  }

  private static validateOption<T extends string>(
    value: string,
    options: readonly T[],
    fieldName: string
  ): T {
    const normalized = String(value).trim().toLowerCase();
    const match = options.find((option) => option === normalized);
    if (!match) {
      throw new InvalidMicrophoneDataError(`${fieldName} must be one of: ${options.join(", ")}.`);
    }
    return match;
  }
}
