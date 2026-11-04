import * as readline from 'node:readline';
import * as fs from 'node:fs';

class Function2 {
    private nombre: string;
    private edad: number;

    constructor(nombre: string = "", edad: number = 0) {
        this.nombre = nombre;
        this.edad = edad;
    }

    toString(): string {
        return `Function2{nombre=${this.nombre}, edad=${this.edad}}`;
    }
    getNombre(): string { return this.nombre; }
    getEdad(): number { return this.edad; }
}

const RUTA_JSON = 'datos.json';
const RUTA_CSV = 'datos_exportados.csv'; // NUEVA RUTA

// ==========================================
// PERSISTENCIA JSON (Se mantiene igual)
// ==========================================
function guardarEnJSON(lista: Function2[]) {
    const datosPlanos = lista.map(obj => ({
        nombre: obj.getNombre(),
        edad: obj.getEdad()
    }));
    const jsonString = JSON.stringify(datosPlanos, null, 4);
    fs.writeFileSync(RUTA_JSON, jsonString, 'utf-8');
    console.log(`Datos guardados en ${RUTA_JSON}`);
}

function cargarDesdeJSON(): Function2[] {
    const lista: Function2[] = [];
    if (fs.existsSync(RUTA_JSON)) {
        const jsonString = fs.readFileSync(RUTA_JSON, 'utf-8');
        const datosParseados = JSON.parse(jsonString);
        for (const d of datosParseados) {
            lista.push(new Function2(d.nombre, d.edad));
        }
        console.log(`Datos cargados desde ${RUTA_JSON}`);
    }
    return lista;
}

// ==========================================
// NUEVO: EXPORTAR A CSV
// ==========================================
function exportarACSV(lista: Function2[]) {
    if (lista.length === 0) {
        console.log("No hay datos para exportar.");
        return;
    }

    // Creamos un arreglo de strings, empezando por los encabezados
    const lineas: string[] = [];
    lineas.push("Nombre,Edad"); // Cabecera

    // Recorremos los objetos y los convertimos en texto separado por comas
    for (const obj of lista) {
        lineas.push(`${obj.getNombre()},${obj.getEdad()}`);
    }

    // Unimos todas las líneas con un salto de línea (\n)
    const contenidoCSV = lineas.join('\n');

    // Escribimos el archivo
    fs.writeFileSync(RUTA_CSV, contenidoCSV, 'utf-8');
    console.log(`¡Datos exportados exitosamente a ${RUTA_CSV}!`);
}

// ==========================================
// MAIN
// ==========================================
const rl = readline.createInterface({ input: process.stdin, output: process.stdout });
const listaObjetos: Function2[] = cargarDesdeJSON();

const mostrarMenu = () => {
    console.log("\n=== MENÚ TYPESCRIPT ===");
    console.log("1. Crear Objeto");
    console.log("2. Mostrar Todos");
    console.log("3. Guardar en JSON");
    console.log("4. Exportar a CSV"); // NUEVA OPCIÓN
    console.log("5. Salir (Guarda automáticamente)");

    rl.question("Seleccione: ", (opcion: string) => {
        if (opcion === "1") {
            rl.question("Ingrese nombre: ", (nombre: string) => {
                rl.question("Ingrese edad: ", (edadStr: string) => {
                    const edad = parseInt(edadStr);
                    if (!isNaN(edad)) {
                        listaObjetos.push(new Function2(nombre, edad));
                        console.log("¡Objeto agregado!");
                    } else {
                        console.log("Error: Edad inválida.");
                    }
                    mostrarMenu();
                });
            });
        } else if (opcion === "2") {
            if (listaObjetos.length === 0) {
                console.log("Lista vacía.");
            } else {
                listaObjetos.forEach((obj, i) => console.log(`${i + 1}. ${obj.toString()}`));
            }
            mostrarMenu();
        } else if (opcion === "3") {
            guardarEnJSON(listaObjetos);
            mostrarMenu();
        } else if (opcion === "4") {
            exportarACSV(listaObjetos); // Llamada a la nueva función
            mostrarMenu();
        } else if (opcion === "5") {
            guardarEnJSON(listaObjetos);
            console.log("Saliendo...");
            rl.close();
        } else {
            console.log("Opción inválida.");
            mostrarMenu();
        }
    });
};

mostrarMenu();