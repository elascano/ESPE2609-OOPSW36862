import * as fs from 'fs';
import { ChickenCoop } from '../model/chicken-coop';
import { Chicken } from '../model/chicken';

export class JsonManager {
    private static readonly FILE_NAME = "chicken_farm_data.json";

    public static saveCoops(coops: ChickenCoop[]): void {
        try {
            fs.writeFileSync(JsonManager.FILE_NAME, JSON.stringify(coops, null, 2), 'utf-8');
        } catch (e: any) {
            console.log("Error saving JSON file: " + e.message);
        }
    }

    public static loadCoops(): ChickenCoop[] {
        let coops: ChickenCoop[] = [];
        try {
            if (fs.existsSync(JsonManager.FILE_NAME)) {
                const data = fs.readFileSync(JsonManager.FILE_NAME, 'utf-8');
                const rawCoops = JSON.parse(data);
                if (rawCoops) {
                    coops = rawCoops.map((c: any) => {
                        const coop = new ChickenCoop(c.id);
                        const loadedChickens = (c.chickens || []).map((ch: any) => {
                            return new Chicken(
                                ch.id,
                                ch.name,
                                ch.color,
                                new Date(ch.bornOnDate),
                                ch.ageInYear,
                                ch.molting
                            );
                        });
                        coop.setChickens(loadedChickens);
                        return coop;
                    });
                }
            }
        } catch (e: any) {
        }
        return coops;
    }
}