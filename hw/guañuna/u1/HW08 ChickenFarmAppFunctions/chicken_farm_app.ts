import * as readline from "node:readline";

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
    terminal: true // Volvemos a true para que VS Code no duplique los inputs
});

// Función ask tradicional
function ask(question: string): Promise<string> {
    return new Promise((resolve) => {
        rl.question(question, (answer) => {
            resolve(answer.trim());
        });
    });
}

async function readInteger(
    question: string,
    minimum: number,
    maximum: number
): Promise<number> {
    while (true) {
        // TRUCO: Si hay un salto de línea "basura" esperando, lo limpiamos
        if (rl.listenerCount('line') > 0) {
            // Esto es solo para TypeScript, en JS puro no hace falta
        }

        const input = await ask(question + " ");

        // Si la entrada está vacía, volvemos a preguntar sin imprimir error
        if (input === "") continue;

        const value = Number(input);

        if (
            Number.isInteger(value) &&
            value >= minimum &&
            value <= maximum
        ) {
            return value;
        }

        console.log(
            `Enter a valid integer between ${minimum} and ${maximum}.`
        );
    }
}

class Egg {
    public id: number;
    public size: string;

    constructor(id: number, size: string) {
        this.id = id;
        this.size = size;
    }

    toString(): string {
        return `Egg{id=${this.id}, size=${this.size}}`;
    }
}

class Poop {
    public id: number;
    public color: string;
    public consistency: string;

    constructor(id: number, color: string, consistency: string) {
        this.id = id;
        this.color = color;
        this.consistency = consistency;
    }

    toString(): string {
        return `Poop{id=${this.id}, color=${this.color}, consistency=${this.consistency}}`;
    }
}

class Chicken {
    public id: number;
    public name: string;
    public color: string;
    public bornOnDate: Date;
    public molting: boolean;
    public ageInYears: number = 0;

    constructor(
        id: number,
        name: string,
        color: string,
        bornOnDate: Date,
        molting: boolean
    ) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.bornOnDate = bornOnDate;
        this.molting = molting;
    }

    computeAgeInYears(): number {
        const today = new Date();
        let age = today.getFullYear() - this.bornOnDate.getFullYear();

        const birthdayHasNotOccurred =
            today.getMonth() < this.bornOnDate.getMonth() ||
            (
                today.getMonth() === this.bornOnDate.getMonth() &&
                today.getDate() < this.bornOnDate.getDate()
            );

        if (birthdayHasNotOccurred) {
            age--;
        }

        return age;
    }

    cluck(): void {
        console.log(`The chicken ${this.name} is clucking...`);
    }

    wander(): void {
        console.log(`The chicken ${this.name} is wandering...`);
    }

    drink(amountOfWater: number): void {
        console.log(
            `The chicken ${this.name} is drinking ${amountOfWater} liters of water...`
        );
    }

    poop(): Poop {
        return new Poop(2, "green", "M");
    }

    layAnEgg(): Egg {
        return new Egg(2, "S");
    }

    doStuff(): void {
        this.cluck();
        this.wander();
        this.drink(1.3);

        console.log(`poop --> ${this.poop()}`);
        console.log(`egg --> ${this.layAnEgg()}`);
    }

    toString(): string {
        return `Chicken{id=${this.id}, name=${this.name}, color=${this.color}, bornOnDate=${this.bornOnDate.toDateString()}, age=${this.computeAgeInYears()}, molting=${this.molting}}`;
    }
}

class ChickenCoop {
    public chickenCoopId: number;
    private chickens: (Chicken | null)[];
    private currentIndex: number = 0;
    private iterationIndex: number = 0;

