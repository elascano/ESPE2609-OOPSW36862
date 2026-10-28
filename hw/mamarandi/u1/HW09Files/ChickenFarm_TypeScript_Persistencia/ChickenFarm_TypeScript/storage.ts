import { existsSync, readFileSync, writeFileSync } from "node:fs";
import { Chicken } from "./Chicken";

const FILE = "chickens.json";
export function saveChickens(chickens: (Chicken | null)[]): void {
    const records = chickens.filter((c): c is Chicken => c !== null).map(c => {
        const d = c.getBorn0nDate();
        const bornOnDate = `${d.getFullYear()}-${String(d.getMonth() + 1).padStart(2,"0")}-${String(d.getDate()).padStart(2,"0")}`;
        return {id: c.getId(), name: c.getName(), color: c.getColor(), bornOnDate, molting: c.isMolting()};
    });
    writeFileSync(FILE, JSON.stringify(records, null, 2), "utf8");
    console.log("Data saved in chickens.json");
}
export function loadChickens(): Chicken[] {
    if (!existsSync(FILE)) return [];
    try {
        const items: unknown = JSON.parse(readFileSync(FILE,"utf8"));
        if (!Array.isArray(items) || items.length > 10) throw Error("Invalid chicken list");
        return items.map(x => {
            if (!x || typeof x !== "object") throw Error("Invalid chicken");
            const r = x as Record<string, unknown>;
            if (!Number.isInteger(r.id) || typeof r.name !== "string" || typeof r.color !== "string" ||
                typeof r.bornOnDate !== "string" || typeof r.molting !== "boolean" ||
                !/^\d{4}-\d{2}-\d{2}$/.test(r.bornOnDate)) throw Error("Invalid chicken record");
            const [y,m,d] = r.bornOnDate.split("-").map(Number);
            const date = new Date(y,m-1,d);
            if (date.getFullYear() !== y || date.getMonth() !== m-1 || date.getDate() !== d) throw Error("Invalid date");
            return new Chicken(r.id as number,r.name,r.color,date,r.molting);
        });
    } catch(e) {
        console.error("Could not read chickens.json:", e);
        process.exit(1);
    }
}
