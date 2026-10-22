import * as readline from 'readline/promises';
import { stdin as input, stdout as output } from 'process';

class Poop {
    constructor(
        public id: number,
        public color: string,
        public consistency: string // Usamos string en lugar de char
    ) {}

    toString(): string {
        return `Poop{id=${this.id}, color=${this.color}, consistency=${this.consistency}}`;
    }
}

class Egg {
    constructor(
        public id: number,
        public size: string
    ) {}

    toString(): string {
        return `Egg{id=${this.id}, size=${this.size}}`;
    }
}

class ChickenFarmer {
  
}

class Chicken {
    public ageInYears: number;

    constructor(
        public id: number,
        public name: string,
        public color: string,
        public bornOnDate: Date,
        public molting: boolean
    ) {
        this.ageInYears = this.computeAgeInYears();
    }

    doStuff(): void {
        this.cluck();
        this.wander();
        this.drink(1.3);
        console.log(`poop --> ${this.poop().toString()}`);
        console.log(`egg --> ${this.layAnEgg().toString()}`);
    }

    private cluck(): void {
        console.log(`The chicken ${this.name} is clucking ...`);
    }

    private wander(): void {
        console.log(`The chicken ${this.name} is wandering ...`);
    }

    private eat(): void {
        console.log(`The chicken ${this.name} is eating ...`);
    }

    private drink(amountOfWater: number): void {
        console.log(`The chicken ${this.name} is drinking ${amountOfWater} ml of water`);
    }

    private poop(): Poop {
        return new Poop(2, "green", "M");
    }

    private layAnEgg(): Egg {
        return new Egg(2, "S");
    }

    computeAgeInYears(): number {
        const today = new Date();
        let age = today.getFullYear() - this.bornOnDate.getFullYear();
        
        const m = today.getMonth() - this.bornOnDate.getMonth();
        if (m < 0 || (m === 0 && today.getDate() < this.bornOnDate.getDate())) {
            age--;
        }
        return age;
    }

    toString(): string {

        const dateStr = this.bornOnDate.toDateString();
        return `Chicken{id=${this.id}, name=${this.name}, color=${this.color}, bornOnDate=${dateStr}, age=${this.ageInYears}, molting=${this.molting}}`;
    }
}

class ChickenCoop {
    public chickens: (Chicken | null)[] = new Array(10).fill(null);
    public currentIndex: number = 0;
    public numberOfChickens: number = 0;

    constructor(public chickenCoopId: number) {}

    add(chicken: Chicken): void {
        if (this.numberOfChickens < this.chickens.length) {
            this.chickens[this.numberOfChickens] = chicken;
            this.numberOfChickens++;
            console.log(`The chicken ${chicken.name} was added to the coop`);
        } else {
            console.log("The coop is full, we can not add any more chickens");
        }
    }

    remove(chickenId: number): void {
        let position = -1;

        for (let i = 0; i < this.numberOfChickens; i++) {
            if (this.chickens[i] && this.chickens[i]!.id === chickenId) {
                position = i;
                break;
            }
        }

        if (position === -1) {
            console.log(`There is no chicken with id ${chickenId}`);
            return;
        }

        const removedName = this.chickens[position]!.name;


        for (let i = position; i < this.numberOfChickens - 1; i++) {
            this.chickens[i] = this.chickens[i + 1];
        }

        this.chickens[this.numberOfChickens - 1] = null;
        this.numberOfChickens--;
        console.log(`The chicken ${removedName} was removed from the coop`);
    }

    resetIteration(): void {
        this.currentIndex = 0;
    }

    hasNext(): boolean {
        return this.currentIndex < this.numberOfChickens;
    }

    next(): Chicken | null {
        if (this.hasNext()) {
            const chicken = this.chickens[this.currentIndex];
            this.currentIndex++;
            return chicken;
        }
        return null;
    }

    toString(): string {
        const chickensStr = "[" + this.chickens.map(c => c ? c.toString() : "null").join(", ") + "]";
        return `ChickenCoop{chickenCoopId=${this.chickenCoopId}, chickens=${chickensStr}}`;
    }
}


class ChickenFarmApp {
    static rl = readline.createInterface({ input, output });
    
    static nextChickenId = 1;
    static nextEggId = 1;
    static nextPoopId = 1;
    static nextCoopId = 1;