    constructor(chickenCoopId: number, capacity: number = 10) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = new Array<Chicken | null>(capacity).fill(null);
    }

    async add(): Promise<void> {
        if (this.currentIndex >= this.chickens.length) {
            console.log("The chicken coop is full.");
            return;
        }

        const id = await readInteger("Enter chicken ID (1-10):", 1, 10);

        for (let i = 0; i < this.currentIndex; i++) {
            const chicken = this.chickens[i];

            if (chicken !== null && chicken !== undefined && chicken.id === id) {
                console.log("This ID is already occupied.");
                console.log("Remove this chicken first if you want to replace it.");
                return;
            }
        }

        const name = await ask("Enter chicken name: ");
        const color = await ask("Enter chicken color: ");

        console.log("Enter chicken birth date:");
        const year = await readInteger("Year:", 1, 9999);
        const month = await readInteger("Month (1-12):", 1, 12);

        let bornOnDate: Date;

        while (true) {
            const day = await readInteger("Day:", 1, 31);
            const candidate = new Date(year, month - 1, day);

            if (
                candidate.getFullYear() === year &&
                candidate.getMonth() === month - 1 &&
                candidate.getDate() === day
            ) {
                bornOnDate = candidate;
                break;
            }

            console.log("Invalid date. Enter the day again.");
        }

        let molting: boolean;

        while (true) {
            const answer = (await ask(
                "Is the chicken molting? (true/false): "
            )).trim().toLowerCase();

            if (answer === "true" || answer === "false") {
                molting = answer === "true";
                break;
            }

            console.log("Invalid input. Enter true or false.");
        }

        const chicken = new Chicken(id, name, color, bornOnDate, molting);
        chicken.ageInYears = chicken.computeAgeInYears();

        this.chickens[this.currentIndex] = chicken;
        this.currentIndex++;

        console.log("Chicken added successfully.");
    }

    async remove(): Promise<void> {
        const chickenId = await readInteger(
            "Enter the ID of the chicken to remove:",
            1,
            10
        );

        for (let i = 0; i < this.currentIndex; i++) {
            const chicken = this.chickens[i];

            if (chicken?.id === chickenId) {
                console.log("Chicken found:");
                console.log(chicken.toString());

                const answer = (await ask(
                    "Are you sure you want to remove this chicken? (yes/no): "
                )).trim().toLowerCase();

                if (answer === "yes") {
                    for (let j = i; j < this.currentIndex - 1; j++) {
                        this.chickens[j] = this.chickens[j + 1] ?? null;
                    }

                    this.currentIndex--;
                    this.chickens[this.currentIndex] = null;

                    console.log("Chicken removed successfully.");
                } else {
                    console.log("Chicken removal cancelled.");
                }

                return;
            }
        }

        console.log(`Chicken with ID ${chickenId} not found.`);
    }

    resetIteration(): void {
        this.iterationIndex = 0;
    }

    next(): Chicken | null {
        if (this.iterationIndex < this.currentIndex) {
            const chicken = this.chickens[this.iterationIndex];
            this.iterationIndex++;

            return chicken ?? null;
        }

        return null;
    }
}

async function main(): Promise<void> {
    console.log("Chicken Farm App Cristopher Guanuna");
    console.log("===================================");

    const chicken = new Chicken(
        1, "Lucy", "brown and white",
        new Date(2020, 6, 10), true
    );

    const egg = new Egg(1, "H");
    const poop = new Poop(1, "brown", "S");

    const chicken1 = new Chicken(
        2, "Renata", "White",
        new Date(2025, 11, 20), false
    );

    chicken.ageInYears = chicken.computeAgeInYears();
    chicken1.ageInYears = chicken1.computeAgeInYears();

    console.log(`chicken --> ${chicken}`);
    console.log(`egg --> ${egg}`);
    console.log(`poop --> ${poop}`);
    console.log(`chicken 2 --> ${chicken1}`);

    chicken.doStuff();

    const chickens: (Chicken | null)[] = new Array(10).fill(null);

    chickens[0] = new Chicken(
        3, "Pepita", "black",
        new Date(2020, 1, 2), true
    );

    chickens[1] = chicken;

    for (const item of chickens) {
        console.log(`chickens --> ${item}`);
    }

    for (let i = 0; i < chickens.length; i++) {
        chickens[i] = new Chicken(
            i,
            `chicken${i + 1}`,
            "white",
            new Date(2015 + i, 2, 3),
            true
        );

        chickens[i]!.ageInYears = chickens[i]!.computeAgeInYears();
    }

    for (const item of chickens) {
        console.log(`chickens --> ${item}`);
    }

    const chickenCoop = new ChickenCoop(1);

    while (true) {
        console.log("\n=== CHICKEN COOP MENU ===");
        console.log("1. Add chicken");
        console.log("2. Remove chicken");
        console.log("3. Show all chickens");
        console.log("4. Exit");

        const option = await readInteger("Choose an option:", 1, 4);

        switch (option) {
            case 1:
                await chickenCoop.add();
                break;

            case 2:
                await chickenCoop.remove();
                break;

            case 3: {
                chickenCoop.resetIteration();

                let chickenItem: Chicken | null;

                while ((chickenItem = chickenCoop.next()) !== null) {
                    console.log(chickenItem.toString());
                }

                break;
            }

            case 4:
                console.log("Exiting menu...");
                rl.close();
                return;
        }
    }
}

main().catch((error: unknown) => {
    console.error("An error occurred:", error);
    rl.close();
});