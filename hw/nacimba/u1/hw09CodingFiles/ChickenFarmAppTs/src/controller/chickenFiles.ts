import * as fs from "fs";
import { Chicken } from "../model/chicken";
import { Poop } from "../model/poop";

const HEADER = "id,name,color,bornOnDate,ageInYears,molting,poopColor,poopConsistency";

export class ChickenFiles {
    private filePath: string;

    constructor(filePath: string) {
        this.filePath = filePath;
    }

    add(chicken: Chicken): boolean {
        try {
            if (!fs.existsSync(this.filePath) || fs.statSync(this.filePath).size === 0) {
                fs.writeFileSync(this.filePath, HEADER + "\n", "utf-8");
            }
            fs.appendFileSync(this.filePath, this.toCsvLine(chicken) + "\n", "utf-8");
            console.log(`Chicken saved in file: ${this.filePath}`);
            return true;
        } catch (error) {
            console.log(`Error saving chicken in file: ${(error as Error).message}`);
            return false;
        }
    }

    delete(chickenId: number): boolean {
        if (!fs.existsSync(this.filePath)) {
            console.log("The file does not exist yet. Nothing to delete.");
            return false;
        }
        try {
            const lines = fs.readFileSync(this.filePath, "utf-8").split(/\r?\n/);
            const remaining: string[] = [];
            let found = false;

            lines.forEach((line, index) => {
                if (index === 0) {
                    remaining.push(line);
                } else if (line.trim() === "") {
                    // skip empty lines
                } else if (this.parseCsvLine(line)[0] === String(chickenId)) {
                    found = true;
                } else {
                    remaining.push(line);
                }
            });

            if (found) {
                fs.writeFileSync(this.filePath, remaining.join("\n") + "\n", "utf-8");
                console.log(`Chicken with ID ${chickenId} deleted from file.`);
            } else {
                console.log(`Chicken with ID ${chickenId} not found in file.`);
            }
            return found;
        } catch (error) {
            console.log(`Error deleting chicken from file: ${(error as Error).message}`);
            return false;
        }
    }

    loadAll(): Chicken[] {
        const chickens: Chicken[] = [];
        if (!fs.existsSync(this.filePath)) {
            return chickens;
        }
        try {
            const lines = fs.readFileSync(this.filePath, "utf-8").split(/\r?\n/);
            for (let i = 1; i < lines.length; i++) {
                if (lines[i].trim() === "") {
                    continue;
                }
                const chicken = this.fromCsvLine(lines[i]);
                if (chicken === null) {
                    console.log(`Skipping invalid line ${i + 1} in file.`);
                } else {
                    chickens.push(chicken);
                }
            }
        } catch (error) {
            console.log(`Error reading file: ${(error as Error).message}`);
        }
        return chickens;
    }

    // ------------------------- helpers -------------------------

    private formatDate(date: Date): string {
        const d = String(date.getDate()).padStart(2, "0");
        const m = String(date.getMonth() + 1).padStart(2, "0");
        return `${d}/${m}/${date.getFullYear()}`;
    }

    private parseDate(text: string): Date | null {
        const match = /^(\d{2})\/(\d{2})\/(\d{4})$/.exec(text);
        if (!match) {
            return null;
        }
        const day = parseInt(match[1], 10);
        const month = parseInt(match[2], 10);
        const year = parseInt(match[3], 10);
        const date = new Date(year, month - 1, day);
        if (date.getFullYear() !== year || date.getMonth() !== month - 1 || date.getDate() !== day) {
            return null;
        }
        return date;
    }

    private escape(value: string): string {
        if (value.includes(",") || value.includes("\"") || value.includes("\n")) {
            return "\"" + value.replace(/"/g, "\"\"") + "\"";
        }
        return value;
    }

    private toCsvLine(chicken: Chicken): string {
        const poop = chicken.poop;
        return [
            String(chicken.id),
            this.escape(chicken.name),
            this.escape(chicken.color),
            this.formatDate(chicken.bornOnDate),
            String(chicken.ageInYears),
            String(chicken.molting),
            this.escape(poop ? poop.color : ""),
            this.escape(poop ? poop.consistency : "")
        ].join(",");
    }

    private fromCsvLine(line: string): Chicken | null {
        const f = this.parseCsvLine(line);
        if (f.length < 6 || !/^\d+$/.test(f[0])) {
            return null;
        }
        const id = parseInt(f[0], 10);
        const bornOnDate = this.parseDate(f[3]);
        if (bornOnDate === null) {
            return null;
        }
        const molting = f[5].trim().toLowerCase() === "true";

        let poop: Poop | null = null;
        if (f.length > 7 && f[6] !== "" && f[7] !== "") {
            poop = new Poop(id, f[6], f[7].charAt(0));
        }
        return new Chicken(id, f[1], f[2], bornOnDate, molting, poop);
    }

    private parseCsvLine(line: string): string[] {
        const fields: string[] = [];
        let current = "";
        let inQuotes = false;

        for (let i = 0; i < line.length; i++) {
            const c = line[i];
            if (inQuotes) {
                if (c === "\"") {
                    if (line[i + 1] === "\"") {
                        current += "\"";
                        i++;
                    } else {
                        inQuotes = false;
                    }
                } else {
                    current += c;
                }
            } else if (c === "\"") {
                inQuotes = true;
            } else if (c === ",") {
                fields.push(current);
                current = "";
            } else {
                current += c;
            }
        }
        fields.push(current);
        return fields;
    }
}