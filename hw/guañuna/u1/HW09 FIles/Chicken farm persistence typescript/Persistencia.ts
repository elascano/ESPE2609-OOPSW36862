import * as fs from 'fs';
import { ChickenCoop } from './ChickenCoop';
import { Chicken } from './Chicken';

const ARCHIVO_JSON = 'chickencoop.json';

export class Persistencia {
    public static guardar(coop: ChickenCoop): void {
        try {
            // Convertimos el objeto a JSON string con formato bonito (indentación de 4)
            const data = JSON.stringify(coop, null, 4);
            fs.writeFileSync(ARCHIVO_JSON, data, 'utf-8');
            console.log(`>> Datos guardados exitosamente en ${ARCHIVO_JSON}`);
        } catch (error) {
            console.log(`>> Error al guardar los datos: ${error}`);
        }
    }

    public static cargar(): ChickenCoop | null {
        if (!fs.existsSync(ARCHIVO_JSON)) {
            console.log(">> No se encontró archivo previo. Creando un corral nuevo.");
            return null;
        }

        try {
            const data = fs.readFileSync(ARCHIVO_JSON, 'utf-8');
            const json = JSON.parse(data);
            return ChickenCoop.fromJSON(json);
        } catch (error) {
            console.log(`>> Error al cargar los datos: ${error}`);
            return null;
        }
    }
}