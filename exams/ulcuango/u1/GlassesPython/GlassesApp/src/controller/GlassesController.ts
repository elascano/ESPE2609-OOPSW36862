import * as fs from 'fs';
import * as path from 'path';
import { Glasses } from '../model/Glasses';

export class GlassesController {
    private filePath: string = path.join(process.cwd(), 'glasses.json');
    public createGlasses(glasses: Glasses): void {
        let glassesList: Glasses[] = this.readGlasses();
        glassesList.push(glasses);

        const data = glassesList.map(g => ({
            id: g.getId(),
            brand: g.getBrand(),
            frameType: g.getFrameType(),
            price: g.getPrice()
        }));

        fs.writeFileSync(this.filePath, JSON.stringify(data, null, 2), 'utf-8');
    }

    public readGlasses(): Glasses[] {
        if (!fs.existsSync(this.filePath)) {
            return [];
        }

        try {
            const fileData = fs.readFileSync(this.filePath, 'utf-8');
            if (!fileData.trim()) {
                return [];
            }
            const jsonData = JSON.parse(fileData);
            return jsonData.map((item: any) => new Glasses(item.id, item.brand, item.frameType, item.price));
        } catch (error) {
            return [];
        }
    }
}