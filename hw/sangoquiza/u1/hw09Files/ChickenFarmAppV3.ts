/**
 * @author Daniel Sangoquiza
 */

import * as fs from 'fs';
import * as path from 'path';
import * as readline from 'readline';

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const question = (query: string): Promise<string> => {
    return new Promise(resolve => rl.question(query, resolve));
};

function formatDate(d: Date): string {
    const year = d.getFullYear();
    const month = String(d.getMonth() + 1).padStart(2, '0');
    const day = String(d.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}

class Egg {
    private id: number;
    private size: string;

    constructor(id: number, size: string) {
        this.id = id;
        this.size = size;
    }

    public toString(): string {
        return `Egg{id=${this.id}, size=${this.size}}`;
    }

    public getId(): number { return this.id; }
    public setId(id: number): void { this.id = id; }
    public getSize(): string { return this.size; }
    public setSize(size: string): void { this.size = size; }
}

class Poop {
    private id: number;
    private color: string;
    private consistency: string;

    constructor(id: number, color: string, consistency: string) {
        this.id = id;
        this.color = color;
        this.consistency = consistency;
    }

    public toString(): string {
        return `Poop{id=${this.id}, color=${this.color}, consistency=${this.consistency}}`;
    }

    public getId(): number { return this.id; }
    public setId(id: number): void { this.id = id; }
    public getColor(): string { return this.color; }
    public setColor(color: string): void { this.color = color; }
    public getConsistency(): string { return this.consistency; }
    public setConsistency(consistency: string): void { this.consistency = consistency; }
}

class Chicken {
    private id: number;
    private name: string;
    private color: string;
    private bornOnDate: Date;
    private ageInYears: number;
    private molting: boolean;

    constructor(id: number, name: string, color: string, bornOnDate: Date, ageInYears: number, molting: boolean) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.bornOnDate = bornOnDate;
        this.ageInYears = ageInYears;
        this.molting = molting;
    }

    public doStuff(): void {
        this.cluck();
        this.wander();
        this.drink(1.3);
        console.log(`poop ---> ${this.poop()}`);
        console.log(`egg ---> ${this.layAnEgg()}`);
    }

    private cluck(): void { console.log(`The chicken ${this.name} is clucking ...`); }
    private wander(): void { console.log(`The chicken ${this.name} is wandering ...`); }
    private eat(): void { console.log(`The chicken ${this.name} is eating ...`); }
    private drink(amountOfWater: number): void { console.log(`The chicken ${this.name} is drinking --> ${amountOfWater}ml of water`); }

    private poop(): Poop { return new Poop(2, "green", 'M'); }
    private layAnEgg(): Egg { return new Egg(2, 'S'); }

    public computeAgeInYears(): number {
        const today = new Date();
        let age = today.getFullYear() - this.bornOnDate.getFullYear();
        if (today.getMonth() < this.bornOnDate.getMonth() || 
            (today.getMonth() === this.bornOnDate.getMonth() && today.getDate() < this.bornOnDate.getDate())) {
            age--;
        }
        return age;
    }

    public toString(): string {
        return `\n Chicken{id=${this.id}, name=${this.name}, color=${this.color}, bornOnDate=${formatDate(this.bornOnDate)}, age=${this.ageInYears}, molting=${this.molting}}`;
    }

    public getId(): number { return this.id; }
    public setId(id: number): void { this.id = id; }
    public getName(): string { return this.name; }
    public setName(name: string): void { this.name = name; }
    public getColor(): string { return this.color; }
    public setColor(color: string): void { this.color = color; }
    public getBornOnDate(): Date { return this.bornOnDate; }
    public setBornOnDate(bornOnDate: Date): void { this.bornOnDate = bornOnDate; }
    public getAgeInYears(): number { return this.ageInYears; }
    public setAgeInYears(): void { this.ageInYears = this.computeAgeInYears(); }
    public isMolting(): boolean { return this.molting; }
    public setMolting(molting: boolean): void { this.molting = molting; }
}

class ChickenCoop {
    public static readonly CAPACITY = 10;
    private chickenCoopId: number;
    private chickens: Chicken[];
    private index: number;

    constructor(chickenCoopId: number = 1, chickens?: Chicken[]) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = [];
        this.index = 0;

