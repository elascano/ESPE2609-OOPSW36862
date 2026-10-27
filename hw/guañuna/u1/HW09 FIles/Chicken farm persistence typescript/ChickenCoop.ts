import * as readline from 'readline';
import { Chicken } from './Chicken.js';

export class ChickenCoop {
    private chickenCoopId: number;
    private chickens: Chicken[];
    private currentIndex: number;

    constructor() {
        this.chickenCoopId = 1;
        this.chickens = [];

        // Gallinas por defecto
        this.chickens.push(new Chicken(1, "Lucy", "black", new Date(), false));
        this.chickens.push(new Chicken(2, "Alejandra", "white", new Date(), false));
        this.currentIndex = 2;
    }

    // Función auxiliar para pedir datos (equivalente al Scanner de Java)
    private async preguntar(rl: readline.Interface, texto: string): Promise<string> {
        return new Promise((resolve) => rl.question(texto, resolve));
    }

    public async add(rl: readline.Interface): Promise<void> {
        if (this.currentIndex >= 10) {
            console.log("The chicken coop is full.");
            return;
        }

        console.log("\n--- Add Chicken ---");

        // 1. Validar ID
        let idValido = false;
        let idIngresado = -1;
        while (!idValido) {
            const entrada = await this.preguntar(rl, "Enter chicken ID: ");
            idIngresado = parseInt(entrada, 10);

            if (isNaN(idIngresado)) {
                console.log(">> Error: ID must be a valid number.");
                continue;
            }

            if (idIngresado < 1 || idIngresado > 10) {
                console.log(">> Error: ID must be between 1 and 10 (coop capacity).");
                continue;
            }

            const existe = this.chickens.some(pollo => pollo.getId() === idIngresado);
            if (existe) {
                console.log(`>> Error: A chicken with this ID (${idIngresado}) already exists.`);
                console.log("--- Registered Chickens ---");
                this.chickens.forEach(pollo => console.log(`ID: ${pollo.getId()} | Name: ${pollo.getName()}`));
                console.log("---------------------------");
                console.log("Please try another ID.");
            } else {
                idValido = true;
            }
        }

        // 2. Validar Nombre
        let name = "";
        while (name.trim() === "") {
            name = await this.preguntar(rl, "Enter chicken name: ");
            if (name.trim() === "") console.log(">> Error: Name cannot be empty.");
        }

        // 3. Validar Color
        let color = "";
        while (color.trim() === "") {
            color = await this.preguntar(rl, "Enter chicken color: ");
            if (color.trim() === "") console.log(">> Error: Color cannot be empty.");
        }

        // 4. Validar Molting
        let molting = false;
        let moltingValido = false;
        while (!moltingValido) {
            const moltingStr = (await this.preguntar(rl, "Is the chicken molting? (true/false): ")).trim().toLowerCase();
            if (moltingStr === "true" || moltingStr === "false") {
                molting = moltingStr === "true";
                moltingValido = true;
            } else {
                console.log(">> Error: Please enter 'true' or 'false'.");
            }
        }

        // 5. Validar Fecha
        console.log("\nEnter the birth date of the chicken:");

        let year = 0;
        while (true) {
            const entrada = await this.preguntar(rl, "Year: ");
            year = parseInt(entrada, 10);
            if (!isNaN(year) && year > 2000 && year <= 2026) break;
            console.log(">> Error: Please enter a realistic year.");
        }

        let month = 0;
        while (true) {
            const entrada = await this.preguntar(rl, "Month (1-12): ");
            month = parseInt(entrada, 10);
            if (!isNaN(month) && month >= 1 && month <= 12) break;
            console.log(">> Error: Month must be between 1 and 12.");
        }

        let day = 0;
        while (true) {
            const entrada = await this.preguntar(rl, "Day (1-31): ");
            day = parseInt(entrada, 10);
            if (!isNaN(day) && day >= 1 && day <= 31) break;
            console.log(">> Error: Day must be between 1 and 31.");
        }

        // En JS los meses van de 0 a 11, por eso month - 1
        const bornOnDate = new Date(year, month - 1, day);

        const chicken = new Chicken(idIngresado, name.trim(), color.trim(), bornOnDate, molting);
        this.chickens.push(chicken);
        this.currentIndex++;
        console.log("Chicken added successfully.");
    }

    public async remove(rl: readline.Interface): Promise<void> {
        if (this.currentIndex === 0) {
            console.log("There are no chickens to remove.");
            return;
        }

        console.log("\n=== CHICKENS IN THE COOP ===");
        this.chickens.forEach(pollo => console.log(pollo.toString()));
        console.log("============================");

        let idABuscar = -1;
        let idValido = false;
        while (!idValido) {
            const entrada = await this.preguntar(rl, "Enter the ID of the chicken to remove: ");
            idABuscar = parseInt(entrada, 10);
            if (!isNaN(idABuscar)) {
                idValido = true;
            } else {
                console.log(">> Error: The ID must be a valid number.");
            }
        }

        const index = this.chickens.findIndex(pollo => pollo.getId() === idABuscar);

        if (index === -1) {
            console.log(`>> Error: No chicken found with ID ${idABuscar}.`);
            return;
        }

        this.chickens.splice(index, 1); // splice elimina el elemento del array
        this.currentIndex--;
        console.log(`Chicken with ID ${idABuscar} removed successfully.`);
    }

    // Métodos para guardar y cargar
    public toJSON(): any {
        return {
            chickenCoopId: this.chickenCoopId,
            chickens: this.chickens.map(pollo => ({
                id: pollo.getId(),
                name: pollo.getName(),
                color: (pollo as any).color, // Acceso temporal
                bornOnDate: pollo.getBornOnDate().toISOString().split('T')[0],
                molting: pollo.isMolting()
            })),
            currentIndex: this.currentIndex
        };
    }

    public static fromJSON(data: any): ChickenCoop {
        const coop = new ChickenCoop();
        coop.chickens = [];
        coop.chickenCoopId = data.chickenCoopId || 1;

        if (data.chickens) {
            data.chickens.forEach((c: any) => {
                const dateParts = c.bornOnDate.split('-');
                const fecha = new Date(parseInt(dateParts[0]), parseInt(dateParts[1]) - 1, parseInt(dateParts[2]));
                coop.chickens.push(new Chicken(c.id, c.name, c.color, fecha, c.molting));
            });
        }
        coop.currentIndex = coop.chickens.length;
        return coop;
    }
}