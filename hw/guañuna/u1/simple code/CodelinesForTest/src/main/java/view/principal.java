/*
package view;

// Importamos la clase Function2 que está en el paquete 'model'
import model.objetos;

public class principal {
    public static void main(String[] args) {
        
        System.out.println("=== CREANDO OBJETOS ===");

        // 1. Crear objeto usando el CONSTRUCTOR CON PARÁMETROS
        // 'new' reserva memoria para el objeto
        objetos obj1 = new objetos("Cristopher", 22);
        
        // 2. Crear objeto usando el CONSTRUCTOR VACÍO
        objetos obj2 = new objetos();
        
        // Luego le asignamos datos usando los SETTERS
        obj2.setNombre("Ana");
        obj2.setEdad(20);

        System.out.println("\n=== MOSTRANDO OBJETOS ===");
        
        // Al imprimir el objeto, Java llama automáticamente al toString()
        System.out.println("Objeto 1: " + obj1.toString());
        System.out.println("Objeto 2: " + obj2.toString());
        
        // También podemos usar los GETTERS para obtener datos individuales
        System.out.println("\nNombre del Objeto 1 (usando getter): " + obj1.getNombre());
        System.out.println("Edad del Objeto 2 (usando getter): " + obj2.getEdad());
    }
}
*/
/*
package view;

import model.objetos;
import java.util.Scanner;
import java.util.ArrayList; // Importamos la colección
import java.util.List;      // Importamos la interfaz List

public class principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        
        // AQUÍ ESTÁ EL CAMBIO: Creamos una lista vacía para guardar MUCHOS objetos
        List<objetos> listaObjetos = new ArrayList<>();

        while (opcion != 3) {
            System.out.println("\n=== MENÚ JAVA ===");
            System.out.println("1. Crear Objeto");
            System.out.println("2. Mostrar Todos los Objetos");
            System.out.println("3. Salir");
            System.out.print("Seleccione: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese nombre: ");
                        String nombre = scanner.nextLine();
                        
                        System.out.print("Ingrese edad: ");
                        int edad = Integer.parseInt(scanner.nextLine());
                        
                        // Creamos el objeto y lo AÑADIMOS a la lista
                        objetos nuevoObjeto = new objetos(nombre, edad);
                        listaObjetos.add(nuevoObjeto); 
                        System.out.println("¡Objeto agregado a la lista!");
                        break;
                        
                    case 2:
                        // Verificamos si la lista está vacía
                        if (listaObjetos.isEmpty()) {
                            System.out.println("No hay objetos creados aún.");
                        } else {
                            System.out.println("--- LISTA DE OBJETOS ---");
                            // Recorremos la lista con un bucle for-each
                            for (objetos obj : listaObjetos) {
                                System.out.println(obj.toString());
                            }
                        }
                        break;
                        
                    case 3:
                        System.out.println("Saliendo...");
                        break;
                        
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un número.");
                opcion = 0; 
            }
        }
        scanner.close();
    }
}
*/
/*
package view;

// Importamos nuestra clase del paquete model
import model.objetos; 

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class principal {

    // Ruta del archivo JSON (se creará en la carpeta principal de tu proyecto)
    private static final String RUTA_ARCHIVO = "datos.json";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        
        // 1. Cargar datos al iniciar (Persistencia)
        List<objetos> listaObjetos = cargarDesdeJSON();

        while (opcion != 4) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Crear Objeto");
            System.out.println("2. Mostrar Todos los Objetos");
            System.out.println("3. Guardar en JSON");
            System.out.println("4. Salir (Guarda automáticamente)");
            System.out.print("Seleccione: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese nombre: ");
                        String nombre = scanner.nextLine();
                        
                        System.out.print("Ingrese edad: ");
                        int edad = Integer.parseInt(scanner.nextLine());
                        
                        // Instanciamos usando tu clase 'objetos'
                        objetos nuevoObjeto = new objetos(nombre, edad);
                        listaObjetos.add(nuevoObjeto); 
                        System.out.println("¡Objeto agregado a la lista!");
                        break;
                        
                    case 2:
                        if (listaObjetos.isEmpty()) {
                            System.out.println("No hay objetos creados aún.");
                        } else {
                            System.out.println("--- LISTA DE OBJETOS ---");
                            for (objetos obj : listaObjetos) {
                                System.out.println(obj.toString());
                            }
                        }
                        break;
                        
                    case 3:
                        guardarEnJSON(listaObjetos);
                        break;
                        
                    case 4:
                        guardarEnJSON(listaObjetos); // Guardar antes de salir
                        System.out.println("Saliendo...");
                        break;
                        
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un número.");
                opcion = 0; 
            }
        }
        scanner.close();
    }

    // ==========================================
    // MÉTODOS DE PERSISTENCIA JSON
    // ==========================================
    
    private static void guardarEnJSON(List<objetos> lista) {
        StringBuilder json = new StringBuilder();
        json.append("[\n"); 
        
        for (int i = 0; i < lista.size(); i++) {
            objetos obj = lista.get(i);
            json.append("  {\n");
            json.append("    \"nombre\": \"").append(obj.getNombre()).append("\",\n");
            json.append("    \"edad\": ").append(obj.getEdad()).append("\n");
            json.append("  }");
            
            if (i < lista.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        json.append("]");

        try (FileWriter writer = new FileWriter(RUTA_ARCHIVO)) {
            writer.write(json.toString());
            System.out.println("Datos guardados exitosamente en " + RUTA_ARCHIVO);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    private static List<objetos> cargarDesdeJSON() {
        List<objetos> lista = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);
        
        if (!archivo.exists()) {
            return lista; 
        }

        try {
            String contenido = new String(Files.readAllBytes(Paths.get(RUTA_ARCHIVO)));
            
            // Parseo manual básico del JSON
            String[] bloques = contenido.split("\\{");
            for (String bloque : bloques) {
                if (bloque.contains("nombre")) {
                    String nombre = bloque.split("\"nombre\": \"")[1].split("\"")[0];
                    String edadStr = bloque.split("\"edad\": ")[1].split("\n")[0].replace(",", "").trim();
                    int edad = Integer.parseInt(edadStr);
                    
                    lista.add(new objetos(nombre, edad));
                }
            }
            System.out.println("Datos cargados desde " + RUTA_ARCHIVO);
        } catch (Exception e) {
            System.out.println("Error al leer JSON: " + e.getMessage());
        }
        return lista;
    }
}
*/
/*
package view;

import model.objetos; 

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class principal {

    // Rutas de los archivos
    private static final String RUTA_JSON = "datos.json";
    private static final String RUTA_CSV = "datos_exportados.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        // Cargar datos al iniciar
        List<objetos> listaObjetos = cargarDesdeJSON();

        while (opcion != 5) { // Cambiamos la condición a 5
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Crear Objeto");
            System.out.println("2. Mostrar Todos los Objetos");
            System.out.println("3. Guardar en JSON");
            System.out.println("4. Exportar a CSV"); // NUEVA OPCIÓN
            System.out.println("5. Salir (Guarda automáticamente)");
            System.out.print("Seleccione: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese edad: ");
                        int edad = Integer.parseInt(scanner.nextLine());
                        
                        listaObjetos.add(new objetos(nombre, edad)); 
                        System.out.println("¡Objeto agregado a la lista!");
                        break;
                        
                    case 2:
                        if (listaObjetos.isEmpty()) {
                            System.out.println("No hay objetos creados aún.");
                        } else {
                            System.out.println("--- LISTA DE OBJETOS ---");
                            for (objetos obj : listaObjetos) {
                                System.out.println(obj.toString());
                            }
                        }
                        break;
                        
                    case 3:
                        guardarEnJSON(listaObjetos);
                        break;
                        
                    case 4:
                        // Llamamos al nuevo método
                        exportarACSV(listaObjetos);
                        break;
                        
                    case 5:
                        guardarEnJSON(listaObjetos); 
                        System.out.println("Saliendo...");
                        break;
                        
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un número.");
                opcion = 0; 
            }
        }
        scanner.close();
    }

    // ==========================================
    // MÉTODO PARA EXPORTAR A CSV
    // ==========================================
    private static void exportarACSV(List<objetos> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay datos para exportar.");
            return;
        }

        // Usamos BufferedWriter para escribir el archivo de texto
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_CSV))) {
            
            // 1. Escribimos la cabecera (los títulos de las columnas)
            writer.write("Nombre,Edad");
            writer.newLine(); // Salto de línea
            
            // 2. Recorremos la lista y escribimos cada objeto
            for (objetos obj : lista) {
                // Escribimos "nombre,edad"
                writer.write(obj.getNombre() + "," + obj.getEdad());
                writer.newLine(); // Salto de línea para el siguiente registro
            }
            
            System.out.println("¡Datos exportados exitosamente a " + RUTA_CSV + "!");
            System.out.println("Puedes abrir este archivo con Excel o Bloc de Notas.");
            
        } catch (IOException e) {
            System.out.println("Error al exportar CSV: " + e.getMessage());
        }
    }

    // ==========================================
    // MÉTODOS DE PERSISTENCIA JSON (Se mantienen igual)
    // ==========================================
    
    private static void guardarEnJSON(List<objetos> lista) {
        StringBuilder json = new StringBuilder();
        json.append("[\n"); 
        
        for (int i = 0; i < lista.size(); i++) {
            objetos obj = lista.get(i);
            json.append("  {\n");
            json.append("    \"nombre\": \"").append(obj.getNombre()).append("\",\n");
            json.append("    \"edad\": ").append(obj.getEdad()).append("\n");
            json.append("  }");
            
            if (i < lista.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        json.append("]");

        try (FileWriter writer = new FileWriter(RUTA_JSON)) {
            writer.write(json.toString());
            System.out.println("Datos guardados exitosamente en " + RUTA_JSON);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    private static List<objetos> cargarDesdeJSON() {
        List<objetos> lista = new ArrayList<>();
        File archivo = new File(RUTA_JSON);
        
        if (!archivo.exists()) {
            return lista; 
        }

        try {
            String contenido = new String(Files.readAllBytes(Paths.get(RUTA_JSON)));
            
            String[] bloques = contenido.split("\\{");
            for (String bloque : bloques) {
                if (bloque.contains("nombre")) {
                    String nombre = bloque.split("\"nombre\": \"")[1].split("\"")[0];
                    String edadStr = bloque.split("\"edad\": ")[1].split("\n")[0].replace(",", "").trim();
                    int edad = Integer.parseInt(edadStr);
                    
                    lista.add(new objetos(nombre, edad));
                }
            }
            System.out.println("Datos cargados desde " + RUTA_JSON);
        } catch (Exception e) {
            System.out.println("Error al leer JSON: " + e.getMessage());
        }
        return lista;
    }
}
*/