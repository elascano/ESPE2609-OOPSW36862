import * as fs from 'fs';
import { Glasses } from './Glasses';

export class GlassesManager {
    glassesList: Glasses[] = [];
    jsonFilePath: string = './data.json';

    addGlasses(glasses: Glasses): void {
        this.glassesList.push(glasses);
    }

    saveToJson(): void {
        try {
            const data = JSON.stringify(this.glassesList, null, 2);
            fs.writeFileSync(this.jsonFilePath, data, 'utf-8');
            console.log('\n[+] Glasses successfully saved to JSON file.');
        } catch (error) {
            console.error('\n[-] Error saving to JSON:', error);
        }
    }

    loadFromJson(): void {
        try {
            if (fs.existsSync(this.jsonFilePath)) {
                const data = fs.readFileSync(this.jsonFilePath, 'utf-8');
                if (data.trim().length === 0) {
                    this.glassesList = [];
                    return;
                }
                const parsedData = JSON.parse(data);
                this.glassesList = parsedData.map((item: any) => 
                    new Glasses(item.id, item.brand, item.price, item.isPolarized)
                );
            } else {
                fs.writeFileSync(this.jsonFilePath, '[]', 'utf-8');
                this.glassesList = [];
            }
        } catch (error) {
            console.error('\n[-] Error reading JSON file:', error);
            this.glassesList = [];
        }
    }
}