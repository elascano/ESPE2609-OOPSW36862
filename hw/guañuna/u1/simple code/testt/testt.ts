// @ts-nocheck
// (La línea de arriba le dice a VS Code que ignore los errores de TypeScript 
// porque estamos programando en JavaScript puro).

// ==========================================
// 1. DEFINICIÓN DE LA CLASE (El molde)
// ==========================================
class Function2 {

    // Declaración de propiedades (Opcional en JS, pero ayuda a que el editor no marque error)
    nombre;
    edad;

    // ==========================================
    // 2. CONSTRUCTOR
    // ==========================================
    // Se ejecuta automáticamente al usar 'new'. 
    // Los signos '=' asignan valores por defecto (simulando el constructor vacío).
    constructor(nombre = "", edad = 0) {
        // 'this.nombre' es el atributo del objeto.
        // 'nombre' (sin this) es el parámetro que llega desde afuera.
        this.nombre = nombre;
        this.edad = edad;
    }

    // ==========================================
    // 3. MÉTODO toString (Para mostrar el objeto)
    // ==========================================
    // Se ejecuta cuando imprimimos el objeto o llamamos a obj.toString().
    toString() {
        // Usamos Template Literals (las comillas invertidas ` `) para mezclar texto y variables fácilmente.
        return `Function2{nombre=${this.nombre}, edad=${this.edad}}`;
    }

    // ==========================================
    // 4. GETTERS Y SETTERS
    // ==========================================
    // Como en Java, sirven para leer (get) y modificar (set) los atributos de forma segura.

    getNombre() {
        return this.nombre; // Devuelve el valor actual
    }

    setNombre(nombre) {
        this.nombre = nombre; // Asigna un nuevo valor
    }

    getEdad() {
        return this.edad;
    }

    setEdad(edad) {
        this.edad = edad;
    }
}

// ==========================================
// 5. MAIN (Punto de entrada del programa)
// ==========================================
// Aquí creamos los objetos y llamamos a sus métodos.

console.log("=== CREANDO OBJETOS ===");

// 1. Instanciación con el constructor que recibe parámetros.
// Usamos 'new' igual que en Java.
const obj1 = new Function2("Cristopher", 22);

// 2. Instanciación con el constructor vacío (usa los valores por defecto "" y 0).
const obj2 = new Function2();

// 3. Modificando los datos del objeto vacío usando los SETTERS.
obj2.setNombre("Ana");
obj2.setEdad(20);

console.log("\n=== MOSTRANDO OBJETOS ===");

// Llamamos explícitamente al método toString() para ver los datos.
console.log("Objeto 1: " + obj1.toString());
console.log("Objeto 2: " + obj2.toString());

// Usando los GETTERS para acceder a datos específicos.
console.log("\nNombre del Objeto 1 (usando getter): " + obj1.getNombre());
console.log("Edad del Objeto 2 (usando getter): " + obj2.getEdad());