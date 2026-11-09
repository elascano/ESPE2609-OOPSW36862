package view;

import model.Truck;
import model.TruckManagement;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        TruckManagement manager = new TruckManagement();

        while (true) {
            System.out.println("\n--- GESTION DE CAMIONES ---");
            System.out.println("1. Anadir Camion");
            System.out.println("2. Ver todos los camiones");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.println("\n--- NUEVO CAMION ---");
                    System.out.print("Ingrese ID (ej. C001): ");
                    String id_input = scanner.nextLine();
                    
                    System.out.print("Ingrese Marca: ");
                    String brand = scanner.nextLine();
                    
                    int capacity = 0;
                    int year = 0;
                    
                    try {
                        System.out.print("Ingrese Capacidad (toneladas): ");
                        capacity = Integer.parseInt(scanner.nextLine());
                        
                        System.out.print("Ingrese Anio: ");
                        year = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Error: La capacidad y el anio deben ser numeros enteros.");
                        continue;
                    }

                    Truck new_truck = new Truck(id_input, brand, capacity, year);
                    manager.add_truck(new_truck);
                    System.out.println("Camion anadido y guardado correctamente!");
                    break;

                case "2":
                    System.out.println("\n--- LISTA DE CAMIONES ---");
                    List<Truck> trucks = manager.get_all_trucks();
                    if (trucks.isEmpty()) {
                        System.out.println("No hay camiones registrados.");
                    } else {
                        for (Truck truck : trucks) {
                            System.out.println(truck);
                        }
                    }
                    break;

                case "3":
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
                    break;
            }
        }
    }
}