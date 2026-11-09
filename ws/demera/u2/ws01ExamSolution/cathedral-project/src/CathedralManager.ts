import * as fs from 'fs';
import * as path from 'path';
import { Cathedral } from './Cathedral';

export class CathedralManager {
    private cathedrals: Cathedral[] = [];
    private filePath: string;

    constructor(fileName: string = 'cathedrals.json') {
        this.filePath = path.resolve(process.cwd(), fileName);
        this.loadFromFile();
    }

    // Add a new cathedral and automatically save to JSON file
    public addCathedral(cathedral: Cathedral): void {
        this.cathedrals.push(cathedral);
        this.saveToFile();
    }

    // Check if ID already exists
    public exists(id: string): boolean {
        return this.cathedrals.some(c => c.getId().toLowerCase() === id.toLowerCase());
    }

    public saveToFile(): void {
        try {
            const data = JSON.stringify(this.cathedrals.map(c => c.toJSON()), null, 4);
            fs.writeFileSync(this.filePath, data, 'utf-8');
            console.log(`\n[SUCCESS] Saved ${this.cathedrals.length} record(s) to JSON file.`);
        } catch (error) {
            console.error('\n[ERROR] Writing to JSON file:', error);
        }
    }

    public loadFromFile(): Cathedral[] {
        if (!fs.existsSync(this.filePath)) {
            this.cathedrals = [];
            return this.cathedrals;
        }

        try {
            const rawData = fs.readFileSync(this.filePath, 'utf-8');
            const parsedData: any[] = JSON.parse(rawData);
            this.cathedrals = parsedData.map(item => Cathedral.fromJSON(item));
        } catch (error) {
            console.error('\n[ERROR] Reading from JSON file:', error);
            this.cathedrals = [];
        }

        return this.cathedrals;
    }

    public getCathedrals(): Cathedral[] {
        return this.cathedrals;
    }
}