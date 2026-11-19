import * as fs from 'fs';
import * as path from 'path';

export class Truck {
    private id: string;
    private brand: string;
    private capacity_tons: number;
    private year: number;

    constructor(id: string, brand: string, capacity_tons: number, year: number) {
        this.id = id;
        this.brand = brand;
        this.capacity_tons = capacity_tons;
        this.year = year;
    }

    private getFilePath(): string {
        const rootDir = path.resolve(__dirname, '..');
        return path.join(rootDir, 'data', 'trucks.json');
    }

    public saveInformation(): void {
        const filePath = this.getFilePath();
        const dirPath = path.dirname(filePath);

        if (!fs.existsSync(dirPath)) {
            fs.mkdirSync(dirPath, { recursive: true });
        }

        let trucksData: any[] = [];
        if (fs.existsSync(filePath)) {
            try {
                const fileContent = fs.readFileSync(filePath, 'utf-8');
                trucksData = JSON.parse(fileContent);
            } catch (error) {
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

    public static LoadInformation(): Truck[] {
        const rootDir = path.resolve(__dirname, '..');
        const filePath = path.join(rootDir, 'data', 'trucks.json');
        
        let trucksList: Truck[] = [];
        
        if (fs.existsSync(filePath)) {
            try {
                const fileContent = fs.readFileSync(filePath, 'utf-8');
                const data = JSON.parse(fileContent);
                for (const item of data) {
                    trucksList.push(new Truck(item.id, item.brand, item.capacity_tons, item.year));
                }
            } catch (error) {
                // Ignorar errores de parseo
            }
        }
        return trucksList;
    }

    public toString(): string {
        return `ID: ${this.id} | Marca: ${this.brand} | Capacidad: ${this.capacity_tons} ton | Año: ${this.year}`;
    }
}