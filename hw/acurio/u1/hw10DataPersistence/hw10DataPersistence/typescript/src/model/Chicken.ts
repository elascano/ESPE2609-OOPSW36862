/**
 * Modelo Chicken reconstruido a partir de ChickenFarmApp.java.
 * FALTA la clase Chicken.java original para replicar doStuff() exactamente.
 */
export interface ChickenData {
    id: number;
    name: string;
    color: string;
    bornOnDate: string;
    ageInYears: number;
    molting: boolean;
}

export function formatISODate(value: Date): string {
    const yyyy = value.getFullYear();
    const mm = String(value.getMonth() + 1).padStart(2, "0");
    const dd = String(value.getDate()).padStart(2, "0");
    return `${yyyy}-${mm}-${dd}`;
}

export function parseISODate(value: string): Date {
    if (!/^\d{4}-\d{2}-\d{2}$/.test(value)) {
        throw new Error(`Invalid date: ${value}`);
    }
    const [year, month, day] = value.split("-").map(Number);
    const date = new Date(year, month - 1, day);
    if (date.getFullYear() !== year || date.getMonth() + 1 !== month || date.getDate() !== day) {
        throw new Error(`Invalid calendar date: ${value}`);
    }
    return date;
}

export class Chicken {
    private id: number;
    private name: string;
    private color: string;
    private bornOnDate: Date;
    private ageInYears: number;
    private molting: boolean;

    constructor(id: number, name: string, color: string, bornOnDate: Date) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.bornOnDate = bornOnDate;
        this.molting = false;
        this.ageInYears = 0;
        this.setAgeInYears();
    }

    getId(): number { return this.id; }
    setId(value: number): void { this.id = value; }
    getName(): string { return this.name; }
    setName(value: string): void { this.name = value; }
    getColor(): string { return this.color; }
    setColor(value: string): void { this.color = value; }
    getBornOnDate(): Date { return this.bornOnDate; }
    setBornOnDate(value: Date): void { this.bornOnDate = value; this.setAgeInYears(); }
    getAgeInYears(): number { return this.ageInYears; }
    isMolting(): boolean { return this.molting; }
    setMolting(value: boolean): void { this.molting = value; }

    computeAgeInYears(): number {
        const today = new Date();
        let years = today.getFullYear() - this.bornOnDate.getFullYear();
        const birthdayHasNotHappened =
            today.getMonth() < this.bornOnDate.getMonth() ||
            (today.getMonth() === this.bornOnDate.getMonth() && today.getDate() < this.bornOnDate.getDate());
        if (birthdayHasNotHappened) years--;
        return years;
    }

    setAgeInYears(): void {
        this.ageInYears = this.computeAgeInYears();
    }

    doStuff(): void {
        // Provisional hasta recibir el código exacto de Chicken.java.
        console.log(`${this.name} is doing chicken stuff.`);
    }

    toJSON(): ChickenData {
        return {
            id: this.id,
            name: this.name,
            color: this.color,
            bornOnDate: formatISODate(this.bornOnDate),
            ageInYears: this.ageInYears,
            molting: this.molting,
        };
    }

    static fromJSON(value: unknown): Chicken {
        if (typeof value !== "object" || value === null || Array.isArray(value)) {
            throw new Error("Chicken data must be a JSON object");
        }
        const data = value as Partial<ChickenData>;
        if (!Number.isInteger(data.id) || typeof data.name !== "string" ||
            typeof data.color !== "string" || typeof data.bornOnDate !== "string" ||
            (data.molting !== undefined && typeof data.molting !== "boolean")) {
            throw new Error("Invalid chicken information");
        }
        const chicken = new Chicken(data.id as number, data.name, data.color, parseISODate(data.bornOnDate));
        chicken.setMolting(data.molting ?? false);
        chicken.setAgeInYears();
        return chicken;
    }

    toString(): string {
        return `Chicken{id=${this.id}, name=${this.name}, color=${this.color}, ` +
            `bornOnDate=${formatISODate(this.bornOnDate)}, ageInYears=${this.ageInYears}, ` +
            `molting=${this.molting}}`;
    }
}
