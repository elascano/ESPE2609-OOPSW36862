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
exports.Truck = void 0;
const fs = __importStar(require("fs"));
const path = __importStar(require("path"));
class Truck {
    constructor(id, brand, capacity_tons, year) {
        this.id = id;
        this.brand = brand;
        this.capacity_tons = capacity_tons;
        this.year = year;
    }
    getFilePath() {
        const rootDir = path.resolve(__dirname, '..');
        return path.join(rootDir, 'data', 'trucks.json');
    }
    saveInformation() {
        const filePath = this.getFilePath();
        const dirPath = path.dirname(filePath);
        if (!fs.existsSync(dirPath)) {
            fs.mkdirSync(dirPath, { recursive: true });
        }
        let trucksData = [];
        if (fs.existsSync(filePath)) {
            try {
                const fileContent = fs.readFileSync(filePath, 'utf-8');
                trucksData = JSON.parse(fileContent);
            }
            catch (error) {
                trucksData = [];
            }
        }
        trucksData.push({
            id: this.id,
            brand: this.brand,
            capacity_tons: this.capacity_tons,
            year: this.year
        });
        fs.writeFileSync(filePath, JSON.stringify(trucksData, null, 4), 'utf-8');
    }
    static LoadInformation() {
        const rootDir = path.resolve(__dirname, '..');
        const filePath = path.join(rootDir, 'data', 'trucks.json');
        let trucksList = [];
        if (fs.existsSync(filePath)) {
            try {
                const fileContent = fs.readFileSync(filePath, 'utf-8');
                const data = JSON.parse(fileContent);
                for (const item of data) {
                    trucksList.push(new Truck(item.id, item.brand, item.capacity_tons, item.year));
                }
            }
            catch (error) {
                // Ignorar errores de parseo
            }
        }
        return trucksList;
    }
    toString() {
        return `ID: ${this.id} | Marca: ${this.brand} | Capacidad: ${this.capacity_tons} ton | Año: ${this.year}`;
    }
}
exports.Truck = Truck;
