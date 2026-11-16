"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || (function () {
    var ownKeys = function(o) {
        ownKeys = Object.getOwnPropertyNames || function (o) {
            var ar = [];
            for (var k in o) if (Object.prototype.hasOwnProperty.call(o, k)) ar[ar.length] = k;
            return ar;
        };
        return ownKeys(o);
    };
    return function (mod) {
        if (mod && mod.__esModule) return mod;
        var result = {};
        if (mod != null) for (var k = ownKeys(mod), i = 0; i < k.length; i++) if (k[i] !== "default") __createBinding(result, mod, k[i]);
        __setModuleDefault(result, mod);
        return result;
    };
})();
Object.defineProperty(exports, "__esModule", { value: true });
const readline = __importStar(require("readline"));
const truck_1 = require("../model/truck");
const truck_management_1 = require("../model/truck_management");
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});
function askQuestion(query) {
    return new Promise(resolve => rl.question(query, resolve));
}
async function main() {
    const manager = new truck_management_1.TruckManagement();
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
            const new_truck = new truck_1.Truck(id_input, brand, capacity, year);
            manager.add_truck(new_truck);
            console.log("¡Camión añadido y guardado correctamente!");
        }
        else if (option === '2') {
            console.log("\n--- LISTA DE CAMIONES ---");
            const trucks = manager.get_all_trucks();
            if (trucks.length === 0) {
                console.log("No hay camiones registrados.");
            }
            else {
                for (const truck of trucks) {
                    console.log(truck.toString());
                }
            }
        }
        else if (option === '3') {
            console.log("Saliendo del programa...");
            rl.close();
            break;
        }
        else {
            console.log("Opción no válida. Intente de nuevo.");
        }
    }
}
main();