    static async readChicken(): Promise<Chicken> {
        const id = this.nextChickenId++;
        console.log(`Chicken id: ${id}`);

        const name = await this.rl.question("Name: ");
        const color = await this.rl.question("Color: ");
        
        const yearStr = await this.rl.question("Year of birth (example 2024): ");
        const year = parseInt(yearStr);
        
        const monthStr = await this.rl.question("Month of birth (1-12): ");
        const month = parseInt(monthStr);
        
        const dayStr = await this.rl.question("Day of birth: ");
        const day = parseInt(dayStr);

        const moltingStr = await this.rl.question("Is it molting? (true/false): ");
        const molting = moltingStr.trim().toLowerCase() === 'true';

   
        const bornOnDate = new Date(year, month - 1, day);

        return new Chicken(id, name, color, bornOnDate, molting);
    }

    static showChickens(coop: ChickenCoop, doStuff: boolean): void {
        if (coop.numberOfChickens === 0) {
            console.log("The coop is empty");
            return;
        }

        coop.resetIteration();
        while (coop.hasNext()) {
            const current = coop.next();
            if (current) {
                console.log(`chickens --> ${current.toString()}`);
                console.log(`Chicken's age: ${current.computeAgeInYears()}`);
                if (doStuff) {
                    current.doStuff();
                }
            }
        }
    }

    static async main() {
        console.log("Daniel Sangoquiza Chicken Farm App");
        console.log("==============================");

        const coopId = this.nextCoopId++;
        const coop = new ChickenCoop(coopId);
        console.log(`Chicken coop id: ${coopId}`);

        let quantityToAdd = 0;
        do {
            const qtyStr = await this.rl.question("\nHow many chickens do you want to add? (1-10): ");
            quantityToAdd = parseInt(qtyStr);
            if (isNaN(quantityToAdd) || quantityToAdd < 1 || quantityToAdd > 10) {
                console.log("Invalid quantity, it must be between 1 and 10");
            }
        } while (isNaN(quantityToAdd) || quantityToAdd < 1 || quantityToAdd > 10);

        for (let i = 0; i < quantityToAdd; i++) {
            console.log(`\n--- Chicken ${i + 1} ---`);
            const chicken = await this.readChicken();
            coop.add(chicken);
        }

        console.log("\n--- Egg ---");
        const eggId = this.nextEggId++;
        console.log(`Egg id: ${eggId}`);
        const eggSize = (await this.rl.question("Egg size (S/M/L): ")).trim().toUpperCase().charAt(0) || 'M';
        const egg = new Egg(eggId, eggSize);

        console.log("\n--- Poop ---");
        const poopId = this.nextPoopId++;
        console.log(`Poop id: ${poopId}`);
        const poopColor = await this.rl.question("Poop color: ");
        const poopSize = (await this.rl.question("Poop size (S/M/L): ")).trim().toUpperCase().charAt(0) || 'M';
        const poop = new Poop(poopId, poopColor, poopSize);

        console.log(`\negg --> ${egg.toString()}`);
        console.log(`poop --> ${poop.toString()}`);

        console.log("\n=== Chickens in the coop ===");
        this.showChickens(coop, true);
        console.log(`number of chickens --> ${coop.numberOfChickens}`);

        let quantityToRemove = -1;
        do {
            const qtyStr = await this.rl.question(`\nHow many chickens do you want to remove? (0-${coop.numberOfChickens}): `);
            quantityToRemove = parseInt(qtyStr);
            if (isNaN(quantityToRemove) || quantityToRemove < 0 || quantityToRemove > coop.numberOfChickens) {
                console.log(`Invalid quantity, it must be between 0 and ${coop.numberOfChickens}`);
            }
        } while (isNaN(quantityToRemove) || quantityToRemove < 0 || quantityToRemove > coop.numberOfChickens);

        for (let i = 0; i < quantityToRemove; i++) {
            const idStr = await this.rl.question("Enter the id of the chicken to remove: ");
            coop.remove(parseInt(idStr));
        }

        console.log("\n=== Chickens in the coop after removing ===");
        this.showChickens(coop, false);
        console.log(`number of chickens --> ${coop.numberOfChickens}`);

       
        this.rl.close();
    }
}


ChickenFarmApp.main();