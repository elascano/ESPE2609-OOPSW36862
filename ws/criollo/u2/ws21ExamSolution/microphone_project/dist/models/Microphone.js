"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Microphone = exports.VALID_CONNECTIONS = exports.VALID_TYPES = void 0;
const errors_1 = require("../errors");
exports.VALID_TYPES = ["dynamic", "condenser", "ribbon"];
exports.VALID_CONNECTIONS = ["xlr", "usb", "wireless"];
class Microphone {
    constructor(id, brand, type, connection) {
        this._id = Microphone.validateId(id);
        this._brand = Microphone.validateBrand(brand);
        this._type = Microphone.validateOption(type, exports.VALID_TYPES, "Type");
        this._connection = Microphone.validateOption(connection, exports.VALID_CONNECTIONS, "Connection");
    }
    get id() {
        return this._id;
    }
    get brand() {
        return this._brand;
    }
    get type() {
        return this._type;
    }
    get connection() {
        return this._connection;
    }
    toJSON() {
        return {
            id: this._id,
            brand: this._brand,
            type: this._type,
            connection: this._connection,
        };
    }
    static fromData(data) {
        const { id, brand, type, connection } = data;
        if (id === undefined || brand === undefined || type === undefined || connection === undefined) {
            throw new errors_1.InvalidMicrophoneDataError("Microphone data has missing fields.");
        }
        return new Microphone(id, brand, type, connection);
    }
    toString() {
        return `Microphone #${this._id} | ${this._brand} | ${this._type} | ${this._connection}`;
    }
    static validateId(id) {
        if (!Number.isInteger(id) || id <= 0) {
            throw new errors_1.InvalidMicrophoneDataError("Id must be a positive integer.");
        }
        return id;
    }
    static validateBrand(brand) {
        if (typeof brand !== "string" || brand.trim() === "") {
            throw new errors_1.InvalidMicrophoneDataError("Brand cannot be empty.");
        }
        return brand.trim();
    }
    static validateOption(value, options, fieldName) {
        const normalized = String(value).trim().toLowerCase();
        const match = options.find((option) => option === normalized);
        if (!match) {
            throw new errors_1.InvalidMicrophoneDataError(`${fieldName} must be one of: ${options.join(", ")}.`);
        }
        return match;
    }
}
exports.Microphone = Microphone;
