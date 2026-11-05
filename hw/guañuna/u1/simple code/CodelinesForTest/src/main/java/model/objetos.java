
package model;

public class objetos {
    
    // ==========================================
    // 1. ATRIBUTOS (Encapsulados)
    // ==========================================
    // 'private' significa que solo esta clase puede modificar estos datos.
    // Para acceder a ellos, usaremos getters y setters.
    private String nombre;
    private int edad;

    // ==========================================
    // 2. CONSTRUCTORES
    // ==========================================
    
    // Constructor VACÍO: Sirve para crear un objeto sin datos iniciales.
    // Luego se le asignan los datos con los setters.
    public objetos() {
    }

    // Constructor CON PARÁMETROS: Sirve para crear el objeto con datos de una vez.
    // 'this.nombre' se refiere al atributo de la clase.
    // 'nombre' (sin this) se refiere al parámetro que recibe el método.
    public objetos(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    // ==========================================
    // 3. MÉTODO toString (Para mostrar el objeto)
    // ==========================================
    // @Override indica que estamos sobrescribiendo un método que ya existe en Java.
    // Se ejecuta automáticamente cuando hacemos System.out.println(objeto).
    @Override
    public String toString() {
        return "Function2{" + "nombre=" + nombre + ", edad=" + edad + '}';
    }

    // ==========================================
    // 4. GETTERS Y SETTERS
    // ==========================================
    
    // GETTER de nombre: Devuelve el valor del atributo.
    public String getNombre() {
        return nombre;
    }

    // SETTER de nombre: Modifica el valor del atributo.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // GETTER de edad
    public int getEdad() {
        return edad;
    }

    // SETTER de edad
    public void setEdad(int edad) {
        this.edad = edad;
    }
}
