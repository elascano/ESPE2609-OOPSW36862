import * as fs from 'fs';
import * as path from 'path';
import { Cellphone } from './model/Cellphone';

const filePath = path.join(__dirname, '../data/cellphones.json');

function saveCellphonesToJson(cellphones: Cellphone[]): void {
    const data = JSON.stringify(cellphones, null, 2);
    fs.writeFileSync(filePath, data, 'utf-8');
}

function readCellphonesFromJson(): Cellphone[] {
    if (!fs.existsSync(filePath)) {
        return [];
    }
    const rawData = fs.readFileSync(filePath, 'utf-8');
    const parsedData: any[] = JSON.parse(rawData);
    
    return parsedData.map(item => new Cellphone(item.id, item.brand, item.model, item.price));
}

function main(): void {
    const cellphones: Cellphone[] = [];

    const cellphone1 = new Cellphone("CEL-001", "Samsung", "Galaxy S23", 999.99);
    const cellphone2 = new Cellphone("CEL-002", "Apple", "iPhone 15", 1099.99);
    const cellphone3 = new Cellphone("CEL-003", "Xiaomi", "13 Pro", 799.50);

    cellphones.push(cellphone1);
    cellphones.push(cellphone2);
    cellphones.push(cellphone3);

    saveCellphonesToJson(cellphones);
    console.log("Data saved to JSON file successfully.");

    const loadedCellphones: Cellphone[] = readCellphonesFromJson();
    console.log("Read cellphones from JSON file:");
    loadedCellphones.forEach(phone => {
        console.log(`ID: ${phone.getId()}, Brand: ${phone.getBrand()}, Model: ${phone.getModel()}, Price: $${phone.getPrice()}`);
    });
}

main();