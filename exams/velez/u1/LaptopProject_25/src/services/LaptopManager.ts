import * as fs from 'fs';
import * as path from 'path';
import { Laptop } from '../models/Laptop';

export class LaptopManager {
    private laptopList: Array<Laptop>;
    private filePath: string;

    constructor() {
        this.laptopList = new Array<Laptop>();
        this.filePath = path.join(__dirname, '../../data/laptops.json');
        this.ensureDirectoryExists();
    }

    private ensureDirectoryExists(): void {
        const dir = path.dirname(this.filePath);
        if (!fs.existsSync(dir)) {
            fs.mkdirSync(dir, { recursive: true });
        }
    }

    public addLaptop(laptop: Laptop): void {
        this.readFromJson();
        this.laptopList.push(laptop);
        this.saveToJson();
    }

    public saveToJson(): void {
        const jsonData = JSON.stringify(this.laptopList, null, 4);
        fs.writeFileSync(this.filePath, jsonData, 'utf-8');
        console.log('\n[SUCCESS] Laptop saved to data/laptops.json successfully!\n');
    }

    public readFromJson(): Array<Laptop> {
        this.laptopList = new Array<Laptop>();

        if (!fs.existsSync(this.filePath)) {
            return this.laptopList;
        }

        const rawData = fs.readFileSync(this.filePath, 'utf-8');
        if (rawData.trim().length === 0) {
            return this.laptopList;
        }

        const parsedArray = JSON.parse(rawData);
        for (const item of parsedArray) {
            const laptop = new Laptop(item.id, item.brand, item.model, item.price);
            this.laptopList.push(laptop);
        }

        return this.laptopList;
    }

    public displayLaptops(): void {
        const list = this.readFromJson();
        console.log('\n========== LAPTOPS IN JSON FILE (ARRAYLIST) ==========');
        if (list.length === 0) {
            console.log('No laptops found in the JSON file.');
        } else {
            for (let i = 0; i < list.length; i++) {
                console.log(`${i + 1}. ${list[i].toString()}`);
            }
        }
        console.log('======================================================\n');
    }
}