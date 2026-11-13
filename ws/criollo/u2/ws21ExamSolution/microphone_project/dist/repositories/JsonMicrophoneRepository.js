"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || (function () {
    var ownKeys = function(o) {
        ownKeys = Object.getOwnPropertyNames || function (o) {
            var ar = [];
            for (var k in o) if (Object.prototype.hasOwnProperty.call(o, k)) ar[ar.length] = k;
            return ar;
        };
        return ownKeys(o);
    };
    return function (mod) {
        if (mod && mod.__esModule) return mod;
        var result = {};
        if (mod != null) for (var k = ownKeys(mod), i = 0; i < k.length; i++) if (k[i] !== "default") __createBinding(result, mod, k[i]);
        __setModuleDefault(result, mod);
        return result;
    };
})();
Object.defineProperty(exports, "__esModule", { value: true });
exports.JsonMicrophoneRepository = void 0;
const fs = __importStar(require("fs"));
const path = __importStar(require("path"));
const errors_1 = require("../errors");
const Microphone_1 = require("../models/Microphone");
class JsonMicrophoneRepository {
    constructor(filePath) {
        this.filePath = filePath;
    }
    saveAll(microphones) {
        try {
            fs.mkdirSync(path.dirname(this.filePath), { recursive: true });
            fs.writeFileSync(this.filePath, JSON.stringify(microphones, null, 4), "utf-8");
        }
        catch (error) {
            throw new errors_1.StorageError(`Could not write ${this.filePath}: ${this.describe(error)}`);
        }
    }
    loadAll() {
        if (!fs.existsSync(this.filePath)) {
            return [];
        }
        return this.buildMicrophones(this.readRecords());
    }
    readRecords() {
        try {
            return JSON.parse(fs.readFileSync(this.filePath, "utf-8"));
        }
        catch (error) {
            throw new errors_1.StorageError(`Could not read ${this.filePath}: ${this.describe(error)}`);
        }
    }
    buildMicrophones(records) {
        if (!Array.isArray(records)) {
            throw new errors_1.InvalidMicrophoneDataError("The JSON file must contain a list.");
        }
        return records.map((record) => Microphone_1.Microphone.fromData(record));
    }
    describe(error) {
        return error instanceof Error ? error.message : String(error);
    }
}
exports.JsonMicrophoneRepository = JsonMicrophoneRepository;
