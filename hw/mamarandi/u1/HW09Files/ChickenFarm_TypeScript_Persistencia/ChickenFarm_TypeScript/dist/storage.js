"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.saveChickens = saveChickens;
exports.loadChickens = loadChickens;
const node_fs_1 = require("node:fs");
const Chicken_1 = require("./Chicken");
const FILE = "chickens.json";
function saveChickens(chickens) {
    const records = chickens.filter((c) => c !== null).map(c => {
        const d = c.getBorn0nDate();
        const bornOnDate = `${d.getFullYear()}-${String(d.getMonth() + 1).padStart(2, "0")}-${String(d.getDate()).padStart(2, "0")}`;
        return { id: c.getId(), name: c.getName(), color: c.getColor(), bornOnDate, molting: c.isMolting() };
    });
    (0, node_fs_1.writeFileSync)(FILE, JSON.stringify(records, null, 2), "utf8");
    console.log("Data saved in chickens.json");
}
function loadChickens() {
    if (!(0, node_fs_1.existsSync)(FILE))
        return [];
    try {
        const items = JSON.parse((0, node_fs_1.readFileSync)(FILE, "utf8"));
        if (!Array.isArray(items) || items.length > 10)
            throw Error("Invalid chicken list");
        return items.map(x => {
            if (!x || typeof x !== "object")
                throw Error("Invalid chicken");
            const r = x;
            if (!Number.isInteger(r.id) || typeof r.name !== "string" || typeof r.color !== "string" ||
                typeof r.bornOnDate !== "string" || typeof r.molting !== "boolean" ||
                !/^\d{4}-\d{2}-\d{2}$/.test(r.bornOnDate))
                throw Error("Invalid chicken record");
            const [y, m, d] = r.bornOnDate.split("-").map(Number);
            const date = new Date(y, m - 1, d);
            if (date.getFullYear() !== y || date.getMonth() !== m - 1 || date.getDate() !== d)
                throw Error("Invalid date");
            return new Chicken_1.Chicken(r.id, r.name, r.color, date, r.molting);
        });
    }
    catch (e) {
        console.error("Could not read chickens.json:", e);
        process.exit(1);
    }
}
