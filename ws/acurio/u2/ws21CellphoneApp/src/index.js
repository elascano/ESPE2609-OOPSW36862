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
const fs = __importStar(require("fs"));
const path = __importStar(require("path"));
const Cellphone_1 = require("./model/Cellphone");
const filePath = path.join(__dirname, '../data/cellphones.json');
function saveCellphonesToJson(cellphones) {
    const data = JSON.stringify(cellphones, null, 2);
    fs.writeFileSync(filePath, data, 'utf-8');
}
function readCellphonesFromJson() {
    if (!fs.existsSync(filePath)) {
        return [];
    }
    const rawData = fs.readFileSync(filePath, 'utf-8');
    const parsedData = JSON.parse(rawData);
    return parsedData.map(item => new Cellphone_1.Cellphone(item.id, item.brand, item.model, item.price));
}
function main() {
    const cellphones = [];
    const cellphone1 = new Cellphone_1.Cellphone("CEL-001", "Samsung", "Galaxy S23", 999.99);
    const cellphone2 = new Cellphone_1.Cellphone("CEL-002", "Apple", "iPhone 15", 1099.99);
    const cellphone3 = new Cellphone_1.Cellphone("CEL-003", "Xiaomi", "13 Pro", 799.50);
    cellphones.push(cellphone1);
    cellphones.push(cellphone2);
    cellphones.push(cellphone3);
    saveCellphonesToJson(cellphones);
    console.log("Data saved to JSON file successfully.");
    const loadedCellphones = readCellphonesFromJson();
    console.log("Read cellphones from JSON file:");
    loadedCellphones.forEach(phone => {
        console.log(`ID: ${phone.getId()}, Brand: ${phone.getBrand()}, Model: ${phone.getModel()}, Price: $${phone.getPrice()}`);
    });
}
main();
