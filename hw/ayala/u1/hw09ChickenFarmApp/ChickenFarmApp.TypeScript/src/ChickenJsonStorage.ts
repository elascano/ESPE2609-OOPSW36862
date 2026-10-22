import * as fs from 'fs';
import { Chicken } from './Chicken.js';
import { ChickenCoop } from './ChickenCoop.js';

/**
 * @author Enrique Ayala, LogicLords,@ESPE
 */
export class ChickenJsonStorage {
    private filePath: string;

    constructor(filePath: string) {
        this.filePath = filePath;
    }

    public save(coop: ChickenCoop): void {
        try {
            const data = coop.toArray().map(chicken => ({
                id: chicken.id,
                name: chicken.name,
                color: chicken.color,
                bornOnDate: chicken.bornOnDate.toISOString().split('T')[0],
                ageInYears: chicken.ageInYears,
                molting: chicken.molting
            }));
            fs.writeFileSync(this.filePath, JSON.stringify(data, null, 2), 'utf-8');
        } catch (e: any) {
            console.log(`Could not save the file: ${e.message}`);
        }
    }

    public load(coop: ChickenCoop): void {
        if (!fs.existsSync(this.filePath)) {
            return; // Primera ejecución, aún no hay archivo
        }

        try {
            const rawData = fs.readFileSync(this.filePath, 'utf-8');
            const data = JSON.parse(rawData);

            if (Array.isArray(data)) {
                for (const item of data) {
                    const bornDate = new Date(item.bornOnDate);
                    const chicken = new Chicken(
                        item.id,
                        item.name,
                        item.color,
                        bornDate,
                        item.molting
                    );
                    chicken.setAgeInYears(); // Actualizar la edad por si ha cambiado
                    coop.add(chicken);
                }
            }
        } catch (e: any) {
            console.log(`Could not read the file: ${e.message}`);
        }
    }
}