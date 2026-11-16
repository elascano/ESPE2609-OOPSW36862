import * as readline from 'readline';
import { Truck } from '../model/truck';
import { TruckManagement } from '../model/truck_management';

const rl = readline.createInterface({
    input: process.stdin as any,
    output: process.stdout
});

function askQuestion(query: string): Promise<string> {
    return new Promise(resolve => rl.question(query, resolve));
}

async function main() {
    const manager = new TruckManagement();

    while (true) {
        console.log("\n--- GESTIÓN DE CAMIONES ---");
        console.log("1. Añadir Camión");
        console.log("2. Ver todos los camiones");
        console.log("3. Salir");

        const option = await askQuestion("Seleccione una opción: ");

        if (option === '1') {
            console.log("\n--- NUEVO CAMIÓN ---");
            const id_input = await askQuestion("Ingrese ID (ej. C001): ");
            const brand = await askQuestion("Ingrese Marca: ");

            const capacityStr = await askQuestion("Ingrese Capacidad (toneladas): ");
            const yearStr = await askQuestion("Ingrese Año: ");

            const capacity = parseInt(capacityStr);
            const year = parseInt(yearStr);

            if (isNaN(capacity) || isNaN(year)) {
                console.log("Error: La capacidad y el año deben ser números enteros.");
                continue;
            }

            const new_truck = new Truck(id_input, brand, capacity, year);
            manager.add_truck(new_truck);
            console.log("¡Camión añadido y guardado correctamente!");

        } else if (option === '2') {
            console.log("\n--- LISTA DE CAMIONES ---");
            const trucks = manager.get_all_trucks();
            if (trucks.length === 0) {
                console.log("No hay camiones registrados.");
            } else {
                for (const truck of trucks) {
                    console.log(truck.toString());
                }
            }

        } else if (option === '3') {
            console.log("Saliendo del programa...");
            rl.close();
            break;
        } else {
            console.log("Opción no válida. Intente de nuevo.");
        }
    }
}

main();