        if (chickens) {
            for (const chicken of chickens) {
                if (chicken) this.add(chicken);
            }
        } else if (!chickens && chickenCoopId === 1) {
            this.add(new Chicken(1, "Lucy", "black", new Date(), 0, false));
            this.add(new Chicken(2, "Alejandra", "white", new Date(), 0, false));
        }
    }

    public add(chicken: Chicken): void {
        if (this.chickens.length < ChickenCoop.CAPACITY) {
            this.chickens.push(chicken);
        }
    }

    public remove(chickenId: number): void {
        for (let i = 0; i < this.chickens.length; i++) {
            if (this.chickens[i].getId() === chickenId) {
                this.chickens.splice(i, 1);
                return;
            }
        }
    }

    public resetIteration(): void {
        this.index = 0;
    }

    public next(): Chicken | null {
        if (this.index < this.chickens.length) {
            const chicken = this.chickens[this.index];
            this.index++;
            return chicken;
        }
        return null;
    }

    public exists(chickenId: number): boolean {
        return this.chickens.some(c => c.getId() === chickenId);
    }

    public isFull(): boolean {
        return this.chickens.length === ChickenCoop.CAPACITY;
    }

    public isEmpty(): boolean {
        return this.chickens.length === 0;
    }

    public toString(): string {
        let localChickens = "";
        for (const chicken of this.chickens) {
            localChickens += chicken.toString() + "\t";
        }
        return `ChickenCoop{chickenCoopId=${this.chickenCoopId}, chickens=\n${localChickens}}`;
    }

    public getChickenCoopId(): number { return this.chickenCoopId; }
    public setChickenCoopId(chickenCoopId: number): void { this.chickenCoopId = chickenCoopId; }
    public getCount(): number { return this.chickens.length; }
    public getCapacity(): number { return ChickenCoop.CAPACITY; }
}

class FileManager {
    private static readonly SEPARATOR = ";";
    private static readonly HEADER = "id;name;color;bornOnDate;molting";

    public static save(coop: ChickenCoop, fileName: string): void {
        let content = FileManager.HEADER + "\n";
        coop.resetIteration();
        let chicken = coop.next();
        
        while (chicken !== null) {
            const line = `${chicken.getId()}${FileManager.SEPARATOR}`
                       + `${FileManager.clean(chicken.getName())}${FileManager.SEPARATOR}`
                       + `${FileManager.clean(chicken.getColor())}${FileManager.SEPARATOR}`
                       + `${formatDate(chicken.getBornOnDate())}${FileManager.SEPARATOR}`
                       + `${chicken.isMolting()}\n`;
            content += line;
            chicken = coop.next();
        }
        fs.writeFileSync(fileName, content, 'utf8');
    }

    public static load(fileName: string, chickenCoopId: number): ChickenCoop {
        const coop = new ChickenCoop(chickenCoopId, []);
        
        if (!FileManager.exists(fileName)) {
            return coop;
        }

        const data = fs.readFileSync(fileName, 'utf8');
        const lines = data.split('\n');
        
        for (let i = 1; i < lines.length; i++) {
            if (coop.isFull()) break;
            const line = lines[i].trim();
            if (line) {
                const chicken = FileManager.toChicken(line);
                if (chicken) {
                    coop.add(chicken);
                }
            }
        }
        return coop;
    }

    public static exists(fileName: string): boolean {
        return fs.existsSync(fileName);
    }

    public static fullPath(fileName: string): string {
        return path.resolve(fileName);
    }

    private static toChicken(line: string): Chicken | null {
        const parts = line.split(FileManager.SEPARATOR);
        if (parts.length !== 5) return null;

        try {
            const id = parseInt(parts[0].trim(), 10);
            const name = parts[1].trim();
            const color = parts[2].trim();
            
            const dateParts = parts[3].trim().split('-');
            const bornOnDate = new Date(
                parseInt(dateParts[0], 10), 
                parseInt(dateParts[1], 10) - 1, 
                parseInt(dateParts[2], 10)
            );

            const molting = parts[4].trim().toLowerCase() === 'true';

            const chicken = new Chicken(id, name, color, bornOnDate, 0, molting);
            chicken.setAgeInYears();
            return chicken;
        } catch (e) {
            return null;
        }
    }

    private static clean(text: string): string {
        return text.replace(new RegExp(FileManager.SEPARATOR, 'g'), ",");
    }
}

class ChickenFarmApp {
    private static readonly WIDTH = 60;
    private static readonly FILE_NAME = "chickens