import * as readline from 'node:readline';
import * as fs from 'node:fs'; // Módulo para archivos

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

const RUTA_ARCHIVO = 'datos.json';

// ==========================================
// MÉTODOS DE PERSISTENCIA JSON
// ==========================================

function guardarEnJSON(lista: Function2[]) {
    // Convertimos los objetos a un formato plano (diccionario)
    const datosPlanos = lista.map(obj => ({
        nombre: obj.getNombre(),
        edad: obj.getEdad()
    }));

    // JSON.stringify convierte el objeto JS a un String con formato JSON
    const jsonString = JSON.stringify(datosPlanos, null, 4); // null, 4 para que sea legible

    // fs.writeFileSync escribe el archivo de forma síncrona
    fs.writeFileSync(RUTA_ARCHIVO, jsonString, 'utf-8');
    console.log(`Datos guardados en ${RUTA_ARCHIVO}`);
}

function cargarDesdeJSON(): Function2[] {
    const lista: Function2[] = [];

    // Verificamos si el archivo existe
    if (fs.existsSync(RUTA_ARCHIVO)) {
        const jsonString = fs.readFileSync(RUTA_ARCHIVO, 'utf-8');
        // JSON.parse convierte el texto JSON a un arreglo de objetos JS
        const datosParseados = JSON.parse(jsonString);

        for (const d of datosParseados) {
            lista.push(new Function2(d.nombre, d.edad));
        }
        console.log(`Datos cargados desde ${RUTA_ARCHIVO}`);
    }
    return lista;
}

// ==========================================
// MAIN
// ==========================================
const rl = readline.createInterface({ input: process.stdin, output: process.stdout });

// Cargamos los datos al iniciar el programa
const listaObjetos: Function2[] = cargarDesdeJSON();

const mostrarMenu = () => {
    console.log("\n=== MENÚ TYPESCRIPT (JSON) ===");
    console.log("1. Crear Objeto");
    console.log("2. Mostrar Todos");
    console.log("3. Guardar en JSON");
    console.log("4. Salir (Guarda automáticamente)");

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
            guardarEnJSON(listaObjetos); // Guardar antes de salir
            console.log("Saliendo...");
            rl.close();
        } else {
            console.log("Opción inválida.");
            mostrarMenu();
        }
    });
};

mostrarMenu();