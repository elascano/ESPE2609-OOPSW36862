import * as fs from "node:fs";
import * as path from "node:path";
import { Chicken } from "../model/Chicken.ts";
import { ChickenCoop } from "../model/ChickenCoop.ts";

/**
 * Data persistence
 *
 * @author Andres Pachacama, Byte Work, @ESPE
 */

const SEPARATOR = ";";
const HEADER = "id;name;color;bornOnDate;molting";

export class FileManager {

    static save(coop: ChickenCoop, fileName: string): void {
        let content = HEADER + "\n";

        coop.resetIteration();
        let chicken = coop.next();
        while (chicken !== null) {
            content += chicken.id
                + SEPARATOR + FileManager.clean(chicken.name)
                + SEPARATOR + FileManager.clean(chicken.color)
                + SEPARATOR + FileManager.formatDate(chicken.bornOnDate)
                + SEPARATOR + chicken.molting + "\n";
            chicken = coop.next();
        }

        fs.writeFileSync(fileName, content, "utf-8");
    }

    static load(fileName: string, chickenCoopId: number): ChickenCoop {
        const coop = new ChickenCoop(chickenCoopId);

        if (!FileManager.exists(fileName)) {
            return coop;
        }

        const lines = fs.readFileSync(fileName, "utf-8").split(/\r?\n/);

        for (const line of lines.slice(1)) {      
            if (coop.isFull()) {
                break;
            }
            const chicken = FileManager.toChicken(line);
            if (chicken !== null) {                
                coop.add(chicken);
            }
        }

        return coop;
    }

    static exists(fileName: string): boolean {
        return fs.existsSync(fileName);
    }

    static fullPath(fileName: string): string {
        return path.resolve(fileName);
    }


    private static toChicken(line: string): Chicken | null {
        const parts = line.split(SEPARATOR);
        if (parts.length !== 5) {
            return null;
        }

        const id = Number(parts[0].trim());
        const name = parts[1].trim();
        const color = parts[2].trim();
        const bornOnDate = FileManager.parseDate(parts[3].trim());
        const molting = parts[4].trim().toLowerCase() === "true";

        if (!Number.isInteger(id) || bornOnDate === null) {
            return null;
        }

        const chicken = new Chicken(id, name, color, bornOnDate, 0, molting);
        chicken.setAgeInYears();      
        return chicken;
    }


    private static formatDate(date: Date): string {
        const year = String(date.getFullYear()).padStart(4, "0");
        const month = String(date.getMonth() + 1).padStart(2, "0");
        const day = String(date.getDate()).padStart(2, "0");
        return year + "-" + month + "-" + day;
    }

  
    private static parseDate(text: string): Date | null {
        const parts = text.split("-").map(Number);
        if (parts.length !== 3 || parts.some((part) => !Number.isInteger(part))) {
            return null;
        }
        const [year, month, day] = parts;
        const date = new Date(year, month - 1, day);
        date.setFullYear(year);     
        if (date.getMonth() !== month - 1 || date.getDate() !== day) {
            return null;
        }
        return date;
    }

    private static clean(text: string): string {
        return text.replaceAll(SEPARATOR, ",");
    }
}
