import * as readline from 'readline';

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
    setNombre(nombre: string): void { this.nombre = nombre; }
    getEdad(): number { return this.edad; }
    setEdad(edad: number): void { this.edad = edad; }
}

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

// AQUÍ ESTÁ EL CAMBIO: Declaramos un array de objetos de tipo Function2
const listaObjetos: Function2[] = [];

const mostrarMenu = () => {
    console.log("\n=== MENÚ TYPESCRIPT ===");
    console.log("1. Crear Objeto");
    console.log("2. Mostrar Todos los Objetos");
    console.log("3. Salir");

    rl.question("Seleccione: ", (opcion: string) => {
        if (opcion === "1") {
            rl.question("Ingrese nombre: ", (nombre: string) => {
                rl.question("Ingrese edad: ", (edadStr: string) => {
                    const edad = parseInt(edadStr);
                    if (isNaN(edad)) {
                        console.log("Error: La edad debe ser un número.");
                    } else {
                        const nuevoObjeto = new Function2(nombre, edad);
                        listaObjetos.push(nuevoObjeto); // Agregamos al array
                        console.log("¡Objeto agregado a la lista!");
                    }
                    mostrarMenu();
                });
            });
        } else if (opcion === "2") {
            if (listaObjetos.length === 0) {
                console.log("No hay objetos creados aún.");
            } else {
                console.log("--- LISTA DE OBJETOS ---");
                // Recorremos el array con forEach
                listaObjetos.forEach((obj, index) => {
                    console.log(`${index + 1}. ${obj.toString()}`);
                });
            }
            mostrarMenu();
        } else if (opcion === "3") {
            console.log("Saliendo...");
            rl.close();
        } else {
            console.log("Opción inválida.");
            mostrarMenu();
        }
    });
};

mostrarMenu();